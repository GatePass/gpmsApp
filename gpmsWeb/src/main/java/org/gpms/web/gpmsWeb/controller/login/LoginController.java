/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.login;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.gpms.web.gpmsBusinessSrv.login.LoginBusinessSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author narenda.kumar
 * 
 */
@Controller
public class LoginController {

	@Autowired
	LoginBusinessSrv loginBusinessSrv;

	@Autowired
	LocaleResolver localeResolver;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView localeChange(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute LoginBean loginBean,
			Model model) throws Exception {

		if (loginBean.getLangId() != null) {
			Locale locale = StringUtils
					.parseLocaleString(loginBean.getLangId());
			localeResolver.setLocale(request, response, locale);
		}

		return new ModelAndView("login/login");
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute LoginBean loginBean, Model model)
			throws IOException {
		return new ModelAndView("login/login");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, params = "submitL")
	public String submitL(@ModelAttribute @Valid LoginBean loginBean,
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
