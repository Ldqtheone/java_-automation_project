package com.mycompany.javadomo.Actions.Homescreen;

import com.mycompany.javadomo.Screens.HomeScreen;
import com.mycompany.javadomo.Screens.Roomscreen.RoomScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RoomAction extends AbstractAction {

    private HomeScreen panel;

    public RoomAction(HomeScreen homeScreen, String name) {
        super(name);

        this.panel = homeScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RoomScreen roomScreen;
        try {
            roomScreen = new RoomScreen();
            roomScreen.setVisible(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}