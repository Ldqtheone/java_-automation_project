package com.mycompany.javadomo.Actions.Sensorscreen.Del;

import com.mycompany.javadomo.Screens.Sensorscreen.DelSensorScreen;
import com.mycompany.javadomo.SqlQuery.SensorQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DelSensorAction extends AbstractAction {

    private DelSensorScreen panel;

    public DelSensorAction(DelSensorScreen delSensorScreen, String name) {
        super(name);

        this.panel = delSensorScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String nameValue = panel.getSensorNameCb().getSelectedItem().toString();
        System.out.println(nameValue);
        SensorQuery sensorQuery;
        try {
            sensorQuery = new SensorQuery();
            sensorQuery.delSensor(nameValue);
            panel.dispose();

        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}