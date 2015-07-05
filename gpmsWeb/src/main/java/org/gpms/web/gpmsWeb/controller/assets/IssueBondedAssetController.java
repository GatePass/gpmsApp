/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.assets;

import javax.validation.Valid;

import org.gpms.web.gpmsBusinessSrv.assets.AssetAssignModel;
import org.gpms.web.gpmsBusinessSrv.assets.IssueAssetsBusinessSrv;
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
public class IssueBondedAssetController {

	@Autowired
	IssueAssetsBusinessSrv issueAssetsBusinessSrv;

	@RequestMapping(value = "/issueBondedAsset", method = RequestMethod.GET)
	public ModelAndView issueBondedAsset(
			@ModelAttribute BondedAssetBean bondedAssetBean, Model model) {

		model.addAttribute("bondedAssetBean", bondedAssetBean);

		return new ModelAndView("assets/issueBondedAsset");
	}

	@RequestMapping(value = "/issueBondedAsset")
	public ModelAndView issueBondedAsset(
			@ModelAttribute @Valid BondedAssetBean bondedAssetBean,
			BindingResult result, Model model) {

		AssetAssignModel assetAssignModel = new AssetAssignModel();

		assetAssignModel.setAssetId(bondedAssetBean.getAssetId());
		assetAssignModel.setUserCorpEmail(bondedAssetBean.getUserCorpEmail());
		assetAssignModel.setUserAssetIssueDate(bondedAssetBean
				.getUserAssetIssueDate());
		assetAssignModel.setUserAssetReturnDate(bondedAssetBean
				.getUserAssetReturnDate());

		issueAssetsBusinessSrv.IssueBondedItems(assetAssignModel);

		model.addAttribute("bondedAssetBean", bondedAssetBean);

		return new ModelAndView("assets/issueBondedAsset");
	}

}
