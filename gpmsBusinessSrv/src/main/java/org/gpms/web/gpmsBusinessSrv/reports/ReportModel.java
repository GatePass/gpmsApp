package org.gpms.web.gpmsBusinessSrv.reports;

import java.util.Date;

/**
 * 
 * @author narenda.kumar
 * 
 */
public class ReportModel {

	private String userAssetId;

	private String userCorpEmail;

	private String assetId;

	private Date userAssetIssueDate;

	private Date userAssetReturnDate;

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

	public String toString() {
		return "\nuserAssetId " + userAssetId + "\nuserCorpEmail "
				+ userCorpEmail + "\nassetId " + assetId
				+ "\nuserAssetIssueDate " + userAssetIssueDate
				+ "\nuserAssetReturnDate " + userAssetReturnDate
				+ "\nassetAssignedComment " + assetAssignComments;
	}

}
