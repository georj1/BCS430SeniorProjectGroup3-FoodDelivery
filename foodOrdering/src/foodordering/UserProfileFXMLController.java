/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodordering;

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
public class UserProfileFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField ProfileTF;

    @FXML
    private TextField passwordTF;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField LastName;

    @FXML
    private TextField AddressTF;

    @FXML
    private TextField PaymentInfoTF;

    @FXML
    private Button EditBttn;

    @FXML
    private Button CancelBttn;

    @FXML
    private Button SaveBttn;
    
    @FXML
    private Button backBttn;
    
    public void LoadLastProfileActivity(ActionEvent event) throws IOException{
        try{
            Parent root = FXMLLoader.load(getClass().getResource("UserMainScreenFXML.fxml"));
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
        // TODO
    }    
    
}
