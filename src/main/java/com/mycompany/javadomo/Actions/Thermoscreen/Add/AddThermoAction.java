package com.mycompany.javadomo.Actions.Thermoscreen.Add;


import com.mycompany.javadomo.ErrorPopup.IntegerError;
import com.mycompany.javadomo.Screens.Thermoscreen.AddThermoScreen;
import com.mycompany.javadomo.SqlQuery.SensorQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class AddThermoAction extends AbstractAction {

    private AddThermoScreen panel;


    public AddThermoAction(AddThermoScreen addThermoScreen, String name) {
        super(name);

        this.panel = addThermoScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String nameValue = panel.getThermoNameTf().getText();
        String roomValue =  panel.getRoomCb().getSelectedItem().toString();
        String statuValue = panel.getStatuCb().getSelectedItem().toString();

        try {
            float targetValue = Float.valueOf(panel.getTargetTf().getText());
            float marginValue = Float.valueOf(panel.getMarginTf().getText());

            SensorQuery sensorQuery;
            try {
                sensorQuery = new SensorQuery();
                sensorQuery.createThermo(nameValue, roomValue, statuValue, targetValue, marginValue);
                panel.dispose();
            } catch (IOException | SQLException ex) {
                ex.printStackTrace();
            }
        }catch (NumberFormatException nu){
            IntegerError.displayFloatError();
        }
    }
}