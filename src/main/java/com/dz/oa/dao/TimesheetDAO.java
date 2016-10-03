package com.dz.oa.dao;

import com.dz.oa.entity.TsMain;

import java.util.Date;
import java.util.List;

/**
 * Created by daweizhuang on 9/30/16.
 */
public interface TimesheetDAO {

    List<TsMain> getTimeSheetDateFor(int userId, Date startDate, Date endDate);
}
