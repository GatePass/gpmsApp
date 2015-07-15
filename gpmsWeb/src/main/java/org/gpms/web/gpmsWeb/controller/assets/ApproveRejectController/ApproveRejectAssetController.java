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
import org.gpms.web.gpmsBusinessSrv.assets.AssetMgmtBusinessSrv;
import org.gpms.web.gpmsBusinessSrv.assets.AssetModel;
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

	@Autowired
	AssetMgmtBusinessSrv assetMgmtBusinessSrv;

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
			@RequestParam String userAssetReturnProcessId,
			@RequestParam String assetId,
			@RequestParam(value = "approveOrRejectParam") String approveOrReject,
			@ModelAttribute BondedAssetBean bondedAssetBean, Model model) {

		if (userAssetId != null) {
			userAssetId = userAssetId.split(",")[0];
		}

		if (userAssetIssueProcessId != null) {
			userAssetIssueProcessId = userAssetIssueProcessId.split(",")[0];
		}

		if (userAssetReturnProcessId != null) {
			userAssetReturnProcessId = userAssetReturnProcessId.split(",")[0];
			if (userAssetReturnProcessId.equals("NONE")) {
				userAssetReturnProcessId = null;
			}
		}

		if (assetId != null) {
			assetId = assetId.split(",")[0];
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Passed Parameter of the Row " + "\nuserAssetId : "
					+ userAssetId + "\napproveOrReject : " + approveOrReject
					+ "\nuserAssetIssueProcessId : " + userAssetIssueProcessId
					+ "\nuserAssetReturnProcessId : "
					+ userAssetReturnProcessId + "\nassetId :" + assetId);
		}

		AssetAssignModel assetAssignModel = new AssetAssignModel();
		assetAssignModel.setUserAssetId(userAssetId);
		assetAssignModel.setUserAssetIssueProcessId(userAssetIssueProcessId);
		assetAssignModel.setUserAssetReturnProcessId(userAssetReturnProcessId);
		assetAssignModel.setAssetId(assetId);

		AssetModel assetModel = assetMgmtBusinessSrv
				.getAssetById(assetAssignModel.getAssetId());
		String processId = null;
		String flowType = null;
		if (assetModel != null) {
			if ("AVAILABLE".equals(assetModel.getAssetStatus())) {
				processId = userAssetIssueProcessId;
				flowType = "ISSUE";
			} else if ("ASSIGNED".equals(assetModel.getAssetStatus())) {
				if (userAssetReturnProcessId != null) {
					processId = userAssetReturnProcessId;
					flowType = "RETURN";
				}
			} else if ("INPROCESS".equals(assetModel.getAssetStatus())) {
				if (userAssetIssueProcessId != null
						&& userAssetReturnProcessId == null) {
					processId = userAssetIssueProcessId;
					flowType = "ISSUE";
				}
				if (userAssetReturnProcessId != null) {
					processId = userAssetReturnProcessId;
					flowType = "RETURN";
				}
			}
		}

		if (approveOrReject.equalsIgnoreCase("Approve")) {
			approveRejectAssetBusinessSrv.approveAsset(assetAssignModel,
					processId, flowType);
		} else if (approveOrReject.equalsIgnoreCase("Reject")) {
			approveRejectAssetBusinessSrv.rejectAsset(assetAssignModel,
					processId, flowType);
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
