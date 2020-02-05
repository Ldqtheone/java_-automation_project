package com.mycompany.javadomo.Actions.Fridgescreen;

import com.mycompany.javadomo.Screens.Fridgescreen.DelFoodScreen;
import com.mycompany.javadomo.Screens.Fridgescreen.FridgeScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DelAction extends AbstractAction {

    private FridgeScreen panel;

    public DelAction(FridgeScreen fridgeScreen, String name) {
        super(name);

        this.panel = fridgeScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DelFoodScreen delFoodScreen;

        try {
            delFoodScreen = new DelFoodScreen();
            delFoodScreen.setVisible(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}
