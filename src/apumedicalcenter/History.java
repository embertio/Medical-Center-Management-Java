/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apumedicalcenter;

/**
 *
 * @author tatsu
 */
public class History {
    private Customer customer;  // Aggregation
    private Doctor doctor;      // Aggregation
    private String date;
    private String time;
    private String feedback;
    private String charges;

    public History(Customer customer, Doctor doctor, String date, String time, String feedback, String charges) {
        this.customer = customer;
        this.doctor = doctor;
        this.date = date.trim();
        this.time = time.trim();
        this.feedback = feedback.trim();
        this.charges = charges;
    }

    public Customer getCustomer() { return customer; }
    public Doctor getDoctor() { return doctor; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getFeedback() { return feedback; }
    public String getCharges() { return charges; }
    
    @Override
    public String toString() {
        return customer.getId() + "|" + customer.getName() + "|" +
           date + "|" + time + "|" +
           doctor.getId() + "|" + doctor.getName() + "|" +
           feedback + "|" + charges;
    }
}

