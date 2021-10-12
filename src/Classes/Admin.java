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
public class Admin extends User implements Serializable{
    
    @Override
    public void showInformation(String name, String id, String dob, String gender, String email, String address) 
    {
        System.out.println("Name: "+name+"ID: "+id+ "Date Of Birth: " + dob + "Gender: " + gender + "Email " + email + "Address: "+ address );
    }
    public void addinformation(String type, String gender, String email, String add, String name, String dob)
    {
        
    }

    public Admin(String name, String id, String dob, String gender, String email, String address) {
        this.name = name;
        this.id = id;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.password = id;
        this.isSuspend = false;
    }

    
    
}
