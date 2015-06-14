/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.login;

import java.io.IOException;

import javax.validation.Valid;

import org.gpms.web.gpmsBusinessSrv.LoginBusinessSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author narenda.kumar
 * 
 */
@Controller
public class LoginController {

	@Autowired
	LoginBusinessSrv loginBusinessSrv;

	@RequestMapping(value = "/login")
	public ModelAndView login(@ModelAttribute @Valid LoginBean loginBean,
			BindingResult result) throws IOException {

		System.out.println(loginBean.getUserId());

		System.out.println("loginBusinessSrv " + loginBusinessSrv);
		loginBusinessSrv.getLogin();

		return new ModelAndView("login/login");
	}

}
