package com.dz.oa.service;

import com.dz.oa.entity.TsBillCodeLookup;
import com.dz.oa.vo.BillCodeVO;
import com.dz.oa.vo.ProjectVO;
import com.dz.oa.vo.TimeSheetDateVO;
import com.dz.oa.vo.TimeSheetProjectVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by daweizhuang on 9/27/16.
 */
@Service
public class TimesheetServiceImpl implements TimesheetService {
    @Override
    public List<TimeSheetDateVO> getCurrentTimesheetDate() {

        Calendar cal = Calendar.getInstance();
        List<TimeSheetDateVO> resList = new ArrayList<>();
        for(int i = 0 ; i < 14; i++) {
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
                pVO.setId(123);
                pVO.setName("Test Project for temp usage");
            vo.setProject(pVO);
        List<BillCodeVO> codeList = new ArrayList<>();
            BillCodeVO billCodeVO1 = new BillCodeVO();
                TsBillCodeLookup lookup = new TsBillCodeLookup();
                lookup.setCodeValue("Here we go - Chair");
                billCodeVO1.setBillCode(lookup);
            codeList.add(billCodeVO1);
        vo.setBillCodeList(codeList);
        resList.add(vo);
        return resList;
    }
}
