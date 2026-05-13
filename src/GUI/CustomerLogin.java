package GUI;
import apumedicalcenter.Customer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class CustomerLogin extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CustomerLogin.class.getName());

    public CustomerLogin() {
        initComponents();
        setTitle("Customer Login");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("CUSTOMER LOGIN");
        jLabel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Username:");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Password:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(jButton1)))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(35, 35, 35)
                .addComponent(jButton1)
                .addGap(70, 70, 70))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String InputUsername = jTextField1.getText().trim(); //local variable
        String InputIC = jTextField2.getText().trim();//trim remove any leading white spaces. local variable
        
        if (InputUsername.isEmpty() || InputIC.isEmpty()) { //if either one is empty
            JOptionPane.showMessageDialog(this, 
                    "Please enter your username and IC number!", 
                    "Input Error", JOptionPane.WARNING_MESSAGE);
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField1.requestFocus();
            return;
        }
        
        Customer logInCustomer = authentication(InputUsername, InputIC);
        
        if(logInCustomer != null){ //if the return value is not null
            JOptionPane.showMessageDialog(this,
                    "Welcome, " + logInCustomer.getName() + "!",
                    "Login Success", JOptionPane.INFORMATION_MESSAGE);
            
            CustomerMainMenu mainMenu = new CustomerMainMenu(logInCustomer);
            mainMenu.setVisible(true);
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(this, 
                    "Invalid Username or IC Number!",
                    "Login Fail", JOptionPane.ERROR_MESSAGE);
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField1.requestFocus();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

   
        
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        jTextField2.requestFocus(); //after the user press enter, it will auto direct user to jTextField2
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        jButton1.doClick();//after the user press enter, it will auto click the button
    }//GEN-LAST:event_jTextField2ActionPerformed

    private Customer authentication(String InputUsername, String InputIC){ //need to declare the variable type again as the initial one if a local variable, meaning other methods cannot access it
        try(BufferedReader br = new BufferedReader(new FileReader("Customers.txt"))){
            String line;
            while ((line = br.readLine())!=null){ //read file line by line and stops when the file reach null
                line = line.trim(); //remove any white spaces
                if (line.isEmpty()) continue; //check whether the line is empty. if yes, skip it and continue to the next one
                String[] parts = line.split("\\|");//split the line into small parts when encounter "|"
                if (parts.length == 6){ //making sure that there is 6 field in the txt file
                    String CusID = parts[0].trim();
                    String CusName = parts[1].trim();
                    String CusGender = parts[2].trim();
                    String DOB = parts[3].trim();
                    String CusContact = parts[4].trim();
                    String CusIC = parts[5].trim(); //assigning each parts[] with a variable in the txt file
                    
                    if (InputUsername.equalsIgnoreCase(CusName)&& InputIC.equals(CusIC)){ // if the credentials matches
                        return new Customer(CusID, CusName, CusGender, DOB, CusContact, CusIC);//a new Customer object and return the variable
                    }
                }
            }        
        }catch (IOException e){
            logger.log(java.util.logging.Level.SEVERE, "Failed to read Customers.txt", e);
            JOptionPane.showMessageDialog(this,"Error in reading Customers.txt file",
                                                "File Error",   
                                                JOptionPane.ERROR_MESSAGE);
        }

        return null; //if no match is found or no file, return the value null
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
