package com.mycompany.javadomo.Graphics;

import com.mycompany.javadomo.SqlQuery.StatsQuery;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

public class BulbColorGraphics extends JFrame {

    public BulbColorGraphics() throws Throwable {

        initUI();
    }

    private void initUI() throws Throwable {

        DefaultPieDataset dataset = createDataset();

        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Pie chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private DefaultPieDataset createDataset() throws Throwable {

        StatsQuery colorBulb = new StatsQuery();
        ResultSet getColor = colorBulb.getBulbsColor();

        DefaultPieDataset dataset = new DefaultPieDataset();

        while (getColor.next()) {
            dataset.setValue((getColor.getString("color")), getColor.getInt("Count"));
        }
        return dataset;
    }

    private JFreeChart createChart(DefaultPieDataset dataset) throws IOException {

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Bulbs Color",
                 dataset, true, false, false);

        System.out.println(dataset.getItemCount());

        for (int i = 0; i < dataset.getItemCount(); i++) {
            PiePlot piePlot = (PiePlot) pieChart.getPlot();
            String color = dataset.getKey(i).toString();
            StyleSheet s = new StyleSheet();
            Color c1 = s.stringToColor(color);
            System.out.println(c1);
            piePlot.setSectionPaint(i, c1);
        }

        ChartUtils.saveChartAsPNG(new File("asset/graph/colorBulbs.png"), pieChart, 600, 360);

        return pieChart;
    }
}