/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.userMgmt;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author narenda.kumar
 * 
 */
@Controller
@SessionAttributes("userBean")
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	UserMgmtBusinessSrv userMgmtBusinessSrv;

	@RequestMapping(value = "/newUser", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView newUser(@ModelAttribute("userBean") UserBean userBean,
			Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("ENTERING");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("userBean\n" + userBean.toString());
		}

		List<SecurityQuestionsModel> securityQuestionsModel = userMgmtBusinessSrv
				.getAllSecurityQuestions();
		List<UserGroupModel> userGroupModel = userMgmtBusinessSrv
				.getUserGroup();

		model.addAttribute("userGroupModel", userGroupModel);
		model.addAttribute("securityQuestionsModel", securityQuestionsModel);
		model.addAttribute("isDisabled", "false");
		model.addAttribute("userBean", userBean);

		if (logger.isDebugEnabled()) {
			logger.debug("EXITING");
		}

		return new ModelAndView("userMgmt/newUser");
	}

	@RequestMapping(value = "/newUser", method = RequestMethod.POST, params = "createUser")
	public String createUser(
			@ModelAttribute("userBean") @Valid UserBean userBean,
			BindingResult result, Model model) throws IOException {

		if (logger.isDebugEnabled()) {
			logger.debug("ENTERING");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("userBean\n" + userBean.toString());
		}

		List<SecurityQuestionsModel> securityQuestionsModel = userMgmtBusinessSrv
				.getAllSecurityQuestions();
		List<UserGroupModel> userGroupModel = userMgmtBusinessSrv
				.getUserGroup();

		if (userBean != null) {
			UserModel userModel = copyBeanToModel(userBean);

			if (logger.isDebugEnabled()) {
				logger.debug("userModel\n" + userModel.toString());
			}

			UserModel returnModel = userMgmtBusinessSrv.createUser(userModel);
			if (returnModel != null && returnModel.getUserId() != null) {
				userBean = copyModelToBean(returnModel);
				if (logger.isDebugEnabled()) {
					logger.debug("userBean\n" + userBean.toString());
				}

				model.addAttribute("isDisabled", "true");
			}
		}

		model.addAttribute("userGroupModel", userGroupModel);
		model.addAttribute("securityQuestionsModel", securityQuestionsModel);
		model.addAttribute("userBean", userBean);

		if (logger.isDebugEnabled()) {
			logger.debug("EXITING");
		}

		return "userMgmt/newUser";
	}

	@RequestMapping(value = "/modifyDeleteUser", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView modifyDeleteUser(
			@ModelAttribute("userBean") UserBean userBean, Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("ENTERING");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("userBean\n" + userBean.toString());
		}

		model.addAttribute("isDisabled", "false");
		model.addAttribute("isButtonDisabled", "true");
		model.addAttribute("userBean", userBean);

		if (logger.isDebugEnabled()) {
			logger.debug("EXITING");
		}

		return new ModelAndView("userMgmt/modifyDeleteUser");
	}

	@RequestMapping(value = "/modifyDeleteUser", method = RequestMethod.POST, params = "getUserData")
	public ModelAndView getUserData(
			@ModelAttribute("userBean") UserBean userBean, Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("ENTERING");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("userBean\n" + userBean.toString());
		}

		String flowType = userBean.getFlowType();

		if (userBean != null) {

			String userId = userBean.getUserId();
			String corpEmailId = userBean.getCorpEmailId();

			UserModel userModel = null;

			if (corpEmailId != null || userId != null) {
				if (corpEmailId != null) {
					userModel = userMgmtBusinessSrv
							.getUserByCorpEmail(corpEmailId);
				} else {
					userModel = userMgmtBusinessSrv.getUserById(userBean
							.getUserId());
				}
				if (logger.isDebugEnabled()) {
					logger.debug("userModel\n" + userModel.toString());
				}
			}

			if (userModel != null) {
				userBean = copyModelToBean(userModel);
				userBean.setFlowType(flowType);
				if (logger.isDebugEnabled()) {
					logger.debug("userBean\n" + userBean.toString());
				}
			}
		}

		model.addAttribute("userBean", userBean);
		model.addAttribute("flowType", flowType);
		model.addAttribute("isDisabled", "false");

		if (logger.isDebugEnabled()) {
			logger.debug("EXITING");
		}

		return new ModelAndView("userMgmt/modifyDeleteUser");
	}

	@RequestMapping(value = "/modifyDeleteUser", method = RequestMethod.POST, params = "modifyUser")
	public String modifyAsset(
			@ModelAttribute("userBean") @Valid UserBean userBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs)
			throws IOException {

		if (logger.isDebugEnabled()) {
			logger.debug("ENTERING");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("userBean\n" + userBean.toString());
		}

		String flowType = userBean.getFlowType();

		List<SecurityQuestionsModel> securityQuestionsModel = userMgmtBusinessSrv
				.getAllSecurityQuestions();
		List<UserGroupModel> userGroupModel = userMgmtBusinessSrv
				.getUserGroup();

		UserModel returnModel = null;

		if (userBean != null) {
			UserModel userModel = copyBeanToModel(userBean);
			if (logger.isDebugEnabled()) {
				logger.debug("userModel\n" + userModel.toString());
			}
			if (userModel != null) {
				returnModel = userMgmtBusinessSrv.modifyUser(userModel);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("returnModel\n" + returnModel.toString());
			}

			userBean = copyModelToBean(returnModel);
			userBean.setFlowType(flowType);
			if (logger.isDebugEnabled()) {
				logger.debug("userBean\n" + userBean.toString());
			}
		}

		if (returnModel != null && returnModel.getUserId() != null) {
			model.addAttribute("isDisabled", "true");
		}

		model.addAttribute("userGroupModel", userGroupModel);
		model.addAttribute("securityQuestionsModel", securityQuestionsModel);
		model.addAttribute("userBean", userBean);
		model.addAttribute("isDisabled", "false");
		model.addAttribute("flowType", "modifyUser");

		if (logger.isDebugEnabled()) {
			logger.debug("EXITING");
		}

		return "userMgmt/modifyDeleteUser";
	}

	@RequestMapping(value = "/modifyDeleteUser", method = RequestMethod.POST, params = "deleteUser")
	public String deleteAsset(
			@ModelAttribute("userBean") @Valid UserBean userBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs)
			throws IOException {

		if (logger.isDebugEnabled()) {
			logger.debug("ENTERING");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("userBean" + userBean);
		}

		String flowType = userBean.getFlowType();

		if (userBean != null) {

			String userId = userBean.getUserId();
			String corpEmailId = userBean.getCorpEmailId();
			String groupId = userBean.getUserGroupId();

			if (logger.isDebugEnabled()) {
				logger.debug("userId" + userId + "\n" + "corpEmailId"
						+ corpEmailId + "\n" + "groupId" + groupId);
			}

			if (corpEmailId != null || userId != null) {
				if (corpEmailId != null) {
					userMgmtBusinessSrv.deleteUserByCorpEmailId(corpEmailId,
							groupId);
				} else {
					userMgmtBusinessSrv.deleteUserById(userId, groupId);
				}
			}
		}

		model.addAttribute("flowType", "deleteUser");
		model.addAttribute("isDisabled", "false");
		userBean.setFlowType(flowType);

		if (logger.isDebugEnabled()) {
			logger.debug("EXITING");
		}

		return "userMgmt/modifyDeleteUser";
	}

	private UserModel copyBeanToModel(UserBean userBean) {

		UserModel userModel = new UserModel();
		userModel.setUserId(userBean.getUserId());
		userModel.setUserFirstName(userBean.getUserFirstName());
		userModel.setUserLastName(userBean.getUserLastName());
		userModel.setUserCorpEmail(userBean.getCorpEmailId());
		userModel.setUserPersonnalEmail(userBean.getPersonalEmailId());
		userModel.setPassword(userBean.getPassword());
		userModel.setUserGroupId(userBean.getUserGroupId());
		return userModel;
	}

	private UserBean copyModelToBean(UserModel userModel) {

		UserBean userBean = new UserBean();
		userBean.setUserId(userModel.getUserId());
		userBean.setUserFirstName(userModel.getUserFirstName());
		userBean.setUserLastName(userModel.getUserLastName());
		userBean.setCorpEmailId(userModel.getUserCorpEmail());
		userBean.setPersonalEmailId(userModel.getUserPersonnalEmail());
		userBean.setPassword(userModel.getPassword());
		userBean.setUserGroupId(userModel.getUserGroupId());
		return userBean;
	}

}
