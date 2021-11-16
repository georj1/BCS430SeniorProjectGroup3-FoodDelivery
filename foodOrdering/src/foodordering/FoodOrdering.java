/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodordering;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jack
 */
public class FoodOrdering {

    /**
     * @param args the command line arguments
     */
	private static Restaurant selectedRestaurant = new Restaurant(); //This is a restaurant dataType that will store the selected restaurant so that it can be used to get the menu -Jack
	private static Restaurant currentRestaurant = new Restaurant();
	private static Customer currentCustomer = new Customer(); //This is the current customer for use with the order, it will be replaced with Chris's login system when that is done -Jack
    private static Driver currentDriver = new Driver(); //This is the current Driver, will be replaced by login eventually hopefully -Jack 
	public static void main(String[] args) 
    {
    	
    	String url = "jdbc:sqlserver://DESKTOP-NJ7L5JK\\sqlexpress;integratedSecurity=true;databaseName=master;"; //Jack surface-Jack
		//String url = "jdbc:sqlserver://DESKTOP-JJR7T08\\sqlexpress;integratedSecurity=true;databaseName=master;"; //Jack PC -Jack
    	//String user ="NT Service\\MSSQL$SQLEXPRESS"; //not needed right now but might be needed for remote access -Jack
    	//String password="bcs430group3"; // same as above comment -Jack
    	Scanner scin = new Scanner(System.in);
    	Scanner scSin = new Scanner(System.in); //Scanner is used to get data from the user -Jack
    	int userIn=-1;
    	 //right now this is an empty string but it will contain the actual SQL statements and then be passed to the execute update method -Jack
    	
    	
    	
    	
    	
    	try {
			Connection connection = DriverManager.getConnection(url); //this is attempting to open the connection to the server -Jack
			System.out.println("Connected"); //Display a message to show it successfully connected -Jack
			while (userIn!=0)
			{
				//scin.nextLine();
				System.out.println("Enter what you want to do: "
						+ "\n[0] Exit"
						+ "\n[1] Create an account"
						+ "\n[2] Login");
				userIn=scin.nextInt();
				scin.nextLine();
				switch(userIn)
				{
				case -2:
					/*
					String sql = "ALTER TABLE Customer"
							+ "\nADD [password] varchar(100);"
							+ "\nALTER TABLE Restaurant"
							+ "\nADD [userName] varchar(50);"
							+ "\nALTER TABLE Restaurant"
							+ "\nADD [password] varchar(100);"		
							+ "\nALTER TABLE Driver"
							+ "\nADD [password] varchar(100);";
							*/
					/*String sql="UPDATE Customer"
							+ "\nSET [password]='abc123'"
							+ "\nWHERE email='georj1@farmingdale.edu';"
							+ "\nUPDATE Restaurant"
							+ "\nSET [password]='abcd1234', [userName]='wend101'"
							+ "\nWHERE restaurantName='Wendys';"
							+ "UPDATE Driver"
							+ "\nSET [password]='12ab'"
							+ "\nWHERE firstName='John';";*/
					String sql="SELECT * FROM Driver";
    		    	Statement statement;
    				try {
    					ResultSet rs;
    					statement = connection.createStatement();
    					//statement.executeUpdate(sql);
						
						  rs=statement.executeQuery(sql); //execute SQL statement
						  
						  while(rs.next()) { String
						  s=""+rs.getInt("driverID")+", "+rs.getString("firstName")+", "+rs.getString(
						  "lastName")+", "+rs.getString("phone")+", "+rs.getString("email");
						  System.out.println(s); }
						 
    					System.out.println("Statement success");
    				} catch (SQLException e) {
    					e.printStackTrace();
    				}
					break;
				case 0:
					break;
				case 1:
					
					int accC=0;
					System.out.println("Please enter which account you want to create something for: "
							+ "\n[1] Customer"
							+ "\n[2] Restaurant"
							+ "\n[3] Driver"
							+ "\n[4] Go back");
					accC=scin.nextInt();
					switch(accC)
					{
					case 1:
						createCustomerAcc(connection);
						break;
					case 2:
						createRestaurantAcc(connection);
						break;
					case 3:
						createDriverAccount(connection);
						break;
					case 4:
						break;
					default:
						break;
					}
					break;
				case 2:
					int logIn=0;
					System.out.println("Please enter which account you want to login for: "
							+ "\n[1] Customer"
							+ "\n[2] Restaurant"
							+ "\n[3] Driver"
							+ "\n[4] Go back");
					logIn=scin.nextInt();
					switch(logIn)
					{
					case 1:
						loginCustomer(connection);
						break;
					case 2:
						loginRestaurant(connection);
						break;
					case 3:
						loginDriver(connection);
						break;
					case 4:
						break;
					default:
						break;
					}
					break;
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    			/*case 6:
    				//System.out.println("\nEnter a zipcode to search for restaurants in that zip code: "); 
    				//String searchZip = scSin.nextLine();
    				//rZipSearch(searchZip, connection);
    				break;
    				
    			
    			case 9:
    				System.out.println("\nEnter the type of restaurant you are looking for:  ");
    				printType(connection);
    				Scanner selectRT = new Scanner(System.in); //Scanner is used to get data from the user -Ahsan
    				int typeSelectIn;
    			    typeSelectIn=selectRT.nextInt();
    			    rTypeSearch(typeSelectIn, connection);
    			    break; */
    }
    
    
    
    public static void customerData(Connection connection) 
    {
    	System.out.print("Displaying customer data: \n");
    	String sql = "SELECT * FROM Customer"; // will get all data regarding customer - Ahsan
    	Statement statement;
		try {
			ResultSet rs;
			statement = connection.createStatement();
			rs = statement.executeQuery(sql); //execute SQL statement
			while(rs.next())
			{
				String s = rs.getString("firstName")+ ", "+rs.getString("lastName")+ ", "+rs.getString("email")+", "+rs.getString("phone");
				System.out.println(s);
				//The above displays the information and breaks the rows into a ResultSet which we then pull the data from to display - Ahsan
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
    
    
    public static void showRestaurants(Connection connection)
    {
    	System.out.print("Displaying restaurant data: \n");
    	String sql = "SELECT *, restaurantType FROM Restaurant JOIN RestaurantType ON Restaurant.restaurantTypeID=RestaurantType.restaurantTypeID"; // will get all data from restaurant - Ahsan
    	Statement statement;
		try {
			ResultSet rs;
			statement = connection.createStatement();
			rs = statement.executeQuery(sql); //execute SQL statement
			while(rs.next())
			{
				String s = "[" + rs.getString("restaurantID")+ "] " + rs.getString("restaurantName")+ ": "+rs.getString("restaurantType");
				System.out.println(s);
				//The above displays the information and breaks the rows into a ResultSet which we then pull the data from to display - Ahsan
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
    } 
    
    
    
    public static void getRestaurantType(Connection connection)
    {
    	String sql="SELECT restaurantTypeID, restaurantType"
    			+ "\nFROM RestaurantType";
    	Statement statement;
    	try {
			ResultSet rs;
			statement = connection.createStatement();
			rs = statement.executeQuery(sql); //execute SQL statement
			while(rs.next())
			{
				String s = "[" + rs.getString("restaurantTypeID")+ "] " + rs.getString("restaurantType");
				System.out.println(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    
    
    public static void inputRestaurantType(Connection connection, Restaurant r, int idToInsert)
    {
    	String sql="SELECT restaurantType"
    			+ "\nFROM RestaurantType"
    			+ "\nWHERE restaurantTypeID= ?";
    	try {
			ResultSet rs;
			PreparedStatement p = connection.prepareStatement(sql);
			p.setInt(1, idToInsert);
			rs = p.executeQuery(); //execute SQL statement
			while(rs.next())
			{
				String s = rs.getString("RestaurantType");
				r.setRestaurantType(s);
				insertRestaurant(r, connection); 
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    
    
    public static void insertRestaurant(Restaurant r1, Connection connection)
    {
    	String rInsert = "INSERT Restaurant ([restaurantName], [restaurantTypeID], [restaurantLocation], [userName], [password]) VALUES (?, ?, ?, ?, ?);";
		try {
			PreparedStatement p = connection.prepareStatement(rInsert);
			p.setString(1, r1.getRestaurantName());
			p.setInt(2, r1.getRestaurantTypeID());
			p.setString(3, r1.getRestaurantZip());
			p.setString(4, r1.getRestaurantUserName());
			p.setString(5,  r1.getRestaurantPassword());
			p.executeUpdate(); //execute SQL statement
			System.out.print("Restaurant account succesfully created: \n");
			//The above inserts new data into the DB and then alerts the user that it was successful -Jack
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    	
    }
    
    
    
    public static void insertCustomer(Customer c1, Connection connection)
    {
    	String cInsert = "USE master INSERT Customer ([lastName], [firstName], [email], [phone], [customerLocation], [password]) VALUES (?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement p = connection.prepareStatement(cInsert);
			p.setString(1, c1.getCustomerLName());
			p.setString(2, c1.getCustomerFName());
			p.setString(3, c1.getCustomerEmail());
			p.setString(4, c1.getCustomerPhone());
			p.setString(5, c1.getCustomerZip());
			p.setString(6, c1.getCustomerPassword());
			p.executeUpdate();
			System.out.print("Customer profile succesfully created: \n");
			//The above inserts new data into the DB and then alerts the user that it was successful -Jack
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    }
    
    public static void insertDriver(Driver d1, Connection connection)
    {
    	String dInsert = "USE master INSERT Driver ([lastName], [firstName], [email], [phone], [password]) VALUES (?, ?, ?, ?, ?);";
		try {
			PreparedStatement p = connection.prepareStatement(dInsert);
			p.setString(1, d1.getLastName());
			p.setString(2, d1.getFirstName());
			p.setString(3, d1.getDriverEmail());
			p.setString(4, d1.getPhone());
			p.setString(5, d1.getPassword());
			p.executeUpdate();
			System.out.print("Driver profile succesfully created: \n");
			//The above inserts new data into the DB and then alerts the user that it was successful -Jack
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    }
    
    
    //TODO: Rework this method 
    public static void rZipSearch(String searchZip, Connection connection)
    {
    	String sql="SELECT * FROM [dbo].[Restaurant] WHERE [location]= ?"; //code to get all data from the restaurant -Jack
		try {
			ResultSet rs;
			PreparedStatement p = connection.prepareStatement(sql);
			p.setString(1, searchZip);
			rs = p.executeQuery();
			while(rs.next())
			{
				String s = "[" + rs.getString("RestaurantID") + "] "+ rs.getString("RestaurantName")+ ": "+rs.getString("ZipCode")+", "+rs.getString("RestaurantType")+", "+rs.getString("RestaurantRating");
				System.out.println(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} //this isn't used yet but is how SQL statements will be inputed -Jack
		
    }
    
    
    
    public static void selectRestaurant(Connection connection, int rSelect)
    {
    	String sql="SELECT restaurantID, restaurantName, Restaurant.restaurantTypeID, restaurantType FROM [dbo].[Restaurant] JOIN RestaurantType ON Restaurant.restaurantTypeID=RestaurantType.restaurantTypeID  WHERE RestaurantID= ?"; //code to get the restaurant based on the ID the customer selected, will need to have the menu added -Jack
		try {
			ResultSet rs;
			PreparedStatement p = connection.prepareStatement(sql);
			p.setInt(1, rSelect);
			rs=p.executeQuery();
			while(rs.next())
			{
				selectedRestaurant.setRestaurantID(rs.getInt("restaurantID"));
				//selectedRestaurant.setRating(rs.getFloat("restaurantAverageRating"));
				selectedRestaurant.setRestaurantName(rs.getString("restaurantName"));
				selectedRestaurant.setRestaurantType(rs.getString("restaurantType"));
				//adds the information to our selected restaurant for more global use -Jack
				String s = rs.getString("RestaurantName")+ ": "+rs.getString("RestaurantType"); //Display the Restaurant for now, will eventually be the menu -Jack
				System.out.println(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
    }
    
    
    
    public static void displayMenu(Connection connection)
    {
    	String sql="SELECT FoodItem.foodItemID, foodName, FoodItem.[description], foodPrice, categoryName"
    			+"\nFROM Restaurant JOIN FoodItem ON Restaurant.restaurantID=FoodItem.restaurantID LEFT JOIN Category ON FoodItem.categoryID=Category.categoryID"
    			+"\nWHERE Restaurant.restaurantID= ? "; //New way of writing the SQL statements to prevent SQL injection -Jack
		try {
			ResultSet rs;
			PreparedStatement p = connection.prepareStatement(sql); //Uses a prepared statement to make sure that nothing else can be added -Jack
			p.setInt(1, selectedRestaurant.getRestaurantID()); //Setting the value in the String -Jack
			rs = p.executeQuery(); //execute SQL statement
			while(rs.next())
			{
				String s = "["+rs.getInt("foodItemID")+ "] "+rs.getString("foodName")+", "+rs.getString("description")+", "+rs.getString("categoryName")+", "+rs.getFloat("foodPrice"); //This is the display, it will show the foodItem ID which for now will be used for selecting and all the menu items separated by commas -Jack
				System.out.println(s); //displays the above information to the console -Jack
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
    }
    
    
    //TODO: Rework this method for full login
    public static void getCurrentCustomer(Connection connection, String cEmail)
    {
    	String sql="SELECT * FROM [dbo].[Customer] WHERE email= ?"; //code to get the customer based on their email, will tie into login later -Jack
		try {
			ResultSet rs;
			PreparedStatement p = connection.prepareStatement(sql);
			p.setString(1, cEmail);
			rs=p.executeQuery();
			while(rs.next())
			{
				currentCustomer.setCustomerID(rs.getInt("customerID"));
				currentCustomer.setCustomerFName(rs.getString("firstName"));
				currentCustomer.setCustomerLName(rs.getString("lastName"));
				currentCustomer.setCustomerEmail(rs.getString("email"));
				currentCustomer.setCustomerPhone(rs.getString("phone"));
				//adds the information to our current customer for more global use -Jack
				String s = rs.getString("firstName")+ ": "+rs.getString("lastName")+", "+rs.getString("email")+", "+rs.getString("phone"); //Display the Customer for now, will eventually be the menu -Jack
				System.out.println(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
    }
    
    
    
    public static void addFood(Connection connection, ArrayList<FoodItem> foodList)
    {
    	//TODO: Fix this code, need to also add the LineItem Table
    	String sqlCOrder="INSERT [Order](customerID, driverID, orderStatus, totalPrice, totalPrepTime) VALUES(?, NULL, 'Preparing', NULL, NULL)"; //this statement creates an order when called -Jack
    	try {
    		PreparedStatement p = connection.prepareStatement(sqlCOrder);
    		p.setInt(1, currentCustomer.getCustomerID());
    		p.executeUpdate();
			System.out.println("Order Created");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    	
    	
    	int oID=-1;
    	String sqlGetOrderID="SELECT TOP 1 orderID FROM [Order] ORDER BY orderID DESC;"; //gets the most recent orderID, will eventually add from the current customer, this will get the just placed order -Jack
    	Statement statement1;
    	try {
			statement1=connection.createStatement();
			
			ResultSet rs;
			rs=statement1.executeQuery(sqlGetOrderID);
			while(rs.next())
				oID=rs.getInt("orderID");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    	String sqlFInsert=""; //this will fill with insert statements to add LineItems to the order -Jack
    	int counter=1;
    	int totalPrepTime=0;
    	float totalPrice=0;
		for (FoodItem i:foodList)
		{
			try {
				sqlFInsert="INSERT LineItem(lineItemNumber, foodItemID, orderID) VALUES("+counter+", ?, ?);\n"; //the SQL code and Java to add Line Items -Jack
				counter++;
				PreparedStatement p2 = connection.prepareStatement(sqlFInsert);
				p2.setInt(1, i.getFoodItemID());
				p2.setInt(2, oID);
				p2.executeUpdate();
			    totalPrepTime+=i.getPrepTime(); //Commented out for now, will eventually calculate totalPrepTime -Jack
			    totalPrice+=i.getFoodPrice();
				System.out.println("Added item to the order");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		try {
			String sqlOrderIn="UPDATE [Order]"
					+ "\nSET totalPrepTime= ?"
					+ "\nWHERE orderID= ? ;"
					+ "\nUPDATE [Order]"
					+ "\nSET totalPrice= ?"
					+ "\nWHERE orderId= ?";
			PreparedStatement p3 = connection.prepareStatement(sqlOrderIn);
			p3.setInt(1, totalPrepTime);
			p3.setInt(2, oID);
			p3.setFloat(3, totalPrice);
			p3.setInt(4, oID);
			p3.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}			
			
    }
    
    
    
    public static void addItemToFList(Connection connection, int idToAdd, ArrayList<FoodItem> foodList)
    {
    	
    	
    	/*String sql="SELECT *, categoryName"
    			+ "\nFROM FoodItem LEFT JOIN Category ON FoodItem.categoryID=Category.categoryID"
    			+ "\nWHERE foodItemID="+idToAdd +";"; //code to get the restaurant based on the ID the customer selected -Jack */
    	String sql="SELECT *, categoryName"
    			+ "\nFROM FoodItem LEFT JOIN Category ON FoodItem.categoryID=Category.categoryID"
    			+ "\nWHERE foodItemID= ?"; //code to get the restaurant based on the ID the customer selected -Jack
		//Statement statement;
			try {
				ResultSet rs;
				PreparedStatement p = connection.prepareStatement(sql);
				p.setInt(1, idToAdd);
				//statement = connection.createStatement();
				//rs = statement.executeQuery(sql); //execute SQL statement
				rs=p.executeQuery();
				while(rs.next())
				{
					foodList.add(new FoodItem(rs.getInt("foodItemID"), rs.getString("foodName"), rs.getFloat("foodPrice"), rs.getInt("calories"), rs.getString("description"), rs.getString("type"), rs.getInt("prepTime"), rs.getString("categoryName"))); //Adds a foodItem to the list which is essentially a cart for right now -Jack
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
    }
    
    
    
    private static void loginDriver(Connection connection) {
    	System.out.println("Enter email");
		String sql="SELECT email FROM Driver WHERE email = ?";
		String user="", pass="";
		Scanner dInput = new Scanner(System.in);
		user=dInput.nextLine();
		try {
			PreparedStatement p = connection.prepareStatement(sql);
			p.setString(1, user);
			ResultSet rs;
			rs = p.executeQuery();
			if (rs.next() == false) 
			{
		        System.out.println("No such account with this email");
		    } 
			else 
			{
                System.out.println("Enter password");
                pass=dInput.nextLine();
                String sql2="SELECT * FROM Driver WHERE email = ? AND [password] = ?";
                PreparedStatement p2 = connection.prepareStatement(sql2);
                p2.setString(1, user);
                p2.setString(2, pass);
                ResultSet rs2; 
                rs2 = p2.executeQuery();
                if (rs2.next() == false) 
    			{
    		        System.out.println("Invalid Password");
    		    } 
    			else
    			{
    				currentDriver.setDriverID(rs2.getInt("driverID"));
    				currentDriver.setFirstName(rs2.getString("firstName"));
    				currentDriver.setLastName(rs2.getString("lastName"));
    				currentDriver.setDriverEmail(rs2.getString("email"));
    				currentDriver.setPhone(rs2.getString("phone"));
    				currentDriver.setPassword(rs2.getString("password"));
    				System.out.println("Login Succesfull");
    				driverPage(connection);
    			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//dInput.close();
	}



	private static void driverPage(Connection connection) {
		int uIn = -1;
		Scanner uInput = new Scanner(System.in);
		while(uIn!=0) {
			System.out.println("Welcome "+currentDriver.getFirstName());
			System.out.println("What would you like to do: "
					+ "\n[0] Logout"
					+ "\n[1] View/Update Account Information"
					+ "\n[2] Select an Order"
					+ "\n[3] View Orders in Progress"
					//+ "\n[4] Leave a review"
					+"");
			uIn=uInput.nextInt();
			uInput.nextLine();
			switch(uIn)
			{
			case 0:
				currentDriver = new Driver();
				break;
			case 1:
				viewDriverAccount(connection);
				break;
			case 2:
				//This method will only be shown to drivers, for now will call a driver login method -Jack
				displayOrders(connection); //Calls method to view the currently open orders -Jack
				int oSelect =uInput.nextInt();
				uInput.nextLine();
				System.out.println("Enter the zip code you are currently in: ");
				String zip=uInput.nextLine();
				selectOrder(connection, oSelect, zip); //calls the method that will update the orderTable to say it's selected and to create a delivery -Jack
				break;
			case 3:
				viewDriverOrders(connection);
				break;
			}
		}
		//uInput.close();
	}



	private static void viewDriverOrders(Connection connection) {
		String sql="SELECT * FROM Delivery JOIN [Order] ON Delivery.orderID=[Order].orderID JOIN Customer ON [Order].customerID=Customer.customerID WHERE Delivery.driverID= ?"; // getting orders that are not taken by a driver and are open- Ahsan

    	ResultSet rs;
    	try {
    		PreparedStatement p =connection.prepareStatement(sql);
    		p.setInt(1, currentDriver.getDriverID());
    		rs=p.executeQuery();
    		while(rs.next())
    		{
    			String s = "[" + rs.getInt("orderID") + "] " + rs.getString("firstName") + " " + rs.getString("lastName") + ", " + rs.getFloat("totalPrice" ); //+ " " + rs.getInt("totalPrepTime") ; //will be changed in the future when we get a better idea of what to show the customer -Ahsan
    			System.out.println(s);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    }
	}



	private static void viewDriverAccount(Connection connection) {
		String sql="SELECT * FROM Driver WHERE driverID= ?";
		try {
			PreparedStatement p = connection.prepareStatement(sql);
			p.setInt(1, currentDriver.getDriverID());
			ResultSet rs;
			rs=p.executeQuery();
			while(rs.next())
			{
				String s = "Name " +rs.getString("firstName") +" "+rs.getString("lastName")+"\nEmail: "+rs.getString("email")+"\nPhone Number: "+rs.getString("phone");
				System.out.println(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	private static void loginRestaurant(Connection connection) {
		System.out.println("Enter username");
		String sql="SELECT userName FROM Restaurant WHERE userName = ?";
		String user="", pass="";
		Scanner rInput = new Scanner(System.in);
		user=rInput.nextLine();
		try {
			PreparedStatement p = connection.prepareStatement(sql);
			p.setString(1, user);
			ResultSet rs;
			rs = p.executeQuery();
			if (rs.next() == false) 
			{
		        System.out.println("No such account with this username");
		    } 
			else 
			{
                System.out.println("Enter password");
                pass=rInput.nextLine();
                String sql2="SELECT * FROM Restaurant WHERE userName = ? AND [password] = ?";
                PreparedStatement p2 = connection.prepareStatement(sql2);
                p2.setString(1, user);
                p2.setString(2, pass);
                ResultSet rs2; 
                rs2 = p2.executeQuery();
                if (rs2.next() == false) 
    			{
    		        System.out.println("Invalid Password");
    		    } 
    			else
    			{
    				currentRestaurant.setRestaurantID(rs2.getInt("restaurantID"));
    				currentRestaurant.setRestaurantName(rs2.getString("restaurantName"));
    				currentRestaurant.setRestaurantZip(rs2.getString("restaurantLocation"));
    				currentRestaurant.setRestaurantTypeID(rs2.getInt("restaurantTypeID"));
    				currentRestaurant.setRestaurantUserName(rs2.getString("userName"));
    				currentRestaurant.setRestaurantPassword(rs2.getString("password"));
    				System.out.println("Login Succesfull");
    				restaurantPage(connection);
    			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//rInput.close();
	}



	private static void restaurantPage(Connection connection) {
		int uIn = -1;
		Scanner uInput = new Scanner(System.in);
		while(uIn!=0) {
			System.out.println("Welcome "+currentRestaurant.getRestaurantName());
			System.out.println("What would you like to do: "
					+ "\n[0] Logout"
					+ "\n[1] View/Update Account Information"
					+ "\n[2] Add items to Menu"
					+ "\n[3] Update Order"
					+"");
			uIn=uInput.nextInt();
			uInput.nextLine();
			switch(uIn)
			{
			case 0:
				currentRestaurant = new Restaurant();
				break;
			case 1:
				viewRestaurantAccount(connection);
				break;
			case 2:
		        Scanner foodDataIn = new Scanner(System.in);
		        FoodItem f1 = new FoodItem();
		        System.out.println("\nEnter Food Name: ");              
		        f1.setFoodName(foodDataIn.nextLine());
		        System.out.println("\nEnter Food Price: ");
		        f1.setFoodPrice(foodDataIn.nextFloat());
		        System.out.println("\nEnter calories: ");
		        f1.setCalories(foodDataIn.nextInt());
		        foodDataIn.nextLine();
		        System.out.println("\nEnter description: ");
		        f1.setDescription(foodDataIn.nextLine());
		        System.out.println("\nEnter type: " );
		        f1.setType(foodDataIn.nextLine());
		        System.out.println("\nEneter preptime");
		        f1.setPrepTime(foodDataIn.nextInt());
		        //foodDataIn.nextLine();
		        //The above gets and stores user input about the foodItems -Aayushma
		        System.out.println("\nEnter category ID from the following:");
		        getCategory(connection); //Calls the method to allow the user to select categories -Aayushma
		        Scanner catIn = new Scanner(System.in);
		        int categoryID = 0;
		        categoryID = catIn.nextInt();
		        f1.setCategoryID(categoryID);
		        inputCategoryType(connection, f1, categoryID); //Calls the method to insert the category based on user input -Aayushma
				break;
			case 3:
				updateRestaurantOrder(connection);
				break;
			}
		}
	}



	private static void updateRestaurantOrder(Connection connection) {
		// TODO Auto-generated method stub
		System.out.println("Enter the order number you want to update");
		
	}



	private static void viewRestaurantAccount(Connection connection) {
		String sql="SELECT *, restaurantType FROM Restaurant JOIN RestaurantType ON Restaurant.restaurantTypeID=RestaurantType.restaurantTypeID WHERE Restaurant.restaurantID= ?";
		try {
			PreparedStatement p = connection.prepareStatement(sql);
			p.setInt(1, currentRestaurant.getRestaurantID());
			ResultSet rs;
			rs=p.executeQuery();
			while(rs.next())
			{
				String s = "Name " +rs.getString("restaurantName") +"\nUsername: "+rs.getString("userName")+"\nType: "+rs.getString("restaurantType") +"\nZip Code: "+rs.getString("restaurantLocation");
				System.out.println(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	private static void loginCustomer(Connection connection) {
		System.out.println("Enter email");
		String sql="SELECT email FROM Customer WHERE email = ?";
		String user="", pass="";
		Scanner cInput = new Scanner(System.in);
		user=cInput.nextLine();
		try {
			PreparedStatement p = connection.prepareStatement(sql);
			p.setString(1, user);
			ResultSet rs;
			rs = p.executeQuery();
			if (rs.next() == false) 
			{
		        System.out.println("No such account with this email");
		    } 
			else 
			{
                System.out.println("Enter password");
                pass=cInput.nextLine();
                String sql2="SELECT * FROM Customer WHERE email = ? AND [password] = ?";
                PreparedStatement p2 = connection.prepareStatement(sql2);
                p2.setString(1, user);
                p2.setString(2, pass);
                ResultSet rs2; 
                rs2 = p2.executeQuery();
                if (rs2.next() == false) 
    			{
    		        System.out.println("Invalid Password");
    		    } 
    			else
    			{
    				currentCustomer.setCustomerID(rs2.getInt("customerID"));
    				currentCustomer.setCustomerFName(rs2.getString("firstName"));
    				currentCustomer.setCustomerLName(rs2.getString("lastName"));
    				currentCustomer.setCustomerEmail(rs2.getString("email"));
    				currentCustomer.setCustomerPhone(rs2.getString("phone"));
    				currentCustomer.setCustomerZip(rs2.getString("customerLocation"));
    				currentCustomer.setCustomerPassword(rs2.getString("password"));
    				System.out.println("Login Succesfull");
    				customerPage(connection);
    			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//cInput.close();
	}



	private static void customerPage(Connection connection) {
		int uIn = -1;
		Scanner uInput = new Scanner(System.in);
		while(uIn!=0) {
			System.out.println("Welcome "+currentCustomer.getCustomerFName());
			System.out.println("What would you like to do: "
					+ "\n[0] Logout"
					+ "\n[1] View/Update Account Information"
					+ "\n[2] Place an Order"
					+ "\n[3] View Order in Progress"
					//+ "\n[4] Leave a review"
					+"");
			uIn=uInput.nextInt();
			uInput.nextLine();
			switch(uIn)
			{
			case 0:
				currentCustomer = new Customer();
				break;
			case 1:
				viewCustomerAccount(connection);
				break;
			case 2:
				showRestaurants(connection);
				int rID=0;
				System.out.println("Enter the number of the restaurant you want to select: ");
				rID=uInput.nextInt();
				uInput.nextLine();
				selectRestaurant(connection, rID);
				ArrayList<FoodItem> newFList = new ArrayList<FoodItem>(); //List to store foodItemID's to be put into order later -Jack
				Scanner nFood = new Scanner(System.in); //this will get the foodItemID -Jack
				int nFoodNum=-3; //variable that will do 2 things, add foodItemIDs, control the loops -Jack
				while(nFoodNum!=-1)
				{
					
				    System.out.println("Enter the number of the food you want to order:"
				    		+ "\n[-2] Complete Order "
						    + "\n[-1] Exit"
						    + "\n[0] View Items in Order or remove items"); //little statement here to give user menu options -Jack
				    displayMenu(connection); //will show the menu -Jack
				    nFoodNum=nFood.nextInt();
				    if(nFoodNum==0)
				    {
				    	Scanner remOrBack = new Scanner(System.in);
				    	int rBack = -1;
				    	while(rBack!=0)
				    	{
				    		System.out.println("Enter ID of the item you want to remove: "
    				    			+ "\n[0] Go back");
				    		viewFoodList(connection, newFList); //this will show what is currently in the order -Jack
				    		rBack=remOrBack.nextInt();    
				    		if(rBack==0)
				    			break;
				    		else
				    		{
				    			FoodItem foodRemove = null;
				    			for(FoodItem i:newFList)
				    			{
				    				if(i.getFoodItemID()==rBack)
				    					foodRemove=i;
				    					
				    			}
				    			newFList.remove(foodRemove);
				    		}
				    			
				    	} 
				    	//Okay so the way this crazy thing works is it asks the user to enter the id of the item they want to remove, stores that then because of how ArrayList works
				    	 // I need to get the object based on the id so it loops through the arrayList until the foodItemID matches the customer selected id to remove, from here it
				    	 // stores the FoodItem in a temporary FoodItem object where after the loop it will then remove the FoodItem from the ArrayList -Jack
				    	 
				    	
				    }
				    else if(nFoodNum==-1)
					    break; //exits the loop -Jack
				    else if(nFoodNum==-2)
				    {
				    	addFood(connection, newFList); //This is the complete order method and will add all the food items to the order through the database -Jack
				    	nFoodNum=-1;
				    }
				    else
				    {
				    	addItemToFList(connection, nFoodNum, newFList);
				    }
				}
				break;
			case 3:
				viewOpenCustomerOrder(connection);
				break;
			case 4:
				break;
			default:
				break;
			}
			
		}
		//uInput.close();;
	}



	private static void viewCustomerAccount(Connection connection) {
		String sql="SELECT * FROM Customer WHERE customerID= ?";
		try {
			PreparedStatement p = connection.prepareStatement(sql);
			p.setInt(1, currentCustomer.getCustomerID());
			ResultSet rs;
			rs=p.executeQuery();
			while(rs.next())
			{
				String s = "Name " +rs.getString("firstName") +" "+rs.getString("lastName")+"\nEmail: "+rs.getString("email")+"\nPhone Number: "+rs.getString("phone") +"\nZip Code: "+rs.getString("customerLocation");
				System.out.println(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}



	private static void viewOpenCustomerOrder(Connection connection) {
		String sql="SELECT * FROM [Order] WHERE customerID= ? AND orderStatus!='closed'";
		try {
			PreparedStatement p = connection.prepareStatement(sql);
			p.setInt(1, currentCustomer.getCustomerID());
			ResultSet rs;
			rs=p.executeQuery();
			if(rs.next()==false)
			{
				System.out.println("No active orders");
			}
			else {
			while(rs.next())
			{
				String s = rs.getInt("orderID")+") " +rs.getString("orderStatus") +", "+rs.getFloat("totalPrice");
				System.out.println(s);
			}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}



	private static void createDriverAccount(Connection connection) {
		String sql="SELECT email FROM Driver WHERE email = ?";
		String dIn="";
		Scanner dInput = new Scanner(System.in);
		System.out.println("Enter email to be used on the account: ");
		dIn=dInput.nextLine();
		try {
			PreparedStatement p = connection.prepareStatement(sql);
			p.setString(1, dIn);
			ResultSet rs;
			rs = p.executeQuery();
			if (rs.next() == false) 
			{
		        String sqlIn="";
		        Driver d1 = new Driver(); //create a new Customer object that the user will enter information into, will eventually be sent to database -Jack
		        d1.setDriverEmail(dIn);
		        String pass="", passA="";
		        int contr=1;
		        while(contr!=0)
		        {
		        System.out.println("\nEnter a password");
		        pass=dInput.nextLine();
		        System.out.println("\nEnter password Again");
		        passA=dInput.nextLine();
                if(pass.equals(passA))
                {
                	d1.setPassword(pass);
                	contr=0;
                }
                else
                	System.out.println("Passwords do not match");
		        }
				System.out.println("\nEnter First Name: ");
				d1.setFirstName(dInput.nextLine());
				System.out.println("\nEnter Last Name: ");
				d1.setLastName(dInput.nextLine());
				
				System.out.println("\nEnter Phone Number: ");
				d1.setPhone(dInput.nextLine());
				//System.out.println("\nEnter City: ");
				//c1.setCustomerCity(scin.nextLine());
				//System.out.println("\nEnter State: ");
				//c1.setCustomerState(scin.nextLine());
				//System.out.println("\nEnter Street: ");
				//c1.setCustomerStreet(scin.nextLine());	
				insertDriver(d1, connection); //Call the method that inserts the data -Jack
		    } 
			else 
			{

		        do 
		        {
		          String data = rs.getString("email")+" is already taken";
		          System.out.println(data);
		        } 
		        while (rs.next());
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	private static void createRestaurantAcc(Connection connection) {
		String sql="SELECT [userName] FROM Restaurant WHERE [userName] = ?";
		String rIn="";
		Scanner rInput = new Scanner(System.in);
		System.out.println("Enter user name to be used on the account: ");
		rIn=rInput.nextLine();
		try {
			PreparedStatement p = connection.prepareStatement(sql);
			p.setString(1, rIn);
			ResultSet rs;
			rs = p.executeQuery();
			if (rs.next() == false) 
			{
		        String sqlIn="";
		        Restaurant r1 = new Restaurant(); //create a new Customer object that the user will enter information into, will eventually be sent to database -Jack
		        r1.setRestaurantUserName(rIn);
		        String pass="", passA="";
		        int contr=1;
		        while(contr!=0)
		        {
		        System.out.println("\nEnter a password");
		        pass=rInput.nextLine();
		        System.out.println("\nEnter password Again");
		        passA=rInput.nextLine();
                if(pass.equals(passA))
                {
                	r1.setRestaurantPassword(pass);
                	contr=0;
                }
                else
                	System.out.println("Passwords do not match");
		        }
				System.out.println("\nEnter Restaurant Name: ");
				r1.setRestaurantName(rInput.nextLine());
				System.out.println("\nEnter Zip: ");
				r1.setRestaurantZip(rInput.nextLine());
				System.out.println("\nEnter Restaurant Type ID from the following: ");
				    getRestaurantType(connection);
				    Scanner tIn = new Scanner(System.in);
				    int typeID = 0;
				    typeID = tIn.nextInt();
				    r1.setRestaurantTypeID(typeID);
				    inputRestaurantType(connection, r1, typeID);
				//insertRestaurant(r1, connection); //Call the method that inserts the data -Jack
		    } 
			else 
			{

		        do 
		        {
		          String data = rs.getString("email")+" is already taken";
		          System.out.println(data);
		        } 
		        while (rs.next());
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}



	private static void createCustomerAcc(Connection connection) {
		String sql="SELECT email FROM Customer WHERE email = ?";
		String cIn="";
		Scanner cInput = new Scanner(System.in);
		System.out.println("Enter email to be used on the account: ");
		cIn=cInput.nextLine();
		try {
			PreparedStatement p = connection.prepareStatement(sql);
			p.setString(1, cIn);
			ResultSet rs;
			rs = p.executeQuery();
			if (rs.next() == false) 
			{
		        String sqlIn="";
		        Customer c1 = new Customer(); //create a new Customer object that the user will enter information into, will eventually be sent to database -Jack
		        c1.setCustomerEmail(cIn);
		        String pass="", passA="";
		        int contr=1;
		        while(contr!=0)
		        {
		        System.out.println("\nEnter a password");
		        pass=cInput.nextLine();
		        System.out.println("\nEnter passwordAgain");
		        passA=cInput.nextLine();
                if(pass.equals(passA))
                {
                	c1.setCustomerPassword(pass);
                	contr=0;
                }
                else
                	System.out.println("Passwords do not match");
		        }
				System.out.println("\nEnter First Name: ");
				c1.setCustomerFName(cInput.nextLine());
				System.out.println("\nEnter Last Name: ");
				c1.setCustomerLName(cInput.nextLine());
				
				System.out.println("\nEnter Phone Number: ");
				c1.setCustomerPhone(cInput.nextLine());
				//System.out.println("\nEnter City: ");
				//c1.setCustomerCity(scin.nextLine());
				//System.out.println("\nEnter State: ");
				//c1.setCustomerState(scin.nextLine());
				//System.out.println("\nEnter Street: ");
				//c1.setCustomerStreet(scin.nextLine());
				System.out.println("\nEnter Zip: ");
				c1.setCustomerZip(cInput.nextLine());	
				insertCustomer(c1, connection); //Call the method that inserts the data -Jack
		    } 
			else 
			{

		        do 
		        {
		          String data = rs.getString("email")+" is already taken";
		          System.out.println(data);
		        } 
		        while (rs.next());
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public static void viewFoodList(Connection connection, ArrayList<FoodItem> foodList)
    {
    	float totalPrice=0;
    	for(FoodItem f:foodList)
    	{
    		System.out.println("[" + f.getFoodItemID() +"] "+ f.getFoodName()+", "+f.getFoodPrice());
    		totalPrice+=f.getFoodPrice();
    	}
    	System.out.println("Total Price: "+totalPrice);
    	/*
    	String sql="SELECT * FROM FoodItem WHERE"; //code to get the restaurant based on the ID the customer selected -Jack
    	for(FoodItem i : foodList)
    	{
    		
    		if(foodList.indexOf(i)==foodList.size()-1)
    		{
    			sql+=(" foodItemID= "+i.getFoodItemID()+";"); //This is here to make sure the end is done correctly -Jack
    		}
    		else
    			sql+=(" foodItemID= "+i.getFoodItemID()+","); //This here will put into the SQL statement to get only the FoodItems from the list -Jack
    	}
		Statement statement;
			try {
				ResultSet rs;
				statement = connection.createStatement();
				rs = statement.executeQuery(sql); //execute SQL statement
				while(rs.next())
				{
					String s = "["+rs.getInt("foodItemID")+"] "+rs.getString("foodName")+", "+rs.getString("description")+", "+rs.getInt("calories")+", "+ rs.getString("categoryName")+", "+rs.getFloat("foodPrice");
					System.out.println(s);
					//Prints the foodItems in the order so far -Jack
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			*/
    }
    
    
    
    public static void getCategory(Connection connection)
    {
    	String sql="SELECT categoryID, categoryName"
    			+ "\nFROM Category"; //SQL to get all the category ID's and names -Aayushma
    	Statement statement;
    	try {
    		ResultSet rs;
    		statement = connection.createStatement();
    		rs = statement.executeQuery(sql); //execute SQL statement
    		while(rs.next())
    		{
    			String s = "[" + rs.getString("categoryID")+ "] " + rs.getString("categoryName"); //Displaying Category information -Aayushma
    			System.out.println(s);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    
    
    public static void inputCategoryType(Connection connection, FoodItem f, int idToInsert)
    {
    	String sql="SELECT categoryName"
				+ "\nFROM Category"
				+ "\nWHERE categoryID= ?"; //This will get us the full data from the specific category -Aayushma
    		try {
    			ResultSet rs;
    			PreparedStatement p = connection.prepareStatement(sql);
    			p.setInt(1, idToInsert);
    			rs=p.executeQuery();
    			while(rs.next())
    			{
    				String s = rs.getString("categoryName");
    				f.setCategoryName(s); //sets the category name in the temporary variable for insertion in the next method -Aayushma
    				insertFoodItem(f, connection); //Calls the method that actually inserts the data -Aayushma
    			}
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    }
    
    
    
    public static void insertFoodItem(FoodItem f1, Connection connection)
    {
    	//The below is the full insert SQL statement to add Food to the menu -Aayushma
    	String fInsert = "INSERT FoodItem ([foodName], [foodPrice], [calories], [description], [type], [prepTime], [categoryID], [restaurantID]) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    	try {
    		PreparedStatement p = connection.prepareStatement(fInsert);
    		p.setString(1, f1.getFoodName());
    		p.setFloat(2, f1.getFoodPrice());
    		p.setInt(3, f1.getCalories());
    		p.setString(4, f1.getDescription());
    		p.setString(5, f1.getType());
    		p.setInt(6, f1.getPrepTime());
    		p.setInt(7, f1.getCategoryID());
    		p.setInt(8, currentRestaurant.getRestaurantID());
    		p.executeUpdate();
    			System.out.print("Menu data succesfully inserted: \n");
    			//The above inserts new data into the DB and then alerts the user that it was successful -Aayushma
    		} catch (SQLException e) {
    				e.printStackTrace();
    		}
   
    }
    
    
    
    public static void printType(Connection connection)
    {
    	String sql="SELECT *"
    			+ "\nFROM RestaurantType;";
    	Statement statement;
    	try {
    		ResultSet rs;
    		statement = connection.createStatement();
    		rs = statement.executeQuery(sql); //execute SQL statement
    		while(rs.next())
    		{
    			String s = "[" + rs.getString("restaurantTypeID") + "] "+ rs.getString("restaurantType");
    			System.out.println(s); //Displays all the restaurants to allow one to be selected -Jack
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} 
    }
    
    
    
    public static void rTypeSearch(int searchID, Connection connection)
    {
    	String sql="SELECT *"
    			+ "\nFROM Restaurant JOIN RestaurantType ON Restaurant.restaurantTypeID=RestaurantType.restaurantTypeID"
    			+ "\nWHERE Restaurant.restaurantTypeID= ? ";
    	try {
    		ResultSet rs;
    		PreparedStatement p = connection.prepareStatement(sql); //Uses a prepared statement to make sure that nothing else can be added -Jack
			p.setInt(1, searchID); //Setting the value in the String -Jack
			rs = p.executeQuery();
    		while(rs.next())
    		{
    			String s = "[" + rs.getString("restaurantID") + "] "+ rs.getString("restaurantName")+ ", "+rs.getString("restaurantType");
    			System.out.println(s); //Displays all the restaurants with a specific type -Ahsan
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} 

    }
    
    
    
    public static void displayOrders(Connection connection)
    {
    	String sql="SELECT * FROM [Order] WHERE orderStatus='preparing'"; // getting orders that are not taken by a driver and are open- Ahsan

    	Statement statement;
    	ResultSet rs;
    	try {
    		statement=connection.createStatement();
    		rs=statement.executeQuery(sql);
    		while(rs.next())
    		{
    			String s = "[" + rs.getInt("orderID") + "] " + rs.getFloat("totalPrice" ); //+ " " + rs.getInt("totalPrepTime") ; //will be changed in the future when we get a better idea of what to show the customer -Ahsan
    			System.out.println(s);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    }



    }
    
    
    //TODO: Rework this method for full login
    public static void getCurrentDriver(Connection connection, String dFName, String dLName)
    {
    	String sql="SELECT * FROM Driver WHERE firstName = ? AND lastName = ?"; //code to get the customer based on their email, will tie into login later -Jack
		try {
			ResultSet rs;
			PreparedStatement p = connection.prepareStatement(sql);
			p.setString(1, dFName);
			p.setString(2,  dLName);
			rs=p.executeQuery();
			while(rs.next())
			{
				/*
				currentDriver.setDriverID(rs.getInt("driverID"));
				currentDriver.setDriverFName(rs.getString("firstName"));
				currentDriver.setDriverLName(rs.getString("lastName"));
				currentDriver.setDriverPhone(rs.getString("phone"));
				*/
				//adds the information to our current driver for more global use -Jack
				String s = rs.getString("firstName")+ ": "+rs.getString("lastName")+", "+rs.getString("phone"); //Display the Customer for now, will eventually be the menu -Jack
				System.out.println(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
    }
    
    
    
    public static int calcTotalTime(Connection connection, int orderID)
    {
    	int totalTime=0;
    	String sql="SELECT prepTime "
    			+ "\nFROM [Order]JOIN LineItem ON [Order].orderID=LineItem.orderID JOIN FoodItem ON LineItem.foodItemID=FoodItem.fooditemID";
    	try {
    		Statement statement;
    		statement=connection.createStatement();
    		ResultSet rs;
    		rs=statement.executeQuery(sql);
    		while(rs.next())
    		{
    			totalTime+=rs.getInt("prepTime");
    		}
    	}catch(SQLException e) {
    			e.printStackTrace();
    		}
    	return totalTime;
    	
    }
    
    
    
    public static int calcTotalPrice(Connection connection, int orderID)
    {
    	int totalPrice=0;
    	String sql="SELECT foodPrice "
    			+ "\nFROM [Order]JOIN LineItem ON [Order].orderID=LineItem.orderID JOIN FoodItem ON LineItem.foodItemID=FoodItem.fooditemID";
    	try {
    		Statement statement;
    		statement=connection.createStatement();
    		ResultSet rs;
    		rs=statement.executeQuery(sql);
    		while(rs.next())
    		{
    			totalPrice+=rs.getInt("foodPrice");
    		}
    	}catch(SQLException e) {
    			e.printStackTrace();
    		}
    	return totalPrice;
    }
    
    
    
    public static void selectOrder(Connection connection, int orderID, String curLoc)
    {
    	int cID=0, rID=0;
    	String custLoc="", restLoc="";
    	String sqlGetC = "SELECT customerLocation, Customer.customerID"
    			+ "\nFROM [Order] JOIN Customer ON [Order].customerID=Customer.customerID"
    			+ "\nWHERE [Order].orderID= ?";
    	try {
    		PreparedStatement p = connection.prepareStatement(sqlGetC);
    		p.setInt(1,  orderID);
    		ResultSet rs;
    		rs=p.executeQuery();
    		while(rs.next())
    		{
    			custLoc=rs.getString("customerLocation");
    			cID=rs.getInt("customerID");
    		}
    	}catch(SQLException e) {
			e.printStackTrace();
		} 
    	
    	String sqlGetR = "SELECT restaurantLocation, Restaurant.restaurantID"
    			+ "\nFROM [Order] JOIN LineItem ON [Order].orderID=LineItem.orderID JOIN FoodItem ON LineItem.foodItemID=FoodItem.foodItemID JOIN Restaurant ON FoodItem.restaurantID=Restaurant.restaurantID"
    			+ "\nWHERE [Order].OrderID = ?";
    	try {
    		PreparedStatement p1 = connection.prepareStatement(sqlGetR);
    		p1.setInt(1,  orderID);
    		ResultSet rs;
    		rs=p1.executeQuery();
    		while(rs.next())
    		{
    			restLoc=rs.getString("restaurantLocation");
    			rID=rs.getInt("restaurantID");
    		}
    	}catch(SQLException e) {
			e.printStackTrace();
		} 
    	int totalTime=calcTotalTime(connection, orderID);
    	float totalPrice=calcTotalPrice(connection, orderID);
    	String sqlIn = "INSERT Delivery(estimatedTimeToArrival, startLocation, restaurantLocation, restaurantID, customerLocation, customerID, orderID, driverID) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    	try {
    		PreparedStatement p2 = connection.prepareStatement(sqlIn);
    		p2.setInt(1, totalTime);
    		p2.setString(2, curLoc);
    		p2.setString(3, restLoc);
    		p2.setInt(4,  rID);
    		p2.setString(5, custLoc);
    		p2.setInt(6, cID);
    		p2.setInt(7, orderID);
    		p2.setInt(8, currentDriver.getDriverID());
    		p2.executeUpdate();
    		System.out.println("Order taken");
    	} catch(SQLException e) {
			e.printStackTrace();
		} 
    	String sqlUpdateO ="UPDATE [Order]"
    			+ "\nSET orderStatus = 'Driver on the way to the restaurant', driverID = ?"
    			+ "\nWHERE orderID= ?";
    	try {
    		PreparedStatement p3 = connection.prepareStatement(sqlUpdateO);
    		p3.setInt(1, currentDriver.getDriverID());
    		p3.setInt(2, orderID);
    		p3.executeUpdate();
    	} catch(SQLException e) {
			e.printStackTrace();
		} 
    }
}