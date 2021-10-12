/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author AhNAF TAzWAR
 */
public class Programs implements Serializable{

    private String name, type;
    private ArrayList<String> enrolledTrainers;
    private ArrayList<String> enrolledMembers;
    private ArrayList<String> sessions = new ArrayList<String>();

    public Programs(String name, String type, ArrayList<String> sessions) {
        this.name = name;
        this.type = type;
        enrolledTrainers = new ArrayList<String>();
        enrolledMembers = new ArrayList<String>();
        this.sessions = sessions;
    }

    public void addTrainers(String id) {
        enrolledTrainers.add(id);
    }

    public void addMembers(String id) {
        enrolledMembers.add(id);
    }

    public void addTimings(String time) {
        sessions.add(time);
    }
    
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public ArrayList<String> getEnrolledTrainers() {
        return enrolledTrainers;
    }

    public boolean findTrainer(String id) {
        String[] splitter = new String[2];
        for (String t : enrolledTrainers) {
            splitter = t.split(",", 2);
            if (splitter[0].equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean findMember(String id) {
        String[] splitter = new String[3];
        for (String t : enrolledTrainers) {
            splitter = t.split(",", 3);
            if (splitter[0].equals(id)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getEnrolledMembers() {
        return enrolledMembers;
    }

    public ArrayList<String> getTimings() {
        return sessions;
    }

}
