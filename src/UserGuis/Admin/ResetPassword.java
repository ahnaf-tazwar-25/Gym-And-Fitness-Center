/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserGuis.Admin;

/**
 *
 * @author AhNAF TAzWAR
 */
public class ResetPassword {
String id, name, accType;

    public ResetPassword(String id, String name, String accType) {
        this.id = id;
        this.name = name;
        this.accType = accType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAccType() {
        return accType;
    }


}
