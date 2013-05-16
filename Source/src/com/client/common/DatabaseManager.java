package com.client.common;

import java.util.*;


public class DatabaseManager
{
	private List<Person> asuPeople;
	private String dbLocation;
	private String fileLocation;
	
	public DatabaseManager()
	{
		asuPeople = new ArrayList<Person>();
	}
	
	public void addPackage(Package p)
	{
		
	}
	
	public void setDatabase(String dbLocation)
	{
		this.dbLocation = dbLocation;
	}
	public void setFile(String fileLocation)
	{
		this.fileLocation = fileLocation;
	}
}
