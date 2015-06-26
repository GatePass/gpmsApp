/**
 * 
 */
package org.gpms.web.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

/**
 * @author narenda.kumar
 * 
 */
@Component
public class MailService {

	static Properties mailServerProperties;
	Session mailSession;
	MimeMessage generateMailMessage;

	static {
		try {
			createMailConfig();
		} catch (MessagingException messagingException) {
			messagingException.printStackTrace();
		}

	}

	private static void createMailConfig() throws AddressException,
			MessagingException {
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");
		mailServerProperties.put("mail.smtp.socketFactory.port", "465");
		mailServerProperties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.port", "465");
	}

	public Session getMailSession(final String userId, final String password) {

		if (mailServerProperties != null) {
			try {
				createMailConfig();
			} catch (MessagingException messagingException) {
				messagingException.printStackTrace();
			}
		}

		mailSession = Session.getDefaultInstance(mailServerProperties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(userId, password);
						// "gatepassapp1@gmail.com", "gatepass1234");
					}
				});

		return mailSession;

	}

	public void generateMessage(MailServiceParams mailServiceParams)
			throws MessagingException {

		if (mailSession == null) {
			getMailSession(mailServiceParams.getMailUserId(),
					mailServiceParams.getMailPassword());
		}

		generateMailMessage = new MimeMessage(mailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO,
				new InternetAddress(mailServiceParams.getMailToAddress()));
		generateMailMessage.addRecipient(Message.RecipientType.CC,
				new InternetAddress(mailServiceParams.getMailCcAddress()));
		generateMailMessage.setSubject(mailServiceParams.getMailSubject());
		generateMailMessage.setContent(mailServiceParams.getMailHtmlBody(),
				"text/html");

	}

	public void sendMessage(MailServiceParams mailServiceParams)
			throws MessagingException {

		Transport transport = mailSession.getTransport("smtp");

		transport.connect("smtp.gmail.com", mailServiceParams.getMailUserId(),
				mailServiceParams.getMailPassword());
		transport.sendMessage(generateMailMessage,
				generateMailMessage.getAllRecipients());
		transport.close();

	}

}
