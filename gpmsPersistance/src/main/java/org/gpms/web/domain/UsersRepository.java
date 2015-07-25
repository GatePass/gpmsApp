/**
 * 
 */
package org.gpms.web.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.gpms.web.entities.users.SecurityQuestionEntity;
import org.gpms.web.entities.users.UserGroupsEntity;
import org.gpms.web.entities.users.UsersLoginEntity;
import org.springframework.stereotype.Repository;

/**
 * @author narenda.kumar
 * 
 */
@Repository
@Transactional
public class UsersRepository {

	@PersistenceContext(unitName = "gpmsPersistenceUnit")
	EntityManager entityManager;

	/**
	 * 
	 * @param usersLoginEntity
	 * @return
	 * @throws PersistenceException
	 */
	public String createUser(UsersLoginEntity usersLoginEntity)
			throws PersistenceException {
		entityManager.persist(usersLoginEntity);
		String userIdCreated = usersLoginEntity.getUserId();
		return userIdCreated;
	}

	/**
	 * 
	 * @param usersLoginEntity
	 * @return
	 * @throws PersistenceException
	 */
	public String modifyUser(UsersLoginEntity usersLoginEntity)
			throws PersistenceException {
		entityManager.merge(usersLoginEntity);
		String userIdCreated = usersLoginEntity.getUserId();
		return userIdCreated;
	}

	/**
	 * 
	 * @param usersLoginEntity
	 * @throws PersistenceException
	 */
	public void deleteUser(UsersLoginEntity usersLoginEntity)
			throws PersistenceException {
		entityManager.remove(usersLoginEntity);
	}

	/**
	 * 
	 * @param userId
	 * @throws PersistenceException
	 */
	public void deleteUserById(String userId) throws PersistenceException {
		UsersLoginEntity usersLoginEntity = getUserById(userId);
		deleteUser(usersLoginEntity);
	}

	/**
	 * 
	 * @param userEmailId
	 * @throws PersistenceException
	 */
	public void deleteUserByCorpEmailId(String userEmailId)
			throws PersistenceException {
		UsersLoginEntity usersLoginEntity = getUserByCorpEmailId(userEmailId);
		deleteUser(usersLoginEntity);
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public UsersLoginEntity getUserById(String userId) {

		UsersLoginEntity usersLoginEntity = entityManager.find(
				UsersLoginEntity.class, userId);

		return usersLoginEntity;

	}

	/**
	 * 
	 * @param userEmailId
	 * @return
	 */
	public UsersLoginEntity getUserByCorpEmailId(String userEmailId) {

		UsersLoginEntity usersLoginEntity = (UsersLoginEntity) entityManager
				.createQuery(
						"SELECT userLogin FROM GPMS_USER_LOGIN userLogin "
								+ "where userLogin.userCorpEmail = :userEmailId")
				.setParameter("userEmailId", userEmailId).getSingleResult();

		return usersLoginEntity;
	}

	/**
	 * 
	 * @param usersLoginEntity
	 * @return
	 * @throws PersistenceException
	 */
	public UsersLoginEntity updatePassword(UsersLoginEntity usersLoginEntity)
			throws PersistenceException {

		UsersLoginEntity usersLoginEntityUpdated = entityManager
				.merge(usersLoginEntity);

		return usersLoginEntityUpdated;
	}

	/**
	 * 
	 * @return
	 */
	public List<SecurityQuestionEntity> getAllSecurityQuestionEntity() {

		List<SecurityQuestionEntity> securityQuestionEntityLst = (List<SecurityQuestionEntity>) entityManager
				.createQuery(
						"SELECT securityQuestion FROM GPMS_SECURITY_QUESTIONS securityQuestion")
				.getResultList();

		return securityQuestionEntityLst;

	}

	/**
	 * 
	 * @return
	 */
	public List<UserGroupsEntity> getAllUserGroupsEntity() {

		List<UserGroupsEntity> userGroupsEntityLst = (List<UserGroupsEntity>) entityManager
				.createQuery("SELECT userGroup FROM GPMS_USER_GROUPS userGroup")
				.getResultList();

		return userGroupsEntityLst;

	}

}
