package com.mycompany.javadomo.Actions.Sensorscreen;

import com.mycompany.javadomo.Screens.Sensorscreen.AddSensorScreen;
import com.mycompany.javadomo.Screens.Sensorscreen.SensorScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddAction extends AbstractAction {

    private SensorScreen panel;
    private int id;
    private String roomName;

    public AddAction(SensorScreen sensorScreen, String name, int id, String roonName) {
        super(name);

        this.panel = sensorScreen;
        this.id = id;
        this.roomName = roonName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AddSensorScreen addSensorScreen;
        try {
            addSensorScreen = new AddSensorScreen(id, roomName);
            addSensorScreen.setVisible(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}