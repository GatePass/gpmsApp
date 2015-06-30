/**
 * 
 */
package org.gpms.web.entities.assets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author narenda.kumar
 * 
 */
@Entity(name = "GPMS_ASSET_TYPES")
public class AssetTypesEntity {

	@Id
	@GeneratedValue
	@Column(name = "ASSET_TYPE_ID")
	private String assetId;

	@Column(name = "ASSET_TYPE_NAME")
	private String assetName;

	@Column(name = "ASSET_TYPE_DESC")
	private String assetTypeDesc;

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
	 * @return the assetName
	 */
	public String getAssetName() {
		return assetName;
	}

	/**
	 * @param assetName
	 *            the assetName to set
	 */
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	/**
	 * @return the assetTypeDesc
	 */
	public String getAssetTypeDesc() {
		return assetTypeDesc;
	}

	/**
	 * @param assetTypeDesc
	 *            the assetTypeDesc to set
	 */
	public void setAssetTypeDesc(String assetTypeDesc) {
		this.assetTypeDesc = assetTypeDesc;
	}

}
