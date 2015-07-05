/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.userMgmt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.gpms.web.activitiUserMgmt.ActivitiUserMgmt;
import org.gpms.web.domain.UsersRepository;
import org.gpms.web.entities.users.SecurityQuestionEntity;
import org.gpms.web.entities.users.UserGroupsEntity;
import org.gpms.web.entities.users.UsersLoginEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author narenda.kumar
 * 
 */
@Service
public class UserMgmtBusinessSrv {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	ActivitiUserMgmt activitiUserMgmt;

	public List<SecurityQuestionsModel> getAllSecurityQuestions() {
		List<SecurityQuestionEntity> securityQuestionList = usersRepository
				.getAllSecurityQuestionEntity();

		List<SecurityQuestionsModel> securityQuestionsModelList = new ArrayList();

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

		return securityQuestionsModelList;
	}

	public List<UserGroupModel> getUserGroup() {

		List<UserGroupsEntity> userGroupsEntityList = usersRepository
				.getAllUserGroupsEntity();

		List<UserGroupModel> userGroupModelList = new ArrayList();

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

		return userGroupModelList;
	}

	public UserModel createUser(UserModel userModel) {

		UsersLoginEntity usersLoginEntity = new UsersLoginEntity();
		usersLoginEntity.setUserFirstName(userModel.getUserFirstName());
		usersLoginEntity.setUserLastName(userModel.getUserLastName());
		usersLoginEntity.setUserCorpEmail(userModel.getUserCorpEmail());
		usersLoginEntity.setUserPersonnalEmail(userModel
				.getUserPersonnalEmail());
		usersLoginEntity.setUserGroupId(userModel.getUserGroupId());
		usersLoginEntity.setPassword("Narendra");

		String userId = usersRepository.createUser(usersLoginEntity);

		if (userId != null) {
			userModel.setUserId(userId);
		}

		activitiUserMgmt.createUser(userModel.getUserCorpEmail(),
				userModel.getUserFirstName(), userModel.getUserLastName(),
				userModel.getUserPersonnalEmail(), userModel.getPassword());
		activitiUserMgmt.assignUserToGroup(userModel.getUserCorpEmail(),
				userModel.getUserGroupId());

		return userModel;
	}
}
