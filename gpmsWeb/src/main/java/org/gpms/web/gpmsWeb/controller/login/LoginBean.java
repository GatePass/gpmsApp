/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.login;

import java.io.Serializable;

/**
 * @author narenda.kumar
 * 
 */
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -356597310566721839L;
	// spring validation
	private String userId;
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
