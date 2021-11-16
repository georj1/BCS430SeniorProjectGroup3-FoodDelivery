/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodorderingappjavafx;

import java.io.IOException;
import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author christophersisa
 */
public class LoginFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Stage stage;
    private Scene scene;
    private Parent root;
    
 
    @FXML
    private Text Exit;
    
    @FXML
    private TextField PWfield;

    @FXML
    private TextField UNfield;


    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    void closeApp(MouseEvent event) {
        System.exit(0);
    }
    
    public void switchToScene2(ActionEvent event) throws IOException{
        try{
            root = FXMLLoader.load(getClass().getResource("RegisterFXML.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadSplashScreen(){

        try {
            StackPane pane = FXMLLoader.load(getClass().getResource(("SplashScreen.fxml")));
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            fadeIn.play();           
        } catch (IOException ex) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    void HandleRegisterButton(MouseEvent event) {
        
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadSplashScreen();
    }    
    
    
    
}
   
