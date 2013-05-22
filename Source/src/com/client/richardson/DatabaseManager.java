package com.client.richardson;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.util.*;
import java.util.Date;
import javax.swing.JOptionPane;

public class DatabaseManager
{
	///---Variables---///
	private List<Person> asuPeople;
	private List<Stop> stops;
	private List<Route> routes;
	private List<Package> packages;
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
		if((dbLocation != null) && (fileLocation != null))
		{
			//Prepare setup
			//Load people
			//Create connection string
			//Prepare for data flow			
			File people = new File(fileLocation);
			FileInputStream fStream;
			try 
			{
				if(!people.exists())
				{
					JOptionPane.showMessageDialog(null, "File Missing\n" + fileLocation);
					File settings = new File("./properties.prop");
					settings.delete();
					System.exit(0);
				}
				else
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
				
					loadRoutes();
					loadStops();
				}
			} 
			catch (Exception e) 
			{
				if(asuPeople.size() > 0)
				{
					JOptionPane.showMessageDialog(null, "Successfully Loaded: " + asuPeople.size() + " People");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Error Creating List of People");
				}
			}
			
			JOptionPane.showMessageDialog(null, "Successfully Loaded: " + asuPeople.size() + " People");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Error with Database Manager ");
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
			while(index < person.length())
			{
				building += person.charAt(index);
				index++;
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
	public void loadRoutes()
	{
		//create route in here
		try
		{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from Route");
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
		try
		{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from Stop;");
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
	}
	public void loadPackages(boolean allStops, String stop)
	{
		//Load packages from today(if available)
		//Also can be used after updating a package(good logic)
		packages = new ArrayList<Package>();
		
		if(allStops)
		{
			try
			{
				Class.forName("org.sqlite.JDBC"); 
				PreparedStatement statement = null;
				Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
				statement = conn.prepareStatement("select * from Package where Date=? and Picked_Up='false';");
				Date d = new Date();
				String date = DateFormat.getDateInstance(DateFormat.SHORT).format(d);
				statement.setString(1, date);
			
				ResultSet rs = statement.executeQuery();
				
				while(rs.next())
				{
					statement = conn.prepareStatement("select Name from Stop where stop_id=?;");
					statement.setInt(1, rs.getInt("stop_id"));
			
					ResultSet rs2 = statement.executeQuery();
					
					packages.add(new Package(rs.getString("First_Name"),
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
				JOptionPane.showMessageDialog(null, "Error Connecting to Database\n" + e.getMessage());
			}
		}
		if(!allStops)
		{
			try
			{
				Class.forName("org.sqlite.JDBC"); 
				PreparedStatement statement = null;
				Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
				statement = conn.prepareStatement("select * from Package where Date=? and stop_id=?;");
				Date d = new Date();
				String date = DateFormat.getDateInstance(DateFormat.SHORT).format(d);
				statement.setString(1, date);
			
				for(int i = 0; i < stops.size(); i++)
				{
					if(stops.get(i).getName().equals(stop))
					{
						statement.setInt(2, stops.get(i).getID());
						break;
					}
				}
				
				ResultSet rs = statement.executeQuery();
				
				while(rs.next())
				{
					statement = conn.prepareStatement("select Name from Stop where stop_id=?;");
					statement.setInt(1, rs.getInt("stop_id"));
			
					ResultSet rs2 = statement.executeQuery();
					
					packages.add(new Package(rs.getString("First_Name"),
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
			packages.add(p);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database: "+e.getMessage());
		}
	}
	//Backup(more logic involved)
	@SuppressWarnings("resource")
	public void updatePackage(String tNumber, boolean value)
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			PreparedStatement statement = null;
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
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
			statement = conn.prepareStatement("alter table Package set At_Stop=?, set Picked_Up=?, set Pick_Up_Date=? where Tracking_Number=?;");
			Date d = new Date();
			String date = DateFormat.getDateInstance(DateFormat.SHORT).format(d);
			statement.setBoolean(1, atStop);
			statement.setBoolean(2, pickedUp);
			statement.setString(3, date);
			statement.setString(4, tNumber);
			statement.execute();
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
			
			if(statement.execute())
			{
				JOptionPane.showMessageDialog(null, "Stop " + name + " Added");
			}
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
			
			if(statement.execute())
			{
				JOptionPane.showMessageDialog(null, "Stop " + name + " Updated");
			}
		}
		catch(Exception e)
		{
			//Error with database Connection
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
	}

	///---Routes---///
	public void addRoute(String route) 
	{
		PreparedStatement statement = null;
		try
		{
			Class.forName("org.sqlite.JDBC"); 
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = conn.prepareStatement("insert into Route(Name) values(?);");
			statement.setString(1, route);
			if(statement.execute())
			{
				loadRoutes();
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
			Class.forName("org.sqlite.JDBC"); 
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
			Class.forName("org.sqlite.JDBC"); 
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
			Class.forName("org.sqlite.JDBC"); 
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
	}
	public List<Package> getPackages()
	{
		return packages;
	}

	///---Printing---///
	public List<Package> getPackagesFromStop(String stop) 
	{
		List<Package> results = new ArrayList<Package>();
		
		for(int i = 0; i < packages.size(); i++)
		{
			if(packages.get(i).getStop().equals(stop))
			{
				results.add(packages.get(i));
			}
		}
		
		return results;
	}
	public List<Stop> getStopsFromRoute(String route) 
	{
		List<Stop> results = new ArrayList<Stop>();
		
		int route_id = 0;
		
		for(int i = 0; i < routes.size(); i++)
		{
			if(routes.get(i).getName().equals(route))
			{
				route_id = routes.get(i).getID();
				break;
			}
		}
		
		for(int i = 0; i < stops.size(); i++)
		{
			if(stops.get(i).getRouteID() == route_id)
			{
				results.add(stops.get(i));
			}
		}		
		return results;
	}
}
