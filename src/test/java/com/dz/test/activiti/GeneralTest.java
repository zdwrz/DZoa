package com.dz.test.activiti;

import org.trs.oa.config.ActivitiConfig;
import org.trs.oa.config.MvcConfig;
import org.trs.oa.config.PersistenceJPAConfig;
import org.trs.oa.service.TimesheetService;
import org.trs.oa.service.activiti.timesheet.ApprovalTsService;
import org.trs.oa.service.activiti.timesheet.TsActivitiService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by daweizhuang on 10/21/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ActivitiConfig.class, PersistenceJPAConfig.class, MvcConfig.class})
@EnableTransactionManagement
//@ContextConfiguration(classes = {ActivitiTestConfig.class})
public class GeneralTest {
    @Autowired
    ApplicationContext ac;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TsActivitiService tsService;
    @Autowired
    private TimesheetService timesheetServiceTop;
    @Test
    public void datasourceTest() {
        assertNotNull(ac.getBean("dataSourceActiviti"));
        ApprovalTsService service = (ApprovalTsService) ac.getBean("approvalTsService");
        assertNotNull(service);
    }

    @Test
    public void testTsSubmit(){
        boolean isResubmit = false;
        int subId1 = tsService.submit(1,new Date(), isResubmit);
        int subId12 = tsService.submit(1,new Date(), isResubmit);
        int subId13 = tsService.submit(1,new Date(), isResubmit);
        List<Task> taskList = tsService.getAllTasks(1,null);
        assertTrue(taskList.size() == 3);
        tsService.approve(1,subId13,"Yes", true);
        tsService.approve(1,subId1, "Yes",true);
        tsService.approve(1,subId12, "Yes",true);
        taskList = tsService.getAllTasks(1,null);
        assertTrue(taskList.size() == 0);
    }

    @Test
    public void testApproval() {
        boolean isResubmit = false;
        int subId1 = tsService.submit(1,new Date(), isResubmit);
        int subId12 = tsService.submit(1,new Date(), isResubmit);
        int subId13 = tsService.submit(1,new Date(), isResubmit);
        List<Integer> approvalIdList = tsService.getAllPendingApprovalTasksId(1);
        approvalIdList.forEach(System.out::println);
        assertTrue(approvalIdList.size() == 3);


    }
}
