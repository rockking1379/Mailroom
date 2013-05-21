<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
package com.client.common;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.util.*;
import java.util.Date;
=======
>>>>>>> origin/master
>>>>>>> origin/Tom
package mailroom;

import java.io.*;
import java.sql.*;
import java.util.*;

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> origin/Tom
>>>>>>> origin/master
>>>>>>> origin/Tom
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
<<<<<<< HEAD
			//Prepare for data flow
=======
			//Prepare for data flow			
>>>>>>> origin/Tom
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
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
				
				loadRoutes();
				loadStops();
=======
>>>>>>> origin/Tom
>>>>>>> origin/master
>>>>>>> origin/Tom
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
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
	public void loadRoutes()
	{
		//create route in here
		PreparedStatement statement = null;
		try
		{
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = conn.prepareStatement("select * from Route;");
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				String name = rs.getString("Name");
				int id = rs.getInt("route_id");
				routes.add(new Route(name, id));
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
	}
	public void loadStops()
	{
		//create stop in here
		PreparedStatement statement = null;
		try
		{
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = conn.prepareStatement("select * from Stop;");
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				String name = rs.getString("Name");
				int id = rs.getInt("stop_id");
				int route = rs.getInt("route_id");
				stops.add(new Stop(name, route, id));
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
=======
>>>>>>> origin/master
	public void createRoute(String route)
	{
		//create route in here
	}
	public void createStop(String stop)
	{
		//create stop in here
<<<<<<< HEAD
=======
>>>>>>> origin/Tom
>>>>>>> origin/master
	}
>>>>>>> origin/Tom
	
	///---Packages---///
	public void addPackage(Package p)
	{
		try
		{
			//Create Insertion String
<<<<<<< HEAD
			PreparedStatement statement = null;
			Connection conn = DriverManager.getConnection(dbLocation);
=======
			Class.forName("org.sqlite.JDBC");
			PreparedStatement statement = null;
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
>>>>>>> origin/Tom
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
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
	@SuppressWarnings("resource")
=======
>>>>>>> origin/Tom
>>>>>>> origin/master
>>>>>>> origin/Tom
	public void updatePackage(String tNumber, boolean value)
	{
		try
		{
<<<<<<< HEAD
			PreparedStatement statement = null;
			Connection conn = DriverManager.getConnection(dbLocation);
=======
			Class.forName("org.sqlite.JDBC");
			PreparedStatement statement = null;
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
<<<<<<< HEAD
=======
<<<<<<< HEAD
			statement = conn.prepareStatement("select At_Stop from Package where Tracking_Number=?;");
			ResultSet rs = statement.executeQuery();
			if(rs.getBoolean("At_Stop"))
			{
				statement = conn.prepareStatement("alter table Package set Picked_Up=?, Pick_Up_Date=? where Tracking_Number=?;");
				statement.setBoolean(1, value);
				Date d = new Date();
				String date = DateFormat.getDateInstance(DateFormat.SHORT).format(d);
				statement.setString(2, date);
				statement.setString(3, tNumber);
				statement.execute();
			}
			else
			{
				statement = conn.prepareStatement("alter table Package set At_Stop=? where Tracking_Number=?;");
				statement.setBoolean(1, value);
				statement.setString(2, tNumber);
				statement.execute();
			}
=======
>>>>>>> origin/Tom
>>>>>>> origin/master
>>>>>>> origin/Tom
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
<<<<<<< HEAD
			PreparedStatement statement = null;
			Connection conn = DriverManager.getConnection(dbLocation);
			
=======
			Class.forName("org.sqlite.JDBC");
			PreparedStatement statement = null;
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
<<<<<<< HEAD
			
=======
<<<<<<< HEAD
			statement = conn.prepareStatement("alter table Package set At_Stop=?, set Picked_Up=?, set Pick_Up_Date=? where Tracking_Number=?;");
			Date d = new Date();
			String date = DateFormat.getDateInstance(DateFormat.SHORT).format(d);
			statement.setBoolean(1, atStop);
			statement.setBoolean(2, pickedUp);
			statement.setString(3, date);
			statement.setString(4, tNumber);
			statement.execute();
=======
			
>>>>>>> origin/Tom
>>>>>>> origin/master
>>>>>>> origin/Tom
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
<<<<<<< HEAD
			PreparedStatement statement = null;
			Connection conn = DriverManager.getConnection(dbLocation);
=======
			Class.forName("org.sqlite.JDBC");
			PreparedStatement statement = null;
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
>>>>>>> origin/Tom
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
			
<<<<<<< HEAD
			statement.executeQuery();
=======
			if(statement.execute())
			{
				JOptionPane.showMessageDialog(null, "Stop " + name + " Added");
			}
>>>>>>> origin/Tom
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
<<<<<<< HEAD
			PreparedStatement statement = null;
			Connection conn = DriverManager.getConnection(dbLocation);
=======
			Class.forName("org.sqlite.JDBC");
			PreparedStatement statement = null;
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
>>>>>>> origin/Tom
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
			
<<<<<<< HEAD
			statement.executeQuery();
=======
			if(statement.execute())
			{
				JOptionPane.showMessageDialog(null, "Stop " + name + " Updated");
			}
>>>>>>> origin/Tom
		}
		catch(Exception e)
		{
			//Error with database Connection
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
	}

<<<<<<< HEAD
	public void addRoute(Route r) {
		// TODO Auto-generated method stub
		
	}

	public List<Person> getAsuPeople() {
		return asuPeople;
	}

	public List<Stop> getStops() {
		return stops;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public String getDbLocation() {
		return dbLocation;
	}

	public String getFileLocation() {
		return fileLocation;
=======
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
	public void updateRoute(String previousName, String currentName)
	{
		PreparedStatement statement = null;
		try
		{
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = conn.prepareStatement("alter TABLE Route(Name) set Name=? where Name=?;");
			statement.setString(1, currentName);
			statement.setString(2, previousName);
			if(statement.execute())
			{
				JOptionPane.showMessageDialog(null, "Updated " + previousName + " to " + currentName);
				for(int i = 0; i < routes.size(); i++)
				{
					if(routes.get(i).getName().equals(previousName))
					{
						routes.get(i).setName(currentName);
						break;
					}
				}
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
	}

<<<<<<< HEAD
=======
<<<<<<< HEAD
	///---Package Searching---///
	public List<Package> findPackage(String[] criteria)
	{
		List<Package> results = new ArrayList<Package>();
		//Logic
		return results;
	}
	public List<Package> findPackage(String beginDate, String endDate)
	{
		List<Package> results = new ArrayList<Package>();
		
		PreparedStatement statement = null;
		try
		{
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = conn.prepareStatement("select * from Package where Date ? and ?;");
			statement.setString(1, beginDate);
			statement.setString(2, endDate);
			
			ResultSet rs = statement.executeQuery();
			
			statement = conn.prepareStatement("select Name from Stop where stop_id=?;");
			statement.setInt(1, rs.getInt("stop_id"));
			
			ResultSet rs2 = statement.executeQuery();
			
			while(rs.next())
			{
				results.add(new Package(rs.getString("First_Name"),
						rs.getString("Last_Name"),
						rs.getString("Email"),
						rs.getDate("Date"),
						rs.getString("Box_Number"),
						rs2.getString("Name"),
						rs.getString("Tracking_Number")
						));
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
		
		return results;
	}
	public List<Package> findPackage(String tNumber)
	{
		List<Package> results = new ArrayList<Package>();
		
		try
		{
			PreparedStatement statement = null;
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = conn.prepareStatement("select * from Package where Tracking_Number=?;");
			statement.setString(1, tNumber);
			
			ResultSet rs = statement.executeQuery();
			
			statement = conn.prepareStatement("select Name from Stop where stop_id=?;");
			statement.setInt(1, rs.getInt("stop_id"));
			
			ResultSet rs2 = statement.executeQuery();
			
			while(rs.next())
			{
				results.add(new Package(rs.getString("First_Name"),
						rs.getString("Last_Name"),
						rs.getString("Email"),
						rs.getDate("Date"),
						rs.getString("Box_Number"),
						rs2.getString("Name"),
						rs.getString("Tracking_Number")
						));
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
		
		return results;		
	}
	
	///---People Searching---///
	public List<Person> findPerson(String firstName, String lastName)
	{
		List<Person> results = new ArrayList<Person>();
		
		for(int i = 0; i < asuPeople.size(); i++)
		{
			if(asuPeople.get(i).getFirstName().equals(firstName))
			{
				if(asuPeople.get(i).getLastName().equals(lastName))
				{
					results.add(asuPeople.get(i));
				}
			}
		}
		
		return results;
	}
	public List<Person> findPerson(String firstName, String lastName, String boxNumber)
	{
		List<Person> results = new ArrayList<Person>();
		
		for(int i = 0; i < asuPeople.size(); i++)
		{
			if(asuPeople.get(i).getFirstName().equals(firstName))
			{
				if(asuPeople.get(i).getLastName().equals(lastName))
				{
					if(asuPeople.get(i).getBox().equals(boxNumber))
					{
						results.add(asuPeople.get(i));
					}
				}
			}
		}
		
		return results;
	}

	///---Get Methods---///
	public List<Stop> getStops()
	{
		return stops;
	}
	public List<Route> getRoutes()
	{
		return routes;
=======
>>>>>>> origin/master
	public ArrayList<Route> getRoutes() {
		// TODO Auto-generated method stub
		return new ArrayList<Route>();
	}

	public ArrayList<Stop> getStopsFromRoute(String routeName) {
		// TODO Auto-generated method stub
		return new ArrayList<Stop>();
>>>>>>> origin/Tom
	}

	public ArrayList<Package> getPackagesFromStop(int id) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		return null;
	}

	public List<Stop> getStopsFromRoute(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Person> findPerson(String text, String text2) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	

	

	
=======
		return new ArrayList<Package>();
	}

	public ArrayList<Stop> getStops() {
		// TODO Auto-generated method stub
		return new ArrayList<Stop>();
	}

	public Person getPerson(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return new Person("Thomas","Nehring", "fdsaf", "asdfsa", "252");
<<<<<<< HEAD
=======
>>>>>>> origin/Tom
>>>>>>> origin/master
	}
>>>>>>> origin/Tom
}
