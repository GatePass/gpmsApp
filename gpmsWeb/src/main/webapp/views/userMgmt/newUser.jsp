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
											<td class="boxHeading" align="left"><b> <label>New User</label>
											</b></td>
										</tr>
										<tr>
											<td align="left"><b><sf:label path="userId">User Id :</sf:label></b></td>
											<td align="left"><sf:label path="userId" ></sf:label></td>
										</tr>
										<tr>
											<td align="left"><b><sf:label path="userName">User Name :</sf:label></b></td>
											<td ><sf:input path="userName" /></td>
										</tr>
										<tr>
											<td align="left"><b><sf:label path="questionId">Select the secret question :</sf:label></b></td>
											<td><sf:select path="questionId" multiple="false">
												<sf:option value="" label="....."></sf:option>
												<sf:options  items="${questionId}"/>
												</sf:select></td>
										</tr>
										<tr>
											<td align="left"><b><sf:label path="secretQuesAnsId">Secret question answer :</sf:label></b></td>
											<td><sf:input path="secretQuesAnsId" /></td>
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