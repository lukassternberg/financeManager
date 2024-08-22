import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class AddTransactionDialog extends JDialog {

  private JTextField amountField;
  private JTextField recipientField;
  private JTextField descriptionField;
  private AccountStatement accountStatement;

  public AddTransactionDialog(JFrame parent, AccountStatement accountStatement) {
    super(parent, "Neue Transaktion hinzufügen", true);
    this.accountStatement = accountStatement;

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
        //Transaction transaction = new Transaction(new Date(), descriptionField.getText(), Double.parseDouble(amountField.getText()));
        //accountStatement.addTransaction(transaction);
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

      Transaction transaction = new Transaction(new Date(), description + " - " + recipient, amount);
      accountStatement.addTransaction(transaction);

      dispose();
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Bitte einen gültigen Betrag eingeben.", "Fehler", JOptionPane.ERROR_MESSAGE);
    }
  }
}
