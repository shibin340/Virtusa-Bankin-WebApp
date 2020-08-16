package contents;

public interface UserDao {
	int saveUser(User user);
	 
    void updateUser(User user);
 
    void deleteUser(int id);
 
    User findUserByMail(String email);
    
    User findUserById(int id);
    
    void updateProfile(User user);

	void updatePassword(User user);
}
