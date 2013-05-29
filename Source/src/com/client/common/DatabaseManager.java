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
	private Connection readConn;
	private Connection writeConn;
	private boolean isSetup = false;
	
	///---Constructor(s)---///
	public DatabaseManager()
	{
		facStaff = new ArrayList<Person>();
		students = new ArrayList<Person>();
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
	public boolean setup()
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
				//readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
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
		
		return checkUser();
	}
	public void createPerson(String person)
	{
		//Delimitated by ',' or ';' not sure which yet
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
			facStaff.add(new Person(firstName, lastName, email, idNumber, boxNumber, building));
		}
		else
		{
			students.add(new Person(firstName, lastName, email, idNumber, boxNumber, 2));
		}
	}
	public void loadRoutes()
	{
		//create route in here
		routes = new ArrayList<Route>();
		try
		{
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			Statement statement = readConn.createStatement();
			ResultSet rs = statement.executeQuery("select * from Route");
			while(rs.next())
			{
				String name = rs.getString("Name");
				int id = rs.getInt("route_id");
				routes.add(new Route(name, id));
			}
			readConn.close();
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
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			Statement statement = readConn.createStatement();
			ResultSet rs = statement.executeQuery("select * from Stop;");
			while(rs.next())
			{
				String name = rs.getString("Name");
				int id = rs.getInt("stop_id");
				int route = rs.getInt("route_id");
				int order = rs.getInt("route_order");
				boolean student = rs.getBoolean("Student");
				stops.add(new Stop(name, route, id, order, student));
			}
			readConn.close();
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
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			Statement statement = readConn.createStatement();
			ResultSet rs = statement.executeQuery("select * from Courier where Is_Used=1;");
			while(rs.next())
			{
				String name = rs.getString("Name");
				int id = rs.getInt("courier_id");
				couriers.add(new Courier(name, id));
			}
			readConn.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
	}
	public void loadFacStaff()
	{
		//Load Faculty and Staff
	}
	public void loadStudent()
	{
		//Load Students
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
				readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
				PreparedStatement statement = readConn.prepareStatement("select * from Package where At_Stop='0' and Picked_Up='0';");			
				ResultSet rs = statement.executeQuery();
				
				while(rs.next())
				{
					statement = readConn.prepareStatement("select Name from Stop where stop_id=?;");
					statement.setInt(1, rs.getInt("stop_id"));
					ResultSet rs2 = statement.executeQuery();
					
					statement = readConn.prepareStatement("select User_Name from User where user_id=?;");
					statement.setInt(1, rs.getInt("processor"));
					ResultSet rs3 = statement.executeQuery();
					
					statement = readConn.prepareStatement("select Name from Courier where courier_id=?;");
					statement.setInt(1, rs.getInt("courier_id"));
					ResultSet rs4 = statement.executeQuery();
					
						packages.add(new Package(rs.getString("First_Name"),
							rs.getString("Last_Name"),
							rs.getString("ASU_Email"),
							rs.getString("Date"),
							rs.getString("Box_Number"),
							rs2.getString("Name"),
							rs.getString("Tracking_Number"),
							rs3.getString("User_Name"),
							rs4.getString("Name"),
							rs.getBoolean("At_Stop"),
							rs.getBoolean("Picked_Up"),
							rs.getString("Pick_Up_Date")
							));
				}
				readConn.close();
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
				readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
				PreparedStatement statement = null;				
				statement = readConn.prepareStatement("select * from Package where stop_id=? and Picked_Up='0';");
			
				for(int i = 0; i < stops.size(); i++)
				{
					if(stops.get(i).getName().equals(stop))
					{
						statement.setInt(1, stops.get(i).getID());
						break;
					}
				}
				
				ResultSet rs = statement.executeQuery();
				
				while(rs.next())
				{
					statement = readConn.prepareStatement("select Name from Stop where stop_id=?;");
					statement.setInt(1, rs.getInt("stop_id"));
					ResultSet rs2 = statement.executeQuery();
					
					statement = readConn.prepareStatement("select User_Name from User where user_id=?;");
					statement.setInt(1, rs.getInt("processor"));
					ResultSet rs3 = statement.executeQuery();
					
					statement = readConn.prepareStatement("select Name from Courier where courier_id=?;");
					statement.setInt(1, rs.getInt("courier_id"));
					ResultSet rs4 = statement.executeQuery();
					
						packages.add(new Package(rs.getString("First_Name"),
							rs.getString("Last_Name"),
							rs.getString("ASU_Email"),
							rs.getString("Date"),
							rs.getString("Box_Number"),
							rs2.getString("Name"),
							rs.getString("Tracking_Number"),
							rs3.getString("User_Name"),
							rs4.getString("Name"),
							rs.getBoolean("At_Stop"),
							rs.getBoolean("Picked_Up"),
							rs.getString("Pick_Up_Date")
							));
				}
				readConn.close();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Error Connecting to Database");
			}
		}
		if(!isSetup)
		{
			JOptionPane.showMessageDialog(null, "Successfully Loaded:\nFaculty/Staff:" + facStaff.size() + "\nStudents:" + students.size() + "\nStops:" + stops.size() + "\nRoutes:" + routes.size() + "\nCouriers:" + couriers.size() + "\nPackages:" + packages.size());
			isSetup = true;
		}
		
	}
	public boolean checkUser()
	{
		int index = 0;
		try
		{
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			Statement s = readConn.createStatement();
			ResultSet rs = s.executeQuery("select * from User where Active=1;");	
			
			while(rs.next())
			{
				index++;
			}
			readConn.close();
		}
		catch(Exception e)
		{
			
		}
		if(index == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	///---Packages---///
	public void addPackage(Package p)
	{
		try
		{
			//Create Insertion String
			writeConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			PreparedStatement statement = null;
			statement = writeConn.prepareStatement("insert into Package(Tracking_Number, Date, ASU_Email, First_Name, Last_Name, Box_Number, At_Stop, Picked_Up, stop_id, courier_id, processor) values(?,?,?,?,?,?,?,?,?,?,?);");
		
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
			
			PreparedStatement s2 = writeConn.prepareStatement("select courier_id from Courier where Name=?;");
			s2.setString(1, p.getCourier());
			ResultSet rs = s2.executeQuery();
			while(rs.next())
			{
				statement.setInt(10, rs.getInt("courier_id"));
			}
			
			s2 = writeConn.prepareStatement("select user_id from User where User_Name=?;");
			s2.setString(1, p.getUser());
			rs = s2.executeQuery();
			while(rs.next())
			{
				statement.setInt(11, rs.getInt("user_id"));
			}
			
			statement.execute();
			statement.close();
			writeConn.close();
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
			writeConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			PreparedStatement statement = null;
			statement = writeConn.prepareStatement("select At_Stop from Package where Tracking_Number=?;");
			ResultSet rs = statement.executeQuery();
			if(rs.getBoolean("At_Stop"))
			{
				statement = writeConn.prepareStatement("update Package set Picked_Up=?, Pick_Up_Date=? where Tracking_Number=?;");
				statement.setBoolean(1, value);
				Date d = new Date();
				String date = DateFormat.getDateInstance(DateFormat.SHORT).format(d);
				statement.setString(2, date);
				statement.setString(3, tNumber);
				statement.execute();
			}
			else
			{
				statement = writeConn.prepareStatement("update Package set At_Stop=? where Tracking_Number=?;");
				statement.setBoolean(1, value);
				statement.setString(2, tNumber);
				statement.execute();
			}
			statement.close();
			writeConn.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
	}
	public void updatePackage(String tNumber, boolean atStop, boolean pickedUp, String stop)
	{
		try
		{
			writeConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			PreparedStatement statement = null;
			statement = writeConn.prepareStatement("update Package set At_Stop=?, Picked_Up=?, Pick_Up_Date=?, stop_id=? where Tracking_Number=?;");
			Date d = new Date();
			java.sql.Date sDate = new java.sql.Date(d.getTime());
			statement.setBoolean(1, atStop);
			statement.setBoolean(2, pickedUp);
			statement.setString(3, sDate.toString());
			for(int i = 0; i < stops.size(); i++)
			{
				if(stops.get(i).getName().equals(stop))
				{
					statement.setInt(4, stops.get(i).getID());
					break;
				}
			}
			statement.setString(5, tNumber);
			statement.execute();
			statement.close();
			writeConn.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
	}
	
	///---Stops---///
	public void addStop(String name, boolean isUsed, String route, int route_order, boolean student)
	{
		try
		{
			writeConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			PreparedStatement statement = null;
			statement = writeConn.prepareStatement("insert into Stop(Name, route_id, Is_Used, route_order, Student) values (?,?,?,?,?);");
			statement.setString(1, name);
			for(int i = 0; i < routes.size(); i++)
			{
				if(routes.get(i).getName().equals(route))
				{
					statement.setInt(2, routes.get(i).getID());
					break;
				}
			}
			//Hopefully its true(but you never know)
			statement.setBoolean(3, isUsed);
			statement.setInt(4, route_order);
			statement.setBoolean(5, student);
			
			statement.execute();
			statement.close();
			writeConn.close();
			JOptionPane.showMessageDialog(null, "Stop " + name + " Added");
			loadStops();
		}
		catch(Exception e)
		{
			//Error with database Connection
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
	}
	public void updateStop(String name, boolean isUsed, String route, int route_order)
	{
		try
		{
			writeConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			PreparedStatement statement = writeConn.prepareStatement("update Stop set Name=?, Is_Used=?, route_id=?, route_order=? where stop_id=?;");
			statement.setString(1, name);
			//Hopefully its true(but you never know)
			statement.setBoolean(2, isUsed);
			for(int i = 0; i < routes.size(); i++)
			{
				if(routes.get(i).getName().equals(route))
				{
					statement.setInt(3, routes.get(i).getID());
					break;
				}
			}
			statement.setInt(4, route_order);
			for(int i = 0; i < stops.size(); i++)
			{
				if(stops.get(i).getName().equals(name))
				{
					statement.setInt(5, stops.get(i).getID());
					break;
				}
			}
			
			statement.execute();
			statement.close();
			writeConn.close();
		}
		catch(Exception e)
		{
			//Error with database Connection
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
		loadStops();
	}

	///---Routes---///
	public void addRoute(String route) 
	{
		PreparedStatement statement = null;
		try
		{
			writeConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = writeConn.prepareStatement("insert into Route(Name) values(?);");
			statement.setString(1, route);
			statement.execute();
			loadRoutes();
			statement.close();
			writeConn.close();
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
			writeConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = writeConn.prepareStatement("update Routes Route(Name) set Name=? where Name=?;");
			statement.setString(1, currentName);
			statement.setString(2, previousName);
			statement.execute();
			statement.close();
			writeConn.close();
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

	///---Person---///
	public void addPerson(Person p)
	{
		//Do Person logic
		try
		{
			writeConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
				PreparedStatement statement = writeConn.prepareStatement("insert into Person(ID_Number, ASU_Email, First_Name, Last_Name, Number, stop_id) values(?,?,?,?,?,?);");
				statement.setString(1, p.getID());
				statement.setString(2, p.getEmail());
				statement.setString(3, p.getFirstName());
				statement.setString(4, p.getLastName());
				statement.setString(5, p.getBox());
				for(int i = 0; i < stops.size(); i++)
				{
					if(stops.get(i).getName().equals(p.getStop()))
					{
						statement.setInt(6, stops.get(i).getID());
						break;
					}
				}
				statement.execute();
				statement.close();
				writeConn.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Adding Person");
		}
	}
	public void updatePerson(Person p)
	{
		//Update Person logic
		try
		{
			writeConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			PreparedStatement statement = writeConn.prepareStatement("update Person set ASU_Email=?, stop_id=?, ID_Number=? where First_Name=? and Last_Name=? and Number=?;");
			statement.setString(1, p.getEmail());
			for(int i = 0; i < stops.size(); i++)
			{
				if(stops.get(i).getName().equals(p.getStop()))
				{
					statement.setInt(2, stops.get(i).getID());
					break;
				}
			}
			statement.setString(3, p.getID());
			statement.setString(4, p.getFirstName());
			statement.setString(5, p.getLastName());
			statement.setString(6, p.getBox());
			
			statement.execute();
			statement.close();
			writeConn.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Updating Person");
		}
	}
	
	///---Couriers---///
	public void addCourier(String courier, boolean isUsed)
	{
		try
		{
			writeConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			PreparedStatement statement = writeConn.prepareStatement("insert into Courier(Name, Is_Used) values(?,?);");
			statement.setString(1, courier);
			statement.setBoolean(2, isUsed);
			
			statement.execute();
			statement.close();
			writeConn.close();
			JOptionPane.showMessageDialog(null, "Courier " + courier + " Added");
			loadCouriers();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
	}
	public void removeCourier(String courier)
	{
		//facade for deleting a courier
		try
		{
			writeConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			PreparedStatement statement = writeConn.prepareStatement("update Courier set Is_Used=?;");
			statement.setBoolean(1, false);
			statement.execute();
			statement.close();
			writeConn.close();
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
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = readConn.prepareStatement("select * from Package where Date between ? and ?;");
			statement.setString(1, beginDate);
			statement.setString(2, endDate);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				statement = readConn.prepareStatement("select Name from Stop where stop_id=?;");
				statement.setInt(1, rs.getInt("stop_id"));
				ResultSet rs2 = statement.executeQuery();
				
				statement = readConn.prepareStatement("select User_Name from User where user_id=?;");
				statement.setInt(1, rs.getInt("processor"));
				ResultSet rs3 = statement.executeQuery();
				
				statement = readConn.prepareStatement("select Name from Courier where courier_id=?;");
				statement.setInt(1, rs.getInt("courier_id"));
				ResultSet rs4 = statement.executeQuery();
				
				if(rs.getString("Pick_Up_Date") == null)
				{
					results.add(new Package(rs.getString("First_Name"),
						rs.getString("Last_Name"),
						rs.getString("ASU_Email"),
						rs.getString("Date"),
						rs.getString("Box_Number"),
						rs2.getString("Name"),
						rs.getString("Tracking_Number"),
						rs3.getString("User_Name"),
						rs4.getString("Name"),
						rs.getBoolean("At_Stop"),
						rs.getBoolean("Picked_Up"),
						rs.getString("Pick_Up_Date")
						));
				}
				else
				{
					results.add(new Package(rs.getString("First_Name"),
							rs.getString("Last_Name"),
							rs.getString("ASU_Email"),
							rs.getString("Date"),
							rs.getString("Box_Number"),
							rs2.getString("Name"),
							rs.getString("Tracking_Number"),
							rs3.getString("User_Name"),
							rs4.getString("Name"),
							rs.getBoolean("At_Stop"),
							rs.getBoolean("Picked_Up")
							));
				}
			}
			readConn.close();
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
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			PreparedStatement statement = null;
			statement = readConn.prepareStatement("select * from Package where Tracking_Number like ?;");
			statement.setString(1, tNumber);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				statement = readConn.prepareStatement("select Name from Stop where stop_id=?;");
				statement.setInt(1, rs.getInt("stop_id"));
				ResultSet rs2 = statement.executeQuery();
				
				statement = readConn.prepareStatement("select User_Name from User where user_id=?;");
				statement.setInt(1, rs.getInt("processor"));
				ResultSet rs3 = statement.executeQuery();
				
				statement = readConn.prepareStatement("select Name from Courier where courier_id=?;");
				statement.setInt(1, rs.getInt("courier_id"));
				ResultSet rs4 = statement.executeQuery();
				
				if(rs.getString("Pick_Up_Date") == null)
				{
					results.add(new Package(rs.getString("First_Name"),
						rs.getString("Last_Name"),
						rs.getString("ASU_Email"),
						rs.getString("Date"),
						rs.getString("Box_Number"),
						rs2.getString("Name"),
						rs.getString("Tracking_Number"),
						rs3.getString("User_Name"),
						rs4.getString("Name"),
						rs.getBoolean("At_Stop"),
						rs.getBoolean("Picked_Up"),
						rs.getString("Pick_Up_Date")
						));
				}
				else
				{
					results.add(new Package(rs.getString("First_Name"),
							rs.getString("Last_Name"),
							rs.getString("ASU_Email"),
							rs.getString("Date"),
							rs.getString("Box_Number"),
							rs2.getString("Name"),
							rs.getString("Tracking_Number"),
							rs3.getString("User_Name"),
							rs4.getString("Name"),
							rs.getBoolean("At_Stop"),
							rs.getBoolean("Picked_Up")
							));
				}
			}
			readConn.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
		
		return results;		
	}
	public List<Package> findPackage(boolean delivered, boolean pickedUp)
	{
		List<Package> results = new ArrayList<Package>();
		
		try
		{
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			PreparedStatement statement = readConn.prepareStatement("select * from Package where At_Stop=? and Picked_Up=?;");
			statement.setBoolean(1, delivered);
			statement.setBoolean(2, pickedUp);
		
			ResultSet rs = statement.executeQuery();
		
			while(rs.next())
			{
				statement = readConn.prepareStatement("select Name from Stop where stop_id=?;");
				statement.setInt(1, rs.getInt("stop_id"));
				ResultSet rs2 = statement.executeQuery();
			
				statement = readConn.prepareStatement("select User_Name from User where user_id=?;");
				statement.setInt(1, rs.getInt("processor"));
				ResultSet rs3 = statement.executeQuery();
			
				statement = readConn.prepareStatement("select Name from Courier where courier_id=?;");
				statement.setInt(1, rs.getInt("courier_id"));
				ResultSet rs4 = statement.executeQuery();
			
				if(rs.getString("Pick_Up_Date") == null)
				{
					results.add(new Package(rs.getString("First_Name"),
						rs.getString("Last_Name"),
						rs.getString("ASU_Email"),
						rs.getString("Date"),
						rs.getString("Box_Number"),
						rs2.getString("Name"),
						rs.getString("Tracking_Number"),
						rs3.getString("User_Name"),
						rs4.getString("Name"),
						rs.getBoolean("At_Stop"),
						rs.getBoolean("Picked_Up"),
						rs.getString("Pick_Up_Date")
						));
				}
				else
				{
					results.add(new Package(rs.getString("First_Name"),
							rs.getString("Last_Name"),
							rs.getString("ASU_Email"),
							rs.getString("Date"),
							rs.getString("Box_Number"),
							rs2.getString("Name"),
							rs.getString("Tracking_Number"),
							rs3.getString("User_Name"),
							rs4.getString("Name"),
							rs.getBoolean("At_Stop"),
							rs.getBoolean("Picked_Up")
							));
				}
			}
			readConn.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
		
		return results;
	}
	public List<Package> searchPackages(String search, int location)
	{
		List<Package> results = new ArrayList<Package>();
		location = 0;//Remove later if API is enhanced
		try
		{
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			PreparedStatement statement = readConn.prepareStatement("select * from Package where Tracking_Number like ? or Date like ? or ASU_Email like ? or First_Name like ? or Last_Name like ? or Box_Number like ?");
			switch(location)
			{
				case 0://Contains
				{
					search = "%" + search + "%";
					statement.setString(1, search);
					statement.setString(2, search);
					statement.setString(3, search);
					statement.setString(4, search);
					statement.setString(5, search);
					statement.setString(6, search);
					break;
				}
				case 1://Begins With
				{
					search = "%" + search;
					statement.setString(1, search);
					statement.setString(2, search);
					statement.setString(3, search);
					statement.setString(4, search);
					statement.setString(5, search);
					statement.setString(6, search);
					break;
				}
				case 2://Ends With
				{
					search = search + "%";
					statement.setString(1, search);
					statement.setString(2, search);
					statement.setString(3, search);
					statement.setString(4, search);
					statement.setString(5, search);
					statement.setString(6, search);
					break;
				}
			}
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				statement = readConn.prepareStatement("select Name from Stop where stop_id=?;");
				statement.setInt(1, rs.getInt("stop_id"));
				ResultSet rs2 = statement.executeQuery();
				
				statement = readConn.prepareStatement("select User_Name from User where user_id=?;");
				statement.setInt(1, rs.getInt("processor"));
				ResultSet rs3 = statement.executeQuery();
				
				statement = readConn.prepareStatement("select Name from Courier where courier_id=?;");
				statement.setInt(1, rs.getInt("courier_id"));
				ResultSet rs4 = statement.executeQuery();
				
				if(rs.getString("Pick_Up_Date") == null)
				{
					results.add(new Package(rs.getString("First_Name"),
						rs.getString("Last_Name"),
						rs.getString("ASU_Email"),
						rs.getString("Date"),
						rs.getString("Box_Number"),
						rs2.getString("Name"),
						rs.getString("Tracking_Number"),
						rs3.getString("User_Name"),
						rs4.getString("Name"),
						rs.getBoolean("At_Stop"),
						rs.getBoolean("Picked_Up"),
						rs.getString("Pick_Up_Date")
						));
				}
				else
				{
					results.add(new Package(rs.getString("First_Name"),
							rs.getString("Last_Name"),
							rs.getString("ASU_Email"),
							rs.getString("Date"),
							rs.getString("Box_Number"),
							rs2.getString("Name"),
							rs.getString("Tracking_Number"),
							rs3.getString("User_Name"),
							rs4.getString("Name"),
							rs.getBoolean("At_Stop"),
							rs.getBoolean("Picked_Up")
							));
				}
			}
			readConn.close();
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
		return results;
	}
	public List<Package> searchPackages(String search, int location, String beginDate, String endDate)
	{
		List<Package> results = new ArrayList<Package>();
		location = 0;//Remove later if API is enhanced
		try
		{
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			PreparedStatement statement = readConn.prepareStatement("select * from Package where Tracking_Number like ? or ASU_Email like ? or First_Name like ? or Last_Name like ? or Box_Number like ? where Date between ? and ?");
			switch(location)
			{
				case 0://Contains
				{
					search = "%" + search + "%";
					statement.setString(1, search);
					statement.setString(2, search);
					statement.setString(3, search);
					statement.setString(4, search);
					statement.setString(5, search);
					statement.setString(6, search);
					break;
				}
				case 1://Begins With
				{
					search = "%" + search;
					statement.setString(1, search);
					statement.setString(2, search);
					statement.setString(3, search);
					statement.setString(4, search);
					statement.setString(5, search);
					statement.setString(6, search);
					break;
				}
				case 2://Ends With
				{
					search = search + "%";
					statement.setString(1, search);
					statement.setString(2, search);
					statement.setString(3, search);
					statement.setString(4, search);
					statement.setString(5, search);
					statement.setString(6, search);
					break;
				}
			}
			statement.setString(7, beginDate);
			statement.setString(8, endDate);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				statement = readConn.prepareStatement("select Name from Stop where stop_id=?;");
				statement.setInt(1, rs.getInt("stop_id"));
				ResultSet rs2 = statement.executeQuery();
				
				statement = readConn.prepareStatement("select User_Name from User where user_id=?;");
				statement.setInt(1, rs.getInt("processor"));
				ResultSet rs3 = statement.executeQuery();
				
				statement = readConn.prepareStatement("select Name from Courier where courier_id=?;");
				statement.setInt(1, rs.getInt("courier_id"));
				ResultSet rs4 = statement.executeQuery();
				
				if(rs.getString("Pick_Up_Date") == null)
				{
					results.add(new Package(rs.getString("First_Name"),
						rs.getString("Last_Name"),
						rs.getString("ASU_Email"),
						rs.getString("Date"),
						rs.getString("Box_Number"),
						rs2.getString("Name"),
						rs.getString("Tracking_Number"),
						rs3.getString("User_Name"),
						rs4.getString("Name"),
						rs.getBoolean("At_Stop"),
						rs.getBoolean("Picked_Up"),
						rs.getString("Pick_Up_Date")
						));
				}
				else
				{
					results.add(new Package(rs.getString("First_Name"),
							rs.getString("Last_Name"),
							rs.getString("ASU_Email"),
							rs.getString("Date"),
							rs.getString("Box_Number"),
							rs2.getString("Name"),
							rs.getString("Tracking_Number"),
							rs3.getString("User_Name"),
							rs4.getString("Name"),
							rs.getBoolean("At_Stop"),
							rs.getBoolean("Picked_Up")
							));
				}
			}
			readConn.close();
			
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
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			firstName = "%" + firstName + "%";
			lastName = "%" + lastName + "%";
			PreparedStatement statement = readConn.prepareStatement("select * from Person where First_Name like ? and Last_Name like ?;");
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				String idNumber = rs.getString("ID_Number");
				String email = rs.getString("ASU_Email");
				String fName = rs.getString("First_Name");
				String lName = rs.getString("Last_Name");
				String suite = rs.getString("Number");
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
			readConn.close();
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
		
		try
		{
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			firstName = "%" + firstName + "%";
			lastName = "%" + lastName + "%";
			PreparedStatement statement = readConn.prepareStatement("select * from Person where First_Name like ? and Last_Name like ? and Number=?;");
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, boxNumber);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				String idNumber = rs.getString("ID_Number");
				String email = rs.getString("ASU_Email");
				String fName = rs.getString("First_Name");
				String lName = rs.getString("Last_Name");
				String suite = rs.getString("Number");
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
			readConn.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
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
			readConn.close();
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
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			PreparedStatement statement = readConn.prepareStatement("select * from User where User_Name=? and Password=? and Active=1;");
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
			}
			statement.close();
			rs.close();
			readConn.close();
			return u;
		}
		catch(Exception e)
		{
			//Assume invalid login
			return null;
		}
		
	}
	public boolean verifyUser(String username)
	{
		int index = 0;
		try
		{
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			PreparedStatement s = readConn.prepareStatement("select * from User where User_Name=? and Active=1;");
			s.setString(1, username);
			ResultSet rs = s.executeQuery();	
			readConn.close();
			while(rs.next())
			{
				index++;
			}
			s.close();
		}
		catch(Exception e)
		{
			
		}
		if(index == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void createUser(User u, int password)
	{
		try
		{
			writeConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			PreparedStatement statement = writeConn.prepareStatement("insert into User(User_Name, First_Name,Last_Name,Password, Admin,Active) values(?,?,?,?,?,1);");
			statement.setString(1, u.getUser());
			statement.setString(2, u.getFName());
			statement.setString(3, u.getLName());
			statement.setInt(4, password);
			statement.setBoolean(5, u.getAdmin());
			
			statement.execute();
			statement.close();
			writeConn.close();
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
					writeConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
					PreparedStatement statement = writeConn.prepareStatement("update User set Active=0 where User_Name=?;");
					statement.setString(1,username);
					statement.execute();
					statement.close();
					writeConn.close();
					JOptionPane.showMessageDialog(null, "User " + username + " Deleted");
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