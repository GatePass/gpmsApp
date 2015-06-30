/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.login;

import java.io.IOException;

import javax.validation.Valid;

import org.gpms.web.gpmsBusinessSrv.login.LoginBusinessSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author narenda.kumar
 * 
 */
@Controller
public class LoginController {

	@Autowired
	LoginBusinessSrv loginBusinessSrv;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute LoginBean loginBean, Model model)
			throws IOException {
		return new ModelAndView("login/login");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, params = "submit")
	public String submit(@ModelAttribute @Valid LoginBean loginBean,
			BindingResult result, Model model) throws IOException {

		boolean isValidatedUser = loginBusinessSrv.validateLoginCredentials(
				loginBean.getUserId(), loginBean.getPassword());

		if (isValidatedUser) {
			return "redirect:navigation";
		}

		return "login/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, params = "reset")
	public String reset(Model model) throws IOException {
		return "redirect:passwordReset";
	}

}
