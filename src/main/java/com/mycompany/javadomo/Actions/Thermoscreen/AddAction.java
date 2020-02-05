package com.mycompany.javadomo.Actions.Thermoscreen;

import com.mycompany.javadomo.Screens.Thermoscreen.AddThermoScreen;
import com.mycompany.javadomo.Screens.Thermoscreen.ThermoScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddAction extends AbstractAction {

    private ThermoScreen panel;


    public AddAction(ThermoScreen thermoScreen, String name) {
        super(name);

        this.panel = thermoScreen;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AddThermoScreen addThermoScreen;
        try {
            addThermoScreen = new AddThermoScreen();
            addThermoScreen.setVisible(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}