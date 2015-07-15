<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<META HTTP-EQUIV="Cache-Control"
	CONTENT="No-Cache,Must-Revalidate,No-Store">
<script>
	function formSubmit() {
		document.getElementById("navRedirectForm").submit();
	}
	function logoutSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>
</head>
<body>
	<div>
		<table width="100%">
			<tr align="center">
				<th style="font-size: 25px"><spring:message code="gpms.header" /></th>
			</tr>
		</table>

		<table id="breadcrump" align="right">
			<tr>
				<td><sf:form action="navigation" id="navRedirectForm">
						<a href="javascript:formSubmit()"> Back to Navigation</a>|
					</sf:form></td>
				<td>User : ${pageContext.request.userPrincipal.name}</td>
				<c:url value="/logout" var="logoutUrl" />
				<td>
					<form action="${logoutUrl}" method="post" id="logoutForm">
						<a href="javascript:logoutSubmit()"> Logout</a> <input
							type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>