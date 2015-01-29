public class BodyAssemblyLine implements IAssemblyLine
{
  public BodyAssemblyLine()
  {
    System.out.println("Assembly Line has been successfully created.");
  }
  
  public IProduct assembleProduct(IProduct product)
  {
    LineStep lineStep = new LineStep();
    product.installFirstPart(lineStep.build
    return (new LineStep
  }
}