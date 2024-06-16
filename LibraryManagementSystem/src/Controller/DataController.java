/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.Toolkit;
import java.util.Date;
import javax.swing.JFrame;

/**
 *
 * @author chath
 */
public class DataController {
    
    public long getDateDifferenceInDays(Date date1, Date date2) {
        long diffInMilliSeconds = date2.getTime() - date1.getTime();
        long diffInDays = diffInMilliSeconds / (24 * 60 * 60 * 1000);
        return diffInDays;
    }
    
    public void setIcon(JFrame frame){
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/icon.png")));
    }
    
}
