/**
 * 
 */
package org.gpms.web.entities.users;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author narenda.kumar
 * 
 */

@Entity(name = "GPMS_USER_LOGIN")
public class UsersLoginEntity {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "USER_FIRST_NAME")
	private String userFirstName;

	@Column(name = "USER_LAST_NAME")
	private String userLastName;

	@Column(name = "USER_CORP_EMAIL")
	private String userCorpEmail;

	@Column(name = "USER_PERSONAL_EMAIL")
	private String userPersonnalEmail;

	@Column(name = "USER_GROUP_ID")
	private String userGroupId;

	@Column(name = "USER_PASSWORD")
	private String password;

	@Column(name = "USER_LAST_LOGIN")
	private Date userLastLogin;

	@Column(name = "USER_SECURITY_QUESTION")
	private String userSecurityQuestion;

	@Column(name = "USER_SECURITY_ANSWER")
	private String userSecurityAnswer;

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
	 * @return the userSecurityQuestion
	 */
	public String getUserSecurityQuestion() {
		return userSecurityQuestion;
	}

	/**
	 * @param userSecurityQuestion
	 *            the userSecurityQuestion to set
	 */
	public void setUserSecurityQuestion(String userSecurityQuestion) {
		this.userSecurityQuestion = userSecurityQuestion;
	}

	/**
	 * @return the userSecurityAnswer
	 */
	public String getUserSecurityAnswer() {
		return userSecurityAnswer;
	}

	/**
	 * @param userSecurityAnswer
	 *            the userSecurityAnswer to set
	 */
	public void setUserSecurityAnswer(String userSecurityAnswer) {
		this.userSecurityAnswer = userSecurityAnswer;
	}

	public String toString() {
		return "userId" + userId + "\nuserFirstName" + userFirstName
				+ "\nuserLastName" + userLastName + "\nuserCorpEmail"
				+ userCorpEmail + "\nuserPersonnalEmail" + userPersonnalEmail
				+ "\nuserGroupId" + userGroupId + "\npassword" + password
				+ "\nuserLastLogin" + userLastLogin + "\nuserSecurityQuestion "
				+ userSecurityQuestion + "\nuserSecurityAnswer "
				+ userSecurityAnswer + "\n";
	}

}
