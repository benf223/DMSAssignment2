<%@ page contentType="text/html;charset=UTF-8"%>
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
        username = request.getParameter("uName") == null ? "" : request.getParameter("uName");

        out.println("<h2>" + request.getSession().getAttribute("failedLogin") + "</h2>");

        request.getSession().setAttribute("failedLogin", null);
    }
%>
<form action="${pageContext.request.contextPath}/UserManagement" method="post">
    <label>
        Username
        <input type="text" value="<%= username%>" name="uName">
    </label>
    <br>
    <label>
        Password
        <input type="password" name="pwd">
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
