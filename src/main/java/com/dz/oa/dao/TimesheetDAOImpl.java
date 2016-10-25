package com.dz.oa.dao;

import com.dz.oa.entity.*;
import com.dz.oa.utility.Constants;
import com.dz.oa.utility.OaUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Calendar;
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

        if(resList.size() < 1){
            tsMain = em.merge(main);
        }else{
            tsMain = resList.get(0);
            tsMain.setValue(main.getValue());
            tsMain.setComment(main.getComment());
        }

        return tsMain;
    }

    @Override
    public TsApproval createSubmit(int userId, Date dateOfMonday) {
        TsApproval approval = new TsApproval();
        User submitter = em.createNamedQuery("User.findUserById",User.class).setParameter("userId",userId).getSingleResult();
        User approver = submitter;
        approval.setApprover(approver);
        approval.setSubmitter(submitter);
        approval.setStatusDate(new Date());
        approval.setStartMonday(dateOfMonday);

        approval.setStatus(new AdminLookup(Constants.DEFAULT_TS_SUBMIT_STATUS_ID));

        em.persist(approval);
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
    }
}
