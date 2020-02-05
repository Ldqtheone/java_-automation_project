package com.mycompany.javadomo.Actions.Thermoscreen;


import com.mycompany.javadomo.ErrorPopup.IntegerError;
import com.mycompany.javadomo.Screens.Thermoscreen.ThermoScreen;
import com.mycompany.javadomo.SqlQuery.SensorQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateAction extends AbstractAction {

    private ThermoScreen panel;
    private int id;

    public UpdateAction(ThermoScreen thermoScreen, String name, int id) {
        super(name);
        this.id = id;
        this.panel = thermoScreen;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String statusValue = panel.getStatusCb(this.id).getSelectedItem().toString();

        try {
            float targetValue = Float.parseFloat((panel.getTargetTf(this.id).getText()));
            float marginValue = Float.parseFloat((panel.getMarginTf(this.id).getText()));

            int idValue = id;
            SensorQuery sensorQuery;

            try {
                sensorQuery = new SensorQuery();
                sensorQuery.updateThermo(statusValue, targetValue, marginValue, idValue);

            } catch (IOException | SQLException ex) {
                ex.printStackTrace();
            }
        } catch (NumberFormatException nu){
            IntegerError.displayFloatError();
        }
    }
}