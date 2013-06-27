package com.client.common;

public class Courier
{
	private int courier_id;
	private String name;

	public Courier(String name, int courier_id)
	{
		this.courier_id = courier_id;
		this.name = name;
	}

	public int getID()
	{
		return courier_id;
	}

	public String getName()
	{
		return name;
	}
}
