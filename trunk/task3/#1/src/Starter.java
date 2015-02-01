public class Starter
{
	public static void main(String [] args)
	{
		String [] testArray = new String [5];
		testArray[0] = "Dobrilko Ekaterina";
		testArray[1] = "Mikhail Petrukovsky";
		testArray[2] = "Vyacheslav Voronovich";
		testArray[3] = "Vyacheslav Adamin";
		testArray[4] = "Anna Bykova";
		
		testArray  = Sorter.sort(testArray);
		
		for(int i=0; i < 5; i++)
		{
			System.out.println(testArray[i]);
		}
	}
}
