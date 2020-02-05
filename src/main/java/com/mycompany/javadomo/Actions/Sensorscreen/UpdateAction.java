package com.mycompany.javadomo.Actions.Sensorscreen;


import com.mycompany.javadomo.ErrorPopup.IntegerError;
import com.mycompany.javadomo.Screens.Sensorscreen.SensorScreen;
import com.mycompany.javadomo.SqlQuery.SensorQuery;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateAction extends AbstractAction {

    private SensorScreen panel;
    private int id;

    public UpdateAction(SensorScreen sensorScreen, String name, int id) {
        super(name);
        this.id = id;
        this.panel = sensorScreen;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String statusValue = panel.getStatusCb(this.id).getSelectedItem().toString();

        try{
            int intervalValue = Integer.parseInt(panel.getIntervalTf(this.id).getText());
            float maxValue = Float.parseFloat((panel.getMaxTf(this.id).getText()));
            float minValue = Float.parseFloat((panel.getMinTf(this.id).getText()));

            int idValue = id;

            SensorQuery sensorQuery;
            try {
                sensorQuery = new SensorQuery();
                sensorQuery.updateSensor(intervalValue, maxValue, minValue, statusValue, idValue);

            } catch (IOException | SQLException ex) {
                ex.printStackTrace();
            }
        }catch (NumberFormatException nb){
            IntegerError.displayIntError();
        }
    }
}