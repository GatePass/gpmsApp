/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.userMgmt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.gpms.web.activitiUserMgmt.ActivitiUserMgmt;
import org.gpms.web.domain.UsersRepository;
import org.gpms.web.entities.users.SecurityQuestionEntity;
import org.gpms.web.entities.users.UserGroupsEntity;
import org.gpms.web.entities.users.UsersLoginEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author narenda.kumar
 * 
 */
@Service
public class UserMgmtBusinessSrv {

	private static final Logger logger = Logger
			.getLogger(UserMgmtBusinessSrv.class);

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	ActivitiUserMgmt activitiUserMgmt;

	/**
	 * 
	 * @param userModel
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public UserModel createUser(UserModel userModel) {

		if (logger.isDebugEnabled()) {
			logger.debug("ENTERING");
		}

		String userId = null;

		if (userModel != null) {
			UsersLoginEntity usersLoginEntity = copyModelToEntity(userModel);
			userId = usersRepository.createUser(usersLoginEntity);
		}

		if (userId != null && userModel != null) {
			userModel.setUserId(userId);
			activitiUserMgmt.createUser(userModel.getUserCorpEmail(),
					userModel.getUserFirstName(), userModel.getUserLastName(),
					userModel.getUserPersonnalEmail(), userModel.getPassword());
			activitiUserMgmt.assignUserToGroup(userModel.getUserCorpEmail(),
					userModel.getUserGroupId());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("EXITING");
		}

		return userModel;
	}

	/**
	 * 
	 * @param userModel
	 * @return
	 */
	public UserModel modifyUser(UserModel userModel) {

		if (logger.isDebugEnabled()) {
			logger.debug("ENTERING");
		}

		String userId = null;

		if (userModel != null) {
			UsersLoginEntity usersLoginEntity = copyModelToEntity(userModel);
			userId = usersRepository.modifyUser(usersLoginEntity);
		}

		if (userId != null && userModel != null) {
			userModel.setUserId(userId);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("EXITING");
		}

		return userModel;
	}

	/**
	 * 
	 * @param corpEmail
	 * @return
	 */
	public UserModel getUserByCorpEmail(String corpEmail) {

		if (logger.isDebugEnabled()) {
			logger.debug("ENTERING");
		}

		UsersLoginEntity usersLoginEntity = null;

		if (corpEmail != null) {
			usersLoginEntity = usersRepository.getUserByCorpEmailId(corpEmail);
		}

		UserModel userModel = null;

		if (usersLoginEntity != null) {
			userModel = copyEntityToModel(usersLoginEntity);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("EXITING");
		}

		return userModel;
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public UserModel getUserById(String userId) {

		if (logger.isDebugEnabled()) {
			logger.debug("ENTERING");
		}

		UsersLoginEntity usersLoginEntity = null;

		if (userId != null) {
			usersLoginEntity = usersRepository.getUserById(userId);
		}

		UserModel userModel = null;

		if (usersLoginEntity != null) {
			userModel = copyEntityToModel(usersLoginEntity);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("EXITING");
		}

		return userModel;
	}

	/**
	 * 
	 * @param userId
	 * @param groupId
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public void deleteUserById(String userId, String groupId) {

		if (logger.isDebugEnabled()) {
			logger.debug("ENTERING");
		}

		if (userId != null && groupId != null) {
			UsersLoginEntity usersLoginEntity = usersRepository
					.getUserById(userId);
			if (usersLoginEntity != null) {
				usersRepository.deleteUserById(usersLoginEntity.getUserId());
				activitiUserMgmt
						.deleteUser(usersLoginEntity.getUserCorpEmail());
				activitiUserMgmt.removeUserFromGroup(
						usersLoginEntity.getUserCorpEmail(),
						usersLoginEntity.getUserGroupId());
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("EXITING");
		}
	}

	/**
	 * 
	 * @param corpEmail
	 * @param groupId
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public void deleteUserByCorpEmailId(String corpEmail, String groupId) {

		if (logger.isDebugEnabled()) {
			logger.debug("ENTERING");
		}

		if (corpEmail != null && groupId != null) {
			usersRepository.deleteUserByCorpEmailId(corpEmail);
			activitiUserMgmt.deleteUser(corpEmail);
			activitiUserMgmt.removeUserFromGroup(corpEmail, groupId);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("EXITING");
		}
	}

	/**
	 * 
	 * @param userModel
	 * @return
	 */
	private UsersLoginEntity copyModelToEntity(UserModel userModel) {

		UsersLoginEntity usersLoginEntity = new UsersLoginEntity();
		usersLoginEntity.setUserId(userModel.getUserId());
		usersLoginEntity.setUserFirstName(userModel.getUserFirstName());
		usersLoginEntity.setUserLastName(userModel.getUserLastName());
		usersLoginEntity.setUserCorpEmail(userModel.getUserCorpEmail());
		usersLoginEntity.setUserPersonnalEmail(userModel
				.getUserPersonnalEmail());
		usersLoginEntity.setUserGroupId(userModel.getUserGroupId());
		usersLoginEntity.setPassword(userModel.getPassword());
		usersLoginEntity.setUserLastLogin(userModel.getUserLastLogin());
		usersLoginEntity.setUserSecurityQuestion(userModel.getQuestionId());
		usersLoginEntity.setUserSecurityAnswer(userModel.getSecretQuesAnsId());

		return usersLoginEntity;

	}

	/**
	 * 
	 * @param usersLoginEntity
	 * @return
	 */
	private UserModel copyEntityToModel(UsersLoginEntity usersLoginEntity) {

		UserModel userModel = new UserModel();
		userModel.setUserId(usersLoginEntity.getUserId());
		userModel.setUserFirstName(usersLoginEntity.getUserFirstName());
		userModel.setUserLastName(usersLoginEntity.getUserLastName());
		userModel.setUserCorpEmail(usersLoginEntity.getUserCorpEmail());
		userModel.setUserPersonnalEmail(usersLoginEntity
				.getUserPersonnalEmail());
		userModel.setUserGroupId(usersLoginEntity.getUserGroupId());
		userModel.setPassword(usersLoginEntity.getPassword());
		userModel.setUserLastLogin(usersLoginEntity.getUserLastLogin());
		userModel.setQuestionId(usersLoginEntity.getUserSecurityQuestion());
		userModel.setSecretQuesAnsId(usersLoginEntity.getUserSecurityAnswer());

		return userModel;

	}

	// ************** getting static data

	public List<SecurityQuestionsModel> getAllSecurityQuestions() {

		if (logger.isDebugEnabled()) {
			logger.debug("ENTERING");
		}

		List<SecurityQuestionEntity> securityQuestionList = usersRepository
				.getAllSecurityQuestionEntity();

		List<SecurityQuestionsModel> securityQuestionsModelList = new ArrayList<SecurityQuestionsModel>();

		Iterator<SecurityQuestionEntity> securityQuestionEntityIter = securityQuestionList
				.iterator();
		while (securityQuestionEntityIter.hasNext()) {
			SecurityQuestionEntity singleSecurityQuestionEntity = securityQuestionEntityIter
					.next();
			SecurityQuestionsModel securityQuestionsModel = new SecurityQuestionsModel();
			securityQuestionsModel
					.setSecurityQuestionId(singleSecurityQuestionEntity
							.getSecurityQuestionId());
			securityQuestionsModel
					.setSecurityQuestion(singleSecurityQuestionEntity
							.getSecurityQuestion());
			securityQuestionsModelList.add(securityQuestionsModel);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("EXITING");
		}

		return securityQuestionsModelList;
	}

	public List<UserGroupModel> getUserGroup() {

		if (logger.isDebugEnabled()) {
			logger.debug("ENTERING");
		}

		List<UserGroupsEntity> userGroupsEntityList = usersRepository
				.getAllUserGroupsEntity();

		List<UserGroupModel> userGroupModelList = new ArrayList<UserGroupModel>();

		Iterator<UserGroupsEntity> userGroupsEntityListIter = userGroupsEntityList
				.iterator();
		while (userGroupsEntityListIter.hasNext()) {
			UserGroupsEntity userGroupsEntityEntity = userGroupsEntityListIter
					.next();
			UserGroupModel userGroupsModel = new UserGroupModel();
			userGroupsModel.setUserGroupId(userGroupsEntityEntity
					.getUserGroupId());
			userGroupsModel.setUserGroupName(userGroupsEntityEntity
					.getUserGroupName());
			userGroupsModel.setUserGroupDesc(userGroupsEntityEntity
					.getUserGroupDesc());

			userGroupModelList.add(userGroupsModel);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("EXITING");
		}

		return userGroupModelList;
	}

}
