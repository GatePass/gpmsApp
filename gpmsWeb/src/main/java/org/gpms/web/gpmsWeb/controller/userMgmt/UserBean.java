/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.userMgmt;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author narenda.kumar
 * 
 */
public class UserBean {

	private String userId;

	@NotEmpty(message = "User First Name cannot be Empty")
	private String userFirstName;

	private String userLastName;

	@NotEmpty(message = "Corp Email Id cannot be Empty")
	@Email(message = "Not a valid Email Id")
	@Size(min = 6, message = "Wrong Email Id Entered")
	private String corpEmailId;

	@NotEmpty(message = "Personal Email Id cannot be Empty")
	@Email(message = "Not a valid Email Id")
	@Size(min = 6, message = "Wrong Email Id Entered")
	private String personalEmailId;

	@NotEmpty(message = "User Group cannot be Empty")
	private String userGroupId;

	@NotEmpty(message = "Question cannot be Empty")
	private List<String> questionId;

	@NotEmpty(message = "Secret answer cannot be Empty")
	private String secretQuesAnsId;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userFirstName
	 */
	public String getUserFirstName() {
		return userFirstName;
	}

	/**
	 * @param userFirstName
	 *            the userFirstName to set
	 */
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	/**
	 * @return the userLastName
	 */
	public String getUserLastName() {
		return userLastName;
	}

	/**
	 * @param userLastName
	 *            the userLastName to set
	 */
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	/**
	 * @return the corpEmailId
	 */
	public String getCorpEmailId() {
		return corpEmailId;
	}

	/**
	 * @param corpEmailId
	 *            the corpEmailId to set
	 */
	public void setCorpEmailId(String corpEmailId) {
		this.corpEmailId = corpEmailId;
	}

	/**
	 * @return the personalEmailId
	 */
	public String getPersonalEmailId() {
		return personalEmailId;
	}

	/**
	 * @param personalEmailId
	 *            the personalEmailId to set
	 */
	public void setPersonalEmailId(String personalEmailId) {
		this.personalEmailId = personalEmailId;
	}

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
	 * @return the questionId
	 */
	public List<String> getQuestionId() {
		return questionId;
	}

	/**
	 * @param questionId
	 *            the questionId to set
	 */
	public void setQuestionId(List<String> questionId) {
		this.questionId = questionId;
	}

	/**
	 * @return the secretQuesAnsId
	 */
	public String getSecretQuesAnsId() {
		return secretQuesAnsId;
	}

	/**
	 * @param secretQuesAnsId
	 *            the secretQuesAnsId to set
	 */
	public void setSecretQuesAnsId(String secretQuesAnsId) {
		this.secretQuesAnsId = secretQuesAnsId;
	}

}
