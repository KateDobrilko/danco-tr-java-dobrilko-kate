
public class CoordinatesMeasure
{
  private int degrees;
  private int seconds;
  
  public CoordinatesMeasure(int degrees, int seconds)
  {
    this.degrees = degrees;
	this.seconds = seconds;
	System.out.println("'CoordinatesMeasure' object has been created.");
  }
  
  public void setDegrees(int degrees)
  {
    this.degrees = degrees;
  }
  public void setSeconds(int seconds)
  {
    this.seconds = seconds;
  }
  public int getDegrees()
  {
    return degrees;
  }
  public int getSeconds()
  { 
    return seconds;
  }	
}