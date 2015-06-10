/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.login;

import java.io.IOException;

import javax.validation.Valid;

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

	@RequestMapping(value = "/login")
	public ModelAndView login(@ModelAttribute @Valid LoginBean loginBean,
			BindingResult result) throws IOException {

		System.out.println(loginBean.getUserId());

		return new ModelAndView("login/login");
	}

}
