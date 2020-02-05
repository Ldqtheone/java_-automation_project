package com.mycompany.javadomo.Actions.Sensorscreen;

import com.mycompany.javadomo.Screens.Sensorscreen.DelSensorScreen;
import com.mycompany.javadomo.Screens.Sensorscreen.SensorScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DelAction extends AbstractAction {

    private SensorScreen panel;
    private int thermosId;

    public DelAction(SensorScreen sensorScreen, String name, int thermosId) {
        super(name);

        this.panel = sensorScreen;
        this.thermosId = thermosId;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DelSensorScreen delSensorScreen;

        try {
            delSensorScreen = new DelSensorScreen(thermosId);
            delSensorScreen.setVisible(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}