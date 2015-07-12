/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.assets.assignAsset;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView issueBondedAsset(
			@ModelAttribute("bondedAssetBean") BondedAssetBean bondedAssetBean,
			Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("bondedAssetBean " + bondedAssetBean);
		}

		model.addAttribute("bondedAssetBean", bondedAssetBean);

		return new ModelAndView("assets/issueBondedAsset");
	}

	@RequestMapping(value = "/issueBondedAsset", method = { RequestMethod.POST }, params = "issueBondedAsset")
	public ModelAndView issueBondedAsset(
			@ModelAttribute @Valid BondedAssetBean bondedAssetBean,
			BindingResult result, Model model) {

		String flowType = bondedAssetBean.getFlowType();

		// TODO
		// Validation if the asset is in available state
		// To check if user exists
		// To check if asset exists

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

			issueAssetsBusinessSrv.IssueBondedItems(assetAssignModel);

		}

		bondedAssetBean.setFlowType(flowType);
		model.addAttribute("bondedAssetBean", bondedAssetBean);

		return new ModelAndView("assets/issueBondedAsset");
	}

}
