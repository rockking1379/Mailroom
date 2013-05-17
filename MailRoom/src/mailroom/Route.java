package mailroom;

public class Route 
{
	private String name;
	private int id;
	
	public Route(String name,int id)
	{
		this.name = name;
		this.id = id;
	}
	public Route(String name)
	{
		this.name = name;
	}
	
	public int getID()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
}
