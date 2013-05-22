package com.client.common;

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
	public Person(String firstName, String lastName, String email, String idNumber, String boxNumber, String building)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.idNumber = idNumber;
		this.boxNumber = boxNumber;
		this.stop = building;
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
	public String getBuilding()
	{
		return stop;
	}
	public Component getStop() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getBoxNumber() {
		return boxNumber;
	}
	public void setBoxNumber(String boxNumber) {
		this.boxNumber = boxNumber;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setStop(String stop) {
		this.stop = stop;
	}
}
