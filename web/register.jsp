<%--
  Created by IntelliJ IDEA.
  User: Ben
  Date: 13/09/2018
  Time: 1:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
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

        if (request.getSession().getAttribute("failed") != null) {
            username = request.getParameter("uName");
            email = request.getParameter("pwd");

            out.println("<h2>" + request.getSession().getAttribute("failed") + "</h2>");
        }
    %>
	<form>
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
</body>
</html>
