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
    public static void main(String[] args) 
    {
    	String url = "jdbc:sqlserver://DESKTOP-NJ7L5JK\\sqlexpress;integratedSecurity=true;databaseName=master;"; //this is the server URL on my local machine -Jack
    	String user ="NT Service\\MSSQL$SQLEXPRESS"; //not needed right now but might be needed for remote access -Jack
    	String password="bcs430group3"; // same as above comment -Jack
    	Scanner scin = new Scanner(System.in); //Scanner is used to get data from the user -Jack
    	String userIn="";
    	 //right now this is an empty string but it will contain the actual SQL statements and then be passed to the execute update method -Jack
    	
    	
    	try 
    	{
    		//Test
    		Connection connection = DriverManager.getConnection(url); //this is attempting to open the connection to the server -Jack
    		System.out.println("Connected"); //Display a message to show it successfully connected -Jack
    		while(!userIn.equals("STOP")) //Once again is temporary, for now this is so it can be tested and accessed multiple times, will eventually have a nicer UI -Jack
    		{
    			System.out.println("Please let us know what you'd like to do:  "); //For now this well be used to see if a Restaurant or Customer is being added and other functionality -Jack
    			userIn = scin.nextLine(); //code to put the user input into a String -Jack
    			if(userIn.equals("Restaurant Enter") || userIn.equals("R E")) //checking if they input Restaurant or R for shorthand testing -Jack
    			{
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
    				
    				insertRestaurant(r1, connection); //Call the method that inserts the data -Jack
    			}
    			else if(userIn.equals("Customer Enter") || userIn.equals("C E"))
    			{
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
    			}
    			else if(userIn.equals("Restaurant See") || userIn.equals("R S"))
    			{
    				restaurantData(connection); //Call the method that shows the data -Ahsan
    			}
    			else if(userIn.equals("Customer See") || userIn.equals("C S"))
    			{
    				customerData(connection); //Call the method that shows the data -Ahsan
    			}
    			else if(userIn.equals("Zipcode search") || userIn.equals("Z S"))
    			{
    		
    				System.out.println("\nEnter a zipcode to search for restaurants in that zip code: "); 
    				String searchZip = scin.nextLine();
    				rZipSearch(searchZip, connection); //call the method to search for restaurants in a certain zip code -Jack
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
    public static void restaurantData(Connection connection)
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
				String s = rs.getString("RestaurantName")+ ": "+rs.getString("City")+", "+rs.getString("Street")+", "+rs.getString("State")+", "+rs.getString("ZipCode")+", "+rs.getString("RestaurantType")+", "+rs.getString("RestaurantRating");
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
				String s = rs.getString("RestaurantName")+ ": "+rs.getString("City")+", "+rs.getString("Street")+", "+rs.getString("State")+", "+rs.getString("ZipCode")+", "+rs.getString("RestaurantType")+", "+rs.getString("RestaurantRating");
				System.out.println(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} //this isn't used yet but is how SQL statements will be inputed -Jack
		
    }
}
