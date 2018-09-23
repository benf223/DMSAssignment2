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
        username = request.getParameter("uName") == null ? "" : request.getParameter("uName");
        email = request.getParameter("email") == null ? "" : request.getParameter("email");

        out.println("<h2>" + request.getSession().getAttribute("failedRegister") + "</h2>");

        request.getSession().setAttribute("failedRegister", null);
    }
%>
<form action="${pageContext.request.contextPath}/UserManagement" method="get">
    <label>
        Username
        <input type="text" value="<%= username%>" name="uName">
    </label>
    <br>
    <label>
        Email
        <input type="email" value="<%= email%>" name="email">
    </label>
    <br>
    <label>
        Password
        <input type="password" name="pwd">
    </label>
    <br>
    <label>
        Confirm Password
        <input type="password" name="confPwd">
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
