import net.sourceforge.chart2d.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PieChart extends JFrame{
    private Map<String, String> mapNames = new HashMap<String, String>();
    private String[] legend;
    private ArrayList<String> list = new ArrayList<String>();

    public PieChart(Map<String, String> map){
        mapNames = map;

        for (Map.Entry<String, String> entry : mapNames.entrySet()) {
            list.add(entry.getKey());
        }

        legend = new String[list.size()];
        legend = list.toArray(legend);
    }

    public void makePieChart(String title) {
        Object2DProperties object2DProps = new Object2DProperties();
        object2DProps.setObjectTitleText (title);

        Chart2DProperties chart2DProperties = new Chart2DProperties();

        LegendProperties legendProperties = new LegendProperties();
        legendProperties.setLegendExistence(true);
        legendProperties.setLegendBorderColor(Color.black);
        legendProperties.setLegendBorderThicknessModel(1);
        legendProperties.setLegendLabelsFontColor(Color.black);
        legendProperties.setLegendLabelsTexts(legend);

        PieChart2DProperties pieChart2DProperties = new PieChart2DProperties();
        pieChart2DProperties.setPieLabelsFontColor (Color.black);
        pieChart2DProperties.setPieLabelsLinesColor (Color.black);
        pieChart2DProperties.setPieLabelsLinesDotsColor (Color.yellow);

        GraphChart2DProperties graphChart2DProperties = new GraphChart2DProperties();

        graphChart2DProperties.setNumbersAxisLabelsFontColor(Color.green);
        graphChart2DProperties.setChartDatasetCustomizeLeastValue(false);
        graphChart2DProperties.setChartDatasetCustomGreatestValue(0);

        GraphProperties graphProperties = new GraphProperties();
        graphProperties.setGraphBorderLeftBottomColor(Color.black);
        graphProperties.setGraphBorderRightTopColor(Color.gray);
        graphProperties.setGraphLabelsLinesColor(Color.gray);
        graphProperties.setGraphNumbersLinesColor(Color.gray);

        Dataset dataset = new Dataset(mapNames.size(), 1, 1);
        int i=0;
        for (Map.Entry<String, String> entry : mapNames.entrySet()) {
            dataset.set(i, 0, 0, Integer.parseInt(entry.getValue()));
            i++;
        }

        MultiColorsProperties multiColorsProperties = new MultiColorsProperties();

        PieChart2D pieChart2D = new PieChart2D();
        pieChart2D.setObject2DProperties (object2DProps);
        pieChart2D.setChart2DProperties (chart2DProperties);
        pieChart2D.setLegendProperties (legendProperties);
        pieChart2D.setPieChart2DProperties (pieChart2DProperties);
        pieChart2D.setDataset (dataset);
        pieChart2D.setMultiColorsProperties (multiColorsProperties);

        JFrame frame = new JFrame("Круговая диаграмма");
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);

        frame.add(pieChart2D);
        frame.setVisible(true);
    }
}