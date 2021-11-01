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
	private static Customer currentCustomer = new Customer(); //This is the current customer for use with the order, it will be replaced with Chris's login system when that is done -Jack
    //private static Driver currentDriver = new Driver(); //This is the current Driver, will be replaced by login eventually hopefully -Jack 
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
    					//+ "\n[6] Search Restaurnts by Zip Code"
    					+ "\n[7] After Selecting a Restaurant, create an order"
    					+ "\n[8] Enter food for a Restaurant"
    					+ "\n[9] Search for a Restaurant based on Type"
    					+ "\n[0] Exit"); //For now this well be used to see if a Restaurant or Customer is being added and other functionality -Jack
    			userIn = scin.nextInt(); //code to put the user input into a String -Jack
    			switch(userIn) //Temporary make navigation easier while waiting for Java FX -Jack
    			{
    			case -2:
    				String sql = "SELECT * "
    						+ "\nFROM [Order] JOIN LineItem ON [Order].orderID=LineItem.orderID JOIN FoodItem ON LineItem.foodItemID=FoodItem.foodItemID"; //SQL statement for us to enter stuff in easily -Jack
    				//System.out.println(sql);
    		    	Statement statement;
    				try {
    					ResultSet rs;
    					statement = connection.createStatement();
    					
    					rs=statement.executeQuery(sql); //execute SQL statement
    					
    					while(rs.next())
    					{
    						String s=""+rs.getInt("orderID")+", "+rs.getInt("customerID")+", "+rs.getInt("driverID")+", "+rs.getString("orderStatus")+", "+rs.getFloat("totalPrice")+", "+rs.getInt("foodItemID")+", "+rs.getString("foodName")+", "+rs.getInt("lineItemNumber");
    						System.out.println(s);
    					}
    					System.out.println("Statement success");
    				} catch (SQLException e) {
    					e.printStackTrace();
    				}
    				break;
    				//The above exists for us to enter in SQL statements and for testing purposes. -Jack
    			case 0: 
    				break;
    			case 1: 
    				Scanner rIn = new Scanner(System.in);
    				Restaurant r1 = new Restaurant(); //create a new Restaurant object that the user will enter information into, will eventually be sent to database -Jack
				    System.out.println("\nEnter Restaurant Name: "); //very repetitive, will be done better later, all of these will just get the information to fill out Restaurant -Jack
				    r1.setRestaurantName(rIn.nextLine()); //set the RestaurantName of our temporary Restaurant to what they enter, could be error checked later -Jack
				    System.out.println("\nEnter Restaurant Type ID from the following: ");
				    getRestaurantType(connection);
				    Scanner tIn = new Scanner(System.in);
				    int typeID = 0;
				    typeID = tIn.nextInt();
				    r1.setRestaurantTypeID(typeID);
				    inputRestaurantType(connection, r1, typeID);
    				break;
    			case 2:
    				showRestaurants(connection); //Call the method that shows the data -Ahsan
    		    	break;
    			case 3:
    				Scanner custIn = new Scanner(System.in);
    				Customer c1 = new Customer(); //create a new Customer object that the user will enter information into, will eventually be sent to database -Jack
    				System.out.println("\nEnter First Name: ");
    				c1.setCustomerFName(custIn.nextLine());
    				System.out.println("\nEnter Last Name: ");
    				c1.setCustomerLName(custIn.nextLine());
    				System.out.println("\nEnter Email: ");
    				c1.setCustomerEmail(custIn.nextLine());
    				System.out.println("\nEnter Phone Number: ");
    				c1.setCustomerPhone(custIn.nextLine());
    				//System.out.println("\nEnter City: ");
    				//c1.setCustomerCity(scin.nextLine());
    				//System.out.println("\nEnter State: ");
    				//c1.setCustomerState(scin.nextLine());
    				//System.out.println("\nEnter Street: ");
    				//c1.setCustomerStreet(scin.nextLine());
    				//System.out.println("\nEnter Zip: ");
    				//c1.setCustomerZip(scin.nextLine());
    				//Same as Restaurant code -Jack
    		
    				insertCustomer(c1, connection); //Call the method that inserts the data -Jack
    				break;
    			case 4:
    				customerData(connection);
    				break;
    			case 5:
    				
    				showRestaurants(connection); //Call the method that shows the data -Ahsan
    				System.out.println("Enter the number of the restaurant you want to order from: ");
    				Scanner selectR = new Scanner(System.in); //Scanner is used to get data from the user -Jack
    		    	int rSelectIn=0;
    		    	rSelectIn=selectR.nextInt();
    		    	selectRestaurant(connection, rSelectIn); //method to select a restaurant based on the number they clicked which is just the restaurant id -Jack
    				break;
    			case 6:
    				//System.out.println("\nEnter a zipcode to search for restaurants in that zip code: "); 
    				//String searchZip = scSin.nextLine();
    				//rZipSearch(searchZip, connection);
    				break;
    				
    			case 7:
    				ArrayList<FoodItem> newFList = new ArrayList<FoodItem>(); //List to store foodItemID's to be put into order later -Jack
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
    				    	/*Okay so the way this crazy thing works is it asks the user to enter the id of the item they want to remove, stores that then because of how ArrayList works
    				    	 * I need to get the object based on the id so it loops through the arrayList until the foodItemID matches the customer selected id to remove, from here it
    				    	 * stores the FoodItem in a temporary FoodItem object where after the loop it will then remove the FoodItem from the ArrayList -Jack
    				    	 */
    				    	
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
    			case 8:
    		        showRestaurants(connection); //Call the method that shows the data -Aayushma
    		        System.out.println("Enter the number of the restaurant you want to add food to: ");
    		        Scanner selectF = new Scanner(System.in); //Scanner is used to get data from the user
    		        int fSelectIn=0;
    		        fSelectIn=selectF.nextInt();
    		        selectRestaurant(connection, fSelectIn); //method to select a restaurant based on the number they clicked which is just the restaurant id -Aayushma
    		        //The above will get the user to pick their restaurant to enter food into -Aayushma
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
    		        f1.setPrepTime(foodDataIn.nextLine());
    		        //The above gets and stores user input about the foodItems -Aayushma
    		        System.out.println("\nEnter category ID from the following:");
    		        getCategory(connection); //Calls the method to allow the user to select categories -Aayushma
    		        Scanner catIn = new Scanner(System.in);
    		        int categoryID = 0;
    		        categoryID = catIn.nextInt();
    		        f1.setCategoryID(categoryID);
    		        inputCategoryType(connection, f1, categoryID); //Calls the method to insert the category based on user input -Aayushma
    				break;
    			case 9:
    				System.out.println("\nEnter the type of restaurant you are looking for:  ");
    				printType(connection);
    				Scanner selectRT = new Scanner(System.in); //Scanner is used to get data from the user -Ahsan
    				int typeSelectIn;
    			    typeSelectIn=selectRT.nextInt();
    			    rTypeSearch(typeSelectIn, connection);
    			    break;
    			case 10:
    				//This method will only be shown to drivers, for now will call a driver login method -Jack
    				scin.nextLine();
    				String dFirstName, dLastName;
    				System.out.println("Enter the first name of the driver: ");
    				dFirstName=scin.nextLine();
    				System.out.println("Enter the last name of the driver: ");
    				dLastName=scin.nextLine();
    				getCurrentDriver(connection, dFirstName, dLastName); //calls the method that will set the current driver. 
    				//viewOrder(connection); //Calls method to view the currently open orders -Jack
    				System.out.println("Enter the ID of the order you want to select: ");
    				int oSelect =scin.nextInt();
    				//selectOrder(connection, oSelect); //calls the method that will update the orderTable to say it's selected and to create a delivery -Jack
    				break;
    			default:
    				break;
    			
    			}
    		}
    	}
    	catch (SQLException e) 
    	{ //catch an error or two -Jack
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
    	/*
    	String sql="SELECT restaurantType"
    			+ "\nFROM RestaurantType"
    			+ "\nWHERE restaurantTypeID="+idToInsert+";";
    			*/
    	
    	String sql="SELECT restaurantType"
    			+ "\nFROM RestaurantType"
    			+ "\nWHERE restaurantTypeID= ?";
    	//Statement statement;
    	
    	
    	try {
			ResultSet rs;
			PreparedStatement p = connection.prepareStatement(sql);
			p.setInt(1, idToInsert);
			//statement = connection.createStatement();
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
    	
    	//String rInsert = "INSERT Restaurant ([restaurantName], [restaurantTypeID]) VALUES ('"+ r1.getRestaurantName()+"', '"+r1.getRestaurantTypeID()+"');";
    	String rInsert = "INSERT Restaurant ([restaurantName], [restaurantTypeID]) VALUES (?, ?);";
    	//Statement statement;
		try {
			PreparedStatement p = connection.prepareStatement(rInsert);
			p.setString(1, r1.getRestaurantName());
			p.setInt(2, r1.getRestaurantTypeID());
			//statement = connection.createStatement();
			p.executeUpdate(); //execute SQL statement
			System.out.print("Restaurant data succesfully inserted: \n");
			//The above inserts new data into the DB and then alerts the user that it was successful -Jack
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    	
    }
    
    
    
    public static void insertCustomer(Customer c1, Connection connection)
    {
    	
    	//String cInsert = "USE master INSERT Customer ([lastName], [firstName], [email], [phone]) VALUES ('"+ c1.getCustomerLName()+"', '" + c1.getCustomerFName()+"', '"+c1.getCustomerEmail()+"', '"+c1.getCustomerPhone()+"');";
    	String cInsert = "USE master INSERT Customer ([lastName], [firstName], [email], [phone]) VALUES (?, ?, ?, ?);";
    	//Statement statement;
		try {
			PreparedStatement p = connection.prepareStatement(cInsert);
			p.setString(1, c1.getCustomerLName());
			p.setString(2, c1.getCustomerFName());
			p.setString(3, c1.getCustomerEmail());
			p.setString(4, c1.getCustomerPhone());
			//statement = connection.createStatement();
			//statement.executeUpdate(cInsert); //execute sql statement
			p.executeUpdate();
			System.out.print("Customer data succesfully inserted: \n");
			//The above inserts new data into the DB and then alerts the user that it was successful -Jack
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    }
    
    
    
    public static void rZipSearch(String searchZip, Connection connection)
    {
    	//String sql="SELECT * FROM [dbo].[Restaurant] WHERE ZipCode="+searchZip+";"; //code to get all data from the restaurant -Jack
    	String sql="SELECT * FROM [dbo].[Restaurant] WHERE ZipCode= ?"; //code to get all data from the restaurant -Jack
		//Statement statement;
		try {
			ResultSet rs;
			PreparedStatement p = connection.prepareStatement(sql);
			p.setString(1, searchZip);
			//statement = connection.createStatement();
			//rs = statement.executeQuery(sql); //execute SQL statement
			rs = p.executeQuery();
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
    	//String sql="SELECT restaurantID, restaurantName, Restaurant.restaurantTypeID, restaurantType FROM [dbo].[Restaurant] JOIN RestaurantType ON Restaurant.restaurantTypeID=RestaurantType.restaurantTypeID  WHERE RestaurantID="+rSelect+";"; //code to get the restaurant based on the ID the customer selected, will need to have the menu added -Jack
    	String sql="SELECT restaurantID, restaurantName, Restaurant.restaurantTypeID, restaurantType FROM [dbo].[Restaurant] JOIN RestaurantType ON Restaurant.restaurantTypeID=RestaurantType.restaurantTypeID  WHERE RestaurantID= ?"; //code to get the restaurant based on the ID the customer selected, will need to have the menu added -Jack
    	//Statement statement;
		try {
			ResultSet rs;
			PreparedStatement p = connection.prepareStatement(sql);
			p.setInt(1, rSelect);
			//statement = connection.createStatement();
			//rs = statement.executeQuery(sql); //execute SQL statement
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
    	/*
    	String sql="SELECT FoodItem.foodItemID, foodName, FoodItem.[description], foodPrice, categoryName"
    			+"\nFROM Restaurant JOIN FoodItem ON Restaurant.restaurantID=FoodItem.restaurantID LEFT JOIN Category ON FoodItem.categoryID=Category.categoryID"
    			+"\nWHERE Restaurant.restaurantID="+selectedRestaurant.getRestaurantID()+";"; //Very lengthy SQL statement here, it is selecting the menu based on the selected Restaurant and then uses joins to get all the important menu information -Jack
    	*/
    	String sql="SELECT FoodItem.foodItemID, foodName, FoodItem.[description], foodPrice, categoryName"
    			+"\nFROM Restaurant JOIN FoodItem ON Restaurant.restaurantID=FoodItem.restaurantID LEFT JOIN Category ON FoodItem.categoryID=Category.categoryID"
    			+"\nWHERE Restaurant.restaurantID= ? "; //New way of writing the SQL statements to prevent SQL injection -Jack
    	//Statement statement;
		try {
			ResultSet rs;
			//statement = connection.createStatement();
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
    
    
    
    public static void getCurrentCustomer(Connection connection, String cEmail)
    {
    	//String sql="SELECT * FROM [dbo].[Customer] WHERE email='"+cEmail+"';"; //code to get the customer based on their email, will tie into login later -Jack
    	String sql="SELECT * FROM [dbo].[Customer] WHERE email= ?"; //code to get the customer based on their email, will tie into login later -Jack
		//Statement statement;
		try {
			ResultSet rs;
			PreparedStatement p = connection.prepareStatement(sql);
			p.setString(1, cEmail);
			//statement = connection.createStatement();
			//rs = statement.executeQuery(sql); //execute SQL statement
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
    	//String sqlCOrder="INSERT [Order](customerID, driverID, orderStatus, totalPrice) VALUES("+ currentCustomer.getCustomerID()+", NULL, 'Preparing', NULL)"; //this statement creates an order when called -Jack
    	String sqlCOrder="INSERT [Order](customerID, driverID, orderStatus, totalPrice) VALUES(?, NULL, 'Preparing', NULL)"; //this statement creates an order when called -Jack
    	//Statement statement;
    	try {
    		PreparedStatement p = connection.prepareStatement(sqlCOrder);
    		p.setInt(1, currentCustomer.getCustomerID());
			//statement=connection.createStatement();
			//statement.executeUpdate(sqlCOrder);
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
		for (FoodItem i:foodList)
		{
			try {
				
				//sqlFInsert+="INSERT LineItem(lineItemNumber, foodItemID, orderID) VALUES("+counter+", "+i.getFoodItemID()+", "+oID+");\n"; //the SQL code and Java to add Line Items -Jack
				sqlFInsert="INSERT LineItem(lineItemNumber, foodItemID, orderID) VALUES("+counter+", ?, ?);\n"; //the SQL code and Java to add Line Items -Jack
				counter++;
				PreparedStatement p2 = connection.prepareStatement(sqlFInsert);
				p2.setInt(1, i.getFoodItemID());
				p2.setInt(2, oID);
				p2.executeUpdate();
			    //totalPrepTime+=i.getPrepTime(); //Commented out for now, will eventually calculate totalPrepTime -Jack
				System.out.println("Added item to the order");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		/*
		try {
			String sqlOrderIn="UPDATE [Order]"
					+ "/nSET totalTime= ?"
					+ "/nWHERE orderID= ?";
			PreparedStatement p3 = connection.prepareStatement(sqlOrderIn);
			p3.setInt(1, totalPrepTime);
			p3.setInt(2, oID);
			p3.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		*/	
			
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
					foodList.add(new FoodItem(rs.getInt("foodItemID"), rs.getString("foodName"), rs.getFloat("foodPrice"), rs.getInt("calories"), rs.getString("description"), rs.getString("type"), rs.getString("prepTime"), rs.getString("categoryName"))); //Adds a foodItem to the list which is essentially a cart for right now -Jack
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
    	/*	
    	String sql="SELECT categoryName"
    				+ "\nFROM Category"
    				+ "\nWHERE categoryID="+idToInsert+";"; //This will get us the full data from the specific category -Aayushma
    				*/
    	String sql="SELECT categoryName"
				+ "\nFROM Category"
				+ "\nWHERE categoryID= ?"; //This will get us the full data from the specific category -Aayushma
    		//Statement statement;
    		try {
    			ResultSet rs;
    			PreparedStatement p = connection.prepareStatement(sql);
    			p.setInt(1, idToInsert);
    			//statement = connection.createStatement();
    			//rs = statement.executeQuery(sql); //execute SQL statement
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
   
    	//String fInsert = "INSERT FoodItem ([foodName], [foodPrice], [calories], [description], [type], [prepTime], [categoryID], [restaurantID]) VALUES ('"+f1.getFoodName()+"', '"+f1.getFoodPrice()+"', '"+f1.getCalories()+"', '"+f1.getDescription()+"', '"+f1.getType()+"', '"+f1.getPrepTime()+"', '"+f1.getCategoryID()+"', '"+selectedRestaurant.getRestaurantID()+"');";
    	//The above is the full insert SQL statement to add Food to the menu -Aayushma
    	String fInsert = "INSERT FoodItem ([foodName], [foodPrice], [calories], [description], [type], [prepTime], [categoryID], [restaurantID]) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    	//Statement statement;
    	try {
    		PreparedStatement p = connection.prepareStatement(fInsert);
    		p.setString(1, f1.getFoodName());
    		p.setFloat(2, f1.getFoodPrice());
    		p.setInt(3, f1.getCalories());
    		p.setString(4, f1.getDescription());
    		p.setString(5, f1.getType());
    		p.setString(6, f1.getPrepTime());
    		p.setInt(7, f1.getCategoryID());
    		p.setInt(8, selectedRestaurant.getRestaurantID());
    			//statement = connection.createStatement();
    			//statement.executeUpdate(fInsert); //execute SQL statement
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
    	/*
    	String sql="SELECT *"
    			+ "\nFROM Restaurant JOIN RestaurantType ON Restaurant.restaurantTypeID=RestaurantType.restaurantTypeID"
    			+ "\nWHERE restaurantType='"+searchType+"';"; //code to get all data from the restaurants with a specific type -Ahsan
    	*/
    	String sql="SELECT *"
    			+ "\nFROM Restaurant JOIN RestaurantType ON Restaurant.restaurantTypeID=RestaurantType.restaurantTypeID"
    			+ "\nWHERE Restaurant.restaurantTypeID= ? ";
    	//Statement statement;
    	try {
    		ResultSet rs;
    		PreparedStatement p = connection.prepareStatement(sql); //Uses a prepared statement to make sure that nothing else can be added -Jack
			p.setInt(1, searchID); //Setting the value in the String -Jack
			rs = p.executeQuery();
    		//statement = connection.createStatement();
    		//rs = statement.executeQuery(sql); //execute SQL statement
    		while(rs.next())
    		{
    			String s = "[" + rs.getString("restaurantID") + "] "+ rs.getString("restaurantName")+ ", "+rs.getString("restaurantType");
    			System.out.println(s); //Displays all the restaurants with a specific type -Ahsan
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} 

    }
    
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
    
    public static void selectOrder(Connection connection, int orderID)
    {
    	String sqlIn = "INSERT Delivery(departTime, estimatedArrivalTime, startLocation, restaurantLocation, customerLocation, totalPrice, driverID) VALUES(?, ?, ?, ?, ?, ?, ?)";
    	try {
    		PreparedStatement p = connection.prepareStatement(sqlIn);
    		p.setString(1, java.time.LocalDateTime.now().toString());
    		//p.setString(2, );
    		//p.setString(3, curLoc);
    		//p.setString(4, restLoc);
    		//p.setString(5, custLoc);
    		//p.setFloat(6, totalPrice);
    		p.setInt(7, orderID);
    		p.executeUpdate();
    	} catch(SQLException e) {
			e.printStackTrace();
		} 
    }
}
