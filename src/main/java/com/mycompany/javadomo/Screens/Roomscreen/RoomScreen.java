package com.mycompany.javadomo.Screens.Roomscreen;

import com.mycompany.javadomo.Actions.Roomscreen.AddAction;
import com.mycompany.javadomo.Actions.Roomscreen.DelAction;
import com.mycompany.javadomo.Actions.Thermoscreen.ToSensorAction;
import com.mycompany.javadomo.Tools.Clock.ClockPanel;
import com.mycompany.javadomo.Configuration.Cache;
import com.mycompany.javadomo.SqlQuery.RoomQuery;

import javax.swing.*;
import java.awt.*;

import java.sql.ResultSet;


public class RoomScreen extends JFrame {

    private JPanel panelU;


    public RoomScreen() throws Throwable {
        super();
        build();
        this.pack();
    }

    private void build() throws Throwable {
        setTitle("ROOMS");
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
        JLabel label = new JLabel("List of rooms ");
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel actualUserNameLabel = new JLabel(cache.userFirstName + "'s home");
        actualUserNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        ClockPanel clock = new ClockPanel();
        panelHeader.add(label);
        panelHeader.add(actualUserNameLabel);
        panelHeader.add(clock);


        panelHeader.setBackground(Color.GRAY);
        panelU.add(panelHeader,BorderLayout.NORTH);

        JPanel panelRooms = new JPanel();
        GridLayout glaL = new GridLayout(0, 4);
        RoomQuery roomQuery = new RoomQuery();
        ResultSet getRooms = roomQuery.getAllRooms();
        panelRooms.setLayout(glaL);
        Dimension d = new Dimension();
        d.setSize(960, 720);
        panelRooms.setPreferredSize(d);

        glaL.setRows(getRooms.getRow());

        JLabel roomNameLabel = new JLabel("Room");
        roomNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel descLabel = new JLabel("desc");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel bulbsLabel = new JLabel("bulbs");
        bulbsLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel sensorsLabel = new JLabel("sensor");
        sensorsLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        panelRooms.add(roomNameLabel);
        panelRooms.add(descLabel);
        panelRooms.add(bulbsLabel);
        panelRooms.add(sensorsLabel);

        while (getRooms.next()) {
          //TODO

            JLabel roomNameQLabel = new JLabel(getRooms.getString("room_name"));
            JLabel descQLabel = new JLabel(getRooms.getString("room_description"));
            JButton bulbsQLabel = new JButton(getRooms.getString("nb_bulbs"));
            JButton sensorsQLabel = new JButton(new ToSensorAction(this,(getRooms.getString("nb_sensor")),getRooms.getInt("tid"), getRooms.getString("room_name")));
            panelRooms.add(roomNameQLabel);
            panelRooms.add(descQLabel);
            panelRooms.add(bulbsQLabel);
            panelRooms.add(sensorsQLabel);
        }
        JScrollPane scrollPane = new JScrollPane(panelRooms,
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

}
