package com.client.common;

public class Person
{
	private String firstName;
	private String lastName;
	private String email;
	private String idNumber;
	private String boxNumber;
	private String stop;

	// Faculty or Staff Constructor
	public Person(String firstName, String lastName, String email,
			String idNumber, String boxNumber, String building)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.idNumber = idNumber;
		this.boxNumber = boxNumber;
		this.stop = building;
	}

	// Student Constructor
	public Person(String firstName, String lastName, String email,
			String idNumber, String boxNumber, int stop)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.idNumber = idNumber;
		this.boxNumber = boxNumber;
		this.stop = Integer.toString(stop);
	}

	// /---Get Methods---///
	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public String getID()
	{
		return idNumber;
	}

	public String getBox()
	{
		return boxNumber;
	}

	public String getStop()
	{
		return stop;
	}

	// /---Set Methods---///
	public void setStop(String stop)
	{
		this.stop = stop;
	}
}
