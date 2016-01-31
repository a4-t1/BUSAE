package Tamias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;


public class GetData {
	
	private static String strQuery;
	private static Connection conn;
	private static DefaultComboBoxModel<String> modelfrom;
	private static ResultSet rS;
	private static PreparedStatement pS;
	
	public GetData()
	{
		conn = OpenConnection.getConnection();
		modelfrom=MainFrame.getCityFrom();
		
		
		strQuery="SELECT DISTINCT Afethria FROM dromologia";
        try{
        pS = conn.prepareStatement(strQuery);
        rS = pS.executeQuery();
        	while(rS.next())
        	{
        		modelfrom.addElement(rS.getString("Afethria"));
        	}
        }catch(Exception e1){
        	e1.printStackTrace();
        }
     }	
}
