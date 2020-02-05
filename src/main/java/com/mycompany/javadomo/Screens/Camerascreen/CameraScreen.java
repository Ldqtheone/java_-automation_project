package com.mycompany.javadomo.Screens.Camerascreen;

import com.mycompany.javadomo.Actions.Camerascreen.UpdateAction;
import com.mycompany.javadomo.Actions.Camerascreen.AddAction;
import com.mycompany.javadomo.Actions.Camerascreen.DelAction;
import com.mycompany.javadomo.Configuration.Cache;
import com.mycompany.javadomo.SqlQuery.CameraQuery;
import com.mycompany.javadomo.Tools.Clock.ClockPanel;

import javax.swing.*;
import java.awt.*;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class CameraScreen extends JFrame {

    private JPanel panelU;
    private JComboBox<String>[] rangeLabel = new JComboBox[100];
    private JComboBox<String>[] statusLabel = new JComboBox[100];
    private JTextField[] startDateField = new JTextField[100];
    private JTextField[] endDateField = new JTextField[100];
    private int id;

    public CameraScreen() throws Throwable {
        super();
        build();
        this.pack();
    }

    private void build() throws Throwable {
        setTitle("CAMERA");
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
        GridLayout gla = new GridLayout(0, 2);
        panelAction.setLayout(gla);

        Cache cache = new Cache();
        JLabel label = new JLabel("List of Camera ");
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel actualUserNameLabel = new JLabel(cache.userFirstName + "'s home");
        actualUserNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        ClockPanel clock = new ClockPanel() ;

        panelHeader.add(label);
        panelHeader.add(actualUserNameLabel);
        panelHeader.add(clock);

        panelHeader.setBackground(Color.GRAY);
        panelU.add(panelHeader,BorderLayout.NORTH);

        JPanel panelCamera = new JPanel();
        GridLayout glaL = new GridLayout(0, 7);
        CameraQuery cameraQuery = new CameraQuery();
        ResultSet getCamera = cameraQuery.getAllCamera();
        panelCamera.setLayout(glaL);

        Dimension d = new Dimension();
        d.setSize(960, 720);
        panelCamera.setPreferredSize(d);

        glaL.setRows(getCamera.getRow());

        JLabel cameraNameLabel = new JLabel("Camera");
        cameraNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel roomNameLabel = new JLabel("Room");
        roomNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel cameraRange = new JLabel("Range");
        cameraRange.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel state = new JLabel("On/Off");
        state.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel startDate = new JLabel("Start-Date");
        startDate.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel endDate = new JLabel("End-Date");
        endDate.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel action = new JLabel("");
        state.setFont(new Font("Arial", Font.PLAIN, 20));

        panelCamera.add(cameraNameLabel);
        panelCamera.add(roomNameLabel);
        panelCamera.add(cameraRange);
        panelCamera.add(state);
        panelCamera.add(startDate);
        panelCamera.add(endDate);
        panelCamera.add(action);

        String[] status = {"on","off"};
        String[] range = {"0.5","1","1.5","2","2.5","3","3.5","4"};

        while (getCamera.next()) {
            id = getCamera.getInt("id");
            statusLabel[id] = new JComboBox<>(status);
            statusLabel[id].setSelectedItem(getCamera.getString("status"));
            rangeLabel[id] = new JComboBox<>(range);
            rangeLabel[id].setSelectedItem(getCamera.getString("detection_range"));
            JLabel nameLabel = new JLabel(getCamera.getString("camera_name"));
            JLabel roomLabel = new JLabel(getCamera.getString("room_name"));
            startDateField[id] = new JTextField(String.valueOf(new SimpleDateFormat("HH:mm:ss")));
            startDateField[id].setText(String.valueOf(getCamera.getTime("start_time")));
            endDateField[id] = new JTextField(String.valueOf(new SimpleDateFormat("HH:mm:ss")));
            endDateField[id].setText(String.valueOf(getCamera.getTime("end_time")));

            JButton update = new JButton(new UpdateAction(this, "Update", id));

            panelCamera.add(nameLabel);
            panelCamera.add(roomLabel);
            panelCamera.add(rangeLabel[id]);
            panelCamera.add(statusLabel[id]);
            panelCamera.add(startDateField[id]);
            panelCamera.add(endDateField[id]);
            panelCamera.add(update);
        }
        JScrollPane scrollPane = new JScrollPane(panelCamera,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panelU.add(scrollPane,BorderLayout.CENTER);

        JButton addButton = new JButton(new AddAction(this,"ADD"));
        JButton delButton = new JButton(new DelAction( this,"DELETE"));

        panelAction.add(addButton);
        panelAction.add(delButton);

        panelU.add(panelAction,BorderLayout.SOUTH);

        return panelU;
    }

    public JTextField getStartDate(int id) {
        return startDateField[id];
    }

    public JTextField getEndDate(int id) {
        return endDateField[id];
    }

    public JComboBox getRangeCb(int id) {
        return rangeLabel[id];
    }

    public JComboBox getStatusCb(int id) {
        return statusLabel[id];
    }
}
