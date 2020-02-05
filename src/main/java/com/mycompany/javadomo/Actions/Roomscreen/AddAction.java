package com.mycompany.javadomo.Actions.Roomscreen;

import com.mycompany.javadomo.Screens.Roomscreen.AddRoomScreen;
import com.mycompany.javadomo.Screens.Roomscreen.RoomScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddAction extends AbstractAction {

    private RoomScreen panel;

    public AddAction(RoomScreen roomScreen, String name) {
        super(name);

        this.panel = roomScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AddRoomScreen addRoomScreen;
        try {
            addRoomScreen = new AddRoomScreen();
            addRoomScreen.setVisible(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}