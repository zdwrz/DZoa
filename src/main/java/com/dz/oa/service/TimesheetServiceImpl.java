package com.dz.oa.service;

import com.dz.oa.dao.TimesheetDAO;
import com.dz.oa.dao.UserDAO;
import com.dz.oa.entity.*;
import com.dz.oa.exception.TimesheetException;
import com.dz.oa.service.activiti.timesheet.TsActivitiService;
import com.dz.oa.utility.OaUtils;
import com.dz.oa.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                if( StringUtils.isEmpty(allInputs.get(key))){
                    continue;//skip the empty inputs
                }
                String[] splitKey = key.split("_");
                int projId = Integer.parseInt(splitKey[0]);
                int billCodeId = Integer.parseInt(splitKey[1]);
                String weekDay = splitKey[2];
                Date slotDate = OaUtils.getDateOfWeekDay(dateOfMonday, weekDay);
                TsSlotLookup slot = timesheetDAO.createSlot(slotDate);
                if (splitKey.length == 3) {
                    //hour information
                    int hour = Integer.parseInt(allInputs.get(key));
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
    public boolean submitTs(Date dateOfMonday, int userId) {
        LOGGER.debug("Starting submiting Timesheet from :" + dateOfMonday + " userID: " + userId);
        //start activiti process
        tsActivitiService.submit(userId, dateOfMonday);//timesheet approval table id
        return true;
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
