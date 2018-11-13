<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login form</title>
</head>
    <body>
      <h1>Tweets</h1>

      <jsp:include page="menu.jsp" />

      <h3>Add new tweet</h3>
      <form:form method="post" modelAttribute="newTweet">
        Text: <form:input path="text" /><form:errors path="text" /><br />
        <input type="submit" value="Add!" />
      </form:form>

      <h3>All tweets</h3>
      <table border="1">
        <tr>
          <th>User</th>
          <th>Created</th>
          <th>Text</th>
        </tr>
        <c:forEach items="${tweets}" var="tweet">
          <tr>
            <td>${tweet.user.email}</td>
            <td>${tweet.created}</td>
            <td>${tweet.text}</td>
          </tr>
        </c:forEach>
      </table>
</html>