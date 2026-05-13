package apumedicalcenter;

import java.time.LocalDate;

public class Customer extends User {
    
    public Customer(){}
   
    public Customer(String id, String name, String gender, String dob, String contact, String ic) {
        super(id, ic, name, gender,
            (dob != null && !dob.trim().isEmpty() ? LocalDate.parse(dob.trim()) : null), contact);
    }

    
    public Customer(String id, String name) {
        super(id, "", name, "", null, "");
    }

    
    public String getCustomerID() { 
        return getId(); 
    }

    public String getCustomerName() { 
        return getName(); 
    }

    public String getContactNumber() { 
        return getContact(); 
    }

    @Override
    public String toString() {
        return getId() + "|" + getName() + "|" + getGender() + "|" + getDob() + "|" + getContact() + "|" + getIc();
    }
    
    @Override
    public String getRole(){
        return "Customer";
    }

}
