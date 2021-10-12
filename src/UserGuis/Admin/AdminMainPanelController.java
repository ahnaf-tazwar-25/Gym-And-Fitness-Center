/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserGuis.Admin;

import ampm_gym_and_fitness.FXMLDocumentController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class AdminMainPanelController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private void logOutOnClick(ActionEvent event) throws IOException {
        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Do you want to Sign Out?");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(yes, no);
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
            System.out.println("ERROR 404!");
        } else if (option.get() == yes) {
            Parent root = FXMLLoader.load(getClass().getResource("/ampm_gym_and_fitness/LogIn.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.show();
        }
    }

    @FXML
    private void createNewAccountOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CreateNewAccount.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void deleteAccountOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DeleteAccount.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void generateGraphOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GenerateGraph.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void suspendAccountButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SuspendAccount.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void updateLatestFeaturesButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("updateLatestFeature.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void addNewProgramButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddNewProgram.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void resetPasswordButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ResetPassword.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void vewApplicantButtonOnClick(ActionEvent event) {
        File f = null;
        Scanner sc;
        String str;
        String[] tokens;
        try {
            f = new File("biodata.txt");
            sc = new Scanner(f);
            if (f.exists()) {
                while (sc.hasNextLine()) {
                    str = sc.nextLine();
                    tokens = str.split("    ");
                    System.out.println("Name: " + tokens[0] + " Address: "  + tokens[1] + " Contact Number : " + tokens[2] +
                            " Email Address : " + tokens[3] + " Education :" + tokens[4]+ " Experiance: " + tokens[5] + " Date Of Birth: " + tokens[6] + "\n");
                }
            } else {
                System.out.println("There is no Application. ");
            }
        } catch (IOException ex) {
        }
    }

    @FXML
    private void appendAccountButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("appendAccount.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void viewListOfAccountButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("viewListOfAccount.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void changePasswordButtonOnClick(MouseEvent event) {
    }


}
