package admin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;


public class EditDromologiaListener implements ActionListener {
	private static JFrame frame;
	private static JTable tableDromologia = AdminMainFrame.getTableDromologia();
	private static JComboBox hourBox;
    private static JComboBox minBox;
    private static JComboBox secBox;
	private static JPanel panelDromologia;
	private static String[] hours = new String[24];
	private static String[] mins = new String[60];
	private static String[] secs = new String[60];
	private static int row,col;
	private static String oldValue, newValue;
	
	public void actionPerformed(ActionEvent arg0) {
		  initialize();
		  if (tableDromologia.getSelectedRow() != -1) {
		            row = tableDromologia.getSelectedRow();
		            col = tableDromologia.getSelectedColumn();
		            oldValue = (String) tableDromologia.getValueAt(row,col);
		            if (col == 2){
		             int result = JOptionPane.showConfirmDialog(frame, panelDromologia, "Ώρα Αναχώρησης (ΩΩ:ΛΛ:ΔΔ):", JOptionPane.OK_CANCEL_OPTION);
		                if (result == JOptionPane.OK_OPTION)
		                 newValue = hourBox.getSelectedItem()+":"+minBox.getSelectedItem()+":"+secBox.getSelectedItem();
		            }else
		             newValue = JOptionPane.showInputDialog(frame, "Νέα Τιμή:",oldValue); 
		            if(newValue == null || newValue.isEmpty()){
		    JOptionPane.showMessageDialog(frame, "Δεν δώσατε τιμή!", "Warning", JOptionPane.WARNING_MESSAGE);
		    return;
		   }
		            AdminDromologia.updateDromologia();
		   }
		  
		 }

	public void initialize(){
			for (int i=0; i<24; i++){
		    	if (i<10)
		    		hours[i] = "0"+(i);
		    	else
		    		hours[i] = Integer.toString(i);
		    }
		    for (int j=0; j<60; j++){
		    	if (j<10){
		    		mins[j] = "0"+(j);
		    		secs[j] = "0"+(j);
		    	}
		    	else{
		    		mins[j] = Integer.toString(j);
		    		secs[j] = Integer.toString(j);
		    	}
		    }
		    hourBox = new JComboBox(hours);
		    minBox = new JComboBox(mins);
		    secBox = new JComboBox(secs);
		    panelDromologia = new JPanel();
		    panelDromologia.add(hourBox);
		    panelDromologia.add(minBox);
		    panelDromologia.add(secBox);
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
}
