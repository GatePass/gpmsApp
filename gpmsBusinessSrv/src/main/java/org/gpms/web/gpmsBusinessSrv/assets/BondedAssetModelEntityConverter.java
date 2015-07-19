/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.assets;

import java.sql.Date;

import org.gpms.web.entities.assets.UserAssetEntity;

/**
 * @author narenda.kumar
 * 
 */
public class BondedAssetModelEntityConverter {

	public static AssetAssignModel convertEntityToModel(
			UserAssetEntity userAssetEntity) {

		AssetAssignModel assetAssignModel = new AssetAssignModel();

		assetAssignModel.setUserAssetId(userAssetEntity.getUserAssetId());
		assetAssignModel.setUserCorpEmail(userAssetEntity.getUserCorpEmail());
		assetAssignModel.setAssetId(userAssetEntity.getAssetId());
		assetAssignModel.setUserAssetIssueDate(userAssetEntity
				.getUserAssetIssueDate());
		assetAssignModel.setCreateDate(userAssetEntity.getCreateDate());
		assetAssignModel.setUserAssetIssueProcessId(userAssetEntity
				.getUserAssetIssueProcessId());
		assetAssignModel.setUserAssetReturnProcessId(userAssetEntity
				.getUserAssetReturnProcessId());
		if (userAssetEntity.getModifiedDate() != null) {
			assetAssignModel.setModifiedDate(userAssetEntity.getModifiedDate());
		}

		if (userAssetEntity.getUserAssetReturnDate() != null) {
			assetAssignModel.setUserAssetReturnDate(userAssetEntity
					.getUserAssetReturnDate());
		}

		return assetAssignModel;

	}

	public static UserAssetEntity convertModelToEntity(
			AssetAssignModel assetAssignModel) {

		UserAssetEntity userAssetEntity = new UserAssetEntity();

		userAssetEntity.setUserAssetId(assetAssignModel.getUserAssetId());
		userAssetEntity.setUserCorpEmail(assetAssignModel.getUserCorpEmail());
		userAssetEntity.setAssetId(assetAssignModel.getAssetId());

		if (assetAssignModel.getCreateDate() != null) {
			userAssetEntity.setCreateDate(new Date(assetAssignModel
					.getCreateDate().getTime()));
		}
		if (assetAssignModel.getModifiedDate() != null) {
			userAssetEntity.setModifiedDate(new Date(assetAssignModel
					.getModifiedDate().getTime()));
		}

		if (assetAssignModel.getUserAssetIssueDate() != null) {
			userAssetEntity.setUserAssetIssueDate(new Date(assetAssignModel
					.getUserAssetIssueDate().getTime()));
		}

		if (assetAssignModel.getUserAssetReturnDate() != null) {
			userAssetEntity.setUserAssetReturnDate(new Date(assetAssignModel
					.getUserAssetReturnDate().getTime()));
		}
		userAssetEntity.setUserAssetIssueProcessId(assetAssignModel
				.getUserAssetIssueProcessId());
		userAssetEntity.setUserAssetReturnProcessId(assetAssignModel
				.getUserAssetReturnProcessId());

		return userAssetEntity;
	}
}
