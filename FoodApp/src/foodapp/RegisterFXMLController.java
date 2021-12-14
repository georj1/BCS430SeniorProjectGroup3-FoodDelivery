/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author christophersisa
 */
public class RegisterFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button RestaurantBttn;

    @FXML
    private Button CustomerBttn;

    @FXML
    private Button DriverBttn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void LoadRegisterDriverActivity(ActionEvent event) throws IOException{
        try{
            Parent root = FXMLLoader.load(getClass().getResource("RegisterDriverFXML.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Food Ordering Application");
            stage.setScene(scene);
            stage.show();
            
         
        }catch(IOException e){
            System.out.println("Couldnt load Driver Activity");
            System.out.println(e.toString());
        }

    }
    
    
    public void LoadRegisterCustomerActivity(ActionEvent event) throws IOException{
        try{
            Parent root = FXMLLoader.load(getClass().getResource("RegisterCustomerFXML.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Food Ordering Application");
            stage.setScene(scene);
            stage.show();
            
         
        }catch(IOException e){
            System.out.println("Couldnt load Customer Activity");
            System.out.println(e.toString());
        }

    }
    
    
    
    public void LoadRegisterRestaurantActivity(ActionEvent event) throws IOException{
        try{
            Parent root = FXMLLoader.load(getClass().getResource("RegisterRestaurantFXML.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Food Ordering Application");
            stage.setScene(scene);
            stage.show();
            
         
        }catch(IOException e){
            System.out.println("Couldnt load Restaurant Activity");
            System.out.println(e.toString());
        }

    }
}
