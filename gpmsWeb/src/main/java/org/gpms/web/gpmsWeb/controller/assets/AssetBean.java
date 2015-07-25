/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.assets;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author narenda.kumar
 * 
 */
public class AssetBean {

	private String flowType;

	private String assetId;

	private String assetBarCode;

	@NotEmpty(message = "Asset Type cannot be be Empty")
	private String assetTypeId;

	@NotEmpty(message = "Asset Purchase Date cannot be be Empty")
	private String assetPurchaseDate;

	private String assetRemovalDate;

	private String assetStatus;

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
	 * @return the assetBarCode
	 */
	public String getAssetBarCode() {
		return assetBarCode;
	}

	/**
	 * @param assetBarCode
	 *            the assetBarCode to set
	 */
	public void setAssetBarCode(String assetBarCode) {
		this.assetBarCode = assetBarCode;
	}

	/**
	 * @return the assetTypeId
	 */
	public String getAssetTypeId() {
		return assetTypeId;
	}

	/**
	 * @param assetTypeId
	 *            the assetTypeId to set
	 */
	public void setAssetTypeId(String assetTypeId) {
		this.assetTypeId = assetTypeId;
	}

	/**
	 * @return the assetPurchaseDate
	 */
	public String getAssetPurchaseDate() {
		return assetPurchaseDate;
	}

	/**
	 * @param assetPurchaseDate
	 *            the assetPurchaseDate to set
	 */
	public void setAssetPurchaseDate(String assetPurchaseDate) {
		this.assetPurchaseDate = assetPurchaseDate;
	}

	/**
	 * @return the assetRemovalDate
	 */
	public String getAssetRemovalDate() {
		return assetRemovalDate;
	}

	/**
	 * @param assetRemovalDate
	 *            the assetRemovalDate to set
	 */
	public void setAssetRemovalDate(String assetRemovalDate) {
		this.assetRemovalDate = assetRemovalDate;
	}

	/**
	 * @return the assetStatus
	 */
	public String getAssetStatus() {
		return assetStatus;
	}

	/**
	 * @param assetStatus
	 *            the assetStatus to set
	 */
	public void setAssetStatus(String assetStatus) {
		this.assetStatus = assetStatus;
	}

}
