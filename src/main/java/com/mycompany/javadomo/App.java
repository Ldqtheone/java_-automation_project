package com.mycompany.javadomo;

import com.apple.eawt.Application;
import com.mycompany.javadomo.Arduino.BulbsArduino;
import com.mycompany.javadomo.Screens.LogScreen;

import java.awt.*;

public class App {
    public static void main(String[] arg) throws Throwable {

        Application application = Application.getApplication();
        Image image = Toolkit.getDefaultToolkit().getImage("asset/desktop.png");
        application.setDockIconImage(image);

        LogScreen logScreen = new LogScreen();
        logScreen.setVisible(true);

        System.out.println("Started");

        BulbsArduino bulbsArduino = new BulbsArduino();
        bulbsArduino.initialize();
        /* SensorArduino sensorArduino = new SensorArduino();
        sensorArduino.initialize(); */
        Thread t= new Thread() {
            public void run() {
                //the following line will keep this app alive for 60 seconds,
                //waiting for events to occur and responding to them    (printing incoming messages to console).
                try {Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
}
