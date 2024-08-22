import java.util.ArrayList;
import java.util.List;

public class AccountStatement {
  private List<Transaction> transactions;

  public AccountStatement() {
    transactions = new ArrayList<>();
  }

  public void addTransaction(Transaction transaction) {
    transactions.add(transaction);
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public double getBalance() {
    double balance = 0;
    for (Transaction transaction : transactions) {
      balance += transaction.getAmount();
    }
    return balance;
  }
}
