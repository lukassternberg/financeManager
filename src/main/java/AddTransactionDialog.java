import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class AddTransactionDialog extends JDialog {

  private JTextField amountField;
  private JTextField recipientField;
  private JTextField descriptionField;
  private AccountStatementGUI parentGui;

  private DefaultListModel<Transaction> transactionListModel;


  public AddTransactionDialog(JFrame parent, DefaultListModel<Transaction> transactionListModel, AccountStatementGUI parentGui) {
    super(parent, "Neue Transaktion hinzufügen", true);
    this.transactionListModel = transactionListModel;
    this.parentGui = parentGui;

    // Layout-Einstellungen
    setLayout(new GridLayout(4, 2));

    // Betragseingabe
    add(new JLabel("Betrag:"));
    amountField = new JTextField();
    add(amountField);

    // Empfängereingabe
    add(new JLabel("Empfänger:"));
    recipientField = new JTextField();
    add(recipientField);

    // Beschreibung/Buchungstext
    add(new JLabel("Buchungstext:"));
    descriptionField = new JTextField();
    add(descriptionField);

    // Buttons zum Hinzufügen oder Abbrechen
    JButton addButton = new JButton("Hinzufügen");
    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        addTransaction();
      }
    });
    add(addButton);

    JButton cancelButton = new JButton("Abbrechen");
    cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    add(cancelButton);

    // Dialog-Einstellungen
    pack();
    setLocationRelativeTo(parent);
    setVisible(true);
  }

  // Methode zum Hinzufügen der Transaktion
  private void addTransaction() {
    try {
      double amount = Double.parseDouble(amountField.getText());
      String recipient = recipientField.getText();
      String description = descriptionField.getText();

      if (recipient.isEmpty() || description.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Alle Felder müssen ausgefüllt werden.", "Fehler", JOptionPane.ERROR_MESSAGE);
        return;
      }

      Transaction transaction = new Transaction(LocalDateTime.now(), description + " - " + recipient, amount);
      transactionListModel.addElement(transaction);
      parentGui.updateChart();
      dispose();
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Bitte einen gültigen Betrag eingeben.", "Fehler", JOptionPane.ERROR_MESSAGE);
    }
  }
}
