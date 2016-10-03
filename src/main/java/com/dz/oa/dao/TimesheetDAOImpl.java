package com.dz.oa.dao;

import com.dz.oa.entity.TsMain;
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
}
