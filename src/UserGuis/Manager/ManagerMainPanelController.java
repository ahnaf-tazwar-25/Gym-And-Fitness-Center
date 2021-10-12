/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserGuis.Manager;

import Classes.Manager;
import Classes.User;
import ampm_gym_and_fitness.ResetPassController;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class ManagerMainPanelController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Manager selectedManeger;
    @FXML
    private Label label;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void passData(User m){
        selectedManeger = (Manager)m;
        label.setText("Welcome "+selectedManeger.getName()+",");
    }
    @FXML
    private void logoutButtonOnClick(ActionEvent event) throws IOException {
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
    private void listOfMembersbuttonOnClick(ActionEvent event) {
        try{
       
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("listOfAllMenebers.fxml"));
            Parent personViewParent = loader.load();
            Scene personViewScene = new Scene(personViewParent);
            ListOfAllMenebersController controller2 = loader.getController();
            controller2.passData(selectedManeger);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(personViewScene);
            window.show();
        }
        catch(Exception e)
        {
            ButtonType b = new ButtonType("Ok");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("No Scene found");
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(b);
            Optional<ButtonType> option = alert.showAndWait();
            if(option.get().equals(null))
            {
                System.out.println("Print 404...!!!");
            }
        }
    }
    @FXML
    private void feedBackButtonOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("feedBack.fxml"));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void reportButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("report.fxml"));
        Parent customer = loader.load();
        Scene customerScene = new Scene(customer);
        ReportController controller = loader.getController();
        controller.passData(selectedManeger);
        Stage customerWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerWindow.setScene(customerScene);
        customerWindow.show();
    }

    @FXML
    private void enrollmentProgramsButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/UserGuis/Manager/EnrollmentToPrograms.fxml"));
        Parent personViewParent = loader.load();
        Scene personViewScene = new Scene(personViewParent);
        EnrollmentToProgramsController controller1 = loader.getController();
        controller1.passData(selectedManeger);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();
    }

    @FXML
    private void listOfMembersButtonOnClick(MouseEvent event) {
    }

    @FXML
    private void changePasswordButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ampm_gym_and_fitness/ResetPass.fxml"));
        Parent personViewParent = loader.load();
        Scene personViewScene = new Scene(personViewParent);
        ResetPassController controller1 = loader.getController();
        controller1.passData(selectedManeger);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();
    }
}
