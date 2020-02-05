package com.mycompany.javadomo.Actions.Thermoscreen;

import com.mycompany.javadomo.Screens.Thermoscreen.DelThermoScreen;
import com.mycompany.javadomo.Screens.Thermoscreen.ThermoScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DelAction extends AbstractAction {

    private ThermoScreen panel;


    public DelAction(ThermoScreen thermoScreen, String name) {
        super(name);

        this.panel = thermoScreen;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DelThermoScreen delThermoScreen;

        try {
            delThermoScreen = new DelThermoScreen();
            delThermoScreen.setVisible(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}