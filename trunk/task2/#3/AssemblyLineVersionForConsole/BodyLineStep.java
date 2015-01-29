public class BodyLineStep implements ILineStep
{
  public BodyLineStep()
  {
    System.out.println("Body Line Step has been created.");
  }
  
  public IProductPart buildProductPart()
  {
    return new Body(); 
  };
}