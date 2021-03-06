/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.assets.resubmitForApproval;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.gpms.web.gpmsBusinessSrv.assets.ApproveRejectAssetBusinessSrv;
import org.gpms.web.gpmsBusinessSrv.assets.AssetAssignModel;
import org.gpms.web.gpmsBusinessSrv.userMgmt.UserModel;
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
public class ResubmitForApprovalController {

	private static final Logger logger = Logger
			.getLogger(ResubmitForApprovalController.class);

	@Autowired
	ApproveRejectAssetBusinessSrv approveRejectAssetBusinessSrv;

	/**
	 * Loads the initial list of action items for the logged in user to perform
	 * 
	 * @param bondedAssetBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/modifyBondedAsset", method = RequestMethod.GET)
	public ModelAndView modifyBondedAsset(HttpServletRequest request,
			@ModelAttribute BondedAssetBean bondedAssetBean, Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug(bondedAssetBean.toString());
		}

		UserModel loggedInUserModel = (UserModel) request.getSession()
				.getAttribute("userLoggedModel");
		String usersPersonalEmail = loggedInUserModel.getUserPersonnalEmail();

		List<BondedAssetBean> bondedAssetBeanLst = getAllTasksForAction(usersPersonalEmail);

		if (logger.isDebugEnabled()) {
			logger.debug("Size of the Bean List : " + bondedAssetBeanLst.size());
		}

		model.addAttribute("BondedAssetBeanLst", bondedAssetBeanLst);

		return new ModelAndView("assets/modifyBondedAsset");
	}

	/**
	 * Performs the action when "Correction" button is clicked on the ReSubmit
	 * for Approval page
	 * 
	 * @param userAssetId
	 * @param userAssetIssueProcessId
	 * @param userAssetReturnProcessId
	 * @param assetId
	 * @param correctionParam
	 * @param bondedAssetBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/modifyBondedAsset", method = RequestMethod.POST)
	public ModelAndView modifyBondedAsset(@RequestParam String userAssetId,
			@RequestParam String userAssetIssueProcessId,
			@RequestParam String userAssetReturnProcessId,
			@RequestParam String assetId,
			@RequestParam(value = "correctionParam") String correctionParam,
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
					+ userAssetId + "\ncorrectionParam : " + correctionParam
					+ "\nuserAssetIssueProcessId : " + userAssetIssueProcessId
					+ "\nuserAssetReturnProcessId : "
					+ userAssetReturnProcessId + "\nassetId :" + assetId);
		}

		String returnFlow = "false";
		if (userAssetReturnProcessId != null) {
			returnFlow = "true";
		}

		AssetAssignModel assetAssignModel = new AssetAssignModel();
		String flowType = bondedAssetBean.getFlowType();

		if (bondedAssetBean != null) {

			if (userAssetId != null) {
				assetAssignModel = approveRejectAssetBusinessSrv
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
		model.addAttribute("returnFlow", returnFlow);

		return new ModelAndView("assets/issueBondedAsset");
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
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
