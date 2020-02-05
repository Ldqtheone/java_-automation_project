package com.mycompany.javadomo.Actions.Roomscreen;


import com.mycompany.javadomo.Screens.Roomscreen.DelRoomScreen;
import com.mycompany.javadomo.Screens.Roomscreen.RoomScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DelAction extends AbstractAction {

    private RoomScreen panel;

    public DelAction(RoomScreen roomScreen, String name) {
        super(name);

        this.panel = roomScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DelRoomScreen delRoomScreen;

        try {
            delRoomScreen = new DelRoomScreen();
            delRoomScreen.setVisible(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}
