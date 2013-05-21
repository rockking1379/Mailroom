<<<<<<< HEAD

package mailroom;
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
package com.client.common;
>>>>>>> origin/Tom

import javax.swing.JOptionPane;

import java.text.DateFormat;
import java.util.Date;
<<<<<<< HEAD

=======
=======
>>>>>>> origin/master
package mailroom;
>>>>>>> origin/Tom

import javax.swing.JOptionPane;

import java.util.*;
<<<<<<< HEAD

=======
<<<<<<< HEAD
=======
>>>>>>> origin/Tom
>>>>>>> origin/master
>>>>>>> origin/Tom

public class Package 
{
	private String firstName;
	private String lastName;
	private String email;
<<<<<<< HEAD

	private Date date;

	

=======
<<<<<<< HEAD
	private String date;
=======
<<<<<<< HEAD
	private Date date;
=======
	private String date;
>>>>>>> origin/Tom
>>>>>>> origin/master
>>>>>>> origin/Tom
	private String boxNumber;
	private String stop;
	private String trackingNumber;
	
<<<<<<< HEAD


=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> origin/master
>>>>>>> origin/Tom
	public Package()
	{
		//Wrong Constructor
		JOptionPane.showMessageDialog(null, "Cannot create an empty Package");
	}
	
<<<<<<< HEAD

=======
<<<<<<< HEAD
=======
>>>>>>> origin/Tom
>>>>>>> origin/master
>>>>>>> origin/Tom
	//Optimal
	public Package(String firstName, String lastName, String email, Date date, String boxNumber, String stop, String trackingNumber)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
<<<<<<< HEAD

		this.date = date;

		

=======
<<<<<<< HEAD
		this.date = new String(date.getMonth() + "-" + date.getDay() + "-" + date.getYear());
=======
<<<<<<< HEAD
		this.date = date;
=======
		this.date = new String(date.getMonth() + "-" + date.getDay() + "-" + date.getYear());
>>>>>>> origin/Tom
>>>>>>> origin/master
>>>>>>> origin/Tom
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
<<<<<<< HEAD

		this.date = date;

		

=======
<<<<<<< HEAD
		this.date = new String(date.getMonth() + "-" + date.getDay() + "-" + date.getYear());
=======
<<<<<<< HEAD
		this.date = date;
=======
		this.date = new String(date.getMonth() + "-" + date.getDay() + "-" + date.getYear());
>>>>>>> origin/Tom
>>>>>>> origin/master
>>>>>>> origin/Tom
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
<<<<<<< HEAD

		this.date = date;

		

=======
<<<<<<< HEAD
		this.date = new String(date.getMonth() + "-" + date.getDay() + "-" + date.getYear());
=======
<<<<<<< HEAD
		this.date = date;
=======
		this.date = new String(date.getMonth() + "-" + date.getDay() + "-" + date.getYear());
>>>>>>> origin/Tom
>>>>>>> origin/master
>>>>>>> origin/Tom
		this.boxNumber = "0";
		this.stop = "RICHARDSON";
		this.trackingNumber = trackingNumber;
	}
	
<<<<<<< HEAD

=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> origin/master
>>>>>>> origin/Tom
	public Package(String text, String text2, Date date2, String text3,
			String stop2, String text4) {
		// TODO Auto-generated constructor stub
	}

<<<<<<< HEAD

=======
<<<<<<< HEAD
=======
>>>>>>> origin/Tom
>>>>>>> origin/master
>>>>>>> origin/Tom
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
<<<<<<< HEAD
		return DateFormat.getDateInstance(DateFormat.SHORT).format(date).toString();

		

=======
<<<<<<< HEAD
		return date;
=======
<<<<<<< HEAD
		return DateFormat.getDateInstance(DateFormat.SHORT).format(date).toString();
=======
		return date;
>>>>>>> origin/Tom
>>>>>>> origin/master
>>>>>>> origin/Tom
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
