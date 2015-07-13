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
import org.gpms.web.mail.MailServiceParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exceptions.GPMSApplicationException;

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

	public void IssueBondedItems(AssetAssignModel assetAssignModel)
			throws GPMSApplicationException {

		/*
		 * Update the gpms database with the record
		 */
		UserAssetEntity userAssetEntity = new UserAssetEntity();

		UsersLoginEntity userLoginEntity = usersRepository
				.getUserByCorpEmailId(assetAssignModel.getUserCorpEmail());

		AssetsEntity assetsEntity = assetsRepository
				.getAssetById(assetAssignModel.getAssetId());

		// try {
		//
		// UsersLoginEntity userLoginEntity = usersRepository
		// .getUserByCorpEmailId(assetAssignModel.getUserCorpEmail());
		//
		// if (logger.isDebugEnabled()) {
		// logger.debug("userLoginEntity :" + userLoginEntity);
		// }
		//
		// } catch (PersistenceException pe) {
		// if (pe instanceof NoResultException) {
		// throw new GPMSApplicationException(
		// ErrorCodeList.ERROR_NO_USER_FOUND,
		// ExceptionMessageList.ERROR_NO_USER_FOUND_MSG);
		// } else {
		// throw new GPMSApplicationException(
		// ErrorCodeList.ERROR_WITH_DATABASE,
		// ExceptionMessageList.ERROR_WITH_DATABASE_MSG);
		// }
		//
		// }
		//
		// AssetsEntity assetsEntity = null;
		//
		// try {
		// assetsEntity = assetsRepository.getAssetById(assetAssignModel
		// .getAssetId());
		// if (logger.isDebugEnabled()) {
		// logger.debug("assetsEntity :" + assetsEntity);
		// }
		//
		// } catch (PersistenceException pe) {
		// if (pe instanceof NoResultException) {
		// throw new GPMSApplicationException(
		// ErrorCodeList.ERROR_NO_USER_FOUND,
		// ExceptionMessageList.ERROR_NO_ASSET_FOUND_MSG);
		// } else {
		// throw new GPMSApplicationException(
		// ErrorCodeList.ERROR_WITH_DATABASE,
		// ExceptionMessageList.ERROR_WITH_DATABASE_MSG);
		// }
		// }
		//
		// if (assetsEntity != null
		// & !ApplicationConstants.ASSET_AVAILABLE_STATUS
		// .equals(assetsEntity.getAssetStatus())) {
		// throw new GPMSApplicationException(
		// ErrorCodeList.ERROR_ASSET_IS_NOT_AVAILABLE,
		// ExceptionMessageList.ERROR_ASSET_IS_NOT_AVAILABLE_MSG);
		// }

		String processInstanceId = null;
		String userAssetId = null;
		if (assetAssignModel.getUserAssetId() == null) {
			userAssetEntity = BondedAssetModelEntityConverter
					.convertModelToEntity(assetAssignModel);
			userAssetEntity.setUserAssetIssueProcessId("-999999999");
			userAssetId = userAssetsRepository.addAssetToUser(userAssetEntity);

			/*
			 * Start the process of assigning the asset and getting approval.
			 */

			if (logger.isDebugEnabled()) {
				logger.debug("Start the process of assigning the asset and approval");
			}

			processInstanceId = bondedItemIssue.startBondItemIssueProcess();

			bondedItemIssue.performApprovalAssignment(
					ApplicationConstants.ISIT_MGR_APPROVAL_TASK,
					processInstanceId, ApplicationConstants.GROUP_ISIT_MANAGER,
					"gpmsISITMgr@gmail.com");

			// Make necessary modification to records in gpms

			userAssetEntity = userAssetsRepository
					.getUserAssetById(userAssetId);
			userAssetEntity.setUserAssetIssueProcessId(processInstanceId);
			userAssetId = userAssetsRepository
					.updateAssetInfoOfUser(userAssetEntity);

			assetsEntity
					.setAssetStatus(ApplicationConstants.ASSET_IN_PROCESS_STATUS);
			assetsRepository.modifyAsset(assetsEntity);

			// Make necessary modification to records in activiti
			String comment = "An asset is for Approval request with id :"
					+ assetsEntity.getAssetId() + "of Type "
					+ assetsEntity.getAssetType()
					+ ". It is to be assigned to "
					+ assetAssignModel.getUserCorpEmail();

			bondedItemIssue.updateInfoOnTask(processInstanceId, comment,
					"UserAssetEntity", userAssetEntity);

		} else {
			userAssetEntity = userAssetsRepository
					.getUserAssetById(assetAssignModel.getUserAssetId());
			userAssetsRepository.updateAssetInfoOfUser(userAssetEntity);

			if (logger.isDebugEnabled()) {
				logger.debug("userAssetEntity :" + userAssetEntity);
				logger.debug("assetAssignModel :" + assetAssignModel);
			}

			MailServiceParams mailServiceParams = new MailServiceParams();
			mailServiceParams.setMailUserId("gpmsisituser3@gmail.com");
			mailServiceParams.setMailPassword("gpmsisituser3777#$");
			mailServiceParams.setMailToAddress("gpmsISITMgr@gmail.com");
			mailServiceParams.setMailSubject(assetAssignModel.getAssetId()
					+ " is Corrected for the user "
					+ assetAssignModel.getUserCorpEmail());
			String mailHtmlBody = "Test email from gpmsisituser3@gmail.com to gpmsISITMgr@gmail.com"
					+ "<br><br> Regards, <br>gpmsisituser3@gmail.com";
			mailServiceParams.setMailHtmlBody(mailHtmlBody);

			processInstanceId = assetAssignModel.getUserAssetIssueProcessId();
			bondedItemIssue.setVariableOnExecution(processInstanceId,
					ApplicationConstants.CORRECTION_MAIL_TO_MANAGER_TASK,
					mailServiceParams);

			bondedItemIssue.completeTask(assetAssignModel
					.getUserAssetIssueProcessId());

			bondedItemIssue.performApprovalAssignment(
					assetAssignModel.getUserAssetIssueProcessId(),
					ApplicationConstants.GROUP_ISIT_MANAGER,
					"gpmsISITMgr@gmail.com");

			// Make necessary modification to records in activiti
			String comment = "An asset has been corrected and is for Approval request with id :"
					+ assetsEntity.getAssetId()
					+ "of Type "
					+ assetsEntity.getAssetType()
					+ ". It is to be assigned to "
					+ assetAssignModel.getUserCorpEmail();

			bondedItemIssue.updateInfoOnTask(processInstanceId, comment,
					"UserAssetEntity", userAssetEntity);

		}

		if (logger.isDebugEnabled()) {
			logger.debug("Done - Sync the gpms and activiti db");
		}

	}
}
