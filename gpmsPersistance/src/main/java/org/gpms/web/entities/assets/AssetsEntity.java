/**
 * 
 */
package org.gpms.web.entities.assets;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

	@Column(name = "ASSET_BAR_CODE")
	private String assetBarCode;

	@Column(name = "ASSET_PURCHASE_DATE")
	private Date assetPurchaseDate;

	@Column(name = "ASSET_REMOVAL_DATE")
	private Date assetRemovalDate;

	@Column(name = "ASSET_STATUS", columnDefinition = "DEFAULT 'AVAILABLE'")
	private String assetStatus;

	@Column(name = "ASSET_TYPE")
	private String assetTypeId;

	@ManyToOne
	@JoinColumn(name = "ASSET_TYPE", insertable = false, updatable = false)
	private AssetTypesEntity assetTypesEntity;

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
	 * @return the assetTypesEntity
	 */
	public AssetTypesEntity getAssetTypesEntity() {
		return assetTypesEntity;
	}

	/**
	 * @param assetTypesEntity
	 *            the assetTypesEntity to set
	 */
	public void setAssetTypesEntity(AssetTypesEntity assetTypesEntity) {
		this.assetTypesEntity = assetTypesEntity;
	}

	public String toString() {
		return "\nassetId " + assetId + "\nassetBarCode " + assetBarCode
				+ "\nassetTypeId " + assetTypeId + "\nassetPurchaseDate "
				+ assetPurchaseDate + "\nassetRemovalDate " + assetRemovalDate
				+ "\nassetStatus " + assetStatus;
	}

}
