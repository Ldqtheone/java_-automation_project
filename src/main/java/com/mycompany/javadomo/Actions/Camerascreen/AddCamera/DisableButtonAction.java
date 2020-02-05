package com.mycompany.javadomo.Actions.Camerascreen.AddCamera;

import com.mycompany.javadomo.Screens.Camerascreen.AddCameraScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DisableButtonAction extends AbstractAction {

    private AddCameraScreen panel;

    public DisableButtonAction(AddCameraScreen addCameraScreen, String name) {
        super(name);

        this.panel = addCameraScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nameValue = panel.getCameraNameTf().getText();
        String targetValue = panel.getStartTimeTf().getText();
        String marginValue = panel.getEndTimeTf().getText();
        JButton button = panel.getValidButton();

        if (nameValue.length() > 0 && targetValue.length() > 0 && marginValue.length() > 0) {

            button.setEnabled(true);
        } else {
            button.setEnabled(false);
        }

    }
}