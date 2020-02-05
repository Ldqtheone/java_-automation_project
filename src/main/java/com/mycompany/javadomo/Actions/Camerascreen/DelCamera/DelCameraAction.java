package com.mycompany.javadomo.Actions.Camerascreen.DelCamera;

import com.mycompany.javadomo.Screens.Camerascreen.DelCameraScreen;
import com.mycompany.javadomo.SqlQuery.CameraQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DelCameraAction extends AbstractAction {

    private DelCameraScreen panel;
    private int id;

    public DelCameraAction(DelCameraScreen delCameraScreen, String name, int id) {
        super(name);

        this.panel = delCameraScreen;
        this.id = id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(this.id);
        CameraQuery cameraQuery;
        try {
            cameraQuery = new CameraQuery();
            cameraQuery.delCamera(this.id);
            panel.dispose();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}