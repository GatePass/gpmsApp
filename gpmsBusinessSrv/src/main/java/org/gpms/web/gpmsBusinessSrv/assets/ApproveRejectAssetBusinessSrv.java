package org.gpms.web.gpmsBusinessSrv.assets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.gpms.web.common.BondedItemManagement;
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

@Service
public class ApproveRejectAssetBusinessSrv {

	@Autowired
	BondedItemManagement bondedItemManagement;

	@Autowired
	UserAssetsRepository userAssetsRepository;

	@Autowired
	private AssetsRepository assetsRepository;

	@Autowired
	private UsersRepository usersRepository;

	private static final Logger logger = Logger
			.getLogger(ApproveRejectAssetBusinessSrv.class);

	/**
	 * 
	 * @param assetAssignModel
	 * @param processId
	 * @param flowType
	 */
	@Transactional
	public void approveAsset(AssetAssignModel assetAssignModel,
			String processId, String flowType) {

		String processInstanceId = processId;

		if (logger.isDebugEnabled()) {
			logger.debug("processInstanceId : " + processInstanceId);
		}

		UserAssetEntity userAssetEntity = userAssetsRepository
				.getUserAssetById(assetAssignModel.getUserAssetId());
		UsersLoginEntity userLoginEntityofEmp = usersRepository
				.getUserByCorpEmailId(userAssetEntity.getUserCorpEmail());
		AssetsEntity assetsEntity = assetsRepository
				.getAssetById(userAssetEntity.getAssetId());

		// Mail to ISIT User

		String mailSubject = assetsEntity.getAssetId() + "of asset type "
				+ assetsEntity.getAssetTypesEntity().getAssetTypeName()
				+ " is APPROVED for the user "
				+ userAssetEntity.getUserCorpEmail();

		String mailHtmlBody = "The asset with " + assetsEntity.getAssetId()
				+ " of asset type "
				+ assetsEntity.getAssetTypesEntity().getAssetTypeName()
				+ " is APPROVED for the user "
				+ userAssetEntity.getUserCorpEmail()
				+ "<br><br> Regards, <br>gpmsISITMgr@gmail.com";

		String processStarterMailId = bondedItemManagement
				.getProcessStarterByProcessId(processInstanceId);
		MailServiceParams mailServiceParams = new MailServiceParams();
		mailServiceParams.setMailToAddress(processStarterMailId);
		mailServiceParams.setMailSubject(mailSubject);
		mailServiceParams.setMailHtmlBody(mailHtmlBody);

		bondedItemManagement.setVariableOnExecution(processInstanceId,
				ApplicationConstants.APPROVE_MAIL_TO_ISIT_MEMBER_TASK,
				mailServiceParams);

		if (logger.isDebugEnabled()) {
			logger.debug("Set Variable : APPROVE_MAIL_TO_ISIT_MEMBER_TASK");
		}

		// Mail to Security
		mailServiceParams = new MailServiceParams();
		mailServiceParams.setMailToAddress("gpmsSecu@gmail.com");
		mailServiceParams.setMailSubject(mailSubject);
		mailServiceParams.setMailHtmlBody(mailHtmlBody);

		bondedItemManagement.setVariableOnExecution(processInstanceId,
				ApplicationConstants.APPROVE_MAIL_TO_SECURITY_TASK,
				mailServiceParams);

		if (logger.isDebugEnabled()) {
			logger.debug("Set Variable : APPROVE_MAIL_TO_SECURITY_TASK");
		}

		// Mail to Employee

		mailServiceParams = new MailServiceParams();
		mailServiceParams.setMailToAddress(userLoginEntityofEmp
				.getUserPersonnalEmail());
		mailServiceParams.setMailSubject(mailSubject);
		mailServiceParams.setMailHtmlBody(mailHtmlBody);

		bondedItemManagement.setVariableOnExecution(processInstanceId,
				ApplicationConstants.APPROVE_MAIL_TO_EMPLOYEE_TASK,
				mailServiceParams);

		if (logger.isDebugEnabled()) {
			logger.debug("Set Variable : APPROVE_MAIL_TO_EMPLOYEE_TASK");
		}

		String comment = mailSubject;

		bondedItemManagement.setVariableOnTask(processInstanceId, "isApproved",
				true);
		bondedItemManagement.updateInfoOnTask(processInstanceId, comment, null,
				null);
		bondedItemManagement.completeTask(processInstanceId);

		if (ApplicationConstants.FLOW_TYPE_ISSUE.equals(flowType)) {
			assetsEntity
					.setAssetStatus(ApplicationConstants.ASSET_ASSIGNED_STATUS);
		} else {
			assetsEntity
					.setAssetStatus(ApplicationConstants.ASSET_AVAILABLE_STATUS);
		}

		assetsRepository.modifyAsset(assetsEntity);

		if (logger.isDebugEnabled()) {
			logger.debug("Approval has approved the task for asset to be issued");
		}

	}

	/**
	 * 
	 * @param assetAssignModel
	 * @param processId
	 * @param flowType
	 */
	@Transactional
	public void rejectAsset(AssetAssignModel assetAssignModel,
			String processId, String flowType) {

		String processInstanceId = processId;

		if (logger.isDebugEnabled()) {
			logger.debug("processInstanceId : " + processInstanceId);
		}

		UserAssetEntity userAssetEntity = userAssetsRepository
				.getUserAssetById(assetAssignModel.getUserAssetId());
		AssetsEntity assetsEntity = assetsRepository
				.getAssetById(userAssetEntity.getAssetId());

		String mailSubject = assetsEntity.getAssetId() + "of asset type "
				+ assetsEntity.getAssetTypesEntity().getAssetTypeName()
				+ " is REJECTED for the user "
				+ userAssetEntity.getUserCorpEmail();

		String mailHtmlBody = "The asset with " + assetsEntity.getAssetId()
				+ " of asset type "
				+ assetsEntity.getAssetTypesEntity().getAssetTypeName()
				+ " is REJECTED for the user "
				+ userAssetEntity.getUserCorpEmail()
				+ "<br><br> Regards, <br>gpmsISITMgr@gmail.com";

		// Mail to ISIT User
		String processStarterMailId = bondedItemManagement
				.getProcessStarterByProcessId(processInstanceId);
		MailServiceParams mailServiceParams = new MailServiceParams();
		mailServiceParams.setMailToAddress(processStarterMailId);
		mailServiceParams.setMailSubject(mailSubject);
		mailServiceParams.setMailHtmlBody(mailHtmlBody);

		bondedItemManagement.setVariableOnExecution(processInstanceId,
				ApplicationConstants.REJECT_MAIL_TO_ISIT_USER_TASK,
				mailServiceParams);

		if (logger.isDebugEnabled()) {
			logger.debug("Set Variable : REJECT_MAIL_TO_ISIT_USER_TASK");
		}

		bondedItemManagement.setVariableOnTask(processInstanceId, "isApproved",
				false);

		bondedItemManagement.completeTask(processInstanceId);

		String comment = mailSubject;

		bondedItemManagement.updateInfoOnTask(processInstanceId, comment,
				"userAssetEntity", userAssetEntity);

		bondedItemManagement.performApprovalAssignment(processInstanceId,
				ApplicationConstants.GROUP_ISIT_USER, processStarterMailId);

		if (logger.isDebugEnabled()) {
			logger.debug("Approval has rejected the task for asset to be issued");
		}

	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<AssetAssignModel> getAllTasksForAction(String userId) {

		List<Task> taskList = bondedItemManagement.getAllTasksForAction(userId);

		if (logger.isDebugEnabled()) {
			logger.debug("taskList : " + taskList.size());
		}

		List<AssetAssignModel> assetAssignModelLst = new ArrayList<AssetAssignModel>();

		Iterator<Task> taskListIter = taskList.iterator();

		while (taskListIter.hasNext()) {
			Task singleTask = taskListIter.next();
			AssetAssignModel assetAssignModel = new AssetAssignModel();

			UserAssetEntity userAssetEntity = (UserAssetEntity) bondedItemManagement
					.getVariableByTaskId(singleTask.getId(), "UserAssetEntity");

			List<Comment> comment = bondedItemManagement
					.getCommentsByTaskId(singleTask.getId());

			if (userAssetEntity != null) {
				assetAssignModel = BondedAssetModelEntityConverter
						.convertEntityToModel(userAssetEntity);

				Iterator<Comment> commentIter = comment.iterator();

				while (commentIter.hasNext()) {
					Comment singleComment = commentIter.next();
					assetAssignModel.setAssetAssignComments(singleComment
							.getFullMessage());
				}
				assetAssignModelLst.add(assetAssignModel);
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("assetAssignModelLst : " + assetAssignModelLst.size());
		}

		return assetAssignModelLst;
	}

	/**
	 * 
	 * @param userAssetId
	 * @return
	 */
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

	/**
	 * @return the bondedItemManagement
	 */
	public BondedItemManagement getBondedItemManagement() {
		return bondedItemManagement;
	}

	/**
	 * @param bondedItemManagement
	 *            the bondedItemManagement to set
	 */
	public void setBondedItemManagement(
			BondedItemManagement bondedItemManagement) {
		this.bondedItemManagement = bondedItemManagement;
	}

}
