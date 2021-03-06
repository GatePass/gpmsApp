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
	private String assetTypeId;

	@Column(name = "ASSET_TYPE_NAME")
	private String assetTypeName;

	@Column(name = "ASSET_TYPE_DESC")
	private String assetTypeDesc;

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
