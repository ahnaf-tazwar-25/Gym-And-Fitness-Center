/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserGuis.Admin;

import Classes.AppendableObjectOutputStream;
import Classes.Programs;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddNewProgramController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox programCombo;
    @FXML
    private TextField programName;
    @FXML
    private TextField sessionTF;
    @FXML
    private Label showSessionLabel;

    private ArrayList<String> sessions = new ArrayList<String>();

    ObservableList<String> programType = FXCollections.observableArrayList("GYM", "Spa", "Parlour");

    @FXML
    private void addSessionButtonOnClick(ActionEvent event) throws IOException {
        sessions.add(sessionTF.getText());
        showSessionLabel.setText(showSessionLabel.getText() + sessionTF.getText() + "\n");
        sessionTF.clear();
    }

    @FXML
    private void backButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminMainPanel.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        programCombo.setValue("GYM");
        programCombo.setItems(programType);
        showSessionLabel.setText("");
    }

    @FXML
    private void addProgramButtonOnClick(ActionEvent event) {
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            String name = programName.getText();
            String type = programCombo.getSelectionModel().getSelectedItem().toString();
            
            f = new File("Programs.bin");
            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }
            Programs p = new Programs(name, type, sessions);
            oos.writeObject(p);
            System.out.println("This is P1:" + p);

        } catch (IOException ex) {
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("The program has been successfully created!");
                    alert.showAndWait();

                }
            } catch (IOException ex) {
                System.out.println("There was an IOException: " + ex);
            }
        }
        programName.clear();
        showSessionLabel.setText("");

    }

}
