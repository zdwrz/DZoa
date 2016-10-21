package com.dz.oa.service.activiti.timesheet;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprovalTsService {

	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;

	public void deny(DelegateExecution execution) {
		System.out.println("deny you ts id:" + execution.getVariable("tsSubId") + "  submitterId: " + execution.getVariable("submitterId") + "  reviewerName: " + execution.getVariable("reviewerName"));
	}
	
	public void validate(DelegateExecution execution) {
		System.out.println("validate you ts id:" + execution.getVariable("tsSubId") + "  submitterId: " + execution.getVariable("submitterId") + "  reviewerName: " + execution.getVariable("reviewerName"));
		execution.setVariable("valid", true);
	}
	
	public void save(DelegateExecution execution) {
		System.out.println("save you ts id:" + execution.getVariable("tsSubId") + "  submitterId: " + execution.getVariable("submitterId") + "  reviewerName: " + execution.getVariable("reviewerName"));
	}
	
	public void invalidNotify(DelegateExecution execution) {
		System.out.println("notify you invalid ts id:" + execution.getVariable("tsSubId") + "  submitterId: " + execution.getVariable("submitterId") + "  reviewerName: " + execution.getVariable("reviewerName"));
	}
	
	public void approve(DelegateExecution execution) {
		System.out.println("approved you ts id:" + execution.getVariable("tsSubId") + "  submitterId: " + execution.getVariable("submitterId") + "  reviewerName: " + execution.getVariable("reviewerName"));
	}

}
