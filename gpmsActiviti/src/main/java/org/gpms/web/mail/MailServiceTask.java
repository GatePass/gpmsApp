/**
 * 
 */
package org.gpms.web.mail;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * @author narenda.kumar
 * 
 */
@Component
public class MailServiceTask implements JavaDelegate {

	// @Autowired
	MailServiceConfiguration mailServiceConfiguration = new MailServiceConfiguration();

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("In side TestActiviti" + mailServiceConfiguration);
		mailServiceConfiguration.createMailConfig();
	}

}
