package ejbs;

import javax.ejb.Stateful;

@Stateful
public class CartBean implements Cart
{
	@Override
	public void addToCart(Object a)
	{
	
	}
	
	@Override
	public boolean removeFromCart(Object a)
	{
		return false;
	}
}
