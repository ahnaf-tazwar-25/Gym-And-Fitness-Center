/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ampm_gym_and_fitness;

import Classes.User;
import UserGuis.Manager.ManagerMainPanelController;
import UserGuis.Member.MemberMainPanelController;
import UserGuis.Trainer.TrainerMainPanelController;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class ResetPassController implements Initializable {

    @FXML
    private TextField pass;
    @FXML
    private TextField retypePass;
    @FXML
    private Label setL;

    User u = null;
    String id = null;

    public void passData(User u) {
        this.u = u;
        setL.setText("Change Password ");

    }

    public void passDataForReset(String s) {
        id = s;
        setL.setText("Reset Password");
    }

    @FXML
    private void resetPassButtonOnClick(ActionEvent event) throws IOException {
        if (pass.getText().equals(retypePass.getText())) {
            String id = "";
            if (this.id == null) {
                id = u.getId();
            } else {
                id = this.id;
            }
            String newPass = pass.getText();
            File f = new File("user.bin");
            if (f.exists()) {
                ObjectOutputStream oos = null;
                FileOutputStream fos = null;
                ArrayList<User> list = getUsers();
                try {
                    fos = new FileOutputStream(f);
                    oos = new ObjectOutputStream(fos);
                    System.out.println(list);
                    for (User i : list) {
                        if (i.getId().equals(id)) {
                            i.setPassword(newPass);
                            i.setForgotPass(false);
                        }
                        oos.writeObject(i);
                    }
                    oos.flush();
                    oos.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Password has been successfully reset!");
                    alert.showAndWait();
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("There was an exception: " + e);
                    alert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Passwords does not match!");
            alert.showAndWait();
        }

    }

    private ArrayList<User> getUsers() {
        ArrayList<User> returnList = new ArrayList<User>();
        File f = new File("user.bin");
        String s = "";
        if (f.exists()) {
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            String type = "";
            User u = null;
            try {
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                while ((u = (User) ois.readObject()) != null) {
                    returnList.add(u);
                }
            } catch (Exception e) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    System.out.println("There waw an exception: " + ex);
                }
            }
        }
        return returnList;
    }

    @FXML
    private void backButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        if (u != null && u.getId().charAt(0) == '2') {
            loader.setLocation(getClass().getResource("/UserGuis/Manager/ManagerMainPanel.fxml"));
            Parent personViewParent = loader.load();
            Scene personViewScene = new Scene(personViewParent);
            ManagerMainPanelController controller1 = loader.getController();
            controller1.passData(u);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(personViewScene);
            window.show();
        } else if (u != null && u.getId().charAt(0) == '3') {
            loader.setLocation(getClass().getResource("/UserGuis/Trainer/TrainerMainPanel.fxml"));
            Parent personViewParent = loader.load();
            Scene personViewScene = new Scene(personViewParent);
            TrainerMainPanelController controller1 = loader.getController();
            controller1.passData(u);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(personViewScene);
            window.show();
        } else if (u != null && u.getId().charAt(0) == '4') {
            loader.setLocation(getClass().getResource("/UserGuis/Member/MemberMainPanel.fxml"));
            Parent personViewParent = loader.load();
            Scene personViewScene = new Scene(personViewParent);
            MemberMainPanelController controller1 = loader.getController();
            controller1.passData(u);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(personViewScene);
            window.show();
        } else {
            loader.setLocation(getClass().getResource("/ampm_gym_and_fitness/LogIn.fxml"));
            Parent personViewParent = loader.load();
            Scene personViewScene = new Scene(personViewParent);
            LogInController controller1 = loader.getController();
            controller1.passData(u);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(personViewScene);
            window.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
