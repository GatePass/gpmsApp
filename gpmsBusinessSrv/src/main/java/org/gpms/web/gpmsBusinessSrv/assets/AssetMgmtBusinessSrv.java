/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.assets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.gpms.web.domain.AssetsRepository;
import org.gpms.web.entities.assets.AssetTypesEntity;
import org.gpms.web.entities.assets.AssetsEntity;
import org.gpms.web.gpmsBusinessSrv.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author narenda.kumar
 * 
 */

@Service
public class AssetMgmtBusinessSrv {

	private static final Logger logger = Logger
			.getLogger(AssetMgmtBusinessSrv.class);

	@Autowired
	private AssetsRepository assetsRepository;

	public AssetModel createAsset(AssetModel assetModel) {

		String assetId = null;

		if (assetModel != null) {
			AssetsEntity assetsEntity = copyModelToEntity(assetModel);
			assetId = assetsRepository.createAsset(assetsEntity);
		}

		if (assetId != null) {
			assetModel.setAssetId(assetId);
		}

		return assetModel;
	}

	public AssetModel modifyAsset(AssetModel assetModel) {

		String assetId = null;

		if (assetModel != null) {
			AssetsEntity assetsEntity = copyModelToEntity(assetModel);
			assetId = assetsRepository.modifyAsset(assetsEntity);
		}

		if (assetId != null) {
			assetModel.setAssetId(assetId);
		}

		return assetModel;
	}

	public AssetModel getAssetById(String assetId) {

		AssetsEntity assetsEntity = null;

		if (assetId != null) {
			assetsEntity = assetsRepository.getAssetById(assetId);
		}

		AssetModel assetModel = null;

		if (assetsEntity != null) {
			assetModel = copyEntityToModel(assetsEntity);
		}

		return assetModel;
	}

	public void deleteAssetById(String assetId) {
		assetsRepository.deleteAssetById(assetId);
	}

	private AssetsEntity copyModelToEntity(AssetModel assetModel) {
		AssetsEntity assetsEntity = new AssetsEntity();

		assetsEntity.setAssetId(assetModel.getAssetId());
		assetsEntity.setAssetBarCode(assetModel.getAssetBarCode());
		assetsEntity.setAssetType(assetModel.getAssetTypeId());
		assetsEntity.setAssetPurchaseDate(DateUtil.getSQLDate(assetModel
				.getAssetPurchaseDate()));
		assetsEntity.setAssetRemovalDate(DateUtil.getSQLDate(assetModel
				.getAssetRemovalDate()));
		assetsEntity.setAssetStatus(assetModel.getAssetStatus());

		return assetsEntity;
	}

	private AssetModel copyEntityToModel(AssetsEntity assetsEntity) {
		AssetModel assetModel = new AssetModel();

		assetModel.setAssetId(assetsEntity.getAssetId());
		assetModel.setAssetBarCode(assetsEntity.getAssetBarCode());
		assetModel.setAssetTypeId(assetsEntity.getAssetType());
		assetModel.setAssetPurchaseDate(assetsEntity.getAssetPurchaseDate()
				.toString());
		if (assetsEntity.getAssetRemovalDate() != null) {
			assetModel.setAssetRemovalDate(assetsEntity.getAssetRemovalDate()
					.toString());
		}
		assetModel.setAssetStatus(assetsEntity.getAssetStatus());

		return assetModel;
	}

	// **************************AssetType*********************************************

	public AssetTypeModel createAssetType(AssetTypeModel assetTypeModel) {

		AssetTypesEntity assetTypesEntity = new AssetTypesEntity();

		assetTypesEntity.setAssetTypeName(assetTypeModel.getAssetTypeName());
		assetTypesEntity.setAssetTypeDesc(assetTypeModel.getAssetTypeDesc());
		String assetTypeId = assetsRepository.createAssetType(assetTypesEntity);

		if (assetTypeId != null) {
			assetTypeModel.setAssetTypeId(assetTypeId);
		}

		return assetTypeModel;
	}

	public List<AssetTypeModel> getAllAssetType() {

		logger.debug("getAllAssetType");

		List<AssetTypesEntity> AssetTypesList = assetsRepository
				.getAllAssetTypesEntity();

		List<AssetTypeModel> assetTypeModelList = new ArrayList<AssetTypeModel>();

		Iterator<AssetTypesEntity> assetTypesEntityIter = AssetTypesList
				.iterator();
		while (assetTypesEntityIter.hasNext()) {
			AssetTypesEntity singleAssetTypesEntity = assetTypesEntityIter
					.next();
			AssetTypeModel assetTypeModel = new AssetTypeModel();

			assetTypeModel.setAssetTypeId(singleAssetTypesEntity
					.getAssetTypeId());
			assetTypeModel.setAssetTypeName(singleAssetTypesEntity
					.getAssetTypeName());
			assetTypeModel.setAssetTypeDesc(singleAssetTypesEntity
					.getAssetTypeDesc());

			assetTypeModelList.add(assetTypeModel);
		}

		return assetTypeModelList;
	}

}
