package com.dz.oa.converter;

import com.dz.oa.entity.Project;
import com.dz.oa.vo.ProjectVO;

import java.util.List;

/**
 * Created by daweizhuang on 9/2/16.
 */
public class ProjectToVoConverter {
    public static ProjectVO convertProjectToVO(Project project) {
        ProjectVO vo = new ProjectVO();
        vo.setId(project.getId());
        vo.setName(project.getName());
        vo.setDesc(project.getDescription());
        vo.setStartDate(project.getStartDate());
        vo.setStatusStr(project.getStatus().getValue());
        if(project.getProjectLocations() != null) {
            vo.setLocationDetail(project.getProjectLocations().get(0));
        }
        return vo;
    }
}
