package com.mycompany.javadomo.Actions.Camerascreen;

import com.mycompany.javadomo.Screens.Camerascreen.CameraScreen;
import com.mycompany.javadomo.Screens.Camerascreen.DelCameraScreen;
import com.mycompany.javadomo.Screens.Fridgescreen.DelFoodScreen;
import com.mycompany.javadomo.Screens.Fridgescreen.FridgeScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DelAction extends AbstractAction {

    private CameraScreen panel;

    public DelAction(CameraScreen cameraScreen, String name) {
        super(name);

        this.panel = cameraScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DelCameraScreen delCameraScreen;

        try {
            delCameraScreen = new DelCameraScreen();
            delCameraScreen.setVisible(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}
