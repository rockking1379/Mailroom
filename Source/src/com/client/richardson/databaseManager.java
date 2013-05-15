package com.client.richardson;

import java.util.*;

public class databaseManager
{
	private List<Person> asuPeople;
	private String dbLocation;
	private String fileLocation;
	
	public databaseManager(String dbLocation, String fileLocation)
	{
		this.dbLocation = dbLocation;
		this.fileLocation = fileLocation;
		
		asuPeople = new ArrayList<Person>();
	}	
}
