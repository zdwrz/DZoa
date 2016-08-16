package com.dz.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/report"})
public class ReportController {

	@RequestMapping("/new")
	public String newRep(ModelMap model) {
		return "report/create_new_report";
	}

	@RequestMapping("/manage")
	public String manageRep(ModelMap model) {
		return "report/manage_report";
	}

	@RequestMapping("/submit")
	public String submitRep(ModelMap model) {
		return "report/submit_report";
	}

}
