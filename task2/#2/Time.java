
public class Time
{
  private int year;
  private int month;
  private int day;
  private int hours;
  private int minutes;
  private int seconds;
  
  public Time(int year,int month,int day,int hours,int minutes,int seconds)
  {
    this.year = year;
	this.month = month;
	this.day = day;
	this.hours = hours;
	this.minutes = minutes;
	this.seconds = seconds;
	System.out.println("'Time' object has been created.");
  }
  
  public int getYear()
  {
    return year;
  }
  public int getMonth()
  {
    return month;
  }
  public int getDay()
  {
    return day;
  }
  public int getHours()
  {
    return hours;
  }
  public int getMinutes()
  {
    return minutes;
  }
  public int getSeconds()
  {
    return seconds;
  }
  public void setYear(int year)
  {
    this.year = year;
  }
  public void setMonth(int month)
  {
    this.month = month;
  }
  public void setDay(int day)
  {
    this.day = day;
  }
  public void setHours(int hours)
  {
    this.hours = hours;
  }
  public void setMinutes(int minutes)
  {
    this.minutes = minutes;
  }
  public void setSeconds(int seconds)
  {
    this.seconds = seconds;
  }
}