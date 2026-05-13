package apumedicalcenter;

import java.time.LocalDate;

public class Staff {
    private String staffID;
    private String staffName;
    private String gender;
    private LocalDate DOB;
    private String contact;
    private String password;
    
    //CONSTRUCTOR
    public Staff(){}
    public Staff(String staffID, String password, String staffName,String gender, LocalDate DOB, String contact){
        this.staffID = staffID;
        this.staffName = staffName;
        this.gender = gender;
        this.DOB = DOB;
        this.contact = contact;
        this.password = password;       
    }
    
    //GETTER SETTER
    public String getStaffID() {
        return staffID;
    }
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
    public String getStaffName() {
        return staffName;
    }
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public LocalDate getDOB() {
        return DOB;
    }
    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    //toString
    @Override
    public String toString() {
        return staffID + "|" + password + "|" + staffName + "|" + gender + "|" + DOB + "|" + contact;
    }

}
