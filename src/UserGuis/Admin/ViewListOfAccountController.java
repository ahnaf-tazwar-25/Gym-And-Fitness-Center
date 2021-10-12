/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserGuis.Admin;

import Classes.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class ViewListOfAccountController implements Initializable {

    @FXML
    private Label listOfaccount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String str="";
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        f = new File("user.bin");
        if (!f.exists()) {
            

        } else {
            try {
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                User u;
                try {
                    while (true) {

                        u = (User) ois.readObject();
                        if(!u.getIsSuspend()){
                            str += "Name: "+u.getName()+" ID: "+u.getId()+ " Date Of Birth: " + u.getDob() + " Gender: " 
                                    + u.getGender() + " Email " + u.getEmail() + " Address: "+ u.getAddress() + " Type of Account: " ; 
                            if(u.getId().charAt(0) == '2')
                                str += "Manager";
                            else if(u.getId().charAt(0) == '3')
                                str += "Trainer";
                            else if(u.getId().charAt(0) == '4')
                                str += "Member";
                            str += "\n";
                            System.out.println(u.getPassword());
                        }
                    }
                } catch (Exception e) {  }
            } catch (IOException ex) { } 
            finally {
                try {
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException ex) {
                }
            }
        }
        listOfaccount.setText(str);
    }    

    @FXML
    private void backOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("AdminMainPanel.fxml"));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene2);
        window.show();
    }
    
}
