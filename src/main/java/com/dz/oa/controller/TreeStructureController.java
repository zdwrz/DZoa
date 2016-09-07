package com.dz.oa.controller;

import com.dz.oa.vo.TreeNodeStateVO;
import com.dz.oa.vo.TreeNodeVO;
import org.apache.log4j.Logger;
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
    @RequestMapping
    public TreeNodeVO getProjectDocNodes(){
        TreeNodeVO root = new TreeNodeVO();
        root.setId("root1");
        root.setValue("All project");
        root.setType("root");
        TreeNodeStateVO state = new TreeNodeStateVO();
        state.setOpened(true);
        root.setState(state);

        List<TreeNodeVO> children = new ArrayList<>();
        TreeNodeVO child1 = new TreeNodeVO();
        child1.setId("c1");
        child1.setValue("Project 1");
        child1.setType("folder");
        child1.setChildren(true);
        children.add(child1);

        root.setChildren(children);
        return root;

    }

    @RequestMapping("/doc")
    public List<TreeNodeVO> getDocs(String id){
        LOGGER.info(id);
        List<TreeNodeVO> children = new ArrayList<>();
        TreeNodeVO child1 = new TreeNodeVO();
        child1.setId("f1");
        child1.setValue("file 1");
        child1.setType("file");

        children.add(child1);
        return children;

    }
}
