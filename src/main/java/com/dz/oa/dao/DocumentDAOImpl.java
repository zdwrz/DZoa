package com.dz.oa.dao;

import com.dz.oa.entity.ProjDocInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by daweizhuang on 9/8/16.
 */
@Repository
public class DocumentDAOImpl implements DocumentDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<ProjDocInfo> getDocInfoByProjId(int projId) {
        return em.createNamedQuery("ProjDocInfo.findByProjId",ProjDocInfo.class).setParameter("projId",projId).getResultList();
    }

    @Override
    public ProjDocInfo saveFileInfo(ProjDocInfo projDocInfo) {
        return em.merge(projDocInfo);
    }

    @Override
    public ProjDocInfo getDocInfoById(int fileId) {
        return em.createNamedQuery("ProjDocInfo.findById",ProjDocInfo.class).setParameter("fileId",fileId).getSingleResult();
    }
}
