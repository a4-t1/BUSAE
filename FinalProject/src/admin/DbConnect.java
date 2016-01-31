package admin;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class DbConnect {
	private static Connection conn = null;
	
	public DbConnect(){ 
		
    	try {
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
            String connectionUrl = "jdbc:mysql://localhost:3306/ekdothria";
            String connectionUser = "root";
            String connectionPassword = "dotaismind";
            conn = DriverManager.getConnection(connectionUrl, connectionUser,connectionPassword);
    	} catch (Exception e) {
    		JOptionPane optionPane = new JOptionPane("Open database", JOptionPane.ERROR_MESSAGE); 
    		JDialog dialog = optionPane.createDialog("Failure");
    		dialog.setAlwaysOnTop(true);
    		dialog.setVisible(true);
    		System.exit(0);
		}
    }   
	
	public static Connection getConnection(){
    	return conn;
    }
}
