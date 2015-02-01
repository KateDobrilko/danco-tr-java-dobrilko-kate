public class Starter 
{
	public static void main(String[] args)
	{
		int LIMIT_OF_PRODUCTS_NUMBER = 10;
		
		Store store = new Store(LIMIT_OF_PRODUCTS_NUMBER);
		
		Product [] products = new Product [LIMIT_OF_PRODUCTS_NUMBER];
		
		for( int i=0; i< products.length; i++ )
		{
			//product's name automatic generation
			StringBuilder s = new StringBuilder("product");
			s.append(i+1);
			
			//adding new product into the store
			products[i] = new Product((new java.util.Random()).nextInt(1000),s.toString());
			store.addProduct(products[i]);
			
			//each product's name & weight output
			s.append(" ");
			s.append(products[i].getWeight());
			System.out.println(s.toString());
			
		}
		
		System.out.println(store.getCommonWeight());
		
		
	}
}
