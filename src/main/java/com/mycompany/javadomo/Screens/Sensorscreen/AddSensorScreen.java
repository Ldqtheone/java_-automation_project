package com.mycompany.javadomo.Screens.Sensorscreen;


import com.mycompany.javadomo.Actions.Sensorscreen.Add.DisableButtonAction;
import com.mycompany.javadomo.Actions.Sensorscreen.Add.AddSensorAction;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AddSensorScreen extends JFrame {


    private JButton validButton;
    private Timer timer;
    private JLabel sensorNameLabel;
    private JTextField minTf;
    private JLabel minLabel;
    private JTextField maxTf;
    private JLabel maxLabel;
    private JTextField interTf;
    private JLabel interLabel;
    private JTextField sensorNameTf;
    private JComboBox<String> statu;
    private JLabel StatuLabel;


    public AddSensorScreen(int id, String roomName) throws IOException {
        super();
        build(id, roomName);
    }

    private void build(int id ,String roomName) throws IOException {
        setTitle("ADD SENSOR");
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(buildContentPane(id, roomName));
    }

    private JPanel buildContentPane(int id, String roomName) throws IOException {

        JPanel panel = new JPanel();
        GridLayout gl = new GridLayout(17, 0);
        panel.setLayout(gl);

        sensorNameLabel = new JLabel("Sensor name");
        sensorNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(sensorNameLabel);

        sensorNameTf = new JTextField(20);
        panel.add(sensorNameTf);

        interLabel = new JLabel("Capture interval");
        interLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(interLabel);

        interTf = new JTextField(20);
        panel.add(interTf);

        StatuLabel = new JLabel("On/Off");
        StatuLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(StatuLabel);

        String[] status = {"on", "off"};
        statu = new JComboBox<>(status);
        panel.add(statu);

        maxLabel = new JLabel("max °C");
        maxLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(maxLabel);

        maxTf = new JTextField(20);
        panel.add(maxTf);

        minLabel = new JLabel("min °C");
        minLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(minLabel);

        minTf = new JTextField(20);
        panel.add(minTf);

        validButton = new JButton(new AddSensorAction(this, "Validate", id, roomName));
        validButton.setEnabled(false);
        panel.add(validButton);

        timer = new Timer(500, new DisableButtonAction(this, "Disable"));
        timer.start();

        return panel;
    }

    public JButton getValidButton() {
        return validButton;
    }

    public JTextField getSensorNameTf() {
        return sensorNameTf;
    }

    public JTextField getInterTf() {
        return interTf;
    }

    public JTextField getMaxTf() {
        return maxTf;
    }

    public JTextField getMinTf() {
        return minTf;
    }

    public JComboBox<String> getStatuCb() {
        return statu;
    }
}
