/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.userMgmt;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.gpms.web.gpmsBusinessSrv.userMgmt.SecurityQuestionsModel;
import org.gpms.web.gpmsBusinessSrv.userMgmt.UserMgmtBusinessSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author narenda.kumar
 * 
 */
@Controller
public class UserController {

	@Autowired
	UserMgmtBusinessSrv userMgmtBusinessSrv;

	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public ModelAndView newUser(@ModelAttribute UserBean userBean, Model model) {

		List<SecurityQuestionsModel> securityQuestionsModel = userMgmtBusinessSrv
				.getAllSecurityQuestions();

		model.addAttribute("securityQuestionsModel", securityQuestionsModel);
		model.addAttribute("userBean", userBean);

		return new ModelAndView("userMgmt/newUser");
	}

	@RequestMapping(value = "/newUser", method = RequestMethod.POST, params = "submit")
	public String submit(@ModelAttribute @Valid UserBean userBean,
			BindingResult result, Model model) throws IOException {

		return "userMgmt/newUser";
	}

}
