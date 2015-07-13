/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.assets;

import org.apache.log4j.Logger;
import org.gpms.web.domain.AssetsRepository;
import org.gpms.web.domain.UserAssetsRepository;
import org.gpms.web.domain.UsersRepository;
import org.gpms.web.entities.assets.AssetsEntity;
import org.gpms.web.entities.assets.UserAssetEntity;
import org.gpms.web.entities.users.UsersLoginEntity;
import org.gpms.web.gpmsBusinessSrv.util.ApplicationConstants;
import org.gpms.web.gpmsBusinessSrv.util.DateUtil;
import org.gpms.web.returnBondedItem.ReturnBondedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exceptions.ErrorCodeList;
import exceptions.ExceptionMessageList;
import exceptions.GPMSApplicationException;

/**
 * @author narenda.kumar
 * 
 */
@Service
public class ReturnAssetsBusinessSrv {

	private static final Logger logger = Logger
			.getLogger(ReturnAssetsBusinessSrv.class);

	@Autowired
	ReturnBondedItem returnBondedItem;

	@Autowired
	UserAssetsRepository userAssetsRepository;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	AssetsRepository assetsRepository;

	public void ReturnBondedItems(AssetAssignModel assetAssignModel)
			throws GPMSApplicationException {

		if (logger.isDebugEnabled()) {
			logger.debug("assetAssignModel :" + assetAssignModel);
		}

		/*
		 * Update the gpms database with the record
		 */
		UserAssetEntity userAssetEntity = new UserAssetEntity();

		AssetsEntity assetsEntity = assetsRepository
				.getAssetById(assetAssignModel.getAssetId());

		UsersLoginEntity userLoginEntity = usersRepository
				.getUserByCorpEmailId(assetAssignModel.getUserCorpEmail());

		if (logger.isDebugEnabled()) {
			logger.debug("userLoginEntity :" + userLoginEntity);
		}

		if (userLoginEntity == null) {
			throw new GPMSApplicationException(
					ErrorCodeList.ERROR_NO_USER_FOUND,
					ExceptionMessageList.ERROR_NO_USER_FOUND_MSG);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("assetsEntity :" + assetsEntity);
		}

		if (assetsEntity == null) {
			throw new GPMSApplicationException(
					ErrorCodeList.ERROR_NO_USER_FOUND,
					ExceptionMessageList.ERROR_NO_ASSET_FOUND_MSG);
		}
		if (assetsEntity != null
				& !ApplicationConstants.ASSET_AVAILABLE_STATUS
						.equals(assetsEntity.getAssetStatus())) {
			throw new GPMSApplicationException(
					ErrorCodeList.ERROR_ASSET_IS_NOT_AVAILABLE,
					ExceptionMessageList.ERROR_ASSET_IS_NOT_AVAILABLE_MSG);
		}

		String userAssetId = assetAssignModel.getUserAssetId();

		if (userAssetId != null) {
			userAssetEntity = userAssetsRepository
					.getUserAssetById(userAssetId);
		}

		userAssetEntity.setUserAssetReturnDate(DateUtil
				.getSQLDate(assetAssignModel.getUserAssetReturnDate()));

		userAssetsRepository.updateAssetInfoOfUser(userAssetEntity);

		/*
		 * Start the process of assigning the assign and getting approval.
		 */

		String processInstanceId = returnBondedItem
				.startReturnBondedItemProcess();

		returnBondedItem.performApprovalAssignment(
				ApplicationConstants.ISIT_MGR_APPROVAL_TASK, processInstanceId,
				ApplicationConstants.GROUP_ISIT_MANAGER,
				"gpmsISITMgr@gmail.com");

		// Make necessary modification to records in gpms

		userAssetEntity = userAssetsRepository.getUserAssetById(userAssetId);
		userAssetEntity.setUserAssetReturnProcessId(processInstanceId);
		userAssetId = userAssetsRepository
				.updateAssetInfoOfUser(userAssetEntity);

		// Make necessary modification to records in activiti
		String comment = "An asset is for Approval request with id :"
				+ assetsEntity.getAssetId() + "of Type "
				+ assetsEntity.getAssetType()
				+ ". It is to been returned back by "
				+ assetAssignModel.getUserCorpEmail();

		returnBondedItem.updateInfoOnTask(processInstanceId, comment,
				"UserAssetEntity", userAssetEntity);

	}

	public AssetAssignModel getUserAssetById(String userAssetId) {

		UserAssetEntity userAssetEntity = userAssetsRepository
				.getUserAssetById(userAssetId);

		AssetAssignModel assetAssignModel = BondedAssetModelEntityConverter
				.convertEntityToModel(userAssetEntity);

		if (logger.isDebugEnabled()) {
			logger.debug("assetAssignModel :" + assetAssignModel);
		}

		return assetAssignModel;

	}

}
