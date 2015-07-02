/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.assets;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author narenda.kumar
 * 
 */
public class AssetTypeBean {

	private String assetTypeId;

	@NotEmpty(message = "Asset Type Name cannot be be Empty")
	private String assetTypeName;

	@NotEmpty(message = "Asset Type Description cannot be be Empty")
	@Size(min = 20, message = "Minimum 20 Character description is required")
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
