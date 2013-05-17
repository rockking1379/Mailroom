package com.client.common;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;


public class DatabaseManager
{
	///---Variables---///
	private List<Person> asuPeople;
	private List<Stop> stops;
	private List<Route> routes;
	private String dbLocation;
	private String fileLocation;
	
	///---Constructor(s)---///
	public DatabaseManager()
	{
		asuPeople = new ArrayList<Person>();
		stops = new ArrayList<Stop>();
		routes = new ArrayList<Route>();
	}
	
	///---Set Methods---///
	public void setDatabase(String dbLocation)
	{
		this.dbLocation = dbLocation;
	}
	public void setFile(String fileLocation)
	{
		this.fileLocation = fileLocation;
	}
	
	///---Setup---///
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
	
	///---Packages---///
	public void addPackage(Package p)
	{
		try
		{
			//Create Insertion String
			Class.forName("org.sqlite.JDBC");
			PreparedStatement statement = null;
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = conn.prepareStatement("insert into Package(Tracking_Number, Date, ASU_Email, First_Name, Last_Name, Box_Number, At_Stop, Picked_Up, stop_id)" + 
			"values(?,?,?,?,?,?,?,?,?);");
			
			statement.setString(1, p.getTrackNum());
			statement.setString(2, p.getDate());
			statement.setString(3, p.getEmail());
			statement.setString(4, p.getFName());
			statement.setString(5, p.getLName());
			statement.setString(6, p.getBoxNum());
			statement.setBoolean(7, false);
			statement.setBoolean(8, false);
			for(int i = 0; i < stops.size(); i++)
			{
				if(stops.get(i).getName().equals(p.getStop()))
				{
					statement.setInt(9, stops.get(i).getID());
					break;
				}
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
	}
	//Backup(more logic involved)
	public void updatePackage(String tNumber, boolean value)
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			PreparedStatement statement = null;
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
	}
	//Optimal
	public void updatePackage(String tNumber, boolean atStop, boolean pickedUp)
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			PreparedStatement statement = null;
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
	}
	
	///---Stops---///
	public void addStop(String name, boolean isUsed, String route)
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			PreparedStatement statement = null;
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = conn.prepareStatement("insert into Stop(Name, route_id, Is_Used) values (?,?,?);");
			statement.setString(1, name);
			for(int i = 0; i < routes.size(); i++)
			{
				if(route.equals(routes.get(i).getName()))
				{
					statement.setInt(2, routes.get(i).getID());
					break;
				}
			}
			//Hopefully its true(but you never know)
			statement.setBoolean(3, isUsed);
			
			statement.executeQuery();
		}
		catch(Exception e)
		{
			//Error with database Connection
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
	}
	public void updateStop(String name, boolean isUsed, String route)
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			PreparedStatement statement = null;
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = conn.prepareStatement("update Stop set Name = ?, isUsed = ?, route_id = ? where stop_id = ?;");
			statement.setString(1, name);
			for(int i = 0; i < routes.size(); i++)
			{
				if(route.equals(routes.get(i).getName()))
				{
					statement.setInt(2, routes.get(i).getID());
					break;
				}
			}
			//Hopefully its true(but you never know)
			statement.setBoolean(3, isUsed);
			
			statement.executeQuery();
		}
		catch(Exception e)
		{
			//Error with database Connection
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
	}

	///---Routes---///
	public void addRoute(String name) 
	{
		PreparedStatement statement = null;
		try
		{
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = conn.prepareStatement("insert into Route(Name) values(?);");
			statement.setString(1, name);
			if(statement.execute())
			{
				statement = conn.prepareStatement("select route_id from Route where Name = ?;");
				statement.setString(1, name);
				ResultSet rs = statement.executeQuery();
				Route r = new Route(name, rs.getInt(0));
				routes.add(r);
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
		
	}
}
