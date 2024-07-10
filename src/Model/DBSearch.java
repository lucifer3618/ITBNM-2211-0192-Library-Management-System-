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
public class DBSearch {
    
    Statement stmt;
    ResultSet rs;
    
    public ResultSet searchLibrarian(String usName){
        try{
            String query = String.format("SELECT * FROM librarian WHERE username = '%s'", usName);
            stmt = DBConnection.getStatementConnection();
            
            rs = stmt.executeQuery(query);
        }catch(SQLException ex){
            Logger.getLogger(DBSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet searchAllLibrarians(){
        try{
            String query = "SELECT * FROM librarian";
            stmt = DBConnection.getStatementConnection();
            rs = stmt.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(DBSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet searchAllBooks(){
        try{
            String query = "SELECT * FROM book";
            stmt = DBConnection.getStatementConnection();
            rs = stmt.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(DBSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet searchBookByISBN(String isbn){
        try{
            String query = String.format("SELECT * FROM book WHERE isbn = '%s'", isbn);
            stmt = DBConnection.getStatementConnection();
            rs = stmt.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(DBSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }  
    
    public ResultSet searchBookByName(String name){
        try{
            String query = String.format("SELECT * FROM book WHERE book_name = '%s'", name);
            stmt = DBConnection.getStatementConnection();
            rs = stmt.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(DBSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet searchBookByAuthor(String author){
        try{
            String query = String.format("SELECT * FROM book WHERE author = '%s'", author);
            stmt = DBConnection.getStatementConnection();
            rs = stmt.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(DBSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet searchAllPatrons(){
        try{
            String query = "SELECT * FROM patron";
            stmt = DBConnection.getStatementConnection();
            rs = stmt.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(DBSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet searchPatronByID(String patronId){
        try{
            String query = String.format("SELECT * FROM patron WHERE patron_id = '%s'", patronId);
            stmt = DBConnection.getStatementConnection();
            rs = stmt.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(DBSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet searchPatronNIC(String nic){
        try{
            String query = String.format("SELECT * FROM patron WHERE nic = '%s'", nic);
            stmt = DBConnection.getStatementConnection();
            rs = stmt.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(DBSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet searchPatronByTP(String telephone){
        try{
            String query = String.format("SELECT * FROM patron WHERE telephone = '%s'", telephone);
            stmt = DBConnection.getStatementConnection();
            rs = stmt.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(DBSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet searchSimillerPatronByID(String patronId){
        try{
            String query = String.format("SELECT * FROM patron WHERE UPPER(patron_id) LIKE '%%%s%%'", patronId.toUpperCase());
            stmt = DBConnection.getStatementConnection();
            rs = stmt.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(DBSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet searchSimillerBookByISBN(String isbn){
        try{
            String query = String.format("SELECT * FROM book WHERE UPPER(isbn) LIKE '%%%s%%'", isbn.toUpperCase());
            stmt = DBConnection.getStatementConnection();
            rs = stmt.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(DBSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet noOfBooksLendByPatron(String patronId){
        try{
            String query = String.format("SELECT * FROM lend WHERE patron_id = '%s'", patronId);
            stmt = DBConnection.getStatementConnection();
            rs = stmt.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(DBSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet getBorrowedBooks(String patronId){
        try{
            String query = String.format("SELECT lended_date, due_date, lend.isbn, book_name FROM lend " +
                                         "INNER JOIN book ON lend.isbn = book.isbn " +
                                         "WHERE lend.patron_id = '%s'", patronId);
            stmt = DBConnection.getStatementConnection();
            rs = stmt.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(DBSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet searchAllLends(){
        try{
            String query = String.format("SELECT * FROM lend");
            stmt = DBConnection.getStatementConnection();
            rs = stmt.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(DBSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet searchLendByPatronID(String patronId){
        try{
            String query = String.format("SELECT * FROM lend WHERE patron_id='%s'", patronId);
            stmt = DBConnection.getStatementConnection();
            rs = stmt.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(DBSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet searchLendByISBN(String isbn){
        try{
            String query = String.format("SELECT * FROM lend WHERE isbn='%s'", isbn);
            stmt = DBConnection.getStatementConnection();
            rs = stmt.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(DBSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet searchLendByLendID(String lendId){
        try{
            String query = String.format("SELECT * FROM lend WHERE lend_id='%s'", lendId);
            stmt = DBConnection.getStatementConnection();
            rs = stmt.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(DBSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet searchLibrarianByUsername(String username){
        try{
            String query = String.format("SELECT * FROM librarian WHERE username = '%s'", username);
            stmt = DBConnection.getStatementConnection();
            rs = stmt.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(DBSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet searchLibrarianByTP(String telephone){
        try{
            String query = String.format("SELECT * FROM librarian WHERE tp_number = '%d'", Integer.parseInt(telephone));
            stmt = DBConnection.getStatementConnection();
            rs = stmt.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(DBSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet searchLibrarianByPrivilege(String privilege){
        try{
            String query = String.format("SELECT * FROM librarian WHERE privilege = '%s'", privilege);
            stmt = DBConnection.getStatementConnection();
            rs = stmt.executeQuery(query);
        }catch (SQLException ex) {
            Logger.getLogger(DBSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
}
