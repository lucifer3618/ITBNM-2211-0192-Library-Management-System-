/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DBConnection;
import Model.DBSearch;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author chath
 */
public class SearchController {
    
    public void searchBook(JComboBox jComboBox, JTextField jTextField, JTable jTable){
        int numOfRows = 0;
        ResultSet rs = null;
        DefaultTableModel tblModel = (DefaultTableModel)jTable.getModel();
        String searchBy = (String)jComboBox.getSelectedItem();
        try {
            if(jTextField.getText().equals("")){
            JOptionPane.showMessageDialog(new JFrame(), "Search Field Empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                switch(searchBy){
                    case "ISBN":
                        rs = new DBSearch().searchBookByISBN(jTextField.getText());
                        break;
                    case "Name":
                        rs = new DBSearch().searchBookByName(jTextField.getText());
                        break;
                    case "Author":
                        rs = new DBSearch().searchBookByAuthor(jTextField.getText());
                        break;
                }
                if(rs.last()){
                    numOfRows = rs.getRow();
                    rs.beforeFirst();
                }
                if(numOfRows <= 0){
                    JOptionPane.showMessageDialog(new JFrame(), "Didn't find any records!", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    tblModel.setRowCount(0);
                    while(rs.next()){
                        String isbn = rs.getString("isbn");
                        String book_name = rs.getString("book_name");
                        String author = rs.getString("author");
                        String totalNoCopies = rs.getString("total_copies");
                        String noOfAvailableCopies = rs.getString("no_available_copies");
                        String tbData[] = {isbn, book_name, author, totalNoCopies, noOfAvailableCopies};
                        tblModel.addRow(tbData);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DBConnection.closeCon();
        }
    }
    
    public void searchPatron(JComboBox jComboBox, JTextField jTextField, JTable jTable){
        int numOfRows = 0;
        ResultSet rs = null;
        DefaultTableModel tblModel = (DefaultTableModel)jTable.getModel();
        String searchBy = (String)jComboBox.getSelectedItem();
        try {
            if(jTextField.getText().equals("")){
            JOptionPane.showMessageDialog(new JFrame(), "Search Field Empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                switch(searchBy){
                    case "Patron Id":
                        rs = new DBSearch().searchPatronByID(jTextField.getText());
                        break;
                    case "NIC":
                        rs = new DBSearch().searchPatronNIC(jTextField.getText());
                        break;
                    case "Telephone":
                        rs = new DBSearch().searchPatronByTP(jTextField.getText());
                        break;
                }
                
                if(rs.last()){
                    numOfRows = rs.getRow();
                    rs.beforeFirst();
                }
                if(numOfRows <= 0){
                    JOptionPane.showMessageDialog(new JFrame(), "Didn't find any records!", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    tblModel.setRowCount(0);
                    while(rs.next()){
                        String patronId = rs.getString("patron_id");
                        String name = rs.getString("full_name");
                        String telephone = rs.getString("telephone");
                        String nic = rs.getString("nic");
                        String tbData[] = {patronId, name, nic, telephone};
                        tblModel.addRow(tbData);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DBConnection.closeCon();
        }
    }
    
    public void searchDropDown(String searchCategory, JTextField id, JComboBox dropDown){
        ResultSet rs;
        String insertedValue = id.getText().trim();
        if(searchCategory.equals("patronId")){
            if(insertedValue.isEmpty()){
                dropDown.removeAllItems();
                dropDown.setPopupVisible(false);
            }else{
                rs = new DBSearch().searchSimillerPatronByID(insertedValue);
                dropDown.removeAllItems();
                dropDown.setPopupVisible(false);

                if(dropDown.getItemCount()<=3){
                    try {
                        while(rs.next()){
                            String patronId = rs.getNString("patron_id");
                            dropDown.addItem(patronId);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    finally{
                        DBConnection.closeCon();
                    }
                }
                dropDown.setPopupVisible(true);
            }
        }else{
            if(insertedValue.isEmpty()){
                dropDown.removeAllItems();
                dropDown.setPopupVisible(false);
            }else{
                rs = new DBSearch().searchSimillerBookByISBN(insertedValue);
                dropDown.removeAllItems();
                dropDown.setPopupVisible(false);

                if(dropDown.getItemCount()<=3){
                    try {
                        while(rs.next()){
                            String isbn = rs.getNString("isbn");
                            dropDown.addItem(isbn);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    finally{
                        DBConnection.closeCon();
                    }
                }
                dropDown.setPopupVisible(true);
            }
        }
    }
    
    public void findNameBySelection(String searchCategory ,JComboBox dropDownCB, JTextField nameTF, JTextField id){
        ResultSet rs;
        String selection = (String)dropDownCB.getSelectedItem();
        String name="";
        
        if(searchCategory.equals("patronId")){
            try {
                rs = new DBSearch().searchPatronByID(selection);
                while(rs.next()){
                    name = rs.getString("full_name");
                }
            } catch (SQLException ex) {
                Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                DBConnection.closeCon();
            }
        }else{
            try {
                rs = new DBSearch().searchBookByISBN(selection);
                while(rs.next()){
                    name = rs.getString("book_name");
                }
            } catch (SQLException ex) {
                Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                DBConnection.closeCon();
            }
        }
        nameTF.setText(name);
        nameTF.setEditable(false);
    }
    
    public void searchLend(JComboBox jComboBox, JTextField jTextField, JTable jTable){
        ResultSet rs = null;
        int numOfRows = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        DefaultTableModel tblModel = (DefaultTableModel)jTable.getModel();
        String searchBy = (String)jComboBox.getSelectedItem();
        try {
            if(jTextField.getText().equals("")){
            JOptionPane.showMessageDialog(new JFrame(), "Search Field Empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                switch(searchBy){
                    case "Patron ID":
                        rs = new DBSearch().searchLendByPatronID(jTextField.getText());
                        break;
                    case "ISBN":
                        rs = new DBSearch().searchLendByISBN(jTextField.getText());
                        break;
                    case "Lend ID":
                        rs = new DBSearch().searchLendByLendID(jTextField.getText());
                        break;
                }
                
                if(rs.last()){
                    numOfRows = rs.getRow();
                    rs.beforeFirst();
                }
                if(numOfRows <= 0){
                    JOptionPane.showMessageDialog(new JFrame(), "Didn't find any records!", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    tblModel.setRowCount(0);
                    while(rs.next()){
                        String lendId = rs.getString("lend_id");
                        String patronId = rs.getString("patron_id");
                        String isbn = rs.getString("isbn");
                        Date lendedDate = rs.getDate("lended_date");
                        Date dueDate = rs.getDate("due_date");
                        Double fine = rs.getDouble("fine");
                        
                        String fineWithCurrency = String.format("Rs.%.2f", fine);
                        
                        String tbData[] = {lendId, patronId, isbn, formatter.format(lendedDate), formatter.format(dueDate), fineWithCurrency};
                        tblModel.addRow(tbData);
                    }  
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DBConnection.closeCon();
        }
    }
    
        public void searchLibrarian(JComboBox jComboBox, JTextField jTextField, JTable jTable){
        int numOfRows = 0;
        ResultSet rs = null;
        DefaultTableModel tblModel = (DefaultTableModel)jTable.getModel();
        String searchBy = (String)jComboBox.getSelectedItem();
        try {
            if(jTextField.getText().equals("")){
            JOptionPane.showMessageDialog(new JFrame(), "Search Field Empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                switch(searchBy){
                    case "Username":
                        rs = new DBSearch().searchLibrarianByUsername(jTextField.getText());
                        break;
                    case "Telephone":
                        rs = new DBSearch().searchLibrarianByTP(jTextField.getText());
                        break;
                    case "Privilege":
                        rs = new DBSearch().searchLibrarianByPrivilege(jTextField.getText());
                        break;
                }
                if(rs.last()){
                    numOfRows = rs.getRow();
                    rs.beforeFirst();
                }
                if(numOfRows <= 0){
                    JOptionPane.showMessageDialog(new JFrame(), "Didn't find any records!", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    tblModel.setRowCount(0);
                    while(rs.next()){
                        String username = rs.getString("username");
                        String fullname = rs.getString("full_name");
                        String telePhone = rs.getString("tp_number");
                        String privilege = rs.getString("privilege");

                        String tbData[] = {username, fullname, telePhone, privilege};
                        tblModel.addRow(tbData);
                    } 
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DBConnection.closeCon();
        }
    }
        
    public void searchAddingDropDown(String searchCategory, JTextField id, JTextField status){
        ResultSet rs;
        String insertedValue = id.getText().trim();
        if(insertedValue.equals("")){
            status.setText(""); 
        } else{
            if(searchCategory.equals("patronId")){
                rs = new DBSearch().searchPatronByID(insertedValue);
                try {
                    if(rs.next()){
                        Color greenColor = new Color(255, 0, 0);
                        status.setForeground(greenColor);
                        status.setText("Patron Id Alreay Exsist!"); 
                    }else{
                        Color greenColor = new Color(50, 205, 50);
                        status.setForeground(greenColor);
                        status.setText("Patron Id Available"); 
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else{
                rs = new DBSearch().searchLibrarianByUsername(insertedValue);
                try {
                    if(rs.next()){
                        Color greenColor = new Color(255, 0, 0);
                        status.setForeground(greenColor);
                        status.setText("Username Alreay Exsist!"); 
                    }else{
                        Color greenColor = new Color(50, 205, 50);
                        status.setForeground(greenColor);
                        status.setText("Username Available"); 
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
