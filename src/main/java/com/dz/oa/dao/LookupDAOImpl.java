package com.dz.oa.dao;

import com.dz.oa.entity.AdminLookup;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Dawei on 8/24/16.
 */
@Repository
public class LookupDAOImpl implements LookupDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<AdminLookup> getAllAdminLookup() {
        return em.createNamedQuery("AdminLookup.findAll",AdminLookup.class).getResultList();
    }
}
