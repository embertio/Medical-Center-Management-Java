/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apumedicalcenter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sheng Jie
 */
public class Manager extends User {

    public Manager(String id, String ic, String name, String gender, LocalDate dob, String contact) {
        super(id, ic, name, gender, dob, contact);
    }

    public boolean createStaff(String ic, String name, String gender, String dob, String contact) {
        // Generate next Staff ID automatically
        String newStaffId = FileOperation.getNextPrimaryKeyFromFile("Staffs.txt", "S"); // e.g. "S001"

        Staff staff = new Staff(newStaffId, ic, name, gender, LocalDate.parse(dob.trim()), contact);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Staffs.txt", true))) {
            writer.write(staff.toString());
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }
    
    public JTable viewAllStaff() {
        DefaultTableModel model = FileOperation.loadFileToTableModel("Staffs.txt");
        return new JTable(model);
    }
    
    public boolean deleteStaff(String staffID){
        return FileOperation.deleteRecordById("Staffs.txt", staffID);
    }
    
    public JTable searchStaff(String id) {
        DefaultTableModel model = FileOperation.loadFilteredTableModelById("Staffs.txt", id);
        return new JTable(model);
    }
    
    public boolean editStaff(Staff updatedStaff) {
        // use a helper function to replace line with same ID
        return FileOperation.editObjectById("Staffs.txt", updatedStaff.getStaffID(), updatedStaff.toString());
    }
    
    @Override
    public String getRole(){
        return "Doctor";
    }

    
    @Override
    public String toString() {
        return getId() + "|" + getIc() + "|" + getName() + "|" + getGender() + "|" + getDob() + "|" + getContact();
    }

    // 1. Create Doctor
    public boolean createDoctor(String ic, String name, String gender, String dob, String contact) {
        String newDoctorId = FileOperation.getNextPrimaryKeyFromFile("Doctor.txt", "D"); // e.g. "D001"
        Doctor doctor = new Doctor(newDoctorId, ic, name, gender, LocalDate.parse(dob.trim()), contact);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Doctor.txt", true))) {
            writer.write(doctor.toString());
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }

    // 2. View All Doctors
    public JTable viewAllDoctors() {
        DefaultTableModel model = FileOperation.loadFileToTableModel("Doctor.txt");
        return new JTable(model);
    }

    // 3. Delete Doctor
    public boolean deleteDoctor(String doctorID) {
        return FileOperation.deleteRecordById("Doctor.txt", doctorID);
    }

    // 4. Search Doctor
    public JTable searchDoctor(String id) {
        DefaultTableModel model = FileOperation.loadFilteredTableModelById("Doctor.txt", id);
        return new JTable(model);
    }

    // 5. Edit Doctor
    public boolean editDoctor(Doctor updatedDoctor) {
        return FileOperation.editObjectById("Doctor.txt", updatedDoctor.getId(), updatedDoctor.toString());
    }
    
    
    // 6. Create Manager
    public boolean createManager(String ic, String name, String gender, String dob, String contact) {
        String newManagerId = FileOperation.getNextPrimaryKeyFromFile("Manager.txt", "M"); // e.g. "M001"
        Manager manager = new Manager(newManagerId, ic, name, gender, LocalDate.parse(dob.trim()), contact);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Manager.txt", true))) {
            writer.write(manager.toString());
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }

    // 7. View All Managers
    public JTable viewAllManagers() {
        DefaultTableModel model = FileOperation.loadFileToTableModel("Manager.txt");
        return new JTable(model);
    }

    // 8. Delete Manager
    public boolean deleteManager(String managerID) {
        return FileOperation.deleteRecordById("Manager.txt", managerID);
    }

    // 9. Search Manager
    public JTable searchManager(String id) {
        DefaultTableModel model = FileOperation.loadFilteredTableModelById("Manager.txt", id);
        return new JTable(model);
    }

    // 10. Edit Manager
    public boolean editManager(Manager updatedManager) {
        return FileOperation.editObjectById("Manager.txt", updatedManager.getId(), updatedManager.toString());
    }
    
    public JTable viewAppointment() {
        DefaultTableModel model = FileOperation.loadFileToTableModel("Appointments.txt");
        return new JTable(model);
    }
    
    // 11. View Customer Feedback (Staff)
    public JTable viewCustomerFeedbackStaff() {
        DefaultTableModel model = FileOperation.loadFileToTableModel("CustomerFeedbackStaff.txt");
        return new JTable(model);
    }
    
    public JTable viewCustomerFeedbackDoctor() {
        DefaultTableModel model = FileOperation.loadFileToTableModel("CustomerFeedbackDoctor.txt");
        return new JTable(model);
    }
    
    public JTable viewDoctorFeedback() {
        DefaultTableModel model = FileOperation.loadFileToTableModel("History.txt");
        return new JTable(model);
    }
    
    public static JTable generateProfitReportByDate(Date selectedDate) {
        return generateProfitReportInternal(selectedDate, false);
    }

    public static JTable generateProfitReportByMonth(Date selectedDate) {
        return generateProfitReportInternal(selectedDate, true);
    }

    private static JTable generateProfitReportInternal(Date selectedDate, boolean wholeMonth) {
        String filePath = "Payments.txt"; // adjust path
        String[] columns = {"Receipt ID", "Customer ID", "Name", "Date", "Amount", "Payment Method"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        double totalProfit = 0.0;
        SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length == 6) {
                    Date fileDate = sdfDay.parse(data[3].trim());
                    boolean match;
                    if (wholeMonth) {
                        // compare by yyyy-MM
                        match = sdfMonth.format(fileDate).equals(sdfMonth.format(selectedDate));
                    } else {
                        // compare exact day
                        match = sdfDay.format(fileDate).equals(sdfDay.format(selectedDate));
                    }
                    if (match) {
                        model.addRow(data);
                        totalProfit += Double.parseDouble(data[4]);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
        }

        model.addRow(new Object[]{"", "", "", wholeMonth ? "TOTAL PROFIT" : "TOTAL PROFIT",
                String.format("%.2f", totalProfit), ""});

        return new JTable(model);
    }
}

    


    