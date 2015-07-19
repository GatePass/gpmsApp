package org.gpms.web.gpmsBusinessSrv.assets;

import java.util.Date;

/**
 * 
 * @author narenda.kumar
 * 
 */
public class AssetAssignModel {

	private String userAssetId;

	private String userCorpEmail;

	private String assetId;

	private Date userAssetIssueDate;

	private Date userAssetReturnDate;

	private String userAssetIssueProcessId;

	private String userAssetReturnProcessId;

	private Date createDate;

	private Date modifiedDate;

	private String assetAssignComments;

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
	 * @return the assetAssignComments
	 */
	public String getAssetAssignComments() {
		return assetAssignComments;
	}

	/**
	 * @param assetAssignComments
	 *            the assetAssignComments to set
	 */
	public void setAssetAssignComments(String assetAssignComments) {
		this.assetAssignComments = assetAssignComments;
	}

	/**
	 * @return the userAssetIssueDate
	 */
	public Date getUserAssetIssueDate() {
		return userAssetIssueDate;
	}

	/**
	 * @param userAssetIssueDate
	 *            the userAssetIssueDate to set
	 */
	public void setUserAssetIssueDate(Date userAssetIssueDate) {
		this.userAssetIssueDate = userAssetIssueDate;
	}

	/**
	 * @return the userAssetReturnDate
	 */
	public Date getUserAssetReturnDate() {
		return userAssetReturnDate;
	}

	/**
	 * @param userAssetReturnDate
	 *            the userAssetReturnDate to set
	 */
	public void setUserAssetReturnDate(Date userAssetReturnDate) {
		this.userAssetReturnDate = userAssetReturnDate;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate
	 *            the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String toString() {
		return "\nuserAssetId " + userAssetId + "\nuserCorpEmail "
				+ userCorpEmail + "\nassetId " + assetId
				+ "\nuserAssetIssueDate " + userAssetIssueDate
				+ "\nuserAssetReturnDate " + userAssetReturnDate
				+ "\nuserAssetIssueProcessId " + userAssetIssueProcessId
				+ "\nuserAssetReturnProcessId " + userAssetReturnProcessId
				+ "\ncreateDate " + createDate + "\nmodifiedDate "
				+ modifiedDate + "\nassetAssignedComment "
				+ assetAssignComments;
	}

}
