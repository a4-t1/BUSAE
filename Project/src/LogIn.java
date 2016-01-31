import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import admin.AdminMain;
import Tamias.Main;


public class LogIn extends JFrame {

	public static boolean userANDpassIsGiven=false,asWhatIsGiven=false;
	public static JDialog dialog;
	public static JOptionPane optionPane;
	public static String asWhat="";
	public static JButton asAdmin,asTamias,logIn;
	static int style = Font.BOLD | Font.ITALIC;
	public static Font font = new Font ("ARIAL", style , 21);
	public static Font font2 = new Font ("ARIAL", Font.PLAIN,22);
	public static Font font3 = new Font("SansSerif", Font.PLAIN, 20);
	public static JTextField usernameField,passwordField;
	public static JPanel mainPanel,southPanel;
	public static JLabel username,password,title,warningUsernameLabel;
	public static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	int frameWidth=this.getSize().width;
	int frameHeight=this.getSize().height;
	int locationX =(dim.width-frameWidth)/4;
	int locationY =(dim.height-frameHeight)/4;
	
	
	public LogIn(){
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagConstraints c = new GridBagConstraints();

		initVars();
		
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
		mainPanel.add(username,c);
		c.gridy++;
		mainPanel.add(password,c);
		c.gridy=5;
		mainPanel.add(asAdmin,c);
		//****************************************
		
		// TEXTFIELDS COLUMN *********************
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx=1;
		c.gridy=1;
		mainPanel.add(usernameField,c);
		c.gridy++;
		mainPanel.add(passwordField,c);
		//*****************************************
		c.gridy++;
		mainPanel.add(logIn,c);
		
		c.weighty = 5;
		c.gridx=1;
		c.gridy=5;
		c.insets = new Insets(0,20,0,0);
		mainPanel.add(asTamias,c);
		//****************************************

		
		
		

		


		
		add(mainPanel,BorderLayout.CENTER);
		add(southPanel,BorderLayout.SOUTH);
		setSize(600,500);
		setLocation(locationX,locationY);
		setTitle("Log in");
		setVisible(true);
	}
	public static void main(String[] args) {
		
		new LogIn();
	}
	
	public void initVars()
	{
		asAdmin = new JButton("Είσοδος σαν admin");
		asAdmin.setFocusable(false);
		asAdmin.setPreferredSize(new Dimension(150,35));
		asAdmin.setActionCommand("admin");
		asAdmin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		asAdmin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				asWhat = e.getActionCommand();
				asTamias.setEnabled(true);
				asAdmin.setEnabled(false);
				asWhatIsGiven=true;
			}
			
			
		});
		
		asTamias = new JButton("Είσοδος σαν ταμίας");
		asTamias.setFocusable(false);
		asTamias.setPreferredSize(new Dimension(150,35));
		asTamias.setCursor(new Cursor(Cursor.HAND_CURSOR));
		asTamias.setActionCommand("tamias");
		asTamias.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				asWhat = arg0.getActionCommand();
				asTamias.setEnabled(false);
				asAdmin.setEnabled(true);
				asWhatIsGiven=true;
			}
			
		});
		
		logIn = new JButton("Είσοδος");
		logIn.setFocusable(false);
		logIn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		logIn.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e) {
				if(usernameField.getText().equals("")){
					optionPane = new JOptionPane("Πρέπει να δώσετε username", JOptionPane.ERROR_MESSAGE);    
					dialog = optionPane.createDialog("Log in error");
					dialog.setVisible(true);
				}
				else if(passwordField.getText().equals("")){
					optionPane = new JOptionPane("Πρέπει να δώσετε password", JOptionPane.ERROR_MESSAGE);    
					dialog = optionPane.createDialog("Log in error");
					dialog.setVisible(true);
				}
				else if(asWhatIsGiven==false){
					optionPane = new JOptionPane("Πρέπει να διαλέξετε ως τι θέλετε να συνδεθείτε", JOptionPane.ERROR_MESSAGE);    
					dialog = optionPane.createDialog("Log in error");
					dialog.setVisible(true);
				}
				else
					if(asWhat.equals("tamias")){
						identifyName identification = new identifyName(usernameField.getText(),passwordField.getText()); 
						if(identification.getAccess())
						{
							try {
								identification.getConnection().close();
							} catch (SQLException e1) {
							}
							dispose();
							Main.startTamiaFrame(usernameField.getText());
						}
					}
					else if(asWhat.equals("admin"))
					{
						identifyAdminName adminIdentification = new identifyAdminName(usernameField.getText(),passwordField.getText());
						if(adminIdentification.getAccess())
						{
							try {
								adminIdentification.getConnection().close();
							} catch (SQLException e1) {
							}
						dispose();
						AdminMain.startAdminFrame("Name Of Admin");
					}
					}

				
			}		
		});
		
		title = new JLabel("Welcome to BUSAE");
		title.setFont(font);
		username = new JLabel("Username :");
		username.setFont(font2);
		password = new JLabel("Password :");
		password.setFont(font2);
		
		usernameField = new JTextField(15);
		usernameField.setFont(font3);
		passwordField = new JTextField(15);
		passwordField.setFont(font3);
		
		mainPanel = new JPanel(new GridBagLayout());
		southPanel = new JPanel();
		
	}
	
	

}
