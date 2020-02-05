package com.mycompany.javadomo.Actions.Roomscreen.Add;



import com.mycompany.javadomo.Screens.Roomscreen.AddRoomScreen;
import com.mycompany.javadomo.SqlQuery.RoomQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

public class AddRoomAction extends AbstractAction {

    private AddRoomScreen panel;

    public AddRoomAction(AddRoomScreen addRoomScreen, String name) {
        super(name);

        this.panel = addRoomScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String nameValue = panel.getRoomNameTf().getText();
        String descValue = panel.getDescNameTf().getText();

        RoomQuery roomQuery;
        try {
            roomQuery = new RoomQuery();
            roomQuery.createRoom(nameValue,descValue);
            panel.dispose();
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
    }
}