/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.userMgmt;

import java.io.Serializable;
import java.util.Date;

/**
 * @author narenda.kumar
 * 
 */
public class UserModel implements Serializable {

	private String userId;

	private String userFirstName;

	private String userLastName;

	private String userCorpEmail;

	private String userPersonnalEmail;

	private String userGroupId;

	private String password;

	private Date userLastLogin;

	private String questionId;

	private String secretQuesAnsId;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userFirstName
	 */
	public String getUserFirstName() {
		return userFirstName;
	}

	/**
	 * @param userFirstName
	 *            the userFirstName to set
	 */
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	/**
	 * @return the userLastName
	 */
	public String getUserLastName() {
		return userLastName;
	}

	/**
	 * @param userLastName
	 *            the userLastName to set
	 */
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	/**
	 * @return the userCorpEmail
	 */
	public String getUserCorpEmail() {
		return userCorpEmail;
	}

	/**
	 * @param userCorpEmail
	 *            the userCorpEmail to set
	 */
	public void setUserCorpEmail(String userCorpEmail) {
		this.userCorpEmail = userCorpEmail;
	}

	/**
	 * @return the userPersonnalEmail
	 */
	public String getUserPersonnalEmail() {
		return userPersonnalEmail;
	}

	/**
	 * @param userPersonnalEmail
	 *            the userPersonnalEmail to set
	 */
	public void setUserPersonnalEmail(String userPersonnalEmail) {
		this.userPersonnalEmail = userPersonnalEmail;
	}

	/**
	 * @return the userGroupId
	 */
	public String getUserGroupId() {
		return userGroupId;
	}

	/**
	 * @param userGroupId
	 *            the userGroupId to set
	 */
	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the userLastLogin
	 */
	public Date getUserLastLogin() {
		return userLastLogin;
	}

	/**
	 * @param userLastLogin
	 *            the userLastLogin to set
	 */
	public void setUserLastLogin(Date userLastLogin) {
		this.userLastLogin = userLastLogin;
	}

	/**
	 * @return the questionId
	 */
	public String getQuestionId() {
		return questionId;
	}

	/**
	 * @param questionId
	 *            the questionId to set
	 */
	public void setQuestionId(String questionId) {
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

	public String toString() {
		return "userId" + userId + "\nuserFirstName" + userFirstName
				+ "\nuserLastName" + userLastName + "\nuserCorpEmail"
				+ userCorpEmail + "\nuserPersonnalEmail" + userPersonnalEmail
				+ "\nuserGroupId" + userGroupId + "\npassword" + password
				+ "\nuserLastLogin" + userLastLogin + "\n";
	}

}
