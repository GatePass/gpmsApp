/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.userMgmt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author narenda.kumar
 * 
 */
@Controller
public class NewUserController {

	@RequestMapping(value = "/newUser")
	public ModelAndView newUser(Model model) {

		UserBean userBean = new UserBean();

		List<String> questionId = new ArrayList<String>();
		questionId.add("This is a question");
		model.addAttribute("questionId", questionId);
		userBean.setQuestionId(questionId);

		model.addAttribute("userBean", userBean);

		return new ModelAndView("userMgmt/newUser");
	}

}
