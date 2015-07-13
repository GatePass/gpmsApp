<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<META HTTP-EQUIV="Cache-Control" CONTENT="No-Cache,Must-Revalidate,No-Store">
	<script>
		function formSubmit() {
			document.getElementById("navRedirectForm").submit();
		}
	</script>
</head>
<body>
	<div align="center">
		<table>
			<tr>
				<th style="font-size: 25px"><spring:message code="gpms.header"/></th>
				<sf:form action="navigation" id="navRedirectForm">
					<td><a href="javascript:formSubmit()"> Back to Navigation</a></td>
				</sf:form>
			</tr>
		</table>
	</div>
</body>
</html>