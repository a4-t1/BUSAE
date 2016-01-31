package admin;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.SQLException;


public class AdminMainFrame extends JFrame{
	private static Connection conn;
	private static JTable tableDromologia;
    private static JScrollPane scrollPaneDromologia;
    private static JTable tableLewforeia;
    private static JScrollPane scrollPaneLewforeia;
    private static DefaultTableModel modelDromologia;
    private static DefaultTableModel modelLewforeia;
    
    public AdminMainFrame(){
    	  //frame
    	  super("Διαχειριστής συστήματος υπεραστικών λεωφορείων");
    	  addWindowListener(new WindowListener(){
    	   public void windowActivated(WindowEvent e) {
    	    //empty body
    	   }
    	   public void windowClosed(WindowEvent e) {
    	    //empty body
    	   }
    	   public void windowClosing(WindowEvent e) {
    	    conn = DbConnect.getConnection();
    	    try {
    	     conn.close();
    	    } catch (SQLException e1) {
    	     System.exit(0);
    	    }
    	   }
    	   public void windowDeactivated(WindowEvent arg0) {
    	    //empty body
    	   }
    	   public final void windowDeiconified(WindowEvent arg0) {
    	    //empty body
    	   }
    	   public void windowIconified(WindowEvent arg0) {
    	    //empty body
    	   }
    	   public void windowOpened(WindowEvent arg0) {
    	    //empty body
    	   }
    	  });
    	  setBounds(100, 100, 800, 600);
    	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	        setMinimumSize(new Dimension(800, 600));
    	        setResizable(false);
    	        getContentPane().setLayout(null);
    	        
    	        //connection
    	        DbConnect conn = new DbConnect();
    	        
    	        //*********dromologia***************
    	        new AdminDromologia();
    	        modelDromologia = AdminDromologia.getModelDromologia();
    	        tableDromologia = new JTable(modelDromologia);
    	        tableDromologia.setFont(new Font("Times New Roman", Font.PLAIN, 15));
    	        tableDromologia.getTableHeader().setFont( new Font( "Times New Roman" , Font.BOLD, 14 ));
    	        
    	        scrollPaneDromologia = new JScrollPane(tableDromologia);
    	        scrollPaneDromologia.setBounds(10, 10, 470, 200);
    	        getContentPane().add(scrollPaneDromologia);
    	        
    	        //add
    	        final JButton btnAddDromologia = new JButton("Προσθήκη δρομολογίου");
    	        btnAddDromologia.setFont(new Font("Tahoma", Font.PLAIN, 11));
    	        btnAddDromologia.setBounds(500, 10, 150, 40);
    	        final ActionListener addDromologiaListener = new AddDromologiaListener();
    	        btnAddDromologia.addActionListener(addDromologiaListener);
    	        getContentPane().add(btnAddDromologia);
    	        
    	        //delete
    	        final JButton btnDelDromologia = new JButton("Διαγραφή δρομολογίου");
    	        btnDelDromologia.setFont(new Font("Tahoma", Font.PLAIN, 11));
    	        btnDelDromologia.setBounds(500, 60, 150, 40);
    	        final ActionListener deleteDromologiaListener = new DeleteDromologiaListener();
    	        btnDelDromologia.addActionListener(deleteDromologiaListener);
    	        getContentPane().add(btnDelDromologia);
    	        
    	        //edit
    	        final JButton btnEditDromologia = new JButton("Επεξεργασία");
    	        btnEditDromologia.setFont(new Font("Tahoma", Font.PLAIN, 11));
    	        btnEditDromologia.setBounds(500, 110, 150, 40);
    	        final ActionListener editDromologiaListener = new EditDromologiaListener();
    	        btnEditDromologia.addActionListener(editDromologiaListener);
    	        getContentPane().add(btnEditDromologia);
    	        
    	        
    	        //*********lewforeia***************
    	        new AdminLewforeia();
    	        modelLewforeia = AdminLewforeia.getModelLewforeia();
    	        tableLewforeia = new JTable(modelLewforeia);
    	        tableLewforeia.setFont(new Font("Times New Roman", Font.PLAIN, 15));
    	        tableLewforeia.getTableHeader().setFont( new Font( "Times New Roman" , Font.BOLD, 14 ));
    	        
    	        scrollPaneLewforeia = new JScrollPane(tableLewforeia);
    	        scrollPaneLewforeia.setBounds(10, 220, 470, 200);
    	        getContentPane().add(scrollPaneLewforeia);
    	       
    	        //add
    	        final JButton btnAddLewforeia = new JButton("Προσθήκη λεωφορείου");
    	        btnAddLewforeia.setFont(new Font("Tahoma", Font.PLAIN, 11));
    	        btnAddLewforeia.setBounds(500, 220, 150, 40);
    	        final ActionListener addLewforeiaListener = new AddLewforeiaListener();
    	        btnAddLewforeia.addActionListener(addLewforeiaListener);
    	        getContentPane().add(btnAddLewforeia);
    	        //delete
    	        final JButton btnDelLewforeia = new JButton("Διαγραφή λεωφορείου");
    	        btnDelLewforeia.setFont(new Font("Tahoma", Font.PLAIN, 11));
    	        btnDelLewforeia.setBounds(500, 270, 150, 40);
    	        final ActionListener deleteLewforeiaListener = new DeleteLewforeiaListener();
    	        btnDelLewforeia.addActionListener(deleteLewforeiaListener);
    	        getContentPane().add(btnDelLewforeia);
    	        //edit
    	        final JButton btnEditLewforeia = new JButton("Επεξεργασία");
    	        btnEditLewforeia.setFont(new Font("Tahoma", Font.PLAIN, 11));
    	        btnEditLewforeia.setBounds(500, 320, 150, 40);
    	        final ActionListener editLewforeiaListener = new EditLewforeiaListener();
    	        btnEditLewforeia.addActionListener(editLewforeiaListener);
    	        getContentPane().add(btnEditLewforeia);
    	        
    	        //**************anakoinwsh***************
    	        final JButton btnAddAnnouncemement = new JButton("Προσθήκη ανακοίνωσης");
    	        btnAddAnnouncemement.setFont(new Font("Tahoma", Font.PLAIN, 11));
    	        btnAddAnnouncemement.setBounds(10, 450, 470, 60);
    	        final ActionListener addAnnouncemement = new AddAnnouncemement();
    	        btnAddAnnouncemement.addActionListener(addAnnouncemement);
    	        getContentPane().add(btnAddAnnouncemement);
    	        setVisible(true);
    	 }
	
	public static JTable getTableDromologia(){
		return tableDromologia;
	}
	
	public static JTable getTableLewforeia(){
		return tableLewforeia;
	}
	
}
