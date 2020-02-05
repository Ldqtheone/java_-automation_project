package com.mycompany.javadomo.Tools.Clock;

import com.mycompany.javadomo.Configuration.Cache;

import javax.swing.*;
import java.io.IOException;

public class UpdateClock extends JButton {

    public ClockLabel clock;

    public UpdateClock(ClockLabel clock) throws IOException {

        super("update");

        this.addActionListener(e -> {
            try {
                Cache cache = new Cache();
                cache.updateClock("");
                cache.updateClock(clock.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
