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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "HomeToShop", urlPatterns = { "/HomeToShop" })
public class HomeToShop extends HttpServlet
{
	@EJB
	private Database dbBean;
	
	@EJB
	private Cart cartBean;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		cartBean.create();
		
		ArrayList<ShopItem> items = dbBean.getItems();
		
		request.getSession(true).setAttribute("Items", items);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/shop.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Unused
		doPost(request, response);
	}
}
