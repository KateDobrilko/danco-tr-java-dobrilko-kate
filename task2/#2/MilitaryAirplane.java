
public class MilitaryAirplane extends AAirplane
{
  private boolean permission;
  
  public MilitaryAirplane(Coordinate coordinates,Man[] crew,double heightOfFlight,double velocity,String model, String id, boolean permission)
  {
    super( coordinates,crew, heightOfFlight, velocity, model,  id);
	this.permission = permission;
	System.out.println("'MilitaryAirplane' object has been created.");
  }
  
  public void setPermission(boolean permission)
  {
    this.permission = permission;
  }
  public boolean getPermission()
  {
    return permission;
  }
}