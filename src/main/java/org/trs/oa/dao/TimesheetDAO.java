package org.trs.oa.dao;

import org.trs.oa.entity.TsApproval;
import org.trs.oa.entity.TsMain;
import org.trs.oa.entity.TsSlotLookup;
import org.trs.oa.entity.TsUserEnrollment;

import java.util.Date;
import java.util.List;

/**
 * Created by daweizhuang on 9/30/16.
 */
public interface TimesheetDAO {

    List<TsMain> getTimeSheetDateFor(int userId, Date startDate, Date endDate);

    TsSlotLookup createSlot(Date slotDate);

    TsMain saveTimeSheetMain(TsMain main);

    TsApproval createSubmit(int userId, Date dateOfMonday, boolean isResubmit);

    void submitTs(Integer subId);

    List<TsApproval> getTimeSheetApprovalStatus(Date startMonday, int userId);

    List<TsMain> getTsToApproveFor(int approverId, List<Integer> approvalIdList);

    void updateApprovalStatus(int tsSubId, String comment, int i);

    List<TsUserEnrollment> getUserEnrollmentByUserId(int userId);

    TsApproval getTimeSheetApprovalById(Integer aId);

    List<TsApproval> getTsHistoryByApprovalId(int approvalSubId);
}
