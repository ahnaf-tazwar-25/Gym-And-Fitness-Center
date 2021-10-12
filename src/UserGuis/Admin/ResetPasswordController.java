/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserGuis.Admin;

import Classes.Manager;
import Classes.Member;
import Classes.Trainer;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class ResetPasswordController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView table;
    @FXML
    private TableColumn<ResetPassword, String> id;
    @FXML
    private TableColumn<ResetPassword, String> name;
    @FXML
    private TableColumn<ResetPassword, String> accType;
    @FXML
    private Label l;

    @FXML
    private void backButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminMainPanel.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    private ObservableList<ResetPassword> showResetList() {
        ObservableList<ResetPassword> returnList = FXCollections.observableArrayList();
        File f = new File("forgotPasswordAccounts.bin");
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
                    if (u instanceof Manager) {
                        type = "Manager";
                    } else if (u instanceof Trainer) {
                        type = "Trainer";
                    } else if (u instanceof Member) {
                        type = "Member";
                    }
                    returnList.add(new ResetPassword(u.getId(), u.getName(), type));
                }
            } catch (Exception e) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    System.out.println("There was an IOException: " + ex);
                }
            }
        } else {
            l.setText("There is no password requests!");
        }

        return returnList;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id.setCellValueFactory(new PropertyValueFactory<ResetPassword, String>("id"));
        name.setCellValueFactory(new PropertyValueFactory<ResetPassword, String>("name"));
        accType.setCellValueFactory(new PropertyValueFactory<ResetPassword, String>("accType"));
        table.setItems(showResetList());
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
                    System.out.println("");
                }
            }
        }
        return returnList;
    }

    private void setForgotPasswordUsers() {
        ArrayList<User> list = new ArrayList<User>();
        ResetPassword r = (ResetPassword) table.getSelectionModel().getSelectedItem();
        File f = new File("forgotPasswordAccounts.bin");
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
                    if (u.getId().equals(r.id)) {
                        continue;
                    }
                    list.add(u);
                }
            } catch (Exception e) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    System.out.println("");
                }
            }

            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
            try {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);

                for (User i : list) {
                    oos.writeObject(i);
                }
                oos.flush();
                oos.close();
            } catch (Exception e) {
                System.out.println("There was an Exception: " + e);
            }
        }

    }

    @FXML
    private void resetPasswordsButtonOnClick(ActionEvent event) throws IOException {
        ResetPassword r = (ResetPassword) table.getSelectionModel().getSelectedItem();
        File f = new File("user.bin");
        if (f.exists()) {
            ArrayList<User> list = getUsers();
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
            try {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
                System.out.println(list);
                for (User u : list) {
                    if (u.getId().equals(r.id)) {
                        u.setForgotPass(true);
                    }
                    oos.writeObject(u);
                }
                oos.flush();
                oos.close();
            } catch (Exception e) {
                System.out.println("There was an Exception: " + e);
            }
            setForgotPasswordUsers();
            table.setItems(showResetList());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Password reset has been successful!");
            alert.showAndWait();
        }
    }

}
