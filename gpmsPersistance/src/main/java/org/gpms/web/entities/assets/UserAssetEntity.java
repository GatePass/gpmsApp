/**
 * 
 */
package org.gpms.web.entities.assets;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author narenda.kumar
 * 
 */
@Entity(name = "GPMS_USER_ASSET")
public class UserAssetEntity {

	@Id
	@GeneratedValue
	@Column(name = "USER_ASSET_ID")
	private String userAssetId;

	@Column(name = "USER_CORP_EMAIL")
	private String userCorpEmail;

	@Column(name = "ASSET_ID")
	private String assetId;

	@Column(name = "USER_ASSET_ISSUE_DATE")
	private Date userAssetIssueDate;

	@Column(name = "USER_ASSET_RETURN_DATE")
	private Date userAssetReturnDate;

	@Column(name = "USER_ASSET_ISSUE_PROCESS_ID")
	private String userAssetIssueProcessId;

	@Column(name = "USER_ASSET_RETURN_PROCESS_ID")
	private String userAssetReturnProcessId;

	@Column(name = "CREATED_DATE")
	private Date createDate;

	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;

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

}
