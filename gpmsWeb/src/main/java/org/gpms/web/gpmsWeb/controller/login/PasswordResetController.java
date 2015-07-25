/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.gpms.web.gpmsBusinessSrv.userMgmt.SecurityQuestionsModel;
import org.gpms.web.gpmsBusinessSrv.userMgmt.UserMgmtBusinessSrv;
import org.gpms.web.gpmsBusinessSrv.userMgmt.UserModel;
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

	/**
	 * 
	 * @param request
	 * @param passwordResetBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/passwordReset", method = RequestMethod.GET)
	public ModelAndView passwordReset(HttpServletRequest request,
			@ModelAttribute PasswordResetBean passwordResetBean, Model model) {

		List<SecurityQuestionsModel> securityQuestionsModel = userMgmtBusinessSrv
				.getAllSecurityQuestions();

		model.addAttribute("securityQuestionsModel", securityQuestionsModel);
		model.addAttribute("passwordResetBean", passwordResetBean);
		model.addAttribute("isDisabled", "true");

		return new ModelAndView("login/passwordReset");
	}

	/**
	 * 
	 * @param request
	 * @param passwordResetBean
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/passwordReset", method = RequestMethod.POST)
	public ModelAndView passwordReset(HttpServletRequest request,
			@ModelAttribute @Valid PasswordResetBean passwordResetBean,
			BindingResult result, Model model) {

		Validator validator = Validation.buildDefaultValidatorFactory()
				.getValidator();

		String userId = passwordResetBean.getLoginId();
		model.addAttribute("isDisabled", "true");
		UserModel userModel = null;
		List<SecurityQuestionsModel> securityQuestionsModel = userMgmtBusinessSrv
				.getAllSecurityQuestions();
		model.addAttribute("securityQuestionsModel", securityQuestionsModel);

		if (userId == null || "".equals(userId)) {
			FieldError userIdFldErr = new FieldError("passwordResetBean",
					"loginId", "Login Id cannot be empty");
			result.addError(userIdFldErr);
		} else {
			userModel = userMgmtBusinessSrv.getUserByCorpEmail(userId);

			ArrayList<String> arrayList = new ArrayList<String>();
			arrayList.add(userModel.getQuestionId());
			passwordResetBean.setQuestionId(arrayList);
		}

		if (result.hasErrors()) {
			return new ModelAndView("login/passwordReset");
		}

		if (!passwordResetBean.getSecretQuesAnsId().equals(
				userModel.getSecretQuesAnsId())) {
			FieldError retrySecuFldErr = new FieldError("passwordResetBean",
					"secretQuesAnsId", "Security answer provided doesnot match");
			result.addError(retrySecuFldErr);
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

		if (result.hasErrors()) {
			return new ModelAndView("login/passwordReset");
		}

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		if (passwordResetBean.getNewPasswordId() != null) {
			String encodedPassword = passwordEncoder.encode(passwordResetBean
					.getNewPasswordId());
			userModel.setPassword(encodedPassword);
		}

		UserModel returnModel = userMgmtBusinessSrv.modifyUser(userModel);

		model.addAttribute("securityQuestionsModel", securityQuestionsModel);
		model.addAttribute("passwordResetBean", passwordResetBean);

		return new ModelAndView("login/passwordReset");
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/passwordReset", params = "loginPage")
	public String loginPage(HttpServletRequest request) throws IOException {
		return "redirect:login";
	}

}
