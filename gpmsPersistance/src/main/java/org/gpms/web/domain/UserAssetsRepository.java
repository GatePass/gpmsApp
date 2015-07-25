/**
 * 
 */
package org.gpms.web.domain;

import java.util.List;

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

	/**
	 * 
	 * @param userAssetEntity
	 * @return
	 */
	public String addAssetToUser(UserAssetEntity userAssetEntity) {
		entityManager.persist(userAssetEntity);
		String userAssetIdCreated = userAssetEntity.getUserAssetId();
		return userAssetIdCreated;
	}

	/**
	 * 
	 * @param userAssetEntity
	 * @return
	 */
	public String updateAssetInfoOfUser(UserAssetEntity userAssetEntity) {
		entityManager.merge(userAssetEntity);
		String userAssetIdCreated = userAssetEntity.getUserAssetId();
		return userAssetIdCreated;
	}

	/**
	 * 
	 * @param userAssetId
	 * @return
	 */
	public UserAssetEntity getUserAssetById(String userAssetId) {

		UserAssetEntity userAssetEntity = entityManager.find(
				UserAssetEntity.class, userAssetId);
		return userAssetEntity;
	}

	/**
	 * 
	 * @param corpEmailId
	 * @return
	 */
	public List<UserAssetEntity> getUserAssetByCorpEmail(String corpEmailId) {

		List<UserAssetEntity> userAssetEntityLst = (List<UserAssetEntity>) entityManager
				.createQuery(
						"SELECT userAssets FROM GPMS_USER_ASSET userAssets "
								+ "where userAssets.userCorpEmail = :userEmailId")
				.setParameter("userEmailId", corpEmailId).getResultList();
		return userAssetEntityLst;
	}

}
