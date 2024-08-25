import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import javax.swing.*;
import java.awt.*;
import java.util.Date;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

//TODO: Chart aktualisierung fixen, skala fixen, mehrfache einträge täglich regeln

public class AccountStatementGUI extends JFrame {

  private JList<Transaction> transactionList;
  private DefaultListModel<Transaction> transactionListModel;
  private ChartPanel chartPanel;
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
    transactionListModel.addElement(new Transaction(LocalDateTime.of(LocalDate.of(2024, Calendar.AUGUST, 23), LocalTime.NOON), "Deposit", 100.00));
    transactionListModel.addElement(new Transaction(LocalDateTime.of(LocalDate.of(2024, Calendar.AUGUST, 24), LocalTime.NOON), "Withdrawal", -50.00));
    transactionListModel.addElement(new Transaction(LocalDateTime.of(LocalDate.of(2024, Calendar.AUGUST, 25), LocalTime.NOON), "Deposit", 200.00));

    // JList erstellen und platzieren
    transactionList = new JList<>(transactionListModel);
    transactionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    transactionList.setVisibleRowCount(10);
    JScrollPane jScrollPane = new JScrollPane(transactionList);
    jScrollPane.setPreferredSize(new Dimension(245, 600));
    add(jScrollPane, BorderLayout.WEST);

    //Kontoverlaufsübersicht
    // Füge das Diagramm zu einem Swing-Panel hinzu
    chartPanel = new ChartPanel(Charts. createLineChart(Charts.listModelToList(transactionListModel)));//in liste umwandeln
    chartPanel.setPreferredSize(new java.awt.Dimension(550, 600));
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
      AddTransactionDialog dialog = new AddTransactionDialog(this, transactionListModel, this);
    };
  }

public void updateChart() {
  chartPanel.revalidate();
  chartPanel.repaint();
}

  public static void main(String[] args) {
    SwingUtilities.invokeLater(AccountStatementGUI::new);
  }
}
