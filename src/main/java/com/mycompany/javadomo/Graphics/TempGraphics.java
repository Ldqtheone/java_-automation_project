package com.mycompany.javadomo.Graphics;

import com.mycompany.javadomo.SqlQuery.StatsQuery;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.jdbc.JDBCCategoryDataset;

import java.io.File;

public class TempGraphics {

    public TempGraphics() throws Throwable {

        JDBCCategoryDataset dataset = new StatsQuery().getTempValues();

        JFreeChart barChart = ChartFactory.createBarChart(
                "Temperatures for Lounge",
                "",
                "Temperatures",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        ChartUtils.saveChartAsPNG(new File("asset/graph/test.png"), barChart, 350, 300);
    }
}