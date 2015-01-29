package classes;

import interfaces.IAssemblyLine;
import interfaces.IProduct;
public class LaptopAssemblyLine implements IAssemblyLine
{
  private BodyLineStep bodyLineStep;
  private MotherboardLineStep motherboardLineStep;
  private DisplayLineStep displayLineStep;
  
  public LaptopAssemblyLine(BodyLineStep bodyLineStep, MotherboardLineStep motherboardLineStep, DisplayLineStep displayLineStep)
  {
    this.bodyLineStep = bodyLineStep;
	this.motherboardLineStep = motherboardLineStep;
	this.displayLineStep = displayLineStep;
    System.out.println("Laptop Assembly Line has been created.");
  }
  
  public IProduct assembleProduct(IProduct product)
  {
    if(product instanceof Laptop)
    {
	  product.installFirstPart(bodyLineStep.buildProductPart());
	  product.installSecondPart(motherboardLineStep.buildProductPart());
	  product.installThirdPart(displayLineStep.buildProductPart());
      
	}
	System.out.println("Laptop has been successfully assembled.");
	return product;
  }
}