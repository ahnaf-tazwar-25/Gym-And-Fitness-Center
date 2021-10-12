package UserGuis.Manager;

import Classes.Programs;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ReportController implements Initializable {

    @FXML
    private Label report;

    User u = null;

    public void passData(User u) {
        this.u = u;
    }

    @FXML
    private PieChart pieChart;
    @FXML
    private ComboBox programCombo;

    ObservableList<String> programList = FXCollections.observableArrayList();
    private int memberCounter = 0;
    private int trainerCounter = 0;

    @FXML
    private void generateButtonOnClick(ActionEvent event) throws IOException {

        programPeopleCounter(programCombo.getSelectionModel().getSelectedItem().toString());
        loadPieChart(memberCounter, trainerCounter);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //loadPieChart();
        setProgramOnComboBox();
        programCombo.setItems(programList);
    }

    @FXML
    private void backButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ManagerMainPanel.fxml"));
        Parent personViewParent = loader.load();
        Scene personViewScene = new Scene(personViewParent);
        ManagerMainPanelController controller2 = loader.getController();
        controller2.passData(u);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();
    }

    private void loadPieChart(int memberCounter, int trainerCounter) {
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList(
                new PieChart.Data("Trainers", trainerCounter),
                new PieChart.Data("Members", memberCounter)
        );
        pieChart.setData(list);

        for (PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    //statusLabel.setText(String.valueOf(data.getPieValue()));
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            }
            );
        }
    }

    private void setProgramOnComboBox() {
        File f = null;
        Programs p = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        f = new File("Programs.bin");
        if (f.exists()) {
            try {
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                try {
                    while (true) {
                        p = (Programs) ois.readObject();
                        programList.add(p.getName());
                    }
                } catch (Exception e) {
                    System.out.println("There is an exception on 'setProgramOnComboBox' method: " + e);
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
    }
    private void programPeopleCounter(String programName){
        File f = null;
        Programs p = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ArrayList<String> trainerList = new ArrayList<String>();
        ArrayList<String> memberList = new ArrayList<String>();
        f = new File("Programs.bin");
        if (f.exists()) {
            try {
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                try {
                    while (true) {
                        p = (Programs) ois.readObject();
                        if (p.getName().equals(programName)){
                            trainerList = p.getEnrolledTrainers();
                            memberList = p.getEnrolledMembers();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("There is an exception on 'setProgramOnComboBox' method: " + e);
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
        for (String i: memberList){
            memberCounter++;
        }
        for (String i: trainerList){
            trainerCounter++;
        }
    }

}
