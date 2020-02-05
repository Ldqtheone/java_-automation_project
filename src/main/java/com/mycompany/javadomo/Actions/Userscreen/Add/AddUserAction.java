package com.mycompany.javadomo.Actions.Userscreen.Add;

import com.mycompany.javadomo.ErrorPopup.IntegerError;
import com.mycompany.javadomo.Screens.Userscreen.AddUserScreen;
import com.mycompany.javadomo.SqlQuery.UserQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

public class AddUserAction extends AbstractAction {

    private AddUserScreen panel;

    public AddUserAction(AddUserScreen addUserScreen, String name) {
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

        try{
            int postalValue = Integer.parseInt(panel.getPostalCodeTf().getText());
            int rankValue = panel.getUserRankTf().getSelectedIndex() + 1;

            UserQuery userQuery;
            try {
                userQuery = new UserQuery();
                userQuery.createUser(firstNameValue, lastNameValue, emailValue, adressValue, phoneValue, postalValue, passwordValue, rankValue);
                panel.dispose();
            } catch (IOException | SQLException ex) {
                ex.printStackTrace();
            }
        }catch (NumberFormatException nb){
            IntegerError.displayIntError();
        }
    }
}