package com.mycompany.javadomo.Actions.Bulbsscreen.Del;

import com.mycompany.javadomo.Screens.Bulbscreen.DelBulbsScreen;
import com.mycompany.javadomo.SqlQuery.BulbsQuery;
import com.mycompany.javadomo.SqlQuery.FridgeQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DelBulbAction extends AbstractAction {

    private DelBulbsScreen panel;
    private int id;

    public DelBulbAction(DelBulbsScreen delBulbScreen, String name) {
        super(name);
        this.panel = delBulbScreen;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String nameValue = panel.getBulbNameCb().getSelectedItem().toString();

        BulbsQuery bulbsQuery;
        try {
            bulbsQuery = new BulbsQuery();
            bulbsQuery.delBulb(nameValue);
            panel.dispose();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}