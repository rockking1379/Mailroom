package mailroom;

public class Stop 
{
	private String name;
	private int id;
	private int route_id;
	
	public Stop(String name)
	{
		this.name=name;
	}
	
	public Stop(String name2, int route, int id2) {
		// TODO Auto-generated constructor stub
	}

	public String getName()
	{
		return name;
	}
	public int getID()
	{
		return id;
	}
	public int getRouteID()
	{
		return route_id;
	}
}
