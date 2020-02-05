package com.mycompany.javadomo.Actions.Fridgescreen.AddFood;

import com.mycompany.javadomo.ErrorPopup.IntegerError;
import com.mycompany.javadomo.ErrorPopup.ParseDateError;
import com.mycompany.javadomo.Screens.Fridgescreen.AddFoodScreen;
import com.mycompany.javadomo.SqlQuery.FridgeQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class AddFoodAction extends AbstractAction {

    private AddFoodScreen panel;
    private int id;

    public AddFoodAction(AddFoodScreen addFoodScreen, String name, int id) {
        super(name);

        this.panel = addFoodScreen;
        this.id = id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String nameValue = panel.getFoodNameTf().getText();
        String expirationValue = panel.getExpirationTf().getText();

        try {
            int quantityValue = parseInt(panel.getQuantityTf().getText());

            FridgeQuery fridgeQuery;
            try {
                Date expirationDate = sdf.parse(expirationValue);
                java.sql.Date newExpirationDate = new java.sql.Date(expirationDate.getTime());
                fridgeQuery = new FridgeQuery();

                fridgeQuery.addFood(nameValue,newExpirationDate,quantityValue, this.id);
                panel.dispose();
            } catch (ParseException ex){
                ParseDateError.displayDateError();
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }catch (NumberFormatException nu){
            IntegerError.displayIntError();
        }
    }
}