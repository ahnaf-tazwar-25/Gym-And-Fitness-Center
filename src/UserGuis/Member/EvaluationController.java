package UserGuis.Member;

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
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;


public class EvaluationController implements Initializable {

    @FXML
    private ToggleGroup E1;
    @FXML
    private ToggleGroup E2;
    @FXML
    private ToggleGroup E3;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void backButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MemberMainPanel.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void submitButtonOnClick(ActionEvent event) {
        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Do you want to save the record? ");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(yes,no);
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get().equals(null))
        {
            System.out.println("Print 404...!!!");
        }
        else if (option.get() == yes)
        {
            System.out.println("Successfully Done..");
        }     
    }
}
