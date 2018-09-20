package ejbs;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Stateless
public class DatabaseBean implements Database
{
	private final String URL = "jdbc:derby://localhost:1527/AssignmentDB;create=true";
	private Connection connection;
	private Statement statement;
	
	private Object connect()
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
		
		return statement == null;
	}
	
	@Override
	public Object getResult(String query)
	{
		if (connection == null)
		{
			return connect();
		}
		
		return statement == null;
	}
}
