package servlets;

import beans.ShopItem;
import ejbs.Cart;
import ejbs.Database;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "CartInterpreter", urlPatterns = {"/CartInterpreter"})
public class CartInterpreter extends HttpServlet
{
	@EJB
	private Cart cartBean;
	
	@EJB
	private Database dbBean;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ArrayList<ShopItem> items = dbBean.getItems();
		
		for (ShopItem item : items)
		{
			if (request.getParameter(item.getName()) != null)
			{
				if (new Integer(request.getParameter(item.getName())) > 0)
				{
					if (item.getQuantity() >= new Integer(request.getParameter(item.getName())))
					{
						cartBean.addToCart(item, new Integer(request.getParameter(item.getName())));
					}
					else
					{
						cartBean.addToCart(item, item.getQuantity());
					}
				}
			}
		}
		
		request.getSession(true).setAttribute("Cart", cartBean.getCart());
		request.getSession().setAttribute("Total", cartBean.getSum());
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/checkout.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if (request.getParameter("logout") != null)
		{
			cartBean.clear();
			
			request.getSession(true).setAttribute("LoggedIn", null);
			
			response.getWriter().println("Logged out");
			response.setContentType("text/html");
			response.getWriter().println("<a href=\"index.jsp\">Home</a>");
		}
		else if (request.getParameter("Checkout") != null)
		{
			ArrayList<ShopItem> cart = (ArrayList<ShopItem>) request.getSession(true).getAttribute("Cart");
			
			for (ShopItem item : cart)
			{
				dbBean.decreaseQuantity(item.getName(), item.getQuantity());
			}
			
			request.getSession().setAttribute("Cart", null);
			request.getSession().setAttribute("Total", null);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
		
	}
}
