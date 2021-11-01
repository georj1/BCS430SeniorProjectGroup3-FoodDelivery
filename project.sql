CREATE TABLE Customer (
    customerID INT NOT NULL IDENTITY(1,1),     
	lastName varchar(25),
    firstName varchar(25),
    --city varchar(55),
	--street varchar(155),
	--[state] varchar(25),
	--zipCode varChar(15),
	email varchar(55),
	phone varChar(20)
	PRIMARY KEY(CustomerID),
);

ALTER TABLE Customer
ADD [location] varChar(10);

CREATE TABLE RestaurantType (
	restaurantTypeID int not null identity(1,1),
	restaurantType varChar(255)
	PRIMARY KEY(restaurantTypeID),
);

--INSERT RestaurantType(restaurantType) VALUES('Italian');
--INSERT RestaurantType(restaurantType) VALUES('Japanese');
--INSERT RestaurantType(restaurantType) VALUES('American');
--INSERT RestaurantType(restaurantType) VALUES('Mexican');
--INSERT RestaurantType(restaurantType) VALUES('Halal');



CREATE TABLE Category(
	categoryID int not null identity(1,1),
	categoryName varChar(255)
	PRIMARY KEY(categoryID),
);

--INSERT Category(categoryName) VALUES('Vegan');
--INSERT Category(categoryName) VALUES('Vegetarian');
--INSERT Category(categoryName) VALUES('Dairy Free');
--INSERT Category(categoryName) VALUES('Gluten Free');
--INSERT Category(categoryName) VALUES('Nut-Free');
--INSERT Category(categoryName) VALUES('Fish Free');
--INSERT Category(categoryName) VALUES('Shellfish Free');
--INSERT Category(categoryName) VALUES('Pescatarian');
--INSERT Category(categoryName) VALUES('Other');


CREATE TABLE Restaurant (
    restaurantID int NOT NULL IDENTITY(1,1),
    restaurantName varchar(25),
	restaurantTypeID int
	--city varchar(55),
	--street varchar(155),
	--[state] varchar(25),
	--zipCode varChar(5)
	PRIMARY KEY(restaurantID),
	FOREIGN KEY(restaurantTypeID) REFERENCES RestaurantType(restaurantTypeID)
);

ALTER TABLE Restaurant
ADD [location] varChar(10);

INSERT Restaurant(restaurantName, restaurantTypeID) VALUES('Wendys', 3);
CREATE TABLE Rating (
	ratingID int not null identity(1,1),
	restaurantID int,
	ratingScore int,
	ratingReview varChar(1000)
	PRIMARY KEY(ratingID),
	FOREIGN KEY(restaurantID) REFERENCES Restaurant(restaurantID)
);

CREATE TABLE FoodItem (
	foodItemID int NOT NULL IDENTITY(1,1),
	foodName	varChar(55),
	foodPrice    Float,
	calories	int,
	[description]	varChar(255),
	[type]		varChar(55),
	prepTime	varChar(50),
	categoryID int,
	restaurantID int
	PRIMARY KEY(foodItemID),
	FOREIGN KEY(categoryID) REFERENCES Category(categoryID),
	FOREIGN KEY(restaurantID) REFERENCES Restaurant(restaurantID)
);

ALTER TABLE FoodItem
ALTER COLUMN prepTime int;

INSERT FoodItem(foodName, foodPrice, calories, [description], [type], prepTime, categoryID, restaurantID) VALUES('Hamburger', 4.99, 600, 'A hamburger with lettuce, tomato, onions and ketchup', NULL, '5 minutes', 1);
INSERT FoodItem(foodName, foodPrice, calories, [description], [type], prepTime, categoryID, restaurantID) VALUES('Chicken Nuggets', 3.99, 500, '6 Chicken Nuggets', NULL, '4 minutes', 1);
INSERT FoodItem(foodName, foodPrice, calories, [description], [type], prepTime, categoryID, restaurantID) VALUES('Salad', 4.99, 400, 'Garden Salad', 1, '5 minutes', 1);







CREATE TABLE Driver (
	driverID int not null identity(1,1),
	firstName varChar(25),
	lastName varChar(25),
	phone varChar(20)
	PRIMARY KEY(driverID)
);

CREATE TABLE [Location] (
	locationID int not null identity(1,1),
	street varchar(155),
	city varchar(55),
	[state] varchar(25),
	zipCode varChar(5),
	customerID int,
	restaurantID int,
	driverID int
	FOREIGN KEY(customerID) REFERENCES Customer(customerID),
	FOREIGN KEY(restaurantID) REFERENCES Restaurant(restaurantID),
	FOREIGN KEY(driverID) REFERENCES Driver(driverID)
);



CREATE TABLE [Order](
	orderID int NOT NULL IDENTITY(1, 1),
	customerID int,
	driverID int,
	orderStatus varChar(255),
	totalPrice float
	PRIMARY KEY(orderID),
	FOREIGN KEY(customerID) REFERENCES Customer(customerID),
	FOREIGN KEY(driverID) REFERENCES Driver(driverID)
);

ALTER TABLE [Order]
ADD totalPrepTime int;

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
	orderID int,
	creditCardType varChar(15),
	creditCardCVV int
	PRIMARY KEY(creditCardNumber, creditCardExpirationDate),
	FOREIGN KEY(customerID) REFERENCES Customer(customerID),
	FOREIGN KEY(orderID) REFERENCES [Order](orderID)
);

