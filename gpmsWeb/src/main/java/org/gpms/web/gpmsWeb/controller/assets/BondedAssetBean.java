/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.assets;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author narenda.kumar
 * 
 */
public class BondedAssetBean implements Serializable {

	private String flowType;

	private String userAssetId;

	@NotEmpty(message = "Employee to whom asset is to be assigned cannot be be Empty")
	@Email(message = "Please enter the corporate mail id")
	@Size(min = 6, message = "Wrong Email Id Entered")
	private String userCorpEmail;

	@NotEmpty(message = "Asset Id to be assigned cannot be be Empty")
	private String assetId;

	@NotEmpty(message = "Asset Issue Date cannot be empty for issuing asset")
	private String userAssetIssueDate;

	@NotEmpty(message = "Asset Return Date cannot be empty for returning asset")
	private String userAssetReturnDate;

	private String userAssetIssueProcessId;

	private String userAssetReturnProcessId;

	private String createDate;

	private String modifiedDate;

	private String assetAssignedComment;

	/**
	 * @return the flowType
	 */
	public String getFlowType() {
		return flowType;
	}

	/**
	 * @param flowType
	 *            the flowType to set
	 */
	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}

	/**
	 * @return the userAssetId
	 */
	public String getUserAssetId() {
		return userAssetId;
	}

	/**
	 * @param userAssetId
	 *            the userAssetId to set
	 */
	public void setUserAssetId(String userAssetId) {
		this.userAssetId = userAssetId;
	}

	/**
	 * @return the userCorpEmail
	 */
	public String getUserCorpEmail() {
		return userCorpEmail;
	}

	/**
	 * @param userCorpEmail
	 *            the userCorpEmail to set
	 */
	public void setUserCorpEmail(String userCorpEmail) {
		this.userCorpEmail = userCorpEmail;
	}

	/**
	 * @return the assetId
	 */
	public String getAssetId() {
		return assetId;
	}

	/**
	 * @param assetId
	 *            the assetId to set
	 */
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	/**
	 * @return the userAssetIssueProcessId
	 */
	public String getUserAssetIssueProcessId() {
		return userAssetIssueProcessId;
	}

	/**
	 * @param userAssetIssueProcessId
	 *            the userAssetIssueProcessId to set
	 */
	public void setUserAssetIssueProcessId(String userAssetIssueProcessId) {
		this.userAssetIssueProcessId = userAssetIssueProcessId;
	}

	/**
	 * @return the userAssetReturnProcessId
	 */
	public String getUserAssetReturnProcessId() {
		return userAssetReturnProcessId;
	}

	/**
	 * @param userAssetReturnProcessId
	 *            the userAssetReturnProcessId to set
	 */
	public void setUserAssetReturnProcessId(String userAssetReturnProcessId) {
		this.userAssetReturnProcessId = userAssetReturnProcessId;
	}

	/**
	 * @return the userAssetIssueDate
	 */
	public String getUserAssetIssueDate() {
		return userAssetIssueDate;
	}

	/**
	 * @param userAssetIssueDate
	 *            the userAssetIssueDate to set
	 */
	public void setUserAssetIssueDate(String userAssetIssueDate) {
		this.userAssetIssueDate = userAssetIssueDate;
	}

	/**
	 * @return the userAssetReturnDate
	 */
	public String getUserAssetReturnDate() {
		return userAssetReturnDate;
	}

	/**
	 * @param userAssetReturnDate
	 *            the userAssetReturnDate to set
	 */
	public void setUserAssetReturnDate(String userAssetReturnDate) {
		this.userAssetReturnDate = userAssetReturnDate;
	}

	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public String getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate
	 *            the modifiedDate to set
	 */
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the assetAssignedComment
	 */
	public String getAssetAssignedComment() {
		return assetAssignedComment;
	}

	/**
	 * @param assetAssignedComment
	 *            the assetAssignedComment to set
	 */
	public void setAssetAssignedComment(String assetAssignedComment) {
		this.assetAssignedComment = assetAssignedComment;
	}

	public String toString() {
		return "\nflowType " + flowType + "\nuserAssetId " + userAssetId
				+ "\nuserCorpEmail " + userCorpEmail + "\nassetId " + assetId
				+ "\nuserAssetIssueDate " + userAssetIssueDate
				+ "\nuserAssetReturnDate " + userAssetReturnDate
				+ "\nuserAssetIssueProcessId " + userAssetIssueProcessId
				+ "\nuserAssetReturnProcessId " + userAssetReturnProcessId
				+ "\ncreateDate " + createDate + "\nmodifiedDate "
				+ modifiedDate + "\nassetAssignedComment "
				+ assetAssignedComment;
	}

}
