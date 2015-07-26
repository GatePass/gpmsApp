/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.assets.assignAsset;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.gpms.web.gpmsBusinessSrv.assets.AssetAssignModel;
import org.gpms.web.gpmsBusinessSrv.assets.IssueAssetsBusinessSrv;
import org.gpms.web.gpmsBusinessSrv.assets.ReturnAssetsBusinessSrv;
import org.gpms.web.gpmsBusinessSrv.userMgmt.UserModel;
import org.gpms.web.gpmsWeb.common.GpmsValidators;
import org.gpms.web.gpmsWeb.controller.assets.BondedAssetBean;
import org.gpms.web.gpmsWeb.controller.assets.BondedAssetDataConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import exceptions.GPMSApplicationException;

/**
 * @author narenda.kumar
 * 
 */
@Controller
@SessionAttributes("bondedAssetBean")
public class AssignAssetController {

	private static final Logger logger = Logger
			.getLogger(AssignAssetController.class);

	@Autowired
	IssueAssetsBusinessSrv issueAssetsBusinessSrv;

	@Autowired
	ReturnAssetsBusinessSrv returnAssetsBusinessSrv;

	/**
	 * Load the initial screen when an asset is to be assigned to the user
	 * 
	 * @param request
	 * @param bondedAssetBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/issueBondedAsset", method = { RequestMethod.GET })
	public ModelAndView issueBondedAsset(HttpServletRequest request,
			@ModelAttribute("bondedAssetBean") BondedAssetBean bondedAssetBean,
			Model model) {

		String flowType = bondedAssetBean.getFlowType();

		if (logger.isDebugEnabled()) {
			logger.debug("bondedAssetBean " + bondedAssetBean);
		}

		model.addAttribute("returnFlow", "false");
		model.addAttribute("errorDisplay", "false");
		model.addAttribute("bondedAssetBean", bondedAssetBean);

		bondedAssetBean.setFlowType(flowType);
		return new ModelAndView("assets/issueBondedAsset");
	}

	/**
	 * Serves the request when the user wants the asset to be assigned. This
	 * will initiate the new asset assigning process
	 * 
	 * @param request
	 * @param bondedAssetBean
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/issueBondedAsset", method = { RequestMethod.POST }, params = "issueBondedAsset")
	public ModelAndView issueBondedAsset(HttpServletRequest request,
			@ModelAttribute @Valid BondedAssetBean bondedAssetBean,
			BindingResult result, Model model) {

		String flowType = bondedAssetBean.getFlowType();
		model.addAttribute("returnFlow", "false");

		result = GpmsValidators.validateDateField(
				bondedAssetBean.getUserAssetIssueDate(), "bondedAssetBean",
				"userAssetIssueDate", result);

		if (result.hasErrors()) {
			return new ModelAndView("assets/issueBondedAsset");
		}

		model.addAttribute("errorDisplay", "true");

		if (logger.isDebugEnabled()) {
			logger.debug("bondedAssetBean " + bondedAssetBean);
		}

		if (bondedAssetBean != null) {

			AssetAssignModel assetAssignModel = BondedAssetDataConverter
					.convertBeanToModel(bondedAssetBean);

			if (logger.isDebugEnabled()) {
				logger.debug("assetAssignModel :" + assetAssignModel);
			}

			try {
				UserModel loggedInUserModel = (UserModel) request.getSession()
						.getAttribute("userLoggedModel");
				String usersPersonalEmail = loggedInUserModel
						.getUserPersonnalEmail();
				issueAssetsBusinessSrv.IssueBondedItems(assetAssignModel,
						usersPersonalEmail);
			} catch (GPMSApplicationException appExp) {
				FieldError appError = new FieldError("bondedAssetBean",
						"errorMessage", appExp.getErrorMessage());
				result.addError(appError);
				return new ModelAndView("assets/issueBondedAsset");
			} catch (Exception appExp) {
				FieldError appError = new FieldError("bondedAssetBean",
						"errorMessage", "System error");
				result.addError(appError);
				return new ModelAndView("assets/issueBondedAsset");
			}

		}

		bondedAssetBean.setFlowType(flowType);
		model.addAttribute("isDisabled", "true");
		model.addAttribute("bondedAssetBean", bondedAssetBean);
		model.addAttribute("errorDisplay", "false");
		return new ModelAndView("assets/issueBondedAsset");
	}

	/**
	 * Serves both the correction required, for assigning and returning of the
	 * asset.
	 * 
	 * @param request
	 * @param bondedAssetBean
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/issueBondedAsset", method = { RequestMethod.POST }, params = "returnBondedCorrection")
	public ModelAndView returnBondedCorrection(HttpServletRequest request,
			@ModelAttribute @Valid BondedAssetBean bondedAssetBean,
			BindingResult result, Model model) {

		String flowType = bondedAssetBean.getFlowType();
		model.addAttribute("returnFlow", "true");

		result = GpmsValidators.validateBlankField(
				bondedAssetBean.getUserAssetId(), "bondedAssetBean",
				"userAssetId", result);
		result = GpmsValidators.validateDateField(
				bondedAssetBean.getUserAssetReturnDate(), "bondedAssetBean",
				"userAssetReturnDate", result);

		if (result.hasErrors()) {
			return new ModelAndView("assets/issueBondedAsset");
		}

		request.getSession().setAttribute("bondedAssetBean", bondedAssetBean);

		if (logger.isDebugEnabled()) {
			logger.debug("bondedAssetBean " + bondedAssetBean);
		}

		if (bondedAssetBean != null) {

			AssetAssignModel assetAssignModel = BondedAssetDataConverter
					.convertBeanToModel(bondedAssetBean);

			if (logger.isDebugEnabled()) {
				logger.debug("assetAssignModel :" + assetAssignModel);
			}

			try {
				UserModel loggedInUserModel = (UserModel) request.getSession()
						.getAttribute("userLoggedModel");
				String usersPersonalEmail = loggedInUserModel
						.getUserPersonnalEmail();
				returnAssetsBusinessSrv.ReturnBondedItems(assetAssignModel,
						usersPersonalEmail);
			} catch (GPMSApplicationException appExp) {
				FieldError appError = new FieldError("bondedAssetBean",
						"errorMessage", appExp.getErrorMessage());
				result.addError(appError);
				model.addAttribute("errorDisplay", "true");
				return new ModelAndView("assets/issueBondedAsset");
			}

		}

		bondedAssetBean.setFlowType(flowType);
		model.addAttribute("isDisabled", "true");
		model.addAttribute("bondedAssetBean", bondedAssetBean);
		model.addAttribute("errorDisplay", "false");
		return new ModelAndView("assets/issueBondedAsset");
	}

}
