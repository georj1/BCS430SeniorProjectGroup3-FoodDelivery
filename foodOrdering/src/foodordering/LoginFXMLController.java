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
import java.util.ResourceBundle;
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
    
    public void LoadUserMainScreenActivity(ActionEvent event) throws IOException{
    	String url1 = "jdbc:sqlserver://DESKTOP-NJ7L5JK\\sqlexpress;integratedSecurity=true;databaseName=master;"; 
    	try {
			Connection connection = DriverManager.getConnection(url1);
			EmailTF.getText();
			PasswordTF.getText();
			loginCustomer(connection, EmailTF.getText(), PasswordTF.getText());
			try{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("UserMainScreenFXML.fxml"));
			    Parent root = loader.load();
			    UserMainScreenFXMLController controller = loader.getController();
			    controller.initData(connection, currentCustomer);
			    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			    Scene scene = new Scene(root);
			    stage.setTitle("Food Ordering Application");
			    stage.setScene(scene);
			    stage.show();
			}catch(IOException e){
			    System.out.println("didnt work");
			    System.out.println(e.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
  
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
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
    			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    

}
