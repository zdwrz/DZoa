package com.dz.oa.service.activiti.timesheet;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by daweizhuang on 10/14/16.
 */
@Service
public class TsServiceImpl implements TsService{
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    public void validate(DelegateExecution execution) {
        System.out.println("validating order for isbn " + execution.getVariable("isbn"));
    }

    public void submit(int userId, int tsSubId) {
        System.out.println("submit a ts:" + tsSubId + " by user: " + userId);

        Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("tsSubId", tsSubId);
        variableMap.put("submitterId", userId);
        variableMap.put("reviewerName", getReviewerName(tsSubId));
		runtimeService.startProcessInstanceByKey("time_sheet_process", tsSubId +"",variableMap);
    }

    public void approve(int userId, int tsSubId) {
        List<Task> tList = taskService.createTaskQuery().list();
        Task aTask = taskService.createTaskQuery().taskAssignee("a").processInstanceBusinessKey(tsSubId + "").taskName("Approver Review").singleResult();
        Map<String, Object> variableMap = new HashMap<String, Object>();
        variableMap.put("requestApproved", false);
       // taskService.resolveTask(aTask.getId(),variableMap);
        taskService.complete(aTask.getId(),variableMap);
        System.out.println(aTask.getName());
    }

    private String getReviewerName(int tsSubId) {
        return "a";
    }

    private boolean validateTs(int tsSubId) {
        return true;
    }
}
