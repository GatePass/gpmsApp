<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Gate Pass Management System</h1>
	<fieldset>
	<sf:form action="login" modelAttribute="loginBean">
		<sf:label path="UserId">User Name:</sf:label>
		<sf:input path="UserId"/>
		<br/>
		<sf:label path="password">Password:</sf:label>
		<sf:password path="password"/><br />
		<input type="submit" value="Submit"/>	
	</sf:form>
	</fieldset>
	
</body>
</html>