/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.assets;

import java.util.Date;

/**
 * @author narenda.kumar
 * 
 */
public class AssetModel {

	private String assetId;

	private String assetBarCode;

	private String assetTypeId;

	private String assetTypeName;

	private Date assetPurchaseDate;

	private Date assetRemovalDate;

	private String assetStatus;

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
	public Date getAssetPurchaseDate() {
		return assetPurchaseDate;
	}

	/**
	 * @param assetPurchaseDate
	 *            the assetPurchaseDate to set
	 */
	public void setAssetPurchaseDate(Date assetPurchaseDate) {
		this.assetPurchaseDate = assetPurchaseDate;
	}

	/**
	 * @return the assetRemovalDate
	 */
	public Date getAssetRemovalDate() {
		return assetRemovalDate;
	}

	/**
	 * @param assetRemovalDate
	 *            the assetRemovalDate to set
	 */
	public void setAssetRemovalDate(Date assetRemovalDate) {
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

	/**
	 * @return the assetTypeName
	 */
	public String getAssetTypeName() {
		return assetTypeName;
	}

	/**
	 * @param assetTypeName
	 *            the assetTypeName to set
	 */
	public void setAssetTypeName(String assetTypeName) {
		this.assetTypeName = assetTypeName;
	}

	public String toString() {
		return "\nassetId " + assetId + "\nassetBarCode " + assetBarCode
				+ "\nassetTypeId " + assetTypeId + "\nassetPurchaseDate "
				+ assetPurchaseDate + "\nassetRemovalDate " + assetRemovalDate
				+ "\nassetStatus " + assetStatus;
	}

}
