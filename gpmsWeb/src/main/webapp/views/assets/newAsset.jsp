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
								<sf:form action="newAsset" modelAttribute="assetBean">
									<table cellspacing="20px"
										style="min-height: 50%; min-width: 50%;">
										<tr>
											<td class="boxHeading" align="left"><b> <label><spring:message
															code="pageHeader.newAsset.text" /></label>
											</b></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="assetId">
														<spring:message code="label.newAsset.assetId.text" />
													</sf:label></b></td>
											<td align="left"><sf:label path="assetId">${assetBean.assetId}</sf:label></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="assetBarCode">
														<spring:message code="label.newAsset.assetBarCode.text" />
													</sf:label></b></td>
											<td><sf:input path="assetBarCode"
													disabled="${isDisabled}" /><br /> <sf:errors
													path="assetBarCode" cssClass="error" /></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="assetTypeId">
														<spring:message
															code="label.newAsset.selectTheAssetType.text" />
													</sf:label></b></td>
											<td><sf:select path="assetTypeId" multiple="false"
													disabled="${isDisabled}">
													<sf:option value="" label=""></sf:option>
													<c:forEach items="${assetTypeModelLst}"
														var="assetTypeModelLst">
														<sf:option value="${assetTypeModelLst.assetTypeId}">${assetTypeModelLst.assetTypeName}</sf:option>
													</c:forEach>
												</sf:select> <br /> <sf:errors path="assetTypeId" cssClass="error" /></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="assetPurchaseDate">
														<spring:message
															code="label.newAsset.assetPurchaseDate.text" />
													</sf:label></b></td>
											<td><sf:input path="assetPurchaseDate"
													disabled="${isDisabled}" /><br /> <sf:errors
													path="assetPurchaseDate" cssClass="error" /></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="assetRemovalDate">
														<spring:message
															code="label.newAsset.assetRemovalDate.text" />
													</sf:label></b></td>
											<td><sf:input path="assetRemovalDate"
													disabled="${isDisabled}" /><br /> <sf:errors
													path="assetRemovalDate" cssClass="error" /></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="assetStatus">Asset Status</sf:label></b></td>
											<td><sf:select path="assetStatus" multiple="false">
													<sf:option value="ASSIGNED" label="ASSIGNED"></sf:option>
													<sf:option value="AVAILABLE" label="AVAILABLE"></sf:option>
													<sf:option value="REMOVED" label="REMOVED"></sf:option>
												</sf:select> <br /> <sf:errors path="assetStatus" cssClass="error" /></td>
										</tr>

									</table>
									<input type="submit" name="createAsset"
										value='<spring:message code="button.newAsset.createAsset.text"/>'
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