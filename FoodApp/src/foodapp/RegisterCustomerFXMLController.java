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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author christophersisa
 */
public class RegisterCustomerFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField FirstNameTF;

    @FXML
    private TextField LastNameTF;

    @FXML
    private TextField EmailTF;

    @FXML
    private TextField PhoneTF;

    @FXML
    private TextField ZipTF;

    @FXML
    private TextField Brentwood;

    @FXML
    private TextField StateTF;

    @FXML
    private TextField StreetTF;

    @FXML
    private TextField PassTF;

    @FXML
    private TextField ConfPassTF;

    @FXML
    private Button CreateAccBttn;
    
    

    public void LoadLoginActivity(ActionEvent event) throws IOException{
        try{
            Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Food Ordering Application");
            stage.setScene(scene);
            stage.show();
            
         
        }catch(IOException e){
            System.out.println("Couldnt load login Activity");
            System.out.println(e.toString());
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }    
    
}
