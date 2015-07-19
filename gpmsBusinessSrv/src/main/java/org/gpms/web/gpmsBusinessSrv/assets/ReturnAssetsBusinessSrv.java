/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.assets;

import java.sql.Date;

import org.apache.log4j.Logger;
import org.gpms.web.domain.AssetsRepository;
import org.gpms.web.domain.UserAssetsRepository;
import org.gpms.web.domain.UsersRepository;
import org.gpms.web.entities.assets.AssetsEntity;
import org.gpms.web.entities.assets.UserAssetEntity;
import org.gpms.web.entities.users.UsersLoginEntity;
import org.gpms.web.gpmsBusinessSrv.util.ApplicationConstants;
import org.gpms.web.mail.MailServiceParams;
import org.gpms.web.returnBondedItem.ReturnBondedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	/**
	 * 
	 * @param assetAssignModel
	 * @throws GPMSApplicationException
	 */
	@Transactional
	public void ReturnBondedItems(AssetAssignModel assetAssignModel,
			String usersPersonalEmail) throws GPMSApplicationException {

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
					ErrorCodeList.ERROR_NO_ASSET_FOUND,
					ExceptionMessageList.ERROR_NO_ASSET_FOUND_MSG);
		}

		String userAssetId = assetAssignModel.getUserAssetId();

		if (userAssetId != null) {
			userAssetEntity = userAssetsRepository
					.getUserAssetById(userAssetId);
		}

		/*
		 * Start the process of assigning the assign and getting approval.
		 */

		if (userAssetEntity != null
				&& userAssetEntity.getUserAssetReturnProcessId() == null) {

			if (assetsEntity != null
					& !ApplicationConstants.ASSET_ASSIGNED_STATUS
							.equals(assetsEntity.getAssetStatus())) {
				throw new GPMSApplicationException(
						ErrorCodeList.ERROR_ASSET_IS_ALREADY_ASSIGNED,
						ExceptionMessageList.ERROR_ASSET_IS_ALREADY_ASSIGNED_MSG);
			}

			returnBondedItem.setUserForProcessStarter(usersPersonalEmail);
			String processInstanceId = returnBondedItem
					.startReturnBondedItemProcess();

			returnBondedItem.performApprovalAssignment(processInstanceId,
					ApplicationConstants.GROUP_ISIT_MANAGER,
					"gpmsISITMgr@gmail.com");

			// Make necessary modification to records in gpms

			userAssetEntity = userAssetsRepository
					.getUserAssetById(userAssetId);
			userAssetEntity.setUserAssetReturnDate(new Date(assetAssignModel
					.getUserAssetReturnDate().getTime()));
			userAssetEntity.setUserAssetReturnProcessId(processInstanceId);
			userAssetId = userAssetsRepository
					.updateAssetInfoOfUser(userAssetEntity);

			assetsEntity
					.setAssetStatus(ApplicationConstants.ASSET_IN_PROCESS_STATUS);
			assetsRepository.modifyAsset(assetsEntity);

			// Make necessary modification to records in activiti
			String comment = "An asset is for Approval request with id :"
					+ assetsEntity.getAssetId() + "of Type "
					+ assetsEntity.getAssetTypesEntity().getAssetTypeName()
					+ ". It is to been returned back by "
					+ assetAssignModel.getUserCorpEmail();

			returnBondedItem.updateInfoOnTask(processInstanceId, comment,
					"UserAssetEntity", userAssetEntity);

		} else {

			String returnProcessInstanceId;
			returnProcessInstanceId = userAssetEntity
					.getUserAssetReturnProcessId();

			if (assetsEntity != null
					& !ApplicationConstants.ASSET_IN_PROCESS_STATUS
							.equals(assetsEntity.getAssetStatus())) {
				throw new GPMSApplicationException(
						ErrorCodeList.ERROR_ASSET_IS_ALREADY_ASSIGNED,
						ExceptionMessageList.ERROR_ASSET_IS_ALREADY_ASSIGNED_MSG);
			}

			String processStarterMailId = returnBondedItem
					.getProcessStarterByProcessId(returnProcessInstanceId);

			String mailSubject = assetsEntity.getAssetId() + "of asset type "
					+ assetsEntity.getAssetTypesEntity().getAssetTypeName()
					+ " is CORRECTED for the user "
					+ userAssetEntity.getUserCorpEmail();

			String mailHtmlBody = "The asset with " + assetsEntity.getAssetId()
					+ " of asset type "
					+ assetsEntity.getAssetTypesEntity().getAssetTypeName()
					+ " is CORRECTED for the user "
					+ userAssetEntity.getUserCorpEmail()
					+ "<br><br> Regards, <br>" + processStarterMailId;

			MailServiceParams mailServiceParams = new MailServiceParams();
			mailServiceParams.setMailToAddress("gpmsISITMgr@gmail.com");
			mailServiceParams.setMailSubject(mailSubject);
			mailServiceParams.setMailHtmlBody(mailHtmlBody);

			returnBondedItem.setVariableOnExecution(returnProcessInstanceId,
					ApplicationConstants.CORRECTION_MAIL_TO_MANAGER_TASK,
					mailServiceParams);

			returnBondedItem.completeTask(userAssetEntity
					.getUserAssetReturnProcessId());

			returnBondedItem.performApprovalAssignment(
					userAssetEntity.getUserAssetReturnProcessId(),
					ApplicationConstants.GROUP_ISIT_MANAGER,
					"gpmsISITMgr@gmail.com");

			// Make necessary modification to records in activiti
			String comment = mailSubject;

			returnBondedItem.updateInfoOnTask(returnProcessInstanceId, comment,
					"UserAssetEntity", userAssetEntity);

		}

	}

	/**
	 * 
	 * @param userAssetId
	 * @return
	 * @throws GPMSApplicationException
	 */
	public AssetAssignModel getUserAssetById(String userAssetId)
			throws GPMSApplicationException {

		UserAssetEntity userAssetEntity = userAssetsRepository
				.getUserAssetById(userAssetId);

		if (userAssetEntity == null) {
			throw new GPMSApplicationException(
					ErrorCodeList.ERROR_ASSET_IS_NOT_AVAILABLE,
					ExceptionMessageList.ERROR_ASSET_IS_NOT_AVAILABLE_MSG);
		}

		AssetAssignModel assetAssignModel = BondedAssetModelEntityConverter
				.convertEntityToModel(userAssetEntity);

		if (logger.isDebugEnabled()) {
			logger.debug("assetAssignModel :" + assetAssignModel);
		}

		return assetAssignModel;

	}

}
