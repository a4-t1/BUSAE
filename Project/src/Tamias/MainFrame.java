package Tamias;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class MainFrame extends JFrame {
	
	private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	private static Connection con;
	private static JButton submit,calendar,info;
	private static JPanel mainPanel,namePanel,top;
	private static JLabel from,to,date,time,title;
	private static Font font = new Font("ARIAL",Font.PLAIN,25);
	private static JComboBox<String> cityFrom,cityTo;
	private static DefaultComboBoxModel<String> modelFrom = new DefaultComboBoxModel<String>();
	private static DefaultComboBoxModel<String> modelTo = new DefaultComboBoxModel<String>();

	
	public MainFrame(){
		super("Εφαρμογή αγοράς υπεραστικών λεωφορείων");
		addWindowListener(new WindowListener(){
			public void windowActivated(WindowEvent e) {				
			}
			public void windowClosed(WindowEvent e) {				
			}
			public void windowClosing(WindowEvent e) {
				con=OpenConnection.getConnection();
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
			public void windowDeactivated(WindowEvent e) {			
			}
			public void windowDeiconified(WindowEvent e) {				
			}
			public void windowIconified(WindowEvent e) {
			}
			public void windowOpened(WindowEvent e) {
			}
		});
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setMinimumSize(new Dimension(1200, 600));
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		OpenConnection conn = new OpenConnection();
		mainPanel = new JPanel(new GridBagLayout());
		top = new JPanel(new BorderLayout());
		namePanel = new JPanel(new BorderLayout());
		GridBagConstraints c = new GridBagConstraints();

		
		JLabel tamias = new JLabel(Main.getNameOfEmploye());
		tamias.setFont(new Font("ARIAL", Font.HANGING_BASELINE,19));
        top.add(tamias, BorderLayout.WEST);
		namePanel.add(tamias,BorderLayout.NORTH);
		
		
		
		initializeVariables();// Initialize JLabels,JComboBoxes etc..	
		
		c.gridwidth = 2;
		c.weightx = 0.5;
		c.weighty = 0.5;
		
		c.gridx=0;
		c.gridy=0;
		
		// *************** JLABELS COLUMN *******
		mainPanel.add(title,c);
		c.gridwidth =1;
		c.anchor = GridBagConstraints.LINE_END;
		c.gridy++;
		mainPanel.add(from,c);
		c.gridy++;
		mainPanel.add(to,c);
		c.gridy++;
		mainPanel.add(date,c);
		c.gridy++;
		//****************************************
		
		// TEXTFIELDS COLUMN *********************
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx=1;
		c.gridy=1;
		mainPanel.add(cityFrom,c);
		c.gridy++;
		mainPanel.add(cityTo,c);
		//*****************************************
		c.gridy++;
		mainPanel.add(calendar,c);//adding calendar button
		c.gridy++;
		mainPanel.add(info,c);

		//****************************************
		
		//****** SUBMIT BUTTON ************************
		c.weighty = 10;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx=1;
		c.gridy=5;
		mainPanel.add(submit,c);
		
		
		
		
		new GetData();
		
		getContentPane().add(namePanel,BorderLayout.NORTH);
		getContentPane().add(mainPanel,BorderLayout.CENTER);
		setVisible(true);
	}
		
	public static void initializeVariables(){
		title = new JLabel("Διάλεξε την διαδρομή");
		title.setFont(new Font("ARIAL", Font.PLAIN,25));
		from = new JLabel("Από: ");
		from.setFont(font);
		to = new JLabel("Προς: ");
		to.setFont(font);
		date = new JLabel("Ημερομηνία: ");
		date.setFont(font);
		time = new JLabel("Ώρα: ");
		time.setFont(font);
		
		

		//******************BEGGINING CITY COMBOBOX LIST *****************
		cityFrom = new JComboBox<String>();	
		cityFrom.setFont(new Font("Arial", Font.ITALIC,16));
		cityFrom.setForeground(Color.BLACK);
		cityFrom.setBackground(Color.WHITE);
		cityFrom.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cityFrom.setPreferredSize(new Dimension(150,25));
		cityFrom.setFocusable(false);
		cityFrom.setModel(modelFrom);
		//******************************************************************
		
		//************** DESTINATION CITY COMBO BOX LIST ******************
		cityTo = new JComboBox<String>();
		cityTo.setFont(new Font("Arial", Font.ITALIC,16));
		cityTo.setForeground(Color.BLACK);
		cityTo.setBackground(Color.WHITE);
		cityTo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cityTo.setPreferredSize(new Dimension(150,25));
		cityTo.setFocusable(false);
		
		cityFrom.setModel(modelFrom);
		cityTo.setModel(modelTo);
		
		cityFrom.addItemListener(new ChangeDataOfTo());
		//*************** MODELS ************************

		info = new JButton();
		info.setPreferredSize(new Dimension(23,24));
		info.setIcon(new ImageIcon("info.png"));
		info.setFocusable(false);
		info.setCursor(new Cursor(Cursor.HAND_CURSOR));
		info.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				GetInformation info = new GetInformation();
			}
		});
		calendar = new JButton();
		calendar.setPreferredSize(new Dimension(50,50));
		calendar.setIcon(new ImageIcon("calendar.png"));
		calendar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	new OpenCalendar(calendar.getBounds().x,calendar.getBounds().y);
            }
        });      
		calendar.setFocusable(false);
		calendar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		submit = new JButton("Αναζήτηση");
		submit.setFont(new Font("ARIAL", Font.BOLD, 14));
		submit.setFocusable(false);
		submit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		submit.addActionListener(new SearchListener());
	}
	public static DefaultComboBoxModel<String> getCityFrom(){
		return modelFrom;
	}
	public static DefaultComboBoxModel<String> getCityTo(){
		return modelTo;
	}
}
