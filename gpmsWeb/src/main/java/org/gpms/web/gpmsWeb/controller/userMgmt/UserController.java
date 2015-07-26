/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.userMgmt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.gpms.web.gpmsBusinessSrv.userMgmt.SecurityQuestionsModel;
import org.gpms.web.gpmsBusinessSrv.userMgmt.UserGroupModel;
import org.gpms.web.gpmsBusinessSrv.userMgmt.UserMgmtBusinessSrv;
import org.gpms.web.gpmsBusinessSrv.userMgmt.UserModel;
import org.gpms.web.gpmsBusinessSrv.util.ApplicationConstants;
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

	/**
	 * 
	 * @param request
	 * @param userBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/newUser", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView newUser(HttpServletRequest request,
			@ModelAttribute("userBean") UserBean userBean, Model model) {

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
		userGroupModel = getRoleBasedUserGroup(userGroupModel, request);

		model.addAttribute("userGroupModel", userGroupModel);
		model.addAttribute("securityQuestionsModel", securityQuestionsModel);
		model.addAttribute("isDisabled", "false");
		model.addAttribute("userBean", userBean);

		if (logger.isDebugEnabled()) {
			logger.debug("EXITING");
		}

		return new ModelAndView("userMgmt/newUser");
	}

	/**
	 * 
	 * @param request
	 * @param userBean
	 * @param result
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/newUser", method = RequestMethod.POST, params = "createUser")
	public String createUser(HttpServletRequest request,
			@ModelAttribute("userBean") @Valid UserBean userBean,
			BindingResult result, Model model) throws IOException {

		if (logger.isDebugEnabled()) {
			logger.debug("ENTERING");
		}
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		List<SecurityQuestionsModel> securityQuestionsModel = userMgmtBusinessSrv
				.getAllSecurityQuestions();
		List<UserGroupModel> userGroupModel = userMgmtBusinessSrv
				.getUserGroup();
		userGroupModel = getRoleBasedUserGroup(userGroupModel, request);

		model.addAttribute("userGroupModel", userGroupModel);
		model.addAttribute("securityQuestionsModel", securityQuestionsModel);

		if (userBean != null
				&& !userBean.getCorpEmailId().equals("")
				&& !userBean.getPersonalEmailId().equals("")
				&& userBean.getCorpEmailId().equals(
						userBean.getPersonalEmailId())) {
			FieldError corpEmailIdFldErr = new FieldError("userBean",
					"corpEmailId", "Same E-Mail address entered");
			FieldError personalEmailFldErr = new FieldError("userBean",
					"personalEmailId", "Same E-Mail address entered");
			result.addError(corpEmailIdFldErr);
			result.addError(personalEmailFldErr);
		}

		if (result.hasErrors()) {
			return "userMgmt/newUser";
		}

		if (logger.isDebugEnabled()) {
			logger.debug("userBean\n" + userBean.toString());
		}

		if (userBean != null) {

			if (userBean.getPassword() != null) {
				String encodedPassword = passwordEncoder.encode(userBean
						.getPassword());
				userBean.setPassword(encodedPassword);
			}

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

		model.addAttribute("userBean", userBean);

		if (logger.isDebugEnabled()) {
			logger.debug("EXITING");
		}

		return "userMgmt/newUser";
	}

	/**
	 * 
	 * @param request
	 * @param userBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/modifyDeleteUser", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView modifyDeleteUser(HttpServletRequest request,
			@ModelAttribute("userBean") UserBean userBean, Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("ENTERING");
		}

		String flowType = userBean.getFlowType();

		List<SecurityQuestionsModel> securityQuestionsModel = userMgmtBusinessSrv
				.getAllSecurityQuestions();
		List<UserGroupModel> userGroupModel = userMgmtBusinessSrv
				.getUserGroup();
		userGroupModel = getRoleBasedUserGroup(userGroupModel, request);

		if (logger.isDebugEnabled()) {
			logger.debug("userBean\n" + userBean.toString());
		}

		userBean.setFlowType(flowType);
		model.addAttribute("userGroupModel", userGroupModel);
		model.addAttribute("securityQuestionsModel", securityQuestionsModel);
		model.addAttribute("isDisabled", "true");
		model.addAttribute("isButtonDisabled", "true");
		model.addAttribute("userBean", userBean);

		if (logger.isDebugEnabled()) {
			logger.debug("EXITING");
		}

		return new ModelAndView("userMgmt/modifyDeleteUser");
	}

	/**
	 * 
	 * @param request
	 * @param userBean
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/modifyDeleteUser", method = RequestMethod.POST, params = "getUserData")
	public ModelAndView getUserData(HttpServletRequest request,
			@ModelAttribute("userBean") UserBean userBean,
			BindingResult result, Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("ENTERING");
		}

		List<SecurityQuestionsModel> securityQuestionsModel = userMgmtBusinessSrv
				.getAllSecurityQuestions();
		List<UserGroupModel> userGroupModel = userMgmtBusinessSrv
				.getUserGroup();
		userGroupModel = getRoleBasedUserGroup(userGroupModel, request);

		model.addAttribute("userGroupModel", userGroupModel);
		model.addAttribute("securityQuestionsModel", securityQuestionsModel);

		FieldError corpEmailIdFldErr = new FieldError("userBean",
				"corpEmailId", "Either the Corp Email or User Id is required");
		FieldError userIdFldErr = new FieldError("userBean", "userId",
				"Either the Corp Email or User Id is required");

		if (userBean != null && userBean.getUserId().equals("")
				&& userBean.getCorpEmailId().equals("")) {
			result.addError(corpEmailIdFldErr);
			result.addError(userIdFldErr);
		}

		if (result.hasErrors()) {
			return new ModelAndView("userMgmt/modifyDeleteUser");
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

		userBean.setFlowType(flowType);
		model.addAttribute("userBean", userBean);
		model.addAttribute("isDisabled", "false");

		if (logger.isDebugEnabled()) {
			logger.debug("EXITING");
		}

		return new ModelAndView("userMgmt/modifyDeleteUser");
	}

	/**
	 * 
	 * @param request
	 * @param userBean
	 * @param result
	 * @param model
	 * @param redirectAttrs
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/modifyDeleteUser", method = RequestMethod.POST, params = "modifyUser")
	public String modifyUser(HttpServletRequest request,
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
		userGroupModel = getRoleBasedUserGroup(userGroupModel, request);

		model.addAttribute("userGroupModel", userGroupModel);
		model.addAttribute("securityQuestionsModel", securityQuestionsModel);

		if (result.hasErrors()) {
			return "userMgmt/modifyDeleteUser";
		}

		FieldError corpEmailIdFldErr = new FieldError("userBean",
				"corpEmailId", "Either the Corp Email or User Id is required");
		FieldError userIdFldErr = new FieldError("userBean", "personalEmailId",
				"Either the Corp Email or User Id is required");

		if (userBean != null && userBean.getUserId().equals("")
				&& userBean.getCorpEmailId().equals("")) {
			result.addError(corpEmailIdFldErr);
			result.addError(userIdFldErr);
		}

		if (result.hasErrors()) {
			return "userMgmt/modifyDeleteUser";
		}

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

		model.addAttribute("userBean", userBean);
		model.addAttribute("isDisabled", "true");

		if (logger.isDebugEnabled()) {
			logger.debug("EXITING");
		}

		return "userMgmt/modifyDeleteUser";
	}

	/**
	 * 
	 * @param userBean
	 * @param result
	 * @param model
	 * @param redirectAttrs
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/modifyDeleteUser", method = RequestMethod.POST, params = "deleteUser")
	public String deleteUser(@ModelAttribute("userBean") UserBean userBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs)
			throws IOException {

		if (logger.isDebugEnabled()) {
			logger.debug("ENTERING");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("userBean" + userBean);
		}

		String flowType = userBean.getFlowType();

		FieldError corpEmailIdFldErr = new FieldError("userBean",
				"corpEmailId", "Either the Corp Email or User Id is required");
		FieldError userIdFldErr = new FieldError("userBean", "personalEmailId",
				"Either the Corp Email or User Id is required");

		if (userBean != null && userBean.getUserId().equals("")
				&& userBean.getCorpEmailId().equals("")) {
			result.addError(corpEmailIdFldErr);
			result.addError(userIdFldErr);
		}

		if (result.hasErrors()) {
			return "userMgmt/modifyDeleteUser";
		}

		if (userBean != null) {

			String userId = userBean.getUserId();
			String corpEmailId = userBean.getCorpEmailId();
			String groupId = userBean.getUserGroupId();

			if (logger.isDebugEnabled()) {
				logger.debug("userId" + userId + "\n" + "corpEmailId"
						+ corpEmailId + "\n" + "groupId" + groupId);
			}

			if (corpEmailId != null || userId != null) {
				if (corpEmailId != null && !corpEmailId.equals("")) {
					userMgmtBusinessSrv.deleteUserByCorpEmailId(corpEmailId,
							groupId);
				} else {
					userMgmtBusinessSrv.deleteUserById(userId, groupId);
				}
				userBean = new UserBean();
			}
		}

		model.addAttribute("isDisabled", "true");
		userBean.setFlowType(flowType);

		if (logger.isDebugEnabled()) {
			logger.debug("EXITING");
		}

		return "userMgmt/modifyDeleteUser";
	}

	/**
	 * 
	 * @param userBean
	 * @return
	 */
	private UserModel copyBeanToModel(UserBean userBean) {

		UserModel userModel = new UserModel();
		userModel.setUserId(userBean.getUserId());
		userModel.setUserFirstName(userBean.getUserFirstName());
		userModel.setUserLastName(userBean.getUserLastName());
		userModel.setUserCorpEmail(userBean.getCorpEmailId());
		userModel.setUserPersonnalEmail(userBean.getPersonalEmailId());
		userModel.setPassword(userBean.getPassword());
		userModel.setUserGroupId(userBean.getUserGroupId());
		userModel.setQuestionId(userBean.getQuestionId().get(0));
		userModel.setSecretQuesAnsId(userBean.getSecretQuesAnsId());

		return userModel;
	}

	/**
	 * 
	 * @param userModel
	 * @return
	 */
	private UserBean copyModelToBean(UserModel userModel) {

		UserBean userBean = new UserBean();
		userBean.setUserId(userModel.getUserId());
		userBean.setUserFirstName(userModel.getUserFirstName());
		userBean.setUserLastName(userModel.getUserLastName());
		userBean.setCorpEmailId(userModel.getUserCorpEmail());
		userBean.setPersonalEmailId(userModel.getUserPersonnalEmail());
		userBean.setPassword(userModel.getPassword());
		userBean.setUserGroupId(userModel.getUserGroupId());
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add(userModel.getQuestionId());
		userBean.setQuestionId(arrayList);
		userBean.setSecretQuesAnsId(userModel.getSecretQuesAnsId());

		return userBean;
	}

	/**
	 * 
	 * @param userGroupModel
	 * @param request
	 * @return
	 */
	private List<UserGroupModel> getRoleBasedUserGroup(
			List<UserGroupModel> userGroupModel, HttpServletRequest request) {

		boolean isitMgrRole = request.isUserInRole("gpmsISITMgrGroup");
		boolean isitAdmRole = request.isUserInRole("gpmsAdminGroup");
		List<UserGroupModel> userGroupList = new ArrayList<UserGroupModel>();
		Iterator<UserGroupModel> userGroupModelIter = userGroupModel.iterator();
		if (isitMgrRole) {
			while (userGroupModelIter.hasNext()) {
				UserGroupModel singleUserGroupModel = userGroupModelIter.next();
				if (singleUserGroupModel.getUserGroupId().equals(
						ApplicationConstants.GROUP_ISIT_USER)) {
					userGroupList.add(singleUserGroupModel);
				} else if (singleUserGroupModel.getUserGroupId().equals(
						ApplicationConstants.GROUP_EMPLOYEE)) {
					userGroupList.add(singleUserGroupModel);
				}
			}
		} else if (isitAdmRole) {
			while (userGroupModelIter.hasNext()) {
				UserGroupModel singleUserGroupModel = userGroupModelIter.next();
				if (singleUserGroupModel.getUserGroupId().equals(
						ApplicationConstants.GROUP_ADMIN)) {
					continue;
				} else {
					userGroupList.add(singleUserGroupModel);
				}

			}
		}

		return userGroupList;
	}

}
