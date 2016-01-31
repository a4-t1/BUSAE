package admin;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLewforeia {
	private static JTable tableLewforeia;
    private static Connection conn;
    private static ResultSet rs = null;
    private static PreparedStatement preparedStatement = null;
    private static String query;
    private static String onomaLewforeiou;
	private static String paroxes;
	private static int numArithmosThesewn;
    private static DefaultTableModel modelLewforeia = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
          return false;
        }
    };
    
    public AdminLewforeia(){    
    	 modelLewforeia.addColumn("Όνομα Λεωφορείου");
    	    modelLewforeia.addColumn("Αριθμός Θέσεων");
    	    modelLewforeia.addColumn("Παροχές");
    	    query = "SELECT * FROM stoixeia_lewforeiwn";
    	    try
    	    {
    	     conn = DbConnect.getConnection();
    	     preparedStatement = (PreparedStatement) conn.prepareStatement(query);
    	        rs = preparedStatement.executeQuery(query);
    	        
    	        while (rs.next())
    	        {
    	        String onomaLewforeiou = rs.getString("busName");
    	        String arithmosThesewn = rs.getString("numberOfSheets");
    	        String paroxes = rs.getString("facilities");
    	        
    	        modelLewforeia.addRow(new Object[]{onomaLewforeiou,arithmosThesewn,paroxes});
    	        }
    	    }
    	    catch (Exception e)
    	    {
    	        e.printStackTrace();
    	    }
    	    finally
    	    {
    	        try { if (rs != null) rs.close(); } catch (SQLException e) {
    	            e.printStackTrace(); }
    	        try { if (preparedStatement != null) preparedStatement.close(); } catch (SQLException e) {
    	            e.printStackTrace(); }
    	    }
    	    
    	}


public static String[] getAvailableBuses(){
	int j = modelLewforeia.getRowCount();
    query = "SELECT stoixeia_lewforeiwn.busName FROM stoixeia_lewforeiwn LEFT JOIN dromologia ON stoixeia_lewforeiwn.busName = dromologia.busName WHERE dromologia.busName IS NULL";
    String[] availableBuses = new String[j];
    try
    {
    	conn = DbConnect.getConnection();
    	preparedStatement = (PreparedStatement) conn.prepareStatement(query);
        rs = preparedStatement.executeQuery(query);
        int z = 0;
        
        while (rs.next())
        {
        String busName = rs.getString("busName");
        availableBuses[z++] =  busName;
        }
    }
    catch (Exception g)
    {
        g.printStackTrace();
    }
    finally
    {
        try { if (rs != null) rs.close(); } catch (SQLException g) {
            g.printStackTrace(); }
        try { if (preparedStatement != null) preparedStatement.close(); } catch (SQLException g) {
            g.printStackTrace(); }
    }
    return availableBuses;
}

public static void insertLewforeia(){
	onomaLewforeiou = AddLewforeiaListener.getOnomaLewforeiou();
	numArithmosThesewn = AddLewforeiaListener.getNumArithmosThesewn();
	paroxes = AddLewforeiaListener.getParoxes();
	
	
	modelLewforeia.addRow(new Object[]{onomaLewforeiou,numArithmosThesewn,paroxes});
	query = "INSERT INTO stoixeia_lewforeiwn VALUES(?,?,?)";
    try
    {
    	conn = DbConnect.getConnection();
    	preparedStatement = (PreparedStatement) conn.prepareStatement(query);
        
    	preparedStatement.setString(1, onomaLewforeiou);
        preparedStatement.setInt(2, numArithmosThesewn);
        preparedStatement.setString(3, paroxes);
        
        preparedStatement.executeUpdate();
        
    }
    catch (Exception f)
    {
        f.printStackTrace();
    }
    finally
    {
        try { if (preparedStatement != null) preparedStatement.close(); } catch (SQLException f) {
            f.printStackTrace(); }
    }
}

public static void deleteLewforeia(){
	int row = DeleteLewforeiaListener.getDeleteRow();
	tableLewforeia = AdminMainFrame.getTableLewforeia();
	
	query = "DELETE FROM stoixeia_lewforeiwn WHERE busName=?";
    try
    {
    	conn = DbConnect.getConnection();
    	preparedStatement = (PreparedStatement) conn.prepareStatement(query);
        
    	preparedStatement.setString(1, (String) tableLewforeia.getValueAt(row,0));
        
        preparedStatement.executeUpdate();
        
    }
    catch (Exception f)
    {
        f.printStackTrace();
    }
    finally
    {
        try { if (preparedStatement != null) preparedStatement.close(); } catch (SQLException f) {
            f.printStackTrace(); }
    }
    modelLewforeia.removeRow(row);
}

public static void updateLewforeia(){
	int col = EditLewforeiaListener.getEditColumn();
	int row = EditLewforeiaListener.getEditRow();
	String newValue = EditLewforeiaListener.getNewValue();
	tableLewforeia = AdminMainFrame.getTableLewforeia();
	
	switch (col) {
    case 0:  {query = "UPDATE stoixeia_lewforeiwn SET busName=? WHERE busName=?";
             break;}
    case 1:  {query = "UPDATE stoixeia_lewforeiwn SET numberOfSheets=? WHERE busName=?";
             break;}
    case 2:  {query = "UPDATE stoixeia_lewforeiwn SET facilities=? WHERE busName=?";
             break;}
    }
	try
    {
		conn = DbConnect.getConnection();
        preparedStatement = (PreparedStatement) conn.prepareStatement(query);
        
        preparedStatement.setString(1, newValue);
        preparedStatement.setString(2, (String) tableLewforeia.getValueAt(row,0));
            
        preparedStatement.executeUpdate();
            
        tableLewforeia.setValueAt(newValue,row,col);
    }
    catch (Exception f)
    {
        f.printStackTrace();
    }
    finally
    {
        try { if (preparedStatement != null) preparedStatement.close(); } catch (SQLException f) {
                f.printStackTrace(); }
    }
}

public static DefaultTableModel getModelLewforeia(){
	return modelLewforeia;
}

}
