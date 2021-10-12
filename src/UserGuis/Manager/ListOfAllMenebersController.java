
package UserGuis.Manager;

import Classes.Manager;
import Classes.Member;
import Classes.Trainer;
import Classes.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class ListOfAllMenebersController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private Manager selectedManeger;
    public void passData(Manager m){
        selectedManeger = m;
        
    }
    @FXML
    private TableView table;
    @FXML
    private TableColumn<AccountList, String> name;
    @FXML
    private TableColumn<AccountList, String> id;
    @FXML
    private TableColumn<AccountList, String> dob;
    @FXML
    private TableColumn<AccountList, String> contactNo;
    @FXML
    private TableColumn<AccountList, String> email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id.setCellValueFactory(new PropertyValueFactory<AccountList, String>("id"));
        name.setCellValueFactory(new PropertyValueFactory<AccountList, String>("name"));
        dob.setCellValueFactory(new PropertyValueFactory<AccountList, String>("dob"));
        contactNo.setCellValueFactory(new PropertyValueFactory<AccountList, String>("contactNo"));
        email.setCellValueFactory(new PropertyValueFactory<AccountList, String>("email"));
    }

    @FXML
    private void backButtonOnClick(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ManagerMainPanel.fxml"));
        Parent personViewParent = loader.load();
        Scene personViewScene = new Scene(personViewParent);
        ManagerMainPanelController controller2 = loader.getController();
        controller2.passData(selectedManeger);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();
    }

    @FXML
    private void trainerButtonOnClick(ActionEvent event) {
        System.out.println("Done...1");
        table.setItems(getUsers(getAllSpecificUsers(1)));
    }

    @FXML
    private void managerButtonOnClick(ActionEvent event) {
        System.out.println("Done ...2");
        table.setItems(getUsers(getAllSpecificUsers(2)));
    }

    @FXML
    private void memberButtonOnClick(ActionEvent event) {
        System.out.println("Done ...3");
        table.setItems(getUsers(getAllSpecificUsers(3)));
    }

    private ArrayList<User> getAllSpecificUsers(int i) {
        ArrayList<User> returnType = new ArrayList<User>();
        File f = null;
        User u = null;
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
                        if (i == 1 && u instanceof Trainer) {
                            returnType.add(u);
                        } else if (i == 2 && u instanceof Manager) {
                            returnType.add(u);
                        } else if (i == 3 && u instanceof Member) {
                            returnType.add(u);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("There is an exception on 'getAllSpecificUsers' method: " + e);
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
        return returnType;
    }

    private ObservableList<AccountList> getUsers(ArrayList<User> list) {
        ObservableList<AccountList> returnType = FXCollections.observableArrayList();
        for (User u : list) {
            returnType.add(new AccountList(u.getName(), u.getId(), u.getDob(), u.getContactNo(), u.getEmail()));
        }
        return returnType;
    }
}
