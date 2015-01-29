


public class Coordinate 
{
  private CoordinatesMeasure latitude;
  private CoordinatesMeasure longitude;
  
  public Coordinate(CoordinatesMeasure latitude, CoordinatesMeasure longitude)
  {
    this.latitude = latitude;
	this.longitude = longitude;
	System.out.println("'Coordinate' object has been created.");
	
  }
  public CoordinatesMeasure getLatitude()
  {
    return latitude;
  }
  
  public CoordinatesMeasure getLongitude()
  {
    return longitude;
  }
  
  public void setLatitude(CoordinatesMeasure latitude)
  {
    this.latitude = latitude;
  }
  
  public void setLongitude(CoordinatesMeasure longitude)
  {
    this.longitude = longitude;
  }
}