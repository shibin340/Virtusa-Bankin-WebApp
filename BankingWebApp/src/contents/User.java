package contents;
import java.util.Set;

public class User {
	
	private int id;
	private String firstname;
	private String lastname;
	private String current;
	private String savings;
	private String email;
	private String password;
	private String currency;
	
	public User(String firstname, String lastname, String current, String savings, String email,
			String password, String currency) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.current = current;
		this.savings = savings;
		this.email = email;
		this.password = password;
		this.currency = currency;
	}

	public User(String firstname, String lastname, String current, String savings, String email,
			String password) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.current = current;
		this.savings = savings;
		this.email = email;
		this.password = password;
	}
	
	public User() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}
	public String getSavings() {
		return savings;
	}
	public void setSavings(String savings) {
		this.savings = savings;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
