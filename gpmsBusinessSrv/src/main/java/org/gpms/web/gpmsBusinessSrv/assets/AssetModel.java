/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.assets;

/**
 * @author narenda.kumar
 * 
 */
public class AssetModel {

	private String assetId;

	private String assetBarCode;

	private String assetTypeId;

	private String assetPurchaseDate;

	private String assetRemovalDate;

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

}
