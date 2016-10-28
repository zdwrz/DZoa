package com.dz.oa.dao;

import com.dz.oa.entity.TsApproval;
import com.dz.oa.entity.TsMain;
import com.dz.oa.entity.TsSlotLookup;

import java.util.Date;
import java.util.List;

/**
 * Created by daweizhuang on 9/30/16.
 */
public interface TimesheetDAO {

    List<TsMain> getTimeSheetDateFor(int userId, Date startDate, Date endDate);

    TsSlotLookup createSlot(Date slotDate);

    TsMain saveTimeSheetMain(TsMain main);

    TsApproval createSubmit(int userId, Date dateOfMonday);

    void submitTs(Integer subId);

    List<TsApproval> getTimeSheetApprovalStatus(Date startMonday, int userId);

    List<TsMain> getTsToApproveFor(int approverId, List<Integer> approvalIdList);

    void updateApprovalStatus(int tsSubId, int i);
}
