package ampm_gym_and_fitness;

import Classes.AppendableObjectOutputStream;
import Classes.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Forget_PasswrodController implements Initializable {

    @FXML
    private TextField id;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private User accountFinder(String id) {
        File f = new File("user.bin");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        User returnUser = null;
        try {
            if (f.exists()) {
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                User u = null;
                while ((u = (User) ois.readObject()) != null) {
                    if (u.getId().equals(id)) {
                        returnUser = u;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("");
        }
        try {
            ois.close();
        } catch (IOException ex) {
            System.out.println("asd");
        }

        return returnUser;
    }

    @FXML
    private void forgetPasswrodButtonOnClick(ActionEvent event) throws IOException {
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        if (accountFinder(id.getText()) != null && !accountFinder(id.getText()).getIsForgotPass()) {
            try {
                f = new File("forgotPasswordAccounts.bin");
                if (f.exists()) {
                    fos = new FileOutputStream(f, true);
                    oos = new AppendableObjectOutputStream(fos);
                } else {
                    fos = new FileOutputStream(f);
                    oos = new ObjectOutputStream(fos);
                }
                oos.writeObject(accountFinder(id.getText()));

            } catch (IOException ex) {
            } finally {
                try {
                    if (oos != null) {
                        oos.flush();
                        oos.close();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Reset request has been sent.\nAn admin will contanct with you soon.!");
                        alert.showAndWait();
                    }
                } catch (IOException ex) {
                }
            }

        } else if (accountFinder(id.getText()) != null && accountFinder(id.getText()).getIsForgotPass()) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ResetPass.fxml"));
            Parent personViewParent = loader.load();
            Scene personViewScene = new Scene(personViewParent);
            ResetPassController controller2 = loader.getController();
            controller2.passDataForReset(id.getText());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(personViewScene);
            window.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Invalid user ID!");
            alert.showAndWait();
        }
    }

    @FXML
    private void backButtonOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene2);
        window.show();
    }

}
