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
@Entity(name = "GPMS_ASSETS")
public class AssetsEntity {

	@Id
	@GeneratedValue
	@Column(name = "ASSET_ID")
	private String assetId;

	@Column(name = "ASSET_TYPE")
	private String assetType;

	@Column(name = "ASSET_PURCHASE_DATE")
	private Date assetPurchaseDate;

	@Column(name = "ASSET_REMOVAL_DATE")
	private Date assetRemovalDate;

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
	 * @return the assetType
	 */
	public String getAssetType() {
		return assetType;
	}

	/**
	 * @param assetType
	 *            the assetType to set
	 */
	public void setAssetType(String assetType) {
		this.assetType = assetType;
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

}
