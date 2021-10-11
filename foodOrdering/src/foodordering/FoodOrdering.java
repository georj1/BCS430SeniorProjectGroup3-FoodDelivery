/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodordering;

import java.sql.Connection;
import java.sql.DriverManager;
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
    public static void main(String[] args) {
        // TODO code application logic here
    	String url = "jdbc:sqlserver://DESKTOP-NJ7L5JK\\sqlexpress;databaseName=master;integratedSecurity=true;"; //this is the server URL on my local machine -Jack
    	String user ="NT Service\\MSSQL$SQLEXPRESS"; //not needed right now but might be needed for remote access -Jack
    	String password="bcs430group3"; // same as above comment -Jack
    	Scanner scin = new Scanner(System.in); //Scanner is used to get data from the user -Jack
    	
    	 //right now this is an empty string but it will contain the actual SQL statements and then be passed to the execute update method -Jack
    	System.out.println("Please let us know if you are a Restaurant or Customer and if you owuld like to enter or see data: "); //For now this well be used to see if a Restaurant or Customer is being added -Jack
    	String rOrC = scin.nextLine(); //code to put the user input into a String -Jack
    	
    	try {
    	Connection connection = DriverManager.getConnection(url); //this is attempting to open the connection to the server -Jack
    	System.out.println("Connected"); //Display a message to show it successfully connected -Jack
    	
    	if(rOrC=="Restaurant Enter" || rOrC=="R E") //checking if they input Restaurant or R for shorthand testing -Jack
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
    		
    		//TODO: input Restaurant data into database - Jack
    	}
    	else if(rOrC=="Customer Enter" || rOrC=="C E")
    	{
    		Customer c1 = new Customer(); //create a new Customer object that the user will enter information into, will eventually be sent to database -Jack
    		System.out.println("\nEnter First Name: ");
    		c1.setCustomerFName(scin.nextLine());
    		System.out.println("\nEnter Last Name: ");
    		c1.setCustomerLName(scin.nextLine());
    		System.out.println("\nEnter Email: ");
    		c1.setCustomerEmail(scin.nextLine());
    		System.out.println("\nEnter Phone Number: ");
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
    		
    		//TODO: input Customer data into database - Jack
    	}
    	else if(rOrC=="Restaurant See" || rOrC=="R S")
    	{
    		String sql="SELECT * FROM Restaurant;"; //code to get all data from the restaurant -Jack
    		Statement statement = connection.createStatement(); //This is how SQL statements will be inputed -Jack
    		statement.executeUpdate(sql); //command to execute the SQL statement -Jack
    		//TODO: Pull Restaurant data from database and display it to user -Jack
    	}
    	else if(rOrC=="Customer See" || rOrC=="C S")
    	{
    		String sql="SELECT * FROM Customer;"; //code to get all data from the Customer -Jack
    		Statement statement = connection.createStatement(); //This is how SQL statements will be inputed -Jack
    		statement.executeUpdate(sql); //command to execute the SQL statement -Jack
    		//TODO: Pull Customer data from database and display it to user -Jack
    	}
    	System.out.println("\nEnter a zipcode to search for restaurants in that zip code: ");
    	String searchZip = scin.nextLine();
    	String sql="SELECT * FROM Restaurant WHERE customerZip="+searchZip+";"; //code to get all data from the restaurant -Jack
		Statement statement = connection.createStatement(); //this isn't used yet but is how SQL statements will be inputed -Jack
		statement.executeUpdate(sql);
    	
    	
    	}catch (SQLException e) { //catch an error -Jack
    		System.out.println("ERROR"); //display that there was an error -Jack
    		e.printStackTrace(); //display what the error was -Jack
    	}
    	
    }
    
}
