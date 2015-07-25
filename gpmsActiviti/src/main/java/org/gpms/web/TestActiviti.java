/**
 * 
 */
package org.gpms.web;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.gpms.web.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author narenda.kumar
 * 
 */
public class TestActiviti implements JavaDelegate {

	@Autowired
	MailService mailService = new MailService();

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("In side TestActiviti"
				+ execution.getCurrentActivityId());
	}

}
