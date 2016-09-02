package com.dz.oa.dao;

import com.dz.oa.entity.Project;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Dawei on 9/1/16.
 */
@Repository
public class ProjectDAOImpl implements ProjectDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public Project saveProject(Project project) {
        return em.merge(project);
    }
}
