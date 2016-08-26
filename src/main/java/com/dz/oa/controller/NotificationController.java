package com.dz.oa.controller;

import com.dz.oa.component.Dropdown;
import com.dz.oa.entity.AdminLookup;
import com.dz.oa.service.NotificationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NotificationController {
	private static final Logger LOGGER = Logger.getLogger(NotificationController.class);
	@Autowired
	NotificationService notiService;
	@Autowired
    Dropdown dropdown;

	@RequestMapping({"/notification"})
	public String manageNotification(ModelMap model) {
		model.addAttribute("fullNotificationList",notiService.getAllNotification());
		return "Notification/dashboard_notification_management";
	}
	@RequestMapping({"/notification/remove"})
	public String removeNotification(@RequestParam("notification_to_remove_id") Integer notificationId, ModelMap model) {
		LOGGER.debug("///Notification with id " + notificationId + " is deleted./");
		notiService.removeNotification(notificationId);
		return "redirect:/notification";
	}

	@RequestMapping({"/notification/createOrUpdate"})
	public String createOrUpdateNotification(@RequestParam("notification_to_remove_id") Integer notificationId, ModelMap model) {
		LOGGER.debug("///Notification with id " + notificationId + " is deleted./");
		notiService.removeNotification(notificationId);
		return "redirect:/notification";
	}

	@ModelAttribute("notificationTypeList")
    public List<AdminLookup> getTypeDropdown(){
	    return dropdown.getNotificationTypeList();
    }

}
