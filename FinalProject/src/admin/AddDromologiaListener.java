package admin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AddDromologiaListener implements ActionListener{
	private static JFrame frame;
	private static JComboBox hourBox;
    private static JComboBox minBox;
    private static JComboBox secBox;
	private static JPanel panelDromologia;
	private static String[] hours = new String[24];
	private static String[] mins = new String[60];
	private static String[] secs = new String[60];
	private String[] availableBuses;
	private static String afethria;
	private static String proorismos;
	private static String wraAnaxwrhshs = null;
	private static String busName;
	
	public void actionPerformed(ActionEvent e) {
		initialize();
		afethria = JOptionPane.showInputDialog(frame, "Αφετηρία:","Προσθήκη δρομολογίου",JOptionPane.PLAIN_MESSAGE);
        if (afethria == null){
         JOptionPane.showMessageDialog(frame, "Δεν δώσατε τιμή!", "Warning", JOptionPane.WARNING_MESSAGE);
         return;
        }
        proorismos = JOptionPane.showInputDialog(frame, "Προορισμός:","Προσθήκη δρομολογίου",JOptionPane.PLAIN_MESSAGE);
        if (proorismos == null){
         JOptionPane.showMessageDialog(frame, "Δεν δώσατε τιμή!", "Warning", JOptionPane.WARNING_MESSAGE);
         return;
        }
        int result = JOptionPane.showConfirmDialog(frame, panelDromologia, "Ώρα Αναχώρησης (ΩΩ:ΛΛ:ΔΔ):", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION)
         wraAnaxwrhshs = hourBox.getSelectedItem()+":"+minBox.getSelectedItem()+":"+secBox.getSelectedItem();
        else {
         JOptionPane.showMessageDialog(frame, "Δεν δώσατε τιμή!", "Warning", JOptionPane.WARNING_MESSAGE);
      return;
        }
        availableBuses = AdminLewforeia.getAvailableBuses();
        busName = (String) JOptionPane.showInputDialog(frame, "Διαθέσιμα λεωφορεία:","Προσθήκη δρομολογίου", JOptionPane.PLAIN_MESSAGE, null, availableBuses, availableBuses[0]); 
        if (busName == null){
         JOptionPane.showMessageDialog(frame, "Δεν δώσατε τιμή!", "Warning", JOptionPane.WARNING_MESSAGE);
         return;
     }
        AdminDromologia.insertDromologia();
    }
	
	public static String getAfethria(){
		return afethria;
	}
	
	public static String getProorismo(){
		return proorismos;
	}
	
	public static String getWraAnaxwrhshs(){
		return wraAnaxwrhshs;
	}
	
	public static String getBusName(){
		return busName;
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
}
