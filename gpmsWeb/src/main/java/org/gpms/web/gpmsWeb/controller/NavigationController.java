/**
 * 
 */
package org.gpms.web.gpmsWeb.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.gpms.web.gpmsBusinessSrv.userMgmt.UserMgmtBusinessSrv;
import org.gpms.web.gpmsBusinessSrv.userMgmt.UserModel;
import org.gpms.web.gpmsWeb.controller.assets.BondedAssetBean;
import org.gpms.web.gpmsWeb.controller.userMgmt.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author narenda.kumar
 * 
 */
@Controller
public class NavigationController {

	private static final Logger logger = Logger
			.getLogger(NavigationController.class);

	@Autowired
	UserMgmtBusinessSrv userMgmtBusinessSrv;

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/", "/navigation" })
	public ModelAndView navigation(HttpServletRequest request)
			throws IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("Request to render Navigation flow");
		}

		String loggedInUser = request.getUserPrincipal().getName();

		UserModel userModel = userMgmtBusinessSrv
				.getUserByCorpEmail(loggedInUser);

		request.getSession().setAttribute("userLoggedModel", userModel);

		return new ModelAndView("common/navigation");
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/navigation", params = "createUser")
	public String createUser(HttpServletRequest request) throws IOException {
		UserBean userBean = new UserBean();
		if (logger.isDebugEnabled()) {
			logger.debug("Request to render createUser flow from navigation page");
		}
		request.getSession().setAttribute("userBean", userBean);
		return "redirect:newUser";
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/navigation", params = "modifyUser")
	public String modifyUser(HttpServletRequest request) throws IOException {
		UserBean userBean = new UserBean();

		if (logger.isDebugEnabled()) {
			logger.debug("Request to render modifyUser flow from navigation page");
		}

		userBean.setFlowType("modifyUser");
		request.getSession().setAttribute("userBean", userBean);
		return "redirect:modifyDeleteUser";
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/navigation", params = "deleteUser")
	public String deleteUser(HttpServletRequest request) throws IOException {
		UserBean userBean = new UserBean();

		if (logger.isDebugEnabled()) {
			logger.debug("Request to render deleteUser flow from navigation page");
		}

		userBean.setFlowType("deleteUser");
		request.getSession().setAttribute("userBean", userBean);
		return "redirect:modifyDeleteUser";
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/navigation", params = "createAsset")
	public String createAsset() throws IOException {

		if (logger.isDebugEnabled()) {
			logger.debug("Request to render createAsset flow from navigation page");
		}

		return "redirect:newAsset";
	}

	/**
	 * 
	 * @param redirectAttrs
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/navigation", params = "modifyAsset")
	public String modifyAsset(RedirectAttributes redirectAttrs)
			throws IOException {

		if (logger.isDebugEnabled()) {
			logger.debug("Request to render modifyAsset flow from navigation page");
		}

		redirectAttrs.addFlashAttribute("flowType", "modifyAsset");
		return "redirect:modifyDeleteAsset";
	}

	/**
	 * 
	 * @param redirectAttrs
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/navigation", params = "deleteAsset")
	public String deleteAsset(RedirectAttributes redirectAttrs)
			throws IOException {

		if (logger.isDebugEnabled()) {
			logger.debug("Request to render deleteAsset flow from navigation page");
		}

		redirectAttrs.addFlashAttribute("flowType", "deleteAsset");
		return "redirect:modifyDeleteAsset";
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/navigation", params = "createAssetType")
	public String createAssetType() throws IOException {

		if (logger.isDebugEnabled()) {
			logger.debug("Request to render createAssetType flow from navigation page");
		}

		return "redirect:newAssetType";
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/navigation", params = "modifyAssetType")
	public String modifyAssetType() throws IOException {

		if (logger.isDebugEnabled()) {
			logger.debug("Request to render modifyAssetType flow from navigation page");
		}

		return "redirect:modifyAssetType";
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/navigation", params = "bondedItemAssign")
	public String bondedItemAssign(HttpServletRequest request)
			throws IOException {
		BondedAssetBean bondedAssetBean = new BondedAssetBean();

		if (logger.isDebugEnabled()) {
			logger.debug("Request to render bondedItemAssign flow from navigation page");
		}

		bondedAssetBean.setFlowType("bondedItemAssign");
		request.getSession().setAttribute("bondedAssetBean", bondedAssetBean);
		return "redirect:issueBondedAsset";
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/navigation", params = "bondedItemReturn")
	public String bondedItemReturn(HttpServletRequest request)
			throws IOException {
		BondedAssetBean bondedAssetBean = new BondedAssetBean();

		if (logger.isDebugEnabled()) {
			logger.debug("Request to render bondedItemReturn flow from navigation page");
		}

		bondedAssetBean.setFlowType("bondedItemReturn");
		request.getSession().setAttribute("bondedAssetBean", bondedAssetBean);
		return "redirect:returnBondedAsset";
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/navigation", params = "approveRejectAsset")
	public String approveRejectAsset(HttpServletRequest request)
			throws IOException {
		BondedAssetBean bondedAssetBean = new BondedAssetBean();

		if (logger.isDebugEnabled()) {
			logger.debug("Request to render approveRejectAsset flow from navigation page");
		}

		bondedAssetBean.setFlowType("approveRejectAsset");
		request.getSession().setAttribute("bondedAssetBean", bondedAssetBean);
		return "redirect:approveRejectAsset";
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/navigation", params = "modifyBondedAsset")
	public String resubmitForApproval(HttpServletRequest request)
			throws IOException {
		BondedAssetBean bondedAssetBean = new BondedAssetBean();

		if (logger.isDebugEnabled()) {
			logger.debug("Request to render modifyBondedAsset flow from navigation page");
		}

		bondedAssetBean.setFlowType("itemCorrection");
		request.getSession().setAttribute("bondedAssetBean", bondedAssetBean);
		return "redirect:modifyBondedAsset";
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/navigation", params = "employeeReportView")
	public String employeeGatePasses(HttpServletRequest request,
			RedirectAttributes redirectAttrs) throws IOException {
		redirectAttrs.addAttribute("employeeReportView", "employeeReportView");
		return "redirect:employeeGatePassReport";
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/navigation", params = "managementReportView")
	public String managementReportView(HttpServletRequest request,
			RedirectAttributes redirectAttrs) throws IOException {
		redirectAttrs.addAttribute("managementReportView",
				"managementReportView");
		return "redirect:employeeGatePassReport";
	}

}
