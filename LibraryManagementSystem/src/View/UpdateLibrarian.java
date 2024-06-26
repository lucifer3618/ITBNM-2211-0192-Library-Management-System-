/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.DataController;
import Controller.UpdateController;

/**
 *
 * @author chath
 */
public class UpdateLibrarian extends javax.swing.JFrame {

    /**
     * Creates new form AddPatrons
     */
    public UpdateLibrarian() {
        initComponents();
        new DataController().setIcon(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        patronIdTF3 = new javax.swing.JTextField();
        patronIdLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        closeLabel = new javax.swing.JLabel();
        minimizeLabel = new javax.swing.JLabel();
        topicLabel = new javax.swing.JLabel();
        UpdateBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        passwordTF = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        privilegeCB = new javax.swing.JComboBox<>();
        privilegeLabel = new javax.swing.JLabel();
        hourlyRateTF = new javax.swing.JTextField();
        hourlyRateLabel = new javax.swing.JLabel();
        genderCB = new javax.swing.JComboBox<>();
        genderLabel = new javax.swing.JLabel();
        telephoneLabel = new javax.swing.JLabel();
        telephoneTF = new javax.swing.JTextField();
        addressTF = new javax.swing.JTextField();
        addressLabel = new javax.swing.JLabel();
        nicLabel = new javax.swing.JLabel();
        nicTF = new javax.swing.JTextField();
        fullNameTF = new javax.swing.JTextField();
        fullNameLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        usernameTF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        patronIdTF3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        patronIdTF3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        patronIdLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        patronIdLabel3.setText("Patron ID:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255), 2));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 550));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close.png"))); // NOI18N
        closeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeLabelMouseClicked(evt);
            }
        });
        jPanel1.add(closeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(762, 15, -1, -1));

        minimizeLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/minimize.png"))); // NOI18N
        minimizeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeLabelMouseClicked(evt);
            }
        });
        jPanel1.add(minimizeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(726, 15, -1, -1));

        topicLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        topicLabel.setForeground(new java.awt.Color(0, 51, 255));
        topicLabel.setText("Update Librarian");
        jPanel1.add(topicLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

        UpdateBtn.setBackground(new java.awt.Color(255, 255, 255));
        UpdateBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/updateBtnSmall.png"))); // NOI18N
        UpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBtnActionPerformed(evt);
            }
        });
        jPanel1.add(UpdateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 470, -1, -1));

        backBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/back.png"))); // NOI18N
        backBtn.setContentAreaFilled(false);
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        jPanel1.add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 470, -1, -1));

        passwordTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        passwordTF.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel1.add(passwordTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 430, 241, -1));

        passwordLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        passwordLabel.setText("Password:");
        jPanel1.add(passwordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 430, -1, -1));

        privilegeCB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        privilegeCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Staff", "Admin" }));
        privilegeCB.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel1.add(privilegeCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 390, 136, -1));

        privilegeLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        privilegeLabel.setText("Privilege:");
        jPanel1.add(privilegeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, -1, -1));

        hourlyRateTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        hourlyRateTF.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel1.add(hourlyRateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, 241, -1));

        hourlyRateLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        hourlyRateLabel.setText("Hourly Rate:");
        jPanel1.add(hourlyRateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, -1, -1));

        genderCB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        genderCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        genderCB.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel1.add(genderCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, 136, -1));

        genderLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        genderLabel.setText("Gender:");
        jPanel1.add(genderLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, -1, -1));

        telephoneLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        telephoneLabel.setText("Telephone:");
        jPanel1.add(telephoneLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, -1, -1));

        telephoneTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        telephoneTF.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel1.add(telephoneTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, 241, -1));

        addressTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addressTF.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel1.add(addressTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, 241, -1));

        addressLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addressLabel.setText("Address: ");
        jPanel1.add(addressLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, -1, -1));

        nicLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nicLabel.setText("NIC Number:");
        jPanel1.add(nicLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, -1, -1));

        nicTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nicTF.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel1.add(nicTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 241, -1));

        fullNameTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fullNameTF.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel1.add(fullNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 241, -1));

        fullNameLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fullNameLabel.setText("Fullname: ");
        jPanel1.add(fullNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, -1, -1));

        usernameLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        usernameLabel.setText("Username:");
        jPanel1.add(usernameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, -1, -1));

        usernameTF.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        usernameTF.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel1.add(usernameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, 241, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/updateBackgroundBig.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255), 2));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLabelMouseClicked
        // TODO add your handling code here:
        dispose();
        System.exit(0);
    }//GEN-LAST:event_closeLabelMouseClicked

    private void minimizeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeLabelMouseClicked
        // TODO add your handling code here:
        this.setExtendedState(AddBooks.ICONIFIED);
    }//GEN-LAST:event_minimizeLabelMouseClicked

    private void UpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateBtnActionPerformed
        // TODO add your handling code here:
        new UpdateController().updateLibrarian(fullNameTF.getText(), nicTF.getText(), addressTF.getText(), telephoneTF.getText(), Double.parseDouble(hourlyRateTF.getText()), 
                (String)genderCB.getSelectedItem(), usernameTF.getText(), passwordTF.getText(), (String)privilegeCB.getSelectedItem(), this); 
    }//GEN-LAST:event_UpdateBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        new AdminMenu().setVisible(true);
        super.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UpdateLibrarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateLibrarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateLibrarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateLibrarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateLibrarian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton UpdateBtn;
    private javax.swing.JLabel addressLabel;
    public javax.swing.JTextField addressTF;
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel closeLabel;
    private javax.swing.JLabel fullNameLabel;
    public javax.swing.JTextField fullNameTF;
    public javax.swing.JComboBox<String> genderCB;
    private javax.swing.JLabel genderLabel;
    private javax.swing.JLabel hourlyRateLabel;
    public javax.swing.JTextField hourlyRateTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel minimizeLabel;
    private javax.swing.JLabel nicLabel;
    public javax.swing.JTextField nicTF;
    private javax.swing.JLabel passwordLabel;
    public javax.swing.JTextField passwordTF;
    private javax.swing.JLabel patronIdLabel3;
    private javax.swing.JTextField patronIdTF3;
    public javax.swing.JComboBox<String> privilegeCB;
    private javax.swing.JLabel privilegeLabel;
    private javax.swing.JLabel telephoneLabel;
    public javax.swing.JTextField telephoneTF;
    private javax.swing.JLabel topicLabel;
    private javax.swing.JLabel usernameLabel;
    public javax.swing.JTextField usernameTF;
    // End of variables declaration//GEN-END:variables
}
