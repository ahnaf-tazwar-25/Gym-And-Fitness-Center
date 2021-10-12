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
public class AccountList {
    String name, id, dob, contactNo, email;

    public AccountList(String name, String id, String dob, String contactNo, String email) {
        this.name = name;
        this.id = id;
        this.dob = dob;
        this.contactNo = contactNo;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDob() {
        return dob;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getEmail() {
        return email;
    }
    
}
