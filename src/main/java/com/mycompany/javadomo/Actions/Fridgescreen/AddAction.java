package com.mycompany.javadomo.Actions.Fridgescreen;

import com.mycompany.javadomo.Screens.Fridgescreen.FridgeScreen;
import com.mycompany.javadomo.Screens.Fridgescreen.AddFoodScreen;;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddAction extends AbstractAction {

    private FridgeScreen panel;

    public AddAction(FridgeScreen fridgeScreen, String name) {
        super(name);

        this.panel = fridgeScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AddFoodScreen addFoodScreen;
        try {
            addFoodScreen = new AddFoodScreen();
            addFoodScreen.setVisible(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}