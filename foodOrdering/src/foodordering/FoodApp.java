/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodordering;

import javafx.application.Application;
import static javafx.application.Application.launch;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author christophersisa
 */
public class FoodApp extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

            try {
				Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
				Scene scene = new Scene(root);
				stage.setTitle("Food Ordering Application");
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e+" LOGIN FXML");
				e.printStackTrace();
			}

    }
    
}
