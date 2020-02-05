package com.mycompany.javadomo.Actions.Homescreen;

import com.mycompany.javadomo.Screens.Camerascreen.CameraScreen;
import com.mycompany.javadomo.Screens.HomeScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CamAction extends AbstractAction {

    private HomeScreen panel;

    public CamAction(HomeScreen homeScreen, String name) {
        super(name);

        this.panel = homeScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CameraScreen cameraScreen;
        try {
            cameraScreen = new CameraScreen();
            cameraScreen.setVisible(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}