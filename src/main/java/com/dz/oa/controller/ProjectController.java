package com.dz.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/project"})
public class ProjectController {

	@RequestMapping("/create")
	public String createProject(ModelMap model) {
		return "/project/create_new_project";
	}

	@RequestMapping("/manage")
	public String showProject(ModelMap model) {
		return "/project/update_project";
	}

}
