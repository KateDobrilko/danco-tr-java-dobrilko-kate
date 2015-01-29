package classes;

import interfaces.IProductPart;
import interfaces.ILineStep;

public class DisplayLineStep implements ILineStep {

	public DisplayLineStep()
	  {
	    System.out.println("Display Line Step has been created.");
	  }
	  
	  public IProductPart buildProductPart()
	  {
	    return new Display(); 
	  };
}
