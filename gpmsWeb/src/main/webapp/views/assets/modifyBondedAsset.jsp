<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="resources/gpmsStyle.css" />
<script type="text/javascript">
	function correctAsset(userAssetId, userAssetIssueProcessId,
			userAssetReturnProcessId, assetId) {

		if (userAssetReturnProcessId == '') {
			userAssetReturnProcessId = 'NONE';
		}

		this.document.getElementById('userAssetId').value = userAssetId;
		this.document.getElementById('userAssetIssueProcessId').value = userAssetIssueProcessId;
		this.document.getElementById('userAssetReturnProcessId').value = userAssetReturnProcessId;
		this.document.getElementById('assetId').value = assetId;

		this.document.getElementById("modifyBondedAssetForm").submit();
	}
</script>
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
								<sf:form id="modifyBondedAssetForm" action="modifyBondedAsset"
									modelAttribute="bondedAssetBean">
									<table cellspacing="20px"
										style="min-height: 50%; min-width: 50%;">
										<tr>
											<td class="boxHeading" align="left"><b> <label>Assets
														Correction </label>
											</b></td>
										</tr>
										<tr>
											<td>
												<table border="1px">
													<tr>
														<td align="center" valign="top" width="70px"><b><sf:label
																	path="assetId">
																	<spring:message
																		code="label.approveRejectAsset.assetId.text" />
																</sf:label></b></td>
														<td align="center" valign="top" width="150px"><b><sf:label
																	path="userCorpEmail">
																	<spring:message
																		code="label.approveRejectAsset.userCorporateEmail.text" />
																</sf:label></b></td>
														<td align="center" valign="top" width="150px"><b><sf:label
																	path="userAssetIssueDate">
																	<spring:message
																		code="label.approveRejectAsset.issueDate.text" />
																</sf:label></b></td>
														<td align="center" valign="top" width="150px"><b><sf:label
																	path="createDate">
																	<spring:message
																		code="label.approveRejectAsset.assignRequestDate.text" />
																</sf:label></b></td>
														<td align="center" valign="top" width="300px"><b><sf:label
																	path="assetAssignedComment">
																	<spring:message
																		code="label.approveRejectAsset.assignmentComments.text" />
																</sf:label></b></td>
														<td align="center" valign="top" width="150px"><b><sf:label
																	path="modifiedDate">Correction</sf:label></b></td>
													</tr>
													<c:forEach items="${BondedAssetBeanLst}" var="bondedAsset"
														varStatus="ctr">
														<c:set var="userAssetID"
															value="${BondedAssetBeanLst[ctr.index].userAssetId}" />
														<c:set var="userAssetIssueProcessId"
															value="${BondedAssetBeanLst[ctr.index].userAssetIssueProcessId}" />
														<c:set var="userAssetReturnProcessId"
															value="${BondedAssetBeanLst[ctr.index].userAssetReturnProcessId}" />
														<c:set var="assetId"
															value="${BondedAssetBeanLst[ctr.index].assetId}" />
														<tr>
															<td align="left"><sf:label path="assetId">${bondedAsset.assetId}</sf:label></td>
															<td align="left"><sf:label path="userCorpEmail">${bondedAsset.userCorpEmail}</sf:label></td>
															<td align="left"><sf:label path="userAssetIssueDate">${bondedAsset.userAssetIssueDate}</sf:label></td>
															<td align="left"><sf:label path="createDate">${bondedAsset.createDate}</sf:label></td>
															<td align="left"><sf:label
																	path="assetAssignedComment">${bondedAsset.assetAssignedComment}</sf:label></td>

															<td align="center" width="75px"><input
																id="userAssetId" type="hidden" name="userAssetId"
																value="" /> <input id="userAssetIssueProcessId"
																type="hidden" name="userAssetIssueProcessId" value="" />
																<input id="userAssetReturnProcessId" type="hidden"
																name="userAssetReturnProcessId" value="" /> <input
																id="assetId" type="hidden" name="assetId" value="" /> <input
																type="submit" name="correctionParam" value="Correct"
																${isDisabled eq "true" ? 'disabled'  : '' }
																onclick="correctAsset('${bondedAsset.userAssetId}', '${bondedAsset.userAssetIssueProcessId}', '${bondedAsset.userAssetReturnProcessId}', '${bondedAsset.assetId}');" />
															</td>
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