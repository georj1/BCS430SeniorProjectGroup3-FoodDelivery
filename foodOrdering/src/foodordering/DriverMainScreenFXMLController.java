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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author christophersisa
 */

public class DriverMainScreenFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private Text DriversNameTxt;

    @FXML
    private Button ProfBttn;

    @FXML
    private Button AcceptOrderBttn;

    @FXML
    private Button OrderHistoryBttn;

    @FXML
    private Button LogoutBttn;

            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
