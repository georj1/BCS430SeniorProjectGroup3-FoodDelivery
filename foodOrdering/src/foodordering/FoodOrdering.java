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
    	try {
    	Connection connection = DriverManager.getConnection(url); //this is attempting to open the connection to the server -Jack
    	Statement statement = connection.createStatement(); //this isn't used yet but is how SQL statements will be inputed -Jack
    	//statement.executeUpdate(); //so this is the statement that will execute the SQL code, I have it commented as I haven't written any SQL yet, a string goes in the parenthesis
    	//which is the SQL statement -Jack
    	System.out.println("Connected"); //Display a message to show it successfully connected -Jack
    	}catch (SQLException e) { //catch an error -Jack
    		System.out.println("ERROR"); //display that there was an error -Jack
    		e.printStackTrace(); //display what the error was -Jack
    	}
    	
    }
    
}
