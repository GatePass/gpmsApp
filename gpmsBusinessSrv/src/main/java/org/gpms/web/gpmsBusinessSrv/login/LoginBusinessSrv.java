/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.login;

import org.gpms.web.domain.UsersRepository;
import org.gpms.web.entities.users.UsersLoginEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author narenda.kumar
 * 
 */
@Service
public class LoginBusinessSrv {

	@Autowired
	private UsersRepository usersRepository;

	/**
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean validateLoginCredentials(String userId, String password) {

		boolean isPasswordCorrect = false;

		UsersLoginEntity userLoginEntity = usersRepository
				.getUserByCorpEmailId(userId);

		if (userLoginEntity != null) {
			String userPassword = userLoginEntity.getPassword();
			if (password.equals(userPassword)) {
				isPasswordCorrect = true;
			}
		}

		return isPasswordCorrect;
	}

	public void resetPassword() {

	}

}
