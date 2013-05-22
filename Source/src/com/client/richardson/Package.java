
package com.client.richardson;

import javax.swing.JOptionPane;

import java.text.DateFormat;
import java.util.Date;


import javax.swing.JOptionPane;

import java.util.*;


public class Package 
{
	private String firstName;
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setReturnToSender(boolean returnToSender) {
		this.returnToSender = returnToSender;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public void setBoxNumber(String boxNumber) {
		this.boxNumber = boxNumber;
	}


	public void setStop(String stop) {
		this.stop = stop;
	}


	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	private String lastName;
	private String email;
	private boolean returnToSender;

	private Date date;

	

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

		this.date = date;

		

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

		this.date = date;

		

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

		this.date = date;

		

		this.boxNumber = "0";
		this.stop = "RICHARDSON";
		this.trackingNumber = trackingNumber;
	}
	

	public Package(String text, String text2, Date date2, String text3,
			String stop2, String text4) {
		// TODO Auto-generated constructor stub
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
		return DateFormat.getDateInstance(DateFormat.SHORT).format(date).toString();

		

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
