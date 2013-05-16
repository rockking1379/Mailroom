package com.client.common;

import javax.swing.JOptionPane;

import sun.util.calendar.BaseCalendar.Date;

public class Package 
{
	private String firstName;
	private String lastName;
	private String email;
	private String date;
	private String boxNumber;
	private String stop;
	private String trackingNumber;
	
	public Package()
	{
		//Wrong Constructor
		JOptionPane.showMessageDialog(null, "Cannot create an empty Package");
	}
	
	//Optimal
	public Package(String firstName, String lastName, String email, Date date, String boxNumber, String stop, String trackingNumber)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.date = new String(date.getMonth() + "-" + date.getDayOfMonth() + "-" + date.getYear());
		this.boxNumber = boxNumber;
		this.stop = stop;
		this.trackingNumber = trackingNumber;
	}
	
	//Faculty?
	public Package(String firstName, String lastName, String email, Date date, String stop, String trackingNumber)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.date = new String(date.getMonth() + "-" + date.getDayOfMonth() + "-" + date.getYear());
		this.boxNumber = "0";
		this.stop = stop;
		this.trackingNumber = trackingNumber;
	}
	
	//Leave in Richardson
	public Package(String firstName, String lastName, String email, Date date, String trackingNumber)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.date = new String(date.getMonth() + "-" + date.getDayOfMonth() + "-" + date.getYear());
		this.boxNumber = "0";
		this.stop = "RICHARDSON";
		this.trackingNumber = trackingNumber;
	}
	
	public String getFName()
	{
		return firstName;
	}
	public String getLName()
	{
		return lastName;
	}
	public String getEmail()
	{
		return email;
	}
	public String getDate()
	{
		return date;
	}
	public String getBoxNum()
	{
		return boxNumber;
	}
	public String getStop()
	{
		return stop;
	}
	public String getTrackNum()
	{
		return trackingNumber;
	}
}
