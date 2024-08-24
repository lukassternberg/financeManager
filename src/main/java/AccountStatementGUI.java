import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.*;
import java.awt.*;
import java.util.Date;
import org.jfree.chart.ChartPanel;

public class AccountStatementGUI extends JFrame {

  private JList<Transaction> transactionList;
  private DefaultListModel<Transaction> transactionListModel;
  public DefaultListModel<Transaction> getTransactionListModel() {
    return transactionListModel;
  }

  public AccountStatementGUI() {

    // Fenster-Einstellungen
    setTitle("Kontoauszug");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // DefaultListModel erstellen und Transaktionen hinzufügen
    transactionListModel = new DefaultListModel<>();
    transactionListModel.addElement(new Transaction(LocalDate.now(), "Deposit", 100.00));
    transactionListModel.addElement(new Transaction(LocalDate.now(), "Withdrawal", -50.00));
    transactionListModel.addElement(new Transaction(LocalDate.now(), "Deposit", 200.00));

    // JList erstellen und platzieren
    transactionList = new JList<>(transactionListModel);
    transactionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    transactionList.setVisibleRowCount(10);
    add(new JScrollPane(transactionList), BorderLayout.WEST);

    //Kontoverlaufsübersicht
    // Füge das Diagramm zu einem Swing-Panel hinzu
    ChartPanel chartPanel = new ChartPanel(Charts. createLineChart(Charts.listModelToList(transactionListModel)));//in liste umwandeln
    chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
    //setContentPane(chartPanel);
    add(chartPanel, BorderLayout.EAST);



    //Button für neue Transaktion
    JButton addButton = new JButton("Add new Transaction");
    addButton.addActionListener(addTransaction());
    add(addButton, BorderLayout.SOUTH);


    // Fenster sichtbar machen
    setLocationRelativeTo(null); // Fenster in der Bildschirmmitte positionieren
    setVisible(true);
  }

  private ActionListener addTransaction() {
    return e -> {
      //open JDialog
      AddTransactionDialog dialog = new AddTransactionDialog(this, transactionListModel);
    };
  }



  public static void main(String[] args) {
    SwingUtilities.invokeLater(AccountStatementGUI::new);
  }
}
