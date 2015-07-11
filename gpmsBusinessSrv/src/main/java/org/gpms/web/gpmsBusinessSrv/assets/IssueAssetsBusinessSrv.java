/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.assets;

import org.apache.log4j.Logger;
import org.gpms.web.bondedItemIssue.BondedItemIssue;
import org.gpms.web.domain.AssetsRepository;
import org.gpms.web.domain.UserAssetsRepository;
import org.gpms.web.domain.UsersRepository;
import org.gpms.web.entities.assets.AssetsEntity;
import org.gpms.web.entities.assets.UserAssetEntity;
import org.gpms.web.entities.users.UsersLoginEntity;
import org.gpms.web.gpmsBusinessSrv.util.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author narenda.kumar
 * 
 */
@Service
public class IssueAssetsBusinessSrv {

	private static final Logger logger = Logger
			.getLogger(IssueAssetsBusinessSrv.class);

	@Autowired
	BondedItemIssue bondedItemIssue;

	@Autowired
	UserAssetsRepository userAssetsRepository;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	AssetsRepository assetsRepository;

	public void IssueBondedItems(AssetAssignModel assetAssignModel) {

		/*
		 * Update the gpms database with the record
		 */
		UserAssetEntity userAssetEntity = new UserAssetEntity();

		UsersLoginEntity userLoginEntity = usersRepository
				.getUserByCorpEmailId(assetAssignModel.getUserCorpEmail());

		if (logger.isDebugEnabled()) {
			logger.debug("userLoginEntity :" + userLoginEntity);
		}

		if (userLoginEntity == null) {
			return;
		}

		AssetsEntity assetsEntity = assetsRepository
				.getAssetById(assetAssignModel.getAssetId());

		if (logger.isDebugEnabled()) {
			logger.debug("assetsEntity :" + assetsEntity);
		}

		if (assetsEntity == null) {
			return;
		}

		userAssetEntity = BondedAssetModelEntityConverter
				.convertModelToEntity(assetAssignModel);
		userAssetEntity.setUserAssetIssueProcessId("-999999999");
		String userAssetId = userAssetsRepository
				.addAssetToUser(userAssetEntity);

		/*
		 * Start the process of assigning the asset and getting approval.
		 */

		if (logger.isDebugEnabled()) {
			logger.debug("Start the process of assigning the asset and approval");
		}

		String processInstanceId = bondedItemIssue.startBondItemIssueProcess();

		bondedItemIssue.performApprovalAssignment(processInstanceId,
				ApplicationConstants.GROUP_ISIT_MANAGER,
				"gpmsISITMgr@gmail.com");

		// Make necessary modification to records in gpms

		userAssetEntity = userAssetsRepository.getUserAssetById(userAssetId);
		userAssetEntity.setUserAssetIssueProcessId(processInstanceId);
		userAssetId = userAssetsRepository
				.updateAssetInfoOfUser(userAssetEntity);

		assetsEntity.setAssetStatus(ApplicationConstants.ASSET_ASSIGNED_STATUS);
		assetsRepository.modifyAsset(assetsEntity);

		// Make necessary modification to records in activiti
		String comment = "An asset is for Approval request with id :"
				+ assetsEntity.getAssetId() + "of Type "
				+ assetsEntity.getAssetType() + ". It is to be assigned to "
				+ assetAssignModel.getUserCorpEmail();

		bondedItemIssue.updateInfoOnTask(processInstanceId, comment,
				"UserAssetEntity", userAssetEntity);

		if (logger.isDebugEnabled()) {
			logger.debug("Done - Sync the gpms and activiti db");
		}

	}

}
