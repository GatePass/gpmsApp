/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.userMgmt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.gpms.web.domain.UsersRepository;
import org.gpms.web.entities.users.SecurityQuestionEntity;
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
}
