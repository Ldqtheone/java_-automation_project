package com.mycompany.javadomo.Actions.Camerascreen;

import com.mycompany.javadomo.Screens.Camerascreen.CameraScreen;
import com.mycompany.javadomo.SqlQuery.CameraQuery;
import com.mycompany.javadomo.ErrorPopup.ParseDateError;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UpdateAction extends AbstractAction {

    private CameraScreen panel;
    private int id;

    public UpdateAction(CameraScreen cameraScreen, String name, int id) {
        super(name);
        this.id = id;
        this.panel = cameraScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        String rangeValue = panel.getRangeCb(this.id).getSelectedItem().toString();
        String statusValue = panel.getStatusCb(this.id).getSelectedItem().toString();
        String startValue = panel.getStartDate(this.id).getText();
        String endValue = panel.getEndDate(this.id).getText();

        try {
            Date startDate = sdf.parse(startValue);
            Date endDate = sdf.parse(endValue);
            java.sql.Time startSqlDate = new java.sql.Time(startDate.getTime());
            java.sql.Time endSqlDate = new java.sql.Time(endDate.getTime());

            int idValue = id;
            CameraQuery cameraQuery;

            try {
                cameraQuery = new CameraQuery();
                cameraQuery.updateCamera(rangeValue, statusValue, idValue, startSqlDate, endSqlDate);
                panel.dispose();
            } catch (IOException | SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ParseException ex) {
            ParseDateError.displayDateError();
            ex.printStackTrace();
        }

    }
}