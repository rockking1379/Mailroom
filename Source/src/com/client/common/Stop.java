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
