/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.assets.assignAsset;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.gpms.web.gpmsBusinessSrv.assets.AssetAssignModel;
import org.gpms.web.gpmsBusinessSrv.assets.IssueAssetsBusinessSrv;
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

	@RequestMapping(value = "/issueBondedAsset", method = { RequestMethod.GET })
	public ModelAndView issueBondedAsset(HttpServletRequest request,
			@ModelAttribute("bondedAssetBean") BondedAssetBean bondedAssetBean,
			Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("bondedAssetBean " + bondedAssetBean);
		}

		model.addAttribute("bondedAssetBean", bondedAssetBean);

		return new ModelAndView("assets/issueBondedAsset");
	}

	@RequestMapping(value = "/issueBondedAsset", method = { RequestMethod.POST }, params = "issueBondedAsset")
	public ModelAndView issueBondedAsset(HttpServletRequest request,
			@ModelAttribute @Valid BondedAssetBean bondedAssetBean,
			BindingResult result, Model model) {

		String flowType = bondedAssetBean.getFlowType();

		if (result.hasErrors()) {
			return new ModelAndView("assets/issueBondedAsset");
		}

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
				issueAssetsBusinessSrv.IssueBondedItems(assetAssignModel);
			} catch (GPMSApplicationException appExp) {
				System.out.println("**********************************    "
						+ appExp.getErrorCode());
				FieldError appError = new FieldError("bondedAssetBean",
						"errorMessage", appExp.getErrorMessage());
				result.addError(appError);

				return new ModelAndView("assets/issueBondedAsset");
			}

		}

		bondedAssetBean.setFlowType(flowType);
		model.addAttribute("bondedAssetBean", bondedAssetBean);

		return new ModelAndView("assets/issueBondedAsset");
	}

	@RequestMapping(value = "/issueBondedAsset", method = { RequestMethod.POST }, params = "returnBondedCorrection")
	public ModelAndView returnBondedCorrection(HttpServletRequest request,
			@ModelAttribute @Valid BondedAssetBean bondedAssetBean,
			BindingResult result, Model model) {

		String flowType = bondedAssetBean.getFlowType();

		System.out
				.println("??????????????????????????????????????????????????");

		bondedAssetBean.setFlowType(flowType);
		model.addAttribute("bondedAssetBean", bondedAssetBean);

		return new ModelAndView("assets/issueBondedAsset");
	}

}
