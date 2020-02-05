package com.mycompany.javadomo.Actions.Fridgescreen;

import com.mycompany.javadomo.ErrorPopup.IntegerError;
import com.mycompany.javadomo.Screens.Fridgescreen.FridgeScreen;
import com.mycompany.javadomo.ErrorPopup.ParseDateError;
import com.mycompany.javadomo.SqlQuery.FridgeQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UpdateAction extends AbstractAction {

    private FridgeScreen panel;
    private int id;

    public UpdateAction(FridgeScreen fridgeScreen, String name, int id) {
        super(name);
        this.id = id;
        this.panel = fridgeScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String openValue = panel.getOpenDate(this.id).getText();

        try {
            int quantityValue = Integer.parseInt(panel.getQuantity(this.id).getText());

            try{
                Date openDate = sdf.parse(openValue);
                java.sql.Date openSqlDate = new java.sql.Date(openDate.getTime());

                int idValue = id;

                FridgeQuery fridgeQuery;
                try {
                    fridgeQuery = new FridgeQuery();
                    fridgeQuery.updateFood(idValue, openSqlDate, quantityValue);
                    panel.dispose();
                } catch (IOException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
            catch(ParseException ex){
                ParseDateError.displayFoodDateError();
                ex.printStackTrace();
            }

        }catch (NumberFormatException nu){
            IntegerError.displayIntError();
        }
    }
}