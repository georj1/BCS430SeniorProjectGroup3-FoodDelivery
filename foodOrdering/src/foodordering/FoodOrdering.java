/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodordering;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	private static Customer currentCustomer = new Customer();
    public static void main(String[] args) 
    {
    	
    	String url = "jdbc:sqlserver://DESKTOP-NJ7L5JK\\sqlexpress;integratedSecurity=true;databaseName=master;"; //this is the server URL on my local machine -Jack
    	String user ="NT Service\\MSSQL$SQLEXPRESS"; //not needed right now but might be needed for remote access -Jack
    	String password="bcs430group3"; // same as above comment -Jack
    	Scanner scin = new Scanner(System.in);
    	Scanner scSin = new Scanner(System.in); //Scanner is used to get data from the user -Jack
    	int userIn=-1;
    	 //right now this is an empty string but it will contain the actual SQL statements and then be passed to the execute update method -Jack
    	
    	
    	try 
    	{
    		//Test
    		Connection connection = DriverManager.getConnection(url); //this is attempting to open the connection to the server -Jack
    		System.out.println("Connected"); //Display a message to show it successfully connected -Jack
    		while(userIn !=0) //Once again is temporary, for now this is so it can be tested and accessed multiple times, will eventually have a nicer UI -Jack
    		{
    			System.out.println("\nPlease let us know what you'd like to do by entering the number:  "
    					+ "\n[1] Enter a Restaurant"
    					+"\n[2] View Restaurants"
    					+ "\n[3] Enter a Customer"
    					+ "\n[4] View Customers"
    					+ "\n[5] Select a Restaurant"
    					+ "\n[6] Search Restaurnts by Zip Code"
    					+ "\n[7] After Selecting a Restaurant, create an order"
    					+ "\n[0] Exit"); //For now this well be used to see if a Restaurant or Customer is being added and other functionality -Jack
    			userIn = scin.nextInt(); //code to put the user input into a String -Jack
    			switch(userIn) //Temporary make naviagtion easier while waiting for Java FX -Jack
    			{
    			case 0: 
    				break;
    			case 1: 
    				Restaurant r1 = new Restaurant(); //create a new Restaurant object that the user will enter information into, will eventually be sent to database -Jack
				    System.out.println("\nEnter Restaurant Name: "); //very repetitive, will be done better later, all of these will just get the information to fill out Restaurant -Jack
				    r1.setRestaurantName(scin.nextLine()); //set the RestaurantName of our temporary Restaurant to what they enter, could be error checked later -Jack
				    System.out.println("\nEnter Restaurant City: "); 
				    r1.setRestaurantCity(scin.nextLine()); 
				    System.out.println("\nEnter Restaurant State: "); 
				    r1.setRestaurantState(scin.nextLine());
				    System.out.println("\nEnter Restaurant Street: "); 
				    r1.setRestaurantStreet(scin.nextLine());
				    System.out.println("\nEnter Restaurant Zip: "); 
				    r1.setRestaurantZip(scin.nextLine());
				    System.out.println("\nEnter Restaurant Type: "); 
				    r1.setRestaurantType(scin.nextLine());
				
				    insertRestaurant(r1, connection); 
    				break;
    			case 2:
    				showRestaurants(connection); //Call the method that shows the data -Ahsan
    		    	break;
    			case 3:
    				Customer c1 = new Customer(); //create a new Customer object that the user will enter information into, will eventually be sent to database -Jack
    				System.out.println("\nEnter First Name: ");
    				c1.setCustomerFName(scin.nextLine());
    				System.out.println("\nEnter Last Name: ");
    				c1.setCustomerLName(scin.nextLine());
    				System.out.println("\nEnter Email: ");
    				//c1.setCustomerEmail(scin.nextLine());
    				//System.out.println("\nEnter Phone Number: ");
    				c1.setCustomerPhone(scin.nextLine());
    				System.out.println("\nEnter City: ");
    				c1.setCustomerCity(scin.nextLine());
    				System.out.println("\nEnter State: ");
    				c1.setCustomerState(scin.nextLine());
    				System.out.println("\nEnter Street: ");
    				c1.setCustomerStreet(scin.nextLine());
    				System.out.println("\nEnter Zip: ");
    				c1.setCustomerZip(scin.nextLine());
    				//Same as Restaurant code -Jack
    		
    				insertCustomer(c1, connection); //Call the method that inserts the data -Jack
    				break;
    			case 4:
    				customerData(connection);
    				break;
    			case 5:
    				
    				showRestaurants(connection); //Call the method that shows the data -Ahsan
    				System.out.println("Enter the nummber of the restaurant you want to order from: ");
    				Scanner selectR = new Scanner(System.in); //Scanner is used to get data from the user -Jack
    		    	int rSelectIn=0;
    		    	rSelectIn=selectR.nextInt();
    		    	selectRestaurant(connection, rSelectIn); //method to select a restaurant based on the number they clicked which is just the restaurant id -Jack
    				break;
    			case 6:
    				System.out.println("\nEnter a zipcode to search for restaurants in that zip code: "); 
    				String searchZip = scSin.nextLine();
    				rZipSearch(searchZip, connection);
    				
    			//TODO: Implement a method that allows the customer to add and remove food from an order
    			case 7:
    				String cEmail;
    				System.out.println("Please enter your email linked to your account: ");
    				Scanner cIn = new Scanner(System.in);
    				cEmail=cIn.nextLine();
    				getCurrentCustomer(connection, cEmail);
    			//TODO: Implement a method that allows the restaurant to enter and remove menu items
    				
    			//TODO: Implement a method to search for restaurants based on the Type of Restaurant, pulling from the Type table to give them options
    			default:
    				break;
    			
    			}
    		}
    	}
    	catch (SQLException e) 
    	{ //catch an error -Jack
    		System.out.println("ERROR"); //display that there was an error -Jack
    		e.printStackTrace(); //display what the error was -Jack
    	}
    }
    public static void customerData(Connection connection) 
    {
    	System.out.print("Displaying customer data: \n");
    	String sql = "SELECT * FROM Customer"; // will get all data regarding customer - ahsan
    	Statement statement;
		try {
			ResultSet rs;
			statement = connection.createStatement();
			rs = statement.executeQuery(sql); //execute sql statement
			while(rs.next())
			{
				String s = rs.getString("FirstName")+ ", "+rs.getString("LastName")+ ", "+rs.getString("City")+", "+rs.getString("Street")+", "+rs.getString("State")+", "+rs.getString("ZipCode")+", "+rs.getString("Email");
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
    	String sql = "SELECT * FROM Restaurant"; // will get all data from restaurant - ahsan
    	Statement statement;
		try {
			ResultSet rs;
			statement = connection.createStatement();
			rs = statement.executeQuery(sql); //execute sql statement
			while(rs.next())
			{
				String s = "[" + rs.getString("RestaurantID")+ "] " + rs.getString("RestaurantName")+ ": "+rs.getString("City")+", "+rs.getString("Street")+", "+rs.getString("State")+", "+rs.getString("ZipCode")+", "+rs.getString("RestaurantType")+", "+rs.getString("RestaurantRating");
				System.out.println(s);
				//The above displays the information and breaks the rows into a ResultSet which we then pull the data from to display - Ahsan
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
    } 
    public static void insertRestaurant(Restaurant r1, Connection connection)
    {
    	
    	String rInsert = "USE master INSERT [dbo].[Restaurant] ([RestaurantName], [City], [Street], [State], [ZipCode], [RestaurantType], [RestaurantRating]) VALUES ('"+ r1.getRestaurantName()+"', '" + r1.getRestaurantCity()+"', '"+r1.getRestaurantStreet()+"', '"+r1.getRestaurantState()+"', "+r1.getRestaurantZip()+", '"+r1.getRestaurantType()+"', NULL);";
    	Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(rInsert); //execute sql statement
			System.out.print("Restaurant data succesfully inserted: \n");
			//The above inserts new data into the DB and then alerts the user that it was successful -Jack
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    	
    }
    public static void insertCustomer(Customer c1, Connection connection)
    {
    	
    	String cInsert = "USE master INSERT [dbo].[Customer] ([LastName], [FirstName], [City], [Street], [State], [ZipCode], [Email]) VALUES ('"+ c1.getCustomerLName()+"', '" + c1.getCustomerFName()+"', '"+c1.getCustomerCity()+"', '"+c1.getCustomerStreet()+"', '"+c1.getCustomerState()+"', "+c1.getCustomerZip()+", '"+c1.getCustomerEmail()+"');";
    	Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(cInsert); //execute sql statement
			System.out.print("Customer data succesfully inserted: \n");
			//The above inserts new data into the DB and then alerts the user that it was successful -Jack
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    }
    public static void rZipSearch(String searchZip, Connection connection)
    {
    	String sql="SELECT * FROM [dbo].[Restaurant] WHERE ZipCode="+searchZip+";"; //code to get all data from the restaurant -Jack
		Statement statement;
		try {
			ResultSet rs;
			statement = connection.createStatement();
			rs = statement.executeQuery(sql); //execute sql statement
			while(rs.next())
			{
				String s = "[" + rs.getString("RestaurantID") + "] "+ rs.getString("RestaurantName")+ ": "+rs.getString("City")+", "+rs.getString("Street")+", "+rs.getString("State")+", "+rs.getString("ZipCode")+", "+rs.getString("RestaurantType")+", "+rs.getString("RestaurantRating");
				System.out.println(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} //this isn't used yet but is how SQL statements will be inputed -Jack
		
    }
    public static void selectRestaurant(Connection connection, int rSelect)
    {
    	String sql="SELECT restaurantID, restaurantName, menuID, Restaurant.restaurantTypeID, restaurantType, AVG(ratingScore) AS restaurantAverageRating FROM [dbo].[Restaurant] JOIN RestaurantType ON Restaurant.restaurantTypeID=RestaurantType.restaurantTypeID JOIN Rating ON Restaurant.ratingID=Rating.RatingID WHERE RestaurantID="+rSelect+";"; //code to get the restaurant based on the ID the customer selected, will need to have the menu added -Jack
		Statement statement;
		try {
			ResultSet rs;
			statement = connection.createStatement();
			rs = statement.executeQuery(sql); //execute sql statement
			while(rs.next())
			{
				selectedRestaurant.setRestaurantID(rs.getInt("restaurantID"));
				selectedRestaurant.setRating(rs.getFloat("restaurantAverageRating"));
				selectedRestaurant.setRestaurantName(rs.getString("restaurantName"));
				selectedRestaurant.setMenuID(rs.getInt("MenuID"));
				selectedRestaurant.setRestaurantType(rs.getString("restaurantType"));
				String s = rs.getString("RestaurantName")+ ": "+rs.getString("RestaurantType")+", "+rs.getFloat("restaurantAverageRating"); //Display the Restaurant for now, will eventually be the menu -Jack
				System.out.println(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
    }
    public void displayMenu(Connection connection)
    {
    	String sql="SELECT FoodItem.foodItemID, foodName, FoodItem.[description], foodPrice, categoryName"
    			+"\nFROM Restaurant JOIN Menu ON Restaurant.menuID=Menu.menuID JOIN FoodItem ON Menu.foodItemID=FoodItem.foodItemID JOIN Category ON FoodItem.categoryID=Category.categoryID"
    			+"WHERE RestaurantID="+selectedRestaurant.getRestaurantID()+";"; //Very lengthy SQL statement here, it is selecting the menu based on the selected Restaurant and then uses joins to get all the important menu information -Jack
    	Statement statement;
		try {
			ResultSet rs;
			statement = connection.createStatement();
			rs = statement.executeQuery(sql); //execute sql statement
			while(rs.next())
			{
				String s = "["+rs.getInt("foodItemID")+ "] "+rs.getString("foodName")+", "+rs.getString("description")+", "+rs.getFloat("foodPrice")+", "+rs.getString("categoryName"); //This is the display, it will show the foodItem ID which for now will be used for selecting and all the mneu items separated by commas -Jack
				System.out.println(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
    }
    public static void getCurrentCustomer(Connection connection, String cEmail)
    {
    	String sql="SELECT * FROM [dbo].[Customer] WHERE email="+cEmail+";"; //code to get the restaurant based on the ID the customer selected, will need to have the menu added -Jack
		Statement statement;
		try {
			ResultSet rs;
			statement = connection.createStatement();
			rs = statement.executeQuery(sql); //execute sql statement
			while(rs.next())
			{
				currentCustomer.setCustomerID(rs.getInt("customerID"));
				String s = rs.getString("firstName")+ ": "+rs.getString("lastName")+", "+rs.getString("email")+", "+rs.getString("phone"); //Display the Customer for now, will eventually be the menu -Jack
				System.out.println(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
    }
}
