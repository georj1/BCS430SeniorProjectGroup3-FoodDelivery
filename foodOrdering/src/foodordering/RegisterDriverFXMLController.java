/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodordering;

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
public class RegisterDriverFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
       @FXML
    private TextField FirstNameTF;

    @FXML
    private TextField LastNameTF;

    @FXML
    private TextField PhoneTF;

    @FXML
    private TextField EmailTF;

    @FXML
    private TextField PassTF;

    @FXML
    private TextField ConfPassTF;

    @FXML
    private Button CreateAccBttn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
