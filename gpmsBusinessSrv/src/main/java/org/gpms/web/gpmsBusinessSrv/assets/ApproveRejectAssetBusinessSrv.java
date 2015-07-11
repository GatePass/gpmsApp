package org.gpms.web.gpmsBusinessSrv.assets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.gpms.web.common.BondedItemManagement;
import org.gpms.web.entities.assets.UserAssetEntity;
import org.gpms.web.gpmsBusinessSrv.util.ApplicationConstants;
import org.gpms.web.mail.MailServiceParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApproveRejectAssetBusinessSrv {

	@Autowired
	BondedItemManagement bondedItemManagement;

	private static final Logger logger = Logger
			.getLogger(ApproveRejectAssetBusinessSrv.class);

	public void approveAsset(AssetAssignModel assetAssignModel) {

		String processInstanceId = assetAssignModel
				.getUserAssetIssueProcessId();

		if (logger.isDebugEnabled()) {
			logger.debug("processInstanceId : " + processInstanceId);
		}

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

		bondedItemManagement.setVariableOnExecution(processInstanceId,
				ApplicationConstants.APPROVE_MAIL_TO_ISIT_MEMBER_TASK,
				mailServiceParams);

		if (logger.isDebugEnabled()) {
			logger.debug("Set Variable : APPROVE_MAIL_TO_ISIT_MEMBER_TASK");
		}

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

		bondedItemManagement.setVariableOnExecution(processInstanceId,
				ApplicationConstants.APPROVE_MAIL_TO_SECURITY_TASK,
				mailServiceParams);

		if (logger.isDebugEnabled()) {
			logger.debug("Set Variable : APPROVE_MAIL_TO_SECURITY_TASK");
		}

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

		bondedItemManagement.setVariableOnExecution(processInstanceId,
				ApplicationConstants.APPROVE_MAIL_TO_EMPLOYEE_TASK,
				mailServiceParams);

		if (logger.isDebugEnabled()) {
			logger.debug("Set Variable : APPROVE_MAIL_TO_EMPLOYEE_TASK");
		}

		String comment = assetAssignModel.getAssetId()
				+ " is approved for the user "
				+ assetAssignModel.getUserCorpEmail();

		bondedItemManagement.setVariableOnTask(processInstanceId, "isApproved",
				true);
		bondedItemManagement.updateInfoOnTask(processInstanceId, comment, null,
				null);
		bondedItemManagement.completeTask(processInstanceId);

		if (logger.isDebugEnabled()) {
			logger.debug("Approval has approved the task for asset to be issued");
		}

	}

	public void rejectAsset(AssetAssignModel assetAssignModel) {

		String processInstanceId = assetAssignModel
				.getUserAssetIssueProcessId();

		if (logger.isDebugEnabled()) {
			logger.debug("processInstanceId : " + processInstanceId);
		}

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

		bondedItemManagement.setVariableOnExecution(processInstanceId,
				ApplicationConstants.REJECT_MAIL_TO_ISIT_USER_TASK,
				mailServiceParams);

		if (logger.isDebugEnabled()) {
			logger.debug("Set Variable : REJECT_MAIL_TO_ISIT_USER_TASK");
		}

		String comment = assetAssignModel.getAssetId()
				+ " is rejected for the user "
				+ assetAssignModel.getUserCorpEmail();

		bondedItemManagement.setVariableOnTask(processInstanceId, "isApproved",
				false);
		bondedItemManagement.updateInfoOnTask(processInstanceId, comment, null,
				null);
		bondedItemManagement.completeTask(processInstanceId);

		if (logger.isDebugEnabled()) {
			logger.debug("Approval has rejected the task for asset to be issued");
		}

	}

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

		if (logger.isDebugEnabled()) {
			logger.debug("assetAssignModelLst : " + assetAssignModelLst.size());
		}

		return assetAssignModelLst;
	}

}
