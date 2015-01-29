
public abstract class AAirplane
{
  private Coordinate coordinates;
  private Man [] crew;
  private double heightOfFlight;
  private double velocity;
  private String model;
  private String id;
  
  public AAirplane(Coordinate coordinates, Man[] crew, double heightOfFlight, double velocity, String model, String id)
  {
    this.coordinates = coordinates;
	this.crew = crew;
	this.heightOfFlight = heightOfFlight;
	this.velocity = velocity;
	this.id = id;
	this.model = model;

  }
  
  public Coordinate getCoordinates()
  {
    return coordinates;
  }
  
  public void setCoordinates(Coordinate coordinates)
  {
    this.coordinates = coordinates;
  }
  
  public Man [] getCrew()
  {
    return crew;
  }
  
  public void setCrew(Man[] crew)
  {
    this.crew = crew;
  }
  
  public double getHeightOfFlight()
  {
    return heightOfFlight; 
  }
  
  public void setHeightOfFlight(double heightOfFlight)
  {
    this.heightOfFlight = heightOfFlight;
  }
  
  public double getVelocity()
  {  
    return velocity; 
  }
  
  public void setVelocity(double velocity)
  {
    this.velocity = velocity;
  }
  
  public String getId()
  {
    return id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public String getModel()
  {
    return model;
  }
  
  public void setModel(String model)
  {
    this.model = model;
  } 
  
}