/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apumedicalcenter;

public class DoctorHistoryRecord {
    private History history;
    private FeedbackDoctor customerFeedback;
    
    public DoctorHistoryRecord(History history, FeedbackDoctor customerFeedback){
        this.history = history;
        this.customerFeedback = customerFeedback;
    }
    
    public History getHistory() { 
        return history;
    }

    public FeedbackDoctor getCustomerFeedback() {
        return customerFeedback;
    }
    
    // Now you fetch values through History → Customer / Doctor
    public String getCusID() {
        return history.getCustomer().getId();
    }

    public String getCusName() {
        return history.getCustomer().getName();
    }

    public String getDocID() {
        return history.getDoctor().getId();
    }

    public String getDocName() {
        return history.getDoctor().getName();
    }

    public String getDate() { 
        return history.getDate(); 
    }
    
    public String getTime() {
        return history.getTime(); 
    }
    
    public String getFeedback() { 
        return history.getFeedback(); 
    }
    
    public String getCharges() { 
        return history.getCharges(); 
    }

    @Override
    public String toString() {
        return history.toString() + " | " +
                (customerFeedback != null ? customerFeedback.getComment() : "-");
    }
}
