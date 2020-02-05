package com.mycompany.javadomo.Actions.Homescreen;

import com.mycompany.javadomo.Screens.HomeScreen;
import com.mycompany.javadomo.Screens.Userscreen.UserScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UserAction extends  AbstractAction {

    private HomeScreen panel;

    public UserAction(HomeScreen homeScreen, String name) {
        super(name);

        this.panel = homeScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UserScreen userScreen;
        try {
            userScreen = new UserScreen();
            userScreen.setVisible(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}
