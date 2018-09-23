package servlets;

import beans.ShopItem;
import ejbs.Cart;
import ejbs.Database;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "HomeToShop", urlPatterns = { "/HomeToShop" })
public class HomeToShop extends HttpServlet
{
	@EJB
	private Database dbBean;
	
	@EJB
	private Cart cardBean;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ArrayList<ShopItem> items = dbBean.getItems();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Unused
		doPost(request, response);
	}
}
