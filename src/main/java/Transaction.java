import java.time.LocalDateTime;

public class Transaction {
  private LocalDateTime date;
  private String description;
  private double amount;

  public Transaction(LocalDateTime date, String description, double amount) {
    this.date = date;
    this.description = description;
    this.amount = amount;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public String getDescription() {
    return description;
  }

  public double getAmount() {
    return amount;
  }

  @Override
  public String toString() {
    return String.format("%s - %s: %.2f", date.toString(), description, amount);
  }
}
