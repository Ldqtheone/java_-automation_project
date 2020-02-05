package com.mycompany.javadomo.Screens.Sensorscreen;


import com.mycompany.javadomo.Actions.Sensorscreen.Del.DelSensorAction;
import com.mycompany.javadomo.SqlQuery.SensorQuery;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class DelSensorScreen extends JFrame {


    private JComboBox<String> sensorNameCb;
    private JButton validButton;
    private JLabel SensorNameLabel;

    public DelSensorScreen(int thermosId) throws Throwable{
        super();
        build(thermosId);
    }
    private void build(int thermosId) throws Throwable {
        setTitle("DELETE SENSOR");
        setSize(320, 240);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(buildContentPane(thermosId));
    }

    private JPanel buildContentPane(int thermosId) throws Throwable {

        JPanel panel = new JPanel();
        GridLayout gl = new GridLayout(3, 0);
        SensorQuery sensorQuery = new SensorQuery();
        ResultSet getDelSensor = sensorQuery.getDelSensor(thermosId);
        panel.setLayout(gl);

        SensorNameLabel = new JLabel("Which Sensor you wan't to delete");
        SensorNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(SensorNameLabel);

        String[] names = {"No Selection"};
        sensorNameCb = new JComboBox<String>(names);
        while (getDelSensor.next()) {
            sensorNameCb.addItem(getDelSensor.getString("sensor_name"));
        }
        panel.add(sensorNameCb);

        validButton = new JButton( new DelSensorAction(this, "Delete"));
        panel.add(validButton);

        return panel;
    }

    public JComboBox getSensorNameCb() {
        return sensorNameCb;
    }
}