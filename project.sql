CREATE TABLE Customer (
    CustomerID INT NOT NULL IDENTITY(1,1),     
	LastName varchar(25),
    FirstName varchar(25),
    City varchar(55),
	Street varchar(155),
	[state] varchar(25),
	ZipCode varChar(15),
	Email varchar(55)
	PRIMARY KEY(CustomerID)
);

CREATE TABLE Restaurant (
    RestaurantID int NOT NULL IDENTITY(1,1),
    RestaurantName varchar(25),
    City varchar(55),
	Street varchar(155),
	[state] varchar(25),
	ZipCode varChar(5),
	RestaurantType varchar(55),
	RestaurantRating varchar(5)
	PRIMARY KEY(RestaurantID)
);