/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chath
 */
public class DBInsert {
    
    Statement stmt;
    ResultSet rs;
    
    public boolean addPatron(String patronId, String fullName, String nic, String bDate, String address, String telephone, String gender){
        boolean status = false;
        String query = String.format("INSERT INTO patron (patron_id, full_name, nic, birthday, address, telephone, gender) VALUES "
                + "('%s', '%s', '%s', '%s', '%s', '%s', '%s')", patronId, fullName, nic, bDate, address, telephone, gender);
        try {
            stmt = DBConnection.getStatementConnection();
            status = stmt.executeUpdate(query) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DBInsert.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public boolean addBook(String isbn, String name, String edition, String author, String genre,int totalCopies, int noAvailableCopies, int year){
        boolean status = false;
        String query = String.format("INSERT INTO book (isbn, book_name, edition, author, genre, total_copies, no_available_copies, issued_year) VALUES "
                + "('%s', '%s', '%s', '%s', '%s', %d, '%d', '%d')", isbn, name, edition, author, genre, totalCopies, noAvailableCopies, year);
        try {
            stmt = DBConnection.getStatementConnection();
            status = stmt.executeUpdate(query) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DBInsert.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public boolean addLend(String patronId, String isbn, String lendDate, String dueDate){
        boolean status = false;
        String query = String.format("INSERT INTO lend (lend_id, isbn, patron_id, lended_date, due_date, fine) VALUES "
                + "('%s', '%s', '%s', '%s', '%s', NULL)", patronId+isbn, isbn, patronId, lendDate, dueDate);
        try {
            stmt = DBConnection.getStatementConnection();
            status = stmt.executeUpdate(query) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DBInsert.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public boolean addLibrarian(String fullName, String nic, String address, String telePhone, double hourlyRate, String gender, String username, String password, String privilege){
        boolean status = false;
        String query = String.format("INSERT INTO librarian (full_name, nic address, tp_number, hourly_rate, gender, username, password, privilege) VALUES "
                + "('%s', '%s', '%s', '%s', '%f', '%s', '%s', '%s', '%s')", fullName, nic, address, telePhone, hourlyRate, gender, username, password, privilege);
        try {
            stmt = DBConnection.getStatementConnection();
            status = stmt.executeUpdate(query) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DBInsert.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
}
