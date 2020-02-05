package com.mycompany.javadomo.Screens.Thermoscreen;


import com.mycompany.javadomo.Actions.Thermoscreen.Del.DelThermoAction;
import com.mycompany.javadomo.SqlQuery.SensorQuery;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class DelThermoScreen extends JFrame {

    private JLabel thermoNameLabel;
    private JComboBox<String> thermoNameCb;
    private JButton validButton;

    public DelThermoScreen() throws Throwable {
        super();
        build();
    }

    private void build() throws Throwable {
        setTitle("DELETE THERMOSTAT");
        setSize(320, 240);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(buildContentPane());
    }

    private JPanel buildContentPane() throws Throwable {

        JPanel panel = new JPanel();
        GridLayout gl = new GridLayout(3, 0);
        SensorQuery roomQuery = new SensorQuery();
        ResultSet getDelThermo = roomQuery.getDelThermo();
        panel.setLayout(gl);

        thermoNameLabel = new JLabel("Which thermostat you wan't to delete");
        thermoNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(thermoNameLabel);

        String[] names = {"No Selection"};
        thermoNameCb = new JComboBox<String>(names);
        while (getDelThermo.next()) {
            thermoNameCb.addItem(getDelThermo.getString("thermostat_name"));
        }
        panel.add(thermoNameCb);

        validButton = new JButton(new DelThermoAction(this, "Delete"));
        panel.add(validButton);

        return panel;
    }

    public JComboBox<String> getThermoNameCb() {
        return thermoNameCb;
    }
}
