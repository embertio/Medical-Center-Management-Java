package apumedicalcenter;

import java.time.LocalDate;

public class Doctor extends User {

    public Doctor(String id, String ic, String name, String gender, LocalDate dob, String contact) {
        super(id, ic, name, gender, dob, contact);
    }
    
    // Overloaded constructor for when only ID + Name are needed (e.g. in history records)
    public Doctor(String id, String name) {
        super(id, "", name, "", null, "");
    }
    
    @Override
    public String getRole() {
        return "Doctor";
    }

    @Override
    public String toString() {
        return getId() + "|" + getIc() + "|" + getName() + "|" +
               getGender() + "|" + getDob() + "|" + getContact();
    }
}