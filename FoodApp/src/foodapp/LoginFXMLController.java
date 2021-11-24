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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author christophersisa
 */
public class LoginFXMLController implements Initializable {

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
    private Text SignUpTextBttn;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
