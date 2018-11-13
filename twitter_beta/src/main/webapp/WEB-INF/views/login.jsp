<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login form</title>
</head>
    <body>
      <h1>Login</h1>
      <jsp:include page="menu.jsp" />
      <c:if test="${param.logout ne null}">
        You have been logged out.
      </c:if>
      <c:if test="${param.error ne null}">
        Invalid email or password.
      </c:if>
        <form action="/login" method="post">
            <div><label> Email : <input type="text" name="username"/> </label></div>
            <div><label> Password: <input type="password" name="password"/> </label></div>
            <div><input type="submit" value="Log in"/></div>
        </form>
</html>