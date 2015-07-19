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
								<sf:form id="employeeReportForm" action="employeeGatePassReport" modelAttribute="reportBean">
									<table cellspacing="20px"
										style="min-height: 50%; min-width: 50%;">
										<tr>
											<td class="boxHeading" align="left" colspan="5"><b> Employee Assigned Gate passes</label>
											</b></td>
										</tr>
										<c:if test="${reportViewType == 'managementReportView'}">
											<tr>
												<td>Filter by Employee</td>
												<td><sf:input path="userCorpEmail" /><br /></td>
												<td><input type="submit" name="getEmployeeData"
												value="Get Employee Data" /></td>
											</tr>
											<tr>
												<td><sf:errors path="userCorpEmail" cssClass="error" /></td>
											</tr>
										</c:if>
										
										<tr>
											<td colspan="5">
												<table border="1px">
													<tr>
														<td align="center" valign="top" width="70px"><b><sf:label
																	path="userAssetId">
																	 User Asset Id
																</sf:label></b></td>
														<td align="center" valign="top" width="150px"><b><sf:label
																	path="assetId">
																	Asset Id
																</sf:label></b></td>
														<td align="center" valign="top" width="150px"><b><sf:label
																	path="userAssetIssueDate">
																	Asset Issue Date
																</sf:label></b></td>
														<td align="center" valign="top" width="150px"><b><sf:label
																	path="userAssetReturnDate">
																	Asset Return Date
																</sf:label></b></td>
														
													</tr>
													<c:forEach items="${reportModelLst}" var="returnModel"
														varStatus="ctr">
														<tr>
															<td align="left"><sf:label path="userAssetId">${returnModel.userAssetId}</sf:label></td>
															<td align="left"><sf:label path="assetId">${returnModel.assetId}</sf:label></td>
															<td align="left"><sf:label path="userAssetIssueDate">${returnModel.userAssetIssueDate}</sf:label></td>
															<td align="left"><sf:label path="userAssetReturnDate">${returnModel.userAssetReturnDate}</sf:label></td>
														</tr>				
													</c:forEach>
												</table>
											</td>
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
					<td><%@include file="../common/gpmsFooter.jsp"%></td>
				</tr>
			</table>
		</div>
		<!-- #footer -->
	</div>
</body>
</html>