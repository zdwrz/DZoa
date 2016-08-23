package com.dz.oa.controller;

import com.dz.oa.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@Autowired
	NotificationService notiService;

	@RequestMapping("/dashboard")
	public String showMain(ModelMap model) {
		model.addAttribute("notiList",notiService.getNotiForToday());
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
