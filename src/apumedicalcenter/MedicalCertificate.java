/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apumedicalcenter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MedicalCertificate {
    private String patientName;
    private String diagnosis;
    private int restDuration;
    private String clinic;
    private Doctor doctor; 
    private String issuedDate;
    
    public MedicalCertificate(String patientName, String diagnosis, int restDuration, String clinic, Doctor doctor) {
        this.patientName = patientName;
        this.diagnosis = diagnosis;
        this.restDuration = restDuration;
        this.clinic = clinic;
        this.doctor = doctor;
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        this.issuedDate = sdf.format(new Date());
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public int getRestDuration() {
        return restDuration;
    }

    public String getClinic() {
        return clinic;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getIssuedDate() {
        return issuedDate;
    }
    
    @Override
    public String toString() {
        return "*************************************\n" +
               "               Medical Certificate\n" +
               "*************************************\n\n" +
               "Patient Name: " + patientName + "\n" +
               "Diagnosis: " + diagnosis + "\n" +
               "Rest Duration: " + restDuration + " day(s)\n" +
               "Clinic: " + clinic + "\n" +
               "Issued By: " + doctor.getName() + "\n" +
               "Issued Date: " + issuedDate + "\n\n" +
               "*************************************";
    }
      
}
