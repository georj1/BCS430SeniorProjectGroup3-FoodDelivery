/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author christophersisa
 */
public class DriverProfileFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField ProfileTF;

    @FXML
    private TextField passwordTF;

    @FXML
    private TextField FirstNameTF;

    @FXML
    private TextField LastNameTF;

    @FXML
    private TextField AddressTF;

    @FXML
    private Button EditBttn;

    @FXML
    private Button CancelBttn;

    @FXML
    private Button SaveBttn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
