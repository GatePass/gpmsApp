/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.login;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author narenda.kumar
 * 
 */
public class PasswordResetBean implements Serializable {

	@NotEmpty(message = "Corp Email Id cannot be Empty")
	@Email(message = "Not a valid Email Id")
	@Size(min = 6, message = "Wrong Email Id Entered")
	private String loginId;

	@NotEmpty(message = "Select a question for Security reason")
	private List<String> questionId;

	@NotEmpty(message = "Secret answer for the selected question cannot be Empty")
	private String secretQuesAnsId;

	@NotEmpty(message = "New password cannot be Empty")
	@Size(min = 10, message = "Password criteria not met")
	private String newPasswordId;

	@NotEmpty(message = "Re-enter New password cannot be Empty")
	@Size(min = 10, message = "Password criteria not met")
	private String reenterNewPasswordId;

	/**
	 * @return the loginId
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId
	 *            the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return the questionId
	 */
	public List<String> getQuestionId() {
		return questionId;
	}

	/**
	 * @param questionId
	 *            the questionId to set
	 */
	public void setQuestionId(List<String> questionId) {
		this.questionId = questionId;
	}

	/**
	 * @return the secretQuesAnsId
	 */
	public String getSecretQuesAnsId() {
		return secretQuesAnsId;
	}

	/**
	 * @param secretQuesAnsId
	 *            the secretQuesAnsId to set
	 */
	public void setSecretQuesAnsId(String secretQuesAnsId) {
		this.secretQuesAnsId = secretQuesAnsId;
	}

	/**
	 * @return the newPasswordId
	 */
	public String getNewPasswordId() {
		return newPasswordId;
	}

	/**
	 * @param newPasswordId
	 *            the newPasswordId to set
	 */
	public void setNewPasswordId(String newPasswordId) {
		this.newPasswordId = newPasswordId;
	}

	/**
	 * @return the reenterNewPasswordId
	 */
	public String getReenterNewPasswordId() {
		return reenterNewPasswordId;
	}

	/**
	 * @param reenterNewPasswordId
	 *            the reenterNewPasswordId to set
	 */
	public void setReenterNewPasswordId(String reenterNewPasswordId) {
		this.reenterNewPasswordId = reenterNewPasswordId;
	}

	public String toString() {
		return "\nloginId " + loginId + "\nquestionId " + questionId
				+ "\nsecretQuesAnsId " + secretQuesAnsId + "\n";

	}

}
