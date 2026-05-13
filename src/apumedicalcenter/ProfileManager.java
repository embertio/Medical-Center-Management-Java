/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apumedicalcenter;
import Interface.EditableProfile;
import java.io.*;
import java.util.function.Function;

public class ProfileManager {
    public static boolean updateProfile(EditableProfile profile, String newName, String newContact) throws IOException{
        String type = profile.getRole();
        
        if(type.equals("Customer")){
            udpateFile("Customers.txt", line ->{
                String[] part = line.split("\\|");
                
                if(part.length == 6 && part[0].trim().equals(profile.getId())){
                    part[1] = newName;
                    part[4] = newContact;
                    
                    profile.setName(newName);
                    profile.setContact(newContact);
                }
                return String.join("|", part);
            });
            
            updateAppointmentsForCustomer(profile);
            updateCustomerFeedback(profile);
            updateHistoryForCustomer(profile);
            
        }else if(type.equals("Doctor")){
            
            udpateFile("Doctor.txt", line ->{
                String[] part = line.split("\\|");
                
                if (part.length ==6 && part[0].trim().equals(profile.getId())){
                    part[2] = newName;
                    part[5] = newContact;
                    
                    profile.setName(newName);
                    profile.setContact(newContact);
                }
                return String.join("|",part);
            });
            
            updateAppointmentsForDoctor(profile);
            updateHistoryForDoctor(profile);
        }

        return true;
    }
    
    private static void udpateFile(String fileName, Function<String, String> updater) throws IOException{
        StringBuilder updatedContent = new StringBuilder();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = reader.readLine())!=null){
                updatedContent.append(updater.apply(line)).append("\n");
            }
        }
        
        try(FileWriter writer = new FileWriter(fileName)){
            writer.write(updatedContent.toString());
        }
    }
    
    private static void updateAppointmentsForCustomer(EditableProfile customer) throws IOException{
        udpateFile("Appointments.txt", line ->{
            String[] part = line.split("\\|");
            if(part.length == 7 && part[1].trim().equals(customer.getId())){
                part[0] = customer.getName();
            }
            return String.join("|", part);
        });
    }
    
    private static void updateCustomerFeedback(EditableProfile customer) throws IOException {
        udpateFile("CustomerFeedbackDoctor.txt", line -> {
            String[] part = line.split("\\|");
            if (part.length == 6 && part[0].trim().equals(customer.getId())) {
                part[1] = customer.getName(); // update customer name
            }
            return String.join("|", part);
        });

        udpateFile("CustomerFeedbackStaff.txt", line -> {
            String[] part = line.split("\\|");
            if (part.length == 6 && part[0].trim().equals(customer.getId())) {
                part[1] = customer.getName(); // update customer name
            }
            return String.join("|", part);
        });
    }

    private static void updateHistoryForCustomer(EditableProfile customer) throws IOException {
        udpateFile("History.txt", line -> {
            String[] part = line.split("\\|");
            // Format: customerId | customerName | date | time | doctorId | doctorName | feedback | charges
            if (part.length == 8 && part[0].trim().equals(customer.getId())) {
                part[1] = customer.getName(); // update customer name
            }
            return String.join("|", part);
        });
    }
    
    private static void updateAppointmentsForDoctor(EditableProfile doctor) throws IOException {
        udpateFile("Appointments.txt", line -> {
            String[] part = line.split("\\|");
            // Format: CustomerName | CustomerID | Date | Time | DoctorID | DoctorName | Feedback
            if (part.length == 7 && part[4].trim().equals(doctor.getId())) {
                part[5] = doctor.getName(); // update doctor name
            }
            return String.join("|", part);
        });
    }

    private static void updateHistoryForDoctor(EditableProfile doctor) throws IOException {
        udpateFile("History.txt", line -> {
            String[] part = line.split("\\|");
            // Format: customerId | customerName | date | time | doctorId | doctorName | feedback | charges
            if (part.length == 8 && part[4].trim().equals(doctor.getId())) {
                part[5] = doctor.getName(); // update doctor name
            }
            return String.join("|", part);
        });
    }
    
    
}
