package com.mycompany.javadomo.Actions.Logscreen;

import com.mycompany.javadomo.Screens.LogScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DisableButtonAction extends AbstractAction {

    private LogScreen panel;

    public DisableButtonAction(LogScreen logScreen, String name) {
        super(name);

        this.panel = logScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String emailValue = panel.getEmailTf().getText();
        String passwordValue = panel.getPasswordPf().getText();
        JButton button = panel.getConnexionButton();

       if(emailValue.length() > 0 && passwordValue.length() > 0){
           if(emailValue.contains("@") && emailValue.contains(".")){
               button.setEnabled(true);
           }
           else{
               System.out.println("Email address not valid");
               button.setEnabled(false);
           }
       }
       else{
           button.setEnabled(false);
       }
    }
}
