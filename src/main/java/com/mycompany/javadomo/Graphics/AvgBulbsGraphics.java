package com.mycompany.javadomo.Graphics;

import com.mycompany.javadomo.SqlQuery.StatsQuery;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

public class AvgBulbsGraphics extends JFrame {

    public AvgBulbsGraphics() throws Throwable {

        initUI();
    }

    private void initUI() throws Throwable {

        CategoryDataset dataset = createDataset();

        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Bar chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private CategoryDataset createDataset() throws Throwable {

        StatsQuery avgBulbs = new StatsQuery();
        ResultSet getBulbs = avgBulbs.getAvgBulbs();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        while (getBulbs.next()) {
            dataset.setValue((getBulbs.getInt("Duree") / 60), "Duration in minutes", getBulbs.getString("bulbs_name"));
        }
        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) throws IOException {

        JFreeChart barChart = ChartFactory.createBarChart(
                "Average light up bulbs",
                "Bulb Name",
                "Duration in minutes",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        ChartUtils.saveChartAsPNG(new File("asset/graph/avgBulb.png"), barChart, 1280, 360);

        return barChart;
    }
}