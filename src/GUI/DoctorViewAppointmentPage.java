
package GUI;

import apumedicalcenter.Appointment;
import apumedicalcenter.Customer;
import apumedicalcenter.Doctor;
import apumedicalcenter.History;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.*;

// JFrame class
public class DoctorViewAppointmentPage extends javax.swing.JFrame {
    private Doctor doctor;

    // Constructor (Doctor passed in)
    public DoctorViewAppointmentPage(Doctor doctor) {
        this.doctor = doctor;
        initComponents();
        setTitle("Doctor View Appointments - " + doctor.getName());
        setLocationRelativeTo(null);

        // Replace default table model
        String[] columns = {"Customer Name", "Customer ID", "Date", "Time", "Reason", "Feedback & Charges"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        jTable1.setModel(model);

        // Column widths
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(90);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(200);

        // Load appointments
        loadAppointments(model);

        // Add button to last column
        addButtonToTable(jTable1, 5);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("VIEW UPCOMING APPOINTMENTS");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 912, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents




    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new DoctorMainMenu(doctor).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
   
     private void loadAppointments(DefaultTableModel model) {
        try (BufferedReader br = new BufferedReader(new FileReader("Appointments.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 7) {
                    Appointment appt = new Appointment(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),
                            parts[3].trim(),
                            parts[4].trim(),
                            parts[5].trim(),
                            parts[6].trim()
                    );


                    if (appt.getDocID().equalsIgnoreCase(doctor.getId())) {
                        model.addRow(new Object[]{appt.getCustomerName(), appt.getCustomerID(), appt.getDate(), appt.getTime(), appt.getReason(), "Feedback & Charges"});
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading Appointments.txt: " + e.getMessage(),
                    "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Add button column
    private void addButtonToTable(JTable table, int columnIndex) {
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(columnIndex).setCellRenderer(new ButtonRenderer());
        columnModel.getColumn(columnIndex).setCellEditor(new ButtonEditor(new JCheckBox(), table, doctor));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}

class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

// =======================
// Button Editor
// =======================
class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean clicked;
    private JTable table;
    private Doctor doctor;

    public ButtonEditor(JCheckBox checkBox, JTable table, Doctor doctor) {
        super(checkBox);
        this.table = table;
        this.doctor = doctor;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            int row = table.getSelectedRow();
            String customerName = table.getValueAt(row, 0).toString();
            String customerId = table.getValueAt(row, 1).toString();
            String date = table.getValueAt(row, 2).toString();
            String time = table.getValueAt(row, 3).toString();

            // Ask for feedback
            String feedbackText = JOptionPane.showInputDialog(button, "Enter feedback for " + customerName + ":");
            if (feedbackText != null && !feedbackText.trim().isEmpty()) {
                // Ask for charges
                String charges = JOptionPane.showInputDialog(button, "Enter charges (RM):");
                if (charges != null && !charges.trim().isEmpty()) {                  
                    
                    Customer customer = new Customer(customerId, customerName);
                    History history = new History(customer, doctor, date, time, feedbackText, charges);


                    try (BufferedWriter bw = new BufferedWriter(new FileWriter("History.txt", true))) {
                        bw.write(history.toString());
                        bw.newLine();
                    } catch (IOException ex) {
                     JOptionPane.showMessageDialog(button, "Error writing to History.txt: " + ex.getMessage(),
                             "File Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    
                    File inputFile = new File("Appointments.txt");
                    File tempFile = new File("Appointments_temp.txt");
                    
                    try(BufferedReader br = new BufferedReader(new FileReader(inputFile));
                        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))){
                        
                        String line;
                        while((line=br.readLine()) != null){
                            if (!line.contains(customerId) || !line.contains(date) || !line.contains(time)){
                                bw.write(line);
                                bw.newLine();
                            }
                        }
                    }catch (IOException ex){
                        JOptionPane.showMessageDialog(button, "Error updating Appointments.txt: " + ex.getMessage(),
                                "File Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    inputFile.delete();
                    tempFile.renameTo(inputFile);

                    // Remove row from table
                    SwingUtilities.invokeLater(() -> {
                        ((DefaultTableModel) table.getModel()).removeRow(row);
                        JOptionPane.showMessageDialog(button,
                            "Feedback & Charges saved!\nFeedback: " + feedbackText + "\nCharges: RM " + charges);
                    });
                }
            }
        }
        clicked = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }
}
