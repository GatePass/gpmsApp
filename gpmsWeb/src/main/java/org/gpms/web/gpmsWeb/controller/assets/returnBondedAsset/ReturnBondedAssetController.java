/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.assets.returnBondedAsset;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.gpms.web.gpmsBusinessSrv.assets.AssetAssignModel;
import org.gpms.web.gpmsBusinessSrv.assets.ReturnAssetsBusinessSrv;
import org.gpms.web.gpmsBusinessSrv.userMgmt.UserModel;
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
public class ReturnBondedAssetController {

	private static final Logger logger = Logger
			.getLogger(ReturnBondedAssetController.class);

	@Autowired
	ReturnAssetsBusinessSrv returnAssetsBusinessSrv;

	/**
	 * Loads the page for return asset flow.
	 * 
	 * @param request
	 * @param bondedAssetBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/returnBondedAsset", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView returnBondedAsset(HttpServletRequest request,
			@ModelAttribute("bondedAssetBean") BondedAssetBean bondedAssetBean,
			Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("bondedAssetBean :" + bondedAssetBean);
		}

		model.addAttribute("bondedAssetBean", bondedAssetBean);

		return new ModelAndView("assets/issueBondedAsset");
	}

	/**
	 * Serves the request when the Get Asset button is clicked on the return
	 * asset flow of the application
	 * 
	 * @param request
	 * @param bondedAssetBean
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/issueBondedAsset", method = RequestMethod.POST, params = "getAssetAssignedData")
	public ModelAndView getAssetAssignedData(
			HttpServletRequest request,
			@ModelAttribute("bondedAssetBean") @Valid BondedAssetBean bondedAssetBean,
			BindingResult result, Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("bondedAssetBean :" + bondedAssetBean);
		}

		String flowType = bondedAssetBean.getFlowType();

		AssetAssignModel assetAssignModel = null;

		if (bondedAssetBean != null) {

			String userAssetId = bondedAssetBean.getUserAssetId();

			if (userAssetId != null) {

				try {
					assetAssignModel = returnAssetsBusinessSrv
							.getUserAssetById(userAssetId);
				} catch (GPMSApplicationException appExp) {
					FieldError userAssetIdError = new FieldError(
							"bondedAssetBean", "userAssetId",
							appExp.getErrorMessage());
					result.addError(userAssetIdError);
					return new ModelAndView("assets/issueBondedAsset");
				}

				if (logger.isDebugEnabled()) {
					logger.debug("assetAssignModel :" + assetAssignModel);
				}

				bondedAssetBean = BondedAssetDataConverter
						.convertModelToBean(assetAssignModel);
			}
		}

		bondedAssetBean.setFlowType(flowType);
		model.addAttribute("bondedAssetBean", bondedAssetBean);

		return new ModelAndView("assets/issueBondedAsset");
	}

	/**
	 * Serves the request when the user wants to return the asset that is
	 * assigned. This will initiate the return process
	 * 
	 * @param request
	 * @param bondedAssetBean
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/issueBondedAsset", method = RequestMethod.POST, params = "returnBondedAsset")
	public ModelAndView returnBondedAsset(
			HttpServletRequest request,
			@ModelAttribute("bondedAssetBean") @Valid BondedAssetBean bondedAssetBean,
			BindingResult result, Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("bondedAssetBean :" + bondedAssetBean);
		}

		String flowType = bondedAssetBean.getFlowType();

		if (bondedAssetBean != null) {

			String userAssetId = bondedAssetBean.getUserAssetId();
			String userReturnDate = bondedAssetBean.getUserAssetReturnDate();

			AssetAssignModel assetAssignModel = BondedAssetDataConverter
					.convertBeanToModel(bondedAssetBean);

			if (logger.isDebugEnabled()) {
				logger.debug("assetAssignModel :" + assetAssignModel);
			}

			if (userAssetId != null && userReturnDate != null) {

				try {
					UserModel loggedInUserModel = (UserModel) request
							.getSession().getAttribute("userLoggedModel");
					String usersPersonalEmail = loggedInUserModel
							.getUserPersonnalEmail();
					returnAssetsBusinessSrv.ReturnBondedItems(assetAssignModel,
							usersPersonalEmail);
				} catch (GPMSApplicationException appExp) {

					FieldError appError = new FieldError("bondedAssetBean",
							"errorMessage", appExp.getErrorMessage());
					result.addError(appError);
				}

			}

		}

		bondedAssetBean.setFlowType(flowType);
		model.addAttribute("bondedAssetBean", bondedAssetBean);

		return new ModelAndView("assets/issueBondedAsset");
	}

}
