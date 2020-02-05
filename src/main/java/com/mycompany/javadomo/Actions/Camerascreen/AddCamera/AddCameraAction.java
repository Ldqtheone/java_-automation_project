package com.mycompany.javadomo.Actions.Camerascreen.AddCamera;


import com.mycompany.javadomo.ErrorPopup.ParseDateError;
import com.mycompany.javadomo.Screens.Camerascreen.AddCameraScreen;
import com.mycompany.javadomo.SqlQuery.CameraQuery;
import com.mycompany.javadomo.SqlQuery.SensorQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCameraAction extends AbstractAction {

    private AddCameraScreen panel;


    public AddCameraAction(AddCameraScreen addCameraScreen, String name) {
        super(name);

        this.panel = addCameraScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        String nameValue = panel.getCameraNameTf().getText();
        String rangevalue = panel.getRangeCb().getSelectedItem().toString();
        String statutValue = panel.getStatu().getSelectedItem().toString();
        String roomValue = panel.getRoomCb().getSelectedItem().toString();
        String startTime = panel.getStartTimeTf().getText();
        String endTime = panel.getEndTimeTf().getText();

        try {
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);
            java.sql.Time startSqlDate = new java.sql.Time(startDate.getTime());
            java.sql.Time endSqlDate = new java.sql.Time(endDate.getTime());

            CameraQuery cameraQuery;

            try {
                cameraQuery = new CameraQuery();
                cameraQuery.createCamera(nameValue,startSqlDate, endSqlDate, roomValue, statutValue, rangevalue);
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