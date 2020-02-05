package com.mycompany.javadomo.Actions.Bulbsscreen.Add;

import com.mycompany.javadomo.ErrorPopup.ParseDateError;
import com.mycompany.javadomo.Screens.Bulbscreen.AddBulbsScreen;

import com.mycompany.javadomo.SqlQuery.BulbsQuery;
import com.mycompany.javadomo.SqlQuery.FridgeQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class AddBulbAction extends AbstractAction {

    private AddBulbsScreen panel;
    private int id;

    public AddBulbAction(AddBulbsScreen addBulbsScreen, String name) {
        super(name);

        this.panel = addBulbsScreen;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        String nameValue = panel.getBulbTf().getText();
        String roomValue = panel.getRoom().getSelectedItem().toString();
        String statuValue = panel.getStatu().getSelectedItem().toString();
        String colorValue = panel.getColor().getSelectedItem().toString();
        String start = panel.getStartTf().getText();
        String stop = panel.getStopTf().getText();

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

        BulbsQuery bulbsQuery;

        try {
            bulbsQuery = new BulbsQuery();
            bulbsQuery.createBulbs(nameValue, roomValue,statuValue,colorValue, startSqlDate, endSqlDate);
            panel.dispose();
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
    }
}