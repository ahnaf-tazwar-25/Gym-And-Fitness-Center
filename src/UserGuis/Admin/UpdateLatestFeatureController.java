package UserGuis.Admin;

import java.io.File;
import java.io.FileWriter;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class UpdateLatestFeatureController implements Initializable {

    @FXML
    private TextArea features;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void backButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminMainPanel.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
    ButtonType Yes = new ButtonType("Yes");
    ButtonType No = new ButtonType("No");
    Optional<ButtonType> option;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Do you want to Append ");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(Yes, No);
        option = alert.showAndWait();

    }

    @FXML
    private void submitButtonOnClick(ActionEvent event) throws IOException {
       
       if (option.get().equals(Yes)) 
        {
            if (features.getText().equalsIgnoreCase("")) 
            {
                ButtonType b = new ButtonType("Ok");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please check your text box.");
                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(b);
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(null)) {
                    System.out.println("Print 404...!!!");
                }
            } 
            else  
            {
                System.out.println("Working...");
                String str = features.getText().toString();
                features.setText("");
                ButtonType b = new ButtonType("Ok");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Successfully Done");
                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(b);
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(null)) 
                {
                    System.out.println("Print 404...!!!");
                }
                File f = null;
                FileWriter fw = null;
                try 
                {
                    f = new File("features.txt");
                    if (f.exists()) {
                        fw = new FileWriter(f, true);
                    } else {
                        fw = new FileWriter(f);
                    }
                    fw.write(str);
                    fw.close();
                } catch (Exception e) {
                }
            }
        }
        else if (option.get().equals(No)) 
        {
            if (features.getText().equalsIgnoreCase("")) 
            {
                ButtonType b = new ButtonType("Ok");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please check your text box.");
                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(b);
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(null)) {
                    System.out.println("Print 404...!!!");
                }
            } 
            else 
            {
                System.out.println("Working...");
                String str = features.getText().toString();
                features.setText("");
                ButtonType b = new ButtonType("Ok");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Successfully Done");
                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(b);
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(null)) 
                {
                    System.out.println("Print 404...!!!");
                }
                File f = null;
                FileWriter fw = null;
                try 
                {
                    f = new File("features.txt");
                    fw = new FileWriter(f);
                    fw.write(str);
                    fw.close();
                } catch (Exception e) {
                }
            }
        }

    }
}
