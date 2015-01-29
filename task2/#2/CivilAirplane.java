

public class CivilAirplane extends AAirplane
{
  private Man[] passangers;
  
   public CivilAirplane(Coordinate coordinates, Man[]crew, double heightOfFlight, double velocity, String model, String id, Man[]passangers)
  {
    super(coordinates, crew,  heightOfFlight, velocity, model,  id);
	this.passangers = passangers;
	System.out.println("'CivilAirplane' object has been created.");
  }
  
  public void setPassangers(Man[] passangers)
  {
    this.passangers = passangers;
  }
  public Man [] getPassangers()
  {
    return passangers;
  }
}