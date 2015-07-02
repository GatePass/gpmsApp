/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.assets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.gpms.web.domain.AssetsRepository;
import org.gpms.web.entities.assets.AssetTypesEntity;
import org.gpms.web.entities.assets.AssetsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author narenda.kumar
 * 
 */

@Service
public class AssetMgmtBusinessSrv {

	@Autowired
	private AssetsRepository assetsRepository;

	public AssetModel createAsset(AssetModel assetModel) {

		AssetsEntity assetsEntity = new AssetsEntity();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY");
		assetsEntity.setAssetType(assetModel.getAssetTypeId());
		try {
			java.util.Date dateStr = formatter.parse(assetModel
					.getAssetPurchaseDate());
			java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
			assetsEntity.setAssetPurchaseDate(new java.sql.Date(dateDB
					.getTime()));

			dateStr = formatter.parse(assetModel.getAssetRemovalDate());
			dateDB = new java.sql.Date(dateStr.getTime());
			assetsEntity
					.setAssetRemovalDate(new java.sql.Date(dateDB.getTime()));

		} catch (ParseException pe) {
			pe.printStackTrace();
		}

		String assetId = assetsRepository.createAsset(assetsEntity);

		if (assetId != null) {
			assetModel.setAssetId(assetId);
		}

		return assetModel;
	}

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
		List<AssetTypesEntity> AssetTypesList = assetsRepository
				.getAllAssetTypesEntity();

		List<AssetTypeModel> assetTypeModelList = new ArrayList();

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
