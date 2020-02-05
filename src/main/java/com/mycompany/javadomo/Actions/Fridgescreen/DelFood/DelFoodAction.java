package com.mycompany.javadomo.Actions.Fridgescreen.DelFood;

import com.mycompany.javadomo.Screens.Fridgescreen.DelFoodScreen;
import com.mycompany.javadomo.SqlQuery.FridgeQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DelFoodAction extends AbstractAction {

    private DelFoodScreen panel;
    private int id;

    public DelFoodAction(DelFoodScreen delFoodScreen, String name, int id) {
        super(name);

        this.panel = delFoodScreen;
        this.id = id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(this.id);
        FridgeQuery FridgeQuery;
        try {
            FridgeQuery = new FridgeQuery();
            FridgeQuery.DelFood(this.id);
            panel.dispose();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}