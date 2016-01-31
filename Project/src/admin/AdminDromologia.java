package admin;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDromologia {
	private static JTable tableDromologia;
    private static Connection conn;
    private static ResultSet rs = null;
    private static PreparedStatement preparedStatement = null;
    private static String query;
    private static String afethria;
	private static String proorismos;
	private static String wraAnaxwrhshs;
	private static String busName;
    private static DefaultTableModel modelDromologia = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
          return false;
        }
    };
    
    public AdminDromologia(){
    	 modelDromologia.addColumn("Αφετηρία");
    	    modelDromologia.addColumn("Προορισμός");
    	    modelDromologia.addColumn("Ώρα Αναχώρησης");
    	    modelDromologia.addColumn("Λεωφορείο");
    	    
    	 query = "SELECT * FROM dromologia";
    	    try
    	    {
    	     conn = DbConnect.getConnection();
    	     preparedStatement = (PreparedStatement) conn.prepareStatement(query);
    	        rs = preparedStatement.executeQuery(query);
    	        
    	        while (rs.next())
    	        {
    	        afethria = rs.getString("Afethria");
    	        proorismos = rs.getString("Proorismos");
    	        wraAnaxwrhshs = rs.getString("wraAnaxwrhshs");
    	        busName = rs.getString("busName");
    	        
    	        modelDromologia.addRow(new Object[]{afethria,proorismos,wraAnaxwrhshs,busName});
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

public static void insertDromologia(){
	afethria = AddDromologiaListener.getAfethria();
	proorismos = AddDromologiaListener.getProorismo();
	wraAnaxwrhshs = AddDromologiaListener.getWraAnaxwrhshs();
	busName = AddDromologiaListener.getBusName();
	
	modelDromologia.addRow(new Object[]{afethria,proorismos,wraAnaxwrhshs,busName});
	query = "INSERT INTO dromologia VALUES(?,?,?,?)";
    try
    {
    	conn = DbConnect.getConnection();
    	preparedStatement = (PreparedStatement) conn.prepareStatement(query);
        
        preparedStatement.setString(1, afethria);
        preparedStatement.setString(2, proorismos);
        preparedStatement.setString(3, wraAnaxwrhshs);
        preparedStatement.setString(4, busName);
        
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

public static void deleteDromologia(){
	int row = DeleteDromologiaListener.getDeleteRow();
	tableDromologia = AdminMainFrame.getTableDromologia();
	
	query = "DELETE FROM dromologia WHERE busName=?";
    try
    {
    	conn = DbConnect.getConnection();
    	preparedStatement = (PreparedStatement) conn.prepareStatement(query);
        
        preparedStatement.setString(1, (String) tableDromologia.getValueAt(row,3));
        
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
    modelDromologia.removeRow(row);
}

public static void updateDromologia(){
	int col = EditDromologiaListener.getEditColumn();
	int row = EditDromologiaListener.getEditRow();
	String newValue = EditDromologiaListener.getNewValue();
	tableDromologia = AdminMainFrame.getTableDromologia();
	
	switch (col) {
    case 0:  {query = "UPDATE dromologia SET Afethria=? WHERE busName=?";
             break;}
    case 1:  {query = "UPDATE dromologia SET Proorismos=? WHERE busName=?";
             break;}
    case 2:  {query = "UPDATE dromologia SET wraAnaxwrhshs=? WHERE busName=?";
             break;}
    case 3:  {query = "UPDATE dromologia SET busName=? WHERE busName=?";
              break;}
    }
	try
    {
		conn = DbConnect.getConnection();
        preparedStatement = (PreparedStatement) conn.prepareStatement(query);
        
        preparedStatement.setString(1, newValue);
        preparedStatement.setString(2, (String) tableDromologia.getValueAt(row,3));
            
        preparedStatement.executeUpdate();
            
        modelDromologia.setValueAt(newValue,row,col);
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

public static DefaultTableModel getModelDromologia(){
	return modelDromologia;
}

}
