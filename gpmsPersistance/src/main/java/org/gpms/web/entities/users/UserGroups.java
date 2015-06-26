/**
 * 
 */
package org.gpms.web.entities.users;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author narenda.kumar
 * 
 */
// @Entity(name = "GPMS_USER_GROUP")
public class UserGroups {

	@Id
	@Column(name = "GPMS_USER_GROUP_ID")
	private String userGroupId;

	@Column(name = "GPMS_USER_GROUP_NAME")
	private String userGroupName;

	@Column(name = "GPMS_USER_GROUP_DESC")
	private String userGroupDesc;

	/**
	 * @return the userGroupId
	 */
	public String getUserGroupId() {
		return userGroupId;
	}

	/**
	 * @param userGroupId
	 *            the userGroupId to set
	 */
	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}

	/**
	 * @return the userGroupName
	 */
	public String getUserGroupName() {
		return userGroupName;
	}

	/**
	 * @param userGroupName
	 *            the userGroupName to set
	 */
	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	/**
	 * @return the userGroupDesc
	 */
	public String getUserGroupDesc() {
		return userGroupDesc;
	}

	/**
	 * @param userGroupDesc
	 *            the userGroupDesc to set
	 */
	public void setUserGroupDesc(String userGroupDesc) {
		this.userGroupDesc = userGroupDesc;
	}

}
