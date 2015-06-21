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

/**
 * @author narenda.kumar
 * 
 */
@Controller
public class LoginController {

	// @Autowired
	// LoginBusinessSrv loginBusinessSrv;

	@RequestMapping(value = "/login")
	public String login(@ModelAttribute @Valid LoginBean loginBean,
			BindingResult result) throws IOException {

		System.out.println("result " + result.getFieldError());
		// if (result.hasErrors()) {
		// return "login/login";
		// }

		System.out.println(loginBean.getUserId());

		return "login/login";
	}

}
