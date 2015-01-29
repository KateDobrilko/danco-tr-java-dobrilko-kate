package classes;

import interfaces.IProductPart;
import interfaces.IProduct;

public class Laptop implements IProduct
{
  private Body body;
  private Motherboard motherboard;
  private Display display;
 
  
  public Laptop()
  {
    body = null;
	motherboard = null;
	display = null;
	
	System.out.println("Laptop billet has been created.");
  }
  
  public void installFirstPart(IProductPart firstPart)
  {
    if(firstPart instanceof Body)
    {body = (Body)firstPart;
	 System.out.println("Body has been installed.");}
	
  }; 
  
  public void installSecondPart(IProductPart secondPart)
  {
    if(secondPart instanceof Motherboard)
    {motherboard = (Motherboard)secondPart;
	System.out.println("Motherboard has been installed.");}
    
  }; 
  public void installThirdPart(IProductPart thirdPart)
  {
    if(thirdPart instanceof Display)
    {display = (Display)thirdPart;
	System.out.println("Display has been installed.");}
  }; 
}