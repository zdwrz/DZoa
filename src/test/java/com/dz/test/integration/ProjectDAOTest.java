package com.dz.test.integration;

import com.dz.oa.config.MvcConfig;
import com.dz.oa.config.PersistenceJPAConfig;
import com.dz.oa.dao.ProjectDAO;
import com.dz.oa.entity.Project;
import com.dz.oa.vo.ProjectVO;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by daweizhuang on 10/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceJPAConfig.class, MvcConfig.class})
@WebAppConfiguration
@Ignore
public class ProjectDAOTest {
    @Autowired
    ProjectDAO projectDAO;

    @Test
    @Transactional
    public void testMapping() {

        List<ProjectVO> resList = projectDAO.getProjectNameList();
        assertTrue(resList.get(0) instanceof ProjectVO);

    }

}
