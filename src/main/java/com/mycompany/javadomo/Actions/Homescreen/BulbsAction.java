package com.mycompany.javadomo.Actions.Homescreen;

import com.mycompany.javadomo.Screens.Bulbscreen.BulbsScreen;
import com.mycompany.javadomo.Screens.HomeScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BulbsAction extends AbstractAction {

    private HomeScreen panel;

    public BulbsAction(HomeScreen homeScreen, String name) {
        super(name);

        this.panel = homeScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BulbsScreen bulbsScreen;
        try {
            bulbsScreen = new BulbsScreen();
            bulbsScreen.setVisible(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}