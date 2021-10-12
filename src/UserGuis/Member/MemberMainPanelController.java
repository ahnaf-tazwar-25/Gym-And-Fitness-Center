package UserGuis.Member;

import Classes.Member;
import Classes.User;
import UserGuis.Admin.CreateNewAccountController;
import ampm_gym_and_fitness.ResetPassController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MemberMainPanelController implements Initializable {

    @FXML
    private Label notification;
    private Member selectedMember = null;
    @FXML
    private Label label;

    public void passData(User m) {
        selectedMember = (Member) m;
        String s = "Welcome " + selectedMember.getName() + "\n";
        s = s + "Weight: " + selectedMember.getWeight() + " kg\n" + "Height: " + selectedMember.getHeight() + " cm";
        label.setText(s);
    }

    @FXML
    private ComboBox weightCombo;
    @FXML
    private ComboBox heightCombo;
    ObservableList<String> weightList = FXCollections.observableArrayList();
    ObservableList<String> heightList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setWightnHeightValues();
        weightCombo.setItems(weightList);
        heightCombo.setItems(heightList);
        notification.setText("There is no notification. ");
    }

    private void setWightnHeightValues() {
        for (int i = 10; i <= 500; i++) {
            weightList.add(Integer.toString(i));
        }
        for (int i = 10; i <= 250; i++) {
            heightList.add(Integer.toString(i));
        }
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
    private void programeScheduleButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProgramSchedule.fxml"));
        Parent personViewParent = loader.load();
        Scene personViewScene = new Scene(personViewParent);
        ProgramScheduleController controller2 = loader.getController();
        controller2.passData(selectedMember);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();
    }

    @FXML
    private void appoinmentButtonOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("Appointment.fxml"));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void evaluationButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("evaluation.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    private void renewMembershipButtonOnClick(ActionEvent event) throws IOException {
        Parent scene2Parent = FXMLLoader.load(getClass().getResource("Renewal1.fxml"));
        Scene scene2 = new Scene(scene2Parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene2);
        window.show();
    }

    @FXML
    private void changePasswordButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ampm_gym_and_fitness/ResetPass.fxml"));
        Parent personViewParent = loader.load();
        Scene personViewScene = new Scene(personViewParent);
        ResetPassController controller1 = loader.getController();
        controller1.passData(selectedMember);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();
    }

    private ArrayList<User> getUserList() throws FileNotFoundException, IOException{
        ArrayList<User> returnType = new ArrayList<User>();
        File f = new File("user.bin");
        if (f.exists()) {
            String s = "";
            User u = null;
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            try {
                while ((u = (User) ois.readObject()) != null) {
                    returnType.add(u);
                }
            } catch (Exception e) {
                ois.close();
            }

        }
        return returnType;
    }
    
    @FXML
    private void updateWeightButtonOnClick(ActionEvent event) throws IOException {
        ArrayList<User> userList = getUserList();
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            f = new File("user.bin");
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
                for (User i: userList){
                    if (i instanceof Member) {
                        if (i.getId().equals(selectedMember.getId())) {
                            i.setWeight(Float.parseFloat(weightCombo.getSelectionModel().getSelectedItem().toString()));
                        }
                    }
                    oos.writeObject(i);
                }

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

    }

    @FXML
    private void updateHeightButtonOnClick(ActionEvent event) throws IOException {
        ArrayList<User> userList = getUserList();
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            f = new File("user.bin");
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
                for (User i: userList){
                    if (i instanceof Member) {
                        if (i.getId().equals(selectedMember.getId())) {
                            i.setHeight(Float.parseFloat(heightCombo.getSelectionModel().getSelectedItem().toString()));
                        }
                    }
                    oos.writeObject(i);
                }

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
                            
    }          

}
