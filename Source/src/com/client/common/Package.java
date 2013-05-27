package com.client.common;

public class Package 
{
	private String firstName;
	private String lastName;
	private String email;
	private String date;
	private String boxNumber;
	private String stop;
	private String trackingNumber;
	private String user;
	private String courier;
	
	//Optimal
	public Package(String firstName, String lastName, String email, String date, String boxNumber, String stop, String trackingNumber, String user, String courier)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.date =  date;
		this.boxNumber = boxNumber;
		this.stop = stop;
		this.trackingNumber = trackingNumber;
		this.user = user;
		this.courier = courier;
	}
	
	//Faculty?
	public Package(String firstName, String lastName, String email, String date, String stop, String trackingNumber, String user, String courier)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.date =  date;
		this.boxNumber = "0";
		this.stop = stop;
		this.trackingNumber = trackingNumber;
		this.user = user;
		this.courier = courier;
	}
	
	//Leave in Richardson
	public Package(String firstName, String lastName, String email, String date, String trackingNumber, String user, String courier)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.date =  date;
		this.boxNumber = "0";
		this.stop = "RICHARDSON";
		this.trackingNumber = trackingNumber;
		this.user = user;
		this.courier = courier;
	}
	
	///---Get Methods---///
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
	public String getCourier()
	{
		return courier;
	}
	public String getUser()
	{
		return user;
	}
}
