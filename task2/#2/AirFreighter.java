
public class AirFreighter extends AAirplane
{
  private int weightOfAFreight;
  
  public AirFreighter(Coordinate coordinates,Man[] crew,double heightOfFlight,double velocity,String model, String id,int weightOFAFreight)
  {
    super(coordinates, crew, heightOfFlight,velocity, model, id);
	this.weightOfAFreight = weightOfAFreight;
	System.out.println("'AirFreighter' object has been created.");
  }
  
  public int getWeightOfAFreight()
  {
    return weightOfAFreight;
  };
  public void setWeigthOfAFreight(int weightOFAFreight)
  {
    this.weightOfAFreight = weightOfAFreight;
  };
}