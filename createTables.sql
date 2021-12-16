CREATE TABLE Customer (
    	customerID INT NOT NULL IDENTITY(1,1),     
	lastName varchar(25),
    	firstName varchar(25),
    	--city varchar(55),
	--street varchar(155),
	--[state] varchar(25),
	--zipCode varChar(15),
	email varchar(55),
	phone varChar(20),
	customerLocation varChar(5),
	password varchar(100)
	PRIMARY KEY(CustomerID),
);


CREATE TABLE RestaurantType (
	restaurantTypeID int not null identity(1,1),
	restaurantType varChar(255)
	PRIMARY KEY(restaurantTypeID),
);

INSERT RestaurantType(restaurantType) VALUES('Italian');
INSERT RestaurantType(restaurantType) VALUES('Japanese');
INSERT RestaurantType(restaurantType) VALUES('American');
INSERT RestaurantType(restaurantType) VALUES('Mexican');
INSERT RestaurantType(restaurantType) VALUES('Halal');



CREATE TABLE Category(
	categoryID int not null identity(1,1),
	categoryName varChar(255)
	PRIMARY KEY(categoryID),
);

INSERT Category(categoryName) VALUES('Vegan');
INSERT Category(categoryName) VALUES('Vegetarian');
INSERT Category(categoryName) VALUES('Dairy Free');
INSERT Category(categoryName) VALUES('Gluten Free');
INSERT Category(categoryName) VALUES('Nut-Free');
INSERT Category(categoryName) VALUES('Fish Free');
INSERT Category(categoryName) VALUES('Shellfish Free');
INSERT Category(categoryName) VALUES('Pescatarian');
INSERT Category(categoryName) VALUES('Other');


CREATE TABLE Restaurant (
    	restaurantID int NOT NULL IDENTITY(1,1),
   	restaurantName varchar(25),
	restaurantTypeID int,
	restaurantLocation varChar(5),
	userName varChar(50),
	password varchar(100)
	--city varchar(55),
	--street varchar(155),
	--[state] varchar(25),
	--zipCode varChar(5)
	PRIMARY KEY(restaurantID),
	FOREIGN KEY(restaurantTypeID) REFERENCES RestaurantType(restaurantTypeID)
);


CREATE TABLE Rating (
	ratingID int not null identity(1,1),
	restaurantID int,
	ratingScore int,
	ratingReview varChar(1000),
	customerID int
	PRIMARY KEY(ratingID),
	FOREIGN KEY(restaurantID) REFERENCES Restaurant(restaurantID),
	FOREIGN KEY(customerID) REFERENCES Customer(customerID)
);

CREATE TABLE FoodItem (
	foodItemID int NOT NULL IDENTITY(1,1),
	foodName	varChar(55),
	foodPrice    Float,
	calories	int,
	[description]	varChar(255),
	prepTime	int,
	categoryID int,
	restaurantID int
	PRIMARY KEY(foodItemID),
	FOREIGN KEY(categoryID) REFERENCES Category(categoryID),
	FOREIGN KEY(restaurantID) REFERENCES Restaurant(restaurantID)
);

CREATE TABLE Driver (
	driverID int not null identity(1,1),
	firstName varChar(25),
	lastName varChar(25),
	phone varChar(20),
	email varchar(50),
	password varChar(100)
	PRIMARY KEY(driverID)
);

CREATE TABLE [Location] (
	locationID int not null identity(1,1),
	street varchar(155),
	city varchar(55),
	[state] varchar(25),
	zipCode varChar(5)
	PRIMARY KEY(locationID)
);



CREATE TABLE [Order](
	orderID int NOT NULL IDENTITY(1, 1),
	customerID int,
	driverID int,
	orderStatus varChar(255),
	totalPrice float,
	totalPrepTime int,
	paid bit default 0,
	creditCardNumber varChar(25),  
	PRIMARY KEY(orderID),
	FOREIGN KEY(customerID) REFERENCES Customer(customerID),
	FOREIGN KEY(driverID) REFERENCES Driver(driverID),
	FOREIGN KEY(creditCardNumber) REFERENCES CreditCard(creditCardNumber)
);



CREATE TABLE LineItem(
	lineItemID int not null identity(1,1),
	lineItemNumber int,
	foodItemID int,
	orderID int
	PRIMARY KEY(lineItemID),
	FOREIGN KEY (fooditemID) REFERENCES FoodItem(foodItemID),
	FOREIGN KEY (orderID) REFERENCES [Order](orderID)
);

CREATE TABLE CreditCard (
	creditCardNumber varChar(25),
	creditCardExpirationDate date,
	customerID int,
	creditCardType varChar(15),
	creditCardCVV int
	PRIMARY KEY(creditCardNumber),
	FOREIGN KEY(customerID) REFERENCES Customer(customerID)
);

CREATE TABLE Delivery (
	deliveryID int not null identity(1, 1),
	estimatedTimeToArrival int,
	startLocation varChar(5),
	customerID int,
	orderID int,
	driverID int
	PRIMARY KEY(deliveryID),
	FOREIGN KEY(customerID) REFERENCES Customer(customerID),
	FOREIGN KEY(orderID) REFERENCES [Order](orderID),
	FOREIGN KEY(driverID) REFERENCES Driver(driverID)
	
);

/*
SELECT * FROM sys.objects

WHERE type_desc LIKE '%CONSTRAINT'
*/
