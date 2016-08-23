package com.dz.oa.controller;

import com.dz.oa.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NotificationController {

	@Autowired
	NotificationService notiService;

	@RequestMapping({"/notification"})
	public String manageNotification(ModelMap model) {
		model.addAttribute("fullNotificationList",notiService.getAllNotification());
		return "Notification/dashboard_notification_management";
	}
	@RequestMapping({"/notification/remove"})
	public String removeNotification(Integer notificationId,ModelMap model) {
		model.addAttribute("fullNotificationList",notiService.getAllNotification());
		return "Notification/dashboard_notification_management";
	}

}
