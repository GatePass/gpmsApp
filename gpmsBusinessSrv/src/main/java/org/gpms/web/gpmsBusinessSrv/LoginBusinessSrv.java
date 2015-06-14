/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv;

import org.gpms.web.domain.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author narenda.kumar
 * 
 */
@Service
public class LoginBusinessSrv {

	@Autowired
	private Login login;

	public void getLogin() {
		System.out.println("Login in Business Service: ");
		login.getUsers();
	}

}
