package Tamias;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class SearchListener implements ActionListener {

	//private static DefaultComboBoxModel<String> cityfromSelected,citytoSelected;
	private static String monthSelected;
	private static String ans;
	private static String[] days = {"Σάββατο","Κύριακη","Δευτέρα","Τρίτη","Τετάρτη","Πέμπτη","Παρασκευή"};

	public SearchListener(){
		//cityfromSelected = MainFrame.getCityFrom();
		//citytoSelected = MainFrame.getCityTo();
		
	}
	public void actionPerformed(ActionEvent e) {
		monthSelected=OpenCalendar.getMonth();
			if(monthSelected==null)
				{
				JOptionPane optionPane = new JOptionPane("You must choose a date", JOptionPane.ERROR_MESSAGE);   
				JDialog dialog = optionPane.createDialog("Ops");
				dialog.setAlwaysOnTop(true);
				dialog.setVisible(true);
				}
			else
				new NewWindowForTime();
		
		
	
			
			

	}
	

}
