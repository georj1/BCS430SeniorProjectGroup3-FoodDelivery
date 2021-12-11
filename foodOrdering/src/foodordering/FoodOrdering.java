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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
/**
 * @author Jack
 */
public class FoodOrdering {
    /**
     * @param args the command line arguments
     */
	private static Restaurant selectedRestaurant = null; //This is a restaurant dataType that will store the selected restaurant so that it can be used to get the menu -Jack
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
				System.out.println("Enter what the number of what you want to do: "
						+ "\n[0] Exit"
						+ "\n[1] Create an account"
						+ "\n[2] Login");
				if(scin.hasNextInt())
				{
				userIn=scin.nextInt();
				scin.nextLine();
				switch(userIn)
				{
				case 0:
					break;
				case 1:
					
					int accC=0;
					System.out.println("Please enter the number for which account you want to create something for: "
							+ "\n[1] Customer"
							+ "\n[2] Restaurant"
							+ "\n[3] Driver"
							+ "\n[4] Go back");
					if(scin.hasNextInt())
					{
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
					}
					else
					{
						System.out.println("Please enter a valid input");
						scin.nextLine();
					}
					break;
				case 2:
					int logIn=0;
					System.out.println("Please enter the number for which account you want to login for: "
							+ "\n[1] Customer"
							+ "\n[2] Restaurant"
							+ "\n[3] Driver"
							+ "\n[4] Go back");
					if(scin.hasNextInt())
					{
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
					else
					{
						System.out.println("Please enter a valid input");
						scin.nextLine();
					}
				}
			}
			else
			{
				System.out.println("Please enter a valid input");
				scin.nextLine();
			}
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}		
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
    
    
    public static void zipSearch(Connection connection)
    {
    	Scanner i = new Scanner(System.in);
    	String searchZip;
    	System.out.println("Enter Zip Code to find restauarnts");
    	searchZip=i.nextLine();
    	String sql="SELECT * "
    			+ "\nFROM Restaurant JOIN RestaurantType ON Restaurant.restaurantTypeID=RestaurantType.restaurantTypeID "
    			+ "\nWHERE restaurantLocation LIKE CONCAT(?, '%')"; //code to get all data from the restaurant -Jack
		try {
			ResultSet rs;
			PreparedStatement p = connection.prepareStatement(sql);
			p.setString(1, searchZip);
			rs = p.executeQuery();
			if(rs.next()==false)
				System.out.println("No Restaurants matching that Zip Code");
			else
			{
				do
				{
					String s = "[" + rs.getString("RestaurantID") + "] "+ rs.getString("RestaurantName")+ ": "+rs.getString("restaurantLocation")+", "+rs.getString("RestaurantType");
					System.out.println(s);
				}
				while(rs.next());
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} //this isn't used yet but is how SQL statements will be inputed -Jack
		
    }
    
    
    
    public static void selectRestaurant(Connection connection, int rSelect)
    {
    	selectedRestaurant = new Restaurant();
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
				String s = "["+rs.getInt("foodItemID")+ "] "+rs.getString("foodName")+", "+rs.getString("description")+", "+rs.getString("categoryName")+", "+rs.getFloat("foodPrice"); //This is the display, it will  the foodItem ID which for now will be used for selecting and all the menu items separated by commas -Jack
				System.out.println(s); //displays the above information to the console -Jack
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
    }
    
    
    
    public static int addFood(Connection connection, ArrayList<FoodItem> foodList)
    {
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
			selectedRestaurant=null;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return oID;
			
    }
    
    
    
    public static void addItemToFList(Connection connection, int idToAdd, ArrayList<FoodItem> foodList)
    {
    	String sql="SELECT *, categoryName"
    			+ "\nFROM FoodItem LEFT JOIN Category ON FoodItem.categoryID=Category.categoryID"
    			+ "\nWHERE foodItemID= ?"; //code to get the restaurant based on the ID the customer selected -Jack
			try {
				ResultSet rs;
				PreparedStatement p = connection.prepareStatement(sql);
				p.setInt(1, idToAdd);
				rs=p.executeQuery();
				while(rs.next())
				{
					foodList.add(new FoodItem(rs.getInt("foodItemID"), rs.getString("foodName"), rs.getFloat("foodPrice"), rs.getInt("calories"), rs.getString("description"), rs.getInt("prepTime"), rs.getString("categoryName"))); //Adds a foodItem to the list which is essentially a cart for right now -Jack
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
	}



	private static void driverPage(Connection connection) {
		int uIn = -1;
		Scanner uInput = new Scanner(System.in);
		while(uIn!=0) {
			System.out.println("Welcome "+currentDriver.getFirstName());
			System.out.println("Please enter the number of What would you like to do: "
					+ "\n[0] Logout"
					+ "\n[1] View/Update Account Information"
					+ "\n[2] Select an Order"
					+ "\n[3] View Orders"
					+ "\n[4] Pick-up Order"
					+ "\n[5] Complete Order"
					+"");
			uIn=uInput.nextInt();
			uInput.nextLine();
			switch(uIn)
			{
			case 0:
				currentDriver = new Driver();
				System.out.println("You have been logged out");
				break;
			case 1:
				viewDriverAccount(connection);
				break;
			case 2:
				//This method will only be shown to drivers, for now will call a driver login method -Jack
				displayOrders(connection); //Calls method to view the currently open orders -Jack
				break;
			case 3:
				viewDriverOrders(connection);
				break;
			case 4:
				pickUpOrder(connection);
				break;
			case 5:
				completeOrder(connection);
				break;
			}
		}
	}



	private static void completeOrder(Connection connection) {
		String sql="SELECT lineItemNumber, foodName, ROUND(foodPrice,2) AS 'foodPriceR'\r\n"
				+ "FROM [Order] JOIN LineItem ON [Order].orderID=LineItem.orderID JOIN FoodItem ON LineItem.foodItemID=FoodItem.foodItemID \r\n"
				+ "WHERE [Order].driverID=? AND orderStatus LIKE '%is on the way with your order'\r\n";
		
				String sql2= "SELECT DISTINCT [Order].orderID, orderStatus, ROUND(totalPrice, 2) AS 'totPriceR', totalPrepTime\r\n"
				+ "FROM [Order] JOIN LineItem ON [Order].orderID=LineItem.orderID JOIN FoodItem ON LineItem.foodItemID=FoodItem.foodItemID \r\n"
				+ "WHERE [Order].driverID=? AND orderStatus LIKE '%is on the way with your order'";
				String r="";
		try {
			PreparedStatement p = connection.prepareStatement(sql2);
			p.setInt(1, currentDriver.getDriverID());
			ResultSet rs=p.executeQuery();
			if (rs.next() == false) 
			{
		        r="No Open Orders";
		        System.out.println(r);
		    } 
			else 
			{
				do
				{
					r+="\n ["+rs.getInt("orderID") +"] "+rs.getString("orderStatus")+", "+rs.getFloat("totPriceR")+", "+rs.getInt("totalPrepTime");
					PreparedStatement p1 = connection.prepareStatement(sql);
					p1.setInt(1, currentDriver.getDriverID());
					ResultSet rs1 = p1.executeQuery();
					while(rs1.next())
					{
						r+="\n\t"+rs1.getInt("lineItemNumber")+" "+rs1.getString("foodName")+", "+rs1.getFloat("foodPriceR");
					}
					System.out.println(r);
					r="";
				}
				while (rs.next());
				System.out.println("Enter the order id to complete: ");
				Scanner oInput = new Scanner(System.in);
				int uOIn=0;
				uOIn=oInput.nextInt();
				oInput.nextLine();
				updateDriverComplete(connection, uOIn);
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	private static void updateDriverComplete(Connection connection, int uOIn) {
		String sql="UPDATE [Order]"
				+ "\nSET orderStatus='Order Complete'"
				+ "\nWhere orderID=?";
		try {
			PreparedStatement p=connection.prepareStatement(sql);
			p.setInt(1, uOIn);
			p.executeUpdate();
			System.out.println("Order Complete");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	private static void pickUpOrder(Connection connection) {
		System.out.println("Enter the order number you want to pick up");
		String sql="SELECT lineItemNumber, foodName, ROUND(foodPrice,2) AS 'foodPriceR'\r\n"
				+ "FROM [Order] JOIN LineItem ON [Order].orderID=LineItem.orderID JOIN FoodItem ON LineItem.foodItemID=FoodItem.foodItemID \r\n"
				+ "WHERE [Order].driverID=? AND orderStatus='Food is ready'\r\n";
		
				String sql2= "SELECT DISTINCT [Order].orderID, orderStatus, ROUND(totalPrice, 2) AS 'totPriceR', totalPrepTime\r\n"
				+ "FROM [Order] JOIN LineItem ON [Order].orderID=LineItem.orderID JOIN FoodItem ON LineItem.foodItemID=FoodItem.foodItemID \r\n"
				+ "WHERE [Order].driverID=? AND orderStatus='Food is ready'";
				String r="";
		try {
			PreparedStatement p = connection.prepareStatement(sql2);
			p.setInt(1, currentDriver.getDriverID());
			ResultSet rs=p.executeQuery();
			if (rs.next() == false) 
			{
		        r="No Open Orders";
		        System.out.println(r);
		    } 
			else 
			{
				do
				{
					r+="\n ["+rs.getInt("orderID") +"] "+rs.getString("orderStatus")+", "+rs.getFloat("totPriceR")+", "+rs.getInt("totalPrepTime");
					PreparedStatement p1 = connection.prepareStatement(sql);
					p1.setInt(1, currentDriver.getDriverID());
					ResultSet rs1 = p1.executeQuery();
					while(rs1.next())
					{
						r+="\n\t"+rs1.getInt("lineItemNumber")+" "+rs1.getString("foodName")+", "+rs1.getFloat("foodPriceR");
					}
					System.out.println(r);
					r="";
				}
				while (rs.next());
				System.out.println("Enter the order id to pick up: ");
				Scanner oInput = new Scanner(System.in);
				int uOIn=0;
				uOIn=oInput.nextInt();
				oInput.nextLine();
				updateDriverPickUp(connection, uOIn);
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	private static void updateDriverPickUp(Connection connection, int uOIn) {
		String sql="UPDATE [Order]"
				+ "\nSET orderStatus='"+currentDriver.getFirstName()+" is on the way with your order'"
				+ "\nWhere orderID=?";
		try {
			PreparedStatement p=connection.prepareStatement(sql);
			p.setInt(1, uOIn);
			p.executeUpdate();
			System.out.println("Order Picked Up");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	private static void viewDriverOrders(Connection connection) {
		String sql="SELECT lineItemNumber, foodName, ROUND(foodPrice,2) AS 'foodPriceR'\r\n"
				+ "FROM [Order] JOIN LineItem ON [Order].orderID=LineItem.orderID JOIN FoodItem ON LineItem.foodItemID=FoodItem.foodItemID \r\n"
				+ "WHERE [Order].driverID=? AND [Order].orderID=?\r\n";
		
				String sql2= "SELECT DISTINCT [Order].orderID, orderStatus, ROUND(totalPrice, 2) AS 'totPriceR', totalPrepTime\r\n"
				+ "FROM [Order] JOIN LineItem ON [Order].orderID=LineItem.orderID JOIN FoodItem ON LineItem.foodItemID=FoodItem.foodItemID \r\n"
				+ "WHERE [Order].driverID=?";
				String r="";
		try {
			PreparedStatement p = connection.prepareStatement(sql2);
			p.setInt(1, currentDriver.getDriverID());
			ResultSet rs=p.executeQuery();
			if (rs.next() == false) 
			{
		        r="No Open Orders";
		        System.out.println(r);
		    } 
			else 
			{
				do
				{
					int orderID;
					r+="\n ["+rs.getInt("orderID") +"] "+rs.getString("orderStatus")+", "+rs.getFloat("totPriceR")+", "+rs.getInt("totalPrepTime");
					orderID=rs.getInt("orderID");
					PreparedStatement p1 = connection.prepareStatement(sql);
					p1.setInt(1, currentDriver.getDriverID());
					p1.setInt(2, orderID);
					ResultSet rs1 = p1.executeQuery();
					while(rs1.next())
					{
						r+="\n\t"+rs1.getInt("lineItemNumber")+" "+rs1.getString("foodName")+", "+rs1.getFloat("foodPriceR");
					}
					System.out.println(r);
					r="";
				}
				while (rs.next());
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
		System.out.println("Edit Data(y/n):");
		Scanner inn = new Scanner(System.in);
		String i="";
		i=inn.nextLine();
		if(i.equals("y"))
			editDriverData(connection); //calls the method that allows the customer to edit their account information -Jack
	}



	private static void editDriverData(Connection connection) {
		Driver tempD = new Driver();
		Scanner inn = new Scanner(System.in);
		String tmpEml="";
		String sql="SELECT email FROM Driver where email=? AND driverID != ?"; //SQL Statement to make sure they don't use an email that is already in the system, will allow them to reuse their email though -Jack
		String sqlI = "UPDATE Driver"
				+ "\nSET firstName= ?,"
				+ "\nlastName= ?,"
				+ "\nemail= ?,"
				+ "\nphone = ?,"
				+ "\npassword=?"
				+ "\nWHERE driverID = ?"; //This is the SQL statement that will update the data -Jack
		try {
			PreparedStatement p = connection.prepareStatement(sql);
			System.out.println("Enter new email: ");
			tmpEml=inn.nextLine();
			p.setString(1, tmpEml);
			p.setInt(2, currentDriver.getDriverID());
			ResultSet rs = p.executeQuery();
			if(rs.next()==false) //This and above checks to make sure that the email isn't used already -Jack
			{
				tempD.setDriverEmail(tmpEml);
				String pass="", passA="";
		        int contr=1;
		        while(contr!=0)
		        {
		        System.out.println("\nEnter a new password");
		        pass=inn.nextLine();
		        System.out.println("\nEnter the password again");
		        passA=inn.nextLine();
                if(pass.equals(passA))
                {
                	tempD.setPassword(pass);
                	contr=0;
                }
                else
                	System.out.println("Passwords do not match, no data was changed");
		        } //All the above is the same password check as on create account, it makes sure they enter the same password twice -Jack
		        System.out.println("Enter a new First Name");
		        tempD.setFirstName(inn.nextLine());
		        System.out.println("Enter a new Last Name");
		        tempD.setLastName(inn.nextLine());
		        System.out.println("Enter a new phone number");
		        tempD.setPhone(inn.nextLine());
		        //The above is getting all the new information -Jack
				try {
					PreparedStatement p1 = connection.prepareStatement(sqlI);
					p1.setString(1, tempD.getFirstName());
					p1.setString(2, tempD.getLastName());
					p1.setString(3, tempD.getDriverEmail());
					p1.setString(4, tempD.getPhone());
					p1.setString(5, tempD.getPassword());
					p1.setInt(6, currentDriver.getDriverID());
					p1.executeUpdate();
					System.out.print("Driver profile succesfully updated: \n");
					tempD.setDriverID(currentDriver.getDriverID());
					currentDriver=tempD; //used a temp driver to allow the switching, this will now change it over to the new customer data -Jack
					//The above inserts new data into the DB and then alerts the user that it was successfully updated-Jack
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else
				System.out.println("Email is already taken, no changes made");
		} catch (SQLException e1) {
			e1.printStackTrace();
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
	}



	private static void restaurantPage(Connection connection) {
		int uIn = -1;
		Scanner uInput = new Scanner(System.in);
		while(uIn!=0) {
			System.out.println("Welcome "+currentRestaurant.getRestaurantName());
			System.out.println("Enter the number for what would you like to do: "
					+ "\n[0] Logout"
					+ "\n[1] View/Update Account Information"
					+ "\n[2] Add items to Menu"
					+ "\n[3] Update Order"
					+ "\n[4] View All Orders"
					+ "\n[5] View All Reviews"
					+"");
			uIn=uInput.nextInt();
			uInput.nextLine();
			switch(uIn)
			{
			case 0:
				currentRestaurant = new Restaurant();
				System.out.println("You have been logged out");
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
		        System.out.println("\nEnter preptime");
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
				showOpenRestaurantOrders(connection);
				break;
			case 4:
				viewAllRestaurantOrders(connection);
                System.out.println("Enter the order id to view more or -3 to go back:");
                uIn = uInput.nextInt();
                uInput.nextLine();
                if(uIn==-3)
                    break;
                else
                    viewFullRestaurantOrder(connection, uIn);
                break;
			case 5:
				viewAllReviews(connection);
				break;
			}
		}
	}



	private static void viewAllReviews(Connection connection) {
		String sql="SELECT restaurantName, AVG(ratingScore) AS arScore"
				+ "\nFROM Restaurant JOIN Rating ON Restaurant.restaurantID=Rating.restaurantID"
				+ "\nWHERE Restaurant.restaurantID=?"
				+ "\nGROUP BY restaurantName";
		
		String sql2="SELECT *"
				+ "\nFROM Rating"
				+ "\nWHERE restaurantID= ?";
		String r="";
		try {
			PreparedStatement p = connection.prepareStatement(sql);
			p.setInt(1, currentRestaurant.getRestaurantID());
			ResultSet rs=p.executeQuery();
			if (rs.next() == false) 
			{
		        r="No Reviews";
		        System.out.println(r);
		    } 
			else 
			{
				do
				{
					r+="\n"+rs.getString("restaurantName") +" Average Rating: "+rs.getDouble("arScore");
					PreparedStatement p1 = connection.prepareStatement(sql2);
					p1.setInt(1, currentRestaurant.getRestaurantID());
					ResultSet rs1 = p1.executeQuery();
					while(rs1.next())
					{
						r+="\n\t"+rs1.getInt("ratingScore")+" "+rs1.getString("ratingReview");
					}
					System.out.println(r);
					r="";
				}
				while (rs.next());
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}



	private static void viewFullRestaurantOrder(Connection connection, int uIn) {
        String sql="SELECT lineItemNumber, foodName, ROUND(foodPrice,2) AS 'foodPriceR'\r\n"
                + "FROM [Order] JOIN LineItem ON [Order].orderID=LineItem.orderID JOIN FoodItem ON LineItem.foodItemID=FoodItem.foodItemID JOIN Restaurant ON FoodItem.restaurantID = Restaurant.restaurantID\r\n"
                + "WHERE [Order].orderID= ? AND Restaurant.restaurantID = ?\r\n";
        try {
            PreparedStatement p = connection.prepareStatement(sql);
            p.setInt(1, uIn);
            p.setInt(2, currentRestaurant.getRestaurantID());
            ResultSet rs=p.executeQuery();
            while(rs.next())
            {
                String r="\n\t"+rs.getInt("lineItemNumber")+" "+rs.getString("foodName")+", "+rs.getFloat("foodPriceR");
                System.out.println(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}



	private static void viewAllRestaurantOrders(Connection connection) {
	        String sql="SELECT DISTINCT [Order].orderID, orderStatus, totalPrice \r\n"
	        		+ "FROM [Order] JOIN LineItem ON [Order].OrderID = LineItem.orderID JOIN FoodItem ON LineItem.foodItemID=FoodItem.foodItemID JOIN Restaurant ON FoodItem.restaurantID=Restaurant.restaurantID\r\n"
	        		+ "WHERE Restaurant.restaurantID=?";
	        try {
	            PreparedStatement p = connection.prepareStatement(sql);
	            p.setInt(1, currentRestaurant.getRestaurantID());
	            ResultSet rs;
	            rs=p.executeQuery();
	            if(rs.next()==false)
	            {
	                System.out.println("No orders");
	            }
	            else {
	            do {
	                String s = rs.getInt("orderID")+") " +rs.getString("orderStatus") +", "+rs.getFloat("totalPrice");
	                System.out.println(s);
	            }
	            while(rs.next());
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}



	private static void updateRestaurantOrder(Connection connection, int uOIn) {
		String sql="UPDATE [Order]"
				+ "\nSET orderStatus='Food is ready'"
				+ "\nWhere orderID=?";
		try {
			PreparedStatement p=connection.prepareStatement(sql);
			p.setInt(1, uOIn);
			p.executeUpdate();
			System.out.println("Order Updated Succesfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	private static void showOpenRestaurantOrders(Connection connection) {
		String sql2= "SELECT DISTINCT [Order].orderID, orderStatus, ROUND(totalPrice, 2) AS 'totPriceR', totalPrepTime\r\n"
				+ "FROM [Order] JOIN LineItem ON [Order].orderID=LineItem.orderID JOIN FoodItem ON LineItem.foodItemID=FoodItem.foodItemID \r\n"
				+ "WHERE FoodItem.restaurantID=? AND (orderStatus='Driver on the way to the restaurant' OR orderStatus='Preparing')";
		String r="";
		try {
			PreparedStatement p = connection.prepareStatement(sql2);
			p.setInt(1, currentRestaurant.getRestaurantID());
			ResultSet rs=p.executeQuery();
			if (rs.next() == false) 
			{
		        r="No Open Orders";
		        System.out.println(r);
		    } 
			else 
			{
				do
				{
					r+="\n ["+rs.getInt("orderID") +"] "+rs.getString("orderStatus")+", "+rs.getFloat("totPriceR")+", "+rs.getInt("totalPrepTime");
					System.out.println(r);
					r="";
				}
				while (rs.next());
				System.out.println("Enter the order number you want to update");
				Scanner oInput = new Scanner(System.in);
				int uOIn=0;
				uOIn=oInput.nextInt();
				oInput.nextLine();
				updateRestaurantOrder(connection, uOIn);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		System.out.println("Edit Data(y/n):");
		Scanner inn = new Scanner(System.in);
		String i="";
		i=inn.nextLine();
		if(i.equals("y"))
			editRestaurantData(connection); //calls the method that allows the customer to edit their account information -Jack
	}



	private static void editRestaurantData(Connection connection) {
		Restaurant tempR = new Restaurant();
		Scanner inn = new Scanner(System.in);
		String tmpUsr="";
		String sql="SELECT userName FROM Restaurant where userName=? AND restaurantID != ?"; //SQL Statement to make sure they don't use an email that is already in the system, will allow them to reuse their email though -Jack
		String sqlI = "UPDATE Restaurant"
				+ "\nSET restaurantName= ?,"
				+ "\nrestaurantTypeID= ?,"
				+ "\nrestaurantLocation= ?,"
				+ "\nuserName= ?,"
				+ "\npassword= ?"
				+ "\nWHERE restaurantID = ?"; //This is the SQL statement that will update the data -Jack m
		try {
			PreparedStatement p = connection.prepareStatement(sql);
			System.out.println("Enter new username: ");
			tmpUsr=inn.nextLine();
			p.setString(1, tmpUsr);
			p.setInt(2, currentRestaurant.getRestaurantID());
			ResultSet rs = p.executeQuery();
			if(rs.next()==false) //This and above checks to make sure that the email isn't used already -Jack
			{
				tempR.setRestaurantUserName(tmpUsr);
				String pass="", passA="";
		        int contr=1;
		        while(contr!=0)
		        {
		        System.out.println("\nEnter a new password");
		        pass=inn.nextLine();
		        System.out.println("\nEnter the password again");
		        passA=inn.nextLine();
                if(pass.equals(passA))
                {
                	tempR.setRestaurantPassword(pass);
                	contr=0;
                }
                else
                	System.out.println("Passwords do not match, no data was changed");
		        } //All the above is the same password check as on create account, it makes sure they enter the same password twice -Jack
		        System.out.println("Enter a new Restaurant Name");
		        tempR.setRestaurantName(inn.nextLine());
		        
		        getRestaurantType(connection);
		        System.out.println("Enter the number of the new restaurant type");
		        tempR.setRestaurantTypeID(inn.nextInt());
		        inn.nextLine();
		        System.out.println("Enter a new Zip Code");
		        tempR.setRestaurantZip(inn.nextLine());
		        //The above is getting all the new information -Jack
				try {
					PreparedStatement p1 = connection.prepareStatement(sqlI);
					p1.setString(1, tempR.getRestaurantName());
					p1.setInt(2, tempR.getRestaurantTypeID());
					p1.setString(3, tempR.getRestaurantZip());
					p1.setString(4, tempR.getRestaurantUserName());
					p1.setString(5, tempR.getRestaurantPassword());
					p1.setInt(6, currentRestaurant.getRestaurantID());
					p1.executeUpdate();
					System.out.print("Restaurant profile succesfully updated: \n");
					tempR.setRestaurantID(currentRestaurant.getRestaurantID());
					currentRestaurant=tempR; //used a temp restaurant to allow the switching, this will now change it over to the new customer data -Jack
					//The above inserts new data into the DB and then alerts the user that it was successfully updated-Jack
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else
				System.out.println("Email is already taken, no changes made");
		} catch (SQLException e1) {
			e1.printStackTrace();
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
	}



	private static void customerPage(Connection connection) {
		int uIn = -1;
		Scanner uInput = new Scanner(System.in);
		while(uIn!=0) {
			System.out.println("Welcome "+currentCustomer.getCustomerFName());
			System.out.println("Enter the number of what would you like to do: "
					+ "\n[0] Logout"
					+ "\n[1] View/update account information"
					+ "\n[2] Place an order"
					+ "\n[3] View order in progress"
					+ "\n[4] Leave a review"
					+ "\n[5] View my reviews"
					+"");
			if(uInput.hasNextInt())
			{
				
			uIn=uInput.nextInt();
			uInput.nextLine();
			switch(uIn)
			{
			case 0:
				currentCustomer = new Customer();
				System.out.println("You have been logged out");
				break;
			case 1:
				viewCustomerAccount(connection);
				break;
			case 2:
				while (selectedRestaurant == null)
				{
					boolean c=false;
					while(!c)
					{
				System.out.println("Enter the number for how would you like to search for a restaurant"
						+ "\n[-3] Go Back"
						+ "\n[1] View All"
						+ "\n[2] Search by Zip Code"
						+ "\n[3] Search by Type"
						+ "\n[4] Search by Name"
						+ "");
				
				if(uInput.hasNextInt())
				{
					c=true;
				int uIn2 = uInput.nextInt();
				uInput.nextLine();
						switch(uIn2)
						{
						case -3:
							break;
						case 1:
							showRestaurants(connection);
							break;
						case 2:
							zipSearch(connection);
							break;
						case 3:
							typeSearch(connection);
							break;
						case 4:
							nameSearch(connection);
							break;
						default:
							break;
						}
						if(uIn2==-3) //if the user entered to exit then it will break them out of the loop -Jack
							break;
						else
							customerOrder(connection);
				}
				else
				{
					System.out.println("Please enter a valid input");
					uInput.nextLine();
				}
				}
				
				
				}
				if (selectedRestaurant == null) //if the user hasn't selected a restaurant it will return them to the main menu -Jack
				{
					System.out.println("No restaurant selected, no order created");
					break;
				}
				else
				{
				ArrayList<FoodItem> newFList = new ArrayList<FoodItem>(); //List to store foodItemID's to be put into order later -Jack
				Scanner nFood = new Scanner(System.in); //this will get the foodItemID -Jack
				int nFoodNum=-3; //variable that will do 2 things, add foodItemIDs, control the loops -Jack
				while(nFoodNum!=-1)
				{
					
				    System.out.println("Enter the number of the food you want to order:"
				    		+ "\n[-2] Complete Order "
						    + "\n[-1] Cancel Order"
						    + "\n[0] View Items in Order or remove items"); //little statement here to give user menu options -Jack
				    displayMenu(connection); //will show the menu -Jack
				    if(nFood.hasNextInt())
				    {
				    	nFoodNum=nFood.nextInt();
				    	nFood.nextLine();
				    	if(nFoodNum==0)
				    	{
				    		
				    		Scanner remOrBack = new Scanner(System.in);
				    		int rBack = -1;
				    		while(rBack!=0)
				    		{
				    			System.out.println("Enter ID of the item you want to remove: "
    				    			+ "\n[0] Go back");
				    			viewFoodList(connection, newFList); //this will show what is currently in the order -Jack  
				    			if(remOrBack.hasNextInt())
				    			{
				    				rBack=remOrBack.nextInt();
				    				remOrBack.nextLine();
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
				    			else
				    			{
				    				System.out.println("Please enter a valid input");
				    				remOrBack.nextLine();
				    			}
				    			
				    		} 
				    		//Okay so the way this crazy thing works is it asks the user to enter the id of the item they want to remove, stores that then because of how ArrayList works
				    		// I need to get the object based on the id so it loops through the arrayList until the foodItemID matches the customer selected id to remove, from here it
				    		// stores the FoodItem in a temporary FoodItem object where after the loop it will then remove the FoodItem from the ArrayList -Jack 	
				    	}
				    	else if(nFoodNum==-1)
				    	{
				    		System.out.println("Order Canceled");
				    		break; //exits the loop -Jack
				    	}
				    	else if(nFoodNum==-2)
				    	{
				    		int oID=addFood(connection, newFList); //This is the complete order method and will add all the food items to the order through the database -Jack
				    		payForOrder(connection, oID);
				    		nFoodNum=-1;
				    	}
				    	else
				    	{
				    		addItemToFList(connection, nFoodNum, newFList);
				    	}
				    }
				    else
				    {
				    	System.out.println("Please enter a valid input");
						nFood.nextLine();
				    }
				}
				selectedRestaurant=null;
				break;
				}
			case 3:
				viewOpenCustomerOrder(connection);
				System.out.println("Enter the order id to view more or -3 to go back:");
				if(uInput.hasNextInt())
				{
				uIn = uInput.nextInt();
				uInput.nextLine();
				if(uIn==-3)
					break;
				else
					viewFullOrder(connection, uIn);
				}
				else
				{
					System.out.println("Please enter a valid input");
					uInput.nextLine();
				}
				break;
			case 4:
				leaveReview(connection);
				break;
			case 5:
				customerViewReviews(connection);
				break;
			default:
				break;
			}
			}
			else
			{
				System.out.println("Please enter a valid input");
				uInput.nextLine();
			}
		}
	}



	private static void customerViewReviews(Connection connection) {
		String sql="SELECT * "
				+ "\nFROM Rating JOIN Restaurant ON Rating.restaurantID=Restaurant.restaurantID"
				+ "\nWHERE customerID= ?";
		try {
			PreparedStatement p = connection.prepareStatement(sql);
			p.setInt(1, currentCustomer.getCustomerID());
			ResultSet rs = p.executeQuery();
			if(rs.next() == false)
				System.out.println("You have left no reviews");
			else
			{
				do
				{
					String s=rs.getString("restaurantName") + ", " + rs.getInt("ratingScore") + ", "+rs.getString("ratingReview");
					System.out.println(s);
				}
				while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	private static void payForOrder(Connection connection, int oID) {
		String sqlS ="SELECT *"
				+ "\nFROM CreditCard"
				+ "\nWHERE customerID=?";
		String r="Enter credit card to use:", creditCardNumber="";
		try {
			int counter=1;
			PreparedStatement p=connection.prepareStatement(sqlS);
			p.setInt(1, currentCustomer.getCustomerID());
			ResultSet rs = p.executeQuery();
			r+="\n[0] Insert new card";
			if(rs.next()==false)
				r+="";
			else
			{
				do {
					r+="\n["+counter+"] " +rs.getString("creditCardType") + ", "+rs.getString("creditCardNumber");
					counter+=1;
				} while (rs.next());
			}
			System.out.println(r);
			Scanner cIN = new Scanner(System.in);
			int id = cIN.nextInt();
			cIN.nextLine();
			if(id==0)
			{
				creditCardNumber=newCreditCard(connection);
			}
			else
			{
				ResultSet rs1 = p.executeQuery();
				while(rs1.next())
				{
					if(id==1)
					{
						creditCardNumber = rs1.getString("creditCardNumber");
						break;
					}
					else
						id-=1;
				}
			}
			makePayment(connection, oID, creditCardNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}



	private static void makePayment(Connection connection, int oID, String creditCardNumber) {
		String sqlU="UPDATE [Order]"
				+ "\nSET creditCardNumber=?, paid=1"
				+ "\nWHERE orderID=?";
		try {
			PreparedStatement p = connection.prepareStatement(sqlU);
			p.setString(1, creditCardNumber);
			p.setInt(2, oID);
			p.executeUpdate();
			System.out.println("Payment succesfull");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	private static String newCreditCard(Connection connection) {
		String sqlI="INSERT CreditCard(creditCardNumber,  creditCardExpirationDate, customerID, creditCardType, creditCardCVV) VALUES(?, ?, ?, ?, ?);";
		CreditCard c = new CreditCard();
		Scanner cardIn = new Scanner(System.in);
		System.out.println("Enter Card Number:");
		c.setCreditCardNumber(cardIn.nextLine());
		System.out.println("Enter Card Expiration date in the format yyyymmdd:");
		long d=cardIn.nextLong();
		cardIn.nextLine();
		c.setCreditCardExpirationDate(d);
		System.out.println("Enter Card Type(Visa, Discover, MasterCard, Amex):");
		c.setCreditCardType(cardIn.nextLine());
		c.setCustomerID(currentCustomer.getCustomerID());
		System.out.println("Enter the Card Security code:");
		c.setCreditCardCVV(cardIn.nextInt());
		try {
			PreparedStatement p = connection.prepareStatement(sqlI);
			p.setString(1, c.getCreditCardNumber());
			java.sql.Date sqlDate = new java.sql.Date(c.getCreditCardExpirationDate());
			p.setDate(2,  sqlDate);
			p.setInt(3, c.getCustomerID());
			p.setString(4, c.getCreditCardType());
			p.setInt(5, c.getCreditCardCVV());
			p.executeUpdate();
			return c.getCreditCardNumber();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}



	private static void nameSearch(Connection connection) {
		System.out.println("Enter name to search for restaurants that match");
		String searchName;
		Scanner i = new Scanner(System.in);
		searchName=i.nextLine();
    	String sql="SELECT *"
    			+ "\nFROM Restaurant JOIN RestaurantType ON Restaurant.restaurantTypeID=RestaurantType.restaurantTypeID"
    			+ "\nWHERE restaurantName LIKE CONCAT(?, '%')";
    	try {
    		ResultSet rs;
    		PreparedStatement p = connection.prepareStatement(sql); //Uses a prepared statement to make sure that nothing else can be added -Jack
			p.setString(1, searchName); //Setting the value in the String -Jack
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



	private static void customerOrder(Connection connection) {
		int rID=0;
		Scanner uInput = new Scanner(System.in);
		
		System.out.println("Enter the number of the restaurant you want to select: ");
		if(uInput.hasNextInt())
		{
			rID=uInput.nextInt();
			uInput.nextLine();
			selectRestaurant(connection, rID);
		}
		else
		{
			System.out.println("Please enter a valid input");
			uInput.nextLine();
		}
	}



	private static void leaveReview(Connection connection) {
		String sql="SELECT DISTINCT Restaurant.restaurantID, restaurantName"
				+ "\nFROM Restaurant JOIN FoodItem ON Restaurant.restaurantID=FoodItem.restaurantID JOIN LineItem ON FoodItem.foodItemID=LineItem.foodItemID JOIN [Order] ON LineItem.orderID=[Order].orderID"
				+ "\nWHERE [Order].customerID= ? AND orderStatus='Order Complete'";
		try {
			PreparedStatement p = connection.prepareStatement(sql);
			p.setInt(1, currentCustomer.getCustomerID());
			ResultSet rs = p.executeQuery();
			if(rs.next()==false)
				System.out.println("No orders placed");
			else
			{
				do
				{
					String s="["+rs.getInt("restaurantID")+"] "+rs.getString("restaurantName");
					System.out.println(s);
				}
				while(rs.next());
				System.out.println("Enter ID of restaurant to leave a review for: ");
				Scanner i = new Scanner(System.in);
				int id = i.nextInt();
				createReview(connection, id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}



	private static void createReview(Connection connection, int id) {
		String sql1="SELECT customerID"
				+ "\nFROM Rating"
				+ "\nWHERE restaurantID=? AND customerID= ?";
		try {
			PreparedStatement p = connection.prepareStatement(sql1);
			p.setInt(1, id);
			p.setInt(2, currentCustomer.getCustomerID());
			ResultSet rs=p.executeQuery();
			if(rs.next()==false)
			{
				Scanner i = new Scanner(System.in);
				int score=0;
				String review="";
				while(score<1||score>10)
				{
					System.out.println("Enter the score from 1-10");
					score=i.nextInt();
					i.nextLine();
				}
				System.out.println("Enter a review if desired");
				review=i.nextLine();
				String sql2="INSERT Rating(restaurantID, ratingScore, ratingReview, customerID) VALUES(?, ?, ?, ?)";
				PreparedStatement p1 = connection.prepareStatement(sql2);
				p1.setInt(1, id);
				p1.setInt(2, score);
				p1.setString(3, review);
				p1.setInt(4, currentCustomer.getCustomerID());
				p1.executeUpdate();
				System.out.println("Review left");
				
			}
			else
				System.out.println("You've already left a review for this restaurant");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	private static void viewFullOrder(Connection connection, int uIn) {
		String sql="SELECT lineItemNumber, foodName, ROUND(foodPrice,2) AS 'foodPriceR'\r\n"
				+ "FROM [Order] JOIN LineItem ON [Order].orderID=LineItem.orderID JOIN FoodItem ON LineItem.foodItemID=FoodItem.foodItemID \r\n"
				+ "WHERE [Order].orderID= ? AND orderStatus!='Order Complete'\r\n";
		try {
			PreparedStatement p = connection.prepareStatement(sql);
			p.setInt(1, uIn);
			ResultSet rs=p.executeQuery();
			while(rs.next())
			{
				String r="\n\t"+rs.getInt("lineItemNumber")+" "+rs.getString("foodName")+", "+rs.getFloat("foodPriceR");
				System.out.println(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		System.out.println("Edit Data(y/n):");
		Scanner inn = new Scanner(System.in);
		String i="";
		i=inn.nextLine();
		if(i.equals("y"))
			editCustomerData(connection); //calls the method that allows the customer to edit their account information -Jack
	}



	private static void editCustomerData(Connection connection) {
		Customer tempC = new Customer();
		Scanner inn = new Scanner(System.in);
		String tmpEml="";
		String sql="SELECT email FROM Customer where email=? AND customerID != ?"; //SQL Statement to make sure they don't use an email that is already in the system, will allow them to reuse their email though -Jack
		String sqlI = "UPDATE Customer"
				+ "\nSET firstName= ?,"
				+ "\nlastName= ?,"
				+ "\nemail= ?,"
				+ "\nphone = ?,"
				+ "\ncustomerLocation = ?,"
				+ "\npassword=?"
				+ "\nWHERE customerID = ?"; //This is the SQL statement that will update the data -Jack
		try {
			PreparedStatement p = connection.prepareStatement(sql);
			System.out.println("Enter new email: ");
			tmpEml=inn.nextLine();
			p.setString(1, tmpEml);
			p.setInt(2, currentCustomer.getCustomerID());
			ResultSet rs = p.executeQuery();
			if(rs.next()==false) //This and above checks to make sure that the email isn't used already -Jack
			{
				tempC.setCustomerEmail(tmpEml);
				String pass="", passA="";
		        int contr=1;
		        while(contr!=0)
		        {
		        System.out.println("\nEnter a new password");
		        pass=inn.nextLine();
		        System.out.println("\nEnter the password again");
		        passA=inn.nextLine();
                if(pass.equals(passA))
                {
                	tempC.setCustomerPassword(pass);
                	contr=0;
                }
                else
                	System.out.println("Passwords do not match, no data was changed");
		        } //All the above is the same password check as on create account, it makes sure they enter the same password twice -Jack
		        System.out.println("Enter a new First Name");
		        tempC.setCustomerFName(inn.nextLine());
		        System.out.println("Enter a new Last Name");
		        tempC.setCustomerLName(inn.nextLine());
		        System.out.println("Enter a new phone number");
		        tempC.setCustomerPhone(inn.nextLine());
		        System.out.println("Enter a new Zip Code");
		        tempC.setCustomerZip(inn.nextLine());
		        //The above is getting all the new information -Jack
				try {
					PreparedStatement p1 = connection.prepareStatement(sqlI);
					p1.setString(1, tempC.getCustomerFName());
					p1.setString(2, tempC.getCustomerLName());
					p1.setString(3, tempC.getCustomerEmail());
					p1.setString(4, tempC.getCustomerPhone());
					p1.setString(5, tempC.getCustomerZip());
					p1.setString(6, tempC.getCustomerPassword());
					p1.setInt(7, currentCustomer.getCustomerID());
					p1.executeUpdate();
					System.out.print("Customer profile succesfully updated: \n");
					tempC.setCustomerID(currentCustomer.getCustomerID());
					currentCustomer=tempC; //used a temp customer to allow the switching, this will now change it over to the new customer data -Jack
					//The above inserts new data into the DB and then alerts the user that it was successfully updated-Jack
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else
				System.out.println("Email is already taken, no changes made");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}



	private static void viewOpenCustomerOrder(Connection connection) {
		String sql="SELECT * FROM [Order] WHERE customerID= ? AND orderStatus!='Order Complete'";
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
			do {
				String s = rs.getInt("orderID")+") " +rs.getString("orderStatus") +", "+rs.getFloat("totalPrice");
				if(rs.getBoolean("paid")==false)
					s+=", unpaid";
				else
					s+=", paid";
				System.out.println(s);
			}
			while(rs.next());
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
    	String fInsert = "INSERT FoodItem ([foodName], [foodPrice], [calories], [description], [prepTime], [categoryID], [restaurantID]) VALUES (?, ?, ?, ?, ?, ?, ?);";
    	try {
    		PreparedStatement p = connection.prepareStatement(fInsert);
    		p.setString(1, f1.getFoodName());
    		p.setFloat(2, f1.getFoodPrice());
    		p.setInt(3, f1.getCalories());
    		p.setString(4, f1.getDescription());
    		p.setInt(5, f1.getPrepTime());
    		p.setInt(6, f1.getCategoryID());
    		p.setInt(7, currentRestaurant.getRestaurantID());
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
    
    
    
    public static void typeSearch(Connection connection)
    {
    	printType(connection);
    	System.out.println("Enter ID of type to search: ");
    	int searchID;
    	Scanner i = new Scanner(System.in);
    	searchID=i.nextInt();
    	i.nextLine();
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
    	String sql="SELECT lineItemNumber, foodName, ROUND(foodPrice,2) AS 'foodPriceR'\r\n"
				+ "FROM [Order] JOIN LineItem ON [Order].orderID=LineItem.orderID JOIN FoodItem ON LineItem.foodItemID=FoodItem.foodItemID \r\n"
				+ "WHERE orderStatus='preparing'";
    	String r="";
    	String sql2= "SELECT DISTINCT [Order].orderID, orderStatus, ROUND(totalPrice, 2) AS 'totPriceR', totalPrepTime, restaurantName, restaurantLocation, firstName, lastName, customerLocation\r\n"
				+ "FROM [Order] JOIN LineItem ON [Order].orderID=LineItem.orderID JOIN FoodItem ON LineItem.foodItemID=FoodItem.foodItemID JOIN Customer ON [Order].customerID=Customer.customerID JOIN Restaurant ON FoodItem.restaurantID=Restaurant.restaurantID\r\n"
				+ "WHERE orderStatus='preparing'";

    	Statement statement;
    	ResultSet rs;
    	try {
			statement= connection.createStatement();
			
			rs=statement.executeQuery(sql2);
			if (rs.next() == false) 
			{
		        r="No Open Orders";
		        System.out.println(r);
		    } 
			else 
			{
				do
				{
					r+="\n ["+rs.getInt("orderID") +"] "+rs.getString("orderStatus")+", "+rs.getFloat("totPriceR")+", "+rs.getInt("totalPrepTime") +", "+rs.getString("restaurantName")+", "+rs.getString("restaurantLocation") + ", "+rs.getString("firstName") + ", "+ rs.getString("lastName")+", "+rs.getString("customerLocation");
					System.out.println(r);
					r="";
				}
				while (rs.next());
				Scanner uInput = new Scanner(System.in);
				System.out.println("Enter id of order you want");
				int oSelect =uInput.nextInt();
				uInput.nextLine();
				System.out.println("Enter the zip code you are currently in: ");
				String zip=uInput.nextLine();
				selectOrder(connection, oSelect, zip); //calls the method that will update the orderTable to say it's selected and to create a delivery -Jack
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
    	int cID=0;
    	String sqlGetC = "SELECT customerID"
    			+ "\nFROM [Order]"
    			+ "\nWHERE [Order].orderID= ?";
    	try {
    		PreparedStatement p = connection.prepareStatement(sqlGetC);
    		p.setInt(1,  orderID);
    		ResultSet rs;
    		rs=p.executeQuery();
    		while(rs.next())
    		{
    			cID=rs.getInt("customerID"); //gets the customerID of the order to put in -Jack
    		}
    	}catch(SQLException e) {
			e.printStackTrace();
		} 
    	
    	int totalTime=calcTotalTime(connection, orderID); //Calculates totalTime of the order, for now this just includes the food items, may later include driving time -Jack
    	String sqlIn = "INSERT Delivery(estimatedTimeToArrival, startLocation, customerID, orderID, driverID) VALUES(?, ?, ?, ?, ?, ?, ?, ?)"; //SQL Insert statement that creates a delivery -Jack
    	try {
    		PreparedStatement p2 = connection.prepareStatement(sqlIn);
    		p2.setInt(1, totalTime);
    		p2.setString(2, curLoc);
    		p2.setInt(3, cID);
    		p2.setInt(4, orderID);
    		p2.setInt(5, currentDriver.getDriverID());
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
    		p3.executeUpdate(); //This whole try catch changes the order status to alert the user it has been taken -Jack
    	} catch(SQLException e) {
			e.printStackTrace();
		} 
    }
}