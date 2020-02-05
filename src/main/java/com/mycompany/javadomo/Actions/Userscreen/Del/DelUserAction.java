package com.mycompany.javadomo.Actions.Userscreen.Del;

import com.mycompany.javadomo.Screens.Userscreen.DelUserScreen;
import com.mycompany.javadomo.SqlQuery.UserQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DelUserAction extends AbstractAction {

    private DelUserScreen panel;

    public DelUserAction(DelUserScreen DelUserScreen, String name) {
        super(name);

        this.panel = DelUserScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String nameValue = panel.getUserNameCb().getSelectedItem().toString();
        System.out.println(nameValue);
        UserQuery userQuery;
        try {
            userQuery = new UserQuery();
            userQuery.DelUsers(nameValue);
            panel.dispose();

        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}