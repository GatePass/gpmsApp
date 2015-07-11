/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.assets.resubmitForApproval;

import org.gpms.web.gpmsBusinessSrv.assets.AssetAssignModel;
import org.gpms.web.gpmsWeb.controller.assets.BondedAssetBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class ResubmitForApprovalController {

	@RequestMapping(value = "/resubmitForApproval", method = RequestMethod.GET)
	public ModelAndView resubmitForApproval(
			@ModelAttribute BondedAssetBean bondedAssetBean, Model model) {

		AssetAssignModel assetAssignModel = new AssetAssignModel();

		assetAssignModel.setAssetId(bondedAssetBean.getAssetId());
		assetAssignModel.setUserCorpEmail(bondedAssetBean.getUserCorpEmail());

		// issueAssetsBusinessSrv.approveRejectAsset(assetAssignModel, false);

		model.addAttribute("bondedAssetBean", bondedAssetBean);

		return new ModelAndView("assets/resubmitForApproval");
	}

}
