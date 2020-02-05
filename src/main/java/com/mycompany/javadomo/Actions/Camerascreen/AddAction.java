package com.mycompany.javadomo.Actions.Camerascreen;

import com.mycompany.javadomo.Screens.Camerascreen.AddCameraScreen;
import com.mycompany.javadomo.Screens.Camerascreen.CameraScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddAction extends AbstractAction {

    private CameraScreen panel;


    public AddAction(CameraScreen cameraScreen, String name) {
        super(name);

        this.panel = cameraScreen;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AddCameraScreen addCameraScreen;
        try {
            addCameraScreen = new AddCameraScreen();
            addCameraScreen.setVisible(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}