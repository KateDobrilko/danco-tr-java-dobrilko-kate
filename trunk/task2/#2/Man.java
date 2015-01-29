
public class Man
{
  private String firstName;
  private String lastName;
  
  public Man(String firstName,String lastName)
  {
    this.lastName = lastName;
	this.firstName = firstName;
	System.out.println("'Man' object has been created.");
  }
  
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }
  public String getFirstName()
  {
    return firstName;
  }
  public String getLastName()
  {
    return lastName;
  }
}