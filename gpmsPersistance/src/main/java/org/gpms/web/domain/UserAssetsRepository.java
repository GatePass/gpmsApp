/**
 * 
 */
package org.gpms.web.domain;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.gpms.web.entities.assets.UserAssetEntity;
import org.springframework.stereotype.Repository;

/**
 * @author narenda.kumar
 * 
 */
@Repository
@Transactional
public class UserAssetsRepository {

	@PersistenceContext
	EntityManager entityManager;

	public String addAssetToUser(UserAssetEntity userAssetEntity) {
		entityManager.persist(userAssetEntity);
		String userAssetIdCreated = userAssetEntity.getUserAssetId();
		return userAssetIdCreated;
	}

	public String updateAssetInfoOfUser(UserAssetEntity userAssetEntity) {
		entityManager.merge(userAssetEntity);
		String userAssetIdCreated = userAssetEntity.getUserAssetId();
		return userAssetIdCreated;
	}

	public void revomeAssetFromUser() {

	}

}
