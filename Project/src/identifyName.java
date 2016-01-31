import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import Tamias.Main;


public class identifyName {
	
	private static Connection conn = null;
	private static String query;
	private static Statement statement;
	private static ResultSet rs;
	private static boolean access=false,usernameFound = false;
	
	public identifyName(String name, String pass)
	{

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ekdothria","root","dotaismind");
			query = "SELECT Username,Password from tamies";
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			while(rs.next())
			{
				if(rs.getString("Username").equals(name))
				{
					if(rs.getString("Password").equals(pass))
					{
						access = true;
					}
					else
						{
							JOptionPane.showMessageDialog(null, "Wrong password.","Access dinied", JOptionPane.ERROR_MESSAGE);
						}
				usernameFound = true;
				}
			}
			if(usernameFound == false)
			{
				JOptionPane.showMessageDialog(null, "Wrong username.","Access dinied", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
    		JOptionPane optionPane = new JOptionPane("Δεν μπόρεσε να συνδεθεί με την βάση.Ελέγξτε την βάση.", JOptionPane.ERROR_MESSAGE); 
    		JDialog dialog = optionPane.createDialog("Failure");
    		dialog.setAlwaysOnTop(true);
    		dialog.setVisible(true);
    		System.exit(0);
		}	
	}
	
	public static boolean getAccess()
	{
		return access;
	}
	public static Connection getConnection()
	{
		return conn;
	}
}
