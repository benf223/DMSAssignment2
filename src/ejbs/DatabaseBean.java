package ejbs;

import beans.ShopItem;

import javax.ejb.Stateless;
import java.sql.*;
import java.util.ArrayList;

@Stateless
public class DatabaseBean implements Database
{
	private final String URL = "jdbc:derby:/AssignmentDB;create=true";
	private Connection connection;
	private Statement statement;
	
	private boolean initialised = false;
	
	private Object connect()
	{
		if (connection == null)
		{
			try
			{
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
				connection = DriverManager.getConnection(URL, "admin", "admin");
				statement = connection.createStatement();
				
				if (!initialised)
				{
					try
					{
						statement.executeUpdate("CREATE SCHEMA ADMIN AUTHORIZATION admin");
					}
					catch (Exception e)
					{
						// Don't care
					}
					
					try
					{
						statement.executeQuery("SELECT * FROM SHOP_ITEMS");
					}
					catch (Exception e)
					{
						statement.executeUpdate("CREATE TABLE SHOP_ITEMS (ID INTEGER PRIMARY KEY, NAME VARCHAR(30), DESCRIPTION VARCHAR(500), PRICE INTEGER, QUANTITY INTEGER)");
						statement.executeUpdate("INSERT INTO SHOP_ITEMS (NAME, DESCRIPTION, PRICE, QUANTITY, ID) VALUES ('Water', 'Good for you', 93, 900000, 1)");
						statement.executeUpdate("INSERT INTO SHOP_ITEMS (NAME, DESCRIPTION, PRICE, QUANTITY, ID) VALUES ('Alcohol', 'Not as good for you', 48390, 40, 2)");
						statement.executeUpdate("INSERT INTO SHOP_ITEMS (NAME, DESCRIPTION, PRICE, QUANTITY, ID) VALUES ('Rat Poison', 'Bad for you', 23, 90000, 3)");
						statement.executeUpdate("INSERT INTO SHOP_ITEMS (NAME, DESCRIPTION, PRICE, QUANTITY, ID) VALUES ('Nectar', 'Good for bees', 890890, 1000, 4)");
					}
					
					try
					{
						statement.executeQuery("SELECT * FROM USERS");
					}
					catch (Exception e)
					{
						statement.executeUpdate("CREATE TABLE USERS (USERNAME VARCHAR(20) PRIMARY KEY, EMAIL VARCHAR(20), PASSWORD VARCHAR(20))");
						statement.executeUpdate("INSERT INTO USERS (USERNAME, EMAIL, PASSWORD) VALUES ('admin', 'admin@admin.com', 'admin')");
					}
					
					initialised = true;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return statement == null;
	}
	
	@Override
	public String register(String uName, String password, String email)
	{
		connect();
		
		try
		{
			ResultSet rs = statement.executeQuery("SELECT USERNAME FROM USERS WHERE USERNAME = '" + uName + "'");
			
			if (rs.next())
			{
				return "Username already in use.";
			}
			
			rs.close();
			
			rs = statement.executeQuery("SELECT EMAIL FROM USERS WHERE EMAIL = '" + email + "'");
			
			if (rs.next())
			{
				return "Email already in use.";
			}
			
			rs.close();
			
			statement.execute("INSERT INTO USERS (USERNAME, EMAIL, PASSWORD) VALUES  ('" + uName + "', '" + email + "', '" + password + "')");
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
			ResultSet rs = statement.executeQuery("SELECT * FROM USERS WHERE USERNAME = '" + uName + "'");
			
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
			ResultSet rs = statement.executeQuery("SELECT * FROM SHOP_ITEMS");
			
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
	
	@Override
	public void decreaseQuantity(String itemName, int quantity)
	{
		ArrayList<ShopItem> items = getItems();
		
		for (ShopItem item : items)
		{
			if (item.getName().equals(itemName))
			{
				quantity = item.getQuantity() - quantity;
				
				try
				{
					statement.executeUpdate("UPDATE SHOP_ITEMS SET QUANTITY = " + quantity + " WHERE NAME = '" + itemName + "'");
					return;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				
				return;
			}
		}
	}
}
