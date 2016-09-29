package com.dz.oa.service;

import com.dz.oa.entity.TsBillCodeLookup;
import com.dz.oa.utility.OaUtils;
import com.dz.oa.vo.*;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by daweizhuang on 9/27/16.
 */
@Service
public class TimesheetServiceImpl implements TimesheetService {
    @Override
    public List<TimeSheetDateVO> getCurrentTimesheetDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(OaUtils.getMondayOfThisWeek());
        List<TimeSheetDateVO> resList = new ArrayList<>();
        for(int i = 0 ; i < 7; i++) {
            resList.add(new TimeSheetDateVO(cal.getTime()));
            cal.add(Calendar.DAY_OF_MONTH,1);
        }
        return resList;
    }

    @Override
    public List<TimeSheetProjectVO> getProjTimesheetData() {
        List<TimeSheetProjectVO> resList = new ArrayList<>();
        TimeSheetProjectVO vo = new TimeSheetProjectVO();
            ProjectVO pVO = new ProjectVO();
                pVO.setId(1234);
                pVO.setName("Test Project for temp usage");
            vo.setProject(pVO);
        List<BillCodeVO> codeList = new ArrayList<>();
            BillCodeVO billCodeVO1 = new BillCodeVO();
                TsBillCodeLookup lookup = new TsBillCodeLookup();
                lookup.setCodeValue("Code #2 - Chair");
                lookup.setId(1);
                billCodeVO1.setBillCode(lookup);
            codeList.add(billCodeVO1);
            BillCodeVO billCodeVO2 = new BillCodeVO();
            TsBillCodeLookup lookup2 = new TsBillCodeLookup();
            lookup2.setCodeValue("Code # 1 - Desk");
            lookup2.setId(2);
            billCodeVO2.setBillCode(lookup2);
            Map<String, TimeSheetSlotVO> slots = new HashMap<>();
            TimeSheetSlotVO slot1 = new TimeSheetSlotVO();
            slot1.setValue(100);
            slot1.setComment("fuck you");
            slots.put("monday",slot1);
            billCodeVO2.setSlots(slots);
            codeList.add(billCodeVO2);
        vo.setBillCodeList(codeList);
        resList.add(vo);

        TimeSheetProjectVO vo2 = new TimeSheetProjectVO();
        ProjectVO pVO1 = new ProjectVO();
        pVO1.setId(123);
        pVO1.setName("Test Project for temp usage");
        vo2.setProject(pVO1);
        List<BillCodeVO> codeList1 = new ArrayList<>();
        BillCodeVO billCodeVO11 = new BillCodeVO();
        TsBillCodeLookup lookup22 = new TsBillCodeLookup();
        lookup22.setCodeValue("Code #4 - Floor");
        lookup22.setId(1);
        billCodeVO11.setBillCode(lookup22);
        codeList1.add(billCodeVO11);
        BillCodeVO billCodeVO22 = new BillCodeVO();
        TsBillCodeLookup lookup223 = new TsBillCodeLookup();
        lookup223.setCodeValue("Code #5 - Concrete");
        lookup223.setId(3);
        billCodeVO22.setBillCode(lookup223);
        codeList1.add(billCodeVO22);
        vo2.setBillCodeList(codeList1);
        resList.add(vo2);
        return resList;
    }
}
