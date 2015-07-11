/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.assets;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.gpms.web.gpmsBusinessSrv.assets.AssetAssignModel;

/**
 * @author narenda.kumar
 * 
 */
public class BondedAssetDataConverter {

	static SimpleDateFormat formatter = new SimpleDateFormat(
			"dd/MM/YYYY hh:mm:ss");

	public static BondedAssetBean convertModelToBean(
			AssetAssignModel assetAssignModel) {

		BondedAssetBean bondedAsset = new BondedAssetBean();

		bondedAsset.setUserAssetId(assetAssignModel.getUserAssetId());
		bondedAsset.setUserCorpEmail(assetAssignModel.getUserCorpEmail());
		bondedAsset.setAssetId(assetAssignModel.getAssetId());
		bondedAsset.setUserAssetIssueDate(assetAssignModel
				.getUserAssetIssueDate().toString());
		bondedAsset.setCreateDate(assetAssignModel.getCreateDate());
		bondedAsset.setAssetAssignedComment(assetAssignModel
				.getAssetAssignComments());
		bondedAsset.setUserAssetIssueProcessId(assetAssignModel
				.getUserAssetIssueProcessId());
		bondedAsset.setUserAssetReturnProcessId(assetAssignModel
				.getUserAssetReturnProcessId());
		bondedAsset.setModifiedDate(assetAssignModel.getModifiedDate());
		bondedAsset.setUserAssetReturnDate(assetAssignModel
				.getUserAssetReturnDate());

		return bondedAsset;

	}

	public static AssetAssignModel convertBeanToModel(
			BondedAssetBean bondedAssetBean) {

		AssetAssignModel assetAssignModel = new AssetAssignModel();

		assetAssignModel.setUserAssetId(bondedAssetBean.getUserAssetId());
		assetAssignModel.setUserCorpEmail(bondedAssetBean.getUserCorpEmail());
		assetAssignModel.setAssetId(bondedAssetBean.getAssetId());
		assetAssignModel.setUserAssetIssueDate(bondedAssetBean
				.getUserAssetIssueDate());
		if (bondedAssetBean.getCreateDate() != null) {
			assetAssignModel.setCreateDate(bondedAssetBean.getCreateDate());
		} else {
			String formattedDate = formatter.format(new Date());
			assetAssignModel.setCreateDate(formattedDate);
		}
		String formattedDate = formatter.format(new Date());
		assetAssignModel.setModifiedDate(formattedDate);
		assetAssignModel.setUserAssetReturnDate(bondedAssetBean
				.getUserAssetReturnDate());
		assetAssignModel.setAssetAssignComments(bondedAssetBean
				.getAssetAssignedComment());
		assetAssignModel.setUserAssetIssueProcessId(bondedAssetBean
				.getUserAssetIssueProcessId());
		assetAssignModel.setUserAssetReturnProcessId(bondedAssetBean
				.getUserAssetReturnProcessId());

		return assetAssignModel;

	}

}
