/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ampm_gym_and_fitness;


import java.io.File;
import java.io.FileWriter;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class CareerController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField contactNumber;
    @FXML
    private TextField email;
    @FXML
    private TextArea education;
    @FXML
    private TextField experience;
    @FXML
    private TextField dateOfBirth;
    
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void backOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("jobInformation.fxml"));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene2);
        window.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submitButtonOnClick(ActionEvent event) throws IOException {
        
        String s = name.getText().toString()+"    " + address.getText().toString()+"    "+ contactNumber.getText().toString()+"    "+
                email.getText().toString()+"    "+education.getText().toString()+"    "+experience.getText().toString()+"    "
                +dateOfBirth.getText().toString()+"\n";
        File f = null;
        FileWriter fw = null;
        try {
            f = new File("biodata.txt");
            if(f.exists()) 	
                fw = new FileWriter(f,true);
            else 
                fw = new FileWriter(f);
           
            fw.write(s);
        } 
        catch (IOException ex) { } 
finally {
            try {
                if(fw != null) fw.close();
            } catch (IOException ex) { }
        }

        name.setText("");
        address.setText("");
        contactNumber.setText("");
        dateOfBirth.setText("");
        email.setText("");
        education.setText("");
        experience.setText("");
        System.out.println("Successfully Done");
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

  
    
        
        
}
