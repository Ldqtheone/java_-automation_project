package com.mycompany.javadomo.Actions.Bulbsscreen;

import com.mycompany.javadomo.Screens.Bulbscreen.AddBulbsScreen;
import com.mycompany.javadomo.Screens.Bulbscreen.BulbsScreen;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddAction extends AbstractAction {

    private BulbsScreen panel;

    public AddAction(BulbsScreen bulbsScreen, String name) {
        super(name);

        this.panel = bulbsScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AddBulbsScreen addBulbsScreen;
        try {
            addBulbsScreen = new AddBulbsScreen();
            addBulbsScreen.setVisible(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}