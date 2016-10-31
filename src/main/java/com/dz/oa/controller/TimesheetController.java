package com.dz.oa.controller;import com.dz.oa.entity.TsApproval;import com.dz.oa.exception.TimesheetException;import com.dz.oa.service.MessageService;import com.dz.oa.service.ProjectService;import com.dz.oa.service.TimesheetService;import com.dz.oa.utility.Constants;import com.dz.oa.vo.TimeSheetListItemVO;import org.apache.log4j.Logger;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.format.annotation.DateTimeFormat;import org.springframework.stereotype.Controller;import org.springframework.ui.ModelMap;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.servlet.mvc.support.RedirectAttributes;import javax.servlet.http.HttpSession;import java.util.Date;import java.util.List;import java.util.Map;@Controller@RequestMapping({"/timesheet"})public class TimesheetController {    private final static Logger LOGGER = Logger.getLogger(TimesheetController.class);    @Autowired    ProjectService projectService;    @Autowired    TimesheetService timesheetService;    @Autowired    MessageService msg;    @RequestMapping(value = "/ts", method = RequestMethod.GET)    public String allTimesheet(ModelMap model, HttpSession session) {        int userId = (int) session.getAttribute(Constants.USER_ID);        List<TimeSheetListItemVO> tsList = timesheetService.getTimesheetListForUser(userId);        model.addAttribute("tsList", tsList);        return "timesheet/timesheetList";    }    @RequestMapping(value = "/ts/{weekId}", method = RequestMethod.GET)    public String newTimesheet(ModelMap model, @PathVariable Integer weekId, HttpSession session) {        int userId = (int) session.getAttribute(Constants.USER_ID);        model.addAttribute("projList", projectService.getProjListForMap());        model.addAttribute("dateList", timesheetService.getCurrentTimesheetDate(weekId));        model.addAttribute("projTsList", timesheetService.getProjTimesheetData(weekId, userId));        TsApproval approvalStatus = timesheetService.getTimesheetStatus(weekId,userId);        if(approvalStatus != null){            model.addAttribute("editable",false);            model.addAttribute("ts_status",approvalStatus.getStatus().getValue());        }else{            model.addAttribute("editable",true);        }        model.addAttribute(weekId);        return "timesheet/timesheet";    }    /**     * allInputs format:     * key : ${projTs.project.id}_${billCodeTs.billCode.id}_monday     * value : # of hours     *     * @param model     * @param dateOfMonday     * @param allInputs     * @return     */    @RequestMapping(value = "/save/{weekId}", method = RequestMethod.POST)    public String saveTimesheet(ModelMap model, @PathVariable Integer weekId,                               @DateTimeFormat(pattern = "MM/dd/yyyy") Date dateOfMonday,                               @RequestParam Map<String, String> allInputs,                               final RedirectAttributes redirectAttributes, HttpSession session) {        allInputs.remove("dateOfMonday");        allInputs.remove("_csrf");        if (allInputs.size() < 1) {            LOGGER.debug("Empty Input, skip and return");            return "redirect:/timesheet/ts/" + weekId;        }        try {            timesheetService.saveTs(dateOfMonday, allInputs, (int) session.getAttribute(Constants.USER_ID));        } catch (TimesheetException e) {            LOGGER.debug(e);            e.printStackTrace();            redirectAttributes.addFlashAttribute("errorMsg", msg.getMessage("ts_save_fail", null));            return "redirect:/timesheet/ts/" + weekId;        }        redirectAttributes.addFlashAttribute("successMsg", msg.getMessage("ts_save_success", null));        return "redirect:/timesheet/ts/" + weekId;    }    @RequestMapping(value = "/saveUpdate/{weekId}", method = RequestMethod.POST)    public String saveAndSubmitTs(ModelMap model, @PathVariable Integer weekId,                                  @DateTimeFormat(pattern = "MM/dd/yyyy") Date dateOfMonday,                                  @RequestParam Map<String, String> allInputs,                                  final RedirectAttributes redirectAttributes, HttpSession session){        String res = saveTimesheet(model, weekId, dateOfMonday, allInputs, redirectAttributes, session);        timesheetService.submitTs(dateOfMonday, (int) session.getAttribute(Constants.USER_ID));        return res;    }    @RequestMapping("/manage")    public String manageTimeSheet(ModelMap model) {        model.addAttribute("projList", projectService.getProjListForMap());        return "/timesheet/manage_time_sheet";    }    @RequestMapping("/approval")    public String showApproveTimeSheet(ModelMap model, HttpSession session) {        model.addAttribute("tsToApproveList", timesheetService.getPendingSubmittedTs((int) session.getAttribute(Constants.USER_ID)));        return "/timesheet/approve_time_sheet";    }    @RequestMapping("/approval/approve")    public String approveTimeSheet(ModelMap model, HttpSession session, final RedirectAttributes redirectAttributes, Integer approvalId) {        int userId = (int) session.getAttribute(Constants.USER_ID);        timesheetService.approveOrDenyTs(userId, approvalId, true);        model.addAttribute("tsToApproveList", timesheetService.getPendingSubmittedTs(userId));        redirectAttributes.addFlashAttribute("successMsg", msg.getMessage("ts_approved_success", null));        return "redirect:/timesheet/approval";    }    @RequestMapping("/approval/deny")    public String denyTimeSheet(ModelMap model, HttpSession session, final RedirectAttributes redirectAttributes, Integer approvalId) {        int userId = (int) session.getAttribute(Constants.USER_ID);        timesheetService.approveOrDenyTs(userId, approvalId, false);        model.addAttribute("tsToApproveList", timesheetService.getPendingSubmittedTs(userId));        redirectAttributes.addFlashAttribute("successMsg", msg.getMessage("ts_denied_success", null));        return "redirect:/timesheet/approval";    }}