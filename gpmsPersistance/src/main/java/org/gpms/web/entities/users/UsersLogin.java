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
public class UsersLogin {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "USER_CORP_EMAIL")
	private String userCorpEmail;

	@Column(name = "USER_PERSONAL_EMAIL")
	private String userPersonnalEmail;

	@Column(name = "USER_PASSWORD")
	private String password;

	@Column(name = "USER_LAST_LOGIN")
	private Date userLastLogin;

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

}
