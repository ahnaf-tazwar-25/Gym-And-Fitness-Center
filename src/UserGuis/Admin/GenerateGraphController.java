/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserGuis.Admin;

import Classes.Manager;
import Classes.Member;
import Classes.Trainer;
import Classes.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class GenerateGraphController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private PieChart pieChart;
    @FXML
    private Button loadPieChartButton;
    @FXML
    private Label statusLabel;

    private int managerCounter = 0;
    private int trainerCounter = 0;
    private int memberCounter = 0;

    @FXML
    private void backOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminMainPanel.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    /*
@FXML
    private void loadGraphButtonOnClick(ActionEvent event) throws IOException {
        loadPieChartButtonOnClick();
        ObservableList <PieChart.Data> list = FXCollections.observableArrayList(
            new PieChart.Data("Managers",managerCounter),
            new PieChart.Data("Trainers",trainerCounter),
            new PieChart.Data("Members",memberCounter)
        );
        pieChart.setData(list);
        
        for(PieChart.Data data: pieChart.getData()){
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    statusLabel.setText(String.valueOf(data.getPieValue()));
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            }
            );
        }
    }*/

    private void loadPieChartButtonOnClick() {
        File f = new File("user.bin");
        if (f.exists()) {
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                User u = null;
                while ((u = (User) ois.readObject()) != null) {
                    if (u instanceof Manager) {
                        managerCounter++;
                    } else if (u instanceof Trainer) {
                        trainerCounter++;
                    } else if (u instanceof Member) {
                        memberCounter++;
                    }
                }
                System.out.println("In Here!");

                System.out.println("In Here!");
            } catch (Exception e) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    System.out.println("There was as IOException: " + ex);
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadPieChartButtonOnClick();
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList(
                new PieChart.Data("Managers", managerCounter),
                new PieChart.Data("Trainers", trainerCounter),
                new PieChart.Data("Members", memberCounter)
        );
        pieChart.setData(list);

        for (PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    String s = "";
                    if (data.getName().equals("Managers")) {
                        s += "Total Managers: ";
                    } else if (data.getName().equals("Trainers")) {
                        s += "Total Trainers: ";
                    } else if (data.getName().equals("Members")) {
                        s += "Total Members: ";
                    }
                    statusLabel.setText(s + String.valueOf((int) data.getPieValue()));
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            }
            );
        }

    }

}
