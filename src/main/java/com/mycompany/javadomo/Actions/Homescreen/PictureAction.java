package com.mycompany.javadomo.Actions.Homescreen;

import com.mycompany.javadomo.Screens.HomeScreen;
import com.mycompany.javadomo.Screens.PictureScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PictureAction extends AbstractAction {

    private HomeScreen panel;

    public PictureAction(HomeScreen homeScreen, String name) {
        super(name);

        this.panel = homeScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PictureScreen pictureScreen;
        try {
            pictureScreen = new PictureScreen();
            pictureScreen.setVisible(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}