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

	@RequestMapping(value = "/navigation", params = "deleteUser")
	public String deleteUser() throws IOException {
		return "redirect:newUser";
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
