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
	private boolean delivered;
	private boolean pickedUp;
	private boolean returned;
	private String pickUpDate;

	// Optimal
	public Package(String firstName, String lastName, String email,
			String date, String boxNumber, String stop, String trackingNumber,
			String user, String courier)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.date = date;
		this.boxNumber = boxNumber;
		this.stop = stop;
		this.trackingNumber = trackingNumber;
		this.user = user;
		this.courier = courier;
	}

	public Package(String firstName, String lastName, String email,
			String date, String boxNumber, String stop, String trackingNumber,
			String user, String courier, boolean delivered, boolean pickedUp)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.date = date;
		this.boxNumber = boxNumber;
		this.stop = stop;
		this.trackingNumber = trackingNumber;
		this.user = user;
		this.courier = courier;
		this.delivered = delivered;
		this.pickedUp = pickedUp;
	}

	public Package(String firstName, String lastName, String email,
			String date, String boxNumber, String stop, String trackingNumber,
			String user, String courier, boolean delivered, boolean pickedUp,
			String pickUpDate, boolean returned)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.date = date;
		this.boxNumber = boxNumber;
		this.stop = stop;
		this.trackingNumber = trackingNumber;
		this.user = user;
		this.courier = courier;
		this.delivered = delivered;
		this.pickedUp = pickedUp;
		this.pickUpDate = pickUpDate;
		this.returned = returned;
	}

	// Faculty?
	public Package(String firstName, String lastName, String email,
			String date, String stop, String trackingNumber, String user,
			String courier)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.date = date;
		this.boxNumber = "0";
		this.stop = stop;
		this.trackingNumber = trackingNumber;
		this.user = user;
		this.courier = courier;
	}

	// Leave in Richardson
	public Package(String firstName, String lastName, String email,
			String date, String trackingNumber, String user, String courier)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.date = date;
		this.boxNumber = "0";
		this.stop = "unassigned";
		this.trackingNumber = trackingNumber;
		this.user = user;
		this.courier = courier;
	}

	// /---Get Methods---///
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

	public boolean getDelivered()
	{
		return delivered;
	}

	public boolean getPickedUp()
	{
		return pickedUp;
	}

	public String getPickedUpDate()
	{
		return pickUpDate;
	}

	public boolean getReturn()
	{
		return returned;
	}

	// /---Set Methods---///
	public void setStop(String name)
	{
		this.stop = name;
	}

	public void setDelivered(boolean delivered)
	{
		this.delivered = delivered;
	}

	public void setPickedUp(boolean pickedUp)
	{
		this.pickedUp = pickedUp;
	}
}
