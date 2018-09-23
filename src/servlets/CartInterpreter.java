package servlets;

import ejbs.Cart;
import ejbs.Database;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CartInterpreter")
public class CartInterpreter extends HttpServlet
{
	@EJB
	private Cart cartBean;
	
	@EJB
	private Database dbBean;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if (request.getParameter("Logout") != null)
		{
			// Clear cart
			
			request.getSession(true).setAttribute("LoggedIn", null);
			return;
		}
	}
}
