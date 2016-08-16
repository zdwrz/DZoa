package com.dz.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/timesheet"})
public class TimesheetController {

	@RequestMapping("/new")
	public String newTimesheet(ModelMap model) {
		return "timesheet/timesheet";
	}

	@RequestMapping("/manage")
	public String manageTimeSheet(ModelMap model) {
		return "/timesheet/manage_time_sheet";
	}

}
