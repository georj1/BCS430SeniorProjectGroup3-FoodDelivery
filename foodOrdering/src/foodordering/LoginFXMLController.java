/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodordering;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author christophersisa
 */
public class LoginFXMLController implements Initializable {

	private static Customer currentCustomer = new Customer();
    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField EmailTF;

    @FXML
    private TextField PasswordTF;

    @FXML
    private Button LoginBttn;

    @FXML
    void LoadRegisterActivity(MouseEvent event) {

    }
    
    public void LoadRegisterActivity(ActionEvent event) throws IOException{
        try{
            Parent root = FXMLLoader.load(getClass().getResource("RegisterFXML.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Food Ordering Application");
            stage.setScene(scene);
            stage.show();
            
         
        }catch(IOException e){
            System.out.println("didnt work");
            System.out.println(e.toString());
        }

    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	String url1 = "jdbc:sqlserver://DESKTOP-NJ7L5JK\\sqlexpress;integratedSecurity=true;databaseName=master;"; 
    	try {
			Connection connection = DriverManager.getConnection(url1); //this is attempting to open the connection to the server -Jack
			EventHandler<ActionEvent> event = new EventHandler<ActionEvent>()
			{
				public void handle(ActionEvent e)
				{
					EmailTF.getText();
					PasswordTF.getText();
					loginCustomer(connection, EmailTF.getText(), PasswordTF.getText());
					
				}
			};
		LoginBttn.setOnAction(event);
			System.out.println("Connected");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    }
    
    private static void loginCustomer(Connection connection, String user, String pass) {
		System.out.println("Enter email");
		String sql="SELECT email FROM Customer WHERE email = ?";
		//String user="", pass="";
		//Scanner cInput = new Scanner(System.in);
		//user=cInput.nextLine();
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
                //pass=cInput.nextLine();
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
			System.out.println("What would you like to do: "
					+ "\n[0] Logout"
					+ "\n[1] View/Update Account Information"
					+ "\n[2] Place an Order"
					+ "\n[3] View Order in Progress"
					+ "\n[4] Leave a review"
					+"");
			uIn=uInput.nextInt();
			uInput.nextLine();
			switch(uIn)
			{
			case 0:
				currentCustomer = new Customer();
				System.out.println("You have been logged out");
				break;
			case 1:
				//viewCustomerAccount(connection);
				break;
			case 2:
				/*
				while (selectedRestaurant == null)
				{
					
				System.out.println("How would you like to search for a restaurant"
						+ "\n[-3] Go Back"
						+ "\n[1] View All"
						+ "\n[2] Search by Zip Code"
						+ "\n[3] Search by Type"
						+ "\n[4] Search by Name"
						+ "");
				int uIn2 = uInput.nextInt();
				uInput.nextLine();
						switch(uIn2)
						{
						case -3:
							break;
						case 1:
							//showRestaurants(connection);
							break;
						case 2:
							//zipSearch(connection);
							break;
						case 3:
							//typeSearch(connection);
							break;
						case 4:
							//nameSearch(connection);
							break;
						default:
							break;
						}
						if(uIn2==-3) //if the user entered to exit then it will break them out of the loop -Jack
							break;
						else
							break;
							//customerOrder(connection);
							 */
				/*}
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
						    + "\n[-1] Exit"
						    + "\n[0] View Items in Order or remove items"); //little statement here to give user menu options -Jack
				    //displayMenu(connection); //will show the menu -Jack
				    nFoodNum=nFood.nextInt();
				    if(nFoodNum==0)
				    {
				    	Scanner remOrBack = new Scanner(System.in);
				    	int rBack = -1;
				    	while(rBack!=0)
				    	{
				    		System.out.println("Enter ID of the item you want to remove: "
    				    			+ "\n[0] Go back");
				    		//viewFoodList(connection, newFList); //this will show what is currently in the order -Jack
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
				    	//addFood(connection, newFList); //This is the complete order method and will add all the food items to the order through the database -Jack
				    	nFoodNum=-1;
				    }
				    else
				    {
				    	//addItemToFList(connection, nFoodNum, newFList);
				    }
				}
				break;
				} */
			case 3:
				//viewOpenCustomerOrder(connection);
				System.out.println("Enter the order id to view more or -3 to go back:");
				uIn = uInput.nextInt();
				uInput.nextLine();
				if(uIn==-3)
					break;
				else
					//viewFullOrder(connection, uIn);
				break;
			case 4:
				//leaveReview(connection);
				break;
			default:
				break;
			}
		}
	}


}
