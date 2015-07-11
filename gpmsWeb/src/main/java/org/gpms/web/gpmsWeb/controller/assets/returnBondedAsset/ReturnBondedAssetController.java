/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.assets.returnBondedAsset;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.gpms.web.gpmsBusinessSrv.assets.AssetAssignModel;
import org.gpms.web.gpmsBusinessSrv.assets.ReturnAssetsBusinessSrv;
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
public class ReturnBondedAssetController {

	private static final Logger logger = Logger
			.getLogger(ReturnBondedAssetController.class);

	@Autowired
	ReturnAssetsBusinessSrv returnAssetsBusinessSrv;

	@RequestMapping(value = "/returnBondedAsset", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView returnBondedAsset(
			@ModelAttribute("bondedAssetBean") BondedAssetBean bondedAssetBean,
			Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("bondedAssetBean :" + bondedAssetBean);
		}

		model.addAttribute("bondedAssetBean", bondedAssetBean);

		return new ModelAndView("assets/issueBondedAsset");
	}

	@RequestMapping(value = "/issueBondedAsset", method = RequestMethod.POST, params = "getAssetAssignedData")
	public ModelAndView getAssetAssignedData(
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
				assetAssignModel = returnAssetsBusinessSrv
						.getUserAssetById(userAssetId);

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

	@RequestMapping(value = "/issueBondedAsset", method = RequestMethod.POST, params = "returnBondedAsset")
	public ModelAndView returnBondedAsset(
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
				returnAssetsBusinessSrv.ReturnBondedItems(assetAssignModel);
			}

		}

		bondedAssetBean.setFlowType(flowType);
		model.addAttribute("bondedAssetBean", bondedAssetBean);

		return new ModelAndView("assets/issueBondedAsset");
	}

}
