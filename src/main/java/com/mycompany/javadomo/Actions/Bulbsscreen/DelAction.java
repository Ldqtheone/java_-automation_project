package com.mycompany.javadomo.Actions.Bulbsscreen;

import com.mycompany.javadomo.Screens.Bulbscreen.BulbsScreen;
import com.mycompany.javadomo.Screens.Bulbscreen.DelBulbsScreen;
import com.mycompany.javadomo.Screens.Fridgescreen.DelFoodScreen;
import com.mycompany.javadomo.Screens.Fridgescreen.FridgeScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DelAction extends AbstractAction {

    private BulbsScreen panel;

    public DelAction(BulbsScreen bulbsScreen, String name) {
        super(name);

        this.panel = bulbsScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DelBulbsScreen delBulbsScreen;

        try {
            delBulbsScreen = new DelBulbsScreen();
            delBulbsScreen.setVisible(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}
