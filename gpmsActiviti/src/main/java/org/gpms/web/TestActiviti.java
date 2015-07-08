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
		// TODO Auto-generated method stub
		System.out.println("In side TestActiviti"
				+ execution.getCurrentActivityId());

		// MailServiceParams mailServiceParams = new MailServiceParams();
		// mailServiceParams.setMailUserId("gpmsisituser3@gmail.com");
		// mailServiceParams.setMailPassword("gpmsisituser3777#$");
		// mailServiceParams.setMailToAddress("gpmsISITMgr@gmail.com");
		// mailServiceParams
		// .setMailSubject("Approve required for the new asset assigned"
		// + "assedId" + "to the member with user Id"
		// + "corpEmailId");
		// String mailHtmlBody =
		// "Test email from gpmsisituser3@gmail.com to gpmsISITMgr@gmail.com"
		// + "<br><br> Regards, <br>gpmsisituser3@gmail.com";
		// mailServiceParams.setMailHtmlBody(mailHtmlBody);
		//
		// System.out.println("mailServiceParams : " + mailServiceParams);
		//
		// Session mailSession = mailService.getMailSession(
		// mailServiceParams.getMailUserId(),
		// mailServiceParams.getMailPassword());
		//
		// System.out.println("mailSession : " + mailSession);
		//
		// MimeMessage generateMailMessage = mailService.generateMessage(
		// mailSession, mailServiceParams);
		//
		// System.out.println("generateMailMessage : " + generateMailMessage);
		//
		// mailService.sendMessage(mailSession, mailServiceParams,
		// generateMailMessage);

	}

}
