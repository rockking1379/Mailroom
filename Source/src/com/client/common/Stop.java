package com.client.common;

public class Stop
{
	private String name;
	private int id;
	private int route_id;
	private int route_order;
	boolean student;

	// /---Constructors
	public Stop(String name)
	{
		this.name = name;
	}

	public Stop(String name, int route_id, int id, int route_order)
	{
		this.name = name;
		this.id = id;
		this.route_id = route_id;
		this.route_order = route_order;
	}

	public Stop(String name, int route_id, int id, int route_order,
			boolean student)
	{
		this.name = name;
		this.id = id;
		this.route_id = route_id;
		this.route_order = route_order;
		this.student = student;
	}

	public Stop(String name, int route_id, int route_order)
	{
		this.name = name;
		this.route_id = route_id;
		this.route_order = route_order;
	}

	// /---Get Methods---///
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

	public int getroutePos()
	{
		return route_order;
	}

	public boolean getType()
	{
		return student;
	}
}
