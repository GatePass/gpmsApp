/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.assets;

import java.sql.Date;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

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
import org.springframework.transaction.annotation.Transactional;

import exceptions.ErrorCodeList;
import exceptions.ExceptionMessageList;
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

	@Transactional
	public void IssueBondedItems(AssetAssignModel assetAssignModel,
			String usersPersonalEmail) throws GPMSApplicationException {

		/*
		 * Update the gpms database with the record
		 */
		UserAssetEntity userAssetEntity = new UserAssetEntity();

		UsersLoginEntity userLoginEntity = null;

		AssetsEntity assetsEntity = null;

		try {

			userLoginEntity = usersRepository
					.getUserByCorpEmailId(assetAssignModel.getUserCorpEmail());

			if (logger.isDebugEnabled()) {
				logger.debug("userLoginEntity :" + userLoginEntity);
			}

		} catch (PersistenceException pe) {
			if (pe instanceof NoResultException) {
				throw new GPMSApplicationException(
						ErrorCodeList.ERROR_NO_USER_FOUND,
						ExceptionMessageList.ERROR_NO_USER_FOUND_MSG);
			} else {
				throw new GPMSApplicationException(
						ErrorCodeList.ERROR_WITH_DATABASE,
						ExceptionMessageList.ERROR_WITH_DATABASE_MSG);
			}

		}

		try {
			assetsEntity = assetsRepository.getAssetById(assetAssignModel
					.getAssetId());
			if (logger.isDebugEnabled()) {
				logger.debug("assetsEntity :" + assetsEntity);
			}

		} catch (PersistenceException pe) {
			if (pe instanceof NoResultException) {
				throw new GPMSApplicationException(
						ErrorCodeList.ERROR_NO_ASSET_FOUND,
						ExceptionMessageList.ERROR_NO_ASSET_FOUND_MSG);
			} else {
				throw new GPMSApplicationException(
						ErrorCodeList.ERROR_WITH_DATABASE,
						ExceptionMessageList.ERROR_WITH_DATABASE_MSG);
			}
		}

		String processInstanceId = null;
		String userAssetId = null;
		if (assetAssignModel.getUserAssetId() == null) {

			if (assetsEntity != null
					& !ApplicationConstants.ASSET_AVAILABLE_STATUS
							.equals(assetsEntity.getAssetStatus())) {
				throw new GPMSApplicationException(
						ErrorCodeList.ERROR_ASSET_IS_NOT_AVAILABLE,
						ExceptionMessageList.ERROR_ASSET_IS_NOT_AVAILABLE_MSG);
			}

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

			bondedItemIssue.setUserForProcessStarter(usersPersonalEmail);
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
					+ assetsEntity.getAssetTypesEntity().getAssetTypeName()
					+ ". It is to be assigned to "
					+ assetAssignModel.getUserCorpEmail();

			comment = comment + "<br><br>\nAdditional Comments : "
					+ assetAssignModel.getAssetComments();

			bondedItemIssue.updateInfoOnTask(processInstanceId, comment,
					"UserAssetEntity", userAssetEntity);

		} else {
			userAssetEntity = userAssetsRepository
					.getUserAssetById(assetAssignModel.getUserAssetId());
			processInstanceId = assetAssignModel.getUserAssetIssueProcessId();

			if (assetAssignModel.getUserAssetIssueDate() != null) {
				userAssetEntity.setUserAssetIssueDate(new Date(assetAssignModel
						.getUserAssetIssueDate().getTime()));
			}
			userAssetId = userAssetsRepository
					.updateAssetInfoOfUser(userAssetEntity);

			if (assetsEntity != null) {
				if (!ApplicationConstants.ASSET_IN_PROCESS_STATUS
						.equals(assetsEntity.getAssetStatus())) {
					throw new GPMSApplicationException(
							ErrorCodeList.ERROR_ASSET_IS_NOT_ASSIGNED,
							ExceptionMessageList.ERROR_ASSET_IS_NOT_ASSIGNED_MSG);
				}
			}

			if (logger.isDebugEnabled()) {
				logger.debug("userAssetEntity :" + userAssetEntity);
				logger.debug("assetAssignModel :" + assetAssignModel);
			}

			MailServiceParams mailServiceParams = new MailServiceParams();
			mailServiceParams.setMailToAddress("gpmsISITMgr@gmail.com");
			mailServiceParams.setMailSubject(userAssetEntity.getAssetId()
					+ " is Corrected for the user "
					+ userAssetEntity.getUserCorpEmail());
			String mailHtmlBody = "Test email from gpmsisituser3@gmail.com to gpmsISITMgr@gmail.com"
					+ "<br><br> Regards, <br>gpmsisituser3@gmail.com";
			mailServiceParams.setMailHtmlBody(mailHtmlBody);

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
					+ assetsEntity.getAssetTypesEntity().getAssetTypeName()
					+ ". It is to be assigned to "
					+ assetAssignModel.getUserCorpEmail();

			comment = comment + "<br><br>\nAdditional Comments : "
					+ assetAssignModel.getAssetComments();

			bondedItemIssue.updateInfoOnTask(processInstanceId, comment,
					"UserAssetEntity", userAssetEntity);

		}

		if (logger.isDebugEnabled()) {
			logger.debug("Done - Sync the gpms and activiti db");
		}

	}
}
