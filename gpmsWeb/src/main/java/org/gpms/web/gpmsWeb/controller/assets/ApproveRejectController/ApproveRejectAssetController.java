/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.assets.ApproveRejectController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.gpms.web.gpmsBusinessSrv.assets.ApproveRejectAssetBusinessSrv;
import org.gpms.web.gpmsBusinessSrv.assets.AssetAssignModel;
import org.gpms.web.gpmsWeb.controller.assets.BondedAssetBean;
import org.gpms.web.gpmsWeb.controller.assets.BondedAssetDataConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author narenda.kumar
 * 
 */
@Controller
@SessionAttributes("bondedAssetBean")
public class ApproveRejectAssetController {

	private static final Logger logger = Logger
			.getLogger(ApproveRejectAssetController.class);

	@Autowired
	ApproveRejectAssetBusinessSrv approveRejectAssetBusinessSrv;

	@RequestMapping(value = "/approveRejectAsset", method = RequestMethod.GET)
	public ModelAndView approveRejectAssetList(
			@ModelAttribute BondedAssetBean bondedAssetBean, Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug(bondedAssetBean.toString());
		}

		// TODO this has to be dynamic and can be enhanced based on the
		// selection criteria
		String userId = "gpmsISITMgr@gmail.com";

		List<BondedAssetBean> bondedAssetBeanLst = getAllTasksForAction(userId);

		if (logger.isDebugEnabled()) {
			logger.debug("Size of the Bean List : " + bondedAssetBeanLst.size());
		}

		model.addAttribute("BondedAssetBeanLst", bondedAssetBeanLst);

		return new ModelAndView("assets/approveRejectAsset");
	}

	@RequestMapping(value = "/approveRejectAsset", method = RequestMethod.POST)
	public ModelAndView approveRejectAsset(
			@RequestParam String userAssetId,
			@RequestParam String userAssetIssueProcessId,
			@RequestParam(value = "approveOrRejectParam") String approveOrReject,
			@ModelAttribute BondedAssetBean bondedAssetBean, Model model) {

		if (userAssetId != null) {
			userAssetId = userAssetId.split(",")[0];
		}

		if (userAssetIssueProcessId != null) {
			userAssetIssueProcessId = userAssetIssueProcessId.split(",")[0];
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Passed Parameter of the Row " + "\nuserAssetId : "
					+ userAssetId + "\napproveOrReject : " + approveOrReject
					+ "\nuserAssetIssueProcessId : " + userAssetIssueProcessId);
		}

		AssetAssignModel assetAssignModel = new AssetAssignModel();
		assetAssignModel.setUserAssetId(userAssetId);
		assetAssignModel.setUserAssetIssueProcessId(userAssetIssueProcessId);

		if (approveOrReject.equalsIgnoreCase("Approve")) {
			approveRejectAssetBusinessSrv.approveAsset(assetAssignModel);
		} else if (approveOrReject.equalsIgnoreCase("Reject")) {
			approveRejectAssetBusinessSrv.rejectAsset(assetAssignModel);
		}

		// TODO this has to be dynamic and can be enhanced based on the
		// selection criteria
		String userId = "gpmsISITMgr@gmail.com";

		List<BondedAssetBean> bondedAssetBeanLst = getAllTasksForAction(userId);

		if (logger.isDebugEnabled()) {
			logger.debug("Size of the Bean List : " + bondedAssetBeanLst.size());
		}

		model.addAttribute("bondedAssetBeanLst", bondedAssetBeanLst);

		return new ModelAndView("assets/approveRejectAsset");
	}

	private List<BondedAssetBean> getAllTasksForAction(String userId) {

		List<AssetAssignModel> assetAssignModelLst = approveRejectAssetBusinessSrv
				.getAllTasksForAction(userId);

		List<BondedAssetBean> bondedAssetBeanLst = new ArrayList<BondedAssetBean>();

		Iterator<AssetAssignModel> assetAssignModelLstIter = assetAssignModelLst
				.iterator();

		while (assetAssignModelLstIter.hasNext()) {
			AssetAssignModel singleAssetAssignModel = assetAssignModelLstIter
					.next();
			BondedAssetBean bondedAsset = BondedAssetDataConverter
					.convertModelToBean(singleAssetAssignModel);
			bondedAssetBeanLst.add(bondedAsset);
		}

		return bondedAssetBeanLst;

	}

}
