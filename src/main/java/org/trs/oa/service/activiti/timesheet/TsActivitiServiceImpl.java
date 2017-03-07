package org.trs.oa.service.activiti.timesheet;

import org.trs.oa.dao.TimesheetDAO;
import org.trs.oa.dao.UserDAO;
import org.trs.oa.entity.TsApproval;
import org.trs.oa.entity.User;
import org.trs.oa.utility.Constants;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * All user task method is defined in this class.
 * Created by daweizhuang on 10/14/16.
 */
@Service
public class TsActivitiServiceImpl implements TsActivitiService{

    private final static Logger LOGGER = Logger.getLogger(TsActivitiServiceImpl.class);
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TimesheetDAO tsDAO;
    @Autowired
    private UserDAO userDAO;
    @Override
    @Transactional
    public void validate(DelegateExecution execution) {
        System.out.println("validating order for isbn " + execution.getVariable("isbn"));
    }

    @Override
    public Integer submit(int userId, Date dateOfMonday, boolean isResubmit) {
        Integer approvalId = null;
        TsApproval approvalStatus = tsDAO.createSubmit(userId, dateOfMonday, isResubmit);
        approvalId = approvalStatus.getId();
        Map<String, Object> variableMap = new HashMap<>();
        variableMap.put("tsSubId", approvalId);
        variableMap.put("submitterId", userId);
        variableMap.put("reviewerName", approvalStatus.getApprover().getUserName());
        runtimeService.startProcessInstanceByKey("time_sheet_process", approvalId +"",variableMap);
        return approvalId;
    }


//    @Transactional
//    public void submit(int userId, int tsSubId) {
//        System.out.println("submit a ts:" + tsSubId + " by user: " + userId);
//
//        Map<String, Object> variableMap = new HashMap<String, Object>();
//		variableMap.put("tsSubId", tsSubId);
//        variableMap.put("submitterId", userId);
//        variableMap.put("reviewerName", getReviewerName(tsSubId));
//		runtimeService.startProcessInstanceByKey("time_sheet_process", tsSubId +"",variableMap);
//    }
    @Override
    @Transactional
    public void approve(int userId, int tsSubId, String comment, Boolean approved) {
//        List<Task> tList = taskService.createTaskQuery().list();
        User user = userDAO.findUserById(userId);
        Task aTask = taskService.createTaskQuery().taskAssignee(user.getUserName()).processInstanceBusinessKey(tsSubId + "").taskName("Approver Review").singleResult();
        Map<String, Object> variableMap = new HashMap<String, Object>();
        variableMap.put("requestApproved", approved);
        tsDAO.updateApprovalStatus(tsSubId,comment,approved? Constants.TS_APPROVED_ID: Constants.TS_DENIED_ID);
       // taskService.resolveTask(aTask.getId(),variableMap);
        LOGGER.info("/////////////////////////////////////////" + aTask.getName() + " is done. process id is " + tsSubId);
        taskService.complete(aTask.getId(),variableMap);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllTasks(int userId, String taskName){
        if (StringUtils.isEmpty(taskName)) {
            taskName = "Approver Review";
        }
        User user = userDAO.findUserById(userId);
        return taskService.createTaskQuery().taskAssignee(user.getUserName()).taskName(taskName).list();
    }

    @Override
    public List<Integer> getAllPendingApprovalTasksId(int approverId) {
        User user = userDAO.findUserById(approverId);
        List<Task> reviewTaskList = taskService.createTaskQuery().taskAssignee(user.getUserName()).taskName("Approver Review").list();
        List<Integer> resList = new ArrayList<>();
        for(Task task : reviewTaskList) {
            String pIdStr = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult().getBusinessKey();
            if (pIdStr != null) {
                resList.add(Integer.parseInt(pIdStr));
            }
        }
        return resList;
    }

    private String getReviewerName(int tsSubId) {
        return "a";
    }

    private boolean validateTs(int tsSubId) {
        return true;
    }
}
