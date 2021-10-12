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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SuspendAccountController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private Label suspend;
    @FXML
    private TextArea suspendReson;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void backOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("AdminMainPanel.fxml"));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void suspendButtonOnClick(ActionEvent event) {
        ArrayList<User> totalUser = new ArrayList<User>();
        String suspendID = id.getText().toString();
        if (suspendID.equals("")) {
            suspend.setText("Please Enter ID...");
        } else {
            
            
            // user file
            File f = null;
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                f = new File("user.bin");
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                User u;
                try {
                    while (true) {
                        u = (User) ois.readObject();
                        if (u.getId().equals(suspendID)) {
                            u.setIsSuspend(true);
                            u.setSuspendReason(suspendReson.getText().toString());
                        }
                        totalUser.add(u);
                    }
                } catch (Exception e) {
                }
            } catch (IOException ex) {
            } finally {
                 
                try {
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException ex) {
                }

                f = null;
                FileOutputStream fos = null;
                ObjectOutputStream oos = null;
                try {
                    f = new File("user.bin");
                    fos = new FileOutputStream(f);
                    oos = new ObjectOutputStream(fos);

                    for (User us : totalUser) {
                        oos.writeObject(us);
                    }

                } catch (IOException ex) {
                } finally {
                    try {
                        if (oos != null) {
                            oos.close();
                        }
                    } catch (IOException ex) {
                    }

                }
            }

        }
        suspend.setText("This Account has been suspende. ");
        id.setText("");
        suspendReson.setText("");
    }
}
