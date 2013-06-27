package com.client.common;

public class Route
{
	private String name;
	private int id;

	// /---Constructors---///
	public Route(String name, int id)
	{
		this.name = name;
		this.id = id;
	}

	public Route(String name)
	{
		this.name = name;
	}

	// /---Get Methods---///
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
