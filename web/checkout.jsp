<%@ page import="beans.ShopItem" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Checkout</title>
</head>
<body>
    <form action="/assignment2/CartInterpreter" method="get">
        <input type="hidden" value="Checkout" name="Checkout">
        <%
            if (session.getAttribute("Cart") == null)
            {
            	out.println("<h3>No items in cart</h3>");
            }
            else
            {
                ArrayList<ShopItem> items = (ArrayList<ShopItem>) session.getAttribute("Cart");

                for (ShopItem i : items)
                {
                    out.println("<h3>" + i.getName() + "</h3>");
                    out.println("<h4>Description: " + i.getDescription() + "</h4>");
                    out.println("<h4>Price: " + i.getPrice() + "</h4>");
                    out.println("<h4>Quantity in Cart: " + i.getQuantity() + "</h4>");
                    out.println("<h4>ID: " + i.getId() + "</h4>");
                    out.println();
                }

                out.println("<h3>Total: " + session.getAttribute("Total"));
                out.println("<input type=\"submit\" value=\"Purchase\">");
            }
        %>
    </form>
    <a href="index.jsp">Empty Cart</a>
</body>
</html>
