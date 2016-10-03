package com.dz.oa.service;

import com.dz.oa.dao.TimesheetDAO;
import com.dz.oa.dao.UserDAO;
import com.dz.oa.entity.*;
import com.dz.oa.utility.OaUtils;
import com.dz.oa.vo.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by daweizhuang on 9/27/16.
 */
@Service
public class TimesheetServiceImpl implements TimesheetService {


    @Autowired
    TimesheetDAO timesheetDAO;

    @Autowired
    UserDAO userDAO;

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
