package classes;

import interfaces.IProductPart;
import interfaces.ILineStep;

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