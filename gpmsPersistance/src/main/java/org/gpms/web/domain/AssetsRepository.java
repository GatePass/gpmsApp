/**
 * 
 */
package org.gpms.web.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.gpms.web.entities.assets.AssetTypesEntity;
import org.gpms.web.entities.assets.AssetsEntity;
import org.springframework.stereotype.Repository;

/**
 * @author narenda.kumar
 * 
 */
@Repository
@Transactional
public class AssetsRepository {

	private static final Logger logger = Logger
			.getLogger(AssetsRepository.class);

	@PersistenceContext(unitName = "gpmsPersistenceUnit")
	EntityManager entityManager;

	public String createAsset(AssetsEntity assetsEntity)
			throws PersistenceException {
		entityManager.persist(assetsEntity);
		String assetIdCreated = assetsEntity.getAssetId();
		return assetIdCreated;
	}

	public String modifyAsset(AssetsEntity assetsEntity)
			throws PersistenceException {
		entityManager.merge(assetsEntity);
		String assetIdCreated = assetsEntity.getAssetId();
		return assetIdCreated;
	}

	public AssetsEntity getAssetById(String assetId) {
		AssetsEntity assetsEntity = entityManager.find(AssetsEntity.class,
				assetId);
		return assetsEntity;
	}

	public void deleteAsset(AssetsEntity assetsEntity)
			throws PersistenceException {
		entityManager.remove(assetsEntity);
	}

	public void deleteAssetById(String assetId) throws PersistenceException {
		AssetsEntity assetsEntity = getAssetById(assetId);
		entityManager.remove(assetsEntity);
	}

	public String createAssetType(AssetTypesEntity assetTypesEntity)
			throws PersistenceException {
		entityManager.persist(assetTypesEntity);
		String assetTypeIdCreated = assetTypesEntity.getAssetTypeId();
		return assetTypeIdCreated;
	}

	public AssetTypesEntity getAssetTypeById(String assetId) {

		AssetTypesEntity assetTypesEntity = entityManager.find(
				AssetTypesEntity.class, assetId);

		return assetTypesEntity;

	}

	public void deleteAssetType(AssetTypesEntity assetTypesEntity)
			throws PersistenceException {
		entityManager.remove(assetTypesEntity);
	}

	public List<AssetTypesEntity> getAllAssetTypesEntity() {

		List<AssetTypesEntity> assetTypesEntityLst = (List<AssetTypesEntity>) entityManager
				.createQuery(
						"SELECT assetTypes FROM GPMS_ASSET_TYPES assetTypes")
				.getResultList();

		return assetTypesEntityLst;

	}

}
