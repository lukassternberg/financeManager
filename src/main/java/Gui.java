import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame {

  public Gui() {
    // Titel des Fensters setzen
    setTitle("Finanzrechner");

    // Größe des Fensters festlegen
    setSize(400, 300);

    // Standardverhalten beim Schließen des Fensters festlegen
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Fenster in der Bildschirmmitte positionieren
    setLocationRelativeTo(null);

    // Layout-Manager festlegen
    setLayout(new BorderLayout());

    // Überschrift hinzufügen
    JLabel titleLabel = new JLabel("Willkommen zur Startseite", JLabel.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
    add(titleLabel, BorderLayout.NORTH);

    // Panel für die Buttons erstellen
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());

    // Button 1 erstellen
    JButton button1 = new JButton("Option 1");
    buttonPanel.add(button1);

    // Button 2 erstellen
    JButton button2 = new JButton("Option 2");
    buttonPanel.add(button2);

    // Buttons dem Hauptfenster hinzufügen
    add(buttonPanel, BorderLayout.CENTER);

    // Button 1 ActionListener hinzufügen
    button1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //JOptionPane.showInputDialog(null, "Option 1 wurde gewählt");
        new AccountStatementGUI();
      }
    });

    // Button 2 ActionListener hinzufügen
    button2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Option 2 wurde gewählt");
      }
    });

    // Fenster sichtbar machen
    setVisible(true);
  }

  public static void main(String[] args) {
    // Startseite-Objekt erstellen
    new Gui();
  }
}
