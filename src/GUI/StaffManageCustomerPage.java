package GUI;

import apumedicalcenter.Customer;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StaffManageCustomerPage extends javax.swing.JFrame {
    
    private final StaffMainMenuPage mainMenu;
    private final ButtonGroup genderGroup = new ButtonGroup();

    private final List<Customer> customers = new ArrayList<>();
    private static final String FILE_NAME = "Customers.txt";
    
    //Constructor
    public StaffManageCustomerPage(StaffMainMenuPage mainMenu) {
        this.mainMenu = mainMenu;
        initComponents();
        setTitle("Manage Customer");
        setLocationRelativeTo(null);

        genderGroup.add(jRadioButton1);
        genderGroup.add(jRadioButton2);

        loadCustomers();   // 直接从文件加载
        displayCustomers(customers);
        autoCustomerIDGenerate();

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                populateFormFromSelectedRow();
            }
        });

        //Table Setup, Default sorting = Customer ID ascending.
        DefaultTableModel modelTbl = (DefaultTableModel) jTable1.getModel();
        javax.swing.table.TableRowSorter<DefaultTableModel> sorter = new javax.swing.table.TableRowSorter<>(modelTbl);
        jTable1.setRowSorter(sorter);

        java.util.List<javax.swing.RowSorter.SortKey> sortKeys = new java.util.ArrayList<>();
        sortKeys.add(new javax.swing.RowSorter.SortKey(0, javax.swing.SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        sorter.sort();

        // Search
        jTextField4.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) { searchCustomer(); }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) { searchCustomer(); }
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) { searchCustomer(); }
        });

        jDateChooser1.setMaxSelectableDate(new java.util.Date());
        ((javax.swing.JTextField) jDateChooser1.getDateEditor().getUiComponent()).setEditable(false);
    }

    //save&load
    private void loadCustomers() {
        customers.clear();
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length == 6) {
                    customers.add(new Customer(data[0], data[1], data[2], data[3], data[4], data[5]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void saveCustomers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Customer c : customers) {
                bw.write(c.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //CRUD
    private boolean addCustomer(Customer c) {
        for (Customer existing : customers) {
            if (existing.getCustomerID().equalsIgnoreCase(c.getCustomerID())) {
                JOptionPane.showMessageDialog(this, "Customer ID already exists!");
                return false;
            }
        }

        for (Customer existing : customers) {
        String newIc = c.getIc();
        String existingIc = existing.getIc();

        if (newIc != null && !newIc.isEmpty() && !newIc.equals("-")) {
            if (existingIc != null && !existingIc.isEmpty() && existingIc.equalsIgnoreCase(newIc)) {
                JOptionPane.showMessageDialog(this, "IC number already exists!");
                return false;
            }
        }
    }


        customers.add(c);
        saveCustomers();
        displayCustomers(customers);
        clearForm();
        autoCustomerIDGenerate();
        return true;
    }
    private boolean updateCustomer(int index, Customer c) {
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to update!");
            return false;
        }

        // 检查重复 IC（排除当前正在更新的行）
        for (int i = 0; i < customers.size(); i++) {
            if (i != index) {
                Customer existing = customers.get(i);
                if (existing.getIc() != null && !existing.getIc().isEmpty() &&
                    existing.getIc().equalsIgnoreCase(c.getIc())) {
                    JOptionPane.showMessageDialog(this, "IC number already exists!");
                    return false;
                }
            }
        }

        customers.set(index, c);
        saveCustomers();
        displayCustomers(customers);
        clearForm();
        autoCustomerIDGenerate();
        JOptionPane.showMessageDialog(this, "Customer updated successfully!");
        return true;
    }
    private void deleteCustomer(int index) {
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete!");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this customer?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            customers.remove(index);
            saveCustomers();
            displayCustomers(customers);
            clearForm();
            autoCustomerIDGenerate();
            JOptionPane.showMessageDialog(this, "Customer deleted successfully!");
        }
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
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("MANAGE CUSTOMER");

        jLabel2.setText("Customer ID:");

        jLabel3.setText("Customer Name:");

        jLabel4.setText("Gender:");

        jLabel5.setText("DOB:");

        jLabel6.setText("Contact:");

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

        jRadioButton1.setText("Female");

        jRadioButton2.setText("Male");

        jDateChooser1.setDateFormatString("yyyy-MM-dd\n");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Customer ID", "Name", "Gender", "DOB", "Contact", "IC"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        jButton1.setText("Create");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Back to Main Menu");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Reset");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel7.setText("Search:");

        jLabel8.setText("IC:");

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(33, 33, 33))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(13, 13, 13))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(60, 60, 60))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(58, 58, 58))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(64, 64, 64)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton2))
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jTextField1)
                            .addComponent(jTextField3)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jTextField5))
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(36, 36, 36)
                                .addComponent(jButton2)))
                        .addGap(27, 27, 27)
                        .addComponent(jButton3)
                        .addGap(30, 30, 30)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField4))
                                    .addComponent(jLabel1))))
                        .addContainerGap(85, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(34, 34, 34))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(jButton4))
                        .addGap(23, 23, 23))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Button
    //Create
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Customer c = createCustomerFromForm();
        if (c != null) {
            boolean success = addCustomer(c);
            if (success) {
                JOptionPane.showMessageDialog(this, "Customer created successfully!");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    //Update
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow = jTable1.convertRowIndexToModel(selectedRow);
            Customer c = createCustomerFromForm();
            if (c != null) {
                updateCustomer(modelRow, c);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to update!");
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    //Delete
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow = jTable1.convertRowIndexToModel(selectedRow);
            deleteCustomer(modelRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete!");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        mainMenu.setVisible(true);  
        this.dispose(); 
    }//GEN-LAST:event_jButton4ActionPerformed
    //Clear
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        clearForm();
        autoCustomerIDGenerate();
    }//GEN-LAST:event_jButton5ActionPerformed

    //TextField
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        jTextField2.requestFocus();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        jTextField3.requestFocus();
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        jTextField5.requestFocus();
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
       jButton1.doClick();
    }//GEN-LAST:event_jTextField5ActionPerformed

    //Function
    
    //Collects data from form.
    //Validates that all fields are filled.
    //Validates contact number.
    //Returns a new Customer object (or null if invalid).
    private Customer createCustomerFromForm() {
        String customerID = jTextField1.getText().trim();
        String name = jTextField2.getText().trim();
        String gender = jRadioButton1.isSelected() ? "Female" :
                        jRadioButton2.isSelected() ? "Male" : "";
        String dob = ((javax.swing.JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText();
        String contact = jTextField3.getText().trim();
        String ic = jTextField5.getText().trim();


        if (customerID.isEmpty() || name.isEmpty() || gender.isEmpty() || dob.isEmpty() || contact.isEmpty() || ic.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!");   
            autoCustomerIDGenerate();
            return null;
        }

        if (!validateContactNumber()) {
            return null;
        }
        
        if(!validateIC()){
            return null;
        }

        return new Customer(customerID, name, gender, dob, contact,ic);
    }
    
    private void displayCustomers(List<Customer> customers) {
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        tableModel.setRowCount(0);
        for (Customer c : customers) {
            tableModel.addRow(new Object[]{
                c.getCustomerID(),
                c.getCustomerName(),
                c.getGender(),
                c.getDob(),
                c.getContactNumber(),
                c.getIc()
            });
        }
    }
    
    private void clearForm() {
        jTextField1.setText("");
        jTextField2.setText("");
        genderGroup.clearSelection();
        jDateChooser1.setDate(null);
        jTextField3.setText("");
        jTextField5.setText("");
    }
    
    //Reads all existing IDs from file.
    //Finds the smallest available ID (C0001 … C9999).
    //Sets it in the form (read-only).
    private void autoCustomerIDGenerate() {
        boolean[] used = new boolean[1112];
        Arrays.fill(used, false);

        File file = new File("Customers.txt");
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length > 0 && parts[0].startsWith("C")) {
                        try {
                            int num = Integer.parseInt(parts[0].substring(1));
                            if (num >= 1 && num <= 1111) used[num] = true;
                        } catch (NumberFormatException ignore) {}
                    }
                }
            } catch (IOException ignored) {}
        }

        String newID = "C0001";
        for (int i = 1; i <= 9999 ; i++) {
            if (!used[i]) {
                newID = String.format("C%04d", i);
                break;
            }
        }

        jTextField1.setText(newID);
        jTextField1.setEditable(false);
    }
    
    //Ensures contact number is 10–11 digits only.
    //If invalid, shows warning and clears the field.
    private boolean validateContactNumber() {
        String contact = jTextField3.getText().trim();
        if (contact.isEmpty()) return true;

        if (!contact.matches("\\d{10,11}")) {
            JOptionPane.showMessageDialog(
                this,
                "Contact number must be 10-11 digits only!\nPlease enter a valid contact number.",
                "Invalid Contact Number",
                JOptionPane.WARNING_MESSAGE
            );
            jTextField3.setText("");
            jTextField3.requestFocus();
            return false;
        }
        return true;
    }
    
    //Validates IC format: 12 digits or digits with dash (for foreigners)
    private boolean validateIC() {
        String ic = jTextField5.getText().trim();
        if (ic.isEmpty()) return true;

        // Malaysian IC: exactly 12 digits (e.g., 990101061234)
        // Foreigner did not have IC so fill with dash (e.g., -)
        if (!ic.matches("\\d{12}") && !ic.matches("-")) {
            JOptionPane.showMessageDialog(
                this,
                "IC must be exactly 12 digits (Malaysian) or '-' (Foreigner)!\nPlease enter a valid IC number.",
                "Invalid IC Number",
                JOptionPane.WARNING_MESSAGE
            );
            jTextField5.setText("");
            jTextField5.requestFocus();
            return false;
        }
        return true;
    }
    private void searchCustomer() {
        String keyword = jTextField4.getText().trim().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        javax.swing.table.TableRowSorter<DefaultTableModel> sorter =
                (javax.swing.table.TableRowSorter<DefaultTableModel>) jTable1.getRowSorter();

        if (keyword.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(new javax.swing.RowFilter<DefaultTableModel, Integer>() {
                @Override
                public boolean include(javax.swing.RowFilter.Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                    for (int i = 0; i < entry.getValueCount(); i++) {
                        if (entry.getStringValue(i) != null &&
                            entry.getStringValue(i).toLowerCase().contains(keyword)) {
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
    }
    
    //When clicking a row in the table → fills the form fields with that customer’s data.
    private void populateFormFromSelectedRow() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow = jTable1.convertRowIndexToModel(selectedRow);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            jTextField1.setText(model.getValueAt(modelRow, 0).toString());
            jTextField2.setText(model.getValueAt(modelRow, 1).toString());

            String gender = model.getValueAt(modelRow, 2).toString();
            switch (gender) {
                case "Female" -> jRadioButton1.setSelected(true);
                case "Male" -> jRadioButton2.setSelected(true);
                default -> genderGroup.clearSelection();
            }

            try {
                String dob = model.getValueAt(modelRow, 3).toString();
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                jDateChooser1.setDate(sdf.parse(dob));
            } catch (ParseException e) {
                jDateChooser1.setDate(null);
            }

            jTextField3.setText(model.getValueAt(modelRow, 4).toString());
            
            if (model.getValueAt(modelRow, 5) != null) {
                jTextField5.setText(model.getValueAt(modelRow, 5).toString());
            } else {
                jTextField5.setText("");
            }
        }
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
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
