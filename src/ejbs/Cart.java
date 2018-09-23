package ejbs;

import beans.ShopItem;

import javax.ejb.Remote;
import java.util.ArrayList;

@Remote
public interface Cart
{
	void create();
	
	void addToCart(ShopItem item, int quantity);
	
	boolean removeFromCart(ShopItem item, int quantity);
	
	int getSum();
	
	void clear();
	
	ArrayList<ShopItem> getCart();
}
