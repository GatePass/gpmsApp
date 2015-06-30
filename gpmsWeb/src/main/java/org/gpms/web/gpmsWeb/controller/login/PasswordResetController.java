/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.login;

import java.util.ArrayList;
import java.util.List;

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
public class PasswordResetController {

	// @Autowired
	// LoginBusinessSrv loginBusinessSrv;

	@RequestMapping(value = "/passwordReset")
	public ModelAndView passwordReset(
			@ModelAttribute @Valid PasswordResetBean passwordResetBean,
			BindingResult result, Model model) {

		List<String> questionId = new ArrayList<String>();
		questionId.add("This is a question");
		model.addAttribute("questionId", questionId);
		passwordResetBean.setQuestionId(questionId);
		passwordResetBean.setLoginId("Narendra");

		model.addAttribute("passwordResetBean", passwordResetBean);

		return new ModelAndView("login/passwordReset");
	}

}
