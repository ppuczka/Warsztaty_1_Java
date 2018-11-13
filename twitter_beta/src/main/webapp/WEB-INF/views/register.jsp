<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
	<h1>Register form</h1>
	<jsp:include page="menu.jsp" />
	<form:form method="post" modelAttribute="user">
	  Login: <form:input path="username" /><form:errors path="username" /><br />
	  Password: <form:password path="password" /><form:errors path="password" /><br />
	  Email: <form:input path="email" /><form:errors path="email" /><br />
  	<input type="submit" />
	</form:form>
</body>
</html>