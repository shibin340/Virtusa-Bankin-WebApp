package contents;

import java.util.List;

public interface TransactionDao {
	void updateTransaction(Transaction trans);
	
	int newTransaction(Transaction trans);
	
	List<Transaction> findTransactionsById(int user_id);
}
