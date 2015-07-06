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
								<sf:form action="approveRejectAsset"
									modelAttribute="bondedAssetBean">
									<table cellspacing="20px"
										style="min-height: 50%; min-width: 50%;">
										<tr>
											<td class="boxHeading" align="left"><b> <label>Approve/Reject
														asset</label>
											</b></td>
										</tr>
										<tr>
											<td>
												<table border="1px">
													<tr>
														<td align="center" valign="top" width="70px"><b><sf:label
																	path="assetId">Asset Id</sf:label></b></td>
														<td align="center" valign="top" width="150px"><b><sf:label
																	path="userCorpEmail">User corporate email / User Id</sf:label></b></td>
														<td align="center" valign="top" width="150px"><b><sf:label
																	path="userAssetIssueDate">Issue Date</sf:label></b></td>
														<td align="center" valign="top" width="150px"><b><sf:label
																	path="createDate">Assign Request Date</sf:label></b></td>
														<td align="center" valign="top" width="300px"><b><sf:label
																	path="assetAssignedComment">Assignment Comments</sf:label></b></td>
														<td align="center" valign="top" width="300px" colspan="2"><b><sf:label
																	path="modifiedDate">Actions</sf:label></b></td>									
													</tr>
													<c:forEach items="BondedAssetBeanLst" begin="0" end="1" step="1" var="bondedAsset">
														<!-- td align="left"><sf:label path="assetId">${bondedAsset.assetId}</sf:label></td-->
														<td align="left"><sf:label path="userCorpEmail">${bondedAsset.userCorpEmail}</sf:label></td>
														<td align="left"><sf:label path="userAssetIssueDate">${bondedAsset.userAssetIssueDate}</sf:label></td>
														<td align="left"><sf:label path="createDate">${bondedAsset.createDate}</sf:label></td>
														<td align="left"><sf:label path="assetAssignedComment">${bondedAsset.assetAssignedComment}</sf:label></td>
														<td align="left"><sf:label path="approveButton">
														<input type="submit" name="approveButton" value="Approve" ${isDisabled eq "true" ? 'disabled'  : '' }/>
														</sf:label></td>
														<td align="left"><sf:label path="rejectButton">
														<input type="submit" name="rejectButton" value="Reject" ${isDisabled eq "true" ? 'disabled'  : '' }/>
														</sf:label></td>
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