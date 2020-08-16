package contents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImplementation implements TransactionDao{
	
	private static TransactionDaoImplementation transDaoImpl = null;
	 
    private Connection connection = Database.getConnection();
    
	@Override
	public void updateTransaction(Transaction trans) {
		// TODO Auto-generated method stub
		String sql = "UPDATE transaction SET"
                +" transaction_status=? "
                + "WHERE transaction_id=?";

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, trans.getStatus());
			pstmt.setInt(2, trans.getTransaction_id());
			//pstmt.setLong(3, user.getId());
			//Update Customer Account
			pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public int newTransaction(Transaction trans) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO transactions"+
                "(user_fromid, transaction_type, transaction_time, transaction_status, transaction_amount, user_toid)"
                + "VALUES(?,?,?,?,?,?)";
		int id = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            //pstmt.setInt(1, trans.getTransaction_id());
            pstmt.setInt(1, trans.getUser_fromid());
            pstmt.setString(2, trans.getType());
            pstmt.setString(3, trans.getTime());
            pstmt.setString(4, trans.getStatus());
            pstmt.setString(5, trans.getAmount());
            pstmt.setInt(6, trans.getUser_toid());
            // Creating Customer Account
            if (pstmt.executeUpdate() > 0) {
                // Returns Generated Primary Key
                ResultSet rs = pstmt.getGeneratedKeys();
                                                              
                if (rs.next())
                    id = rs.getInt(1);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
 
        return id;
	}

	@Override
	public List<Transaction> findTransactionsById(int user_id) {
		String sql = "SELECT * FROM transactions WHERE user_toid=? or user_fromid=? order by transaction_id desc";
		List<Transaction> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, user_id);
            pstmt.setInt(2, user_id);
            
            // Getting Transaction Detail
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()) {
                Transaction user = new Transaction();
                user.setTransaction_id(resultSet.getInt(1));
                user.setUser_fromid(resultSet.getInt(2));
                user.setType(resultSet.getString(3));
                user.setTime(resultSet.getString(4));
                user.setStatus(resultSet.getString(5));
                user.setAmount(resultSet.getString(6));
                user.setUser_toid(resultSet.getInt(7));
                list.add(user);
            }
 
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
 
        return list;
	}
	
	public static TransactionDao getInstance() {
        if (transDaoImpl == null)
            transDaoImpl = new TransactionDaoImplementation();
 
        return transDaoImpl;
    }

}
