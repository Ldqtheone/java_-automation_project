package com.mycompany.javadomo.Screens.Thermoscreen;

import com.mycompany.javadomo.Actions.Thermoscreen.AddAction;
import com.mycompany.javadomo.Actions.Thermoscreen.DelAction;
import com.mycompany.javadomo.Actions.Thermoscreen.ToSensorAction;
import com.mycompany.javadomo.Actions.Thermoscreen.UpdateAction;
import com.mycompany.javadomo.Configuration.Cache;
import com.mycompany.javadomo.SqlQuery.SensorQuery;
import com.mycompany.javadomo.Tools.Clock.ClockPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class ThermoScreen extends JFrame {

    private JPanel panelU;
    private JComboBox<String>[] statusLabel = new JComboBox[100];
    private int id;
    private String roomName;
    private JTextField[] targetTf = new JTextField[100];
    private JTextField[] marginTf = new JTextField[100];
    private float[] lastTarget = new float[100];
    private float[] lastMargin = new float[100];


    public ThermoScreen() throws Throwable {
        super();
        build();
        this.pack();

    }

    private void build() throws Throwable {
        setTitle("THERMOSTATS");
        setSize(960, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setContentPane(buildContentPane());
    }

    private JPanel buildContentPane() throws Throwable {
        panelU = new JPanel();
        panelU.setLayout(new BorderLayout());
        panelU.setBackground(Color.LIGHT_GRAY);

        JPanel panelHeader = new JPanel();
        GridLayout gl = new GridLayout(1, 3);
        panelHeader.setLayout(gl);

        JPanel panelAction = new JPanel();
        GridLayout gla = new GridLayout(1, 2);
        panelAction.setLayout(gla);

        Cache cache = new Cache();
        JLabel label = new JLabel("List of Thermostats ");
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel actualUserNameLabel = new JLabel(cache.userFirstName + "'s home");
        actualUserNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        ClockPanel clock = new ClockPanel();

        panelHeader.add(label);
        panelHeader.add(actualUserNameLabel);
        panelHeader.add(clock);

        panelHeader.setBackground(Color.GRAY);
        panelU.add(panelHeader, BorderLayout.NORTH);


        JPanel panelThermos = new JPanel();
        GridLayout glaL = new GridLayout(0, 7);
        Dimension d = new Dimension();
        d.setSize(960, 720);
        panelThermos.setPreferredSize(d);
        SensorQuery sensorQuery = new SensorQuery();
        ResultSet getThermos = sensorQuery.getAllThermos();
        panelThermos.setLayout(glaL);

        glaL.setRows(getThermos.getRow());

        JLabel thermoNameLabel = new JLabel("Thermostat");
        thermoNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel roomNameLabel = new JLabel("Room");
        roomNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel state = new JLabel("On/Off");
        state.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel target = new JLabel("Target °C");
        target.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel margin = new JLabel("margin");
        margin.setFont(new Font("Arial", Font.PLAIN, 20) );

        JLabel sensor = new JLabel("Sensors");
        sensor.setFont(new Font("Arial", Font.PLAIN, 20) );


        JLabel blank = new JLabel("");
        blank.setFont(new Font("Arial", Font.PLAIN, 20));

        panelThermos.add(thermoNameLabel);
        panelThermos.add(roomNameLabel);
        panelThermos.add(state);
        panelThermos.add(target);
        panelThermos.add(margin);
        panelThermos.add(sensor);
        panelThermos.add(blank);

        String[] status = {"on", "off"};


        while (getThermos.next()) {
            id = getThermos.getInt("id");
            roomName = getThermos.getString("room_name");
            statusLabel[id] = new JComboBox<>(status);
            statusLabel[id].setSelectedItem(getThermos.getString("status"));
            targetTf[id] = new JTextField((int) getThermos.getFloat("target"));
            lastTarget[id] = getThermos.getFloat("target");
            targetTf[id].setText(String.valueOf(lastTarget[id]));
            marginTf[id] = new JTextField((int) getThermos.getFloat("margin"));
            lastMargin[id] = getThermos.getFloat("margin");
            marginTf[id].setText(String.valueOf(lastMargin[id]));
            JLabel nameLabel = new JLabel(getThermos.getString("thermostat_name"));
            JLabel roomLabel = new JLabel(getThermos.getString("room_name"));
            JButton count =new JButton(new ToSensorAction(this,(getThermos.getString("count")),id, roomName));
            JButton update = new JButton(new UpdateAction(this, "Update", id));

            panelThermos.add(nameLabel);
            panelThermos.add(roomLabel);
            panelThermos.add(statusLabel[id]);
            panelThermos.add(targetTf[id]);
            panelThermos.add(marginTf[id]);
            panelThermos.add(count);
            panelThermos.add(update);

        }


        JScrollPane scrollPane = new JScrollPane(panelThermos,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panelU.add(scrollPane, BorderLayout.CENTER);

        JButton addButton = new JButton(new AddAction(this,"ADD"));
        JButton delButton = new JButton(new DelAction( this,"DELETE"));

        panelAction.add(addButton);
        panelAction.add(delButton);
        panelU.add(panelAction,BorderLayout.SOUTH);

        return panelU;
    }


    public JComboBox getStatusCb(int id) {
        return statusLabel[id];
    }

    public JTextField getTargetTf(int id) {
        return targetTf[id];
    }
    public JTextField getMarginTf(int id) {
        return marginTf[id];
    }

    public float getLastTarget(int id) {
        return lastTarget[id];
    }

    public float getLastMargin(int id) {
        return lastMargin[id];
    }
}
