package ampm_gym_and_fitness;

import Classes.Manager;
import Classes.Member;
import Classes.Trainer;
import Classes.User;
import UserGuis.Manager.ManagerMainPanelController;
import UserGuis.Member.MemberMainPanelController;
import UserGuis.Trainer.TrainerMainPanelController;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private PasswordField password;
    @FXML
    private Label label;
    @FXML
    private Label labe2l;

    public void passData(User u){
        
    }
    
    @FXML
    private void backOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void logInUserOnClick(ActionEvent event) throws IOException {
        String s = id.getText();
        User u = (User) idValidation();
        if (u == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Invalid Username or Password!");
            alert.showAndWait();
        } else if (u != null && !u.getIsForgotPass()) {
            try {
                if (id.getText().toString().equals("1234")) {
                    if (password.getText().equals("1111")) {
                        Parent scene2Parent = FXMLLoader.load(getClass().getResource("/UserGuis/Admin/AdminMainPanel.fxml"));
                        Scene scene2 = new Scene(scene2Parent);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(scene2);
                        window.show();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Invalid Username or Password!");
                        alert.showAndWait();
                    }
                } else if (s.charAt(0) == '2' && password.getText().equals(u.getPassword())) {
                    if (u != null) {

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/UserGuis/Manager/ManagerMainPanel.fxml"));
                        Parent personViewParent = loader.load();
                        Scene personViewScene = new Scene(personViewParent);
                        ManagerMainPanelController controller = loader.getController();
                        controller.passData((User) idValidation());
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(personViewScene);
                        window.show();

                    }

                } else if (s.charAt(0) == '4' && password.getText().equals(u.getPassword())) {

                    if (u != null) {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/UserGuis/Member/MemberMainPanel.fxml"));
                        Parent personViewParent = loader.load();
                        Scene personViewScene = new Scene(personViewParent);
                        MemberMainPanelController controller1 = loader.getController();
                        controller1.passData(u);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(personViewScene);
                        window.show();
                    }
                } else if (s.charAt(0) == '3' && password.getText().equals(u.getPassword())) {
                    if (u != null) {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/UserGuis/Trainer/TrainerMainPanel.fxml"));
                        Parent personViewParent = loader.load();
                        Scene personViewScene = new Scene(personViewParent);
                        TrainerMainPanelController controller2 = loader.getController();
                        controller2.passData(u);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(personViewScene);
                        window.show();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Username or Password!");
                    alert.showAndWait();
                }
            } catch (RuntimeException re) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Invalid Username or Password!");
                alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void forgetPassButtonOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("Forget_Passwrod.fxml"));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene2);
        window.show();
    }

    private User idValidation() {
        User u = null;
        User returnType = null;
        String ID = id.getText().toString();
        String pass = password.getText().toString();
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        f = new File("user.bin");
        if (f.exists()) {
            try {
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);

                try {

                    while (true) {
                        u = (User) ois.readObject();
                        String userID = u.getId();
                        String pass1 = u.getPassword();
                        if (u instanceof Manager) {
                            System.out.println(u);
                            if (u.getId().equals(ID) && !u.getIsSuspend()) {
                                u = (Manager) u;
                                break;

                            }
                        } else if (u instanceof Member) {
                            System.out.println(u);
                            if (u.getId().equals(ID) && !u.getIsSuspend()) {
                                u = (Member) u;
                                break;
                            }
                        } else if (u instanceof Trainer) {
                            if (u.getId().equals(ID) && !u.getIsSuspend()) {
                                u = (Trainer) u;
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("There is an exception: " + e);
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
        return u;
    }
}
