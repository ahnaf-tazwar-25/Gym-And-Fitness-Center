/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ampm_gym_and_fitness;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class JobInformationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private void backOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }
    
    @FXML
    private void applyForTrainerButtonOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("Career.fxml"));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
