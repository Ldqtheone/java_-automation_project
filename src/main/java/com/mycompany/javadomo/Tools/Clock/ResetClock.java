package com.mycompany.javadomo.Tools.Clock;

import com.mycompany.javadomo.Configuration.Cache;

import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResetClock extends JButton {
    public ClockLabel clockLabel;
    public ResetClock(ClockLabel clock) throws IOException {
        super("reset");

        this.clockLabel = clock;
        this.addActionListener(e -> {

                    try {
                       Cache cache = new Cache();
                        cache.updateClock("");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    clockLabel.setText("" + (new SimpleDateFormat("HH:mm:ss").format(new Date())));
                }
                );
    }
}