package com.dz.oa.controller;

import com.dz.oa.service.MessageService;
import com.dz.oa.service.ProjectService;
import com.dz.oa.utility.Constants;
import com.dz.oa.vo.ProjectVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping({"/project"})
public class ProjectController {
    private static final Logger LOGGER = Logger.getLogger(ProjectController.class);

	@Autowired
	ProjectService projectService;

	@Autowired
	MessageService msg;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showCreateProject(ModelMap model) {
		return "/project/create_new_project";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createProject(ProjectVO projectVO, final RedirectAttributes redirectAttributes, HttpSession session) {
	    LOGGER.info("////////" + projectVO + " " + session.getAttribute(Constants.USER_ID)+ " " + session.getAttribute(Constants.ENTERPRISE_ID));
		projectVO.setEnterpriseId((Integer)session.getAttribute(Constants.ENTERPRISE_ID));
		if(projectService.saveProject(projectVO)) {
			redirectAttributes.addFlashAttribute("successMsg", msg.getMessage("project_created_success", null));
		}else{

		}
        return "redirect:/dashboard";
	}

	@RequestMapping("/manage")
	public String showProject(ModelMap model) {
		return "/project/update_project";
	}

}
