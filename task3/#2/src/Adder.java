public class Adder 
{

	public static short getSum(short[] array) 
	{
		short sum = 0;

		for (int i = 0; i < array.length; i++) 
		{
			sum += array[i];
		}

		return sum;

	}
	
	public static int getSum(int[] array) 
	{
		int sum = 0;

		for (int i = 0; i < array.length; i++) 
		{
			sum += array[i];
		}

		return sum;

	}
}
