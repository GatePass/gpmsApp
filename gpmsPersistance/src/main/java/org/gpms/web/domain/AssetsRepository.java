/**
 * 
 */
package org.gpms.web.domain;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

/**
 * @author narenda.kumar
 * 
 */
@Repository
@Transactional
public class AssetsRepository {

	@PersistenceContext
	EntityManager entityManager;

}
