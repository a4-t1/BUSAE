package admin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class AddAnnouncemement implements ActionListener {
	private static JFrame frame;
	private static String announcement;
	private static String query;
	private static Connection conn;
    private static ResultSet rs = null;
    private static PreparedStatement preparedStatement = null;
	
	public void actionPerformed(ActionEvent arg0) {
		announcement = JOptionPane.showInputDialog(frame, "Ανακοίνωση:","Προσθήκη ανακοίνωσης",JOptionPane.PLAIN_MESSAGE);
		  if(announcement == null || announcement.isEmpty()){
		         JOptionPane.showMessageDialog(frame, "Η ανακοίνωση είναι κενή!", "Warning", JOptionPane.WARNING_MESSAGE);
		         return;
		        
        }
		query = "INSERT INTO anakoinwseis VALUES(?,?)";
		try
		{
			conn = DbConnect.getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
        
			preparedStatement.setInt(1, 1);
			preparedStatement.setString(2, announcement);
            
			preparedStatement.executeUpdate();
        
		}
		catch (Exception f)
		{
			f.printStackTrace();
		}
		finally
		{
			try { if (preparedStatement != null) preparedStatement.close(); } catch (SQLException f) {
                f.printStackTrace(); }
		}
	}
}
