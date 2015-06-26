/**
 * 
 */
package org.gpms.web.domain;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.gpms.web.entities.users.UsersLogin;
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

	public void createUser() {

		UsersLogin usersLogin = new UsersLogin();
		usersLogin.setUserCorpEmail("aSDASD");
		usersLogin.setUserPersonnalEmail("asdfsd");
		usersLogin.setPassword("asdfasdf");
		usersLogin.setUserLastLogin(new Date());

		entityManager.persist(usersLogin);

	}

}
