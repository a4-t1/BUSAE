package Tamias;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class NewWindowForTime extends JFrame {
	
	private static JButton ok;
	private static JLabel timeLabel,dateLabel,name,lastName;
	private static JTextField timeDisplay,dateDisplay,nameInsertion,lastNameInsertion;
	private static JComboBox category;
	private static String categorySelected;
	private static DefaultComboBoxModel<String> modelPrice = new DefaultComboBoxModel<String>();
	private static Component[] panelSelected;
	private static boolean selected = false;
	private static Font font= new Font("ARIAL", Font.ITALIC, 17);
	private static Font hedaerFont= new Font("Times New Roman", Font.BOLD, 20);
	private static int counter=0;
	private static Connection conn;
	private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static int x = (int) screenSize.getWidth();
	private static int y = (int) screenSize.getHeight();
	private static String busForTicket,timeForTicket;
	private static JPanel mainPanel;
	private static String cityfrom,cityto,strQuery;
	private static int bus;
	private static PreparedStatement pS;
	private static ResultSet rS;
	private static ArrayList<String> times = new ArrayList<String>();
	private static ArrayList<String> busNames = new ArrayList<String>();
	private static ArrayList<String> sheetsNumber = new ArrayList<String>();
	private static ArrayList<String> facility = new ArrayList<String>();
	
	
	public NewWindowForTime(){
		
		setBounds(0,dim.height/4-this.getSize().height/2,x,500);
		setSize(x,500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Διάλεξε ώρα");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
		    {
				busNames.removeAll(busNames);
				facility.removeAll(facility);
				sheetsNumber.removeAll(sheetsNumber);
				times.removeAll(times);
				counter=0;
				dispose();
		    }
		});
		
		cityfrom=MainFrame.getCityFrom().getSelectedItem().toString();
		cityto=MainFrame.getCityTo().getSelectedItem().toString();
		conn = OpenConnection.getConnection();	
		setLayout(new BorderLayout());
		
		makeQueries();	//Make queries and fill the array lists from database
		initPanelAndHeaders(); //Initialiaze the mainPanel of and the Titles of every column
		placePanels(); // Initiliazes and places the each time differently number of Jpanel 
		placeHelpSouthLabels(); //Places the south JPanel that's helper

		JPanel southPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		
		southPanel.add(dateLabel,c);
		c.gridx++;
		southPanel.add(dateDisplay,c);
		c.gridx++;
		southPanel.add(timeLabel,c);
		c.gridx++;
		southPanel.add(timeDisplay,c);
		c.gridx++;
		southPanel.add(category,c);
		c.gridx++;
		southPanel.add(ok,c);
		c.gridx = 0;
		c.gridy++;
		
		southPanel.add(name,c);
		c.gridx++;
		southPanel.add(nameInsertion,c);
		c.gridx++;
		southPanel.add(lastName,c);
		c.gridx++;
		southPanel.add(lastNameInsertion,c);
		
		
		mainPanel.add(southPanel);
		facility.removeAll(facility);
		sheetsNumber.removeAll(sheetsNumber);
		counter=0;
		add(mainPanel);
		setVisible(true);
	}
	
	public void makeQueries()
	{
		strQuery = "SELECT wraAnaxwrhshs, busName FROM dromologia WHERE Afethria=? AND Proorismos=?";
		try{
			pS = conn.prepareStatement(strQuery);
			pS.setString(1, cityfrom);
			pS.setString(2, cityto);
			rS = pS.executeQuery();
				while(rS.next()){
					times.add(rS.getString("wraAnaxwrhshs"));
					busNames.add(rS.getString("busName"));
					counter++;
				}
		}catch(Exception e1){
			
		}	
					
		strQuery = "SELECT numberOfSheets, facilities FROM stoixeia_lewforeiwn INNER JOIN dromologia ON dromologia.busName=stoixeia_lewforeiwn.busName WHERE Afethria=? AND Proorismos=?";
		try{
			pS = conn.prepareStatement(strQuery);
			pS.setString(1, cityfrom);
			pS.setString(2, cityto);
			rS = pS.executeQuery();
				while(rS.next()){
					facility.add(rS.getString("facilities"));
					sheetsNumber.add(rS.getString("numberOfSheets"));
				}
		}catch(Exception e2)
		{
			e2.printStackTrace();
		}
	}
	
	public void initPanelAndHeaders()
	{
		mainPanel = new JPanel(new GridLayout(counter+2,6));
		mainPanel.setBackground(new Color(255, 255, 255));
			
		JPanel headerPanel = new JPanel(new GridLayout(1,6));
		JLabel route = new JLabel("Διαδρομή");
		route.setFont(hedaerFont);
		JLabel timeDeparture = new JLabel("Ώρα Αναχ.");
		timeDeparture.setFont(hedaerFont);
		JLabel sheets = new JLabel("Θέσεις");
		sheets.setFont(hedaerFont);
		JLabel busname = new JLabel("Όνομα λεωφ.");
		busname.setFont(hedaerFont);
		JLabel price = new JLabel("Τιμή");
		price.setFont(hedaerFont);
		JLabel fac = new JLabel("Ευκολίες");
		fac.setFont(hedaerFont);
		
		headerPanel.add(route);
		headerPanel.add(timeDeparture);
		headerPanel.add(sheets);
		headerPanel.add(busname);
		headerPanel.add(price);
		headerPanel.add(fac);
		mainPanel.add(headerPanel);
		
		panelSelected = new Component[counter];
	}
	
	public void placePanels()
	{
		for(int i=0;i<counter;i++)
		{
			JPanel panel = new JPanel(new GridLayout(1,6));
			panel.setBackground(Color.WHITE);
			panelSelected[i] = panel;
			panel.setName(String.valueOf(i));
			panel.addMouseListener(new MouseListener(){

				public void mouseClicked(MouseEvent arg0) {
					for(int j=0;j<panelSelected.length;j++){
						panelSelected[j].setBackground(Color.WHITE);
					}
					ok.setEnabled(true);
					panel.setBackground(Color.GREEN);
					//int nth = Integer.valueOf(panel.getName());
					JLabel as = (JLabel) panel.getComponent(1);
					timeDisplay.setText(as.getText());
					selected=true;
				}
				public void mouseEntered(MouseEvent arg0) {
					if(selected==false)
						panel.setBackground(new Color(166, 244, 253));		
				}
				public void mouseExited(MouseEvent arg0) {
					if(selected==false){
						panel.setBackground(Color.WHITE);
					}
				}
				public void mousePressed(MouseEvent arg0) {
					bus = Integer.valueOf(panel.getName());
				}

				public void mouseReleased(MouseEvent arg0) {
				}			
			});		
			
			panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			JLabel diadromi = new JLabel(cityfrom + "-" + cityto);
			diadromi.setFont(font);
			JLabel wraAnaxwrhshs = new JLabel(times.get(i));
			wraAnaxwrhshs.setName(String.valueOf(i));
			wraAnaxwrhshs.setFont(font);
			JLabel slots = new JLabel(sheetsNumber.get(i));
			slots.setFont(font);
			JLabel busName = new JLabel(busNames.get(i));
			busName.setFont(font);
			JLabel cost = new JLabel("22,34\u20ac");
			cost.setFont(font);
			JLabel facilities = new JLabel(facility.get(i));
			facilities.setFont(font);
			panel.add(diadromi);
			panel.add(wraAnaxwrhshs);
			panel.add(slots);
			panel.add(busName);
			panel.add(cost);
			panel.add(facilities);
			mainPanel.add(panel);
		}
	}
	
	public void placeHelpSouthLabels()
	{	
		nameInsertion = new JTextField(15);
		nameInsertion.setFont(hedaerFont);
		lastNameInsertion = new JTextField(15);
		lastNameInsertion.setFont(hedaerFont);
		name = new JLabel("Όνομα :");
		name.setFont(font);
		lastName = new JLabel("Επώνυμο");
		lastName.setFont(font);
		dateLabel = new JLabel("Επιλεγμένη ημερομηνία ");
		dateLabel.setFont(font);
		timeLabel = new JLabel("Ώρα αναχώρησης ");
		timeLabel.setFont(font);
		dateDisplay = new JTextField(OpenCalendar.getMonth()+" "+ String.valueOf(OpenCalendar.getDay()+","+OpenCalendar.getYear()),15);
		dateDisplay.setFocusable(false);
		timeDisplay = new JTextField(15);
		dateDisplay.setFont(hedaerFont);
		timeDisplay.setFont(hedaerFont);
		timeDisplay.setFocusable(false);
		category = new JComboBox();
		category.setFont(font);
		category.setPreferredSize(new Dimension(140,25));
		category.setCursor(new Cursor(Cursor.HAND_CURSOR));
		category.setFocusable(false);
		category.setModel(modelPrice);
		modelPrice.addElement("Κανονικό");
		modelPrice.addElement("Φοιτητικό");
		modelPrice.addElement("Πολύτεκνο");
		
		
		ok = new JButton("Προβολή/Εκτύπωση");
		ok.setEnabled(false);
		ok.setPreferredSize(new Dimension(150,40));
		ok.setFocusable(false);
		ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
					if(nameInsertion.getText().equals("") || lastNameInsertion.getText().equals("")){
						JOptionPane optionPane = new JOptionPane("Πρέπει να δώσετε όνομα και επώνυμο του πελάτη", JOptionPane.ERROR_MESSAGE);    
						JDialog dialog = optionPane.createDialog("Εισάγεται όνομα και επώνυμο");
						dialog.setVisible(true);
						
					}
					else{
						categorySelected = category.getSelectedItem().toString();
						category.removeAllItems();
						dispose();	
						new TicketFrame();	
					}
			}		
		});
	}
	public static String getDate()
	{
		return dateDisplay.getText();
	}
	public static String getTime()
	{
		timeForTicket = times.get(bus);
		times.removeAll(times);
		return timeForTicket;
	}
	public static String getCategory()
	{
		return categorySelected;
	}
	public static String getCityFrom()
	{
		return cityfrom;
	}
	public static String getCityTo()
	{
		return cityto;
	}
	public static String getBus()
	{
		busForTicket = busNames.get(bus);
		busNames.removeAll(busNames);
		return busForTicket;
		
	}
	public static String getCustomerName()
	{
		return nameInsertion.getText();
	}
	public static String getCustomerLastName()
	{
		return lastNameInsertion.getText();
	}
}
