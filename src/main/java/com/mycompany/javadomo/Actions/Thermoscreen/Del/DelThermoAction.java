package com.mycompany.javadomo.Actions.Thermoscreen.Del;


import com.mycompany.javadomo.Screens.Thermoscreen.DelThermoScreen;
import com.mycompany.javadomo.SqlQuery.SensorQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DelThermoAction extends AbstractAction {

    private DelThermoScreen panel;

    public DelThermoAction(DelThermoScreen delThermoScreen, String name) {
        super(name);

        this.panel = delThermoScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String nameValue = panel.getThermoNameCb().getSelectedItem().toString();
        System.out.println(nameValue);
        SensorQuery sensorQuery;
        try {
            sensorQuery = new SensorQuery();
            sensorQuery.delThermo(nameValue);
            panel.dispose();

        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}