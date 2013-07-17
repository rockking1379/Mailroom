CREATE TABLE Route
(
	route_id INTEGER PRIMARY KEY AUTOINCREMENT,
	Name varchar(50) NOT NULL
);
CREATE TABLE Stop
(
	stop_id INTEGER PRIMARY KEY AUTOINCREMENT,
	Name varchar(50) NOT NULL,
	route_id int,
	Is_Used BOOLEAN NOT NULL,
	route_order int,
	Student BOOLEAN,
	FOREIGN KEY(route_id) REFERENCES Route(route_id)
);
CREATE TABLE Courier
(
	courier_id INTEGER PRIMARY KEY AUTOINCREMENT,
	Name varchar(50) NOT NULL,
	Is_used BOOLEAN NOT NULL
);
CREATE TABLE Package
(
	package_id INTEGER PRIMARY KEY AUTOINCREMENT,
	Tracking_Number varchar(50) NOT NULL,
	Date DATE NOT NULL,
	ASU_Email varchar(50) NOT NULL,
	First_Name varchar(50) NOT NULL,
	Last_Name varchar(50) NOT NULL,
	Box_Number varchar(50) NOT NULL,
	At_Stop BOOLEAN NOT NULL,
	Picked_Up BOOLEAN NOT NULL,
	Pick_Up_Date DATE,
	stop_id int,
	courier_id int,
	processor int,
	Returned BOOLEAN,
	FOREIGN KEY(stop_id) REFERENCES Stop(stop_id),
	FOREIGN KEY(courier_id) REFERENCES Courier(courier_id)
	FOREIGN KEY(processor) REFERENCES User(user_id)
);
CREATE TABLE Person
(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	ID_Number varchar(50),
	ASU_Email varchar(50),
	First_Name varchar(50) NOT NULL,
	Last_Name varchar(50) NOT NULL,
	Number varchar(50),
	stop_id int,
	Forward_Address varchar(150),
	FOREIGN KEY(stop_id) REFERENCES Stop(stop_id)
);
CREATE TABLE User
(
	user_id INTEGER PRIMARY KEY AUTOINCREMENT,
	User_Name varchar(50) NOT NULL,
	First_Name varchar(50) NOT NULL,
	Last_Name varchar(50) NOT NULL,
	Password INTEGER NOT NULL,
	Admin BOOLEAN NOT NULL,
	Active BOOLEAN,
);

insert into Route(Name) values('unassigned');

insert into Stop(Name, Is_Used, route_id, route_order, Student) values('unassigned', 1, 1, 0, 0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('AAO',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Academic Affairs',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Admissions',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('AITC',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Alumni/Foundation',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Art',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('AS&F',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Bookstore',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Business Office',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Community Partnership',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Computing Services',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Counseling & Career',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Counselor Education',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('EEO',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('English/ Communication',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Enrollment',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Extended Studies',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Facilities Office',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Facilities Warehouse',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Finance/ Administration',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Financial Aid',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Gingerbread House',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Graduate School',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('HGPPSL',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Hold for Pickup',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Housing',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('HPPE',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Human Resources',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Institutional Research',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Library',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Museum',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Music',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Nursing',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('One Stop',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Payroll',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Plachy',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Police Department',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('President',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Print Shop',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Purchasing',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Radio Station',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Records',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('REX',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('School of Business',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('SMT',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('SODEXO',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Student Affairs',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Student Life',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('SUB Office',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('SUB Mailroom',1,1,0,1);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('SVP Enrollment Manager',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Teacher Education',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Theatre',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Title V',1,1,0,0);
insert into Stop(Name, Is_Used, route_id, route_order, Student) values('Upward Bound',1,1,0,0);

insert into Courier(Name, Is_Used) values('Unknown', 1);
insert into Courier(Name, Is_Used) values('Bill Clarke Truck Lines', 1);
insert into Courier(Name, Is_Used) values('CWX Con-Way Western', 1);
insert into Courier(Name, Is_Used) values('DHL', 1);
insert into Courier(Name, Is_Used) values('FedEx Express', 1);
insert into Courier(Name, Is_Used) values('FedEx Freight', 1);
insert into Courier(Name, Is_Used) values('FedEx Ground', 1);
insert into Courier(Name, Is_Used) values('Gobins', 1);
insert into Courier(Name, Is_Used) values('UPS', 1);
insert into Courier(Name, Is_Used) values('USPS Mail', 1);
insert into Courier(Name, Is_Used) values('XPEDX', 1);

select * from Stop;
select * from Route;