package Tamias;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class OpenCalendar{
	
	//private static int daySelected;
	private static String year,month;
	private static int rowz,col,day;
    private static JLabel lblMonth;//, lblYear;
    private static JButton btnPrev, btnNext,buttonOK;
    private static JTable tblCalendar;
    private static JComboBox cmbYear;
    private static JFrame frmMain;
    private static Container pane;
    private static DefaultTableModel mtblCalendar; //Table model
    private static JScrollPane stblCalendar; //The scrollpane
    private static JPanel pnlCalendar;
    private static int realYear, realMonth, currentYear, currentMonth;
    private static String[] months;
    private static String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers

    public OpenCalendar(int x,int y){
    	
    	
    	
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (ClassNotFoundException e) {}
        catch (InstantiationException e) {}
        catch (IllegalAccessException e) {}
        catch (UnsupportedLookAndFeelException e) {}

 
        frmMain = new JFrame ("Choose date"); //Create frame
        frmMain.setBounds(x,y,330, 375); 
        pane = frmMain.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout
        frmMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Close when X is clicked

 

        //Create controls
        lblMonth = new JLabel ("January");
        lblMonth.setFont(new Font("ARIAL", Font.BOLD, 14));
        buttonOK = new JButton ("OK");
        buttonOK.setEnabled(false);
        buttonOK.addActionListener(new dateIsReady());
        cmbYear = new JComboBox();
        btnPrev = new JButton ("<<");
        btnNext = new JButton (">>");
        mtblCalendar = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
        tblCalendar = new JTable(mtblCalendar);
        tblCalendar.addMouseListener(new CustomListener());
        stblCalendar = new JScrollPane(tblCalendar);
        tblCalendar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnlCalendar = new JPanel(null);

 
       // pnlCalendar.setBorder(BorderFactory.createTitledBorder("SE GAMAW"));
        btnPrev.addActionListener(new btnPrev_Action());
        btnNext.addActionListener(new btnNext_Action());
        cmbYear.addActionListener(new cmbYear_Action());


        pane.add(pnlCalendar);
        pnlCalendar.add(lblMonth);
        pnlCalendar.add(buttonOK);
        pnlCalendar.add(cmbYear);
        pnlCalendar.add(btnPrev);
        pnlCalendar.add(btnNext);
        pnlCalendar.add(stblCalendar);
         
        //Set bounds

        pnlCalendar.setBounds(0, 0, 320, 335);
        lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 100, 25);
        buttonOK.setBounds(10, 305, 50, 30);
        cmbYear.setBounds(230, 305, 80, 20);
        btnPrev.setBounds(10, 25, 50, 25);
        btnNext.setBounds(260, 25, 50, 25);
        stblCalendar.setBounds(10, 50, 300, 250);

        //Make frame visible

        frmMain.setResizable(false);
        frmMain.setVisible(true);

         

        //Get real month/year
        GregorianCalendar cal = new GregorianCalendar(); //Create calendar
        realMonth = cal.get(GregorianCalendar.MONTH); //Get month
        realYear = cal.get(GregorianCalendar.YEAR); //Get year
        currentMonth = realMonth; //Match month and year
        currentYear = realYear;

         

        //Add headers

        for (int i=0; i<7; i++){
            mtblCalendar.addColumn(headers[i]);
        }

         

        tblCalendar.getParent().setBackground(tblCalendar.getBackground()); //Set background 
        //No resize/reorder
        tblCalendar.getTableHeader().setResizingAllowed(false);
        tblCalendar.getTableHeader().setReorderingAllowed(false);
        tblCalendar.setColumnSelectionAllowed(true);
        tblCalendar.setRowSelectionAllowed(true);
        tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblCalendar.setRowHeight(38);
        mtblCalendar.setColumnCount(7);
        mtblCalendar.setRowCount(6);

        for (int i=realYear-23; i<=realYear+20; i++){
            cmbYear.addItem(String.valueOf(i));
        }

        refreshCalendar (realMonth, realYear); //Refresh calendar

    }



	public static void refreshCalendar(int month, int year){

        months = new String[] {"Ιανουάριος", "Φεβρουάριος", "Μάρτιος", "Απρίλιος", "Μάιος", "Ιούνιος","Ιούλιος", "Αύγουστος", "Σεπτέμβριος", "Οκτώβριος", "Νοέμβριος", "Δεκέμβριος"};
        int nod, som; //Number Of Days, Start Of Month
        btnPrev.setEnabled(true);
        btnNext.setEnabled(true);
        if (month == 0 && year <= realYear-10){btnPrev.setEnabled(false);} //Too early
        if (month == 11 && year >= realYear+100){btnNext.setEnabled(false);} //Too late
        lblMonth.setText(months[month]); //Refresh the month label (at the top)
        lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 180, 25); //Re-align label with calendar
        cmbYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box 
        for (int i=0; i<6; i++){
            for (int j=0; j<7; j++){
                mtblCalendar.setValueAt(null, i, j);
            }
        }

         

        //Get first day of month and number of days
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        som = cal.get(GregorianCalendar.DAY_OF_WEEK);
        for (int i=1; i<=nod; i++){
        	int row = new Integer((i+som-2)/7);
            int column  =  (i+som-2)%7;
            mtblCalendar.setValueAt(i, row, column);
        }
        tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer());
    }

 

    static class tblCalendarRenderer extends DefaultTableCellRenderer{
        public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected, boolean focused, int row, int column){
            super.getTableCellRendererComponent(table, value, isSelected, focused, row, column);
            setBackground(Color.WHITE);
            
            
            if (isSelected && row == rowz & column == col){
            		setBackground(new Color(255, 179, 102));
            }
            
            
            setBorder(null);
            setForeground(Color.black);
            return this; 
        }

    }

 

    static class btnPrev_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (currentMonth == 0){ //Back one year
                currentMonth = 11;
                currentYear -= 1;
            }
            else{ //Back one month
                currentMonth -= 1;
            }
            refreshCalendar(currentMonth, currentYear);
        }
    }

    static class btnNext_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (currentMonth == 11){ //Foward one year
                currentMonth = 0;
                currentYear += 1;
            }
            else{ //Foward one month
                currentMonth += 1;
            }
            refreshCalendar(currentMonth, currentYear);
        }
    }
		static class cmbYear_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (cmbYear.getSelectedItem() != null){
                String b = cmbYear.getSelectedItem().toString();
              currentYear = Integer.parseInt(b);
                refreshCalendar(currentMonth, currentYear);
            }
        }
    }
		
		public class CustomListener extends MouseAdapter {

			
			public void mouseClicked(MouseEvent e) {
				
				rowz = tblCalendar.getSelectedRow();
		        col = tblCalendar.getSelectedColumn();
		        
		        if (tblCalendar.getModel().getValueAt(rowz,col)!=null){
					buttonOK.setEnabled(true);
				}
		        else
		        	buttonOK.setEnabled(false);
		        
		        tblCalendar.repaint();
			}
		}
		
		public class dateIsReady implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				day = (int) tblCalendar.getModel().getValueAt(rowz,col);	
				year = cmbYear.getSelectedItem().toString();	
				month = lblMonth.getText();
				frmMain.dispose();
			}

		}
		
		public static String getMonth(){
			return month;
		}
		public static String getYear(){
			return year;
		}
		public static int getDay(){
			return day;
		}
}
