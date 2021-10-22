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
	private static Customer currentCustomer = new Customer(); //This is the current customer for use with the order, it will be replaced with Chris's login system when that is done -Jack
    public static void main(String[] args) 
    {
    	
    	String url = "jdbc:sqlserver://DESKTOP-NJ7L5JK\\sqlexpress;integratedSecurity=true;databaseName=master;"; //this is the server URL on my local machine -Jack
    	//String user ="NT Service\\MSSQL$SQLEXPRESS"; //not needed right now but might be needed for remote access -Jack
    	//String password="bcs430group3"; // same as above comment -Jack
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
    			switch(userIn) //Temporary make navigation easier while waiting for Java FX -Jack
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
    				
    			case 7:
    				ArrayList<Integer> newFList = new ArrayList<Integer>(); //List to store foodItemID's to be put into order later -Jack
    				String cEmail; //Will use email to get the customer for now -Jack
    				int nFoodNum=-3; //variable that will do 2 things, add foodItemIDs, control the loops -Jack
    				System.out.println("Please enter your email linked to your account: ");
    				Scanner cIn = new Scanner(System.in); //this will get the email -Jack
    				Scanner nFood = new Scanner(System.in); //this will get the foodItemID -Jack
    				cEmail=cIn.nextLine();
    				
    				//All of the above is to get an email for the account, right now this is instead of login and will have error checking later -Jack
    				getCurrentCustomer(connection, cEmail); //calls the method that sets the current customer -Jack
    				
    				
    				while(nFoodNum!=-1)
    				{
    					displayMenu(connection); //will show the menu -Jack
    				    System.out.println("Enter the numebr of food you want to order:"
    				    		+ "\n[-2] Complete Order "
    						    + "\n[-1] Exit"
    						    + "\n[0] View Items in Order"); //little statement here to give user menu options -Jack
    				    nFoodNum=nFood.nextInt();
    				    if(nFoodNum==0)
    				    {
    				    	viewFoodList(connection, newFList); //this will show what is currently in the order, will soon add functionality to remove items -Jack
    				    	//TODO: Add a method to remove items from the list
    				    }
    				    else if(nFoodNum==-1)
    					    break; //exits the loop -Jack
    				    else if(nFoodNum==-2)
    				    {
    				    	addFood(connection, newFList); //This is the complete order method and will add all the food items to the order through the database -Jack
    				    }
    				    else
    				    {
    				    	newFList.add(nFoodNum); //Adds a foodItem to the list which is essentially a cart for right now -Jack
    				    }
    				}
    				break;
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
    	String sql = "SELECT * FROM Customer"; // will get all data regarding customer - Ahsan
    	Statement statement;
		try {
			ResultSet rs;
			statement = connection.createStatement();
			rs = statement.executeQuery(sql); //execute SQL statement
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
    	String sql = "SELECT * FROM Restaurant"; // will get all data from restaurant - Ahsan
    	Statement statement;
		try {
			ResultSet rs;
			statement = connection.createStatement();
			rs = statement.executeQuery(sql); //execute SQL statement
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
			statement.executeUpdate(rInsert); //execute SQL statement
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
				//adds the information to our selected restaurant for more global use -Jack
				String s = rs.getString("RestaurantName")+ ": "+rs.getString("RestaurantType")+", "+rs.getFloat("restaurantAverageRating"); //Display the Restaurant for now, will eventually be the menu -Jack
				System.out.println(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
    }
    
    
    
    public static void displayMenu(Connection connection)
    {
    	String sql="SELECT FoodItem.foodItemID, foodName, FoodItem.[description], foodPrice, categoryName"
    			+"\nFROM Restaurant JOIN Menu ON Restaurant.menuID=Menu.menuID JOIN FoodItem ON Menu.foodItemID=FoodItem.foodItemID JOIN Category ON FoodItem.categoryID=Category.categoryID"
    			+"WHERE RestaurantID="+selectedRestaurant.getRestaurantID()+";"; //Very lengthy SQL statement here, it is selecting the menu based on the selected Restaurant and then uses joins to get all the important menu information -Jack
    	Statement statement;
		try {
			ResultSet rs;
			statement = connection.createStatement();
			rs = statement.executeQuery(sql); //execute SQL statement
			while(rs.next())
			{
				String s = "["+rs.getInt("foodItemID")+ "] "+rs.getString("foodName")+", "+rs.getString("description")+", "+rs.getString("categoryName")+", "+rs.getFloat("foodPrice"); //This is the display, it will show the foodItem ID which for now will be used for selecting and all the menu items separated by commas -Jack
				System.out.println(s); //displays the above information to the console -Jack
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
    }
    
    
    
    public static void getCurrentCustomer(Connection connection, String cEmail)
    {
    	String sql="SELECT * FROM [dbo].[Customer] WHERE email="+cEmail+";"; //code to get the customer based on their email, will tie into login later -Jack
		Statement statement;
		try {
			ResultSet rs;
			statement = connection.createStatement();
			rs = statement.executeQuery(sql); //execute SQL statement
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
    
    
    
    public static void addFood(Connection connection, ArrayList<Integer> foodList)
    {
    	String sqlCOrder="INSERT [Order](customerID, driverID, orderStatus, totalPrice) VALUES("+ currentCustomer.getCustomerID()+", NULL, 'Preparing', NULL)"; //this statement creates an order when called -Jack
    	Statement statement;
    	try {
			statement=connection.createStatement();
			statement.executeQuery(sqlCOrder);
			System.out.println("Order Created");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    	
    	
    	int oID=-1;
    	String sqlGetOrderID="SELECT orderID FROM [Order] ORDER BY orderID DESC LIMIT 1;"; //gets the most recent orderID, will eventually add from the current customer, this will get the just placed order -Jack
    	Statement statement1;
    	try {
			statement1=connection.createStatement();
			
			ResultSet rs;
			rs=statement1.executeQuery(sqlGetOrderID);
			while(rs.next())
			{
				oID=rs.getInt("orderID");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    	String sqlFInsert=""; //this will fill with insert statements to add LineItems to the order -Jack
		for (int i:foodList)
		{
			int counter=1;
			sqlFInsert+="INSERT LineItem(lineItemNumber, foodItemID, orderID) VALUES("+counter+", "+i+", "+oID+");\n"; //the SQL code and Java to add Line Items -Jack
			counter++;
		}
    	Statement statement2;
			try {
				statement2 = connection.createStatement();
				statement2.executeQuery(sqlFInsert); //execute SQL statement
				System.out.println("Added all items to the order");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
    }
    
    
    
    public static void viewFoodList(Connection connection, ArrayList<Integer> foodList)
    {
    	String sql="SELECT * FROM FoodItem WHERE"; //code to get the restaurant based on the ID the customer selected, will need to have the menu added -Jack
    	for(int i : foodList)
    	{
    		sql+=(" foodItemID= "+i);
    	}
		Statement statement;
			try {
				ResultSet rs;
				statement = connection.createStatement();
				rs = statement.executeQuery(sql); //execute SQL statement
				while(rs.next())
				{
					String s = rs.getString("foodName")+", "+rs.getString("description")+", "+rs.getInt("calories")+", "+ rs.getString("categoryName")+", "+rs.getFloat("foodPrice");
					System.out.println(s);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
    }
}
