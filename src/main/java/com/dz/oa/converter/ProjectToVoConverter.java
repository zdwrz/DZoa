package com.dz.oa.converter;

import com.dz.oa.entity.ProjDocInfo;
import com.dz.oa.entity.Project;
import com.dz.oa.entity.UserDetail;
import com.dz.oa.vo.ProjDocVO;
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

    public static ProjDocVO convertDocInfoToVO(ProjDocInfo doc) {
        ProjDocVO vo = new ProjDocVO();
        vo.setFileName(doc.getDocName());
        vo.setFileId(doc.getId());
        if(doc.getProject() != null) {
            vo.setProjectName(doc.getProject().getName());
        }
        if(doc.getUser()!= null && doc.getUser().getUserDetails()!=null) {
            vo.setUploadedBy(convertUserToDisplayName(doc.getUser().getUserDetails()));
        }
        vo.setUploadedTime(doc.getUploadTime());
        return vo;
    }

    public static String convertUserToDisplayName(UserDetail userDetails) {
        String res = userDetails.getLastName() + ", " + userDetails.getFirstName();
        return res;
    }
}
