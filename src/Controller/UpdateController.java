/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DBConnection;
import Model.DBSearch;
import Model.DBUpdate;
import View.AdminMenu;
import View.UpdateBook;
import View.UpdateLibrarian;
import View.UpdatePatron;
import View.ViewAllBooks;
import View.ViewAllPatrons;
import View.ViewBook;
import View.ViewLibrarian;
import View.ViewPatron;
import com.toedter.calendar.JDateChooser;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class UpdateController {
    ResultSet rs;
    
    
    public void updateBooksTable(JTable jTable){
        rs = new DBSearch().searchAllBooks();
        try {
            DefaultTableModel tblModel = (DefaultTableModel)jTable.getModel();
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
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DBConnection.closeCon();
        }
    }
    
    public void updateBookFromViewAll(JTable jTable, JFrame frame){
        int selectedRow = jTable.getSelectedRow();
        if(selectedRow != -1){
            String isbnSelected = (String)jTable.getValueAt(selectedRow, 0);
            rs = new DBSearch().searchBookByISBN(isbnSelected);
            try {
                String totalCopies = "";
                String availableCopies="", isbn="", book_name="", edition="", author="", issued_year="", genre="";
                while(rs.next()){
                    totalCopies = rs.getString("total_copies");
                    availableCopies = rs.getString("no_available_copies");
                    isbn = rs.getString("isbn");
                    book_name = rs.getString("book_name");
                    edition = rs.getString("edition");
                    author = rs.getString("author");
                    genre = rs.getString("genre");
                    issued_year = rs.getString("issued_year");
                }
                UpdateBook updateBook = new UpdateBook();
                updateBook.getVariabesFromUpdate(totalCopies, availableCopies);
                updateBook.authorTF.setText(author);
                updateBook.bookNameTF.setText(book_name);
                updateBook.editionTF.setText(edition);
                updateBook.genreCB.setSelectedItem(genre);
                updateBook.issuedYearTF.setText(issued_year);
                updateBook.isbnTF.setText(isbn);
                updateBook.noOfCopiesTF.setText(totalCopies);
                
                updateBook.isbnTF.setEditable(false);
                updateBook.setVisible(true);
                frame.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(UpdateController.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                DBConnection.closeCon();
            }
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "Please Select a Row!", "Error", JOptionPane.ERROR_MESSAGE);
        }  
    }
    
    
    public void updateBook(String isbn, String name, String edition, String author, String genre,int totalCopies, int oldAvailableCopies, int oldTotalCopies, int issued_year, JFrame updateBookFrame){
        try{
            int difference = oldTotalCopies - oldAvailableCopies;
            int newAvailableCopies = totalCopies-difference;
            
            if(isbn.equals("") || name.equals("") || edition.equals("") || author.equals("") || totalCopies==0 || newAvailableCopies==0 || issued_year==0){
                JOptionPane.showMessageDialog(null, "Some fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                DBUpdate dBUpdate = new DBUpdate();
                boolean status = dBUpdate.updateBook(isbn, name, edition, author, genre, totalCopies, newAvailableCopies, issued_year);
                if(status){
                    JOptionPane.showMessageDialog(null, "Book Details Updated successfully!");
                    new ViewAllBooks().setVisible(true);
                    updateBookFrame.dispose();
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Error Updating Book!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            DBConnection.closeCon();
        }
    }
      
    public void updatePatronTable(JTable jTable){
        rs = new DBSearch().searchAllPatrons();
        try {
            DefaultTableModel tblModel = (DefaultTableModel)jTable.getModel();
            tblModel.setRowCount(0);
            while(rs.next()){
                        String patronId = rs.getString("patron_id");
                        String name = rs.getString("full_name");
                        String telephone = rs.getString("telephone");
                        String nic = rs.getString("nic");
                        String tbData[] = {patronId, name, nic, telephone};
                        tblModel.addRow(tbData);
                    }  
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DBConnection.closeCon();
        }
    }
     
    public void updatePatronFromViewAll(JTable jTable, JFrame frame) throws ParseException{
        int selectedRow = jTable.getSelectedRow();
        if(selectedRow != -1){
            String patronIdSelected = (String)jTable.getValueAt(selectedRow, 0);
            rs = new DBSearch().searchPatronByID(patronIdSelected);
            try {
                String patronId = "";
                String name="", nic="", birthDay="", address="", telephone="", gender="";
                while(rs.next()){
                    patronId = rs.getNString("patron_id");
                    name = rs.getNString("full_name");
                    nic = rs.getNString("nic");
                    birthDay = rs.getString("birthday");
                    address = rs.getNString("address");
                    telephone = rs.getNString("telephone");
                    gender = rs.getNString("gender");   
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(birthDay);
                
                UpdatePatron updatePatron = new UpdatePatron();
                updatePatron.patronIdTF.setText(patronId);
                updatePatron.fullNameTF.setText(name);
                updatePatron.nicTF.setText(nic);
                updatePatron.birthDayDC.setDate(date);
                updatePatron.addressTF.setText(address);
                updatePatron.telephoneTF.setText(telephone);
                updatePatron.genderCB.setSelectedItem(gender);
                
                updatePatron.patronIdTF.setEditable(false);
                
                updatePatron.setVisible(true);
                frame.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(UpdateController.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                DBConnection.closeCon();
            }
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "Please Select a Row!", "Error", JOptionPane.ERROR_MESSAGE);
        }  
    }
    
    public void updatePatron(String patronId, String name, String nic, JDateChooser birthDay, String address, String telePhone, String gender, JFrame frame){
        try{
            if(patronId.equals("") || name.equals("") || nic.equals("") || birthDay==null || address.equals("") || telePhone.equals("") || gender.equals("")){
                JOptionPane.showMessageDialog(null, "Some fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                String formattedBirthDate = new SimpleDateFormat("yyyy-MM-dd").format(birthDay.getDate());
                DBUpdate dBUpdate = new DBUpdate();
                boolean status = dBUpdate.updatePatron(patronId, name, nic, formattedBirthDate, address, telePhone, gender);
                if(status){
                    JOptionPane.showMessageDialog(null, "Patron Details Updated successfully!");
                    new ViewAllPatrons().setVisible(true);
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Error Updating Book!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            DBConnection.closeCon();
        }
    }
    
    public void viewBook(JTable jTable, JFrame frame) {
       int selectedRow = jTable.getSelectedRow();
       if (selectedRow != -1) {
           String isbnSelected = (String) jTable.getValueAt(selectedRow, 0);
           rs = new DBSearch().searchBookByISBN(isbnSelected);
           try {
               String totalCopies = "";
               String availableCopies = "";
               String isbn = "";
               String book_name = "";
               String edition = "";
               String author = "";
               String issued_year = "";
               String genre = "";
               while (rs.next()) {
                   totalCopies = rs.getString("total_copies");
                   availableCopies = rs.getString("no_available_copies");
                   isbn = rs.getString("isbn");
                   book_name = rs.getString("book_name");
                   edition = rs.getString("edition");
                   author = rs.getString("author");
                   genre = rs.getString("genre");
                   issued_year = rs.getString("issued_year");
               }
               ViewBook viewBook = new ViewBook();
               viewBook.authorTF.setText(author);
               viewBook.bookNameTF.setText(book_name);
               viewBook.editionTF.setText(edition);
               viewBook.genreCB.setSelectedItem(genre);
               viewBook.issuedYearTF.setText(issued_year);
               viewBook.isbnTF.setText(isbn);
               viewBook.noOfCopiesTF.setText(totalCopies);
               viewBook.isbnTF.setEditable(false);
               viewBook.authorTF.setEditable(false);
               viewBook.bookNameTF.setEditable(false);
               viewBook.editionTF.setEditable(false);
               viewBook.genreCB.setEnabled(false);
               viewBook.issuedYearTF.setEditable(false);
               viewBook.isbnTF.setEditable(false);
               viewBook.noOfCopiesTF.setEditable(false);
               viewBook.setVisible(true);
               frame.dispose();
           } catch (SQLException ex) {
               Logger.getLogger(UpdateController.class.getName()).log(Level.SEVERE, null, ex);
           }
           finally{
                DBConnection.closeCon();
            }
       } else {
           JOptionPane.showMessageDialog(new JFrame(), "Please Select a Row!", "Error", JOptionPane.ERROR_MESSAGE);
       }
   }

   public void addPattronClear(JTextField user_id, JTextField fullName, JTextField nic, JDateChooser bDate, JTextField address, JTextField telephone, JComboBox gender) {
       try {
           user_id.setText("");
           fullName.setText("");
           nic.setText("");
           bDate.setDate(null);
           address.setText("");
           telephone.setText("");
           gender.setSelectedIndex(0);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   
   public void addLibrarianClear(JTextField fullname, JTextField nic, JTextField address, JTextField telephone, JComboBox gender, JTextField hourlyRate, JComboBox privilege, JTextField username, JTextField password){
       fullname.setText("");
       nic.setText("");
       address.setText("");
       telephone.setText("");
       gender.setSelectedIndex(0);
       hourlyRate.setText("");
       privilege.setSelectedIndex(0);
       username.setText("");
       password.setText("");
   }

   public void addBookClear(JTextField book_id, JTextField name, JTextField edition, JTextField author, JComboBox genre, JTextField noCopies, JTextField issued_year) {
       try {
           book_id.setText("");
           name.setText("");
           edition.setText("");
           author.setText("");
           genre.setSelectedIndex(0);
           noCopies.setText("");
           issued_year.setText("");
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   
   public void viewPatron(JTable jTable, JFrame frame) throws ParseException {
       int selectedRow = jTable.getSelectedRow();
       if (selectedRow != -1) {
           String patronIdSelected = (String) jTable.getValueAt(selectedRow, 0);
           rs = new DBSearch().searchPatronByID(patronIdSelected);
           try {
               String patronId="", name="", nic="", birthDay="", address="", telephone="", gender="";
               while (rs.next()) {
                    patronId = rs.getString("patron_id");
                    name = rs.getNString("full_name");
                    nic = rs.getNString("nic");
                    birthDay = rs.getString("birthday");
                    address = rs.getNString("address");
                    telephone = rs.getNString("telephone");
                    gender = rs.getNString("gender");   
               }
               SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
               Date date = format.parse(birthDay);
               
               ViewPatron viewPatron = new ViewPatron();
               
               viewPatron.patronIdTF.setText(patronId);
               viewPatron.fullNameTF.setText(name);
               viewPatron.nicTF.setText(nic);
               viewPatron.birthDayDC.setDate(date);
               viewPatron.addressTF.setText(address);
               viewPatron.telephoneTF.setText(telephone);
               viewPatron.genderCB.setSelectedItem(gender);
               
               viewPatron.patronIdTF.setEditable(false);
               viewPatron.fullNameTF.setEditable(false);
               viewPatron.nicTF.setEditable(false);
               viewPatron.telephoneTF.setEditable(false);
               viewPatron.genderCB.setEnabled(false);
               viewPatron.birthDayDC.setEnabled(false);
               
               viewPatron.setVisible(true);
               frame.dispose();
           } catch (SQLException ex) {
               Logger.getLogger(UpdateController.class.getName()).log(Level.SEVERE, null, ex);
           }
           finally{
                DBConnection.closeCon();
            }
       } else {
           JOptionPane.showMessageDialog(new JFrame(), "Please Select a Row!", "Error", JOptionPane.ERROR_MESSAGE);
       }
   }
   
   public void updateDateFromLendConstructor(JDateChooser lendDateDC, JTextField dueDateTF, int lendedPeriod){
        Date lendDate = new Date();
        Calendar calendar = Calendar.getInstance();   
        calendar.setTime(lendDate);
        calendar.add(Calendar.DAY_OF_MONTH, lendedPeriod);
        Date dueDate = calendar.getTime();
        
        lendDateDC.setDate(lendDate);
        
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
        String formattedDueDate = sdf.format(dueDate);
        dueDateTF.setText(formattedDueDate);
        dueDateTF.setEditable(false);  
   }
   
   public void updateDateInLend(JDateChooser lendDateDC, JTextField dueDateTF, int lendedPeriod){
       Date lendDate;
        if(lendDateDC.getDate()==null){
            lendDate = new Date();
        }else{
            lendDate = lendDateDC.getDate();
        }
        Calendar calendar = Calendar.getInstance();   
        calendar.setTime(lendDate);
        calendar.add(Calendar.DAY_OF_MONTH, lendedPeriod);
        Date dueDate = calendar.getTime();
        
        lendDateDC.setDate(lendDate);
        
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
        String formattedDueDate = sdf.format(dueDate);
        dueDateTF.setText(formattedDueDate);
        dueDateTF.setEditable(false);  
   }
   
   public int reduceAvailableBookCount(String isbn){
       ResultSet rs;
       int status = 0;
       int noAvailableBooks = 0, total_copies = 0;
       rs = new DBSearch().searchBookByISBN(isbn);
        try {
            while(rs.next()){
                noAvailableBooks = rs.getInt("no_available_copies");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBConnection.closeCon();
        }
        if(noAvailableBooks > 0){
            int newNoAvailableBooks =  noAvailableBooks - 1;
            new DBUpdate().updateAvailbleBookCount(isbn, newNoAvailableBooks);
            status = 1;
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "Book not Available!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return status;
   }
   
   public int increaseAvailableBookCount(String isbn){
       int status = 0;
       ResultSet book_rs;
       int noAvailableBooks = 0, total_copies = 0;
       book_rs = new DBSearch().searchBookByISBN(isbn);
        try {
            while(book_rs.next()){
                noAvailableBooks = book_rs.getInt("no_available_copies");
                total_copies = book_rs.getInt("total_copies");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBConnection.closeCon();
        }
        if(total_copies > noAvailableBooks){
            int newNoAvailableBooks =  noAvailableBooks + 1;
            new DBUpdate().updateAvailbleBookCount(isbn, newNoAvailableBooks);
            status = 1;
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "Incorrect Function!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return status;
   }
   
   public void updateBorrowedBooksTable(JTable jTable){
       int selectedRow = View.ViewAllPatrons.patronsTable.getSelectedRow();
       String patronId = (String)View.ViewAllPatrons.patronsTable.getValueAt(selectedRow, 0);
       rs = new DBSearch().getBorrowedBooks(patronId);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            DefaultTableModel tblModel = (DefaultTableModel)jTable.getModel();
            tblModel.setRowCount(0);
            while(rs.next()){
                String isbn = rs.getNString("isbn");
                String bookName = rs.getNString("book_name");
                Date borrowedDate = rs.getDate("lended_date");
                Date dueDate = rs.getDate("due_date");
                
                String formattedBorrowedDate = formatter.format(borrowedDate);
                String formattedDueDate = formatter.format(dueDate);

                String tbData[] = {isbn, bookName, formattedBorrowedDate, formattedDueDate};
                tblModel.addRow(tbData);
            }   
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBConnection.closeCon();
        }
   }
   
   public void updateAllFines(){
       Date currentDate = new Date();
       rs = new DBSearch().searchAllLends();
       try {
            while(rs.next()){
                String patronID = rs.getString("patron_id");
                Date dueDate = rs.getDate("due_date");
                long dateDifference = new DataController().getDateDifferenceInDays(dueDate, currentDate);
                if(dateDifference > 0){
                    double fine = dateDifference * 10;
                    new DBUpdate().updateFine(fine, patronID);
                }else{
                    new DBUpdate().updateFine(0, patronID);
                }        
                        
            }
       } catch (SQLException ex) {
            Logger.getLogger(UpdateController.class.getName()).log(Level.SEVERE, null, ex);
       }finally{
            DBConnection.closeCon();
        }
   }
   
   public void updateLendTable(JTable jTable){
       rs = new DBSearch().searchAllLends();
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            DefaultTableModel tblModel = (DefaultTableModel)jTable.getModel();
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
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBConnection.closeCon();
        }
   }
   
   public void updateLibrarianTable(JTable jTable){
        rs = new DBSearch().searchAllLibrarians();
        try {
            DefaultTableModel tblModel = (DefaultTableModel)jTable.getModel();
            tblModel.setRowCount(0);
            while(rs.next()){
                String username = rs.getString("username");
                String fullname = rs.getString("full_name");
                String telePhone = rs.getString("tp_number");
                String privilege = rs.getString("privilege");

                String tbData[] = {username, fullname, telePhone, privilege};
                tblModel.addRow(tbData);
            }   
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DBConnection.closeCon();
        }
    }
   
   public void viewLibrarian(JTable jTable, JFrame frame){
       int selectedRow = jTable.getSelectedRow();
       if (selectedRow != -1) {
            String usernameSelected = (String) jTable.getValueAt(selectedRow, 0);
            rs = new DBSearch().searchLibrarianByUsername(usernameSelected);
           try {
               String username="", name="", nic="", address="", telephone="", gender="", password="", privilege="", hourlyRate="";
               while (rs.next()) {
                    username = rs.getString("username");
                    password = rs.getString("password"); 
                    name = rs.getString("full_name");
                    hourlyRate = rs.getString("hourly_rate");
                    privilege = rs.getString("privilege");
                    nic = rs.getString("nic");
                    address = rs.getString("address");
                    telephone = rs.getString("tp_number");
                    gender = rs.getString("gender");   
               }
               
               ViewLibrarian viewLibrarian = new ViewLibrarian();
               
               viewLibrarian.usernameTF.setText(username);
               viewLibrarian.passwordTF.setText(password);
               viewLibrarian.hourlyRateTF.setText(hourlyRate);
               viewLibrarian.fullNameTF.setText(name);
               viewLibrarian.nicTF.setText(nic);
               viewLibrarian.addressTF.setText(address);
               viewLibrarian.telephoneTF.setText(telephone);
               viewLibrarian.genderCB.setSelectedItem(gender);
               viewLibrarian.privilegeCB.setSelectedItem(privilege);
               
               viewLibrarian.passwordTF.setEditable(false);
               viewLibrarian.addressTF.setEditable(false);
               viewLibrarian.usernameTF.setEditable(false);
               viewLibrarian.fullNameTF.setEditable(false);
               viewLibrarian.nicTF.setEditable(false);
               viewLibrarian.telephoneTF.setEditable(false);
               viewLibrarian.genderCB.setEnabled(false);
               viewLibrarian.privilegeCB.setEnabled(false);
               viewLibrarian.hourlyRateTF.setEditable(false);
               
               viewLibrarian.setVisible(true);
               frame.dispose();
           } catch (SQLException ex) {
               Logger.getLogger(UpdateController.class.getName()).log(Level.SEVERE, null, ex);
           }
           finally{
                DBConnection.closeCon();
            }
       } else {
           JOptionPane.showMessageDialog(new JFrame(), "Please Select a Row!", "Error", JOptionPane.ERROR_MESSAGE);
       }
   }
 
   public void updateLibrarianFromAdminMenu(JTable jTable, JFrame frame){
       int selectedRow = jTable.getSelectedRow();
       if (selectedRow != -1) {
            String usernameSelected = (String) jTable.getValueAt(selectedRow, 0);
            rs = new DBSearch().searchLibrarianByUsername(usernameSelected);
           try {
               String username="", name="", nic="", address="", telephone="", gender="", password="", privilege="", hourlyRate="";
               while (rs.next()) {
                    username = rs.getString("username");
                    password = rs.getString("password"); 
                    name = rs.getString("full_name");
                    hourlyRate = rs.getString("hourly_rate");
                    privilege = rs.getString("privilege");
                    nic = rs.getString("nic");
                    address = rs.getString("address");
                    telephone = rs.getString("tp_number");
                    gender = rs.getString("gender");   
               }
               
               UpdateLibrarian updateLibrarian = new UpdateLibrarian();
               
               updateLibrarian.usernameTF.setText(username);
               updateLibrarian.passwordTF.setText(password);
               updateLibrarian.hourlyRateTF.setText(hourlyRate);
               updateLibrarian.fullNameTF.setText(name);
               updateLibrarian.nicTF.setText(nic);
               updateLibrarian.addressTF.setText(address);
               updateLibrarian.telephoneTF.setText(telephone);
               updateLibrarian.genderCB.setSelectedItem(gender);
               updateLibrarian.privilegeCB.setSelectedItem(privilege);
               
               updateLibrarian.usernameTF.setEditable(false);
          
               updateLibrarian.setVisible(true);
               frame.dispose();
           } catch (SQLException ex) {
               Logger.getLogger(UpdateController.class.getName()).log(Level.SEVERE, null, ex);
           }
           finally{
                DBConnection.closeCon();
            }
       } else {
           JOptionPane.showMessageDialog(new JFrame(), "Please Select a Row!", "Error", JOptionPane.ERROR_MESSAGE);
       }
   }
   
    public void updateLibrarian(String fullName, String nic, String address, String telePhone, double hourlyRate, String gender, String username, String password, String privilege, JFrame frame){
         if(fullName.equals("") || nic.equals("") || address.equals("") || telePhone.equals("") || hourlyRate==0.0 || gender.equals("") || username.equals("") || password.equals("") || privilege.equals("")){
             JOptionPane.showMessageDialog(null, "Some fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
         }else{
            boolean status = new DBUpdate().updateLibrarian(fullName, nic, address, telePhone, hourlyRate, gender, username, password, privilege);
            if(status){
                JOptionPane.showMessageDialog(null, "Librarian updated successfully!");
                new AdminMenu().setVisible(true);
                frame.dispose();
            }else{
                JOptionPane.showMessageDialog(new JFrame(), "Error updating Librarian!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
   
}
