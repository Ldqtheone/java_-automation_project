package com.mycompany.javadomo.Actions.Homescreen;

import com.mycompany.javadomo.Screens.Fridgescreen.FridgeScreen;
import com.mycompany.javadomo.Screens.HomeScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FridgeAction extends AbstractAction {

    private HomeScreen panel;

    public FridgeAction(HomeScreen homeScreen, String name) {
        super(name);

        this.panel = homeScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FridgeScreen fridgeScreen;
        try {
            fridgeScreen = new FridgeScreen();
            fridgeScreen.setVisible(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}