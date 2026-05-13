/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apumedicalcenter;
import java.time.LocalDate;
import Interface.EditableProfile;

public abstract class User implements EditableProfile {
    private String id;
    private String name;
    private String gender;
    private LocalDate dob;
    private String contact;
    private String ic;
    
    public User(){}
    
    public User(String id, String ic, String name, String gender, LocalDate dob, String contact){
        this.id = id;
        this.ic=ic;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.contact = contact;
    }
    
    @Override
    public abstract String getRole();

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override //implementing the methods that are declared in interface
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String getContact() {
        return contact;
    }

    @Override
    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }


    
  
}
