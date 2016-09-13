package com.dz.oa.controller;

import com.dz.oa.service.DocumentService;
import com.dz.oa.service.NotificationService;
import com.dz.oa.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@Autowired
	NotificationService notiService;

	@Autowired
	ProjectService projectService;

	@Autowired
	DocumentService documentService;

	@RequestMapping("/dashboard")
	public String showMain(ModelMap model) {
		model.addAttribute("notiList",notiService.getNotiForToday());
		model.addAttribute("projList",projectService.getProjListForDashboard());
		model.addAttribute("docList",documentService.getLatestDoc());
		return "dashboard";
	}
	@RequestMapping("/changepwd")
	public String changePwd(ModelMap model) {
		return "changepwd";
	}
	@RequestMapping("/profile")
	public String showProfile(ModelMap model) {
		return "profile";
	}
}
