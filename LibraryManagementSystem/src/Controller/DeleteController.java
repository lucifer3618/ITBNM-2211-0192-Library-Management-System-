/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DBDelete;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author chath
 */
public class DeleteController {
    
    public void deleteBookFromTable(JTable jTable){
        int selectedRow = jTable.getSelectedRow();
        if(selectedRow != -1){
            String isbn = (String)jTable.getValueAt(selectedRow, 0);
            
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                boolean result = new DBDelete().deleteBook(isbn);
                if(result){
                    JOptionPane.showMessageDialog(new JFrame(), "Sucessfully Deleted!");
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Error When Deleting!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "Please Select a Row!", "Error", JOptionPane.ERROR_MESSAGE);
        }  
    }
    
    public void deletePatronFromTable(JTable jTable){
        int selectedRow = jTable.getSelectedRow();
        if(selectedRow != -1){
            String patronId = (String)jTable.getValueAt(selectedRow, 0);
            
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                boolean result = new DBDelete().deletePatron(patronId);
                if(result){
                    JOptionPane.showMessageDialog(new JFrame(), "Sucessfully Deleted!");
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Error When Deleting!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "Please Select a Row!", "Error", JOptionPane.ERROR_MESSAGE);
        }  
    }
    
    public void returnBook(JTable jTable){
        int selectedRow = jTable.getSelectedRow();
        if(selectedRow != -1){
            String lendId = (String)jTable.getValueAt(selectedRow, 0);
            String isbn = (String)jTable.getValueAt(selectedRow, 2);
            System.out.println(isbn);
            
            int status = new UpdateController().increaseAvailableBookCount(isbn);
            
            if(status == 1){
                boolean result = new DBDelete().deleteLend(lendId);
                if(result){
                    JOptionPane.showMessageDialog(new JFrame(), "Book Returned Sucessfully!");
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Error When Returning Book!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "Please Select a Row!", "Error", JOptionPane.ERROR_MESSAGE);
        }  
    }
    
    public void deleteLibrarianFromTable(JTable jTable){
        int selectedRow = jTable.getSelectedRow();
        if(selectedRow != -1){
            String username = (String)jTable.getValueAt(selectedRow, 0);
            
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                boolean result = new DBDelete().deleteLibrarian(username);
                if(result){
                    JOptionPane.showMessageDialog(new JFrame(), "Sucessfully Deleted!");
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Error When Deleting!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "Please Select a Row!", "Error", JOptionPane.ERROR_MESSAGE);
        }  
    }
   
    
}
