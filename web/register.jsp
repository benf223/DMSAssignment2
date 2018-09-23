<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h2>Register</h2>
<br>
<%
    String username = "";
    String email = "";

    if (request.getSession(true).getAttribute("failedRegister") != null)
    {
        username = request.getParameter("uName");
        email = request.getParameter("pwd");

        out.println("<h2>" + request.getSession().getAttribute("failedRegister") + "</h2>");
    }
%>
<form action="${pageContext.request.contextPath}/UserManagement" method="get">
    <label>
        Username
        <input type="text" placeholder="<%= username%>">
    </label>
    <br>
    <label>
        Email
        <input type="email" placeholder="<%= email%>">
    </label>
    <br>
    <label>
        Password
        <input type="password">
    </label>
    <br>
    <label>
        Confirm Password
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
        out.println("<a href=\"login.jsp\">Login</a>");
    }
%>
</body>
</html>
