/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.reports;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.gpms.web.gpmsBusinessSrv.reports.ReportModel;
import org.gpms.web.gpmsBusinessSrv.reports.ReportsBusinessSrv;
import org.gpms.web.gpmsWeb.controller.userMgmt.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author narenda.kumar
 * 
 */
@Controller
public class ReportsController {

	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	ReportsBusinessSrv reportsBusinessSrv;

	@RequestMapping(value = "/employeeGatePassReport", method = {
			RequestMethod.GET, RequestMethod.POST }, params = "employeeReportView")
	public ModelAndView employeeReport(HttpServletRequest request,
			@ModelAttribute("reportBean") ReportBean reportBean, Model model) {

		String userId = null;

		if (request.getUserPrincipal() != null) {
			userId = request.getUserPrincipal().getName();
		}

		List<ReportModel> reportModelLst = reportsBusinessSrv
				.getEmployeeGatePass(userId);

		model.addAttribute("reportModelLst", reportModelLst);
		model.addAttribute("reportViewType", "employeeReportView");

		return new ModelAndView("reports/employeeGatePassReport");

	}

	@RequestMapping(value = "/employeeGatePassReport", method = {
			RequestMethod.GET, RequestMethod.POST }, params = "managementReportView")
	public ModelAndView managementReportView(HttpServletRequest request,
			@ModelAttribute("reportBean") ReportBean reportBean, Model model) {

		List<ReportModel> reportModelLst = null;

		model.addAttribute("reportViewType", "managementReportView");
		model.addAttribute("reportModelLst", reportModelLst);

		return new ModelAndView("reports/employeeGatePassReport");

	}

	@RequestMapping(value = "/employeeGatePassReport", method = { RequestMethod.POST }, params = "getEmployeeData")
	public ModelAndView getEmployeeData(HttpServletRequest request,
			@ModelAttribute("reportBean") ReportBean reportBean,
			BindingResult result, Model model) {

		List<ReportModel> reportModelLst = null;

		model.addAttribute("reportViewType", "managementReportView");
		model.addAttribute("reportModelLst", reportModelLst);

		FieldError corpEmailIdFldErr = new FieldError("reportBean",
				"userCorpEmail", "Enter the Corp Email to filter by employee");

		if (reportBean != null && reportBean.getUserCorpEmail().equals("")) {
			result.addError(corpEmailIdFldErr);
		}

		if (result.hasErrors()) {
			return new ModelAndView("reports/employeeGatePassReport");
		}

		reportModelLst = reportsBusinessSrv.getEmployeeGatePass(reportBean
				.getUserCorpEmail());

		model.addAttribute("reportViewType", "managementReportView");
		model.addAttribute("reportModelLst", reportModelLst);

		return new ModelAndView("reports/employeeGatePassReport");

	}

}
