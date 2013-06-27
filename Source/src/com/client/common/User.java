package com.client.common;

public class User
{
	private String username;
	private String firstName;
	private String lastName;
	private boolean admin;

	// /---Constructors---///
	public User(String username, String firstName, String lastName)
	{
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		admin = false;
	}

	public User(String username, String firstName, String lastName,
			boolean admin)
	{
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.admin = admin;
	}

	// /---Get Methods---///
	public String getUser()
	{
		return username;
	}

	public String getFName()
	{
		return firstName;
	}

	public String getLName()
	{
		return lastName;
	}

	public boolean getAdmin()
	{
		return admin;
	}
}
