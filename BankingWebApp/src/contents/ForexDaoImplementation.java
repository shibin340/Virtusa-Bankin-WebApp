package contents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ForexDaoImplementation implements ForexDao{
	
	private static ForexDaoImplementation forexDaoImpl = null;
	 
    private Connection connection = Database.getConnection();
	
	@Override
	public String getMultiplier(String base, String target) {
		// TODO Auto-generated method stub
		String sql = "SELECT multiplier FROM forex WHERE base=? and target=?";
		try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, base);
            pstmt.setString(2, target);
            
            // Getting Transaction Detail
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()) {
                String mult = resultSet.getString(1);
                return mult;
            }
 
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
		return null;
	}
	
	public static ForexDao getInstance() {
        if (forexDaoImpl == null)
            forexDaoImpl = new ForexDaoImplementation();
 
        return forexDaoImpl;
    }

}
