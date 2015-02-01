public class Product 
{
	private String name;
	private int weight;
	
	public Product (int weight, String s)
	{
		this.weight = weight;
		this.name = s;
	}

	public int getWeight() 
	{
		return weight;
	}

	public String getName() 
	{
		return name;
	}

	
}
