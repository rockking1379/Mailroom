package com.client.common;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.swing.JOptionPane;

public class ETL
{
	private File people;
	private Connection writeConn = null;
	private String dbLocation = null;
	private List<Stop> stops = null;
	private updateViewer viewer = null;

	// /---Constructor---///
	public ETL(ArrayList<Stop> stops, String dbLocation, Connection writeConn)
	{
		// Process Params
		this.writeConn = writeConn;
		this.dbLocation = dbLocation;
		this.stops = new ArrayList<Stop>();
		this.stops = stops;
	}

	// /---Running---///
	public boolean check()
	{
		File dir = new File("./");
		File[] files = dir.listFiles();
		boolean status = false;

		for (int i = 0; i < files.length; i++)
		{
			try
			{
				String fileName = files[i].getCanonicalPath();
				if (fileName.endsWith(".peep"))
				{
					viewer = new updateViewer();
					viewer.start();
					Thread.currentThread().sleep(500);
					people = files[i];
					status = true;
					break;
				}
			}
			catch (IOException | InterruptedException e)
			{
				// Ignore Error
			}
		}

		return status;
	}

	// /---Process File---///
	@SuppressWarnings("unused")
	public void process()
	{
		JOptionPane
				.showMessageDialog(null,
						"An ETL routine must be completed\nThis will take approximately 3-5 minutes");

		FileInputStream fStream = null;
		DataInputStream dis = null;
		BufferedReader br = null;

		Date start = new Date();

		try
		{
			fStream = new FileInputStream(people);
			dis = new DataInputStream(fStream);
			br = new BufferedReader(new InputStreamReader(dis));
			String person;
			while ((person = br.readLine()) != null)
			{
				createPerson(person);
			}
			br.close();
		}
		catch (Exception e)
		{
			// Not sure what i want to do yet
		}
		finally
		{
			try
			{
				if (fStream != null)
				{
					fStream.close();
				}
				if (dis != null)
				{
					dis.close();
				}
				if (br != null)
				{
					br.close();
				}
			}
			catch (Exception e)
			{
				// Do Nothing
			}
		}
		Date finish = new Date();
		long delta = finish.getTime() - start.getTime();
		Date dDate = new Date(delta);

		people.renameTo(new File("./people.oldpeep"));
		viewer.etlComplete(dDate);
		// JOptionPane.showMessageDialog(null, "ETL Complete\n" +
		// dDate.getMinutes() + ":" + dDate.getSeconds());
	}

	// /---ETL---///
	public void routine(Person p)
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
				viewer.etlUpdate("Added");

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
			else
			{
				viewer.etlUpdate("Updated");
			}
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("database is locked"))
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
					routine(p);
				}
				catch (Exception e2)
				{
					// Ignore we are closing up
				}
			}
			else
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "ETL Error\n"
						+ e.getMessage());
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

	// /---Process Data---///
	public void createPerson(String person)
	{
		// deliminated by ',' or ';' not sure which yet
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

		routine(new Person(firstName, lastName, email, idNumber, boxNumber,
				building));
	}
}
