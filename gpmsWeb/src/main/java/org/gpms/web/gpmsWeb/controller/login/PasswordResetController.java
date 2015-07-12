/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.login;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.gpms.web.gpmsBusinessSrv.userMgmt.SecurityQuestionsModel;
import org.gpms.web.gpmsBusinessSrv.userMgmt.UserMgmtBusinessSrv;
import org.gpms.web.gpmsWeb.controller.userMgmt.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class PasswordResetController {

	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	UserMgmtBusinessSrv userMgmtBusinessSrv;

	@RequestMapping(value = "/passwordReset", method = RequestMethod.GET)
	public ModelAndView passwordReset(
			@ModelAttribute PasswordResetBean passwordResetBean, Model model) {

		List<SecurityQuestionsModel> securityQuestionsModel = userMgmtBusinessSrv
				.getAllSecurityQuestions();

		model.addAttribute("securityQuestionsModel", securityQuestionsModel);
		model.addAttribute("passwordResetBean", passwordResetBean);

		return new ModelAndView("login/passwordReset");
	}

	@RequestMapping(value = "/passwordReset", method = RequestMethod.POST)
	public ModelAndView passwordReset(
			@ModelAttribute @Valid PasswordResetBean passwordResetBean,
			BindingResult result, Model model) {

		List<SecurityQuestionsModel> securityQuestionsModel = userMgmtBusinessSrv
				.getAllSecurityQuestions();
		model.addAttribute("securityQuestionsModel", securityQuestionsModel);

		if (result.hasErrors()) {
			return new ModelAndView("login/passwordReset");
		}

		// TODO messages to be localized
		if (!passwordResetBean.getNewPasswordId().equals(
				passwordResetBean.getReenterNewPasswordId())) {
			FieldError newPasswordIdFldErr = new FieldError(
					"passwordResetBean", "newPasswordId",
					"entered passwords donot match");
			FieldError reenterNewPasswordIdFldErr = new FieldError(
					"passwordResetBean", "reenterNewPasswordId",
					"entered passwords donot match");
			result.addError(newPasswordIdFldErr);
			result.addError(reenterNewPasswordIdFldErr);
		}

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		model.addAttribute("passwordResetBean", passwordResetBean);

		return new ModelAndView("login/passwordReset");
	}

}
