public class Starter 
{

	public static void main(String[] args) 
	{
		int[] array = new int[3];
		short[] firstDigits = new short [3];
		int firstDigitsSum = 0;

		for (int i = 0; i < array.length; i++) 
		{
			array[i] = NumberGenerator.generate();
			firstDigits[i] = FirstDigit.get(array[i]);
		}
		
		firstDigitsSum = Adder.getSum(firstDigits);
		
		for(int i = 0; i < array.length; i++)
		{
			System.out.println(array[i]);
		}
		
		System.out.println(firstDigitsSum);
	}

}
