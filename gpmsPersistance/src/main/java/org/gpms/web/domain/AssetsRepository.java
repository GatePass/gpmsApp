/**
 * 
 */
package org.gpms.web.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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

	@PersistenceContext(unitName = "gpmsPersistenceUnit")
	EntityManager entityManager;

	public String createAsset(AssetsEntity assetsEntity) {
		entityManager.persist(assetsEntity);
		String assetIdCreated = assetsEntity.getAssetId();
		return assetIdCreated;
	}

	public AssetsEntity getAssetById(String assetId) {

		AssetsEntity assetsEntity = entityManager.find(AssetsEntity.class,
				assetId);

		return assetsEntity;

	}

	public void deleteAsset(AssetsEntity assetsEntity) {
		entityManager.remove(assetsEntity);
	}

	public String createAssetType(AssetTypesEntity assetTypesEntity) {
		entityManager.persist(assetTypesEntity);
		String assetTypeIdCreated = assetTypesEntity.getAssetTypeId();
		return assetTypeIdCreated;
	}

	public AssetTypesEntity getAssetTypeById(String assetId) {

		AssetTypesEntity assetTypesEntity = entityManager.find(
				AssetTypesEntity.class, assetId);

		return assetTypesEntity;

	}

	public void deleteAssetType(AssetTypesEntity assetTypesEntity) {
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
