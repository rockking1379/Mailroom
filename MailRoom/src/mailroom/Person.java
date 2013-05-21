package mailroom;

public class Person 
{
	private String firstName;
	private String lastName;
	private String email;
	private String idNumber;
	private String boxNumber;
	private String building;
	
	//Faculty or Staff Constructor
	public Person(String firstName, String lastName, String email, String idNumber, String boxNumber, String building)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.idNumber = idNumber;
		this.boxNumber = boxNumber;
		this.building = building;
	}
	//Student Constructor
	public Person(String firstName, String lastName, String email, String idNumber, String boxNumber)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.idNumber = idNumber;
		this.boxNumber = boxNumber;
		this.building = "SUB";
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
		return building;
	}
<<<<<<< HEAD

=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> origin/master
>>>>>>> origin/Tom
	public Stop getStop() {
		// TODO Auto-generated method stub
		Stop s=  new Stop("SMT");
		return s;
	}
<<<<<<< HEAD

=======
<<<<<<< HEAD
=======
>>>>>>> origin/Tom
>>>>>>> origin/master
>>>>>>> origin/Tom
}
