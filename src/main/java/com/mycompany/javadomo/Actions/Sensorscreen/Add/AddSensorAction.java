package com.mycompany.javadomo.Actions.Sensorscreen.Add;


import com.mycompany.javadomo.ErrorPopup.IntegerError;
import com.mycompany.javadomo.Screens.Sensorscreen.AddSensorScreen;
import com.mycompany.javadomo.SqlQuery.SensorQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

public class AddSensorAction extends AbstractAction {

    private AddSensorScreen panel;
    private int id;
    private String roomName;

    public AddSensorAction(AddSensorScreen addSensorScreen, String name, int id, String roomName) {
        super(name);

        this.panel = addSensorScreen;
        this.id = id;
        this.roomName = roomName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String nameValue = panel.getSensorNameTf().getText();
        String statuValue = panel.getStatuCb().getSelectedItem().toString();

        try{
            int interValue = Integer.parseInt(panel.getInterTf().getText());
            float maxValue = Float.valueOf(panel.getMaxTf().getText());
            float minValue = Float.valueOf(panel.getMinTf().getText());

            SensorQuery sensorQuery;
            try {
                sensorQuery = new SensorQuery();
                sensorQuery.createSensor(nameValue, statuValue, interValue, maxValue, minValue, id, roomName);
                panel.dispose();
            } catch (IOException | SQLException ex) {
                ex.printStackTrace();
            }
        } catch (NumberFormatException nux){
            IntegerError.displayIntError();
        }
    }
}