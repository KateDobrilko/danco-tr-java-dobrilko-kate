
public class Starter
{
  public static void main (String args[])
  {
    Time startTime1 = new Time(2014,12,31,12,30,0);
	Time endTime1 = new Time(2015,1,1,11,30,0);
	CoordinatesMeasure longitude1 = new CoordinatesMeasure(27,33); 
	CoordinatesMeasure latitude1 = new CoordinatesMeasure(53,55); 
	Coordinate startCoordinates = new Coordinate(latitude1,longitude1);
	Man[] crew1 = new Man[3];
	crew1[0] = new Man("Alexander", "Ivanov");
	crew1[1] = new Man("Ruslan", "Samoylov");
	Man[] passengers = new Man[3];
	passengers[0] = new Man("Anastasia","Udot");
	passengers[1] = new Man("Kate","Dobrilko");
	passengers[2] = new Man("Inna","Samoylova");
	AAirplane airplane1 = new CivilAirplane(startCoordinates,crew1,9500,500,"TU-154","1",passengers);
	AAirplane airplane2 = new MilitaryAirplane(startCoordinates, crew1, 10000, 600, "YaK-141","2",true);
	AAirplane airplane3 = new AirFreighter(startCoordinates, crew1, 10500, 450, "AN-124","3",5000);
    Flight flight1 = new Flight("Minsk","Moscow",startTime1, endTime1,airplane1);
	Flight flight2 = new Flight("Minsk","Moscow",startTime1, endTime1,airplane2);
	Flight flight3 = new Flight("Minsk","Moscow",startTime1, endTime1,airplane3);
  }
}