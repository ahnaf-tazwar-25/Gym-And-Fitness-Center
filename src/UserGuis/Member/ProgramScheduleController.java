/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserGuis.Member;

import Classes.Member;
import Classes.User;
import UserGuis.Manager.AccountList;
import java.io.IOException;
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

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class ProgramScheduleController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Member selectedMember = null;
    public void passData(User m) {
        selectedMember = (Member) m;
    }
    
    @FXML
    private TableView programScheduleTable;
    @FXML
    private TableColumn <ProgramTableShow, String> programName;
    @FXML
    private TableColumn <ProgramTableShow, String> programSchedule;
    
    @FXML
    private void backButtonOnClick(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MemberMainPanel.fxml"));
            Parent personViewParent = loader.load();
            Scene personViewScene = new Scene(personViewParent);
            MemberMainPanelController controller2 = loader.getController();
            controller2.passData(selectedMember);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(personViewScene);
            window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        programName.setCellValueFactory(new PropertyValueFactory<ProgramTableShow, String>("name"));
        programSchedule.setCellValueFactory(new PropertyValueFactory<ProgramTableShow, String>("schedule"));
    }  
    
    //private 
    
     private ObservableList<AccountList> getSchedule(ArrayList<User> list) {
        ObservableList<AccountList> returnType = FXCollections.observableArrayList();
        for (User u : list) {
            returnType.add(new AccountList(u.getName(), u.getId(), u.getDob(), u.getContactNo(), u.getEmail()));
        }
        return returnType;
    }
    
}
