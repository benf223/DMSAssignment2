<%--
  Created by IntelliJ IDEA.
  User: Ben
  Date: 13/09/2018
  Time: 1:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login:</h2>
<%
    String username = "";

    if (request.getSession(true).getAttribute("failedLogin") != null)
    {
        username = request.getParameter("uName");

        out.println("<h2>" + request.getSession().getAttribute("failedRegister") + "</h2>");
    }
%>
<form action="${pageContext.request.contextPath}/UserManagement" method="post">
    <label>
        Username
        <input type="text" placeholder="<%= username%>">
    </label>
    <br>
    <label>
        Password
        <input type="password">
    </label>
    <br>
    <input type="submit">
</form>
<br>
<a href="index.jsp">Home</a>
<%
    if (request.getSession(true).getAttribute("LoggedIn") == null)
    {
        out.println("<a href=\"register.jsp\">Register</a>");
    }
%>
</body>
</html>
