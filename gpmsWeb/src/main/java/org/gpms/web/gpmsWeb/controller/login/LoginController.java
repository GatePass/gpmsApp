/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.login;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
	LocaleResolver localeResolver;

	/**
	 * 
	 * @param request
	 * @param response
	 * @param loginBean
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute LoginBean loginBean,
			Model model) throws Exception {

		if (request.getParameter("lang") != null) {
			Locale locale = StringUtils.parseLocaleString(request
					.getParameter("lang"));
			localeResolver.setLocale(request, response, locale);
			loginBean.setLangId(request.getParameter("lang"));
		}

		model.addAttribute("loginBean", loginBean);

		return new ModelAndView("login/login");
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

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
