package com.mycompany.javadomo.Actions.Sensorscreen.Add;


import com.mycompany.javadomo.Screens.Sensorscreen.AddSensorScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DisableButtonAction extends AbstractAction {

    private AddSensorScreen panel;

    public DisableButtonAction(AddSensorScreen addSensorScreen, String name) {
        super(name);

        this.panel = addSensorScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nameValue = panel.getSensorNameTf().getText();
        String interValue = panel.getInterTf().getText();
        String maxValue = panel.getMaxTf().getText();
        String minValue = panel.getMinTf().getText();
        JButton button = panel.getValidButton();

        if (nameValue.length() > 0 && interValue.length() > 0 && maxValue.length() > 0 && minValue.length() > 0) {

            button.setEnabled(true);
        } else {
            button.setEnabled(false);
        }

    }
}