/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.login;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.gpms.web.gpmsBusinessSrv.login.LoginBusinessSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

	private static final Logger logger = Logger
			.getLogger(LoginController.class);

	@Autowired
	LoginBusinessSrv loginBusinessSrv;

	@Autowired
	LocaleResolver localeResolver;

	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute LoginBean loginBean,
			Model model) throws Exception {

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  "
				+ request.getParameter("lang"));

		if (request.getParameter("lang") != null) {
			Locale locale = StringUtils.parseLocaleString(request
					.getParameter("lang"));
			localeResolver.setLocale(request, response, locale);
			loginBean.setLangId(request.getParameter("lang"));
		}

		model.addAttribute("loginBean", loginBean);

		return new ModelAndView("login/login");
	}

	// @RequestMapping(value = "/login", method = RequestMethod.GET)
	// public ModelAndView login(@ModelAttribute LoginBean loginBean, Model
	// model)
	// throws IOException {
	//
	// System.out
	// .println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  ???????????????????");
	//
	// logger.debug("login ");
	//
	// return new ModelAndView("login/login");
	// }

	@RequestMapping(value = "/login", method = RequestMethod.POST, params = "submitL")
	public String submitL(@ModelAttribute @Valid LoginBean loginBean,
			BindingResult result, Model model) throws IOException {

		System.out.println("22222222222222222222222222222");

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

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("common/403");
		return model;

	}

}
