/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserGuis.Manager;

import Classes.Member;
import Classes.Programs;
import Classes.Trainer;
import Classes.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhNAF TAzWAR
 */
public class EnrollmentToProgramsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    User u = null;

    public void passData(User u) {
        this.u = u;
    }

    @FXML
    private ComboBox memberIDsCombo;
    @FXML
    private ComboBox trainerIDsCombo;

    //Member TabPane
    @FXML
    private TableView programTable_Member;
    @FXML
    private TableColumn<ProgramT, String> programName_Member;
    @FXML
    private TableColumn<ProgramT, String> programType_Member;
    @FXML
    private TableView trainerTable_Member;
    @FXML
    private TableColumn<TrainerT, String> trainerID_Member;
    @FXML
    private TableColumn<TrainerT, String> trainerName_Member;
    @FXML
    private TableView enrolledMemberTable_Member;
    @FXML
    private TableColumn<EnrolledMemberT, String> memberID_Member;
    @FXML
    private TableColumn<EnrolledMemberT, String> memberName_Member;
    @FXML
    private TableColumn<EnrolledMemberT, String> corrpondingToTrainerName_Member;

    //Trainer TabPane
    @FXML
    private TableView programTable_Trainer;
    @FXML
    private TableColumn<ProgramT, String> programName_Trainer;
    @FXML
    private TableColumn<ProgramT, String> programType_Trainer;
    @FXML
    private TableView trainerTable_Trainer;
    @FXML
    private TableColumn<TrainerT, String> trainerID_Trainer;
    @FXML
    private TableColumn<TrainerT, String> trainerName_Trainer;

    @FXML
    private Label trainerInfoLabel;

    ObservableList<String> memberList = FXCollections.observableArrayList();
    ObservableList<String> trainerList = FXCollections.observableArrayList();

    private boolean isTrainer = false;

    @FXML
    private void loadButton_MemberOnClick(ActionEvent event) {
        try {
            trainerTable_Member.setItems(loadEnrolledTrainers(getEnrolledTrainers()));
            enrolledMemberTable_Member.setItems(loadEnrolledMembers(getEnrolledMembers()));
        } catch (RuntimeException re) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select all the necessary selections!");
            alert.showAndWait();
        }
    }

    @FXML
    private void loadButton_TrainerOnClick(ActionEvent event) {
        isTrainer = true;
        try {
            trainerTable_Trainer.setItems(loadEnrolledTrainers(getEnrolledTrainers()));
        } catch (RuntimeException re) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select all the necessary selections!");
            alert.showAndWait();
        }

    }

    @FXML
    private void loadTrainerInfoButtonOnClick(ActionEvent event) {
        User t = null;
        try {
            t = (User) findUser(trainerIDsCombo.getSelectionModel().getSelectedItem().toString());
            String s = "Name: " + t.getName() + "\n" + "ID: " + t.getId() + "\n" + "Gender: " + t.getGender()
                    + "\n" + "Email: " + t.getEmail();
            trainerInfoLabel.setText(s);
        } catch (RuntimeException re) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select all the necessary selections!");
            alert.showAndWait();
        }

    }

    @FXML
    private void enrolMemberButtonOnClick(ActionEvent event) {
        User m = null;
        User t = null;
        try {
            m = (User) findUser(memberIDsCombo.getSelectionModel().getSelectedItem().toString());
            TrainerT t1 = (TrainerT) trainerTable_Member.getSelectionModel().getSelectedItem();

            t = (User) findUser(t1.id);
            addMemberToSelectedProgram(getProgramList(), m.getId(), m.getName(), t.getId());

            enrolledMemberTable_Member.setItems(loadEnrolledMembers(getEnrolledMembers()));
        } catch (RuntimeException re) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select all the necessary selections!");
            alert.showAndWait();
        }

    }

    @FXML
    private void enrolTrainerButtonOnClick(ActionEvent event) {
        User t = null;
        try {
            t = (User) findUser(trainerIDsCombo.getSelectionModel().getSelectedItem().toString());
            addTrainerToSelectedProgram(getProgramList(), t.getId(), t.getName());
            isTrainer = true;
            trainerTable_Trainer.setItems(loadEnrolledTrainers(getEnrolledTrainers()));
        } catch (RuntimeException re) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select a trainer's ID!");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        memberID_Member.setCellValueFactory(new PropertyValueFactory<EnrolledMemberT, String>("id"));
        memberName_Member.setCellValueFactory(new PropertyValueFactory<EnrolledMemberT, String>("name"));
        corrpondingToTrainerName_Member.setCellValueFactory(new PropertyValueFactory<EnrolledMemberT, String>("trainerID"));

        trainerID_Trainer.setCellValueFactory(new PropertyValueFactory<TrainerT, String>("id"));
        trainerName_Trainer.setCellValueFactory(new PropertyValueFactory<TrainerT, String>("name"));

        trainerID_Member.setCellValueFactory(new PropertyValueFactory<TrainerT, String>("id"));;
        trainerName_Member.setCellValueFactory(new PropertyValueFactory<TrainerT, String>("name"));

        programName_Member.setCellValueFactory(new PropertyValueFactory<ProgramT, String>("name"));
        programType_Member.setCellValueFactory(new PropertyValueFactory<ProgramT, String>("type"));
        programTable_Member.setItems(loadPrograms());

        programName_Trainer.setCellValueFactory(new PropertyValueFactory<ProgramT, String>("name"));
        programType_Trainer.setCellValueFactory(new PropertyValueFactory<ProgramT, String>("type"));
        programTable_Trainer.setItems(loadPrograms());
        getUserList();

        memberIDsCombo.setItems(memberList);
        trainerIDsCombo.setItems(trainerList);
    }

    @FXML
    private void backButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/UserGuis/Manager/ManagerMainPanel.fxml"));
        Parent personViewParent = loader.load();
        Scene personViewScene = new Scene(personViewParent);
        ManagerMainPanelController controller1 = loader.getController();
        controller1.passData(u);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(personViewScene);
        window.show();
    }

    private ObservableList<ProgramT> loadPrograms() {
        ObservableList<ProgramT> returnType = FXCollections.observableArrayList();
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
                        returnType.add(new ProgramT(p.getName(), p.getType()));
                    }
                } catch (Exception e) {
                    System.out.println("There is an exception on 'loadPrograms' method: " + e);
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

    private void getUserList() {
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
                        if (u instanceof Member) {
                            memberList.add(u.getId());
                        } else if (u instanceof Trainer) {
                            trainerList.add(u.getId());
                        }
                    }
                } catch (Exception e) {
                    System.out.println("There is an exception on 'getUserList' method: " + e);
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

    private User findUser(String id) {
        File f = null;
        User u = null;
        User returnU = null;
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
                        if (u.getId().equals(id)) {
                            returnU = u;
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("There is an exception on 'findUser' method: " + e);
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
        return returnU;
    }

    private ArrayList<String> getEnrolledMembers() {
        ArrayList<String> returnType = new ArrayList<String>();
        File f = null;
        Programs p = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        f = new File("Programs.bin");
        if (f.exists()) {
            ProgramT selectedProgram = null;
            if (isTrainer) {
                selectedProgram = (ProgramT) programTable_Trainer.getSelectionModel().getSelectedItem();
            } else if (!isTrainer) {
                selectedProgram = (ProgramT) programTable_Member.getSelectionModel().getSelectedItem();
            }
            String selectedProgramName = selectedProgram.getName();
            try {
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                try {
                    while (true) {
                        p = (Programs) ois.readObject();
                        if (p.getName().equals(selectedProgramName)) {
                            returnType = p.getEnrolledMembers();
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("There is an exception on 'getEnrolledMembers' method: " + e);
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
        isTrainer = false;
        return returnType;
    }

    private ArrayList<String> getEnrolledTrainers() {
        ArrayList<String> returnType = new ArrayList<String>();
        File f = null;
        Programs p = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        f = new File("Programs.bin");
        if (f.exists()) {
            ProgramT selectedProgram = null;
            if (isTrainer) {
                selectedProgram = (ProgramT) programTable_Trainer.getSelectionModel().getSelectedItem();
            } else if (!isTrainer) {
                selectedProgram = (ProgramT) programTable_Member.getSelectionModel().getSelectedItem();
            }
            String selectedProgramName = selectedProgram.getName();
            try {
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                try {
                    while (true) {
                        p = (Programs) ois.readObject();
                        if (p.getName().equals(selectedProgramName)) {
                            returnType = p.getEnrolledTrainers();
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("There is an exception on 'getEnrolledTrainers' method: " + e);
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
        isTrainer = false;
        return returnType;
    }

    private ObservableList<EnrolledMemberT> loadEnrolledMembers(ArrayList<String> memberList) {
        ObservableList<EnrolledMemberT> returnType = FXCollections.observableArrayList();
        String splitter[] = new String[3];
        User t = null;
        
        for (String i : memberList) {
            splitter = i.split(",", 3);
            System.out.println("This is List: " + splitter[2]);
            t = (User)findUser(splitter[2]);
            returnType.add(new EnrolledMemberT(splitter[0], splitter[1], t.getName()));
        }

        return returnType;
    }

    private ObservableList<TrainerT> loadEnrolledTrainers(ArrayList<String> trainerList) {
        ObservableList<TrainerT> returnType = FXCollections.observableArrayList();
        String splitter[] = new String[2];
        for (String i : trainerList) {
            splitter = i.split(",", 2);
            returnType.add(new TrainerT(splitter[0], splitter[1]));
        }

        return returnType;
    }

    private ArrayList<Programs> getProgramList() {
        ArrayList<Programs> returnType = new ArrayList<Programs>();
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
                        returnType.add(p);
                    }
                } catch (Exception e) {
                    System.out.println("There is an exception 'getProgramList' method: " + e);
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

    private void addTrainerToSelectedProgram(ArrayList<Programs> progList, String id, String name) {
        File f = null;
        Programs p = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        ProgramT selectedProgram = (ProgramT) programTable_Trainer.getSelectionModel().getSelectedItem();
        String selectedProgramName = selectedProgram.getName();
        String trainerText = id + "," + name;
        f = new File("Programs.bin");
        try {
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            for (Programs i : progList) {
                if (i.getName().equals(selectedProgramName) && !i.findTrainer(id)) {
                    i.addTrainers(trainerText);
                }
                oos.writeObject(i);
            }
            oos.flush();
            oos.close();

        } catch (Exception e) {
            System.out.println("There is an exception on 'addTrainerToSelectedProgram' method: " + e);
        }

    }

    private void addMemberToSelectedProgram(ArrayList<Programs> progList, String id, String name, String trainerID) {
        File f = null;
        Programs p = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        ProgramT selectedProgram = (ProgramT) programTable_Member.getSelectionModel().getSelectedItem();
        String selectedProgramName = selectedProgram.getName();
        String memberText = id + "," + name + "," + trainerID;
        f = new File("Programs.bin");
        try {
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            for (Programs i : progList) {
                if (i.getName().equals(selectedProgramName) && !i.findMember(id)) {
                    i.addMembers(memberText);
                }
                oos.writeObject(i);
            }
            oos.flush();
            oos.close();

        } catch (Exception e) {
            System.out.println("There is an exception 'addMemberToSelectedProgram' method: " + e);
        }

    }
}
