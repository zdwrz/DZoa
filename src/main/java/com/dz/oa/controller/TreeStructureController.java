package com.dz.oa.controller;

import com.dz.oa.entity.ProjDocInfo;
import com.dz.oa.entity.Project;
import com.dz.oa.exception.FileContentException;
import com.dz.oa.service.DocumentService;
import com.dz.oa.service.ProjectService;
import com.dz.oa.utility.Constants;
import com.dz.oa.vo.FileUploadResponse;
import com.dz.oa.vo.ProjectVO;
import com.dz.oa.vo.TreeNodeStateVO;
import com.dz.oa.vo.TreeNodeVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daweizhuang on 9/7/16.
 */
@RestController
@RequestMapping("/projDoc")
public class TreeStructureController {
    private final static Logger LOGGER = Logger.getLogger(TreeStructureController.class);

    @Autowired
    ProjectService projectService;

    @Autowired
    DocumentService documentService;

    @RequestMapping
    public TreeNodeVO getProjectNodes(){
        List<ProjectVO> projList = projectService.getProjListForDocumentTree();

        TreeNodeVO root = new TreeNodeVO();
        root.setId("root1");
        root.setValue("All project");
        root.setType("root");
        TreeNodeStateVO state = new TreeNodeStateVO();
        state.setOpened(true);
        root.setState(state);
        List<TreeNodeVO> children = new ArrayList<>();
        for (ProjectVO pV : projList) {
            TreeNodeVO child = new TreeNodeVO();
            child.setId(Constants.PROJECT_ID_PREFIX+pV.getId());
            child.setValue(pV.getName() + " -- " + pV.getLocationDetail().getCustomAddress());
            child.setType("folder");
            child.setChildren(true);
            children.add(child);
        }
        root.setChildren(children);
        return root;

    }

    @RequestMapping("/doc")
    public List<TreeNodeVO> getDocs(String id){
        LOGGER.info(id);
        String realId = id.replace(Constants.PROJECT_ID_PREFIX, "");
        List<ProjDocInfo> files = documentService.getDocInfoByProjectId(Integer.parseInt(realId));

        List<TreeNodeVO> docs = new ArrayList<>();
        for (ProjDocInfo doc : files) {
            TreeNodeVO docVO = new TreeNodeVO();
            docVO.setId(String.valueOf(doc.getId()));
            docVO.setType("file");
            docVO.setValue(doc.getDocName());
            docs.add(docVO);
        }

        return docs;

    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public FileUploadResponse handleException(Exception e){
        LOGGER.error(e);
        FileUploadResponse response = new FileUploadResponse();
        response.setError(e.getMessage()); // has to be error field, front end js needs this
        return response;
    }
}
