package GUI;
import apumedicalcenter.Appointment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.ButtonGroup;
import javax.swing.table.DefaultTableModel;

public class StaffAppointmentPage extends javax.swing.JFrame {

    private final StaffMainMenuPage mainMenu;
    private final ButtonGroup timeGroup = new ButtonGroup();
    
    private final List<Appointment> appointments = new ArrayList<>();
    private static final String APPT_FILE = "Appointments.txt";
    private static final String CUST_FILE = "Customers.txt";
    private static final String DOCTOR_FILE = "Doctor.txt";
    
    public StaffAppointmentPage(StaffMainMenuPage mainMenu) {
        this.mainMenu = mainMenu;
        initComponents();
        setTitle("Appointment");
        setLocationRelativeTo(null); 
        setMinimumDateForAppointments();
        setupTimeButtons();
        setupEventListeners();
        setupTable();
        jTextField3.setEditable(false);
        jTextField6.setEditable(false);
        
        loadAppointments();
        displayAppointments(appointments);
        refreshTimeAvailability();
    }

    //load&save
    private void loadAppointments() {
        appointments.clear();
        File file = new File(APPT_FILE);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] d = line.split("\\|");
                    if (d.length == 7) {
                        appointments.add(new Appointment(d[0].trim(),
                                                         d[1].trim(),   
                                                         d[2].trim(),
                                                         d[3].trim(),   
                                                         d[4].trim(),
                                                         d[5].trim(),
                                                         d[6].trim()));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void saveAppointments() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(APPT_FILE))) {
            for (Appointment a : appointments) {
                bw.write(a.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //CRUD
    private boolean addAppointment(Appointment a) {
        if (!customerExists(a.getCustomerID())) {
            showError("Customer ID not found! Please create the customer first.");
            clearCustomerID();
            focusCustomerID();
            return false;
        }
        if (hasConflict(a.getDocID(), a.getDate(), a.getTime(), null)) {
            clearTimeSelection();
            showWarn("This doctor already has an appointment at " + a.getTime() +
                     " on " + a.getDate() + ". Please choose another time.");
            refreshTimeAvailability();
            return false;
        }
        appointments.add(a);
        saveAppointments();
        displayAppointments(appointments);
        refreshTimeAvailability();
        return true;
    }
    private boolean updateAppointment(int index, Appointment updated) {
        if (index < 0 || index >= appointments.size()) return false;

        Appointment original = appointments.get(index);
        Appointment preserved = new Appointment(
                original.getCustomerName(),
                original.getCustomerID(),
                updated.getDate(),
                updated.getTime(),
                updated.getDocID(),
                updated.getDocName(),
                updated.getReason()
        );

        if (hasConflict(preserved.getDocID(), preserved.getDate(), preserved.getTime(), index)) {
            showWarn("This doctor already has an appointment at " + preserved.getTime() +
                     " on " + preserved.getDate() + ". Please choose another time.");
            refreshTimeAvailability();
            clearTimeSelectionOnly();
            return false;
        }

        appointments.set(index, preserved);
        saveAppointments();
        displayAppointments(appointments);
        refreshTimeAvailability();
        return true;
    }
    private void deleteAppointment(int index) {
        if (index < 0 || index >= appointments.size()) return;
        appointments.remove(index);
        saveAppointments();
        displayAppointments(appointments);
        refreshTimeAvailability();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jRadioButton10 = new javax.swing.JRadioButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("MANAGE APPOINTMENT");

        jLabel2.setText("Customer ID:");

        jLabel3.setText("Customer Name:");

        jLabel4.setText("Doctor:");

        jLabel5.setText("Date:");

        jLabel6.setText("Time:");

        jLabel7.setText("Reason for visit:");

        jLabel8.setText("Search:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer Name", "Customer ID", "Date", "Time", "Doctor ID", "Doctor Name", "Reason to visit"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(5);
        }

        jRadioButton1.setText("12pm");

        jRadioButton2.setText("1pm");

        jRadioButton3.setText("2pm");

        jRadioButton4.setText("3pm");

        jRadioButton5.setText("4pm");

        jRadioButton6.setText("5pm");

        jRadioButton7.setText("6pm");

        jRadioButton8.setText("7pm");

        jRadioButton9.setText("8pm");

        jRadioButton10.setText("9pm");

        jDateChooser1.setDateFormatString("yyyy-MM-dd\n");

        jButton1.setText("Back to Main Menu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Create");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Cancel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Update");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel9.setText("Doctor Name:");

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jButton2)
                    .addComponent(jLabel9))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jRadioButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jRadioButton1, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jRadioButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jRadioButton7))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jRadioButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jRadioButton8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jRadioButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jRadioButton9))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jRadioButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jRadioButton10)))
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton5)
                                        .addGap(34, 34, 34)
                                        .addComponent(jButton3)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(488, 488, 488))
                                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jRadioButton1)
                                            .addComponent(jRadioButton2))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jRadioButton6)
                                            .addComponent(jRadioButton7)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jRadioButton3)
                                            .addComponent(jRadioButton4)
                                            .addComponent(jRadioButton5))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jRadioButton8)
                                            .addComponent(jRadioButton9)
                                            .addComponent(jRadioButton10))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(108, 108, 108))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)))
                        .addComponent(jButton1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel3)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel4)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel9)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel5)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton3)
                                    .addComponent(jButton5))
                                .addGap(18, 18, 18)
                                .addComponent(jButton4))
                            .addComponent(jButton2))
                        .addGap(43, 43, 43))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        jTextField3.requestFocus();
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        jTextField5.requestFocus();
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        jButton2.doClick();
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    //Button
    //Back to main menu
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mainMenu.setVisible(true);  
        this.dispose(); 
    }//GEN-LAST:event_jButton1ActionPerformed

    //Create
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Appointment appointment = createAppointmentFromForm();
        if (appointment != null) {
            boolean success = addAppointment(appointment);
            if(success){
                clearForm();
                showSuccess("Appointment created successfully!");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    //Update
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            showError("Please select a row to update!");
            return;
        }

        int modelRow = jTable1.convertRowIndexToModel(selectedRow);
        Appointment appointment = createAppointmentFromForm();

        if (appointment != null) {
        boolean updateSuccess = updateAppointment(modelRow, appointment);

            if (updateSuccess) {
                clearForm();
                showSuccess("Appointment updated successfully!");
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed
    //Cancel
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            showError("Please select a row to delete!");
            return;
        }

        int confirm = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to cancel this appointment?",
            "Confirm Delete",
            javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            int modelRow = jTable1.convertRowIndexToModel(selectedRow);
            deleteAppointment(modelRow);
            clearForm();
            showSuccess("Appointment canceled successfully!");
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    //Clear
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        clearForm();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
       jTextField4.requestFocus();
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        jTextField6.requestFocus();
    }//GEN-LAST:event_jTextField5ActionPerformed


    //function
    private void displayAppointments(List<Appointment> appointments) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (Appointment appointment : appointments) {
            model.addRow(new Object[]{
                appointment.getCustomerName(),
                appointment.getCustomerID(),
                appointment.getDate(),
                appointment.getTime(),
                appointment.getDocID(),
                appointment.getDocName(),
                appointment.getReason()
            });
        }
    }
    
    private void showError(String message) {
        javax.swing.JOptionPane.showMessageDialog(this, message, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    
    private void showWarn(String message) {
        javax.swing.JOptionPane.showMessageDialog(this, message, "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
    }
    
    private void showSuccess(String message) {
        javax.swing.JOptionPane.showMessageDialog(this, message, "Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void clearForm() {
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jDateChooser1.setDate(null);
        timeGroup.clearSelection();
        jTextField4.setText("");
        
        refreshTimeAvailability();
    }
    
    private void clearTimeSelection() {
        timeGroup.clearSelection();
    }
    
    private void enableAllTimes() {
        jRadioButton1.setEnabled(true);
        jRadioButton2.setEnabled(true);
        jRadioButton3.setEnabled(true);
        jRadioButton4.setEnabled(true);
        jRadioButton5.setEnabled(true);
        jRadioButton6.setEnabled(true);
        jRadioButton7.setEnabled(true);
        jRadioButton8.setEnabled(true);
        jRadioButton9.setEnabled(true);
        jRadioButton10.setEnabled(true);
    }
    
    //Disables time slots already taken for the chosen doctor & date.
    //controller.refreshTimeAvailability() calls this.
    private void applyUnavailableTimes(Set<String> unavailableTimes) {
        jRadioButton1.setEnabled(!unavailableTimes.contains("12pm"));
        jRadioButton2.setEnabled(!unavailableTimes.contains("1pm"));
        jRadioButton3.setEnabled(!unavailableTimes.contains("2pm"));
        jRadioButton4.setEnabled(!unavailableTimes.contains("3pm"));
        jRadioButton5.setEnabled(!unavailableTimes.contains("4pm"));
        jRadioButton6.setEnabled(!unavailableTimes.contains("5pm"));
        jRadioButton7.setEnabled(!unavailableTimes.contains("6pm"));
        jRadioButton8.setEnabled(!unavailableTimes.contains("7pm"));
        jRadioButton9.setEnabled(!unavailableTimes.contains("8pm"));
        jRadioButton10.setEnabled(!unavailableTimes.contains("9pm"));
    }

    private String getSelectedDateString() {
        return ((javax.swing.JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText();
    }
    
    private void setCustomerName(String name) {
        jTextField3.setText(name);
    }
    
    // Private helper methods
    private void setupTimeButtons() {
        timeGroup.add(jRadioButton1);
        timeGroup.add(jRadioButton2);
        timeGroup.add(jRadioButton3);
        timeGroup.add(jRadioButton4);
        timeGroup.add(jRadioButton5);
        timeGroup.add(jRadioButton6);
        timeGroup.add(jRadioButton7);
        timeGroup.add(jRadioButton8);
        timeGroup.add(jRadioButton9);
        timeGroup.add(jRadioButton10);
    }

    private void setupEventListeners() {
        // Customer ID auto-fill
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                handleCustomerIDChange();
            }
        });
        
        // Doctor ID auto-fill
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                handleDoctorIDChange();
            }
        });
        
        // Date change listener
        jDateChooser1.addPropertyChangeListener("date", evt -> {
        refreshTimeAvailability();
        });
    }
    
    private void setupTable() {
        // Table click listener
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                populateFormFromSelectedRow();
            }
        });

        // Table sorting
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        javax.swing.table.TableRowSorter<DefaultTableModel> sorter = new javax.swing.table.TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);

        // Default sort by date
        java.util.List<javax.swing.RowSorter.SortKey> sortKeys = new java.util.ArrayList<>();
        sortKeys.add(new javax.swing.RowSorter.SortKey(2, javax.swing.SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        sorter.sort();
        
        // Search functionality. Works across all columns (ID, name, doctor, etc.).
        jTextField1.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void search() {
                String text = jTextField1.getText().trim().replaceAll("\\s+", "");
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(new javax.swing.RowFilter<DefaultTableModel, Integer>() {
                        @Override
                        public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                            for (int i = 0; i < entry.getValueCount(); i++) {
                                String cell = entry.getStringValue(i);
                                if (cell != null) {
                                    String normalizedCell = cell.replaceAll("\\s+", "").toLowerCase();
                                    if (normalizedCell.contains(text.toLowerCase())) {
                                        return true;
                                    }
                                }
                            }
                            return false;
                        }
                    });
                }
            }
            
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) { search(); }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) { search(); }
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) { search(); }
        });
    }
    
    //Customer Auto-Fill
    //When user enters Customer ID → automatically fills in Customer Name using AppointmentModel.
    private void handleCustomerIDChange() {
    String customerID = jTextField2.getText().trim();

                if (customerID.isEmpty()) {
                    setCustomerName("");
                    return;
                }

                if (!customerExists(customerID)) {
                showError("Customer doesn't exist!");
                clearCustomerID();
                focusCustomerID();
                setCustomerName(""); 
                return;
            }

            if (jTextField3.getText().trim().isEmpty()) {
                String customerName = findCustomerName(customerID);
                setCustomerName(customerName);
            }
    }
    
    //When a row is clicked → fills form so staff can update/cancel.
    private void populateFormFromSelectedRow() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow = jTable1.convertRowIndexToModel(selectedRow);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            //Customer
            String customerID = model.getValueAt(modelRow, 1).toString().trim();
            jTextField2.setText(customerID);

            String customerName = model.getValueAt(modelRow, 0).toString().trim();
            setCustomerName(customerName);

            // Date
            try {
                String date = model.getValueAt(modelRow, 2).toString().trim();
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                jDateChooser1.setDate(sdf.parse(date));
            } catch (Exception e) {
                jDateChooser1.setDate(null);
            }

            // Time
            String time = model.getValueAt(modelRow, 3).toString().trim();
            selectTimeRadioButton(time);

            // Doctor
            String doctorID = model.getValueAt(modelRow, 4).toString().trim();
            jTextField5.setText(doctorID);

            String doctorName = findDoctorName(doctorID);
            jTextField6.setText(doctorName);
            jTextField6.setEditable(false);
            
            // Reason
            String reason = model.getValueAt(modelRow, 6).toString().trim();
            jTextField4.setText(reason);

            refreshTimeAvailability();
        }
    }

    //Create appointment from form
    private Appointment createAppointmentFromForm() {
        String customerID = jTextField2.getText().trim();
        String customerName = jTextField3.getText().trim();
        String doctorID = jTextField5.getText().trim();
        String doctorName = jTextField6.getText().trim();
        String time = getSelectedTime();
        String date = getSelectedDateString();
        String reason = jTextField4.getText().trim();

        if (customerID.isEmpty() || customerName.isEmpty() || doctorID.isEmpty() || 
            doctorName.isEmpty() || time.isEmpty() || date.isEmpty() || reason.isEmpty()) {
            showError("Please fill in all the fields");
            return null;
        }
        
        //Returns an Appointment object if valid.
        return new Appointment(customerName, customerID, date, time, doctorID, doctorName, reason);
    }
    
    private String getSelectedTime() {
        if (jRadioButton1.isSelected()) return "12pm";
        if (jRadioButton2.isSelected()) return "1pm";
        if (jRadioButton3.isSelected()) return "2pm";
        if (jRadioButton4.isSelected()) return "3pm";
        if (jRadioButton5.isSelected()) return "4pm";
        if (jRadioButton6.isSelected()) return "5pm";
        if (jRadioButton7.isSelected()) return "6pm";
        if (jRadioButton8.isSelected()) return "7pm";
        if (jRadioButton9.isSelected()) return "8pm";
        if (jRadioButton10.isSelected()) return "9pm";
        return "";
    }
    
    private void selectTimeRadioButton(String time) {
        timeGroup.clearSelection();
        switch (time) {
            case "12pm" -> jRadioButton1.setSelected(true);
            case "1pm" -> jRadioButton2.setSelected(true);
            case "2pm" -> jRadioButton3.setSelected(true);
            case "3pm" -> jRadioButton4.setSelected(true);
            case "4pm" -> jRadioButton5.setSelected(true);
            case "5pm" -> jRadioButton6.setSelected(true);
            case "6pm" -> jRadioButton7.setSelected(true);
            case "7pm" -> jRadioButton8.setSelected(true);
            case "8pm" -> jRadioButton9.setSelected(true);
            case "9pm" -> jRadioButton10.setSelected(true);
            default -> System.out.println("Unknown time: " + time);
        }
    }
    
    private void setMinimumDateForAppointments() {
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        jDateChooser1.setMinSelectableDate(today);
    }

    private void clearCustomerID() {
        jTextField2.setText("");
    }

    private void focusCustomerID() {
        jTextField2.requestFocus();
    }

    private void clearTimeSelectionOnly() {
        timeGroup.clearSelection();
        refreshTimeAvailability();
    }
    
    private boolean hasConflict(String doctor, String date, String time, Integer excludeIndex) {
        for (int i = 0; i < appointments.size(); i++) {
            if (excludeIndex != null && i == excludeIndex) continue;
            Appointment a = appointments.get(i);
            if (doctor.equals(a.getDocID()) && date.equals(a.getDate()) && time.equals(a.getTime())) {
                return true;
            }
        }
        return false;
    }

    private boolean customerExists(String customerID) {
        try (BufferedReader br = new BufferedReader(new FileReader(CUST_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split("\\|");
                if (d.length >= 1 && d[0].trim().equals(customerID.trim())) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String findCustomerName(String customerID) {
        try (BufferedReader br = new BufferedReader(new FileReader(CUST_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split("\\|");
                if (d.length >= 2 && d[0].trim().equals(customerID.trim())) {
                    return d[1].trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void refreshTimeAvailability() {
        String doctorID = jTextField5.getText().trim();
        String date = getSelectedDateString();
           if (doctorID.isEmpty() || date.isEmpty()) {
           enableAllTimes();
           return;
        }
        
        Set<String> unavailable = new HashSet<>();
        for (Appointment a : appointments) {
            if (doctorID.equals(a.getDocID()) && date.equals(a.getDate())) {
                unavailable.add(a.getTime());
            }
        }
        applyUnavailableTimes(unavailable);
    }

    private boolean doctorExists(String doctorID) {
        try (BufferedReader br = new BufferedReader(new FileReader(DOCTOR_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split("\\|");
                if (d.length >= 3 && d[0].trim().equalsIgnoreCase(doctorID.trim())) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String findDoctorName(String doctorID) {
        try (BufferedReader br = new BufferedReader(new FileReader(DOCTOR_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split("\\|");
                if (d.length >= 3 && d[0].trim().equalsIgnoreCase(doctorID.trim())) {
                    return d[2].trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void handleDoctorIDChange() {
        String doctorID = jTextField5.getText().trim();
        if (doctorID.isEmpty()) {
            jTextField6.setText("");
            return;
        }

        if (!doctorExists(doctorID)) {
            showError("Doctor ID doesn't exist.");
            jTextField5.setText("");
            jTextField6.setText("");
            jTextField5.requestFocus();
            return;
        }

        String doctorName = findDoctorName(doctorID);
        jTextField6.setText(doctorName);
        jTextField6.setEditable(false);
        refreshTimeAvailability();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
