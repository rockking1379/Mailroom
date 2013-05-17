package mailRoom;

import java.util.ArrayList;

public class Route 
{
	private String name;
	private int id;
	private ArrayList<Stop> stops = new ArrayList<Stop>();
	
	
	public Route(String name,int id, ArrayList<Stop> stops)
	{
		this.stops=stops;
		this.name=name;
		this.id=id;
	}
	
	public int getID()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
}
