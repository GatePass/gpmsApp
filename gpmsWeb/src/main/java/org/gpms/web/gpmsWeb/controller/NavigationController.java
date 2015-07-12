/**
 * 
 */
package org.gpms.web.gpmsWeb.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.gpms.web.gpmsWeb.controller.assets.BondedAssetBean;
import org.gpms.web.gpmsWeb.controller.userMgmt.UserBean;
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

	@RequestMapping(value = "/navigation")
	public ModelAndView navigation() throws IOException {
		return new ModelAndView("common/navigation");
	}

	@RequestMapping(value = "/navigation", params = "createUser")
	public String createUser(HttpServletRequest request) throws IOException {
		UserBean userBean = new UserBean();
		request.getSession().setAttribute("userBean", userBean);
		return "redirect:newUser";
	}

	@RequestMapping(value = "/navigation", params = "modifyUser")
	public String modifyUser(HttpServletRequest request) throws IOException {
		UserBean userBean = new UserBean();
		userBean.setFlowType("modifyUser");
		request.getSession().setAttribute("userBean", userBean);
		return "redirect:modifyDeleteUser";
	}

	@RequestMapping(value = "/navigation", params = "deleteUser")
	public String deleteUser(HttpServletRequest request) throws IOException {
		UserBean userBean = new UserBean();
		userBean.setFlowType("deleteUser");
		request.getSession().setAttribute("userBean", userBean);
		return "redirect:modifyDeleteUser";
	}

	@RequestMapping(value = "/navigation", params = "createAsset")
	public String createAsset() throws IOException {
		return "redirect:newAsset";
	}

	@RequestMapping(value = "/navigation", params = "modifyAsset")
	public String modifyAsset(RedirectAttributes redirectAttrs)
			throws IOException {
		redirectAttrs.addFlashAttribute("flowType", "modifyAsset");
		return "redirect:modifyDeleteAsset";
	}

	@RequestMapping(value = "/navigation", params = "deleteAsset")
	public String deleteAsset(RedirectAttributes redirectAttrs)
			throws IOException {
		redirectAttrs.addFlashAttribute("flowType", "deleteAsset");
		return "redirect:modifyDeleteAsset";
	}

	@RequestMapping(value = "/navigation", params = "createAssetType")
	public String createAssetType() throws IOException {
		return "redirect:newAssetType";
	}

	@RequestMapping(value = "/navigation", params = "modifyAssetType")
	public String modifyAssetType() throws IOException {
		return "redirect:modifyAssetType";
	}

	@RequestMapping(value = "/navigation", params = "bondedItemAssign")
	public String bondedItemAssign(HttpServletRequest request)
			throws IOException {
		BondedAssetBean bondedAssetBean = new BondedAssetBean();
		bondedAssetBean.setFlowType("bondedItemAssign");
		request.getSession().setAttribute("bondedAssetBean", bondedAssetBean);
		return "redirect:issueBondedAsset";
	}

	@RequestMapping(value = "/navigation", params = "bondedItemReturn")
	public String bondedItemReturn(HttpServletRequest request)
			throws IOException {
		BondedAssetBean bondedAssetBean = new BondedAssetBean();
		bondedAssetBean.setFlowType("bondedItemReturn");
		request.getSession().setAttribute("bondedAssetBean", bondedAssetBean);
		return "redirect:returnBondedAsset";
	}

	@RequestMapping(value = "/navigation", params = "approveRejectAsset")
	public String approveRejectAsset(HttpServletRequest request)
			throws IOException {
		BondedAssetBean bondedAssetBean = new BondedAssetBean();
		bondedAssetBean.setFlowType("approveRejectAsset");
		request.getSession().setAttribute("bondedAssetBean", bondedAssetBean);
		return "redirect:approveRejectAsset";
	}

	@RequestMapping(value = "/navigation", params = "modifyBondedAsset")
	public String resubmitForApproval(HttpServletRequest request)
			throws IOException {
		BondedAssetBean bondedAssetBean = new BondedAssetBean();
		bondedAssetBean.setFlowType("itemCorrection");
		request.getSession().setAttribute("bondedAssetBean", bondedAssetBean);
		return "redirect:modifyBondedAsset";
	}

}
