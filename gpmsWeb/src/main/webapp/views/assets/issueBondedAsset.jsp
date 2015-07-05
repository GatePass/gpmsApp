<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
								<sf:form action="issueBondedAsset"
									modelAttribute="bondedAssetBean">
									<table cellspacing="20px"
										style="min-height: 50%; min-width: 50%;">
										<tr>
											<td class="boxHeading" align="left"><b> <label><spring:message
															code="pageHeader.issueBondedItem.text" /></label>
											</b></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="userAssetId">
														<spring:message
															code="label.issueBondedItem.userAssetId.text" />
													</sf:label></b></td>
											<td align="left"><sf:label path="userAssetId">${bondedAssetBean.userAssetId}</sf:label></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="userCorpEmail">
														<spring:message
															code="label.issueBondedItem.userCorpEmail.text" />
													</sf:label></b></td>
											<td><sf:input path="userCorpEmail"
													disabled="${isDisabled}" /><br /> <sf:errors
													path="userCorpEmail" cssClass="error" /></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="assetId">
														<spring:message code="label.issueBondedItem.assetId.text" />
													</sf:label></b></td>
											<td><sf:input path="assetId" disabled="${isDisabled}" /><br />
												<sf:errors path="assetId" cssClass="error" /></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="userAssetIssueDate">
														<spring:message
															code="label.issueBondedItem.userAssetIssueDate.text" />
													</sf:label></b></td>
											<td><sf:input path="userAssetIssueDate"
													disabled="${isDisabled}" /><br /> <sf:errors
													path="userAssetIssueDate" cssClass="error" /></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="userAssetReturnDate">
														<spring:message
															code="label.issueBondedItem.userAssetReturnDate.text" />
													</sf:label></b></td>
											<td><sf:input path="userAssetReturnDate"
													disabled="${isDisabled}" /><br /> <sf:errors
													path="userAssetReturnDate" cssClass="error" /></td>
										</tr>
									</table>
									<input type="submit" name="createAsset"
										value='<spring:message code="button.AssignAsset.text"/>'
										${isDisabled eq "true" ? 'disabled'  : '' } />
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