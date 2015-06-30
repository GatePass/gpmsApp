/**
 * 
 */
package org.gpms.web.entities.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author narenda.kumar
 * 
 */
@Entity(name = "GPMS_SECURITY_QUESTIONS")
public class SecurityQuestionEntity {

	@Id
	@Column(name = "SECURITY_QUESTIONS_ID")
	private String securityQuestionId;

	@Column(name = "SECURITY_QUESTIONS")
	private String securityQuestion;

	/**
	 * @return the securityQuestionId
	 */
	public String getSecurityQuestionId() {
		return securityQuestionId;
	}

	/**
	 * @param securityQuestionId
	 *            the securityQuestionId to set
	 */
	public void setSecurityQuestionId(String securityQuestionId) {
		this.securityQuestionId = securityQuestionId;
	}

	/**
	 * @return the securityQuestion
	 */
	public String getSecurityQuestion() {
		return securityQuestion;
	}

	/**
	 * @param securityQuestion
	 *            the securityQuestion to set
	 */
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

}
