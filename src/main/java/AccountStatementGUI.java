import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class AccountStatementGUI extends JFrame {

  private AccountStatement accountStatement;
  private JList<Transaction> transactionList;

  public AccountStatementGUI() {
    accountStatement = new AccountStatement();
    accountStatement.addTransaction(new Transaction(new Date(), "Deposit", 500.00));
    accountStatement.addTransaction(new Transaction(new Date(), "Withdrawal", -200.00));

    // Fenster-Einstellungen
    setTitle("Kontoauszug");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // TextArea für Transaktionen
    JTextArea textArea = new JTextArea();
    textArea.setEditable(false);
    add(new JScrollPane(textArea), BorderLayout.CENTER);

    // Button zum Aktualisieren der Anzeige
    JButton refreshButton = new JButton("Refresh");
    refreshButton.addActionListener(refresh(textArea));

    //Button für neue Transaktion
    JButton addButton = new JButton("Add new Transaction");
    addButton.addActionListener(addTransaction(this.accountStatement));

    add(refreshButton, BorderLayout.SOUTH);
    add(addButton, BorderLayout.WEST);

    // JList erstellen
    Transaction[] transactionArray = accountStatement.getTransactions().toArray(new Transaction[0]);
    transactionList = new JList<>(transactionArray);
    transactionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    transactionList.setVisibleRowCount(10);
    add(new JScrollPane(transactionList), BorderLayout.CENTER);

    // Fenster sichtbar machen
    setLocationRelativeTo(null); // Fenster in der Bildschirmmitte positionieren
    setVisible(true);
  }

  private ActionListener refresh(JTextArea textArea) {
    return e -> {
      textArea.setText("");
      for (Transaction transaction : accountStatement.getTransactions()) {
        textArea.append(transaction.toString() + "\n");
      }
      textArea.append("Current Balance: " + accountStatement.getBalance());
    };
  }

  private ActionListener addTransaction(AccountStatement accountStatement) {
    return e -> {
      //open JDialog
      AddTransactionDialog dialog = new AddTransactionDialog(this, accountStatement);
    };
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(AccountStatementGUI::new);
  }
}
