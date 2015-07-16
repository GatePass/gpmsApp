/**
 * 
 */
package org.gpms.web.activitiUserMgmt;

import org.activiti.engine.identity.User;
import org.gpms.web.common.IdentityManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author narenda.kumar
 * 
 */
@Component
public class ActivitiUserMgmt {

	@Autowired
	IdentityManagement identityManagement;

	/**
	 * 
	 * @param userId
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 */
	public void createUser(String userId, String firstName, String lastName,
			String email, String password) {
		identityManagement.createUser(userId, firstName, lastName, email,
				password);
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public User getUser(String userId) {
		User user = identityManagement.getUser(userId);
		return user;
	}

	/**
	 * 
	 * @param userId
	 */
	public void deleteUser(String userId) {
		identityManagement.deleteUser(userId);
	}

	/**
	 * 
	 * @param userId
	 * @param groupId
	 */
	public void assignUserToGroup(String userId, String groupId) {
		identityManagement.assignUserToGroup(userId, groupId);
	}

	/**
	 * 
	 * @param userId
	 * @param groupId
	 */
	public void removeUserFromGroup(String userId, String groupId) {
		identityManagement.removeUserFromGroup(userId, groupId);
	}

}
