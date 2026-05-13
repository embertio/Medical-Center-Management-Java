
package GUI;

import apumedicalcenter.Customer;
import apumedicalcenter.Doctor;
import apumedicalcenter.DoctorHistoryRecord;
import apumedicalcenter.FeedbackDoctor;
import apumedicalcenter.History;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class DoctorViewHistoryPage extends javax.swing.JFrame {
    private Doctor doctor;

    public DoctorViewHistoryPage(Doctor doctor) {
        this.doctor = doctor;
        initComponents();
        setTitle("Doctor View History - " + doctor.getName());
        setLocationRelativeTo(null);

        // Replace table model with proper columns
        String[] columns = {"Customer ID", "Customer Name", "Date", "Time", "Doctor ID", "Doctor Name", "Doctor Feedback", "Charges", "Customer Feedback"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        jTable1.setModel(model);
        
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);//cusID
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(80);//cusName
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(50);//date
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(30);//time
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(50);//docID
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(60);//docName
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(120);//feedback
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(30);//charges
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(120);//cusfeedback
       
        // Load history from file
        loadHistory(model, "");
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("VIEW HISTORY");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Back to Main Menu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGap(96, 96, 96))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 896, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(378, 378, 378)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jButton1)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new DoctorMainMenu(doctor).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String keyword = jTextField1.getText().trim();  
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        // Clear old rows first
        model.setRowCount(0);

        // Reload history with search keyword
        loadHistory(model, keyword);

        // Check if no rows were found
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                    "No customer found for keyword: " + keyword,
                    "Search Result",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

private void loadHistory(DefaultTableModel model, String keyword) {
    File doctorHistoryFile = new File("History.txt");
    File customerFeedbackFile = new File("CustomerFeedbackDoctor.txt");

    Map<String, FeedbackDoctor> customerComments = new HashMap<>();

    // Load customer feedback
    if (customerFeedbackFile.exists()) {
        try (BufferedReader br = new BufferedReader(new FileReader(customerFeedbackFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 6) {
                    FeedbackDoctor feedback = new FeedbackDoctor(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),
                            parts[3].trim(),
                            parts[4].trim(),
                            parts[5].trim()
                    );
                    
                    customerComments.put(parts[0].trim() + "|" + parts[2].trim() + "|" + parts[4].trim(), feedback);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading CustomerFeedbackDoctor.txt: " + e.getMessage(),
                    "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Load doctor history
    if (doctorHistoryFile.exists()) {
        try (BufferedReader br = new BufferedReader(new FileReader(doctorHistoryFile))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 8) {
                    Customer c = new Customer(parts[0].trim(), parts[1].trim());
                    Doctor d = new Doctor(parts[4].trim(), parts[5].trim());
                    
                    History history = new History(
                            c,
                            d,
                            parts[2].trim(),
                            parts[3].trim(),
                            parts[6].trim(),
                            parts[7].trim()
                    );
                    


                    FeedbackDoctor cf = customerComments.get(parts[0].trim() + "|" + parts[4].trim() + "|" + parts[2].trim());
                    DoctorHistoryRecord record = new DoctorHistoryRecord(history, cf);

                    if (record.getDocID().equalsIgnoreCase(doctor.getId())) {
                        // Apply search filter
                        if (keyword.isEmpty() || 
                            record.getCusID().toLowerCase().contains(keyword.toLowerCase()) ||
                            record.getCusName().toLowerCase().contains(keyword.toLowerCase())) {
                            

                            model.addRow(new Object[]{
                                record.getCusID(),
                                record.getCusName(),
                                record.getDate(),
                                record.getTime(),
                                record.getDocID(),
                                record.getDocName(),
                                record.getFeedback(),
                                record.getCharges(),
                                (cf  != null ? cf.getComment() : "-")
                            });
                        }
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading History.txt: " + e.getMessage(),
                    "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}





    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
