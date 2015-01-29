public class LaptopAssemblyLine implements IAssemblyLine
{
  public LaptopAssemblyLine()
  {
    System.out.println("Laptop Assembly Line has been successfully created.");
  }
  
  public IProduct assembleProduct(IProduct product)
  {
    LineStep lineStep = new LineStep();
    product.installFirstPart(lineStep.build
    return (new LineStep
  }
}