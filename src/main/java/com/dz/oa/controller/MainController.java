package com.dz.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/dashboard")
	public String showMain(ModelMap model) {
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
