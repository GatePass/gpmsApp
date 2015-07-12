/**
 * 
 */
package org.gpms.web.mail;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.runtime.Execution;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author narenda.kumar
 * 
 */
@Component
public class MailServiceTask implements JavaDelegate {

	private static final Logger logger = Logger
			.getLogger(MailServiceTask.class);

	@Autowired
	MailService mailService = new MailService();

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug("ENTERING");
			logger.debug("execution.getCurrentActivityId() "
					+ execution.getCurrentActivityId());
		}

		String processInstanceId = execution.getProcessInstanceId();

		if (logger.isDebugEnabled()) {
			logger.debug("processInstanceId " + processInstanceId);
		}

		Execution executionMain = execution.getEngineServices()
				.getRuntimeService().createExecutionQuery()
				.processInstanceId(processInstanceId).singleResult();

		if (logger.isDebugEnabled()) {
			logger.debug("executionMain " + executionMain);
		}

		MailServiceParams mailServiceParams = (MailServiceParams) execution
				.getEngineServices()
				.getRuntimeService()
				.getVariable(executionMain.getId(),
						execution.getCurrentActivityId());

		if (logger.isDebugEnabled()) {
			logger.debug("mailServiceParams " + mailServiceParams);
		}

		Session mailSession = mailService.getMailSession(
				mailServiceParams.getMailUserId(),
				mailServiceParams.getMailPassword());

		if (logger.isDebugEnabled()) {
			logger.debug("mailSession " + mailSession);
		}

		MimeMessage generateMailMessage = mailService.generateMessage(
				mailSession, mailServiceParams);

		if (logger.isDebugEnabled()) {
			logger.debug("generateMailMessage " + generateMailMessage);
		}

		mailService.sendMessage(mailSession, mailServiceParams,
				generateMailMessage);

	}
}
