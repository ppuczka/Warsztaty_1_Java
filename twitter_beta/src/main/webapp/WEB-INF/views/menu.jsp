<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <body>
        <sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
          <b>Menu:</b> <a href="/login">Login</a> | <a href="/register">Register</a>
          <br /><br />
        </sec:authorize>
        <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
          <b>Menu:</b> <a href="/tweets">Tweets</a> | <a href="/logout">Logout</a>
          <br /><br />
        </sec:authorize>
    </body>
</html>