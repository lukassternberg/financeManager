import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
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



    return chart;
  }

  public static TimeSeriesCollection createDataset(List<Transaction> transactions) {
    TimeSeries series = new TimeSeries("Konto");
    HashMap<Transaction, Double> balances = Charts.getBalanceAfterEachTransaction(transactions);
    for (Transaction transaction : transactions) {
      LocalDateTime date = transaction.getDate();
      double balance = balances.get(transaction);
      series.addOrUpdate(new Day(date.getDayOfMonth(), date.getMonthValue(), date.getYear()), balance);
    }
    return new TimeSeriesCollection(series);
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
