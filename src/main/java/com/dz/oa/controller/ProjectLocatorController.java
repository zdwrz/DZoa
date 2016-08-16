package com.dz.oa.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/location"})
public class ProjectLocatorController {
	private final static Logger LOGGER = Logger.getLogger(ProjectLocatorController.class);


	@RequestMapping("/view")
	public String showMap(ModelMap model) {
		return "locator/locator";
	}

}
