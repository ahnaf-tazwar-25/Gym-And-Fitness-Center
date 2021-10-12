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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class AppendAccountController implements Initializable {
    @FXML
    private Label appendLabel;
    ObservableList<SuspendAccount> accountNumber = FXCollections.observableArrayList();
    @FXML
    private TableView<SuspendAccount> suspendTable;
    @FXML
    private TableColumn<SuspendAccount, String> id;
    @FXML
    private TableColumn<SuspendAccount, String> name;
    @FXML
    private TableColumn<SuspendAccount, String> accountType;
    @FXML
    private TableColumn<SuspendAccount, String> reason;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id.setCellValueFactory(new PropertyValueFactory<SuspendAccount, String>("id"));
        name.setCellValueFactory(new PropertyValueFactory<SuspendAccount, String>("name"));
        accountType.setCellValueFactory(new PropertyValueFactory<SuspendAccount, String>("accountType"));
        reason.setCellValueFactory(new PropertyValueFactory<SuspendAccount, String>("reason"));
        addSuspendAccountList();
        suspendTable.setItems(accountNumber);

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
    private void appendButtonOnClick(ActionEvent event) throws IOException {

        ArrayList<User> totalUser = new ArrayList<User>();
        SuspendAccount suspendID = suspendTable.getSelectionModel().getSelectedItem();

        suspendTable.getItems().clear();
        String id = suspendID.getId();
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
                    if (u.getId().equals(id)) {
                        u.setIsSuspend(false);
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
            oos.close();
            appendLabel.setText("This Account has been Appended. ");
        }
        
        addSuspendAccountList();
        suspendTable.setItems(accountNumber);

    }

    void addSuspendAccountList() {
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
                        if (u.getIsSuspend() == true) {
                            String type = "";
                            if (u.getId().charAt(0) == '2') {
                                type = "Manager";
                            }
                            if (u.getId().charAt(0) == '3') {
                                type = "Trainer";
                            }
                            if (u.getId().charAt(0) == '4') {
                                type = "Member";
                            }
                            SuspendAccount s = new SuspendAccount(u.getName(), u.getId(), type, u.getSuspendReason());
                            accountNumber.add(s);
                        }
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
            }
        }
    }
}
