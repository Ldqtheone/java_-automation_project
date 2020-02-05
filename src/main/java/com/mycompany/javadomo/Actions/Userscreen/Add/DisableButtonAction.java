package com.mycompany.javadomo.Actions.Userscreen.Add;

import com.mycompany.javadomo.Screens.Userscreen.AddUserScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DisableButtonAction extends AbstractAction {

    private AddUserScreen panel;

    public DisableButtonAction(AddUserScreen addUserScreen, String name) {
        super(name);

        this.panel = addUserScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String emailValue = panel.getEmailTf().getText();
        String passwordValue = panel.getPasswordPf().getText();
        String firstNameValue = panel.getFirstNameTf().getText();
        String lastNameValue = panel.getLastNameTf().getText();
        String phoneValue = panel.getPhoneTf().getText();
        String adressValue = panel.getAdressTf().getText();
        String postalValue = panel.getPostalCodeTf().getText();
        JButton button = panel.getValidButton();

       if(emailValue.length() > 0 && passwordValue.length() > 0 && firstNameValue.length() > 0
               && lastNameValue.length() > 0 && phoneValue.length() > 0 && adressValue.length() > 0
               && postalValue.length() > 0){
           if(emailValue.contains("@") && emailValue.contains(".") && passwordValue.length() > 7
                   && phoneValue.length() > 9 && postalValue.length() == 5){
               button.setEnabled(true);
           }
           else{
               button.setEnabled(false);
           }
       }
       else{
           button.setEnabled(false);
       }
    }
}
