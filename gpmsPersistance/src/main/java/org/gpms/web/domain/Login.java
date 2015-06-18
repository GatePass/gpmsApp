/**
 * 
 */
package org.gpms.web.domain;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

/**
 * @author narenda.kumar
 * 
 */
@Repository
public class Login {

	@PersistenceContext
	EntityManager entityManager;

	public void getUsers() {
		System.out.println("entityManager : " + entityManager);

	}

	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager
	 *            the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
