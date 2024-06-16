/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chath
 */
public class DBConnection {
    static Connection conn;
    static Statement stmt = null;
 
    public static Statement getStatementConnection(){
        try {
            //Establish the connection
            String userName = "root";
            String passWord = "";
            String url = "jdbc:mysql://localhost:3306/library_management_system";
            conn = DriverManager.getConnection(url, userName, passWord);
            //Create the connection
            stmt = conn.createStatement(); 
        }
        catch (Exception e) {
            e.printStackTrace(); 
        }
        return stmt;
    }
    //Close the connection
    public static void closeCon(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}
