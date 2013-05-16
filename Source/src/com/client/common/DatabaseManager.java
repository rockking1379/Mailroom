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
		String firstName = "";
		String lastName = "";
		String email = "";
		String idNumber = "";
		String boxNumber = "";
		String building = "";
		
		int index = 0;
		//Main Loop
		while(index < person.length())
		{
			//First Name
			while(person.charAt(index) != ',')
			{
				firstName += person.charAt(index);
				index++;
			}
			index++;
			//Last Name
			while(person.charAt(index) != ',')
			{
				lastName += person.charAt(index);
				index++;
			}
			index++;
			//Email
			while(person.charAt(index) != ',')
			{
				email += person.charAt(index);
				index++;
			}
			index++;
			//idNumber
			while(person.charAt(index) != ',')
			{
				idNumber += person.charAt(index);
				index++;
			}
			index++;
			//boxNumber
			while(person.charAt(index) != ',')
			{
				boxNumber += person.charAt(index);
				index++;
			}
			index++;
			//Building
			try
			{
				while(person.charAt(index) != ',')
				{
					building += person.charAt(index);
				}
			}
			catch(Exception e)
			{
				//Just means no building was in file
			}
		}
		
		if(!building.equals(""))
		{
			asuPeople.add(new Person(firstName, lastName, email, idNumber, boxNumber, building));
		}
		else
		{
			asuPeople.add(new Person(firstName, lastName, email, idNumber, boxNumber));
		}
	}
	
	public void addPackage(Package p)
	{
		//Create Insertion String
		String insert = "insert into Package(Tracking_Number, Date, ASU_Email, First_Name, Last_Name, Box_Number, At_Stop, Picked_Up, stop_id)" +
		"values ('" + p.getTrackNum() +"','" + p.getDate() + "','" + p.getEmail() + "','" + p.getFName() + "','" + p.getLName() + "','" + p.getBoxNum() + "','false','false'," +
				"select stop_id from Stop where Name = '"+ p.getStop() + "');";
		
	}
}
