package com.mycompany.javadomo.Actions.Bulbsscreen;

import com.mycompany.javadomo.Arduino.BulbsArduino;
import com.mycompany.javadomo.ErrorPopup.ParseDateError;
import com.mycompany.javadomo.Screens.Bulbscreen.BulbsScreen;
import com.mycompany.javadomo.SqlQuery.BulbsQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateAction extends AbstractAction {

    private BulbsScreen panel;
    private int id;
    private String colorValue;
    private BulbsArduino bulbsArduino;

    public UpdateAction(BulbsScreen bulbsScreen, String name, int id) {
        super(name);
        this.id = id;
        this.panel = bulbsScreen;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        if(id ==26 ) {
            colorValue = panel.getColorCb(this.id).getSelectedItem().toString();
        }else{
            colorValue = "white";
        }
        String statusValue = panel.getStatusCb(this.id).getSelectedItem().toString();
        String start = panel.getStartLabel(this.id).getText();
        String stop = panel.getStopLabel(this.id).getText();

        java.sql.Time startSqlDate = null;
        java.sql.Time endSqlDate = null;

        try {
            Date startDate = sdf.parse(start);
            Date endDate = sdf.parse(stop);
            startSqlDate = new java.sql.Time(startDate.getTime());
            endSqlDate = new java.sql.Time(endDate.getTime());
        } catch (ParseException ex) {
            ParseDateError.displayDateError();
            ex.printStackTrace();
        }

        int idValue = id;

        BulbsQuery bulbsQuery;

        try {
            bulbsQuery = new BulbsQuery();
            bulbsArduino = new BulbsArduino();
            bulbsQuery.updateBulbs(colorValue, statusValue,startSqlDate,endSqlDate, idValue);
            if(statusValue.equals("on")) {
                bulbsQuery.switchOnOff(idValue, 1);
            }else if (bulbsQuery.getSelectedBulb(23).get(0).equals("scheduled")) {
                if (bulbsArduino.getCurrentTime().after(bulbsArduino.getStartTime(idValue)) && bulbsArduino.getCurrentTime().before(bulbsArduino.getEndTime(idValue))) {
                    bulbsQuery.switchOnOff(idValue, 1);
                } else {
                    bulbsQuery.switchOnOff(idValue, 0);
                }
            }
            else {
                bulbsQuery.switchOnOff(idValue,0);
            }
            panel.dispose();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}