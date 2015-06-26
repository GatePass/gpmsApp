/**
 * 
 */
package org.gpms.web.mail;

import org.springframework.stereotype.Component;

/**
 * @author narenda.kumar
 * 
 */
@Component
public class MailServiceParams {

	private String mailUserId;

	private String mailPassword;

	private String mailToAddress;

	private String mailCcAddress;

	private String mailSubject;

	private String mailHtmlBody;

	/**
	 * @return the mailUserId
	 */
	public String getMailUserId() {
		return mailUserId;
	}

	/**
	 * @param mailUserId
	 *            the mailUserId to set
	 */
	public void setMailUserId(String mailUserId) {
		this.mailUserId = mailUserId;
	}

	/**
	 * @return the mailPassword
	 */
	public String getMailPassword() {
		return mailPassword;
	}

	/**
	 * @param mailPassword
	 *            the mailPassword to set
	 */
	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	/**
	 * @return the mailToAddress
	 */
	public String getMailToAddress() {
		return mailToAddress;
	}

	/**
	 * @param mailToAddress
	 *            the mailToAddress to set
	 */
	public void setMailToAddress(String mailToAddress) {
		this.mailToAddress = mailToAddress;
	}

	/**
	 * @return the mailCcAddress
	 */
	public String getMailCcAddress() {
		return mailCcAddress;
	}

	/**
	 * @param mailCcAddress
	 *            the mailCcAddress to set
	 */
	public void setMailCcAddress(String mailCcAddress) {
		this.mailCcAddress = mailCcAddress;
	}

	/**
	 * @return the mailSubject
	 */
	public String getMailSubject() {
		return mailSubject;
	}

	/**
	 * @param mailSubject
	 *            the mailSubject to set
	 */
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	/**
	 * @return the mailHtmlBody
	 */
	public String getMailHtmlBody() {
		return mailHtmlBody;
	}

	/**
	 * @param mailHtmlBody
	 *            the mailHtmlBody to set
	 */
	public void setMailHtmlBody(String mailHtmlBody) {
		this.mailHtmlBody = mailHtmlBody;
	}

}
