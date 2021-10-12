/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ampm_gym_and_fitness;

import Classes.Manager;
import Classes.Member;
import Classes.Trainer;
import Classes.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Label notice;

    @FXML
    private void logInPanelOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void galleryPanelOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gallery.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void careerPanelOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("jobInformation.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void aboutPanelOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("About.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void programButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("programe.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String str = "There is no Notice.";
        File f = null;
        Scanner sc;
        try {
            f = new File("features.txt");
            if (f.exists()) {
                str = "";
                sc = new Scanner(f);
                while (sc.hasNext()) {
                    str += sc.nextLine();
                }
            }
        } catch (Exception e) {

        }

        notice.setText(str);
    }

    @FXML
    private void closeButtonOnClick(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

    @FXML
    private void adminButtonOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("/UserGuis/Admin/AdminMainPanel.fxml"));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void butButtonOnClick(ActionEvent event) throws IOException {
        File f = new File("user.bin");
        //File f = new File("forgotPasswordAccounts.bin");
        if (f.exists()) {
            String s = "";
            User u = null;
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            try {
                while ((u = (User) ois.readObject()) != null) {
                    if (u instanceof Member) {
                        s = s + "Member - " + u.getName() + " ID: " + u.getId() + " Password: " + u.getPassword();
                        s = s + (u.getIsForgotPass() ? " TRUE" : " False") + "\n";
                    } else if (u instanceof Manager) {
                        s = s + "Manager - " + u.getName() + " ID: " + u.getId() + " Password: " + u.getPassword();
                        s = s + (u.getIsForgotPass() ? " TRUE" : " False") + "\n";
                    } else if (u instanceof Trainer) {
                        s = s + "Trainer - " + u.getName() + " ID: " + u.getId() + " Password: " + u.getPassword();
                        s = s + (u.getIsForgotPass() ? " TRUE" : " False") + "\n";
                    }

                }
            } catch (Exception e) {
                ois.close();
            }
            notice.setText(s);
        }
    }

}
