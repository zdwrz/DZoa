package com.dz.oa.service;

import com.dz.oa.dao.LookupDAO;
import com.dz.oa.entity.AdminLookup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dawei on 8/24/16.
 */
@Service
public class LookupServiceImpl implements LookupService {

    @Autowired
    LookupDAO lookupDAO;

    @Override
    public List<AdminLookup> getAllLookup() {
        return lookupDAO.getAllAdminLookup();
    }
}
