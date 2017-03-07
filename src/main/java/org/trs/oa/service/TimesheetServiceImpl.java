package org.trs.oa.service;

import org.trs.oa.dao.TimesheetDAO;
import org.trs.oa.dao.UserDAO;
import org.trs.oa.entity.*;
import org.trs.oa.exception.TimesheetException;
import org.trs.oa.service.activiti.timesheet.TsActivitiService;
import org.trs.oa.utility.OaUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.trs.oa.vo.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Timesheet operation on main database.
 * Created by daweizhuang on 9/27/16.
 */
@Service
public class TimesheetServiceImpl implements TimesheetService {

    private final static Logger LOGGER = Logger.getLogger(TimesheetServiceImpl.class);

    @Autowired
    TimesheetDAO timesheetDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    TsActivitiService tsActivitiService;

    @Override
    @Transactional(readOnly = true)
    public List<TimeSheetDateVO> getCurrentTimesheetDate(Integer offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(OaUtils.getMondayOfThisWeek());
        cal.add(Calendar.WEEK_OF_YEAR, offset);
        List<TimeSheetDateVO> resList = new ArrayList<>();
        for(int i = 0 ; i < 7; i++) {
            resList.add(new TimeSheetDateVO(cal.getTime()));
            cal.add(Calendar.DAY_OF_MONTH,1);
        }
        return resList;
    }

    @Override
    @Transactional(rollbackFor = {TimesheetException.class,RuntimeException.class})
    public void saveTs(Date dateOfMonday, Map<String, String> allInputs, int userId) throws TimesheetException {
        if(dateOfMonday == null){
            throw new TimesheetException("dateofmonday is null");
        }
        if(checkInputsForTs(allInputs)){
            throw new TimesheetException("input not valid");
        }
        try {
            for (String key : allInputs.keySet()) {
//                if( StringUtils.isEmpty(allInputs.get(key))){
//                    continue;//skip the empty inputs
//                }
                String[] splitKey = key.split("_");
                int projId = Integer.parseInt(splitKey[0]);
                int billCodeId = Integer.parseInt(splitKey[1]);
                String weekDay = splitKey[2];
                Date slotDate = OaUtils.getDateOfWeekDay(dateOfMonday, weekDay);
                TsSlotLookup slot = timesheetDAO.createSlot(slotDate);
                if (splitKey.length == 3) {
                    //hour information
                    int hour = StringUtils.isEmpty(allInputs.get(key))?0:Integer.parseInt(allInputs.get(key));
                    TsMain main = new TsMain();
                    main.setUser(new User(userId));
                    main.setBillCode(new TsBillCodeLookup(billCodeId));
                    main.setSlot(slot);
                    main.setValue(hour);
                    main.setComment(allInputs.get(key+"_comment"));
                    timesheetDAO.saveTimeSheetMain(main);
                } else if (splitKey.length == 4) {
                    //comment information, ignored
                    //String comment = splitKey[3];

                } else {
                    throw new TimesheetException("input not valid");
                }
            }
        }catch (NumberFormatException e){
            LOGGER.error(e);
            throw new TimesheetException("Cannot parse projId and billCodeId");
        }
    }

    @Override
    @Transactional
    public boolean submitTs(Date dateOfMonday, int userId, boolean isResubmit) {
        LOGGER.debug("Starting submiting Timesheet from :" + dateOfMonday + " userID: " + userId);
        //start activiti process
        tsActivitiService.submit(userId, dateOfMonday, isResubmit);//timesheet approval table id
        return true;
    }

    @Override
    public boolean approveOrDenyTs(int approverId, int approvalSubId, String comment, Boolean approved) {

        tsActivitiService.approve(approverId, approvalSubId, comment, approved);//timesheet approval table id
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public TsApproval getTimesheetStatus(Integer offset, int userId) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(OaUtils.getMondayOfThisWeek());
        cal.add(Calendar.WEEK_OF_YEAR, offset);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        List<TsApproval> appList = timesheetDAO.getTimeSheetApprovalStatus(cal.getTime(), userId);
        if (appList != null && appList.size() > 0){
            return appList.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Map<TsApproval,List<TimeSheetProjectVO>> getPendingSubmittedTs(int approverId) {
        //double check with Activiti process engine
        //get all current process with task Review
        //then get data from db using those approvalIds.
        Map<TsApproval,List<TimeSheetProjectVO>> resultMap = new HashMap<>();
        List<Integer> approvalIdList = tsActivitiService.getAllPendingApprovalTasksId(approverId);
        if(approvalIdList != null && approvalIdList.size() > 0) {
            for (Integer aId : approvalIdList) {
                TsApproval approval = timesheetDAO.getTimeSheetApprovalById(aId);
                setApprovalDateList(approval);
                if(approval !=null){
                    resultMap.put(approval,this.getProjTimesheetDate(approval.getSubmitter().getId(),approval.getStartMonday()));
                }
            }
        }
        return resultMap;
    }

    private void setApprovalDateList(final TsApproval approval) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(OaUtils.trimTimeFromDate(approval.getStartMonday()));
        List<Date> dateList = new ArrayList<>();
        for(int i = 0 ; i < 7; i++) {
            dateList.add(cal.getTime());
            cal.add(Calendar.DAY_OF_MONTH,1);
        }
        approval.setDateList(dateList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TimeSheetListItemVO> getTimesheetListForUser(int userId) {
        List<TimeSheetListItemVO> resList = new ArrayList<>();
        List<TsUserEnrollment> entityList = timesheetDAO.getUserEnrollmentByUserId(userId);

        for (TsUserEnrollment entity : entityList) {
            resList.addAll(parseTimesheetList(entity,userId));
        }
        resList.sort(Comparator.comparing(TimeSheetListItemVO::getOffSet));
        return resList;
    }

    @Override
    public List<TsApproval> getTimesheetHistory(int approvalSubId) {
        List<TsApproval> list = timesheetDAO.getTsHistoryByApprovalId(approvalSubId);

        return list;
    }

    private List<TimeSheetListItemVO> parseTimesheetList(TsUserEnrollment entity, int userId) {
        int offset = OaUtils.getOffsetOf(entity.getStartDate());
        List<TimeSheetListItemVO> resList = new ArrayList<>();
        Date startDate = entity.getStartDate();
        for(int i = offset; i <= 0 ; i++) {
            Date endDate = OaUtils.getTimesheetEndDate(startDate);
            TimeSheetListItemVO vo = new TimeSheetListItemVO(i,startDate,endDate);
            TsApproval approvalStatus = getTimesheetStatus(i,userId);
            if(approvalStatus != null){
                vo.setStatus(approvalStatus.getStatus().getValue());
            }
            resList.add(vo);
            startDate = OaUtils.addOneDay(endDate);
        }
        return resList;
    }

    private static final Pattern VALID_TS_INPUT_KEY = Pattern.compile("^[0-9]+_[0-9]+_[A-Za-z]+$");

    private boolean checkInputsForTs(Map<String, String> allInputs) {
        boolean res = true;
        Matcher matcher = null;
        for (String key : allInputs.keySet()) {
            matcher = VALID_TS_INPUT_KEY .matcher(key);
            if(!matcher.matches()){
                res = false;
                break;
            }
        }
        return res;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TimeSheetProjectVO> getProjTimesheetData(Integer offset, int userId) {

        //calculate the start date and end date
        Calendar cal = Calendar.getInstance();
        cal.setTime(OaUtils.getMondayOfThisWeek());
        cal.add(Calendar.WEEK_OF_YEAR, offset);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND, 0);
        Date startDate = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH,6);
        Date endDate = cal.getTime();
        return getProjTimesheetDate(userId,startDate,endDate);
    }
    @Transactional(readOnly = true)
    public List<TimeSheetProjectVO> getProjTimesheetDate(int userId, Date startDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DAY_OF_MONTH,6);
        Date endDate = cal.getTime();
        return getProjTimesheetDate(userId,startDate,endDate);
    }
    @Override
    @Transactional(readOnly = true)
    public List<TimeSheetProjectVO> getProjTimesheetDate(int userId, Date startDate, Date endDate){
        List<TsMain> entityList = timesheetDAO.getTimeSheetDateFor(userId,startDate,endDate);
        entityList.stream().forEach(e -> Hibernate.initialize(e.getUser().getTsSchedule()));
        //this map stores existing project ts data
        Map<Project, List<TsMain>> entityMap = entityList.stream().collect(Collectors.groupingBy(o -> o.getBillCode().getProject()));
        //add those empty project data. as to display them
        User user = userDAO.findUserById(userId);
        List<TsEmpSchedule> scheduleList = user.getTsSchedule();
        Hibernate.initialize(user.getTsSchedule());

        for(TsEmpSchedule schedule : scheduleList){
            if(!entityMap.containsKey(schedule.getBillCode().getProject())){
                //add a empty list
                entityMap.put(schedule.getBillCode().getProject(), new ArrayList<>());
            }
        }

        //final result
        List<TimeSheetProjectVO> resList = new ArrayList<>();
        for(Map.Entry<Project, List<TsMain>> mainEntry : entityMap.entrySet()){
            //for each VO we need to set projectVO
            TimeSheetProjectVO vo = new TimeSheetProjectVO();
            //get current project
            Project proj = mainEntry.getKey();
            ProjectVO pVO = new ProjectVO();
            pVO.setId(proj.getId());
            pVO.setName(proj.getName());
            vo.setProject(pVO);
            //Add all bill code - we have bill code in TsEmpSchedule, and its subset in TsMain.
            List<BillCodeVO> codeList = new ArrayList<>();
            //inside billcode vo , we have the code info and slot data.
            //TsMainSchedule
//                List<TsEmpSchedule> scheduleList = mainEntry.getValue().get(0).getUser().getTsSchedule();
            for(TsEmpSchedule schedule : scheduleList){
                if(schedule.getBillCode().getProject().getId() == proj.getId()) {
                    BillCodeVO billCodeVO = new BillCodeVO();
                    //set bill code info
                    TsBillCodeLookup code = new TsBillCodeLookup();
                    code.setId(schedule.getBillCode().getId());
                    code.setCodeValue(schedule.getBillCode().getCodeValue());
                    //this is schedule, so no slot.
                    billCodeVO.setBillCode(code);
                    codeList.add(billCodeVO);
                }
            }
            vo.setBillCodeList(codeList);
            //--end schedule, all code are in codelist now.
            // start to populate exsisting data.
            List<TsMain> existingTsEntity = mainEntry.getValue();
            for (TsMain main : existingTsEntity) {
                for (BillCodeVO code : vo.getBillCodeList()) {
                    if(code.getBillCode().getId() == main.getBillCode().getId()){
                        Map<String, TimeSheetSlotVO> slots = code.getSlots();
                        if(slots == null){
                            slots = new HashMap<>();
                        }
                        TimeSheetSlotVO slot = new TimeSheetSlotVO();
                        slot.setValue(main.getValue());
                        slot.setComment(main.getComment());
                        slots.put(OaUtils.getDayOfWeek(main.getSlot().getDate()),slot);
                        code.setSlots(slots);
                    }
                }
            }
            resList.add(vo);
        }

        return resList;
    }
}
