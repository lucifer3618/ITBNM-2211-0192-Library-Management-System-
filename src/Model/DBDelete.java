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
public class DBDelete {
        
    Statement stmt;
    
    public boolean deleteBook(String isbn){
        boolean result = false;
            String query = String.format("DELETE FROM book WHERE isbn = '%s'",isbn);
            stmt = DBConnection.getStatementConnection();
        try {
            result = stmt.executeUpdate(query) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DBDelete.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public boolean deletePatron(String patronId){
        boolean result = false;
            String query = String.format("DELETE FROM patron WHERE patron_Id = '%s'",patronId);
            stmt = DBConnection.getStatementConnection();
        try {
            result = stmt.executeUpdate(query) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DBDelete.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public boolean deleteLend(String lendId){
        boolean result = false;
            String query = String.format("DELETE FROM lend WHERE lend_id = '%s'",lendId);
            stmt = DBConnection.getStatementConnection();
        try {
            result = stmt.executeUpdate(query) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DBDelete.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public boolean deleteLibrarian(String username){
        boolean result = false;
            String query = String.format("DELETE FROM librarian WHERE username = '%s'",username);
            stmt = DBConnection.getStatementConnection();
        try {
            result = stmt.executeUpdate(query) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DBDelete.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
       
}
