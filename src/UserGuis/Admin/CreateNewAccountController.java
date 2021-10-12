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
import java.io.OutputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class CreateNewAccountController implements Initializable {

    @FXML
    private ComboBox combo;

    ObservableList<String> programType = FXCollections.observableArrayList("Manager", "Trainer", "Member");
    @FXML
    private ToggleGroup Gender;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField addressTxtField;
    @FXML
    private DatePicker dobTxtField;
    @FXML
    private RadioButton maleTxtField;
    @FXML
    private RadioButton femaleTxtField;
    @FXML
    private RadioButton otherTxtField;
    @FXML
    private TextField emailTxtField;
    @FXML
    private TextField contacNoTextField;
    @FXML
    private Label idLabel;

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
        combo.setValue("");
        combo.setItems(programType);
    }

    @FXML
    private void submitButtonOnClick(ActionEvent event) {

        String type = combo.getValue().toString();
        String gender = null;
        if (maleTxtField.isSelected()) {
            gender = "Male";
        } else if (femaleTxtField.isSelected()) {
            gender = "Female";
        } else if (otherTxtField.isSelected()) {
            gender = "Others";
        }

        String email = emailTxtField.getText().toString();
        String dob = dobTxtField.getValue().toString();
        String add = addressTxtField.getText().toString();
        String name = nameTextField.getText().toString();
        String contact = contacNoTextField.getText();

        Random r = new Random();
        String id = generateID();
        String pass = generateID();
        System.out.println(id);
        User u = null;
        if (type == "Manager") {
            u = new Manager(name, id, pass, dob, gender, email, add, contact);
        } else if (type == "Trainer") {
            u = new Trainer(name, id, pass, dob, gender, email, add, contact);
        } else if (type == "Member") {
            u = new Member(name, id, pass, dob, gender, email, add, contact);
        }

        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            f = new File("user.bin");
            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }
            oos.writeObject(u);
            System.out.println("From here: " + u.getPassword());

        } catch (IOException ex) {
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                    System.out.println("Finally");
                }
            } catch (IOException ex) {
            }
        }
        idLabel.setText("Your id is " + id);
        emailTxtField.setText("");
        addressTxtField.setText("");
        nameTextField.setText("");
        dobTxtField.setValue(null);
        combo.setValue("");
        maleTxtField.setSelected(false);
        femaleTxtField.setSelected(false);
        otherTxtField.setSelected(false);
        contacNoTextField.setText("");
    }

    private String generateID() {
        int id = 1;
        String eId = "";
        String type = combo.getValue().toString();
        if (type == "Manager") {
            eId += "2";
        } else if (type == "Trainer") {
            eId += "3";
        } else if (type == "Member") {
            eId += "4";
        }
        String uniqueID = "";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();
        String s = dtf.format(now);
        String st = Character.toString(s.charAt(2)) + Character.toString(s.charAt(3));
        eId += st;

        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        f = new File("user.bin");
        if (!f.exists()) {
            uniqueID = "1";

        } else {
            try {
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                User u;
                try {
                    while (true) {

                        u = (User) ois.readObject();
                        String userID = u.getId();
                        if (userID.charAt(0) == eId.charAt(0)) {
                            id += 1;
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
        uniqueID = Integer.toString(id);
        eId += uniqueID;
        return eId;
    }


    class AppendableObjectOutputStream extends ObjectOutputStream {

        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
        }
    }
}
