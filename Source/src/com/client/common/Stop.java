package com.client.common;

public class Stop 
{
	private String name;
	private int id;
	private int route_id;
	
	public Stop(String name)
	{
		this.name=name;
	}
	public Stop(String name, int route_id, int id)
	{
		this.name = name;
		this.id = id;
		this.route_id = route_id;
	}
	public Stop(String name, int route_id)
	{
		this.name = name;
		this.route_id = route_id;
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
