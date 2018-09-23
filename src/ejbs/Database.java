package ejbs;

import beans.ShopItem;

import javax.ejb.Remote;
import java.util.ArrayList;

@Remote
public interface Database
{
	Object getResult(String query);
	
	String register(String uName, String password, String email);
	
	String login(String uName, String password);
	
	ArrayList<ShopItem> getItems();
}
