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

@WebServlet(name = "UserManagement", urlPatterns = { "/UserManagement" })
public class UserManagement extends HttpServlet
{
	@EJB
	private Database dbBean;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Login
		String uName = request.getParameter("uName");
		String password = request.getParameter("pwd");
		String dest = "/index.jsp";
		
		String result = dbBean.login(uName, password);
		
		if (result == null)
		{
			request.getSession(true).setAttribute("LoggedIn", true);
			
			if (request.getSession().getAttribute("failedLogin") != null)
			{
				request.getSession().setAttribute("failedLogin", null);
			}
		}
		else if (result.equals("Incorrect Password"))
		{
			dest = "/login.jsp?uName=" + uName;
			request.getSession(true).setAttribute("failedLogin", result);
		}
		else if (result.equals("User does not exist."))
		{
			dest = "/login.jsp";
			request.getSession(true).setAttribute("failedLogin", result);
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(dest);
		dispatcher.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Register
		String uName = request.getParameter("uName");
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
				
				if (result.equals("Username already in use."))
				{
					dest += "?email=" + email;
				}
				else if (result.equals("Email already in use."))
				{
					dest += "?uName=" + uName;
				}
				
				request.getSession(true).setAttribute("failedRegister", result);
			}
			else
			{
				request.getSession(true).setAttribute("LoggedIn", true);
				
				if (request.getSession().getAttribute("failedRegister") != null)
				{
					request.getSession().setAttribute("failedRegister", null);
				}
			}
		}
		else
		{
			dest = "/register.jsp?uName=" + uName;
			request.getSession(true).setAttribute("failedRegister", "Passwords don't match");
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(dest);
		dispatcher.forward(request, response);
	}
}
