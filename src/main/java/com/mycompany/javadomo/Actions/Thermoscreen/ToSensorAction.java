package com.mycompany.javadomo.Actions.Thermoscreen;

import com.mycompany.javadomo.Screens.Sensorscreen.SensorScreen;
import com.mycompany.javadomo.Screens.Thermoscreen.ThermoScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ToSensorAction extends AbstractAction {

    private int id;
    private String roomName;
    private JFrame panel;

    public ToSensorAction(JFrame thermoScreen, String name, int id, String roomName) {
        super(name);

        this.panel = thermoScreen;
        this.id = id;
        this.roomName = roomName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SensorScreen sensorScreen;
        try {
            sensorScreen = new SensorScreen(id, roomName);
            sensorScreen.setVisible(true);
            panel.dispose();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}