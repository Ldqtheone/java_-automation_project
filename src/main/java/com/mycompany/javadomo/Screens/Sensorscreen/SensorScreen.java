package com.mycompany.javadomo.Screens.Sensorscreen;

import com.mycompany.javadomo.Actions.Sensorscreen.AddAction;
import com.mycompany.javadomo.Actions.Sensorscreen.DelAction;
import com.mycompany.javadomo.Actions.Sensorscreen.UpdateAction;
import com.mycompany.javadomo.Configuration.Cache;
import com.mycompany.javadomo.SqlQuery.SensorQuery;
import com.mycompany.javadomo.Tools.Clock.ClockPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;


public class SensorScreen extends JFrame {

    private JPanel panelU;
    private JComboBox<String>[] statusLabel = new JComboBox[100];
    private int id;
    private String roomName;
    private JTextField[] intervalLabel = new JTextField[100];
    private JTextField[] maxLabel = new JTextField[100];
    private JTextField[] minLabel = new JTextField[100];

    public SensorScreen(int id, String roomName) throws Throwable {
        super();
        build(id, roomName);
        this.pack();

    }

    private void build(int id, String roomName) throws Throwable {
        setTitle("SENSOR");
        setSize(960, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setContentPane(buildContentPane(id, roomName));
    }

    private JPanel buildContentPane(int thermosId, String roomName) throws Throwable {
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
        JLabel label = new JLabel("List of Sensor ");
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel actualUserNameLabel = new JLabel(cache.userFirstName + "'s home");
        actualUserNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        ClockPanel clock = new ClockPanel();

        panelHeader.add(label);
        panelHeader.add(actualUserNameLabel);
        panelHeader.add(clock);

        panelHeader.setBackground(Color.GRAY);
        panelU.add(panelHeader, BorderLayout.NORTH);


        JPanel panelSensor = new JPanel();
        GridLayout glaL = new GridLayout(0, 7);
        Dimension d = new Dimension();
        d.setSize(960, 720);
        panelSensor.setPreferredSize(d);
        SensorQuery sensorQuery = new SensorQuery();
        ResultSet getSensor = sensorQuery.getAllSensor(thermosId);
        panelSensor.setLayout(glaL);


        glaL.setRows(getSensor.getRow());

        JLabel SensorNameLabel = new JLabel("Sensor");
        SensorNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel roomNameLabel = new JLabel("Room");
        roomNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel state = new JLabel("On/Off");
        state.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel interval = new JLabel("Interval");
        interval.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel max = new JLabel("max °C");
        max.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel min = new JLabel("min °C");
        min.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel blank = new JLabel("");
        min.setFont(new Font("Arial", Font.PLAIN, 20));

        panelSensor.add(SensorNameLabel);
        panelSensor.add(roomNameLabel);
        panelSensor.add(state);
        panelSensor.add(interval);
        panelSensor.add(max);
        panelSensor.add(min);
        panelSensor.add(blank);

        String[] status = {"on", "off"};


        while (getSensor.next()) {
            id = getSensor.getInt("id");
            roomName = getSensor.getString("room_name");
            statusLabel[id] = new JComboBox<>(status);
            statusLabel[id].setSelectedItem(getSensor.getString("status"));
            intervalLabel[id] = new JTextField(getSensor.getInt("capture_interval"));
            intervalLabel[id].setText(String.valueOf(getSensor.getInt("capture_interval")));
            maxLabel[id] = new JTextField((int) getSensor.getFloat("max_temp"));
            maxLabel[id].setText(String.valueOf(getSensor.getFloat("max_temp")));
            minLabel[id] = new JTextField((int) getSensor.getFloat("min_temp"));
            minLabel[id].setText(String.valueOf(getSensor.getFloat("min_temp")));
            JLabel nameLabel = new JLabel(getSensor.getString("sensor_name"));
            JLabel roomLabel = new JLabel(getSensor.getString("room_name"));
            JButton update = new JButton(new UpdateAction(this, "Update", id));

            panelSensor.add(nameLabel);
            panelSensor.add(roomLabel);
            panelSensor.add(statusLabel[id]);
            panelSensor.add(intervalLabel[id]);
            panelSensor.add(maxLabel[id]);
            panelSensor.add(minLabel[id]);
            panelSensor.add(update);
        }


        JScrollPane scrollPane = new JScrollPane(panelSensor,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panelU.add(scrollPane, BorderLayout.CENTER);

        JButton addButton = new JButton(new AddAction(this, "ADD", thermosId, roomName));
        JButton delButton = new JButton(new DelAction(this, "DELETE", thermosId));

        panelAction.add(addButton);
        panelAction.add(delButton);
        panelU.add(panelAction, BorderLayout.SOUTH);

        return panelU;
    }


    public JComboBox getStatusCb(int id) {
        return statusLabel[id];
    }

    public JTextField getIntervalTf(int id) {
        return intervalLabel[id];
    }

    public JTextField getMaxTf(int id) {
        return maxLabel[id];
    }

    public JTextField getMinTf(int id) {
        return minLabel[id];
    }
}
