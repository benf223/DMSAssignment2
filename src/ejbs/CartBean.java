package ejbs;

import beans.ShopItem;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.HashMap;

@Stateless
public class CartBean implements Cart
{
	private HashMap<ShopItem, Integer> itemsInCart;
	private int total;
	
	@Override
	public void create()
	{
		itemsInCart = new HashMap<>();
		total = 0;
	}
	
	@Override
	public void addToCart(ShopItem item, int quantity)
	{
		if (itemsInCart.containsKey(item))
		{
			itemsInCart.replace(item, itemsInCart.get(item) + quantity);
			total = calculateSum();
		}
		else
		{
			itemsInCart.put(item, quantity);
			total = calculateSum();
		}
	}
	
	@Override
	public boolean removeFromCart(ShopItem item, int quantity)
	{
		if (itemsInCart.containsKey(item))
		{
			if (itemsInCart.get(item) > quantity)
			{
				itemsInCart.replace(item, itemsInCart.get(item) - quantity);
				total = calculateSum();
				return true;
			}
			else if (itemsInCart.get(item) == quantity)
			{
				itemsInCart.remove(item);
				total = calculateSum();
				return true;
			}
			else
			{
				return false;
			}
		}
		
		return false;
	}
	
	@Override
	public ArrayList<ShopItem> getCart()
	{
		if (!this.itemsInCart.isEmpty())
		{
			ArrayList<ShopItem> out = new ArrayList<>();
			
			for (ShopItem item : itemsInCart.keySet())
			{
				item.setQuantity(itemsInCart.get(item));
				out.add(item);
			}
			
			return out;
		}
		
		return null;
	}
	
	private int calculateSum()
	{
		int out = 0;
		
		for (ShopItem i : itemsInCart.keySet())
		{
			out += i.getPrice() * itemsInCart.get(i);
		}
		
		return out;
	}
	
	@Override
	public int getSum()
	{
		return total;
	}
	
	@Override
	public void clear()
	{
		create();
	}
}
