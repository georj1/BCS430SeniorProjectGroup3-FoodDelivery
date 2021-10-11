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
    	
    	String sql=""; //right now this is an empty string but it will contain the actual SQL statements and then be passed to the execute update method -Jack
    	System.out.println("Are you a Restaurant or a Customer?: "); //For now this well be used to see if a Restaurant or Customer is being added -Jack
    	String rOrC = scin.nextLine(); //code to put the user input into a String -Jack
    	if(rOrC=="Restaurant" || rOrC=="R") //checking if they input Restaurant or R for shorthand testing -Jack
    	{
    		Restaurant r1 = new Restaurant(); //create a new Restaurant object that the user will enter information into, will eventually be sent to database -Jack
    		System.out.println("Enter Restaurant Name: "); //very repetitive, will be done better later, all of these will just get the information to fill out Restaurant -Jack
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
    	}
    	else
    	{
    		
    	}
    	try {
    	Connection connection = DriverManager.getConnection(url); //this is attempting to open the connection to the server -Jack
    	Statement statement = connection.createStatement(); //this isn't used yet but is how SQL statements will be inputed -Jack
    	//statement.executeUpdate(sql); //so this is the statement that will execute the SQL code, I have it commented as I haven't written any SQL yet, a string goes in the parenthesis
    	//which is the SQL statement -Jack
    	System.out.println("Connected"); //Display a message to show it successfully connected -Jack
    	}catch (SQLException e) { //catch an error -Jack
    		System.out.println("ERROR"); //display that there was an error -Jack
    		e.printStackTrace(); //display what the error was -Jack
    	}
    	
    }
    
}
