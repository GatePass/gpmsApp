/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.util;

/**
 * @author narenda.kumar
 * 
 */
public interface ApplicationConstants {

	String ASSET_AVAILABLE_STATUS = "AVAILABLE";
	String ASSET_IN_PROCESS_STATUS = "INPROCESS";
	String ASSET_ASSIGNED_STATUS = "ASSIGNED";
	String ASSET_REMOVED_STATUS = "REMOVED";

	String GROUP_ADMIN = "ROLE_gpmsAdminGroup";
	String GROUP_ISIT_MANAGER = "ROLE_gpmsISITMgrGroup";
	String GROUP_SECUTITY = "ROLE_gpmsSecurityGroup";
	String GROUP_ISIT_USER = "ROLE_gpmsISITUserGroup";
	String GROUP_EMPLOYEE = "ROLE_gpmsEmployeeGroup";

	String BONDED_ITEM_CREATED_TASK = "bondedItemCreatedMailtask";
	String APPROVE_MAIL_TO_ISIT_MEMBER_TASK = "ApproveMailToISITMember";
	String APPROVE_MAIL_TO_SECURITY_TASK = "ApproveMailToSecurity";
	String APPROVE_MAIL_TO_EMPLOYEE_TASK = "ApproveMailToEmployee";
	String REJECT_MAIL_TO_ISIT_USER_TASK = "RejectMailToISITUser";
	String CORRECTION_MAIL_TO_MANAGER_TASK = "correctionMailToManager";
	String BONDED_ITEM_RETURN_TASK = "bondedItemReturnTask";

	String ISIT_MGR_APPROVAL_TASK = "ISITMgrApprovalTask";
	String BONDED_ITEM_CORRECTION = "bondedItemCorrection";

}
