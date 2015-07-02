<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="resources/gpmsStyle.css" />
<title>Gate Pass Management System</title>
</head>
<body>
	<div id="wrapper">

		<div id="header" align="center">
			<table>
				<tr>
					<td><%@include file="../common/gpmsHeader.jsp"%></td>
				</tr>
			</table>
		</div>
		<!-- #header -->

		<div id="content" align="center">
			<table style="margin-top: 20px">
				<tr>
					<td>
						<div align="center">
							<fieldset id="fieldSet">
								<sf:form action="newUser" modelAttribute="userBean">
									<table cellspacing="20px"
										style="min-height: 50%; min-width: 50%;">
										<tr>
											<td class="boxHeading" align="left"><b> <label>New
														User</label>
											</b></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label path="userId">User Id :</sf:label></b></td>
											<td align="left"><sf:label path="userId">${userBean.userId}</sf:label></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label path="userFirstName">User First Name :</sf:label></b></td>
											<td><sf:input path="userFirstName" disabled="${isDisabled}"/><br /> <sf:errors
													path="userFirstName" cssClass="error" /></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label path="userLastName">User Last Name:</sf:label></b></td>
											<td><sf:input path="userLastName" disabled="${isDisabled}"/><br /> <sf:errors
													path="userLastName" cssClass="error" /></td>
										</tr>
										
										<tr>
											<td align="left" valign="top"><b><sf:label path="corpEmailId">Corporate Email Id :</sf:label></b></td>
											<td><sf:input path="corpEmailId"   disabled="${isDisabled}" /><br /> <sf:errors
													path="corpEmailId" cssClass="error" /></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label path="personalEmailId">Personal Email Id :</sf:label></b></td>
											<td><sf:input path="personalEmailId" disabled="${isDisabled}"/><br /> <sf:errors
													path="personalEmailId" cssClass="error" /></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label path="userGroupId">Select the User Group :</sf:label></b></td>
											<td><sf:select path="userGroupId" multiple="false" disabled="${isDisabled}" >
													<sf:option value="" label=""></sf:option>
													<c:forEach items="${userGroupModel}"
														var="userGroupModel">
														<sf:option
															value="${userGroupModel.userGroupId}">${userGroupModel.userGroupName}</sf:option>
													</c:forEach>
												</sf:select>
												<br /> 
											<sf:errors path="userGroupId" cssClass="error" /></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label path="questionId">Select the secret question :</sf:label></b></td>
											<td><sf:select path="questionId" multiple="false" disabled="${isDisabled}">
													<sf:option value="" label=""></sf:option>
													<c:forEach items="${securityQuestionsModel}"
														var="securityQuestionsModel">
														<sf:option
															value="${securityQuestionsModel.securityQuestionId}">${securityQuestionsModel.securityQuestion}</sf:option>
													</c:forEach>
												</sf:select>
												<br /> 
											<sf:errors path="questionId" cssClass="error" /></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label path="secretQuesAnsId">Secret question answer :</sf:label></b></td>
											<td><sf:input path="secretQuesAnsId" disabled="${isDisabled}"/><br /> 
											<sf:errors path="secretQuesAnsId" cssClass="error" /></td>
										</tr>
									</table>
								
									<input type="submit" name="createUser" value="Create User" ${isDisabled eq "true" ? 'disabled'  : '' }/>
								
								</sf:form>
							</fieldset>
						</div>
					</td>
				</tr>

			</table>
		</div>
		<!-- #content -->

		<div id="footer" align="center">
			<table>

				<tr>
					<td><%@include file="../common/gpmsFooter.jsp"%></td>
				</tr>
			</table>
		</div>
		<!-- #footer -->
	</div>
</body>
</html>