package com.dz.oa.service.activiti.timesheet;

import org.activiti.engine.delegate.DelegateExecution;

/**
 * Created by daweizhuang on 10/14/16.
 */
public interface TsService {
     void validate(DelegateExecution execution);
     void submit(int userId, int tsSubId);
     void approve(int userId, int tsSubId);
}
