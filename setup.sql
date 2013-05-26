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
Returned BOOLEAN NOT NULL,
FOREIGN KEY(stop_id) REFERENCES Stop(stop_id),
FOREIGN KEY(courier_id) REFERENCES Courier(courier_id)
FOREIGN KEY(processor) REFERENCES User(user_id)
);
CREATE TABLE FacStaff
(
id INTEGER PRIMARY KEY AUTOINCREMENT,
ID_Number varchar(50),
ASU_Email varchar(50),
First_Name varchar(50) NOT NULL,
Last_Name varchar(50) NOT NULL,
Suite_Number varchar(50),
stop_id int,
FOREIGN KEY(stop_id) REFERENCES Stop(stop_id)
);
CREATE TABLE Student
(
id INTEGER PRIMARY KEY AUTOINCREMENT,
ID_Number varchar(50),
ASU_Email varchar(50),
First_Name varchar(50) NOT NULL,
Last_Name varchar(50) NOT NULL,
Box_Number varchar(50),
stop_id int,
FOREIGN KEY(stop_id) REFERENCES Stop(stop_id)
);
CREATE TABLE User
(
user_id INTEGER PRIMARY KEY AUTOINCREMENT,
User_Name varchar(50) NOT NULL,
First_Name varchar(50) NOT NULL,
Last_Name varchar(50) NOT NULL,
Password INTEGER NOT NULL,
Admin BOOLEAN NOT NULL
);

insert into Route(Name) values('unassigned');
insert into Stop(Name, Is_Used, route_id) values('unassigned', 1, 1);

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