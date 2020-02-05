package com.mycompany.javadomo.Actions.Homescreen;

import com.mycompany.javadomo.Screens.HomeScreen;

import com.mycompany.javadomo.Screens.Thermoscreen.ThermoScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TempAction extends AbstractAction {

    private HomeScreen panel;

    public TempAction(HomeScreen homeScreen, String name) {
        super(name);

        this.panel = homeScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ThermoScreen thermoScreen;
        try {
            thermoScreen = new ThermoScreen();
            thermoScreen.setVisible(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}