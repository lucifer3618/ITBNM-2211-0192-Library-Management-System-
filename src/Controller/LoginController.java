/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DBConnection;
import Model.DBSearch;
import View.AdminMenu;
import View.LibrarianMenu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author chath
 */
public class LoginController {
    
    ResultSet rs;
    
    public void librarianLogin(JTextField textField, JPasswordField passwordField, JFrame frame) throws SQLException{
        String usName = textField.getText();
        String passWord = passwordField.getText();
        
        String password = null;
        String username = null;

        try{
            rs = new DBSearch().searchLibrarian(usName);
            
            if(usName.equals("") || passWord.equals("")){
                JOptionPane.showMessageDialog(null, "Some fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                while(rs.next()){
                    username = rs.getString("username");
                    password = rs.getString("password");
                }
                if(username != null && password != null){
                    if(password.equals(passWord) && username.equals(usName)){
                        System.out.println("Login Successfull");
                        LibrarianMenu LBM = new LibrarianMenu();
                        LBM.welcomeTL.setText("Welcome, "+username+"!");
                        LBM.setVisible(true);
                        frame.dispose();
                        JOptionPane.showMessageDialog(null, "Loged in as " + usName);
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "Please check the cridentials", "Error", JOptionPane.ERROR_MESSAGE);
                        textField.setText("");
                        passwordField.setText("");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Please check the cridentials", "Error", JOptionPane.ERROR_MESSAGE);
                    textField.setText("");
                    passwordField.setText("");
                }
            }
        }
        catch(SQLException ex){
             Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DBConnection.closeCon();
        }
    }
    
    public void AdminLogin(JTextField textField, JPasswordField passwordField, JFrame frame){
        String usName = textField.getText();
        String passWord = passwordField.getText();
        
        String password = null;
        String username = null;
        String privilege = null;

        try{
            rs = new DBSearch().searchLibrarian(usName);
            
            if(usName.equals("") || passWord.equals("")){
                JOptionPane.showMessageDialog(null, "Some fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                while(rs.next()){
                    username = rs.getString("username");
                    password = rs.getString("password");
                    privilege = rs.getString("privilege");
                }
                if(username != null && password != null){
                    if(password.equals(passWord) && username.equals(usName)){
                        if(privilege.equals("Admin")){
                            new AdminMenu().setVisible(true);
                            JOptionPane.showMessageDialog(null, "Loged in as " + usName);
                            frame.dispose();
                        }else{
                            JOptionPane.showMessageDialog(null, "User doesn't have Admin privileges!", "Error", JOptionPane.ERROR_MESSAGE);
                            textField.setText("");
                            passwordField.setText("");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Please check the cridentials", "Error", JOptionPane.ERROR_MESSAGE);
                        textField.setText("");
                        passwordField.setText("");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Please check the cridentials", "Error", JOptionPane.ERROR_MESSAGE);
                    textField.setText("");
                    passwordField.setText("");
                }
            }
        }
        catch(SQLException ex){
             Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DBConnection.closeCon();
        }
    }
      
}
