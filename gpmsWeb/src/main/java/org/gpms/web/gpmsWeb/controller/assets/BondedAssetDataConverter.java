/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.assets;

import java.util.Date;

import org.gpms.web.gpmsBusinessSrv.assets.AssetAssignModel;
import org.gpms.web.gpmsBusinessSrv.util.DateUtil;

/**
 * @author narenda.kumar
 * 
 */
public class BondedAssetDataConverter {

	public static BondedAssetBean convertModelToBean(
			AssetAssignModel assetAssignModel) {

		BondedAssetBean bondedAsset = new BondedAssetBean();

		bondedAsset.setUserAssetId(assetAssignModel.getUserAssetId());
		bondedAsset.setUserCorpEmail(assetAssignModel.getUserCorpEmail());
		bondedAsset.setAssetId(assetAssignModel.getAssetId());
		bondedAsset.setUserAssetIssueDate(assetAssignModel
				.getUserAssetIssueDate().toString());
		bondedAsset.setCreateDate(DateUtil
				.getDateFormattedString(assetAssignModel.getCreateDate()));
		bondedAsset.setAssetAssignedComment(assetAssignModel
				.getAssetAssignComments());
		bondedAsset.setUserAssetIssueProcessId(assetAssignModel
				.getUserAssetIssueProcessId());
		bondedAsset.setUserAssetReturnProcessId(assetAssignModel
				.getUserAssetReturnProcessId());
		bondedAsset.setModifiedDate(DateUtil
				.getDateFormattedString(assetAssignModel.getModifiedDate()));
		if (assetAssignModel.getUserAssetReturnDate() != null) {
			bondedAsset.setUserAssetReturnDate(DateUtil
					.getDateFormattedString(assetAssignModel
							.getUserAssetReturnDate()));
		}

		return bondedAsset;

	}

	public static AssetAssignModel convertBeanToModel(
			BondedAssetBean bondedAssetBean) {

		AssetAssignModel assetAssignModel = new AssetAssignModel();

		assetAssignModel.setUserAssetId(bondedAssetBean.getUserAssetId());
		assetAssignModel.setUserCorpEmail(bondedAssetBean.getUserCorpEmail());
		assetAssignModel.setAssetId(bondedAssetBean.getAssetId());

		if (bondedAssetBean.getCreateDate() == null) {
			Date formattedDate = new Date();
			assetAssignModel.setCreateDate(formattedDate);
		}

		assetAssignModel.setModifiedDate(new Date());

		if (bondedAssetBean.getUserAssetIssueDate() != null) {
			Date issueDate = DateUtil.getSQLDateForTimeStamp(bondedAssetBean
					.getUserAssetIssueDate());
			assetAssignModel.setUserAssetIssueDate(issueDate);
		}

		if (bondedAssetBean.getUserAssetReturnDate() != null) {
			Date returnDate = DateUtil.getSQLDateForTimeStamp(bondedAssetBean
					.getUserAssetReturnDate());
			assetAssignModel.setUserAssetReturnDate(returnDate);
		}

		assetAssignModel.setAssetAssignComments(bondedAssetBean
				.getAssetAssignedComment());
		assetAssignModel.setUserAssetIssueProcessId(bondedAssetBean
				.getUserAssetIssueProcessId());
		assetAssignModel.setUserAssetReturnProcessId(bondedAssetBean
				.getUserAssetReturnProcessId());

		return assetAssignModel;

	}

}
