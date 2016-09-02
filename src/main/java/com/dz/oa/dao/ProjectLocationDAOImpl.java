package com.dz.oa.dao;

import com.dz.oa.entity.ProjectLocation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by daweizhuang on 9/2/16.
 */
@Repository
public class ProjectLocationDAOImpl implements ProjectLocationDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public ProjectLocation save(ProjectLocation location) {
        return em.merge(location);
    }
}
