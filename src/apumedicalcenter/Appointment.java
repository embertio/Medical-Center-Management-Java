package apumedicalcenter;

public class Appointment {
    private String customerName;
    private String customerID;
    private String date;   // yyyy-MM-dd
    private String time;   // 12pm..9pm
    private String docID;
    private String docName;// e.g. D001(Dr. Lee)
    private String reason;

    //Constructor
    public Appointment(String customerName, String customerID, String date, String time, String docID, String docName, String reason) {
        this.customerName = customerName.trim();
        this.customerID = customerID.trim();
        this.date = date.trim();
        this.time = time.trim();
        this.docID = docID.trim();
        this.docName = docName.trim();
        this.reason = reason.trim();
    }

    //Getter&Setter
    public String getCustomerName() { 
        return customerName; 
    }
    public String getCustomerID() { 
        return customerID; 
    }
    public String getDate() { 
        return date;
    }
    public String getTime() { 
        return time; 
    }

    public String getDocID() {
        return docID;
    }
    
    public String getDocName() {
        return docName;
    }
    
    public String getReason() { 
        return reason;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public void setDate(String date) { 
        this.date = date.trim();
    }
    public void setTime(String time) {
        this.time = time.trim(); 
    }

    public void setReason(String reason) { 
        this.reason = reason.trim(); 
    }

    @Override
    public String toString() {
        return String.join("|", customerName, customerID, date, time, docID, docName, reason);
        
    }
}
