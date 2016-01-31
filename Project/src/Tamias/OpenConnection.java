package Tamias;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JDialog;
import javax.swing.JOptionPane;




public class OpenConnection {
	
	private static Connection conn = null;
	
    public OpenConnection(){ 
    	try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ekdothria","root","dotaismind");
    	} catch (Exception e) {
    		JOptionPane optionPane = new JOptionPane("Δεν μπόρεσε να συνδεθεί με την βάση.Ελέγξτε την βάση.", JOptionPane.ERROR_MESSAGE); 
    		JDialog dialog = optionPane.createDialog("Failure");
    		dialog.setAlwaysOnTop(true);
    		dialog.setVisible(true);
    		System.exit(0);
		}
    }
    public static Connection getConnection()
    {
    	return conn;
    }
    
}
