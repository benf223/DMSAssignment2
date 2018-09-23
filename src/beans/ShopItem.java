package beans;

import java.io.Serializable;

public class ShopItem implements Serializable
{
	private int price;
	private String name;
	private int id;
	private String description;
	private int quantity;
	
	public ShopItem()
	{
	}
	
	public int getPrice()
	{
		return price;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public void setPrice(int price)
	{
		this.price = price;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
}
