package ejbs;

import javax.ejb.Remote;

@Remote
public interface Database
{
	Object getResult(String query);
}
