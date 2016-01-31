package Tamias;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;


public class ChangeDataOfTo implements ItemListener {
	
	private static String strQuery;
	private static ResultSet rS;
	private static PreparedStatement pS;
	private static DefaultComboBoxModel<String> modelfrom,modelto;
	private static Connection conn;
	
	public ChangeDataOfTo(){
		modelfrom = MainFrame.getCityFrom();
		modelto = MainFrame.getCityTo();
		conn=OpenConnection.getConnection();
	}
	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		if(e.getStateChange() == ItemEvent.SELECTED)
		{
			modelto.removeAllElements();
			strQuery = "SELECT DISTINCT Proorismos FROM dromologia WHERE Afethria=?";
			try{
				pS = conn.prepareStatement(strQuery);
				pS.setString(1, modelfrom.getSelectedItem().toString());
				rS = pS.executeQuery();
					while(rS.next()){
						modelto.addElement(rS.getString("Proorismos"));
					}
			}catch(Exception e1){
				
			}				
		}
	}
	
}


