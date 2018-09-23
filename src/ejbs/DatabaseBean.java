package ejbs;

import beans.ShopItem;

import javax.ejb.Stateless;
import java.sql.*;
import java.util.ArrayList;

@Stateless
public class DatabaseBean implements Database
{
	private final String URL = "jdbc:derby:D:/Documents/Code Projects/Java/DMSAssignment2/AssignmentDB";
	private Connection connection;
	private Statement statement;
	
	private Object connect()
	{
		if (connection == null)
		{
			try
			{
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
				connection = DriverManager.getConnection(URL, "admin", "admin");
				statement = connection.createStatement();
			}
			catch (Exception e)
			{
				return e;
			}
			
		}
		
		return statement == null;
	}
	
	@Override
	public Object getResult(String query)
	{
		connect();
		
		return statement == null;
	}
	
	@Override
	public String register(String uName, String password, String email)
	{
		connect();
		
		try
		{
			ResultSet rs = statement.executeQuery("SELECT USERNAME FROM USERS WHERE USERNAME =\'" + uName + "\'");
			
			if (rs.next())
			{
				return "Username already in use.";
			}
			
			rs.close();
			
			rs = statement.executeQuery("SELECT EMAIL FROM USERS WHERE EMAIL = \'" + email + "\'");
			
			if (rs.next())
			{
				return "Email already in use.";
			}
			
			rs.close();
			
			statement.execute("INSERT INTO USERS (USERNAME, EMAIL, PASSWORD) VALUES  (\'" + uName + "\', \'" + email + "\', \'" + password + "\')");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public String login(String uName, String password)
	{
		connect();
		
		try
		{
			ResultSet rs = statement.executeQuery("SELECT * FROM USERS WHERE USERNAME = \'" + uName + "\'");
			
			if (rs.next())
			{
				if (password.equals(rs.getString("PASSWORD")))
				{
					return null;
				}
				else
				{
					return "Incorrect password.";
				}
			}
			else
			{
				return "User does not exist.";
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public ArrayList<ShopItem> getItems()
	{
		connect();
		
		ArrayList<ShopItem> out = new ArrayList<>();
		
		try
		{
			ResultSet rs = statement.executeQuery("SELECT * FROM SHOP_ITEM");
			
			while (rs.next())
			{
				ShopItem i = new ShopItem();
				
				i.setDescription(rs.getString("DESCRIPTION"));
				i.setId(rs.getInt("ID"));
				i.setName(rs.getString("NAME"));
				i.setPrice(rs.getInt("PRICE"));
				i.setQuantity(rs.getInt("QUANTITY"));
				
				out.add(i);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return out;
	}
}
