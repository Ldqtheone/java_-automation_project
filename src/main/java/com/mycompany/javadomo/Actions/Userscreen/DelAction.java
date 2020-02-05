package com.mycompany.javadomo.Actions.Userscreen;

import com.mycompany.javadomo.Screens.Userscreen.DelUserScreen;
import com.mycompany.javadomo.Screens.Userscreen.UserScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DelAction extends AbstractAction {

    private UserScreen panel;

    public DelAction(UserScreen userScreen, String name) {
        super(name);

        this.panel = userScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DelUserScreen delUserScreen;
        try {
            delUserScreen = new DelUserScreen();
            delUserScreen.setVisible(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}
