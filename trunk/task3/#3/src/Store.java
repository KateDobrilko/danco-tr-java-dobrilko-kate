public class Store 
{
	private Product[] products;
	private int currentNumberOfProducts;

	public Store(int limit) 
	{
		products = new Product[limit];
		currentNumberOfProducts = 0;
	}

	public void addProduct(Product product) 
	{
		if (currentNumberOfProducts < products.length) 
		{
			products[currentNumberOfProducts] = product;
			currentNumberOfProducts++;
		}
	}

	public int getCommonWeight() 
	{
		int weight = 0;
		for (int i = 0; i < currentNumberOfProducts; i++) 
		{
			weight += products[i].getWeight();
		}
		return weight;
	}
}
