###Bad Joins###
select Package.*, Stop.*, Courier.*, User.*
from
(
	select Package.Tracking_Number,
		Package.Date,
		Package.ASU_Email,
		Package.First_Name,
		Package.Last_Name,
		Package.Box_Number,
		Package.At_Stop,
		Package.Picked_Up,
		Package.Pick_Up_Date,
		Package.stop_id,
		Package.courier_id,
		Package.processor,
		Package.returned
		
		///add clause here///
)Package
left join 
(
	select Courier.Name AS 'Courier'
	from Courier
) Courier ON Package.courier_id = Courier.courier_id
left join
(
	select Stop.Name AS 'Stop'
	from Stop
) Stop ON Package.stop_id = Stop.stop_id
left join
(
	select User.User_Name AS 'User'
	from User
) User ON Package.processor = User.user_id


###Throws 'Ubiguous Column' Error###
SELECT *
FROM
(
	(
		(
			Package p LEFT JOIN Courier c ON p.courier_id = c.courier_id
		) cp LEFT JOIN Stop s ON cp.stop_id = s.stop_id
	) cps LEFT JOIN User u ON cps.processor = u.user_id
) cpsu
///Add WHERE CLAUSE///

###Returns proper information###
select Package.Tracking_Number, Package.First_Name, Package.Last_Name, Package.ASU_Email, Package.Date, Package.Box_Number, Package.At_Stop, Package.Picked_Up, Package.Pick_Up_Date, Package.Returned,
				Courier.Name AS 'Courier', Stop.Name AS 'Stop', User.User_Name AS 'Username'
				from Package, Courier, Stop, User
				where Package.courier_id = Courier.courier_id and Package.stop_id = Stop.stop_id and User.user_id = Package.processor AND ///Finish clause///

###Shorter Version###
"select Package.*, Courier.Name AS 'Courier', Stop.Name AS 'Stop', User.User_Name AS 'Username' " + 
"from Package, Courier, Stop, User " + 
"where Package.courier_id = Courier.courier_id and Package.stop_id = Stop.stop_id and User.user_id = Package.processor AND ///Add Specific Case///