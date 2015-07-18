<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
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
					<td><%@include file="gpmsHeader.jsp"%></td>
				</tr>
			</table>
		</div>
		<!-- #header -->

		<div id="content" align="center">
			<table style="margin-top: 30px">
				<tr>
					<td>
						<div align="center">
							<fieldset id="fieldSet"
								style="border-width: 2px; background-color: lightblue; width: 800px;">
								<sf:form action="navigation">
									<table cellspacing="10px">
										<security:authorize
											access="hasAnyRole('ROLE_gpmsAdminGroup', 'ROLE_gpmsISITMgrGroup')">
											<tr>
												<td class="boxHeading" align="left" colspan="2"><b>
														<label><spring:message
																code="subHeader.userAccessMgmt.text" /></label>
												</b></td>
											</tr>
											<tr>
												<td align="left" colspan="2"><label><spring:message
															code="label.userAccessMgmt.createModifyUser.text" /></label></td>
												<td><input type="submit" name="createUser"
													value='<spring:message code="button.userAccessMgmt.createuser.text"/>' /></td>
												<td><input type="submit" name="modifyUser"
													value='<spring:message code="button.userAccessMgmt.modifyUser.text"/>' /></td>
											</tr>

											<tr>
												<td align="left" colspan="2"><label><spring:message
															code="label.userAccessMgmt.deleteUser.text" /></label></td>
												<td><input type="submit" name="deleteUser"
													value='<spring:message code="button.userAccessMgmt.deleteUser.text"/>' /></td>
											</tr>
										</security:authorize>
										<security:authorize
											access="hasAnyRole('ROLE_gpmsISITUserGroup', 'ROLE_gpmsISITMgrGroup')">
											<br>
											
												<tr>
													<td class="boxHeading" align="left" colspan="2"><b>
															<label><spring:message
																	code="subHeader.assetsMgmt.text" /></label>
													</b></td>
												</tr>
											<security:authorize
												access="hasAnyRole('ROLE_gpmsISITMgrGroup')">	
												<tr>
													<td align="left" colspan="2"><label><spring:message
																code="label.userAccessMgmt.createModifyAssetType.text" /></label>
													</td>
													<td><input type="submit" name="createAssetType"
														value='<spring:message code="button.userAccessMgmt.createAssetType.text"/>' /></td>
													<td><input type="submit" name="modifyAssetType"
														value='<spring:message code="button.userAccessMgmt.modifyAssetType.text"/>' /></td>
												</tr>
											</security:authorize>
											<tr>
												<td align="left" colspan="2"><label><spring:message
															code="label.userAccessMgmt.createModifyBondedAsset.text" /></label>
												</td>
												<td><input type="submit" name="createAsset"
													value='<spring:message code="button.userAccessMgmt.createAsset.text"/>' /></td>
												<td><input type="submit" name="modifyAsset"
													value='<spring:message code="button.userAccessMgmt.modifyAsset.text"/>' /></td>
											</tr>
											<tr>
												<td align="left" colspan="2"><label><spring:message
															code="label.userAccessMgmt.deleteBondedAsset.text" /></label></td>
												<td><input type="submit" name="deleteAsset"
													value='<spring:message code="button.userAccessMgmt.deleteAsset.text"/>' /></td>
											</tr>
											<security:authorize access="hasAnyRole('ROLE_gpmsISITUserGroup')">
											<tr>
												<td align="left" colspan="2"><label><spring:message
															code="label.userAccessMgmt.assignReturnBondedItem.text" /></label>
												</td>
												<td><input type="submit" name="bondedItemAssign"
													value='<spring:message code="button.userAccessMgmt.assignAsset.text"/>' /></td>
												<td><input type="submit" name="bondedItemReturn"
													value='<spring:message code="button.userAccessMgmt.returnAsset.text"/>' /></td>
											</tr>
											</security:authorize>
											<tr>
												<td align="left" colspan="2"><label><spring:message
															code="label.userAccessMgmt.issueBondedItemActivity.text" /></label>
												</td>
												<security:authorize access="hasAnyRole('ROLE_gpmsISITMgrGroup')">
												<td><input type="submit" name="approveRejectAsset"
													value='<spring:message code="button.userAccessMgmt.approveReject.text"/>' /></td>
												</security:authorize>	
												<td><input type="submit" name="modifyBondedAsset"
													value='<spring:message code="button.userAccessMgmt.modifyBondedAsset.text"/>' /></td>
											</tr>
											<br>
										</security:authorize>
										<tr>
											<td class="boxHeading" align="left" colspan="2"><b>
													<label><spring:message
															code="subHeader.reporting.text" /></label>
											</b></td>
										</tr>
										<tr>
											<td align="left" colspan="2"><label>Reset
													Password</label>
											<td><input type="submit" name="passwordReset"
												value='<spring:message code="button.loginPage.resetPassword.text"/>' /></td>
										</tr>
									</table>
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
					<td><%@include file="gpmsFooter.jsp"%></td>
				</tr>
			</table>
		</div>
		<!-- #footer -->




	</div>
</body>
</html>