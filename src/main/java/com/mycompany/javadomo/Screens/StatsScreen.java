package com.mycompany.javadomo.Screens;

import com.mycompany.javadomo.Actions.Homescreen.*;
import com.mycompany.javadomo.SqlQuery.StatsQuery;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;

public class StatsScreen extends JFrame {

    private JButton[] button = new JButton[100];
    private JPanel panelM;
    private JPanel panel;
    private JPanel panelP;
    private String imageLocation = "asset/graph/";
    private String imgExt = ".png";
    private JPanel panelRowT;
    private JPanel panelRowO;

    public StatsScreen() throws Throwable {
        super();
        build();
        this.pack();
    }


    private void build() throws Throwable {
        setTitle("STATS");
        setSize(450, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new OnCloseAction());
        setResizable(true);
        setLocationRelativeTo(null);
        setContentPane(buildContentPane());
    }

    private JPanel buildContentPane() throws Throwable {

        panelM = new JPanel();
        GridLayout gl = new GridLayout(2, 2);
        panelM.setLayout(gl);
        panelM.setBackground(Color.darkGray);



        panelRowO = new JPanel();
        GridLayout gla = new GridLayout(0, 2);
        panelRowO.setLayout(gla);
        panelRowO.setBackground(Color.darkGray);

        panelRowT = new JPanel();
        panelRowT.setBackground(Color.darkGray);

        StatsQuery statsQuery = new StatsQuery();
        ResultSet getCharts = statsQuery.getCharts();

        int i = 0;

        while (getCharts.next()) {
            System.out.println(imageLocation + getCharts.getString("graph_name") + imgExt);

            BufferedImage iconUser = ImageIO.read(new File(imageLocation + getCharts.getString("graph_name") + imgExt));
            button[i] = new JButton();
            button[i].setText("");
            button[i].setIcon(new ImageIcon(iconUser));

            JLabel nameLabel = new JLabel(getCharts.getString("graph_name"));

            StatsQuery avgTemp = new StatsQuery();
            ResultSet getTemp = avgTemp.getMinMaxTemp();

            panel = new JPanel(new BorderLayout());
            panel.setBackground(Color.LIGHT_GRAY);
            panel.setBorder(BorderFactory.createMatteBorder(
                    5, 5, 5, 5, Color.darkGray));

            panelP = new JPanel();
            GridLayout glal = new GridLayout(0, 2);
            panelP.setLayout(glal);
            panelP.setBackground(Color.lightGray);

            if(getCharts.getString("graph_name").equals("avgTemp")) {
                while (getTemp.next()) {
                    JLabel minMaxTemp = new JLabel(String.valueOf("Min : " + getTemp.getInt("minTemp") + "°C  " + "Max : " + getTemp.getInt("maxTemp") + "°C  "));
                    panelP.add(minMaxTemp);
                }
            }

            panel.add(button[i], BorderLayout.CENTER);
            panelP.add(nameLabel);
            panel.add(panelP, BorderLayout.SOUTH);
            if(getCharts.getString("graph_name").equals("avgBulb")) {
                panelRowT.add(panel);
            } else {
                panelRowO.add(panel);
            }
            i++;
        }
        panelM.add(panelRowO);
        panelM.add(panelRowT);

        return panelM;
    }
}
