/**
 * 
 */
package org.gpms.web.common;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author narenda.kumar
 * 
 */
@Component
public class IdentityManagement {

	@Autowired(required = true)
	public RuntimeService runtimeService;

	@Autowired
	public RepositoryService repositoryService;

	@Autowired
	public IdentityService identityService;

	@Autowired
	public TaskService taskService;

	/**
	 * 
	 * @param groupId
	 * @param groupName
	 * @param groupType
	 */
	public void createGroup(String groupId, String groupName, String groupType) {

		Group group = identityService.newGroup(groupId);
		group.setName(groupName);
		group.setType(groupType);
		identityService.saveGroup(group);

	}

	/**
	 * 
	 * @param groupId
	 * @return
	 */
	public Group getGroup(String groupId) {
		GroupQuery groupQuery = identityService.createGroupQuery().groupId(
				groupId);
		Group group = groupQuery.singleResult();
		return group;
	}

	/**
	 * 
	 * @param groupId
	 */
	public void deleteGroup(String groupId) {
		identityService.deleteGroup(groupId);
	}

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

		User user = identityService.newUser(userId);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);
		identityService.saveUser(user);

	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public User getUser(String userId) {
		UserQuery userQuery = identityService.createUserQuery().userId(userId);
		User user = userQuery.singleResult();
		return user;

	}

	/**
	 * 
	 * @param userId
	 */
	public void deleteUser(String userId) {
		identityService.deleteUser(userId);
	}

	/**
	 * 
	 * @param userId
	 * @param groupId
	 */
	public void assignUserToGroup(String userId, String groupId) {
		identityService.createMembership(userId, groupId);
	}

	/**
	 * 
	 * @param userId
	 * @param groupId
	 */
	public void removeUserFromGroup(String userId, String groupId) {
		identityService.deleteMembership(userId, groupId);
	}

	/**
	 * 
	 * @param authenticatedUserId
	 */
	public void setUserForProcessStarter(String authenticatedUserId) {
		identityService.setAuthenticatedUserId(authenticatedUserId);
	}

}
