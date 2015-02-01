
public class FirstDigit 
{
	public static short get(int number)
	{
		if((number > 99) && (number < 1000))
		{ return (short)(number/100); }
		else return 0;
	}
}
