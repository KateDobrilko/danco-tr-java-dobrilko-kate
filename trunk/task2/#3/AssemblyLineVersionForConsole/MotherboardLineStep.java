public class MotherboardLineStep implements ILineStep
{
  public MotherboardLineStep()
  {
    System.out.println("Motherboard Line Step has been created.");
  }
  
  public IProductPart buildProductPart()
  {
    return new Motherboard(); 
  };
}