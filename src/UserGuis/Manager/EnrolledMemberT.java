/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserGuis.Manager;

/**
 *
 * @author AhNAF TAzWAR
 */
public class EnrolledMemberT {
    String id, name, trainerID;

    public EnrolledMemberT(String id, String name, String trainerID) {
        this.id = id;
        this.name = name;
        this.trainerID = trainerID;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTrainerID() {
        return trainerID;
    }
    
    
}
