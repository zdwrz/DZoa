package org.trs.oa.dao;

import org.trs.oa.entity.*;
import org.trs.oa.utility.Constants;
import org.trs.oa.utility.OaUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by daweizhuang on 9/30/16.
 */
@Repository
public class TimesheetDAOImpl implements TimesheetDAO {

    private static final Logger LOGGER = Logger.getLogger(TimesheetDAOImpl.class);

    @PersistenceContext
    EntityManager em;


    @Override
    public List<TsMain> getTimeSheetDateFor(int userId, Date startDate, Date endDate) {
        Query query = em.createNamedQuery("TsMain.findForUserIdWithStartDate",TsMain.class);
        query.setParameter("userId", userId);
        query.setParameter("startDate",startDate);
        query.setParameter("endDate",endDate);
        return query.getResultList();
    }

    /**
     * if existing slot, return it
     * else create a new one , return it
     * @param slotDate
     * @return slot
     */
    @Override
    public TsSlotLookup createSlot(Date slotDate) {
        TsSlotLookup slot = null;
        Query searchQuery = em.createNamedQuery("TsSlotLookup.findByDate", TsSlotLookup.class);
        searchQuery.setParameter("date", slotDate);
        List<TsSlotLookup> resList = searchQuery.getResultList();
        if(resList.size() < 1){
            TsSlotLookup newSlot = new TsSlotLookup();
            newSlot.setDate(slotDate);
            slot = em.merge(newSlot);
        }else{
            slot = resList.get(0);
        }
        return slot;
    }

    @Override
    public TsMain saveTimeSheetMain(TsMain main) {
        TsMain tsMain = null;
        Query searchQuery = em.createNamedQuery("TsMain.findByUserSlotBillCode", TsMain.class);
        searchQuery.setParameter("userId", main.getUser().getId());
        searchQuery.setParameter("billCode", main.getBillCode().getId());
        searchQuery.setParameter("slotId", main.getSlot().getId());
        List<TsMain> resList = searchQuery.getResultList();

        if(main.getValue() == 0){ // keep old value in DB if they give 0 as new value
            if (resList.size() >= 1) {
                tsMain = resList.get(0);
                //tsMain.setValue(main.getValue());
                tsMain.setComment(main.getComment());
                tsMain.setInactiveInd("Y");
            }
        }else {
            if (resList.size() < 1) {
                tsMain = em.merge(main);
            } else {
                tsMain = resList.get(0);
                tsMain.setValue(main.getValue());
                tsMain.setComment(main.getComment());
            }
        }

        return tsMain;
    }

    @Override
    public TsApproval createSubmit(int userId, Date dateOfMonday, boolean isResubmit) {
        TsApproval approval = null;
        if(isResubmit){
            approval = em.createNamedQuery("TsApproval.findByUserIdAndStartMonday",TsApproval.class).setParameter("submitterId", userId).setParameter("startMonday",dateOfMonday).getSingleResult();
            approval.setStatus(new AdminLookup(Constants.DEFAULT_TS_RE_SUBMIT_STATUS_ID));
        }else {
            approval = new TsApproval();
            User submitter = em.createNamedQuery("User.findUserById", User.class).setParameter("userId", userId).getSingleResult();
            User approver = submitter; // TODO : get approver
            approval.setApprover(approver);
            approval.setSubmitter(submitter);
            approval.setStatusDate(new Date());
            approval.setStartMonday(dateOfMonday);
            approval.setStatus(new AdminLookup(Constants.DEFAULT_TS_SUBMIT_STATUS_ID));
            em.persist(approval);
        }
        return approval;
    }

    /**
     * update the tsMain table to set all ts main to link to this submission
     * @param subId
     */
    @Override
    public void submitTs(Integer subId) {
        TsApproval approval = em.createNamedQuery("TsApproval.findById", TsApproval.class).setParameter("id",subId).getSingleResult();
        Date startDate = OaUtils.trimTimeFromDate(approval.getStartMonday());
        Date endDate = OaUtils.getTimesheetEndDate(startDate);
        int submitterId = approval.getSubmitter().getId();
        em.createNamedQuery("TsMain.updateApprovalStatus").setParameter("startDate", startDate).setParameter("endDate", endDate).setParameter("userId", submitterId).setParameter("subId", subId).executeUpdate();
        em.createNativeQuery("update ts_approval a set a.total_hrs = (select sum(value) from ts_main t " +
                "where t.user_id = ? and t.inactive_ind = 'N' and t.approval_id = ?) where a.id= ?;")
                .setParameter(1, submitterId).setParameter(2, subId).setParameter(3, subId)
                .executeUpdate();
    }

    @Override
    public List<TsApproval> getTimeSheetApprovalStatus(Date startMonday, int userId) {
        return em.createNamedQuery("TsApproval.findByUserIdAndStartMonday",TsApproval.class).setParameter("submitterId", userId).setParameter("startMonday",startMonday).getResultList();
    }

    @Override
    public List<TsMain> getTsToApproveFor(int approverId, List<Integer> approvalIdList) {
        int[] pendingStatusIds =  new int[]{Constants.DEFAULT_TS_SUBMIT_STATUS_ID, Constants.DEFAULT_TS_RE_SUBMIT_STATUS_ID};
        return em.createNamedQuery("TsApproval.findPendingTsToApproveByApproverId",TsMain.class).setParameter("approverId", approverId).setParameter("approvalIdList",approvalIdList).setParameter("statusIds", pendingStatusIds).getResultList();
    }

    @Override
    public void updateApprovalStatus(int tsSubId, String comment, int statusId) {
        em.createNamedQuery("TsApproval.updateStatusById").setParameter("approvalId",tsSubId).setParameter("statusId",statusId).setParameter("comment",comment).setParameter("statusDate",new Date()).executeUpdate();
    }

    @Override
    public List<TsUserEnrollment> getUserEnrollmentByUserId(int userId) {
        return em.createNamedQuery("TsUserEnrollment.findByUserId",TsUserEnrollment.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public TsApproval getTimeSheetApprovalById(Integer id) {
        List<TsApproval> list = em.createNamedQuery("TsApproval.findById",TsApproval.class).setParameter("id", id).getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<TsApproval> getTsHistoryByApprovalId(int approvalSubId) {
        List<TsApproval> resList = em.createNativeQuery("select * from ts_approval_audit a where a.approval_id = :approvalId order by a.status_date",TsApproval.class).setParameter("approvalId", approvalSubId).getResultList();
        resList.add(getTimeSheetApprovalById(approvalSubId));
        return resList;
    }
}
