/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.userMgmt;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.gpms.web.gpmsBusinessSrv.userMgmt.SecurityQuestionsModel;
import org.gpms.web.gpmsBusinessSrv.userMgmt.UserGroupModel;
import org.gpms.web.gpmsBusinessSrv.userMgmt.UserMgmtBusinessSrv;
import org.gpms.web.gpmsBusinessSrv.userMgmt.UserModel;
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

		System.out.println("Narendra 1");

		List<SecurityQuestionsModel> securityQuestionsModel = userMgmtBusinessSrv
				.getAllSecurityQuestions();
		List<UserGroupModel> userGroupModel = userMgmtBusinessSrv
				.getUserGroup();

		model.addAttribute("userGroupModel", userGroupModel);
		model.addAttribute("securityQuestionsModel", securityQuestionsModel);
		model.addAttribute("isDisabled", "false");
		model.addAttribute("userBean", userBean);

		return new ModelAndView("userMgmt/newUser");
	}

	@RequestMapping(value = "/newUser", method = RequestMethod.POST, params = "createUser")
	public String createUser(@ModelAttribute @Valid UserBean userBean,
			BindingResult result, Model model) throws IOException {

		System.out.println("Narendra 2");

		List<SecurityQuestionsModel> securityQuestionsModel = userMgmtBusinessSrv
				.getAllSecurityQuestions();
		List<UserGroupModel> userGroupModel = userMgmtBusinessSrv
				.getUserGroup();

		UserModel userModel = new UserModel();
		userModel.setUserFirstName(userBean.getUserFirstName());
		userModel.setUserLastName(userBean.getUserLastName());
		userModel.setUserCorpEmail(userBean.getCorpEmailId());
		userModel.setUserPersonnalEmail(userBean.getPersonalEmailId());
		userModel.setUserGroupId(userBean.getUserGroupId());
		UserModel returnModel = userMgmtBusinessSrv.createUser(userModel);
		userBean.setUserId(returnModel.getUserId());

		if (returnModel.getUserId() != null) {
			model.addAttribute("isDisabled", "true");
		}

		model.addAttribute("userGroupModel", userGroupModel);
		model.addAttribute("securityQuestionsModel", securityQuestionsModel);
		model.addAttribute("userBean", userBean);

		return "userMgmt/newUser";
	}

}
