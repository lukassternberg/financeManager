import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultListModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.time.LocalDate;
import java.time.DayOfWeek;

public class Charts {

  public static JFreeChart createLineChart(List<Transaction> transactions) {
    // Aktuelles Datum
    LocalDate today = LocalDate.now();

    JFreeChart chart = ChartFactory.createXYLineChart(
        "Account overview",
        "Date", // X-Achse Label
        "Balance", // Y-Achse Label
        createDataset(transactions),
        PlotOrientation.VERTICAL,
        true, true, false);

    // Plot und X-Achse holen
    XYPlot plot = chart.getXYPlot();
    DateAxis xAxis = (DateAxis) plot.getDomainAxis(); // X-Achse als DateAxis

    // Anpassung der X-Achse auf die letzten 6 Tage
    Date startDate = Date.from(today.minusDays(6).atStartOfDay(ZoneId.systemDefault()).toInstant());
    Date endDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
    xAxis.setRange(startDate, endDate);

    // Datum-Format für die X-Achse festlegen
    xAxis.setDateFormatOverride(new SimpleDateFormat("dd.MM.yyyy"));

    return chart;
  }

  public static XYSeriesCollection createDataset(List<Transaction> transactions) {
    XYSeries series = new XYSeries("Konto");
    HashMap<Transaction, Double> balances = Charts.getBalanceAfterEachTransaction(transactions);
    for (Transaction transaction : transactions) {
      double balance = balances.get(transaction);
      series.add(transaction.getDate().toEpochDay(), balance);
    }
    return new XYSeriesCollection(series);
  }


  public static HashMap<Transaction, Double> getBalanceAfterEachTransaction(List<Transaction> transactions) {
    double tmpBalance = 0;
    HashMap<Transaction, Double> res = new HashMap<Transaction, Double>();

    for (Transaction transaction : transactions) {
      tmpBalance += transaction.getAmount();
      res.put(transaction, tmpBalance);
    }
    return res;
  }

  public static List<Transaction> listModelToList(DefaultListModel<Transaction> listModel) {
    List<Transaction> res = new LinkedList<Transaction>();
    for (int i = 0; i < listModel.getSize(); i++) {
      res.add(listModel.getElementAt(i));
    }
    return res;
  }
}
