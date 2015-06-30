/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.login;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author narenda.kumar
 * 
 */

public class LoginBean implements Serializable {

	private static final long serialVersionUID = -356597310566721839L;
	// spring validation

	@NotEmpty(message = "User Id cannot be Empty")
	@Email(message = "Not a valid User Id")
	@Size(min = 6, message = "Wrong UserName Entered")
	private String userId;

	@NotEmpty(message = "Password cannot be Empty")
	@Size(min = 6, max = 20, message = "Wrong Password Entered")
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
