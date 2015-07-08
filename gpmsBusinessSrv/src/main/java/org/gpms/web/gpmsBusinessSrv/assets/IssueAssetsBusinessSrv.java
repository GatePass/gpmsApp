/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.assets;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.gpms.web.bondedItemIssue.BondedItemIssue;
import org.gpms.web.domain.AssetsRepository;
import org.gpms.web.domain.UserAssetsRepository;
import org.gpms.web.domain.UsersRepository;
import org.gpms.web.entities.assets.AssetsEntity;
import org.gpms.web.entities.assets.UserAssetEntity;
import org.gpms.web.entities.users.UsersLoginEntity;
import org.gpms.web.gpmsBusinessSrv.util.ApplicationConstants;
import org.gpms.web.gpmsBusinessSrv.util.DateUtil;
import org.gpms.web.mail.MailServiceParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author narenda.kumar
 * 
 */
@Service
public class IssueAssetsBusinessSrv {

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
		if (userLoginEntity == null) {
			return;
		}

		AssetsEntity assetsEntity = assetsRepository
				.getAssetById(assetAssignModel.getAssetId());
		if (assetsEntity == null) {
			return;
		}

		userAssetEntity.setAssetId(assetAssignModel.getAssetId());
		userAssetEntity.setUserCorpEmail(assetAssignModel.getUserCorpEmail());
		userAssetEntity.setUserAssetIssueDate(DateUtil
				.getSQLDate(assetAssignModel.getUserAssetIssueDate()));
		userAssetEntity.setUserAssetIssueProcessId("-999999999");
		userAssetEntity.setCreateDate(new Date());
		String userAssetId = userAssetsRepository
				.addAssetToUser(userAssetEntity);

		/*
		 * Start the process of assigning the assign and getting approval.
		 */

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
		assetsRepository.updateAssetInfo(assetsEntity);

		// Make necessary modification to records in activiti
		String comment = "An asset is for Approval request with id :"
				+ assetsEntity.getAssetId() + "of Type "
				+ assetsEntity.getAssetType() + ". It is to be assigned to "
				+ assetAssignModel.getUserCorpEmail();

		bondedItemIssue.updateInfoOnTask(processInstanceId, comment,
				"UserAssetEntity", userAssetEntity);

	}

	public List<AssetAssignModel> getAllTasksForAction(String userId) {
		List<Task> taskList = bondedItemIssue.getAllTasksForAction(userId);

		List<AssetAssignModel> assetAssignModelLst = new ArrayList<AssetAssignModel>();

		Iterator<Task> taskListIter = taskList.iterator();

		while (taskListIter.hasNext()) {
			Task singleTask = taskListIter.next();
			AssetAssignModel assetAssignModel = new AssetAssignModel();

			UserAssetEntity userAssetEntity = (UserAssetEntity) bondedItemIssue
					.getVariableByTaskId(singleTask.getId(), "UserAssetEntity");

			List<Comment> comment = bondedItemIssue
					.getCommentsByTaskId(singleTask.getId());

			assetAssignModel.setUserAssetId(userAssetEntity.getUserAssetId());
			assetAssignModel.setUserCorpEmail(userAssetEntity
					.getUserCorpEmail());
			assetAssignModel.setAssetId(userAssetEntity.getAssetId());
			assetAssignModel.setUserAssetIssueDate(userAssetEntity
					.getUserAssetIssueDate().toString());
			assetAssignModel.setCreateDate(userAssetEntity.getCreateDate()
					.toString());
			assetAssignModel.setUserAssetIssueProcessId(userAssetEntity
					.getUserAssetIssueProcessId());

			Iterator<Comment> commentIter = comment.iterator();

			while (commentIter.hasNext()) {
				Comment singleComment = commentIter.next();
				assetAssignModel.setAssetAssignComments(singleComment
						.getFullMessage());
			}

			assetAssignModelLst.add(assetAssignModel);
		}

		return assetAssignModelLst;
	}

	public void approveAsset(AssetAssignModel assetAssignModel) {

		String processInstanceId = assetAssignModel
				.getUserAssetIssueProcessId();

		System.out.println("processInstanceId : " + processInstanceId);

		// Mail to ISIT User
		MailServiceParams mailServiceParams = new MailServiceParams();
		mailServiceParams.setMailUserId("gpmsISITMgr@gmail.com");
		mailServiceParams.setMailPassword("gpmsISITMgr777#$");
		mailServiceParams.setMailToAddress("gpmsisituser3@gmail.com");
		mailServiceParams.setMailSubject(assetAssignModel.getAssetId()
				+ " is approved for the user "
				+ assetAssignModel.getUserCorpEmail());
		String mailHtmlBody = "Test email from gpmsISITMgr@gmail.com to gpmsisituser3@gmail.com"
				+ "<br><br> Regards, <br>gpmsISITMgr@gmail.com";
		mailServiceParams.setMailHtmlBody(mailHtmlBody);

		bondedItemIssue.setVariableOnExecution(processInstanceId,
				ApplicationConstants.APPROVE_MAIL_TO_ISIT_MEMBER_TASK,
				mailServiceParams);
		// Mail to Security

		mailServiceParams = new MailServiceParams();
		mailServiceParams.setMailUserId("gpmsISITMgr@gmail.com");
		mailServiceParams.setMailPassword("gpmsISITMgr777#$");
		mailServiceParams.setMailToAddress("gpmsSecu@gmail.com");
		mailServiceParams.setMailSubject(assetAssignModel.getAssetId()
				+ " is approved for the user "
				+ assetAssignModel.getUserCorpEmail());
		mailHtmlBody = "Test email from gpmsISITMgr@gmail.com to gpmsSecu@gmail.com"
				+ "<br><br> Regards, <br>gpmsISITMgr@gmail.com";
		mailServiceParams.setMailHtmlBody(mailHtmlBody);

		bondedItemIssue.setVariableOnExecution(processInstanceId,
				ApplicationConstants.APPROVE_MAIL_TO_SECURITY_TASK,
				mailServiceParams);

		// Mail to Employee

		mailServiceParams = new MailServiceParams();
		mailServiceParams.setMailUserId("gpmsISITMgr@gmail.com");
		mailServiceParams.setMailPassword("gpmsISITMgr777#$");
		mailServiceParams.setMailToAddress("gpmsisituser3@gmail.com");
		mailServiceParams.setMailSubject(assetAssignModel.getAssetId()
				+ " is approved for the user "
				+ assetAssignModel.getUserCorpEmail());
		mailHtmlBody = "Test email from gpmsISITMgr@gmail.com to "
				+ assetAssignModel.getUserCorpEmail()
				+ "<br><br> Regards, <br>gpmsISITMgr@gmail.com";
		mailServiceParams.setMailHtmlBody(mailHtmlBody);

		bondedItemIssue.setVariableOnExecution(processInstanceId,
				ApplicationConstants.APPROVE_MAIL_TO_EMPLOYEE_TASK,
				mailServiceParams);

		String comment = assetAssignModel.getAssetId()
				+ " is approved for the user "
				+ assetAssignModel.getUserCorpEmail();

		bondedItemIssue
				.setVariableOnTask(processInstanceId, "isApproved", true);
		bondedItemIssue
				.updateInfoOnTask(processInstanceId, comment, null, null);
		bondedItemIssue.completeTask(processInstanceId);
	}

	public void rejectAsset(AssetAssignModel assetAssignModel) {

		String processInstanceId = assetAssignModel
				.getUserAssetIssueProcessId();

		// Mail to ISIT User
		MailServiceParams mailServiceParams = new MailServiceParams();
		mailServiceParams.setMailUserId("gpmsISITMgr@gmail.com");
		mailServiceParams.setMailPassword("gpmsISITMgr777#$");
		mailServiceParams.setMailToAddress("gpmsisituser3@gmail.com");
		mailServiceParams.setMailSubject(assetAssignModel.getAssetId()
				+ " is rejected for the user "
				+ assetAssignModel.getUserCorpEmail());
		String mailHtmlBody = "Test email from gpmsISITMgr@gmail.com to gpmsisituser3@gmail.com"
				+ "<br><br> Regards, <br>gpmsISITMgr@gmail.com";
		mailServiceParams.setMailHtmlBody(mailHtmlBody);

		bondedItemIssue.setVariableOnExecution(processInstanceId,
				ApplicationConstants.REJECT_MAIL_TO_ISIT_USER_TASK,
				mailServiceParams);

		String comment = assetAssignModel.getAssetId()
				+ " is rejected for the user "
				+ assetAssignModel.getUserCorpEmail();

		bondedItemIssue.setVariableOnTask(processInstanceId, "isApproved",
				false);
		bondedItemIssue
				.updateInfoOnTask(processInstanceId, comment, null, null);
		bondedItemIssue.completeTask(processInstanceId);

	}

}
