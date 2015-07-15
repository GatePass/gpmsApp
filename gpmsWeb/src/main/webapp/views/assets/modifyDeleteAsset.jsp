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
								<sf:form action="modifyDeleteAsset" modelAttribute="assetBean">
									<table cellspacing="20px"
										style="min-height: 50%; min-width: 50%;">
										<tr>
											<td class="boxHeading" align="left"><b> <c:if
														test="${flowType == 'modifyAsset'}">
														<label>Modify Asset</label>
													</c:if> <c:if test="${flowType == 'deleteAsset'}">
														<label>Delete Asset</label>
													</c:if>
											</b></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="assetId">
														<spring:message code="label.newAsset.assetId.text" />
													</sf:label></b></td>
											<td align="left"><sf:input path="assetId" /></td>
											<td><input type="submit" name="getAssetData"
												value="Get Data" /></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="assetBarCode">
														<spring:message code="label.newAsset.assetBarCode.text" />
													</sf:label></b></td>
											<td><sf:label path="assetBarCode">${assetBean.assetBarCode}</sf:label>
												<input id="assetBarCode" type="hidden" name="assetBarCode"
												value="${assetBean.assetBarCode}"></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="assetTypeId">
														<spring:message
															code="label.newAsset.selectTheAssetType.text" />
													</sf:label></b></td>
											<td><sf:label path="assetTypeId">${assetBean.assetTypeId}</sf:label>
												<input id="assetTypeId" type="hidden" name="assetTypeId"
												value="${assetBean.assetTypeId}"></td>
										</tr>
										<c:if test="${flowType == 'modifyAsset'}">
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

										</c:if>
										<c:if test="${flowType == 'deleteAsset'}">
											<tr>
												<td align="left" valign="top"><b><sf:label
															path="assetPurchaseDate">
															<spring:message
																code="label.newAsset.assetPurchaseDate.text" />
														</sf:label></b></td>
												<td><sf:label path="assetPurchaseDate">${assetBean.assetPurchaseDate}</sf:label></td>
											</tr>
											<tr>
												<td align="left" valign="top"><b><sf:label
															path="assetRemovalDate">
															<spring:message
																code="label.newAsset.assetRemovalDate.text" />
														</sf:label></b></td>
												<td><sf:label path="assetRemovalDate">${assetBean.assetRemovalDate}</sf:label></td>
											</tr>
											<tr>
												<td align="left" valign="top"><b><sf:label
															path="assetStatus">Asset Status</sf:label></b></td>
												<td><sf:label path="assetStatus">${assetBean.assetStatus}</sf:label></td>
											</tr>
										</c:if>

									</table>

									<c:if test="${flowType == 'modifyAsset'}">
										<input type="submit" name="modifyAsset" value="Modify Asset"
											${isDisabled eq "true" ? 'disabled'  : '' } />
									</c:if>
									<c:if test="${flowType == 'deleteAsset'}">
										<input type="submit" name="deleteAsset" value="Delete Asset"
											${isDisabled eq "true" ? 'disabled'  : '' }
											onclick="return confirm('Do you really want to delete this asset?');" />
									</c:if>
									<input id="flowType" type="hidden" name="flowType"
										value="${assetBean.flowType}">
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