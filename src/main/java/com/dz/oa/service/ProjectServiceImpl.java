package com.dz.oa.service;

import com.dz.oa.converter.ProjectToVoConverter;
import com.dz.oa.dao.ProjectDAO;
import com.dz.oa.dao.ProjectLocationDAO;
import com.dz.oa.entity.Enterprise;
import com.dz.oa.entity.Project;
import com.dz.oa.entity.ProjectLocation;
import com.dz.oa.entity.ProjectLookup;
import com.dz.oa.utility.Constants;
import com.dz.oa.utility.OaUtils;
import com.dz.oa.vo.ProjectVO;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawei on 9/1/16.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectDAO projectDAO;

    @Autowired
    ProjectLocationDAO locationDAO;

    @Override
    @Transactional
    public Project saveProject(ProjectVO projectVO) {
        Project proj = new Project();
        proj.setName(projectVO.getName());
        proj.setDescription(projectVO.getDesc());
        proj.setStartDate(projectVO.getStartDate());
        proj.setCompleteDate(projectVO.getEndDate());
        proj.setEnterprise(new Enterprise(projectVO.getEnterpriseId()));
        proj.setStatus(new ProjectLookup(Constants.DEFAUL_PROJ_STATUS_ID));
        Project persistedProj = projectDAO.saveProject(proj);
        //location
        if(projectVO.getLocationDetail() != null) {
            List<ProjectLocation> locations = new ArrayList<>();
            projectVO.getLocationDetail().setProject(persistedProj);
            ProjectLocation persistedLocation = locationDAO.save(projectVO.getLocationDetail());
            locations.add(persistedLocation);
            persistedProj.setProjectLocations(locations);
        }
        return persistedProj;
    }

    @Override
    @Transactional
    public List<ProjectVO> getProjListForDashboard() {
        List<Project> projectList = projectDAO.getProjectWithLocation();
        List<ProjectVO> voList = new ArrayList<>();
        for (Project proj : projectList) {
            Hibernate.initialize(proj.getProjectLocations());
            voList.add(ProjectToVoConverter.convertProjectToVO(proj));
        }
        return voList;
    }
}
