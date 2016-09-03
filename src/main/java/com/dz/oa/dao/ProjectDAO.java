package com.dz.oa.dao;

import com.dz.oa.entity.Project;

import java.util.List;

/**
 * Created by Dawei on 9/1/16.
 */
public interface ProjectDAO {
    Project saveProject(Project project);

    List<Project> getProjectWithLocation();
}