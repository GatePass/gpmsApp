/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.util;

/**
 * @author narenda.kumar
 * 
 */
public interface ApplicationConstants {

	String ASSET_ASSIGNED_STATUS = "ASSIGNED";
	String ASSET_AVAILABLE_STATUS = "AVAILABLE";
	String ASSET_REMOVED_STATUS = "REMOVED";

	String GROUP_ADMIN = "gpmsAdminGroup";
	String GROUP_ISIT_MANAGER = "gpmsISITMgrGroup";
	String GROUP_SECUTITY = "gpmsSecurityGroup";
	String GROUP_ISIT_USER = "gpmsISITUserGroup";
	String GROUP_EMPLOYEE = "gpmsEmployeeGroup";

	String BONDED_ITEM_CREATED_TASK = "bondedItemCreatedMailtask";
	String APPROVE_MAIL_TO_ISIT_MEMBER_TASK = "ApproveMailTOISITMember";
	String APPROVE_MAIL_TO_SECURITY_TASK = "ApproveMailToSecurity";
	String APPROVE_MAIL_TO_EMPLOYEE_TASK = "ApproveMailToEmployee";
	String REJECT_MAIL_TO_ISIT_USER_TASK = "RejectMailToISITUser";
	String CORRECTION_MAIL_TO_MANAGER_TASK = "correctionMailtoManager";
	String BONDED_ITEM_RETURN_TASK = "bondedItemReturnTask";

}
