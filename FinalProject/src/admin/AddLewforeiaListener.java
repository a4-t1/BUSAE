package admin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class AddLewforeiaListener implements ActionListener {
	private static JFrame frame;
	private static String onomaLewforeiou;
	private static String arithmosThesewn;
	private static String paroxes = null;
	private static int numArithmosThesewn;
	private static JPanel panelLewforeia;
	private static JCheckBox wifiBox = new JCheckBox("Wifi");
	private static JCheckBox tvBox = new JCheckBox("TV");
	private static JCheckBox wcBox = new JCheckBox("WC");
	
	public void actionPerformed(ActionEvent e) {
		initialize();
		panelLewforeia = new JPanel();
        panelLewforeia.add(wifiBox);
        panelLewforeia.add(tvBox);
        panelLewforeia.add(wcBox);
        onomaLewforeiou = JOptionPane.showInputDialog(frame, "Όνομα λεωφορείου:","Προσθήκη λεωφορείου",JOptionPane.PLAIN_MESSAGE);
        if (onomaLewforeiou == null){
         JOptionPane.showMessageDialog(frame, "Δεν δώσατε τιμή!", "Warning", JOptionPane.WARNING_MESSAGE);
         return;
        }
        arithmosThesewn = JOptionPane.showInputDialog(frame, "Αριθμός θέσεων:","Προσθήκη λεωφορείου",JOptionPane.PLAIN_MESSAGE);
        if (arithmosThesewn == null){
         JOptionPane.showMessageDialog(frame, "Δεν δώσατε τιμή!", "Warning", JOptionPane.WARNING_MESSAGE);
         return;
        }
        try{
            numArithmosThesewn = Integer.parseInt(arithmosThesewn);
        }catch (NumberFormatException ex) {
         JOptionPane.showMessageDialog(frame, "Δεν δώσατε αριθμό!", "Warning", JOptionPane.WARNING_MESSAGE);
         return;
        }
        int result = JOptionPane.showConfirmDialog(frame, panelLewforeia, "Επιλογή παροχών:", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION){
          if (wifiBox.isSelected())
           paroxes = "Wifi";
          if (tvBox.isSelected()){
           if (paroxes == null)
            paroxes = "TV";
           else
            paroxes = paroxes+",TV";
          }
          if (wcBox.isSelected())
           if (paroxes == null)
            paroxes = "WC";
           else
            paroxes = paroxes+",WC";
        }
        else{
         JOptionPane.showMessageDialog(frame, "Δεν δώσατε τιμή!", "Warning", JOptionPane.WARNING_MESSAGE);
      return;
        }
        AdminLewforeia.insertLewforeia();
	}
	
	public static String getOnomaLewforeiou(){
		return onomaLewforeiou;
	}
	
	public static int getNumArithmosThesewn(){
		return numArithmosThesewn;
	}
	
	public static String getParoxes(){
		return paroxes;
	}
	
	public void initialize(){
		paroxes = null;
        wifiBox.setSelected(false);
        tvBox.setSelected(false);
        wcBox.setSelected(false);
	}
}
