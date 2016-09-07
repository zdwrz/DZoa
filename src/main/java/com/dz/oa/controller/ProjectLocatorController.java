package com.dz.oa.controller;

import com.dz.oa.service.ProjectService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/location"})
public class ProjectLocatorController {
	private final static Logger LOGGER = Logger.getLogger(ProjectLocatorController.class);


	@Autowired
	ProjectService projectService;

	@RequestMapping("/view")
	public String showMap(ModelMap model) {
		model.addAttribute("projList", projectService.getProjListForMap());
		return "locator/locator";
	}

}
