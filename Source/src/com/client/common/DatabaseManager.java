package com.client.common;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.util.*;
import java.util.Date;
import javax.swing.JOptionPane;

public class DatabaseManager
{
	///---Variables---///
	private List<Person> facStaff;
	private List<Person> students;
	private List<Stop> stops;
	private List<Route> routes;
	private List<Courier> couriers;
	private List<Package> packages;
	private String dbLocation;
	private String fileLocation;
	private Connection conn;
	private boolean setup;
	
	///---Constructor(s)---///
	public DatabaseManager()
	{
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
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
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
					loadCouriers();
					loadFacStaff();
					loadStudent();
				}
				
				JOptionPane.showMessageDialog(null, "Successfully Loaded:\nFaculty/Staff:" + facStaff.size() + "\nStudents:" + students.size() + "\nStops:" + stops.size() + "\nRoutes:" + routes.size() + "\nCouriers:" + couriers.size() + "\nPackages:" + packages.size());
			} 
			catch (Exception e) 
			{
				if(facStaff.size() > 0 || students.size() > 0)
				{
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Error Creating List of People");
				}
			}
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
			while(index < person.length())
			{
				building += person.charAt(index);
				index++;
			}
		}
		
		if(!building.equals(""))
		{
			//asuPeople.add(new Person(firstName, lastName, email, idNumber, boxNumber, building));
		}
		else
		{
			//asuPeople.add(new Person(firstName, lastName, email, idNumber, boxNumber, 2));
		}
	}
	public void loadRoutes()
	{
		//create route in here
		routes = new ArrayList<Route>();
		try
		{
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
		stops = new ArrayList<Stop>();
		try
		{
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from Stop;");
			while(rs.next())
			{
				String name = rs.getString("Name");
				int id = rs.getInt("stop_id");
				int route = rs.getInt("route_id");
				int order = rs.getInt("route_order");
				stops.add(new Stop(name, route, id, order));
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
	}
	public void loadCouriers()
	{
		couriers = new ArrayList<Courier>();
		
		try
		{
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from Courier where Is_Used=1;");
			while(rs.next())
			{
				String name = rs.getString("Name");
				int id = rs.getInt("courier_id");
				couriers.add(new Courier(name, id));
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
	}
	public void loadFacStaff()
	{
		//Load Faculty and Staff
		facStaff = new ArrayList<Person>();
	}
	public void loadStudent()
	{
		//Load Students
		students = new ArrayList<Person>();
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
				 
				PreparedStatement statement = conn.prepareStatement("select * from Package where Date=? and Picked_Up='0';");
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
							rs.getString("ASU_Email"),
							rs.getDate("Date"),
							rs.getString("Box_Number"),
							rs2.getString("Name"),
							rs.getString("Tracking_Number")
							));
				}
				
				JOptionPane.showMessageDialog(null, "Successfully Loaded:\nFaculty/Staff:" + facStaff.size() + "\nStudents:" + students.size() + "\nStops:" + stops.size() + "\nRoutes:" + routes.size() + "\nPackages:" + packages.size());
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
				 
				PreparedStatement statement = null;
				
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
							rs.getString("ASU_Email"),
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
			PreparedStatement statement = null;
			statement = conn.prepareStatement("insert into Package(Tracking_Number, Date, ASU_Email, First_Name, Last_Name, Box_Number, At_Stop, Picked_Up, stop_id) values(?,?,?,?,?,?,?,?,?);");
		
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
			
			statement.execute();
			
			packages.add(p);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
	}
	@SuppressWarnings("resource")
	public void updatePackage(String tNumber, boolean value)
	{
		try
		{
			PreparedStatement statement = null;
			statement = conn.prepareStatement("select At_Stop from Package where Tracking_Number=?;");
			ResultSet rs = statement.executeQuery();
			if(rs.getBoolean("At_Stop"))
			{
				statement = conn.prepareStatement("update Package set Picked_Up=?, Pick_Up_Date=? where Tracking_Number=?;");
				statement.setBoolean(1, value);
				Date d = new Date();
				String date = DateFormat.getDateInstance(DateFormat.SHORT).format(d);
				statement.setString(2, date);
				statement.setString(3, tNumber);
				statement.execute();
			}
			else
			{
				statement = conn.prepareStatement("update Package set At_Stop=? where Tracking_Number=?;");
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
	public void updatePackage(String tNumber, boolean atStop, boolean pickedUp)
	{
		try
		{
			PreparedStatement statement = null;
			statement = conn.prepareStatement("update Package set At_Stop=?, set Picked_Up=?, set Pick_Up_Date=? where Tracking_Number=?;");
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
			PreparedStatement statement = null;
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
			
			statement.execute();
			JOptionPane.showMessageDialog(null, "Stop " + name + " Added");
			loadStops();
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
			PreparedStatement statement = conn.prepareStatement("update Stop set Name = ?, Is_Used = ?, route_id = ? where stop_id = ?;");
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
			
			statement.execute();
			JOptionPane.showMessageDialog(null, "Stop " + name + " Updated");
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
			statement = conn.prepareStatement("insert into Route(Name) values(?);");
			statement.setString(1, route);
			statement.execute();
			loadRoutes();
			JOptionPane.showMessageDialog(null,"Route Created");
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
			statement = conn.prepareStatement("update Routes Route(Name) set Name=? where Name=?;");
			statement.setString(1, currentName);
			statement.setString(2, previousName);
			statement.execute();
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
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
	}

	///---Couriers---///
	public void addCourier(String courier, boolean isUsed)
	{
		//Courier logic
	}
	public void removeCourier(String courier)
	{
		//facade for deleting a courier
		try
		{
			PreparedStatement statement = conn.prepareStatement("update Courier set Is_Used=?;");
			statement.setBoolean(1, false);
			statement.execute();
			JOptionPane.showMessageDialog(null, "Courier " + courier + " Removed");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecing to Database");
		}
	}
	
	///---Package Searching---///
	public List<Package> findPackage(String beginDate, String endDate)
	{
		List<Package> results = new ArrayList<Package>();
		
		PreparedStatement statement = null;
		try
		{
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
						rs.getString("ASU_Email"),
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
						rs.getString("ASU_Email"),
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
	public List<Package> searchPackages(String search)
	{
		List<Package> results = new ArrayList<Package>();
		
		try
		{
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
		
		try
		{
			PreparedStatement statement = conn.prepareStatement("select * from FacStaff where First_Name like %?% and Last_Name like %?%;");
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				String idNumber = rs.getString("ID_Number");
				String email = rs.getString("ASU_Email");
				String fName = rs.getString("First_Name");
				String lName = rs.getString("Last_Name");
				String suite = rs.getString("Suite_Number");
				String stop = "";
				for(int i = 0; i < stops.size(); i++)
				{
					if(stops.get(i).getID() == rs.getInt("stop_id"))
					{
						stop = stops.get(i).getName();
					}
				}
				
				results.add(new Person(fName, lName, email, idNumber, suite, stop));
			}
			
			statement = conn.prepareStatement("select * from Student where First_Name like %?% and Last_Name like %?%;");
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			rs = statement.executeQuery();
			
			while(rs.next())
			{
				String idNumber = rs.getString("ID_Number");
				String email = rs.getString("ASU_Email");
				String fName = rs.getString("First_Name");
				String lName = rs.getString("Last_Name");
				String box = rs.getString("Box_Number");
				String stop = "";
				for(int i = 0; i < stops.size(); i++)
				{
					if(stops.get(i).getID() == rs.getInt("stop_id"))
					{
						stop = stops.get(i).getName();
					}
				}
				
				results.add(new Person(fName, lName, email, idNumber, box, stop));
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return results;
	}
	public List<Person> findPerson(String firstName, String lastName, String boxNumber)
	{
		List<Person> results = new ArrayList<Person>();
		
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
	public List<Courier> getCouriers()
	{
		return couriers;
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
	
	///---Closing Up---///
	public void closeUp()
	{
		try
		{
			conn.close();
		}
		catch(Exception e)
		{
			//Ignore it
			//We are shutting down
		}
	}
	
	///---Get Unassigned Items---///
	public List<Stop> getUnassignedStops()
	{
		List<Stop> results = new ArrayList<Stop>();
		
		for(int i = 0; i < stops.size(); i++)
		{
			if(stops.get(i).getRouteID() == 1)
			{
				results.add(stops.get(i));
			}
		}
		
		return results;
	}
	
	///---User Stuff---///
	public User login(String username, int password)
	{
		User u = null;
		
		try
		{
			PreparedStatement statement = conn.prepareStatement("select * from User where Username=? and Password=?;");
			statement.setString(1, username);
			statement.setInt(2, password);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				String uname = rs.getString("User_Name");
				String firstName = rs.getString("First_Name");
				String lastName = rs.getString("Last_Name");
				boolean admin = rs.getBoolean("Admin");
				u = new User(uname, firstName, lastName, admin);
				break;
			}
			
			return u;
		}
		catch(Exception e)
		{
			//Assume invalid login
			return null;
		}
		
	}
	public void createUser(User u, int password)
	{
		try
		{
			PreparedStatement statement = conn.prepareStatement("insert into User(Username, First_Name,Last_Name,Password, Admin) values(?,?,?,?,?);");
			statement.setString(1, u.getUser());
			statement.setString(2, u.getFName());
			statement.setString(3, u.getLName());
			statement.setInt(4, password);
			statement.setBoolean(5, u.getAdmin());
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Erro Creating User " + u.getUser());
		}
	}
	public void deleteUser(String username)
	{
		switch(JOptionPane.showConfirmDialog(null, "You are about to Delete: " + username + "\nDo You Wish to Continue?"))
		{
		case JOptionPane.YES_OPTION:
		{
			//Execute a statement
			try
			{
				PreparedStatement statement = conn.prepareStatement("delete * from User where Username=?;");
				statement.setString(1,username);
				statement.execute();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Error Deleting User " + username);
			}
			break;
		}
		case JOptionPane.NO_OPTION:
		{
			return;
		}
		case JOptionPane.CANCEL_OPTION:
		{
			return;
		}
		}
	}
}