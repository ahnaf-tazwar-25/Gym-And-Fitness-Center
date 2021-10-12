/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;

/**
 *
 * @author Acer
 */
public class Member extends User implements Serializable{

    @Override
    public void showInformation(String name, String id, String dob, String gender, String email, String address) {
        
    }
    public Member(String name, String id, String pass, String dob, String gender, String email, String address, String contactNo) {
        System.out.println(id);
        this.name = name;
        this.id = id;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.password = pass;
        this.contactNo = contactNo;
        this.isSuspend = false;
        this.forgotPass = false;
    }
}
