package ampm_gym_and_fitness;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class ProgrameController implements Initializable {

    @FXML
    private void backButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
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
    private void salunButtonOnClick(Event event) {
        System.out.println("Salun");
    }

    @FXML
    private void spaButtonOnClick(Event event) {
        System.out.println("Spa");
    }

    @FXML
    private void gymButtonOnClick(Event event) {
        System.out.println("Gym");
    }

    @FXML
    private void parlourButtonOnClick(Event event) {
        System.out.println("Parlour");
    }
    
}
