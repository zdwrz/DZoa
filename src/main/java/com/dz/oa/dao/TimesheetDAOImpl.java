package com.dz.oa.dao;

import com.dz.oa.entity.TsMain;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    @Transactional(readOnly = true)
    public List<TsMain> getTimeSheetDateFor(int userId, Date startDate) {
        return null;
    }
}
