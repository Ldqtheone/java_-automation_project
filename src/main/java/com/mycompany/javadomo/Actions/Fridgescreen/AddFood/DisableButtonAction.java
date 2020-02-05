package com.mycompany.javadomo.Actions.Fridgescreen.AddFood;

import com.mycompany.javadomo.Screens.Fridgescreen.AddFoodScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DisableButtonAction extends AbstractAction {

    private AddFoodScreen panel;

    public DisableButtonAction(AddFoodScreen addFoodScreen, String name) {
        super(name);

        this.panel = addFoodScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nameValue = panel.getFoodNameTf().getText();

        JButton button = panel.getValidButton();

        if(nameValue.length() > 0 ){

                button.setEnabled(true);
            }
            else{
                button.setEnabled(false);
            }
        }

    }

