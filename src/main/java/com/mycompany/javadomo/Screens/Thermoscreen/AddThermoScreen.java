package com.mycompany.javadomo.Screens.Thermoscreen;


import com.mycompany.javadomo.Actions.Thermoscreen.Add.AddThermoAction;
import com.mycompany.javadomo.Actions.Thermoscreen.Add.DisableButtonAction;
import com.mycompany.javadomo.SqlQuery.RoomQuery;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class AddThermoScreen extends JFrame {


    private JButton validButton;
    private Timer timer;
    private JLabel thermoNameLabel;
    private JTextField marginTf;
    private JLabel marginLabel;
    private JTextField targetTf;
    private JLabel targetLabel;
    private JTextField thermoNameTf;
    private JComboBox<String> statu;
    private JLabel StatuLabel;
    private JComboBox<String> room;


    public AddThermoScreen() throws Throwable {
        super();
        build();
    }

    private void build() throws Throwable {
        setTitle("ADD THERMOSTAT");
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

        thermoNameLabel = new JLabel("Thermostat name");
        thermoNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(thermoNameLabel);

        thermoNameTf = new JTextField(20);
        panel.add(thermoNameTf);

        RoomQuery roomQuery = new RoomQuery();
        ResultSet getDelRooms = roomQuery.getDelRooms();
        String[] names = {};
        room = new JComboBox<String>(names);
        while (getDelRooms.next()) {
            room.addItem(getDelRooms.getString("room_name"));
        }
        panel.add(room);

        StatuLabel = new JLabel("On/Off");
        StatuLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(StatuLabel);

        String[] status = {"on", "off"};
        statu = new JComboBox<>(status);
        panel.add(statu);

        targetLabel = new JLabel("target °C");
        targetLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(targetLabel);

        targetTf = new JTextField(20);
        panel.add(targetTf);

        marginLabel = new JLabel("margin °C");
        marginLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(marginLabel);

        marginTf = new JTextField(20);
        panel.add(marginTf);

        validButton = new JButton(new AddThermoAction(this, "Validate"));
        validButton.setEnabled(false);
        panel.add(validButton);

        timer = new Timer(500, new DisableButtonAction(this, "Disable"));
        timer.start();

        return panel;
    }

    public JButton getValidButton() {
        return validButton;
    }


    public JComboBox<String> getStatuCb() {
        return statu;
    }

    public JTextField getMarginTf() {
        return marginTf;
    }

    public JTextField getTargetTf() {
        return targetTf;
    }

    public JTextField getThermoNameTf() {
        return thermoNameTf;
    }

    public JComboBox<String> getRoomCb() {
        return room;
    }
}
