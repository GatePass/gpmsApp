/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.userMgmt;

import java.util.List;

/**
 * @author narenda.kumar
 * 
 */
public class UserBean {

	private String userId;

	private String userName;

	private List<String> questionId;

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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
