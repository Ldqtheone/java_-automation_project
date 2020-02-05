package com.mycompany.javadomo.Actions.Roomscreen.Add;



import com.mycompany.javadomo.Screens.Roomscreen.AddRoomScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DisableButtonAction extends AbstractAction {

    private AddRoomScreen panel;

    public DisableButtonAction(AddRoomScreen addRoomScreen, String name) {
        super(name);

        this.panel = addRoomScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nameValue = panel.getRoomNameTf().getText();

        JButton button = panel.getValidButton();

        if(nameValue.length() > 0 ){

                button.setEnabled(true);
            }
            else{
                button.setEnabled(false);
            }
        }

    }

