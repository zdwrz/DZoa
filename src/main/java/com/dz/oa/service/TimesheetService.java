package com.dz.oa.service;

import com.dz.oa.exception.TimesheetException;
import com.dz.oa.vo.TimeSheetDateVO;
import com.dz.oa.vo.TimeSheetProjectVO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by daweizhuang on 9/27/16.
 */
public interface TimesheetService {
    List<TimeSheetProjectVO>  getProjTimesheetData(Integer offset, int userId);

    List<TimeSheetDateVO> getCurrentTimesheetDate(Integer offset);

    void saveTs(Date dateOfMonday, Map<String, String> allInputs, int userId) throws TimesheetException;

    boolean submitTs(Date dateOfMonday, int userId);
}
