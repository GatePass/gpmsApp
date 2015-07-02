/**
 * 
 */
package org.gpms.web.gpmsWeb.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public String modifyUser() throws IOException {
		return "redirect:modifyUser";
	}

	@RequestMapping(value = "/navigation", params = "deleteUser")
	public String deleteUser() throws IOException {
		return "redirect:deleteUser";
	}

	@RequestMapping(value = "/navigation", params = "createAsset")
	public String createAsset() throws IOException {
		return "redirect:newAsset";
	}

	@RequestMapping(value = "/navigation", params = "modifyAsset")
	public String modifyAsset() throws IOException {
		return "redirect:modifyAsset";
	}

	@RequestMapping(value = "/navigation", params = "deleteAsset")
	public String deleteAsset() throws IOException {
		return "redirect:deleteAsset";
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

}
