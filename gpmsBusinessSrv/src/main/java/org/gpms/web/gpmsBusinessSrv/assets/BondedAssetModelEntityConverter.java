/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.assets;

import org.gpms.web.entities.assets.UserAssetEntity;
import org.gpms.web.gpmsBusinessSrv.util.DateUtil;

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
				.getUserAssetIssueDate().toString());
		assetAssignModel.setCreateDate(userAssetEntity.getCreateDate()
				.toString());
		assetAssignModel.setUserAssetIssueProcessId(userAssetEntity
				.getUserAssetIssueProcessId());
		assetAssignModel.setUserAssetReturnProcessId(userAssetEntity
				.getUserAssetReturnProcessId());
		if (userAssetEntity.getModifiedDate() != null) {
			assetAssignModel.setModifiedDate(userAssetEntity.getModifiedDate()
					.toString());
		} else {
			assetAssignModel.setModifiedDate(null);
		}
		if (userAssetEntity.getUserAssetReturnDate() != null) {
			assetAssignModel.setUserAssetReturnDate(userAssetEntity
					.getUserAssetReturnDate().toString());
		} else {
			assetAssignModel.setUserAssetReturnDate(null);
		}

		return assetAssignModel;

	}

	public static UserAssetEntity convertModelToEntity(
			AssetAssignModel assetAssignModel) {

		UserAssetEntity userAssetEntity = new UserAssetEntity();

		userAssetEntity.setUserAssetId(assetAssignModel.getUserAssetId());
		userAssetEntity.setUserCorpEmail(assetAssignModel.getUserCorpEmail());
		userAssetEntity.setAssetId(assetAssignModel.getAssetId());
		userAssetEntity.setUserAssetIssueDate(DateUtil
				.getSQLDateForTimeStamp(assetAssignModel
						.getUserAssetIssueDate()));
		userAssetEntity.setCreateDate(DateUtil
				.getSQLDateForTimeStamp(assetAssignModel.getCreateDate()));
		userAssetEntity.setModifiedDate(DateUtil
				.getSQLDateForTimeStamp(assetAssignModel.getModifiedDate()));
		userAssetEntity.setUserAssetIssueProcessId(assetAssignModel
				.getUserAssetIssueProcessId());
		userAssetEntity.setUserAssetReturnProcessId(assetAssignModel
				.getUserAssetReturnProcessId());
		userAssetEntity.setModifiedDate(DateUtil.getSQLDate(assetAssignModel
				.getModifiedDate()));
		if (assetAssignModel.getUserAssetReturnDate() != null) {
			userAssetEntity.setUserAssetReturnDate(DateUtil
					.getSQLDateForTimeStamp(assetAssignModel
							.getUserAssetReturnDate()));
		} else {
			userAssetEntity.setUserAssetReturnDate(null);
		}

		// // TODO Remove
		//
		// userAssetEntity.setUserAssetIssueDate(new Date());
		// userAssetEntity.setCreateDate(new Date());
		// userAssetEntity.setModifiedDate(new Date());
		// userAssetEntity.setUserAssetReturnDate(new Date());

		return userAssetEntity;

	}

}
