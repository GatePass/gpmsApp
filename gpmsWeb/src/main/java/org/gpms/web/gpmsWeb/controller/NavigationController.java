/**
 * 
 */
package org.gpms.web.gpmsWeb.controller;

import java.io.IOException;

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
	public String createUser() throws IOException {
		return "redirect:newUser";
	}

	@RequestMapping(value = "/navigation", params = "modifyUser")
	public String modifyUser(RedirectAttributes redirectAttrs)
			throws IOException {
		redirectAttrs.addFlashAttribute("flowType", "modifyUser");
		return "redirect:modifyDeleteUser";
	}

	@RequestMapping(value = "/navigation", params = "deleteUser")
	public String deleteUser(RedirectAttributes redirectAttrs)
			throws IOException {
		redirectAttrs.addFlashAttribute("flowType", "deleteUser");
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
	public String bondedItemAssign() throws IOException {
		return "redirect:issueBondedAsset";
	}

	@RequestMapping(value = "/navigation", params = "bondedItemReturn")
	public String bondedItemReturn() throws IOException {
		return "redirect:returnBondedAsset";
	}

	@RequestMapping(value = "/navigation", params = "approveRejectAsset")
	public String approveRejectAsset() throws IOException {
		return "redirect:approveRejectAsset";
	}

	@RequestMapping(value = "/navigation", params = "resubmitForApproval")
	public String resubmitForApproval() throws IOException {
		return "redirect:resubmitForApproval";
	}

}
