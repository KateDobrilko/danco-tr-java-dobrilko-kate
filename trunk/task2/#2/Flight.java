
public class Flight
{
  private String startPoint;
  private String endPoint;
  private Time startTime;
  private Time endTime;
  private AAirplane airplane;
  
  public Flight(String startPoint,String endPoint,Time startTime,Time endTime,AAirplane airplane)
  {
    this.startPoint = startPoint;
	this.endPoint = endPoint;
	this.startTime = startTime;
	this.endTime = endTime;
	this.airplane = airplane;
	System.out.println("'Flight' object has been created.");
  }
  public String getStartPoint()
  {
    return startPoint;  
  }
  public String getEndPoint()
  {
    return endPoint;
  }
  public Time getStartTime()
  {
    return startTime;
  }
  public Time getEndTime()
  {
    return endTime;
  }
  public AAirplane getAirplane()
  { 
    return airplane;
  }
  public void setStartPoint(String startPoint)
  {
    this.startPoint = startPoint;
  }
  public void setEndPoint(String endPoint)
  {
    this.endPoint = endPoint;
  }
  public void setStartTime(Time startTime)
  {
    this.startTime = startTime;
  }
  public void setEndTime(Time endTime)
  {
    this.endTime = endTime;
  }
  public void setAirplane(AAirplane airplane)
  {
    this.airplane = airplane;
  }
}