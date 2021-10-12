
package Classes;

import java.io.Serializable;
import static jdk.nashorn.internal.runtime.Debug.id;


public abstract class User implements Serializable{
    protected String name;
    protected String id;
    protected String dob;
    protected String gender;
    protected String email;
    protected String address;
    protected String password;
    protected boolean isSuspend;
    protected String suspendReason;
    protected boolean forgotPass;
    protected float weight;
    protected float height;
    protected String contactNo;

    public void setForgotPass(boolean forgorPass) {
        this.forgotPass = forgorPass;
    }
    
    public void setSuspendReason(String suspendReason) {
        this.suspendReason = suspendReason;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setContactNo(String ContactNo) {
        this.contactNo = ContactNo;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public String getContactNo() {
        return contactNo;
    }

    public boolean getIsForgotPass() {
        return forgotPass;
    }
    
    public String getSuspendReason() {
        return suspendReason;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getIsSuspend() {
        return isSuspend;
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
    public abstract void showInformation(String name,String id,String dob,String gender, String email,String address);
    
    public void showInfo()
    {
        System.out.println("Name: "+name+" ID: "+id+ " Date Of Birth: " + dob + " Gender: " + gender + " Email " + email + " Address: "+ address );
    }

    public void setIsSuspend(Boolean isSuspend) {
        this.isSuspend = isSuspend;
    }

    
    
    
}
