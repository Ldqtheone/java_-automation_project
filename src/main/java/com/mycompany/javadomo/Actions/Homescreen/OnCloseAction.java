package com.mycompany.javadomo.Actions.Homescreen;

import com.mycompany.javadomo.Configuration.Cache;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class OnCloseAction extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        super.windowClosing(e);

        Cache cache;
        try {
            cache = new Cache();
            cache.deleteCache();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Application has been closed");
    }
}
