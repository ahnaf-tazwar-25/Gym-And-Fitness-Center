/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserGuis.Admin;

import Classes.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class DeleteAccountController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField accID;

    @FXML
    private void backOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("AdminMainPanel.fxml"));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene2);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private boolean isAccountExist(String id) {
        boolean returnValue = false;
        File f = new File("user.bin");
        if (f.exists()) {
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                User u = null;
                while ((u = (User) ois.readObject()) != null) {
                    if (u.getId().equals(id)) {
                        returnValue = true;
                        break;
                    }
                }
            } catch (Exception e) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    System.out.println("There was an IOException!");
                }
            }
            try {
                ois.close();
            } catch (IOException ex) {
                System.out.println("There was an IOException!");
            }
        }
        return returnValue;
    }

    private ArrayList<User> tempUsers() {
        ArrayList<User> returnArrayList = new ArrayList<User>();
        File f = new File("user.bin");
        FileInputStream fis = null;    
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            User u = null;
            while ((u = (User) ois.readObject()) != null) {
                returnArrayList.add(u);
            }
        } catch (Exception e) {
            try {
                ois.close();
            } catch (IOException ex) {
                System.out.println("There was an IOException!");
            }
        }
        return returnArrayList;
    }

    @FXML
    private void deleteButtonOnClick(ActionEvent event) {
        String id = accID.getText();
        if (isAccountExist(id)) {

            ArrayList<User> tempList = tempUsers();
            File f = new File("user.bin");
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
            try {
                fos = new FileOutputStream(f);
                
                oos = new ObjectOutputStream(fos);
                for (User u : tempList) {
                    if (id.equals(u.getId())) {
                        continue;
                    }
                    oos.writeObject(u);
                }
                oos.flush();
                oos.close();
            } catch (Exception e) {
                System.out.println("There was an exception: " + e);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("The account has been successfully deleted!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Invalid user ID!");
            alert.showAndWait();
        }

    }

}
