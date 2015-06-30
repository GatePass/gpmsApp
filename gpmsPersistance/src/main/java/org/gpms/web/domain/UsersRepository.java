/**
 * 
 */
package org.gpms.web.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.gpms.web.entities.users.SecurityQuestionEntity;
import org.gpms.web.entities.users.UsersLoginEntity;
import org.springframework.stereotype.Repository;

/**
 * @author narenda.kumar
 * 
 */
@Repository
@Transactional
public class UsersRepository {

	@PersistenceContext
	EntityManager entityManager;

	public void createUser(UsersLoginEntity usersLoginEntity) {

		UsersLoginEntity usersLogin = new UsersLoginEntity();
		usersLogin.setUserCorpEmail("aSDASD");
		usersLogin.setUserPersonnalEmail("asdfsd");
		usersLogin.setPassword("asdfasdf");
		usersLogin.setUserLastLogin(new Date());

		entityManager.persist(usersLogin);

	}

	public void deleteUser(UsersLoginEntity usersLoginEntity) {
		entityManager.remove(usersLoginEntity);
	}

	public void deleteUserById(String userEmailId) {
		UsersLoginEntity usersLoginEntity = getUserById(userEmailId);
		deleteUser(usersLoginEntity);
	}

	public void deleteUserByCorpEmailId(String userEmailId) {
		UsersLoginEntity usersLoginEntity = getUserByCorpEmailId(userEmailId);
		deleteUser(usersLoginEntity);
	}

	public UsersLoginEntity getUserById(String userEmailId) {

		UsersLoginEntity usersLoginEntity = entityManager.find(
				UsersLoginEntity.class, userEmailId);

		return usersLoginEntity;

	}

	public UsersLoginEntity getUserByCorpEmailId(String userEmailId) {

		UsersLoginEntity usersLoginEntity = (UsersLoginEntity) entityManager
				.createQuery(
						"SELECT userLogin FROM GPMS_USER_LOGIN userLogin "
								+ "where userLogin.userCorpEmail = :userEmailId")
				.setParameter("userEmailId", userEmailId).getSingleResult();

		return usersLoginEntity;
	}

	public UsersLoginEntity updatePassword(UsersLoginEntity usersLoginEntity) {

		UsersLoginEntity usersLoginEntityUpdated = entityManager
				.merge(usersLoginEntity);

		return usersLoginEntityUpdated;
	}

	public List<SecurityQuestionEntity> getAllSecurityQuestionEntity() {

		List<SecurityQuestionEntity> securityQuestionEntityLst = (List<SecurityQuestionEntity>) entityManager
				.createQuery(
						"SELECT securityQuestion FROM GPMS_SECURITY_QUESTIONS securityQuestion")
				.getResultList();

		System.out.println("securityQuestionEntityLst "
				+ securityQuestionEntityLst.size());

		return securityQuestionEntityLst;

	}

}
