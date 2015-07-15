<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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

		<div id="header">
			<table width="100%">
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
											<td class="boxHeading" align="left"><b> <c:if
														test="${bondedAssetBean.flowType == 'bondedItemAssign'}">
														<label><spring:message
																code="pageHeader.issueBondedItem.text" /></label>
													</c:if> <c:if
														test="${bondedAssetBean.flowType == 'bondedItemReturn'}">
														<label>Return Bonded Item</label>
													</c:if> <c:if
														test="${bondedAssetBean.flowType == 'itemCorrection'}">
														<label>Bonded Item Correction</label>
													</c:if>
											</b></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="errorMessage" cssClass="error">
														Error : 
													</sf:label></b></td>
											<td><sf:errors path="errorMessage" cssClass="error" /></td>
										</tr>


										<tr>
											<td align="left" valign="top"><b><sf:label
														path="userAssetId">
														<spring:message
															code="label.issueBondedItem.userAssetId.text" />
													</sf:label></b></td>
											<c:if
												test="${bondedAssetBean.flowType == 'bondedItemAssign' || bondedAssetBean.flowType == 'itemCorrection'}">
												<td align="left"><sf:label path="userAssetId">${bondedAssetBean.userAssetId}</sf:label></td>
											</c:if>
											<c:if
												test="${bondedAssetBean.flowType == 'bondedItemReturn'}">
												<td><sf:input path="userAssetId" /><br /> <sf:errors
														path="userAssetId" cssClass="error" /></td>
												<td><input type="submit" name="getAssetAssignedData"
													value="Get Assigned Asset" /></td>
											</c:if>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="userCorpEmail">
														<spring:message
															code="label.issueBondedItem.userCorpEmail.text" />
													</sf:label></b></td>
											<c:if
												test="${bondedAssetBean.flowType == 'bondedItemAssign' || bondedAssetBean.flowType == 'itemCorrection'}">
												<td><sf:input path="userCorpEmail"
														disabled="${isDisabled}" /><br /> <sf:errors
														path="userCorpEmail" cssClass="error" /></td>
											</c:if>
											<c:if
												test="${bondedAssetBean.flowType == 'bondedItemReturn'}">
												<td><sf:label path="userCorpEmail">${bondedAssetBean.userCorpEmail}</sf:label></td>
											</c:if>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="assetId">
														<spring:message code="label.issueBondedItem.assetId.text" />
													</sf:label></b></td>
											<c:if
												test="${bondedAssetBean.flowType == 'bondedItemAssign' || bondedAssetBean.flowType == 'itemCorrection'}">
												<td><sf:input path="assetId" disabled="${isDisabled}" /><br />
													<sf:errors path="assetId" cssClass="error" /></td>
											</c:if>
											<c:if
												test="${bondedAssetBean.flowType == 'bondedItemReturn'}">
												<td><sf:label path="assetId">${bondedAssetBean.assetId}</sf:label></td>
											</c:if>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="userAssetIssueDate">
														<spring:message
															code="label.issueBondedItem.userAssetIssueDate.text" />
													</sf:label></b></td>
											<c:if
												test="${bondedAssetBean.flowType == 'bondedItemAssign' || bondedAssetBean.flowType == 'itemCorrection'}">
												<td><sf:input path="userAssetIssueDate"
														disabled="${isDisabled}" /><br /> <sf:errors
														path="userAssetIssueDate" cssClass="error" /></td>
											</c:if>
											<c:if
												test="${bondedAssetBean.flowType == 'bondedItemReturn'}">
												<td><sf:label path="userAssetIssueDate">${bondedAssetBean.userAssetIssueDate}</sf:label></td>
											</c:if>

										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="userAssetReturnDate">
														<spring:message
															code="label.issueBondedItem.userAssetReturnDate.text" />
													</sf:label></b></td>
											<c:if
												test="${bondedAssetBean.flowType == 'bondedItemAssign' || bondedAssetBean.flowType == 'itemCorrection'}">
												<td><sf:label path="userAssetReturnDate">${bondedAssetBean.userAssetReturnDate}</sf:label></td>
											</c:if>
											<c:if
												test="${bondedAssetBean.flowType == 'bondedItemReturn'}">
												<td><sf:input path="userAssetReturnDate"
														disabled="${isDisabled}" /><br /> <sf:errors
														path="userAssetReturnDate" cssClass="error" /></td>
											</c:if>

										</tr>
									</table>
									<c:if
										test="${bondedAssetBean.flowType == 'bondedItemAssign' || bondedAssetBean.flowType == 'itemCorrection'}">
										<c:if test="${returnFlow == 'false' }">
											<input type="submit" name="issueBondedAsset"
												value='<spring:message code="button.issueBondedItem.assignAsset.text"/>'
												${isDisabled eq "true" ? 'disabled'  : '' } />
										</c:if>
										<c:if test="${returnFlow == 'true' }">
											<input type="submit" name="returnBondedCorrection"
												value='<spring:message code="button.issueBondedItem.assignAsset.text"/>'
												${isDisabled eq "true" ? 'disabled'  : '' } />
										</c:if>
									</c:if>
									<c:if test="${bondedAssetBean.flowType == 'bondedItemReturn'}">
										<input type="submit" name="returnBondedAsset"
											value="Return Bonded Asset"
											${isDisabled eq "true" ? 'disabled'  : '' } />
									</c:if>


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