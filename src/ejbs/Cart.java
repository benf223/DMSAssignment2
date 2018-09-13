package ejbs;

import javax.ejb.Remote;

@Remote
public interface Cart
{
	void addToCart(Object a);
	
	boolean removeFromCart(Object a);
}
