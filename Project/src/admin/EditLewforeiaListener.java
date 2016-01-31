package admin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;


public class EditLewforeiaListener implements ActionListener {
	private static JFrame frame;
	private static JTable tableLewforeia = AdminMainFrame.getTableLewforeia();
	private static JPanel panelLewforeia;
	private static JCheckBox wifiBox = new JCheckBox("Wifi");
	private static JCheckBox tvBox = new JCheckBox("TV");
	private static JCheckBox wcBox = new JCheckBox("WC");
	private static int row, col;
	private static String oldValue, newValue;
	private static int numNewValue;
	
	public void actionPerformed(ActionEvent e) {
		  if (tableLewforeia.getSelectedRow() != -1) {
		   initialize();
		   panelLewforeia = new JPanel();
		         panelLewforeia.add(wifiBox);
		         panelLewforeia.add(tvBox);
		         panelLewforeia.add(wcBox);
		   row = tableLewforeia.getSelectedRow();
		   col = tableLewforeia.getSelectedColumn();
		   oldValue = (String) tableLewforeia.getValueAt(row,col);
		   if (col == 0){
		    newValue = JOptionPane.showInputDialog(frame, "Νέα Τιμή:",oldValue);
		    if(newValue == null || newValue.isEmpty()){
		     JOptionPane.showMessageDialog(frame, "Δεν δώσατε τιμή!", "Warning", JOptionPane.WARNING_MESSAGE);
		     return;
		    }
		   }
		   if (col == 1){
		    try{
		     newValue = JOptionPane.showInputDialog(frame, "Νέα Τιμή:",oldValue);
		     numNewValue = Integer.parseInt(newValue);
		          }catch (NumberFormatException ex) {
		           JOptionPane.showMessageDialog(frame, "Δεν δώσατε αριθμό!", "Warning", JOptionPane.WARNING_MESSAGE);
		           return;
		          }
		   }
		   if (col == 2){
		    int result = JOptionPane.showConfirmDialog(frame, panelLewforeia, "Επιλογή παροχών:", JOptionPane.OK_CANCEL_OPTION);
		    if (result == JOptionPane.OK_OPTION){
		               if (wifiBox.isSelected())
		                newValue = "Wifi";
		               if (tvBox.isSelected())
		                if (newValue.isEmpty())
		                 newValue = "TV";
		                else
		                 newValue = newValue+",TV";
		               if (wcBox.isSelected())
		                if (newValue.isEmpty())
		                 newValue = "WC";
		                else
		                 newValue = newValue+",WC";
		    }
		   }
		        AdminLewforeia.updateLewforeia();
		  }
		  
		 }
	
	public static int getEditColumn(){
		return col;
	}
	
	public static int getEditRow(){
		return row;
	}
	
	public static String getNewValue(){
		return newValue;
	}
	
	public void initialize(){
		wifiBox.setSelected(false);
        tvBox.setSelected(false);
        wcBox.setSelected(false);
	}
}
