/**
 * 
 */
package org.gpms.web.mail;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.runtime.Execution;
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

		System.out.println("In side MailServiceTask"
				+ execution.getCurrentActivityId());

		String processInstanceId = execution.getProcessInstanceId();

		Execution executionMain = execution.getEngineServices()
				.getRuntimeService().createExecutionQuery()
				.processInstanceId(processInstanceId).singleResult();

		MailServiceParams mailServiceParams = (MailServiceParams) execution
				.getEngineServices()
				.getRuntimeService()
				.getVariable(executionMain.getId(),
						execution.getCurrentActivityId());

		System.out.println("mailServiceParams : " + mailServiceParams);

		Session mailSession = mailService.getMailSession(
				mailServiceParams.getMailUserId(),
				mailServiceParams.getMailPassword());

		MimeMessage generateMailMessage = mailService.generateMessage(
				mailSession, mailServiceParams);

		mailService.sendMessage(mailSession, mailServiceParams,
				generateMailMessage);

	}
}
