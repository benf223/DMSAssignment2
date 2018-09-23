<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.ShopItem" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Shop</title>
</head>
<body>
    <form action="/assignment2/CartInterpreter" method="post">
        <%
            ArrayList<ShopItem> items = (ArrayList<ShopItem>) session.getAttribute("Items");

            for (ShopItem i : items)
            {
                out.println("<h3>" + i.getName() + "</h3>");
                out.println("<h4>Description: " + i.getDescription() + "</h4>");
                out.println("<h4>Price: " + i.getPrice() + "</h4>");
                out.println("<h4>Quantity Available: " + i.getQuantity() + "</h4>");
                out.println("<h4>ID: " + i.getId() + "</h4>");
                out.println("<input type=\"number\" value=\"0\" name=\"" + i.getName() + "\">");
                out.println();
            }
        %>
        <input type="submit" value="Add to Cart">
    </form>
</body>
</html>
