package Tamias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class GetInformation {
	
	final static String newline = "\n";
	public static String text;
	public static JTextArea textarea;
	private static String strQuery;
	private static PreparedStatement pS;
	private static Connection conn;
	private static ResultSet rS;
	public static JDialog dialog;
	public static JOptionPane optionPane;
	public GetInformation()
	{
		textarea = new JTextArea();
		conn = OpenConnection.getConnection();
		strQuery="SELECT Keimeno FROM anakoinwseis";
        try{
        pS = conn.prepareStatement(strQuery);
        rS = pS.executeQuery();
        	while(rS.next())
        	{
        		textarea.append(rS.getString("Keimeno")+newline);
        	}
        	optionPane = new JOptionPane(textarea.getText(), JOptionPane.INFORMATION_MESSAGE);    
			dialog = optionPane.createDialog("Information");
			dialog.setVisible(true);
        }catch(Exception e1){
        	e1.printStackTrace();
        }
	}
}
