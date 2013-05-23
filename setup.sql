CREATE TABLE Route
(
route_id INTEGER PRIMARY KEY AUTOINCREMENT,
Name varchar(50) NOT NULL
)
;
CREATE TABLE Stop
(
stop_id INTEGER PRIMARY KEY AUTOINCREMENT,
Name varchar(50) NOT NULL,
route_id int,
Is_Used BOOLEAN NOT NULL,
FOREIGN KEY(route_id) REFERENCES Route(route_id)
)
;
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
FOREIGN KEY(stop_id) REFERENCES Stop(stop_id)
);

insert into Route(Name) values('unassigned');
insert into Stop(Name, Is_Used, route_id) values('unassigned', 1, 1);

select * from Stop;
select * from Route;