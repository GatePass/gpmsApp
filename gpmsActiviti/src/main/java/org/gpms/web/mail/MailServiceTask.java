/**
 * 
 */
package org.gpms.web.mail;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author narenda.kumar
 * 
 */
@Component
public class MailServiceTask implements JavaDelegate {

	@Autowired
	MailService mailService = new MailService();

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		System.out.println("In side TestActiviti" + mailService);

	}

}
