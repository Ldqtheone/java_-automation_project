package com.mycompany.javadomo.Actions.Roomscreen.Del;

import com.mycompany.javadomo.Screens.Roomscreen.DelRoomScreen;
import com.mycompany.javadomo.SqlQuery.RoomQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DelRoomAction extends AbstractAction {

    private DelRoomScreen panel;

    public DelRoomAction(DelRoomScreen delRoomScreen, String name) {
        super(name);

        this.panel = delRoomScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String nameValue = panel.getRoomNameCb().getSelectedItem().toString();
        System.out.println(nameValue);
        RoomQuery roomQuery;
        try {
            roomQuery = new RoomQuery();
            roomQuery.DelRoom(nameValue);
            panel.dispose();

        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}