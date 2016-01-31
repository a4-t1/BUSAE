package admin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;


public class DeleteLewforeiaListener implements ActionListener {
	private static JFrame frame;
	private static JTable tableLewforeia = AdminMainFrame.getTableLewforeia();
	private static int row;
	
	public void actionPerformed(ActionEvent arg0) {
		if (tableLewforeia.getSelectedRow() != -1) {
			int dialogResult = JOptionPane.showConfirmDialog (frame, "Είστε σίγουρος ότι θέλετε να διαγράψετε το συγκεκριμένο λεωφορείο;","Διαγραφή λεωφορείου",JOptionPane.WARNING_MESSAGE);
            if(dialogResult == JOptionPane.YES_OPTION)
                row = tableLewforeia.getSelectedRow();
			}
		AdminLewforeia.deleteLewforeia();
	}
	
	public static int getDeleteRow(){
		return row;
	}
}
