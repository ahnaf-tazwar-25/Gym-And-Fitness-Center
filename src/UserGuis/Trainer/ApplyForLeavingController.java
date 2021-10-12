package UserGuis.Trainer;

import Classes.Trainer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ApplyForLeavingController implements Initializable {

    @FXML
    private TextArea application;

    private Trainer selectedTrainer = null;
    @FXML
    private Label Label;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void passinfo(Trainer t) {
        selectedTrainer = t;
    }

    @FXML
    private void backButtonOnClick(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/UserGuis/Trainer/TrainerMainPanel.fxml"));
        Parent personViewParent = loader.load();
        Scene personViewScene = new Scene(personViewParent);
        TrainerMainPanelController controller2 = loader.getController();
        controller2.passinfo(selectedTrainer);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();
    }

    @FXML
    private void sendButtonOnClick(ActionEvent event) {
        if (!application.getText().isEmpty()) {
            String applicationBox = application.getText().toString();

            File f = null;
            FileWriter fw = null;
            try {
                f = new File("applicationForLeave(Trainer).txt");
                if (f.exists()) {
                    fw = new FileWriter(f, true);
                } else {
                    fw = new FileWriter(f);
                }

                fw.write(selectedTrainer.getId() + " || " + applicationBox + "\n");
            } catch (IOException ex) {
            } finally {
                try {
                    if (fw != null) {
                        fw.close();
                    }
                } catch (IOException ex) {
                }
                application.setText("");
                Label.setText("Successfully Done. ");
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("There is no Application ...!!");
            alert.showAndWait();
        }
    }

}
