/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.assets;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author narenda.kumar
 * 
 */
@Controller
public class NewAssetController {

	@RequestMapping(value = "/newAsset")
	public ModelAndView newAsset(@ModelAttribute @Valid AssetBean assetBean,
			BindingResult result, Model model) {

		model.addAttribute("assetBean", assetBean);

		return new ModelAndView("assets/newAsset");
	}

}
