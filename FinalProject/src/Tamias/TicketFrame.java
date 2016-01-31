package Tamias;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;


public class TicketFrame extends JFrame {
	
	
	private static Font font= new Font("ARIAL", Font.ITALIC, 21);
	private static JTextArea ticket;
	Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - this.getHeight()) / 4);
    private static String name,lastName,time,cityFrom,cityTo,busName,ticketPrice,date;
    
    
	public TicketFrame(){
		super("Το εισιτήριο προς εκτύπωση");
		setLocation(y,y);
		setLayout(null);
		name = NewWindowForTime.getCustomerName();
		lastName = NewWindowForTime.getCustomerLastName();
		date = NewWindowForTime.getDate();
		time = NewWindowForTime.getTime();
		cityFrom = NewWindowForTime.getCityFrom();
		cityTo = NewWindowForTime.getCityTo();
		ticketPrice = NewWindowForTime.getCategory();
		busName = NewWindowForTime.getBus();
		
		generateQRCode();

		ticket = new JTextArea();
		ticket.setBounds(15,10,620,400);
		ticket.setLayout(null);
		
		ImageIcon img = new ImageIcon("bus.jpg");
		JLabel pic = new JLabel();
		pic.setBounds(300,0,289,78);
		pic.setIcon(img);
		
		JLabel nameLabel = new JLabel(name + " " + lastName);
		nameLabel.setBounds(15,30,250,25);
		nameLabel.setFont(font);
		
		JLabel category = new JLabel(ticketPrice);
		category.setBounds(15,90,100,25);
		category.setFont(font);
		
		JLabel price = new JLabel(String.valueOf(22*0.4+"\u20ac"));
		price.setBounds(130,90,100,25);
		price.setFont(font);
		
		
		JLabel dromologio = new JLabel(cityFrom + " - " + cityTo);
		dromologio.setBounds(15,140,250,25);
		dromologio.setFont(font);
		
		JLabel busLabel = new JLabel("Όνομα λεωφορείου - " + busName);
		busLabel.setBounds(15,190,270,25);
		busLabel.setFont(font);
		
		JLabel dateLabel = new JLabel(date);
		dateLabel.setBounds(300,270,200,25);
		dateLabel.setFont(font);
		
		ImageIcon QRCode_img = new ImageIcon("QR_Code.jpg");
		JLabel QRCode = new JLabel();
		QRCode.setIcon(QRCode_img);
		QRCode.setBounds(15,270,125,125);
		
		
		JButton print = new JButton("Εκτύπωση");
		print.setFocusable(false);
		print.setBounds(320,420,95,45);
		print.setCursor(new Cursor(Cursor.HAND_CURSOR));
		/*print.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Boolean complete;
				try {
					complete = ticket.print();
				if(complete){
					JOptionPane.showMessageDialog(null, "Done printing..");
				}
				else{
					JOptionPane.showMessageDialog(null,"Printing..");
				}
				} catch (PrinterException e) {
					e.printStackTrace();
				}
			
			}
		});*/
		
		ticket.add(busLabel);
		ticket.add(QRCode);
		ticket.add(dateLabel);
		ticket.add(dromologio);
		ticket.add(price);
		ticket.add(category);
		ticket.add(nameLabel);
		ticket.add(pic);
		ticket.setFocusable(false);
		add(print);
		add(ticket);
	

		

		setSize(700,500);
		setResizable(false);
		setVisible(true);
	}
	

	
	public static void generateQRCode(){
		ByteArrayOutputStream out = QRCode.from("asdasd")
				.to(ImageType.PNG).stream();
		
		try{
			FileOutputStream fout = new FileOutputStream(new File("QR_Code.JPG"));
			
			fout.write(out.toByteArray());
			fout.flush();
			fout.close();
		}catch(FileNotFoundException e)
		{
			
		}catch(IOException e)
		{
			
		}
	}
	

}
