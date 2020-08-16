package contents;

public class Transaction {
	private int transaction_id;
	private int user_toid;
	private int user_fromid;
	private String type;
	private String time;
	private String status;
	private String amount;
	
	
	public Transaction() {
		super();
	}
	

	public Transaction(int user_toid, int user_fromid, String type, String time, String status, String amount) {
		super();
		this.user_toid = user_toid;
		this.user_fromid = user_fromid;
		this.type = type;
		this.time = time;
		this.status = status;
		this.amount = amount;
	}



	public Transaction(int transaction_id, int user_fromid, String type, String time, String status, String amount, int user_toid) {
		super();
		this.transaction_id = transaction_id;
		this.user_fromid = user_fromid;
		this.type = type;
		this.time = time;
		this.status = status;
		this.amount = amount;
		this.user_toid = user_toid;
	}

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public int getUser_toid() {
		return user_toid;
	}

	public void setUser_toid(int user_toid) {
		this.user_toid = user_toid;
	}

	public int getUser_fromid() {
		return user_fromid;
	}

	public void setUser_fromid(int user_fromid) {
		this.user_fromid = user_fromid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
