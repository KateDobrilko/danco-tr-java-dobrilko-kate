public class Starter
{
  public static void main(String args[])
  {
  BodyLineStep bodyLineStep = new BodyLineStep();
  MotherboardLineStep motherboardLineStep = new MotherboardLineStep();
  DisplayLineStep displayLineStep = new DisplayLineStep();
  LaptopAssemblyLine laptopAssemblyLine = new LaptopAssemblyLine(bodyLineStep,motherboardLineStep,displayLineStep);
  Laptop laptopBillet = new Laptop();
  Laptop laptop = (Laptop)laptopAssemblyLine.assembleProduct(laptopBillet);
  }
  
}