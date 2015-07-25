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

	/**
	 * 
	 */
	static {
		try {
			createMailConfig();
		} catch (MessagingException messagingException) {
			messagingException.printStackTrace();
		}

	}

	/**
	 * 
	 * @throws AddressException
	 * @throws MessagingException
	 */
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

	/**
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	public Session getMailSession(final String userId, final String password) {

		Session mailSession;

		if (mailServerProperties != null) {
			try {
				createMailConfig();
			} catch (MessagingException messagingException) {
				messagingException.printStackTrace();
			}
		}

		mailSession = Session.getInstance(mailServerProperties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(userId, password);
					}
				});

		return mailSession;

	}

	/**
	 * 
	 * @param mailSession
	 * @param mailServiceParams
	 * @return
	 * @throws MessagingException
	 */
	public MimeMessage generateMessage(Session mailSession,
			MailServiceParams mailServiceParams) throws MessagingException {

		MimeMessage generateMailMessage;

		generateMailMessage = new MimeMessage(mailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO,
				new InternetAddress(mailServiceParams.getMailToAddress()));
		// generateMailMessage.addRecipient(Message.RecipientType.CC,
		// new InternetAddress(mailServiceParams.getMailCcAddress()));
		generateMailMessage.setSubject(mailServiceParams.getMailSubject());
		generateMailMessage.setContent(mailServiceParams.getMailHtmlBody(),
				"text/html");

		return generateMailMessage;

	}

	/**
	 * 
	 * @param mailSession
	 * @param mailServiceParams
	 * @param generateMailMessage
	 * @throws MessagingException
	 */
	public void sendMessage(Session mailSession,
			MailServiceParams mailServiceParams, MimeMessage generateMailMessage)
			throws MessagingException {

		Transport transport = mailSession.getTransport("smtp");

		transport.connect("smtp.gmail.com", mailServiceParams.getMailUserId(),
				mailServiceParams.getMailPassword());
		transport.sendMessage(generateMailMessage,
				generateMailMessage.getAllRecipients());
		transport.close();
	}

}
