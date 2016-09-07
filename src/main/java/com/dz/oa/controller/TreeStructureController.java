package com.dz.oa.controller;

import com.dz.oa.entity.Project;
import com.dz.oa.service.ProjectService;
import com.dz.oa.vo.ProjectVO;
import com.dz.oa.vo.TreeNodeStateVO;
import com.dz.oa.vo.TreeNodeVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            child.setId(pV.getId().toString());
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
        List<TreeNodeVO> children = new ArrayList<>();
        TreeNodeVO child1 = new TreeNodeVO();
        child1.setId("f1" + id);
        child1.setValue("file 1");
        child1.setType("file");

        TreeNodeVO child2 = new TreeNodeVO();
        child2.setId("f2" + id);
        child2.setValue("file 2");
        child2.setType("file");

        children.add(child1);
        children.add(child2);
        return children;

    }
}
