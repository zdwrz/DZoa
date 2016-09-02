package com.dz.oa.service;

import com.dz.oa.dao.ProjectDAO;
import com.dz.oa.entity.Enterprise;
import com.dz.oa.entity.Project;
import com.dz.oa.entity.ProjectLookup;
import com.dz.oa.utility.Constants;
import com.dz.oa.vo.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dawei on 9/1/16.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectDAO projectDAO;

    @Override
    @Transactional
    public boolean saveProject(ProjectVO projectVO) {
        Project proj = new Project();
        proj.setName(projectVO.getName());
        proj.setStartDate(projectVO.getStartDate());
        proj.setCompleteDate(projectVO.getEndDate());
        proj.setEnterprise(new Enterprise(projectVO.getEnterpriseId()));
        proj.setStatus(new ProjectLookup(Constants.DEFAUL_PROJ_STATUS_ID));
        return projectDAO.saveProject(proj) != null;
    }
}
