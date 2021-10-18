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

CREATE TABLE FoodItem (
	FoodItemID int NOT NULL IDENTITY(1,1),
	FoodName	varChar(55),
	FoodPrice    Float,
	calories	int
	PRIMARY KEY(FoodItemID)
);

CREATE TABLE Menu (
	MenuID int Not NULL IDENTITY(1,1),
	FoodItemID int not Null
	PRIMARY KEY(MenuID),
	FOREIGN KEY (FoodItemID) REFERENCES FoodItem(FoodItemID)
);
