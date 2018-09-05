package ejbs;

import javax.ejb.Stateless;

@Stateless
public class DatabaseBean implements Database
{
	@Override
	public Object getResult(String query)
	{
		return null;
	}
}
