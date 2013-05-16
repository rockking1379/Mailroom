package com.client.common;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;


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
	
	public void setup()
	{
		if(dbLocation != null && fileLocation != null)
		{
			//Prepare setup
			//Load people
			//Create connection string
			//Prepare for data flow
			File people = new File(fileLocation);
			FileInputStream fStream;
			try 
			{
				fStream = new FileInputStream(people);
				DataInputStream dis = new DataInputStream(fStream);
				BufferedReader br = new BufferedReader(new InputStreamReader(dis));
				String person;
				while((person = br.readLine()) != null)
				{
					createPerson(person);
				}
				br.close();
			} 
			catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null, "Error Creating List of People");
			}
			
			JOptionPane.showMessageDialog(null, "Successfully Loaded: " + asuPeople.size() + " People");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Error with Database Manager");
			System.exit(0);
		}
	}
	
	public void createPerson(String person)
	{
		//Dilimanted by ',' or ';' not sure which yet
	}
}
