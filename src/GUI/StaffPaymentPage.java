package GUI;
import apumedicalcenter.Customer;
import apumedicalcenter.Payment;
import java.awt.print.PrinterException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class StaffPaymentPage extends javax.swing.JFrame {
    
    private final StaffMainMenuPage mainMenu;
    
    private final List<Payment> payments = new ArrayList<>();
    private static final String FILE_NAME = "Payments.txt";
    
    //Constructor
    public StaffPaymentPage(StaffMainMenuPage mainMenu) {
        this.mainMenu = mainMenu;
        loadPayments(); // 加载支付记录
        initComponents();
        setTitle("Payment Record & Receipt");
        setLocationRelativeTo(null);

        refreshTable(jTable2);
        jTextField1.setText(generateNextReceiptNo());
        jTextField1.setEditable(false);

        jTable2.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && jTable2.getSelectedRow() != -1) {
                int row = jTable2.convertRowIndexToModel(jTable2.getSelectedRow());
                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                jTextField1.setText(model.getValueAt(row, 0).toString());
                jTextField1.setEditable(false);
                jTextField2.setText(model.getValueAt(row, 1).toString());
                jTextField3.setText(model.getValueAt(row, 2).toString());
                try {
                    jDateChooser1.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(row, 3).toString()));
                } catch (Exception e) { jDateChooser1.setDate(null); }
                jTextField4.setText(model.getValueAt(row, 4).toString());
                jComboBox1.setSelectedItem(model.getValueAt(row, 5).toString());
            }
        });

        //Table Handling
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable2.setRowSorter(sorter);
        
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        sorter.sort();
        
        jTextField5.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void search() {
                String text = jTextField5.getText().trim();
                sorter.setRowFilter(text.isEmpty() ? null : RowFilter.regexFilter("(?i)" + text, 0,1,2));
            }
            @Override public void insertUpdate(javax.swing.event.DocumentEvent e) { search(); }
            @Override public void removeUpdate(javax.swing.event.DocumentEvent e) { search(); }
            @Override public void changedUpdate(javax.swing.event.DocumentEvent e) { search(); }
        });

        jDateChooser1.setMaxSelectableDate(new Date());
        ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).setEditable(false);

    }

    //load&save
    private void loadPayments() {
        payments.clear();
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 6) {
                    payments.add(new Payment(
                        parts[0], parts[1], parts[2], parts[3],
                        Double.parseDouble(parts[4]), parts[5]
                    ));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void savePayments() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Payment p : payments) {
                bw.write(p.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //CRUD
    private void addPayment(Payment p) {
        payments.add(p);
        savePayments();
    }
    private void updatePayment(int index, Payment p) {
        payments.set(index, p);
        savePayments();
    }
    private void deletePayment(int index) {
        payments.remove(index);
        savePayments();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        area = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("MANAGE PAYMENT");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Receipt No.", "Customer ID", "Customer Name", "Date", "Amount", "Method"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jLabel2.setText("Receipt No. :");

        jLabel3.setText("Customer ID:");

        jLabel4.setText("Customer Name:");

        jLabel5.setText("Date:");

        jLabel6.setText("Amount (RM) :");

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

        jDateChooser1.setDateFormatString("yyyy-MM-dd\n");

        area.setColumns(20);
        area.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        area.setRows(5);
        jScrollPane3.setViewportView(area);

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

        jButton4.setText("Reset");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Print");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Back to Main Menu");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel7.setText("Search:");

        jButton7.setText("Generate");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Clear");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel8.setText("Method:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TNG", "CASH", "Credit Card", "Debit Card" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(501, 501, 501)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(618, 618, 618))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(73, 73, 73)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(52, 52, 52)
                            .addComponent(jTextField3))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(72, 72, 72)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel8))
                            .addGap(63, 63, 63)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(62, 62, 62)
                                .addComponent(jButton3)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addGap(37, 37, 37)
                                .addComponent(jButton8)
                                .addGap(46, 46, 46)
                                .addComponent(jButton5)
                                .addGap(52, 52, 52))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton6)
                                .addGap(138, 138, 138))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton5)
                                    .addComponent(jButton7)
                                    .addComponent(jButton8)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(54, 54, 54)
                                        .addComponent(jLabel5)
                                        .addGap(59, 59, 59))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(51, 51, 51)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1)
                                    .addComponent(jButton2)
                                    .addComponent(jButton3))
                                .addGap(35, 35, 35)
                                .addComponent(jButton4))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Button1
    //create
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Payment p = getPaymentFromForm();
        if (getPaymentIndexByReceiptNo(p.getReceiptNo()) != -1) {
            JOptionPane.showMessageDialog(this, "Receipt No already exists!");
            return;
        }
        addPayment(p);
        refreshTable(jTable2);
        clearForm();
        jTextField1.setText(generateNextReceiptNo());
        
        JOptionPane.showMessageDialog(this, "Payment record created successfully!");
    }//GEN-LAST:event_jButton1ActionPerformed
    //update
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int row = getSelectedRow();
        if (row == -1) return;
        Payment p = getPaymentFromForm();
        if (p == null) return;
        updatePayment(row, p);
        refreshTable(jTable2);
        clearForm();
        jTextField1.setText(generateNextReceiptNo());
        
        JOptionPane.showMessageDialog(this, "Payment record updated successfully!");
    }//GEN-LAST:event_jButton2ActionPerformed
    //delete
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int row = getSelectedRow();
        if (row == -1) return;
        int confirm = JOptionPane.showConfirmDialog(this, "Delete this payment?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            deletePayment(row);
            refreshTable(jTable2);
            clearForm();
            jTextField1.setText(generateNextReceiptNo());
            
                    JOptionPane.showMessageDialog(this, "Payment record deleted successfully!");
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed
    //clear
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        clearForm();
        jTextField1.setText(generateNextReceiptNo());
    }//GEN-LAST:event_jButton4ActionPerformed
    //print
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try{
            area.print();
        }
        catch(PrinterException e){
        }
        
    }//GEN-LAST:event_jButton5ActionPerformed
    //Back to Main Menu
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        mainMenu.setVisible(true);  
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    //TextField
    //Receipt No.
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        jTextField2.requestFocus();
    }//GEN-LAST:event_jTextField1ActionPerformed
    //Customer ID
    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        autoFillCustomerName();
        jTextField3.requestFocus();
    }//GEN-LAST:event_jTextField2ActionPerformed
    //Customer Name
    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        jDateChooser1.requestFocus();
    }//GEN-LAST:event_jTextField3ActionPerformed
    //Amount
    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        String amountText = jTextField4.getText().trim();
        if(!amountText.matches("^\\d+(\\.\\d{1,2})?$")){
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number with up to 2 decimal places.", "Input Error", JOptionPane.ERROR_MESSAGE);
            jTextField4.setText(""); 
            jTextField4.requestFocus();
        }
    
    }//GEN-LAST:event_jTextField4ActionPerformed
    //Search
    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
       
    }//GEN-LAST:event_jTextField5ActionPerformed

    //Button2
    //Generate
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        generateReceipt();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        clearForm();
        jTextField1.setText(generateNextReceiptNo());
        area.setText("");
    }//GEN-LAST:event_jButton8ActionPerformed

    
    //function
    private int getSelectedRow() {
        int row = jTable2.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row first!");
            return -1;
        }
        return jTable2.convertRowIndexToModel(row);
    }
    
    private List<Customer> getAllCustomersFromFile() {
        List<Customer> list = new ArrayList<>();
        File file = new File("Customers.txt");
        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 2) { 
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    String gender = parts.length > 2 ? parts[2].trim() : "";
                    String dob = parts.length > 3 ? parts[3].trim() : "";
                    String contact = parts.length > 4 ? parts[4].trim() : "";
                    String ic = parts.length > 5 ? parts[5].trim() : "";
                    list.add(new Customer(id, name, gender, dob, contact, ic));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    private void autoFillCustomerName() {
        String customerID = jTextField2.getText().trim();
        if (customerID.isEmpty()) return;

        List<Customer> customers = getAllCustomersFromFile();
        boolean found = false;
        for (Customer c : customers) {
            if (c.getCustomerID().equalsIgnoreCase(customerID)) { 
                jTextField3.setText(c.getCustomerName());
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Customer doesn't exist.", "Input Error", JOptionPane.ERROR_MESSAGE);
            jTextField2.setText("");
            jTextField3.setText("");
        }
    }
    
    private void clearForm(){
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jDateChooser1.setDate(null);
        jComboBox1.setSelectedIndex(0);
    }
    
    //Builds a receipt string with all details and displays it in the area (text area).
    private void generateReceipt(){
        area.setText("****************************************\n");
        area.setText(area.getText() + "*                        Receipt                        *\n");
        area.setText(area.getText() + "****************************************\n");

        Date now = new Date();
        SimpleDateFormat sdfDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentDate = sdfDateTime.format(now);
        area.append("Generated On : " + currentDate + "\n\n");

        String receiptNo = jTextField1.getText();
        String customerId = jTextField2.getText();
        String customerName = jTextField3.getText();
        String paymentDate = ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText();
        String amount = jTextField4.getText();
        String method = jComboBox1.getSelectedItem().toString();

        
        area.append("----------------------------------------------\n");
        area.append("Receipt No   : " + receiptNo + "\n");
        area.append("Patient ID   : " + customerId + "\n");
        area.append("Patient Name : " + customerName + "\n");
        area.append("Payment Date : " + paymentDate + "\n");
        area.append("Amount (RM)  : " + amount + "\n");
        area.append("Method       : " + method + "\n");
        area.append("----------------------------------------------\n\n");

        area.setText(area.getText() + "****************************************\n");
        area.setText(area.getText() + "*          Thank you for payment!         *\n");
        area.setText(area.getText() + "****************************************\n");
    }
    
    //Reads values from the form, validates them, and creates a Payment object.
    private Payment getPaymentFromForm() {
        try {
            String receiptNo = jTextField1.getText().trim();
            String cid = jTextField2.getText().trim();
            String cname = jTextField3.getText().trim();
            String date = ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText().trim();
            double amt = Double.parseDouble(jTextField4.getText().trim());
            String method = jComboBox1.getSelectedItem().toString();

            if (receiptNo.isEmpty() || cid.isEmpty() || cname.isEmpty() || date.isEmpty())
                throw new IllegalArgumentException("All fields must be filled.");
            return new Payment(receiptNo, cid, cname, date, amt, method);
        } 
        catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage());
            return null;
        }
    }
    
    private int getPaymentIndexByReceiptNo(String receiptNo) {
        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getReceiptNo().equals(receiptNo)) {
                return i;
            }
        }
        return -1;
    }
    
    private void refreshTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Payment p : payments) {
            model.addRow(new Object[]{
                p.getReceiptNo(),
                p.getCustomerID(),
                p.getCustomerName(),
                p.getPayDate(),
                p.getAmount(),
                p.getMethod()
            });
        }
    }
    
    private String generateNextReceiptNo() {
        Set<Integer> used = new HashSet<>();
        for (Payment p : payments) {
            try {
                int num = Integer.parseInt(p.getReceiptNo().substring(1));
                used.add(num);
            } catch (NumberFormatException ignored) {}
        }

        for (int i = 1; i <= 9999; i++) {
            if (!used.contains(i)) {
                return String.format("R%04d", i);
            }
        }
        return "FULL"; 
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
