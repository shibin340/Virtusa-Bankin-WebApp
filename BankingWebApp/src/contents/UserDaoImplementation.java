package contents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;


public class UserDaoImplementation implements UserDao {

	private static UserDaoImplementation userDaoImpl = null;
	 
    private Connection connection = Database.getConnection();
    
    @Override
    public int saveUser(User user) {
        String sql = "INSERT INTO auth_users"+
                    "(auth_firstname, auth_lastname, auth_email, auth_password,auth_currbal, auth_savebal,currency)"
                    + "VALUES(?,?,?,?,10000,10000,?)";
        int id = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getFirstname());
            pstmt.setString(2, user.getLastname());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getCurrency());
            // Creating Customer Account
            if (pstmt.executeUpdate() > 0) {
                // Returns Generated Primary Key
                ResultSet rs = pstmt.getGeneratedKeys();
                                                              
                if (rs.next())
                    id = rs.getInt(1);
                user.setId(id);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
 
        return id;
    }

	@Override
	public void updateUser(User user) {
		String sql = "UPDATE auth_users SET"
                +" auth_savebal=?, auth_currbal=? "
                + "WHERE idauth_users=?";

    try {
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, user.getSavings());
        pstmt.setString(2, user.getCurrent());
        pstmt.setLong(3, user.getId());

        // Update Customer Account
        pstmt.executeUpdate();

    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
		
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM auth_users WHERE auth_userid=?";
		 
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
 
            // Delete Customer Account
            pstmt.executeUpdate();
 
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
		
	}

	@Override
	public User findUserByMail(String mail) {
		String sql = "SELECT * FROM auth_users WHERE auth_email=?";
		 
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, mail);
 
            // Getting Customer Detail
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setFirstname(resultSet.getString(2));
                user.setLastname(resultSet.getString(3));
                user.setEmail(resultSet.getString(6));
                user.setPassword(resultSet.getString(7));
                user.setSavings(resultSet.getString(5));
                user.setCurrent(resultSet.getString(4));
                user.setCurrency(resultSet.getString(8));
                return user;
            }
 
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
 
        return null;
	}
	
	public static UserDao getInstance() {
        if (userDaoImpl == null)
            userDaoImpl = new UserDaoImplementation();
 
        return userDaoImpl;
    }

	@Override
	public User findUserById(int id) {
		String sql = "SELECT * FROM auth_users WHERE idauth_users=?";
		 
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
 
            // Getting Customer Detail
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setFirstname(resultSet.getString(2));
                user.setLastname(resultSet.getString(3));
                user.setEmail(resultSet.getString(6));
                user.setPassword(resultSet.getString(7));
                user.setSavings(resultSet.getString(5));
                user.setCurrent(resultSet.getString(4));
                user.setCurrency(resultSet.getString(8));
                return user;
            }
 
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
 
        return null;
	}

	@Override
	public void updateProfile(User user) {
		String sql = "UPDATE auth_users SET"
                +" auth_firstname=?, auth_lastname=?, auth_email=? "
                + "WHERE idauth_users=?";

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, user.getFirstname());
			pstmt.setString(2, user.getLastname());
			pstmt.setString(3, user.getEmail());
			pstmt.setInt(4, user.getId());
			// Update Customer Account
			pstmt.executeUpdate();

    	} catch (Exception ex) {
        System.out.println(ex.getMessage());
    	}
	}
	
	@Override
	public void updatePassword(User user) {
		String sql = "UPDATE auth_users SET"
                +" auth_password=? "
                + "WHERE idauth_users=?";

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setInt(2, user.getId());
			// Update Customer Account
			pstmt.executeUpdate();

    	} catch (Exception ex) {
        System.out.println(ex.getMessage());
    	}
	}


}
