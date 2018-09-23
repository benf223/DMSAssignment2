<%--
  Created by IntelliJ IDEA.
  User: Ben
  Date: 5/09/2018
  Time: 12:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<%
        if (session.getAttribute("LoggedIn") == null) {
		out.println("<a href=\"login.jsp\">Login</a>");
		out.println("<a href=\"register.jsp\">Register</a>");
	}%>
	<%
        if (session.getAttribute("LoggedIn") != null) {
        out.println("<form action=\"${pageContext.request.contextPath}/HomeToShop\" method=\"post\">\n<input type=\"submit\" placeholder=\"Shop\">\n</form>");
		out.println("<form action=\"${pageContext.request.contextPath}/CartInterpreter\" method=\"get\">\n<input type=\"hidden\" value=\"logout\" name=\"logout\">\n<input type=\"submit\" placeholder=\"Logout\">\n</form>");
	}%>
    <%--<form action="${pageContext.request.contextPath}/DatabaseInterpreter" method="post">--%>
        <%--<input type="submit" placeholder="submit">--%>
    <%--</form>--%>
</body>
</html>
