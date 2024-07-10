/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chath
 */
public class DBUpdate {
    
    Statement stmt;      
    
    public boolean updateBook(String isbn, String name, String edition, String author, String genre,int totalCopies, int noAvailableCopies, int year){
        boolean status = false;
        String query = String.format("UPDATE book SET book_name='%s', edition='%s', author='%s', genre='%s', total_copies='%d', no_available_copies='%d', issued_year='%d' "
                + "WHERE isbn='%s'", name, edition, author, genre, totalCopies, noAvailableCopies, year, isbn);
        try {
            stmt = DBConnection.getStatementConnection();
            status = stmt.executeUpdate(query) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DBInsert.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public boolean updatePatron(String patronId, String name, String nic, String birthDay, String address, String telePhone, String gender){
        boolean status = false;
        String query = String.format("UPDATE patron SET full_name='%s', nic='%s', birthday='%s', address='%s', telephone='%s', gender='%s'"
                + "WHERE patron_id='%s'", name,nic, birthDay, address, telePhone, gender, patronId);
        try {
            stmt = DBConnection.getStatementConnection();
            status = stmt.executeUpdate(query) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DBInsert.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public boolean updateAvailbleBookCount(String isbn, int newValue){
        boolean status = false;
        String query = String.format("UPDATE book SET no_available_copies= %d WHERE isbn='%s'", newValue, isbn);
        try {
            stmt = DBConnection.getStatementConnection();
            status = stmt.executeUpdate(query) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DBInsert.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public boolean updateFine(double amount, String patronId){
        boolean status = false;
        String query = String.format("UPDATE lend SET fine= %.2f WHERE patron_id='%s'", amount, patronId);
        try {
            stmt = DBConnection.getStatementConnection();
            status = stmt.executeUpdate(query) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DBInsert.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public boolean updateLibrarian(String fullName, String nic, String address, String telePhone, double hourlyRate, String gender, String username, String password, String privilege){
        boolean status = false;
        String query = String.format("UPDATE librarian SET full_name='%s', nic='%s', address='%s', tp_number='%s', hourly_rate='%f', gender='%s', password='%s', privilege='%s' "
                + "WHERE username='%s'", fullName, nic, address, telePhone, hourlyRate, gender, password, privilege, username);
        try {
            stmt = DBConnection.getStatementConnection();
            status = stmt.executeUpdate(query) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DBInsert.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
}
