package admin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;


public class DeleteDromologiaListener implements ActionListener {
	private static JFrame frame;
	private static JTable tableDromologia = AdminMainFrame.getTableDromologia();
	private static int row;
	
	public void actionPerformed(ActionEvent e) {
		if (tableDromologia.getSelectedRow() != -1) {
			int dialogResult = JOptionPane.showConfirmDialog (frame, "Είστε σίγουρος ότι θέλετε να διαγράψετε το συγκεκριμένο δρομολόγιο;","Διαγραφή δρομολογίου",JOptionPane.WARNING_MESSAGE);
            if(dialogResult == JOptionPane.YES_OPTION)
                row = tableDromologia.getSelectedRow();
			}
		AdminDromologia.deleteDromologia();
	}
	
	public static int getDeleteRow(){
		return row;
	}
}