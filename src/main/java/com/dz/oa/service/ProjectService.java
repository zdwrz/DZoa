package com.dz.oa.service;

import com.dz.oa.entity.Project;
import com.dz.oa.vo.ProjectVO;

import java.util.List;

/**
 * Created by Dawei on 9/1/16.
 */
public interface ProjectService {
    Project saveProject(ProjectVO projectVO);

    List<ProjectVO> getProjListForDashboard();
}
