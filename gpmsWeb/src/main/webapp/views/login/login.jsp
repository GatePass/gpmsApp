<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="resources/gpmsStyle.css" />
<title>Gate Pass Management System</title>
<script type="text/javascript">
	function onLocaleChange(lang) {
		var e = document.getElementById('language');
		var baseUri = null;
		uri = window.location.href;
		if (uri.indexOf('?') !== -1) {
			baseUri = uri.substring(0, uri.indexOf('?'));
		} else {
			baseUri = uri;
		}
		langId = e.options[e.selectedIndex].value;
		window.location = baseUri + "?lang=" + langId;
	}
</script>
</head>
<body>
	<div id="wrapper">

		<div id="header" align="center">
			<table width="100%">
				<tr>
					<td><%@include file="loginHeader.jsp"%></td>
				</tr>
			</table>
		</div>
		<!-- #header -->

		<div id="content" align="center">
			<table style="margin-top: 100px">
				<tr>
					<td>
						<div align="center">
							<fieldset id="fieldSet"
								style="border-width: 2px; background-color: lightblue; height: 250px; width: 400px;">
								<sf:form id="loginForm" action="login"
									modelAttribute="loginBean">
									<table cellspacing="20px">
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="langId">
														<spring:message
															code="label.loginPage.selectTheLanguage.text" />
													</sf:label></b></td>
											<td><sf:select id="language" path="langId"
													multiple="false" onchange="onLocaleChange(this);">
													<sf:option value="en" label="English"></sf:option>
													<sf:option value="fr" label="French"></sf:option>
												</sf:select> <br /> <sf:errors path="langId" cssClass="error" /></td>
										</tr>

										<tr>
											<td class="boxHeading" align="left" colspan="2"><b>
													<label><spring:message
															code="label.loginPage.header" /></label>
											</b></td>
										</tr>


										<tr>
											<td align="left" valign="top"><b><sf:label
														path="userId">
														<spring:message code="label.loginPage.username.text" />
													</sf:label></b></td>
											<td><sf:input path="userId" /><br /> <sf:errors
													path="userId" cssClass="error" /></td>
										</tr>
										<tr>
											<td align="left" valign="top"><b><sf:label
														path="password">
														<spring:message code="label.loginPage.password.text" />
													</sf:label></b></td>
											<td><sf:password path="password" /><br /> <sf:errors
													path="password" cssClass="error" /></td>
										</tr>
									</table>
									<input id="lang" type="hidden" name="lang" value="" />
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
									<input type="submit" name="submitL"
										value='<spring:message code="button.loginPage.submit.text"/>' />
								</sf:form>
								<table>
								<tr><td>
								<c:url value="/passwordReset" var="passwordResetUrl" />
								<td>
									<form action="${passwordResetUrl}" method="get" id="passwordResetUrlForm">
										<input type="submit"
												value='<spring:message code="button.loginPage.resetPassword.text"/>' />	
									</form>
								</td>
								</tr>
							</table>
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