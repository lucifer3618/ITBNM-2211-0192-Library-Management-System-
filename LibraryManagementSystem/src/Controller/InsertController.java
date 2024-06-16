/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DBConnection;
import Model.DBInsert;
import Model.DBSearch;
import com.toedter.calendar.JDateChooser;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author chath
 */
public class InsertController {
    
    public void insertPatron(String patron_id, String fullName, String nic, JDateChooser jDateChooser, String address, String telephone, String gender){
        try{
            if(patron_id.equals("") || fullName.equals("") || nic.equals("") || jDateChooser==null || address.equals("") || telephone.equals("") || gender.equals("")){
                JOptionPane.showMessageDialog(null, "Some fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                ResultSet rs = new DBSearch().searchPatronByID(patron_id);
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "Patron ID Already Exsist!", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(jDateChooser.getDate());
                    DBInsert DBI = new DBInsert();
                    boolean status = DBI.addPatron(patron_id, fullName, nic, formattedDate, address, telephone, gender);
                    if(status){
                        JOptionPane.showMessageDialog(null, "Patron added successfully!");
                    }else{
                        JOptionPane.showMessageDialog(new JFrame(), "Error Adding Patron!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                
            }
        }
        catch (SQLException ex) {
                Logger.getLogger(InsertController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBConnection.closeCon();
        }
    }
     
    public void insertBook(String isbn, String name, String edition, String author, String genre,int totalCopies, int noAvailableCopies, int issued_year){
        try{
            if(isbn.equals("") || name.equals("") || edition.equals("") || author.equals("") || totalCopies==0 || noAvailableCopies==0 || issued_year==0){
                JOptionPane.showMessageDialog(null, "Some fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                ResultSet rs = new DBSearch().searchBookByISBN(isbn);
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "Book ID Already Exsist!", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    DBInsert DBI = new DBInsert();
                    boolean status = DBI.addBook(isbn, name, edition, author, genre, totalCopies, noAvailableCopies, issued_year);
                    if(status){
                        JOptionPane.showMessageDialog(null, "Book added successfully!");
                    }else{
                        JOptionPane.showMessageDialog(new JFrame(), "Error Adding Book!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        catch (SQLException ex) {
                Logger.getLogger(InsertController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBConnection.closeCon();
        }
    }
    
    public void lendBook(JComboBox patronIdCB, JComboBox isbnCB, JDateChooser lendDate, JTextField dueDateTF){
        String selectedPatronId = (String)patronIdCB.getSelectedItem();
        String selectedISBN = (String)isbnCB.getSelectedItem();
        Date currentDate = lendDate.getDate();
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("MMM dd, yyyy");
        String formattedLendDate = formatter1.format(currentDate);
        String dueDate = dueDateTF.getText();
        String formattedDueDate = "";
        try {
            formattedDueDate = formatter1.format(formatter2.parse(dueDate));
        } catch (ParseException ex) {
            Logger.getLogger(InsertController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(selectedPatronId.equals("") || selectedISBN.equals("") || lendDate==null || dueDateTF.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Some fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            ResultSet lend_id_rs = new DBSearch().searchLendByLendID(selectedPatronId+selectedISBN);
            try {
                if(lend_id_rs.next()){
                    JOptionPane.showMessageDialog(null, "Cannot lend more than one copy of a book!", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    ResultSet rs = new DBSearch().noOfBooksLendByPatron(selectedPatronId);
                    int rowCount = 0;
                    try {
                        while (rs.next()){
                            rowCount++;
                        }
                        if(rowCount>=2){
                            JOptionPane.showMessageDialog(new JFrame(), "Limit of borrowing books exceeded!", "Error", JOptionPane.ERROR_MESSAGE);
                        }else{
                            int status = new UpdateController().reduceAvailableBookCount(selectedISBN);
                            if(status == 1){
                                new DBInsert().addLend(selectedPatronId, selectedISBN, formattedLendDate, formattedDueDate);
                                JOptionPane.showMessageDialog(null, "Book Lended successfully!");
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(InsertController.class.getName()).log(Level.SEVERE, null, ex);
                    }finally{
                        DBConnection.closeCon();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(InsertController.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                DBConnection.closeCon();
            }  
        }
    }
    
    public void insertLibrarian(String fullName, String nic, String address, String telePhone, double hourlyRate, String gender, String username, String password, String privilege){
        try{
            if(fullName.equals("") || nic.equals("") || address.equals("") || telePhone.equals("") || hourlyRate==0.0 || gender.equals("") || username.equals("") || password.equals("") || privilege.equals("")){
                JOptionPane.showMessageDialog(null, "Some fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                ResultSet rs = new DBSearch().searchLibrarian(username);
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "User Already Exsist!", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    DBInsert DBI = new DBInsert();
                    boolean status = DBI.addLibrarian(fullName, nic, address, telePhone, hourlyRate, gender, username, password, privilege);
                    if(status){
                        JOptionPane.showMessageDialog(null, "Librarian added successfully!");
                    }else{
                        JOptionPane.showMessageDialog(new JFrame(), "Error Adding Librarian!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        catch (SQLException ex) {
                Logger.getLogger(InsertController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBConnection.closeCon();
        }
    }
    
}
