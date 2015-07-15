<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
			<table style="margin-top: 20px">
				<tr>
					<td>
						<div align="center">
							<fieldset id="fieldSet">
								<sf:form action="newAssetType" modelAttribute="assetTypeBean">
									<table cellspacing="20px"
										style="min-height: 50%; min-width: 50%;">
										<tr>
											<td class="boxHeading" align="left"><b> <label><spring:message
															code="pageHeader.newAssetType.text" /></label>
											</b></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="assetTypeId">
														<spring:message code="label.newAssetType.assetTypeId.text" />
													</sf:label></b></td>
											<td align="left"><sf:label path="assetTypeId">${assetBean.assetTypeId}</sf:label></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="assetTypeName">
														<spring:message
															code="label.newAssetType.assetTypeName.text" />
													</sf:label></b></td>
											<td><sf:input path="assetTypeName"
													disabled="${isDisabled}" /><br /> <sf:errors
													path="assetTypeName" cssClass="error" /></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="assetTypeDesc">
														<spring:message
															code="label.newAssetType.assetTypeDescription.text" />
													</sf:label></b></td>
											<td><sf:textarea path="assetTypeDesc"
													disabled="${isDisabled}" /><br /> <sf:errors
													path="assetTypeDesc" cssClass="error" /></td>
										</tr>
									</table>
									<input type="submit" name="createAssetType"
										value='<spring:message code="button.newAssetType.createAssetType.text"/>'
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