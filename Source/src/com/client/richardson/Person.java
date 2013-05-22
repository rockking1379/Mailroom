package com.client.richardson;

import java.awt.Component;

public class Person 
{
	private String firstName;
	private String lastName;
	private String email;
	private String idNumber;
	private String boxNumber;
	private String stop;
	
	//Faculty or Staff Constructor
	public Person(String firstName, String lastName, String email, String idNumber, String boxNumber, String stop)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.idNumber = idNumber;
		this.boxNumber = boxNumber;
		this.stop = stop;
	}
	//Student Constructor
	public Person(String firstName, String lastName, String email, String idNumber, String boxNumber)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.idNumber = idNumber;
		this.boxNumber = boxNumber;
		this.stop = "SUB";
	}
	
	//Get Methods
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
	public void setStop(String s){
		stop = s;
	}
	
}
