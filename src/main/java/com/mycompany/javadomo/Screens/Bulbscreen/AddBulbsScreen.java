package com.mycompany.javadomo.Screens.Bulbscreen;

import com.mycompany.javadomo.Actions.Bulbsscreen.Add.AddBulbAction;
import com.mycompany.javadomo.Actions.Sensorscreen.Add.AddSensorAction;
import com.mycompany.javadomo.Actions.Sensorscreen.Add.DisableButtonAction;
import com.mycompany.javadomo.SqlQuery.RoomQuery;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.ResultSet;

public class AddBulbsScreen extends JFrame {


    private JButton validButton;
    private Timer timer;
    private JLabel bulbNameLabel;
    private JTextField bulbTf;
    private JComboBox<String> statu;
    private JLabel StatuLabel;
    private JComboBox<String> color;
    private JLabel colorLabel;
    private JComboBox<String> room;
    private JLabel roomLabel;
    private JLabel startLabel;
    private JLabel stopLabel;
    private JTextField startTf;
    private JTextField stopTf;


    public AddBulbsScreen () throws Throwable {
        super();
        build();
        this.pack();
    }

    private void build( ) throws Throwable {
        setTitle("ADD BULBS");
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(buildContentPane());
    }

    private JPanel buildContentPane() throws Throwable {

        JPanel panel = new JPanel();
        GridLayout gl = new GridLayout(17, 0);
        panel.setLayout(gl);

        bulbNameLabel = new JLabel("Bulb name");
        bulbNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(bulbNameLabel);

        bulbTf = new JTextField(20);
        panel.add(bulbTf);

        roomLabel = new JLabel("Room");
        roomLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(roomLabel);

        String[] rooms = {};
        RoomQuery roomQuery = new RoomQuery();
        ResultSet getDelRooms = roomQuery.getDelRooms();

        room = new JComboBox<>(rooms);
        while (getDelRooms.next()) {
            room.addItem(getDelRooms.getString("room_name"));
        }
        panel.add(room);

        StatuLabel = new JLabel("On/Off");
        StatuLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(StatuLabel);

        String[] status = {"on","off","scheduled"};
        statu = new JComboBox<>(status);
        panel.add(statu);

        colorLabel = new JLabel("Color");
        colorLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(colorLabel);

        String[] colors = {"red","purple","blue","green","yellow","orange","white","pink"};
        color = new JComboBox<>(colors);
        panel.add(color);


        startLabel = new JLabel("Start time");
        startLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(startLabel);

        startTf = new JTextField(20);
        startTf.setText("00:00:00");
        panel.add(startTf);

        stopLabel = new JLabel("Stop time");
        stopLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(stopLabel);

        stopTf = new JTextField(20);
        stopTf.setText("00:00:00");
        panel.add(stopTf);





        validButton = new JButton(new AddBulbAction(this, "Validate"));
       // validButton.setEnabled(false);
        panel.add(validButton);

       /* timer = new Timer(500, new DisableButtonAction(this, "Disable"));
        timer.start();*/

        return panel;
    }

    public JTextField getBulbTf() {
        return bulbTf;
    }

    public JComboBox<String> getStatu() {
        return statu;
    }

    public JComboBox<String> getColor() {
        return color;
    }

    public JComboBox<String> getRoom() {
        return room;
    }

    public JTextField getStartTf() {
        return startTf;
    }

    public JTextField getStopTf() {
        return stopTf;
    }

    public Timer getTimer() {
        return timer;
    }

}
