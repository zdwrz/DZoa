package com.dz.oa.service;

import com.dz.oa.vo.TimeSheetDateVO;
import com.dz.oa.vo.TimeSheetProjectVO;

import java.util.List;

/**
 * Created by daweizhuang on 9/27/16.
 */
public interface TimesheetService {
    public List<TimeSheetDateVO> getCurrentTimesheetDate();

    public List<TimeSheetProjectVO>  getProjTimesheetData();
}
