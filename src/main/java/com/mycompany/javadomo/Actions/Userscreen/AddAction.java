package com.mycompany.javadomo.Actions.Userscreen;


import com.mycompany.javadomo.Screens.Userscreen.AddUserScreen;
import com.mycompany.javadomo.Screens.Userscreen.UserScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddAction extends AbstractAction {

    private UserScreen panel;

    public AddAction(UserScreen userScreen, String name) {
        super(name);

        this.panel = userScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AddUserScreen addUserScreen;
        try {
            addUserScreen = new AddUserScreen();
            addUserScreen.setVisible(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}
