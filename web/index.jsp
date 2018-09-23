<%@ page contentType="text/html;charset=UTF-8"%>
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
        out.println("<form action=\"/assignment2/HomeToShop\" method=\"post\">\n<input type=\"submit\" value=\"Shop\">\n</form>");
		out.println("<form action=\"/assignment2/CartInterpreter\" method=\"get\">\n<input type=\"hidden\" value=\"logout\" name=\"logout\">\n<input type=\"submit\" value=\"Logout\">\n</form>");
	}%>
</body>
</html>
