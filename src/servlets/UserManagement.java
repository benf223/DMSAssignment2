package servlets;

import ejbs.Database;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserManagement", urlPatterns = {"/UserManagement"})
public class UserManagement extends HttpServlet
{
	@EJB
	private Database dbBean;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Login
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Register
		String uName = request.getParameter("user");
		String password = request.getParameter("pwd");
		String confirmPassword = request.getParameter("confPwd");
		String email = request.getParameter("email");
		
		String dest = "/index.jsp";
		
		if (password.equals(confirmPassword))
		{
			String result = dbBean.register(uName, password, email);
			
			if (result != null)
			{
				dest = "/register.jsp";
				request.getSession().setAttribute("failed", result);
			}
		}
		else
		{
			dest = "/register.jsp";
			request.getSession().setAttribute("failed", "Passwords don't match");
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(dest);
		dispatcher.forward(request, response);
	}
}
