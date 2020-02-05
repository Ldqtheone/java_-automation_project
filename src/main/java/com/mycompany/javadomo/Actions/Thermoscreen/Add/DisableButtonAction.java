package com.mycompany.javadomo.Actions.Thermoscreen.Add;

import com.mycompany.javadomo.Screens.Thermoscreen.AddThermoScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DisableButtonAction extends AbstractAction {

    private AddThermoScreen panel;

    public DisableButtonAction(AddThermoScreen addThermoScreen, String name) {
        super(name);

        this.panel = addThermoScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nameValue = panel.getThermoNameTf().getText();
        String targetValue = panel.getTargetTf().getText();
        String marginValue = panel.getMarginTf().getText();
        JButton button = panel.getValidButton();

        if (nameValue.length() > 0 && targetValue.length() > 0 && marginValue.length() > 0) {

            button.setEnabled(true);
        } else {
            button.setEnabled(false);
        }

    }
}