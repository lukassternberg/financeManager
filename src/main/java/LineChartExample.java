import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.JFrame;

public class LineChartExample extends JFrame {

  public LineChartExample(String title) {
    super(title);

    // Erstelle das Diagramm
    JFreeChart lineChart = ChartFactory.createXYLineChart(
        "Beispiel für ein Liniendiagramm",
        "Kategorie", // X-Achse Label
        "Wert", // Y-Achse Label
        createDataset(),
        PlotOrientation.VERTICAL,
        true, true, false);

    // Füge das Diagramm zu einem Swing-Panel hinzu
    ChartPanel chartPanel = new ChartPanel(lineChart);
    chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
    setContentPane(chartPanel);
  }

  private XYSeriesCollection createDataset() {
    XYSeries series1 = new XYSeries("Serie 1");
    series1.add(1, 1);
    series1.add(2, 4);
    series1.add(3, 3);
    series1.add(4, 5);
    series1.add(5, 4);

    XYSeries series2 = new XYSeries("Serie 2");
    series2.add(1, 5);
    series2.add(2, 7);
    series2.add(3, 6);
    series2.add(4, 8);
    series2.add(5, 4);

    XYSeriesCollection dataset = new XYSeriesCollection();
    dataset.addSeries(series1);
    dataset.addSeries(series2);

    return dataset;
  }

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(() -> {
      LineChartExample example = new LineChartExample("Liniendiagramm Beispiel");
      example.setSize(800, 600);
      example.setLocationRelativeTo(null);
      example.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      example.setVisible(true);
    });
  }
}
