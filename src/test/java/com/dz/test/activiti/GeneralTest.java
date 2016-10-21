package com.dz.test.activiti;

import com.dz.oa.config.ActivitiConfig;
import com.dz.oa.config.MvcConfig;
import com.dz.oa.config.PersistenceJPAConfig;
import com.dz.oa.service.activiti.timesheet.ApprovalTsService;
import com.dz.oa.service.activiti.timesheet.TsService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by daweizhuang on 10/21/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = {ActivitiConfig.class, PersistenceJPAConfig.class, MvcConfig.class})
//@ContextConfiguration(classes = {ActivitiTestConfig.class})
@ContextConfiguration("classpath:activiti-context.xml")
public class GeneralTest {
    @Autowired
    ApplicationContext ac;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TsService tsService;

    @Test
    public void datasourceTest() {
        assertNotNull(ac.getBean("dataSourceActiviti"));
        ApprovalTsService service = (ApprovalTsService) ac.getBean("approvalTsService");
        assertNotNull(service);
    }

    @Test
    public void testTsSubmit(){
        tsService.submit(1,100);
        tsService.approve(1,100);

        tsService.submit(2,200);
        //tsService.approve(2,100);

        tsService.submit(3,300);
        tsService.approve(3,300);

        tsService.approve(2,200);
    }
}
