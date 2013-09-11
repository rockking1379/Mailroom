package com.client.common;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.util.*;
import java.util.Date;
import javax.swing.JOptionPane;

public class DatabaseManager
{
	// /---Variables---///
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
	public final int SEARCH_CONTAINS = 0;
	public final int SEARCH_BEGINS_WITH = 1;
	public final int SEARCH_ENDS_WITH = 2;

	// /---Constructor(s)---///
	public DatabaseManager()
	{
		facStaff = new ArrayList<Person>();
		students = new ArrayList<Person>();
	}

	// /---Set Methods---///
	public void setDatabase(String dbLocation)
	{
		this.dbLocation = dbLocation;
	}

	public void setFile(String fileLocation)
	{
		this.fileLocation = fileLocation;
	}

	// /---Setup---///
	@SuppressWarnings({ "unused", "deprecation" })
	public boolean setup()
	{
		if ((dbLocation != null) && (fileLocation != null))
		{
			// Prepare setup
			// Load people
			// Create connection string
			// Prepare for data flow
			Date start = new Date();
			System.out.println(start.getHours() + ":" + start.getMinutes()
					+ ":" + start.getSeconds());
			File people = new File(fileLocation);
			try
			{
				Class.forName("org.sqlite.JDBC");
				// readConn = DriverManager.getConnection("jdbc:sqlite:" +
				// dbLocation);
				checkUpdate();
				loadRoutes();
				loadStops();
				loadCouriers();
				loadFacStaff();
				loadStudent();
				ETL e = new ETL((ArrayList<Stop>) stops, dbLocation, writeConn);
				if (e.check())
				{
					e.process();
				}

				Date finish = new Date();
				System.out.println(finish.getHours() + ":"
						+ finish.getMinutes() + ":" + finish.getSeconds());
			}
			catch (Exception e)
			{
				if (facStaff.size() == 0 && students.size() == 0)
				{
					JOptionPane.showMessageDialog(null,
							"Error Creating List of People");
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

	public void loadRoutes()
	{
		// create route in here
		routes = new ArrayList<Route>();
		Statement statement = null;
		ResultSet rs = null;

		try
		{
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = readConn.createStatement();
			rs = statement.executeQuery("select * from Route");
			while (rs.next())
			{
				String name = rs.getString("Name");
				int id = rs.getInt("route_id");
				routes.add(new Route(name, id));
			}
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
			{
				loadRoutes();
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (statement != null)
				{
					statement.close();
				}
				if (readConn != null)
				{
					readConn.close();
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
		}
	}

	public void loadStops()
	{
		// create stop in here
		stops = new ArrayList<Stop>();
		Statement statement = null;
		ResultSet rs = null;

		try
		{
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = readConn.createStatement();
			rs = statement
					.executeQuery("select * from Stop where Is_Used='1';");
			while (rs.next())
			{
				String name = rs.getString("Name");
				int id = rs.getInt("stop_id");
				int route = rs.getInt("route_id");
				int order = rs.getInt("route_order");
				boolean student = rs.getBoolean("Student");
				stops.add(new Stop(name, route, id, order, student));
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (statement != null)
				{
					statement.close();
				}
				if (readConn != null)
				{
					readConn.close();
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
		}
	}

	public void loadCouriers()
	{
		couriers = new ArrayList<Courier>();
		Statement statement = null;
		ResultSet rs = null;
		try
		{
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = readConn.createStatement();
			rs = statement
					.executeQuery("select * from Courier where Is_Used=1;");
			while (rs.next())
			{
				String name = rs.getString("Name");
				int id = rs.getInt("courier_id");
				couriers.add(new Courier(name, id));
			}
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
			{
				loadCouriers();
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (statement != null)
				{
					statement.close();
				}
				if (readConn != null)
				{
					readConn.close();
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
		}
	}

	public void loadFacStaff()
	{
		// Load Faculty and Staff
		Statement statement = null;
		ResultSet rs = null;
		try
		{
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = readConn.createStatement();
			rs = statement
					.executeQuery("select * from Person where ASU_Email not like '%grizzlies.adams.edu';");

			while (rs.next())
			{
				String building = null;
				PreparedStatement s = readConn
						.prepareStatement("select Name from Stop where stop_id=?;");
				s.setInt(1, rs.getInt("stop_id"));
				ResultSet rs2 = s.executeQuery();
				while (rs2.next())
				{
					building = rs2.getString("Name");
				}

				facStaff.add((new Person(rs.getString("First_Name"), rs
						.getString("Last_Name"), rs.getString("ASU_Email"), rs
						.getString("ID_Number"), rs.getString("Number"),
						building)));
			}
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
			{
				loadFacStaff();
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (statement != null)
				{
					statement.close();
				}
				if (readConn != null)
				{
					readConn.close();
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
		}

	}

	public void loadStudent()
	{
		// Load Students
		Statement statement = null;
		ResultSet rs = null;
		try
		{
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = readConn.createStatement();
			rs = statement
					.executeQuery("select * from Person where ASU_Email like '%grizzlies.adams.edu';");

			while (rs.next())
			{
				String building = null;
				PreparedStatement s = readConn
						.prepareStatement("select Name from Stop where stop_id=?;");
				s.setInt(1, rs.getInt("stop_id"));
				ResultSet rs2 = s.executeQuery();
				while (rs2.next())
				{
					building = rs2.getString("Name");
				}

				students.add((new Person(rs.getString("First_Name"), rs
						.getString("Last_Name"), rs.getString("ASU_Email"), rs
						.getString("ID_Number"), rs.getString("Number"),
						building)));
			}
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
			{
				loadStudent();
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (statement != null)
				{
					statement.close();
				}
				if (readConn != null)
				{
					readConn.close();
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
		}
	}

	public void loadPackages(boolean allStops, String stop)
	{
		// Load packages from today(if available)
		// Also can be used after updating a package(good logic)
		packages = new ArrayList<Package>();
		PreparedStatement statement = null;
		ResultSet rs = null;

		if (allStops)
		{
			try
			{
				readConn = DriverManager.getConnection("jdbc:sqlite:"
						+ dbLocation);
				statement = readConn
						.prepareStatement("select Package.*, Courier.Name AS 'Courier', Stop.Name AS 'Stop', User.User_Name AS 'Username' "
								+ "from Package, Courier, Stop, User "
								+ "where Package.courier_id = Courier.courier_id and Package.stop_id = Stop.stop_id and User.user_id = Package.processor AND Package.At_Stop='0' and Package.Picked_Up='0';");
				rs = statement.executeQuery();

				while (rs.next())
				{
					packages.add(new Package(rs.getString("First_Name"), rs
							.getString("Last_Name"), rs.getString("ASU_Email"),
							rs.getString("Date"), rs.getString("Box_Number"),
							rs.getString("Stop"), rs
									.getString("Tracking_Number"), rs
									.getString("Username"), rs
									.getString("Courier"), rs
									.getBoolean("At_Stop"), rs
									.getBoolean("Picked_Up"), rs
									.getString("Pick_Up_Date"), rs
									.getBoolean("Returned")));
				}
			}
			catch (Exception e)
			{
				if (e.getMessage().equals("database is locked"))
				{
					loadPackages(allStops, stop);
				}
				else
				{
					JOptionPane.showMessageDialog(null,
							"Error Connecting to Database\n" + e.getMessage());
				}
			}
			finally
			{
				try
				{
					if (rs != null)
					{
						rs.close();
					}
					if (statement != null)
					{
						statement.close();
					}
					if (readConn != null)
					{
						readConn.close();
					}
				}
				catch (Exception e)
				{
					// Do nothing
				}
			}
		}
		if (!allStops)
		{
			try
			{
				readConn = DriverManager.getConnection("jdbc:sqlite:"
						+ dbLocation);

				statement = readConn
						.prepareStatement("select Package.*, Courier.Name AS 'Courier', Stop.Name AS 'Stop', User.User_Name AS 'Username' "
								+ "from Package, Courier, Stop, User "
								+ "where Package.courier_id = Courier.courier_id and Package.stop_id = Stop.stop_id and User.user_id = Package.processor AND Package.stop_id=? and Package.Picked_Up='0';");

				for (int i = 0; i < stops.size(); i++)
				{
					if (stops.get(i).getName().equals(stop))
					{
						statement.setInt(1, stops.get(i).getID());
						break;
					}
				}

				rs = statement.executeQuery();

				while (rs.next())
				{
					packages.add(new Package(rs.getString("First_Name"), rs
							.getString("Last_Name"), rs.getString("ASU_Email"),
							rs.getString("Date"), rs.getString("Box_Number"),
							rs.getString("Stop"), rs
									.getString("Tracking_Number"), rs
									.getString("Username"), rs
									.getString("Courier"), rs
									.getBoolean("At_Stop"), rs
									.getBoolean("Picked_Up"), rs
									.getString("Pick_Up_Date"), rs
									.getBoolean("Returned")));
				}
			}
			catch (Exception e)
			{
				if (e.getMessage().equals("database is locked"))
				{
					loadPackages(allStops, stop);
				}
				else
				{
					JOptionPane.showMessageDialog(null,
							"Error Connecting to Database\n" + e.getMessage());
				}
			}
			finally
			{
				try
				{
					if (rs != null)
					{
						rs.close();
					}
					if (statement != null)
					{
						statement.close();
					}
					if (readConn != null)
					{
						readConn.close();
					}
				}
				catch (Exception e)
				{
					// Do nothing
				}
			}
		}
		if (!isSetup)
		{
			JOptionPane.showMessageDialog(null,
					"Successfully Loaded:\nFaculty/Staff:" + facStaff.size()
							+ "\nStudents:" + students.size() + "\nStops:"
							+ stops.size() + "\nRoutes:" + routes.size()
							+ "\nCouriers:" + couriers.size() + "\nPackages:"
							+ packages.size());
			facStaff = null;
			students = null;
			isSetup = true;
		}

	}

	public boolean checkUser()
	{
		int index = 0;
		Statement s = null;
		ResultSet rs = null;
		try
		{
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			s = readConn.createStatement();
			rs = s.executeQuery("select * from User where Active=1;");

			while (rs.next())
			{
				index++;
			}
		}
		catch (Exception e)
		{
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (s != null)
				{
					s.close();
				}
				if (readConn != null)
				{
					readConn.close();
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
		}
		if (index == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	// /---ETL---///
	public void ETL(Person p)
	{
		PreparedStatement statement = null;
		try
		{
			writeConn = DriverManager
					.getConnection("jdbc:sqlite:" + dbLocation);
			statement = writeConn
					.prepareStatement("update Person set ASU_Email=?, ID_Number=? where First_Name like ? and Last_Name like ? and Number=?;");
			statement.setString(1, p.getEmail());
			statement.setString(2, p.getID());
			statement.setString(3, "%" + p.getFirstName() + "%");
			statement.setString(4, "%" + p.getLastName() + "%");
			statement.setString(5, p.getBox());

			int count = statement.executeUpdate();

			if (count == 0)
			{
				statement = writeConn
						.prepareStatement("insert into Person(ID_Number, ASU_Email, First_Name, Last_Name, Number, stop_id) values(?,?,?,?,?,?);");
				statement.setString(1, p.getID());
				String email = p.getEmail();
				String emailEnd = "";
				int index = 0;
				while (email.charAt(index) != '@')
				{
					index++;
				}
				// index++;
				for (int x = index; x < email.length(); x++)
				{
					emailEnd += email.charAt(x);
				}

				if (emailEnd.equals("grizzlies.adams.edu"))
				{
					for (int i = 0; i < stops.size(); i++)
					{
						if (stops.get(i).getType())
						{
							statement.setInt(6, stops.get(i).getID());
							break;
						}
					}
				}
				else
				{
					statement.setInt(6, 1);
				}
				statement.setString(2, email);
				statement.setString(3, p.getFirstName());
				statement.setString(4, p.getLastName());
				statement.setString(5, p.getBox());

				statement.execute();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ETL Error");
		}
		finally
		{
			try
			{
				if (statement != null)
				{
					statement.close();
				}
				if (writeConn != null)
				{
					writeConn.close();
				}
			}
			catch (Exception e)
			{
				// Ignore we are closing up
			}
		}
	}

	public void createPerson(String person)
	{
		// Delimitated by ',' or ';' not sure which yet
		String firstName = "";
		String lastName = "";
		String email = "";
		String idNumber = "";
		String boxNumber = "";
		String building = "";

		int index = 0;
		// Main Loop
		while (index < person.length())
		{
			// First Name
			while (person.charAt(index) != ',')
			{
				firstName += person.charAt(index);
				index++;
			}
			index++;
			// Last Name
			while (person.charAt(index) != ',')
			{
				lastName += person.charAt(index);
				index++;
			}
			index++;
			// Email
			while (person.charAt(index) != ',')
			{
				email += person.charAt(index);
				index++;
			}
			index++;
			// idNumber
			while (person.charAt(index) != ',')
			{
				idNumber += person.charAt(index);
				index++;
			}
			index++;
			// boxNumber
			while (person.charAt(index) != ',')
			{
				boxNumber += person.charAt(index);
				index++;
			}
			index++;
			// Building
			while (index < person.length())
			{
				building += person.charAt(index);
				index++;
			}
		}

		ETL(new Person(firstName, lastName, email, idNumber, boxNumber,
				building));
	}

	// /---Packages---///
	public void addPackage(Package p)
	{
		PreparedStatement statement = null;
		ResultSet rs = null;
		PreparedStatement s2 = null;
		try
		{
			// Create Insertion String
			writeConn = DriverManager
					.getConnection("jdbc:sqlite:" + dbLocation);
			statement = writeConn
					.prepareStatement("insert into Package(Tracking_Number, Date, ASU_Email, First_Name, Last_Name, Box_Number, At_Stop, Picked_Up, stop_id, courier_id, processor, Returned) values(?,?,?,?,?,?,?,?,?,?,?,?);");

			statement.setString(1, p.getTrackNum());
			statement.setString(2, p.getDate());
			statement.setString(3, p.getEmail());
			statement.setString(4, p.getFName());
			statement.setString(5, p.getLName());
			statement.setString(6, p.getBoxNum());
			statement.setBoolean(7, false);
			statement.setBoolean(8, false);
			for (int i = 0; i < stops.size(); i++)
			{
				if (stops.get(i).getName().equals(p.getStop()))
				{
					statement.setInt(9, stops.get(i).getID());
					break;
				}
			}

			s2 = writeConn
					.prepareStatement("select courier_id from Courier where Name=?;");
			s2.setString(1, p.getCourier());
			rs = s2.executeQuery();
			while (rs.next())
			{
				statement.setInt(10, rs.getInt("courier_id"));
			}

			s2 = writeConn
					.prepareStatement("select user_id from User where User_Name=?;");
			s2.setString(1, p.getUser());
			rs = s2.executeQuery();
			while (rs.next())
			{
				statement.setInt(11, rs.getInt("user_id"));
			}
			statement.setBoolean(12, false);

			statement.execute();
			packages.add(p);
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
			{
				addPackage(p);
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (statement != null)
				{
					statement.close();
				}
				if (s2 != null)
				{
					s2.close();
				}
				if (writeConn != null)
				{
					writeConn.close();
				}
			}
			catch (Exception e)
			{
				// Ignore we are closing up
			}
		}
	}

	@SuppressWarnings("resource")
	public void updatePackage(String tNumber, boolean value)
	{
		PreparedStatement statement = null;
		try
		{
			writeConn = DriverManager
					.getConnection("jdbc:sqlite:" + dbLocation);
			statement = writeConn
					.prepareStatement("select At_Stop from Package where Tracking_Number=?;");
			ResultSet rs = statement.executeQuery();
			if (rs.getBoolean("At_Stop"))
			{
				statement = writeConn
						.prepareStatement("update Package set Picked_Up=?, Pick_Up_Date=? where Tracking_Number=?;");
				statement.setBoolean(1, value);
				Date d = new Date();
				String date = DateFormat.getDateInstance(DateFormat.SHORT)
						.format(d);
				statement.setString(2, date);
				statement.setString(3, tNumber);
				statement.execute();
			}
			else
			{
				statement = writeConn
						.prepareStatement("update Package set At_Stop=? where Tracking_Number=?;");
				statement.setBoolean(1, value);
				statement.setString(2, tNumber);
				statement.execute();
			}
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
			{
				updatePackage(tNumber, value);
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (statement != null)
				{
					statement.close();
				}
				if (writeConn != null)
				{
					writeConn.close();
				}
			}
			catch (Exception e)
			{
				// Ignore we are closing up
			}
		}
	}

	public void updatePackage(String tNumber, boolean atStop, boolean pickedUp,
			String stop)
	{
		PreparedStatement statement = null;
		try
		{
			writeConn = DriverManager
					.getConnection("jdbc:sqlite:" + dbLocation);
			if (pickedUp)
			{
				statement = writeConn
						.prepareStatement("update Package set At_Stop=?, Picked_Up=?, Pick_Up_Date=?, stop_id=? where Tracking_Number like ?;");
				Date d = new Date();
				java.sql.Date sDate = new java.sql.Date(d.getTime());
				statement.setBoolean(1, atStop);
				statement.setBoolean(2, pickedUp);
				statement.setString(3, sDate.toString());
				for (int i = 0; i < stops.size(); i++)
				{
					if (stops.get(i).getName().equals(stop))
					{
						statement.setInt(4, stops.get(i).getID());
						break;
					}
				}
				statement.setString(5, "%" + tNumber + "%");
			}
			else
			{
				statement = writeConn
						.prepareStatement("update Package set At_Stop=?, Picked_Up=?, Pick_Up_Date='', stop_id=? where Tracking_Number like ?;");
				statement.setBoolean(1, atStop);
				statement.setBoolean(2, pickedUp);
				for (int i = 0; i < stops.size(); i++)
				{
					if (stops.get(i).getName().equals(stop))
					{
						statement.setInt(3, stops.get(i).getID());
						break;
					}
				}
				statement.setString(4, tNumber);
			}
			statement.execute();
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
			{
				updatePackage(tNumber, atStop, pickedUp, stop);
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (statement != null)
				{
					statement.close();
				}
				if (writeConn != null)
				{
					writeConn.close();
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
		}
	}

	public void returnPackage(String tNumber)
	{
		PreparedStatement statement = null;
		try
		{
			writeConn = DriverManager
					.getConnection("jdbc:sqlite:" + dbLocation);
			statement = writeConn
					.prepareStatement("update Package set Returned=? where Tracking_Number like ?;");
			statement.setBoolean(1, true);
			statement.setString(2, "%" + tNumber + "%");
			statement.execute();
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
			{
				returnPackage(tNumber);
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (statement != null)
				{
					statement.close();
				}
				if (writeConn != null)
				{
					writeConn.close();
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
		}
	}

	// /---Stops---///
	public void addStop(String name, boolean isUsed, String route,
			int route_order, boolean student)
	{
		PreparedStatement statement = null;
		try
		{
			writeConn = DriverManager
					.getConnection("jdbc:sqlite:" + dbLocation);
			// Statement s = writeConn.createStatement();
			// s.execute("update Stop set Student=0 where Student=1;");
			statement = writeConn
					.prepareStatement("insert into Stop(Name, route_id, Is_Used, route_order, Student) values (?,?,?,?,?);");
			statement.setString(1, name);
			for (int i = 0; i < routes.size(); i++)
			{
				if (routes.get(i).getName().equals(route))
				{
					statement.setInt(2, routes.get(i).getID());
					break;
				}
			}
			// Hopefully its true(but you never know)
			statement.setBoolean(3, isUsed);
			statement.setInt(4, route_order);
			statement.setBoolean(5, student);
			statement.execute();
			JOptionPane.showMessageDialog(null, "Stop " + name + " Added");
		}
		catch (Exception e)
		{
			// Error with database Connection
			if (e.getMessage().equals("database is locked"))
			{
				addStop(name, isUsed, route, route_order, student);
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (statement != null)
				{
					statement.close();
				}
				if (writeConn != null)
				{
					writeConn.close();
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
			loadStops();
		}
	}

	public void updateStop(String name, boolean isUsed, String route,
			int route_order)
	{
		PreparedStatement statement = null;
		try
		{
			writeConn = DriverManager
					.getConnection("jdbc:sqlite:" + dbLocation);
			statement = writeConn
					.prepareStatement("update Stop set Name=?, Is_Used=?, route_id=?, route_order=? where stop_id=?;");
			statement.setString(1, name);
			// Hopefully its true(but you never know)
			statement.setBoolean(2, isUsed);
			for (int i = 0; i < routes.size(); i++)
			{
				if (routes.get(i).getName().equals(route))
				{
					statement.setInt(3, routes.get(i).getID());
					break;
				}
			}
			statement.setInt(4, route_order);
			for (int i = 0; i < stops.size(); i++)
			{
				if (stops.get(i).getName().equals(name))
				{
					statement.setInt(5, stops.get(i).getID());
					break;
				}
			}

			statement.execute();
		}
		catch (Exception e)
		{
			// Error with database Connection
			if (e.getMessage().equals("database is locked"))
			{
				updateStop(name, isUsed, route, route_order);
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (statement != null)
				{
					statement.close();
				}
				if (writeConn != null)
				{
					writeConn.close();
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
			loadStops();
		}

	}

	// /---Routes---///
	public void addRoute(String route)
	{
		PreparedStatement statement = null;
		try
		{
			writeConn = DriverManager
					.getConnection("jdbc:sqlite:" + dbLocation);
			statement = writeConn
					.prepareStatement("insert into Route(Name) values(?);");
			statement.setString(1, route);
			statement.execute();
			loadRoutes();
			JOptionPane.showMessageDialog(null, "Route Created");
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
			{
				addRoute(route);
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (statement != null)
				{
					statement.close();
				}
				if (writeConn != null)
				{
					writeConn.close();
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
		}
	}

	public void updateRoute(String previousName, String currentName)
	{
		PreparedStatement statement = null;
		try
		{
			writeConn = DriverManager
					.getConnection("jdbc:sqlite:" + dbLocation);
			statement = writeConn
					.prepareStatement("update Routes Route(Name) set Name=? where Name=?;");
			statement.setString(1, currentName);
			statement.setString(2, previousName);
			statement.execute();
			JOptionPane.showMessageDialog(null, "Updated " + previousName
					+ " to " + currentName);
			for (int i = 0; i < routes.size(); i++)
			{
				if (routes.get(i).getName().equals(previousName))
				{
					routes.get(i).setName(currentName);
					break;
				}
			}
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
			{
				updateRoute(previousName, currentName);
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (statement != null)
				{
					statement.close();
				}
				if (writeConn != null)
				{
					writeConn.close();
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
		}
	}

	// /---Person---///
	public void addPerson(Person p)
	{
		// Do Person logic
		PreparedStatement statement = null;
		try
		{
			writeConn = DriverManager
					.getConnection("jdbc:sqlite:" + dbLocation);
			statement = writeConn
					.prepareStatement("insert into Person(ID_Number, ASU_Email, First_Name, Last_Name, Number, stop_id) values(?,?,?,?,?,?);");
			statement.setString(1, p.getID());
			statement.setString(2, p.getEmail());
			statement.setString(3, p.getFirstName());
			statement.setString(4, p.getLastName());
			statement.setString(5, p.getBox());
			for (int i = 0; i < stops.size(); i++)
			{
				if (stops.get(i).getName().equals(p.getStop()))
				{
					statement.setInt(6, stops.get(i).getID());
					break;
				}
			}
			statement.execute();
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
			{
				addPerson(p);
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (statement != null)
				{
					statement.close();
				}
				if (writeConn != null)
				{
					writeConn.close();
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
		}
	}

	public void updatePerson(Person p)
	{
		// Update Person logic
		PreparedStatement statement = null;
		try
		{
			writeConn = DriverManager
					.getConnection("jdbc:sqlite:" + dbLocation);
			statement = writeConn
					.prepareStatement("update Person set ASU_Email=?, stop_id=?, ID_Number=? where First_Name=? and Last_Name=? and Number=?;");
			statement.setString(1, p.getEmail());
			for (int i = 0; i < stops.size(); i++)
			{
				if (stops.get(i).getName().equals(p.getStop()))
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
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
			{
				updatePerson(p);
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (statement != null)
				{
					statement.close();
				}
				if (writeConn != null)
				{
					writeConn.close();
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
		}
	}

	// /---Couriers---///
	public void addCourier(String courier, boolean isUsed)
	{
		PreparedStatement statement = null;
		try
		{
			writeConn = DriverManager
					.getConnection("jdbc:sqlite:" + dbLocation);
			statement = writeConn
					.prepareStatement("insert into Courier(Name, Is_Used) values(?,?);");
			statement.setString(1, courier);
			statement.setBoolean(2, isUsed);
			statement.execute();
			JOptionPane
					.showMessageDialog(null, "Courier " + courier + " Added");
			loadCouriers();
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
			{
				addCourier(courier, isUsed);
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (statement != null)
				{
					statement.close();
				}
				if (writeConn != null)
				{
					writeConn.close();
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
		}
	}

	public void removeCourier(String courier)
	{
		// facade for deleting a courier
		PreparedStatement statement = null;
		try
		{
			writeConn = DriverManager
					.getConnection("jdbc:sqlite:" + dbLocation);
			statement = writeConn
					.prepareStatement("update Courier set Is_Used=?;");
			statement.setBoolean(1, false);
			statement.execute();
			JOptionPane.showMessageDialog(null, "Courier " + courier
					+ " Removed");
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
			{
				removeCourier(courier);
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (statement != null)
				{
					statement.close();
				}
				if (writeConn != null)
				{
					writeConn.close();
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
		}
	}

	// /---Package Searching---///
	public List<Package> findPackage(String beginDate, String endDate)
	{
		List<Package> results = new ArrayList<Package>();
		PreparedStatement statement = null;
		ResultSet rs = null;

		try
		{
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = readConn
					.prepareStatement("select Package.*, Courier.Name AS 'Courier', Stop.Name AS 'Stop', User.User_Name AS 'Username' "
							+ "from Package, Courier, Stop, User "
							+ "where Package.courier_id = Courier.courier_id and Package.stop_id = Stop.stop_id and User.user_id = Package.processor AND Package.Date between ? and ?;");
			statement.setString(1, beginDate);
			statement.setString(2, endDate);
			rs = statement.executeQuery();

			results = processResults(rs);
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
			{
				results = findPackage(beginDate, endDate);
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (statement != null)
				{
					statement.close();
				}
				if (readConn != null)
				{
					readConn.close();
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
		}

		return results;
	}

	public List<Package> findPackage(String tNumber)
	{
		List<Package> results = new ArrayList<Package>();
		PreparedStatement statement = null;
		ResultSet rs = null;

		try
		{
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = readConn
					.prepareStatement("select Package.*, Courier.Name AS 'Courier', Stop.Name AS 'Stop', User.User_Name AS 'Username' "
							+ "from Package, Courier, Stop, User "
							+ "where Package.courier_id = Courier.courier_id and Package.stop_id = Stop.stop_id and User.user_id = Package.processor AND Package.Tracking_Number like ?;");
			statement.setString(1, "%" + tNumber + "%");
			rs = statement.executeQuery();
			results = processResults(rs);
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
			{
				results = findPackage(tNumber);
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (statement != null)
				{
					statement.close();
				}
				if (readConn != null)
				{
					readConn.close();
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
		}

		return results;
	}

	public List<Package> findPackage(boolean delivered, boolean pickedUp)
	{
		List<Package> results = new ArrayList<Package>();
		PreparedStatement statement = null;
		ResultSet rs = null;

		try
		{
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = readConn
					.prepareStatement("select Package.*, Courier.Name AS 'Courier', Stop.Name AS 'Stop', User.User_Name AS 'Username' "
							+ "from Package, Courier, Stop, User "
							+ "where Package.courier_id = Courier.courier_id and Package.stop_id = Stop.stop_id and User.user_id = Package.processor AND  Package.At_Stop=? and Package.Picked_Up=?;");
			statement.setBoolean(1, delivered);
			statement.setBoolean(2, pickedUp);

			rs = statement.executeQuery();

			results = processResults(rs);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Connecting to Database");
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (statement != null)
				{
					statement.close();
				}
				if (readConn != null)
				{
					readConn.close();
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
		}

		return results;
	}

	public List<Package> findPackage(String tNumber, boolean delivered,
			boolean pickedUp)
	{
		List<Package> results = new ArrayList<Package>();
		PreparedStatement statement = null;
		ResultSet rs = null;

		try
		{
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = readConn
					.prepareStatement("select Package.*, Courier.Name AS 'Courier', Stop.Name AS 'Stop', User.User_Name AS 'Username' "
							+ "from Package, Courier, Stop, User "
							+ "where Package.courier_id = Courier.courier_id and Package.stop_id = Stop.stop_id and User.user_id = Package.processor AND Package.Tracking_Number like ? and Package.At_Stop=? and Package.Picked_Up=?;");
			statement.setString(1, tNumber);
			statement.setBoolean(2, delivered);
			statement.setBoolean(3, pickedUp);

			rs = statement.executeQuery();

			results = processResults(rs);
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
			{
				results = findPackage(tNumber, delivered, pickedUp);
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (statement != null)
				{
					statement.close();
				}
				if (readConn != null)
				{
					readConn.close();
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
		}

		return results;
	}

	public List<Package> searchPackages(String search, int location)
	{
		List<Package> results = new ArrayList<Package>();
		if(search.equals(""))
		{
		}
		else
		{
			location = SEARCH_CONTAINS;// Remove later if API is enhanced
			PreparedStatement statement = null;
			ResultSet rs = null;
			try
			{
				readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
				statement = readConn
						.prepareStatement("select Package.* "
								+ "from Package "
								+ "where Package.Tracking_Number like ? or Package.Date like ? or Package.ASU_Email like ? or Package.First_Name like ? or Package.Last_Name like ? or Package.Box_Number like ?;");
				switch (location)
				{
					case SEARCH_CONTAINS:// Contains
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
					case SEARCH_BEGINS_WITH:// Begins With
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
					case SEARCH_ENDS_WITH:// Ends With
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
				rs = statement.executeQuery();
				
				//Process the Results(OLD STYLE)
				while(rs.next())
				{
					statement = readConn.prepareStatement("select Stop.Name from Stop where stop.stop_id = ?;");
					statement.setInt(1, rs.getInt("stop_id"));
					ResultSet stop = statement.executeQuery();
					
					statement = readConn.prepareStatement("select Courier.Name from Courier where courier_id = ?;");
					statement.setInt(1, rs.getInt("courier_id"));
					ResultSet courier = statement.executeQuery();
					
					statement = readConn.prepareStatement("select User.User_Name from User where User.user_id = ?;");
					statement.setInt(1, rs.getInt("processor"));
					ResultSet user = statement.executeQuery();
					
					results.add(new Package(rs.getString("First_Name"), rs
							.getString("Last_Name"), rs.getString("ASU_Email"), rs
							.getString("Date"), rs.getString("Box_Number"), stop
							.getString("Name"), rs.getString("Tracking_Number"), user
							.getString("User_Name"), courier.getString("Name"), rs
							.getBoolean("At_Stop"), rs.getBoolean("Picked_Up"), rs
							.getString("Pick_Up_Date"), rs.getBoolean("Returned")));
					
					try
					{
						if (stop != null)
						{
							stop.close();
						}
						if (courier != null)
						{
							courier.close();
						}
						if (user != null)
						{
							user.close();
						}
					}
					catch(Exception SQLException)
					{
						stop = null;
						courier = null;
						user = null;
					}
				}
				
			}
			catch (Exception e)
			{
				System.out.println("Error Encountered");
				if (e.getMessage().equals("database is locked"))
				{
					results = searchPackages(search, location);
				}
				else
				{
					JOptionPane.showMessageDialog(null,
							"Error Connecting to Database");
				}
			}
			finally
			{
				try
				{
					if (rs != null)
					{
						rs.close();
					}
					if (statement != null)
					{
						statement.close();
					}
					if (readConn != null)
					{
						readConn.close();
					}
				}
				catch (Exception e)
				{
					// Ignore we are closing up
				}
			}
		}

		return results;
	}

	public List<Package> searchPackages(String search, int location,
			String beginDate, String endDate)
	{
		List<Package> results = new ArrayList<Package>();
		location = SEARCH_CONTAINS;// Remove later if API is enhanced
		PreparedStatement statement = null;
		ResultSet rs = null;
		try
		{
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = readConn
					.prepareStatement("select Package.* "
							+ "from Package "
							+ "where Package.Tracking_Number like ? or Package.ASU_Email like ? or Package.First_Name like ? or Package.Last_Name like ? or Package.Box_Number like ? where Package.Date between ? and ?");
			switch (location)
			{
				case SEARCH_CONTAINS:
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
				case SEARCH_BEGINS_WITH:
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
				case SEARCH_ENDS_WITH:
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

			rs = statement.executeQuery();

			//Process the Results(OLD STYLE)
			while(rs.next())
			{
				statement = readConn.prepareStatement("select Stop.Name from Stop where stop.stop_id = ?;");
				statement.setInt(1, rs.getInt("stop_id"));
				ResultSet stop = statement.executeQuery();
				
				statement = readConn.prepareStatement("select Courier.Name from Courier where courier_id = ?;");
				statement.setInt(1, rs.getInt("courier_id"));
				ResultSet courier = statement.executeQuery();
				
				statement = readConn.prepareStatement("select User.User_Name from User where User.user_id = ?;");
				statement.setInt(1, rs.getInt("processor"));
				ResultSet user = statement.executeQuery();
				
				results.add(new Package(rs.getString("First_Name"), rs
						.getString("Last_Name"), rs.getString("ASU_Email"), rs
						.getString("Date"), rs.getString("Box_Number"), stop
						.getString("Name"), rs.getString("Tracking_Number"), user
						.getString("User_Name"), courier.getString("Name"), rs
						.getBoolean("At_Stop"), rs.getBoolean("Picked_Up"), rs
						.getString("Pick_Up_Date"), rs.getBoolean("Returned")));
				
				try
				{
					if (stop != null)
					{
						stop.close();
					}
					if (courier != null)
					{
						courier.close();
					}
					if (user != null)
					{
						user.close();
					}
				}
				catch(Exception SQLException)
				{
					stop = null;
					courier = null;
					user = null;
				}
			}

		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
			{
				results = searchPackages(search, location, beginDate, endDate);
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (statement != null)
				{
					statement.close();
				}
				if (readConn != null)
				{
					readConn.close();
				}
			}
			catch (Exception e)
			{
				// Ignore we are closing up
			}
		}

		return results;
	}

	// /---Processes Package Search Results---///
	public List<Package> processResults(ResultSet rs)
	{
		List<Package> results = new ArrayList<Package>();

		try
		{
			while (rs.next())
			{
				results.add(new Package(rs.getString("First_Name"), rs
						.getString("Last_Name"), rs.getString("ASU_Email"), rs
						.getString("Date"), rs.getString("Box_Number"), rs
						.getString("Stop"), rs.getString("Tracking_Number"), rs
						.getString("Username"), rs.getString("Courier"), rs
						.getBoolean("At_Stop"), rs.getBoolean("Picked_Up"), rs
						.getString("Pick_Up_Date"), rs.getBoolean("Returned")));
			}
		}
		catch (Exception e)
		{
			results = processResults(rs);
		}
		finally
		{
		}

		return results;
	}

	// /---People Searching---///
	public List<Person> findPerson(String firstName, String lastName)
	{
		List<Person> results = new ArrayList<Person>();
		PreparedStatement statement = null;
		ResultSet rs = null;

		try
		{
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			firstName = "%" + firstName + "%";
			lastName = "%" + lastName + "%";
			statement = readConn
					.prepareStatement("select * from Person where First_Name like ? and Last_Name like ?;");
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			rs = statement.executeQuery();

			while (rs.next())
			{
				String idNumber = rs.getString("ID_Number");
				String email = rs.getString("ASU_Email");
				String fName = rs.getString("First_Name");
				String lName = rs.getString("Last_Name");
				String suite = rs.getString("Number");
				String stop = "";
				for (int i = 0; i < stops.size(); i++)
				{
					if (stops.get(i).getID() == rs.getInt("stop_id"))
					{
						stop = stops.get(i).getName();
					}
				}

				results.add(new Person(fName, lName, email, idNumber, suite,
						stop));
			}
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
			{
				results = findPerson(firstName, lastName);
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (statement != null)
				{
					statement.close();
				}
				if (readConn != null)
				{
					readConn.close();
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
		}

		return results;
	}

	public List<Person> findPerson(String firstName, String lastName,
			String boxNumber)
	{
		List<Person> results = new ArrayList<Person>();
		PreparedStatement statement = null;
		ResultSet rs = null;

		try
		{
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			firstName = "%" + firstName + "%";
			lastName = "%" + lastName + "%";
			statement = readConn
					.prepareStatement("select * from Person where First_Name like ? and Last_Name like ? and Number=?;");
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, boxNumber);
			rs = statement.executeQuery();

			while (rs.next())
			{
				String idNumber = rs.getString("ID_Number");
				String email = rs.getString("ASU_Email");
				String fName = rs.getString("First_Name");
				String lName = rs.getString("Last_Name");
				String suite = rs.getString("Number");
				String stop = "";
				for (int i = 0; i < stops.size(); i++)
				{
					if (stops.get(i).getID() == rs.getInt("stop_id"))
					{
						stop = stops.get(i).getName();
					}
				}

				results.add(new Person(fName, lName, email, idNumber, suite,
						stop));
			}
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
			{
				results = findPerson(firstName, lastName, boxNumber);
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (statement != null)
				{
					statement.close();
				}
				if (readConn != null)
				{
					readConn.close();
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
		}

		return results;
	}

	// /---Get Methods---///
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

	// /---Printing---///
	public List<Package> getPackagesFromStop(String stop)
	{
		List<Package> results = new ArrayList<Package>();

		for (int i = 0; i < packages.size(); i++)
		{
			if (packages.get(i).getStop().equals(stop))
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

		for (int i = 0; i < routes.size(); i++)
		{
			if (routes.get(i).getName().equals(route))
			{
				route_id = routes.get(i).getID();
				break;
			}
		}

		for (int i = 0; i < stops.size(); i++)
		{
			if (stops.get(i).getRouteID() == route_id)
			{
				results.add(stops.get(i));
			}
		}
		return results;
	}

	// /---Closing Up---///
	public void closeUp()
	{
		try
		{
			readConn.close();
		}
		catch (Exception e)
		{
			// Ignore it
			// We are shutting down
		}
	}

	// /---Get Unassigned Items---///
	public List<Stop> getUnassignedStops()
	{
		List<Stop> results = new ArrayList<Stop>();

		for (int i = 0; i < stops.size(); i++)
		{
			if (stops.get(i).getRouteID() == 1)
			{
				results.add(stops.get(i));
			}
		}

		return results;
	}

	// /---User Stuff---///
	public User login(String username, int password)
	{
		User u = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try
		{
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			statement = readConn
					.prepareStatement("select * from User where User_Name=? and Password=? and Active=1;");
			statement.setString(1, username);
			statement.setInt(2, password);
			rs = statement.executeQuery();

			while (rs.next())
			{
				String uname = rs.getString("User_Name");
				String firstName = rs.getString("First_Name");
				String lastName = rs.getString("Last_Name");
				boolean admin = rs.getBoolean("Admin");
				u = new User(uname, firstName, lastName, admin);
			}
			return u;
		}
		catch (Exception e)
		{
			// Assume invalid login
			if (e.getMessage().equals("database is locked"))
			{
				return login(username, password);
			}
			else
			{
				return null;
			}
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (statement != null)
				{
					statement.close();
				}
				if (readConn != null)
				{
					readConn.close();
				}
			}
			catch (Exception e)
			{
				// Ignore we are closing up
			}
		}
	}

	public boolean verifyUser(String username)
	{
		int index = 0;
		PreparedStatement s = null;
		ResultSet rs = null;
		try
		{
			readConn = DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
			s = readConn
					.prepareStatement("select * from User where User_Name=? and Active=1;");
			s.setString(1, username);
			rs = s.executeQuery();
			while (rs.next())
			{
				index++;
			}
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
			{
				return verifyUser(username);
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Error Connecting to Database");
			}
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (s != null)
				{
					s.close();
				}
				if (readConn != null)
				{
					readConn.close();
				}
			}
			catch (Exception e)
			{
				// Ignore we are closing up
			}
		}
		if (index == 0)
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
		PreparedStatement statement = null;
		try
		{
			writeConn = DriverManager
					.getConnection("jdbc:sqlite:" + dbLocation);
			statement = writeConn
					.prepareStatement("insert into User(User_Name, First_Name,Last_Name,Password, Admin,Active) values(?,?,?,?,?,1);");
			statement.setString(1, u.getUser());
			statement.setString(2, u.getFName());
			statement.setString(3, u.getLName());
			statement.setInt(4, password);
			statement.setBoolean(5, u.getAdmin());

			statement.execute();
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
			{
				createUser(u, password);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Erro Creating User "
						+ u.getUser());
			}

		}
		finally
		{
			try
			{
				if (writeConn != null)
				{
					writeConn.close();
				}
				if (statement != null)
				{
					statement.close();
				}
			}
			catch (Exception e)
			{

			}
		}
	}

	public void deleteUser(String username)
	{
		PreparedStatement statement = null;
		switch (JOptionPane.showConfirmDialog(null, "You are about to Delete: "
				+ username + "\nDo You Wish to Continue?"))
		{
			case JOptionPane.YES_OPTION:
			{
				// Execute a statement
				try
				{
					writeConn = DriverManager.getConnection("jdbc:sqlite:"
							+ dbLocation);
					statement = writeConn
							.prepareStatement("update User set Active=0 where User_Name=?;");
					statement.setString(1, username);
					statement.execute();
					JOptionPane.showMessageDialog(null, "User " + username
							+ " Deleted");
				}
				catch (Exception e)
				{
					if (e.getMessage().equals("database is locked"))
					{
						deleteUser(username);
					}
					else
					{
						JOptionPane.showMessageDialog(null,
								"Error Deleting User " + username);
					}

				}
				finally
				{
					try
					{
						if (writeConn != null)
						{
							writeConn.close();
						}
						if (statement != null)
						{
							statement.close();
						}
					}
					catch (Exception e)
					{

					}
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

	// /---Update Logic---///
	public void checkUpdate()
	{
		File update = new File("./update.up");
		if (update.exists())
		{
			// /Display the results and then delete the file

			updateViewer uView = new updateViewer(update);
			uView.setVisible(true);
		}
	}
}