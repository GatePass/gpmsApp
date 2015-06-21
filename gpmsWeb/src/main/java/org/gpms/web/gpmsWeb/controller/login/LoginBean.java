/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.login;

import java.io.Serializable;

import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.Email;

/**
 * @author narenda.kumar
 * 
 */
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -356597310566721839L;
	// spring validation
	@Null(message = "User Id cannot be Empty")
	@Email(message = "Not a valid User Id")
	private String userId;

	@Null(message = "Password cannot be Empty")
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
