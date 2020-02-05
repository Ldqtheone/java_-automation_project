package com.mycompany.javadomo.Actions.Homescreen;

import com.mycompany.javadomo.Graphics.AvgBulbsGraphics;
import com.mycompany.javadomo.Graphics.AvgTempGraphics;
import com.mycompany.javadomo.Graphics.BulbColorGraphics;
import com.mycompany.javadomo.Screens.HomeScreen;
import com.mycompany.javadomo.Screens.StatsScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class StatsAction extends AbstractAction {

    private HomeScreen panel;

    public StatsAction(HomeScreen homeScreen, String name) {
        super(name);

        this.panel = homeScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StatsScreen statsScreen;
        try {
            AvgBulbsGraphics ag = new AvgBulbsGraphics();
            AvgTempGraphics ex = new AvgTempGraphics();
            BulbColorGraphics bg = new BulbColorGraphics();
            statsScreen = new StatsScreen();
            statsScreen.setVisible(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}