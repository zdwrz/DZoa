package com.dz.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NotificationController {

	@RequestMapping({"/notification"})
	public String manageNotification(ModelMap model) {
		return "Notification/dashboard_notification_management";
	}

}
