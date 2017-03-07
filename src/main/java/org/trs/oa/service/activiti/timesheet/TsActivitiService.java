package org.trs.oa.service.activiti.timesheet;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.task.Task;

import java.util.Date;
import java.util.List;

/**
 * Created by daweizhuang on 10/14/16.
 */
public interface TsActivitiService {
     void validate(DelegateExecution execution);
     Integer submit(int userId, Date dateOfMonday, boolean isResubmit);
     void approve(int userId, int tsSubId, String comment, Boolean approved);
     List<Task> getAllTasks(int userId, String taskName);

    List<Integer> getAllPendingApprovalTasksId(int approverId);
}
