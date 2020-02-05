package com.mycompany.javadomo.Tools.Clock;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ClockPanel extends JPanel  {

    public ClockLabel clockLabel;

    public ClockPanel() throws IOException {
        clockLabel = new ClockLabel();
        this.add(clockLabel);
        this.add(new UpdateClock(clockLabel));
        this.add(new ResetClock(clockLabel));
        this.setBackground(Color.GRAY);
    }

    public ClockLabel getClockLabel() {
        return clockLabel;
    }

    public void setClockLabel(ClockLabel clockLabel) {
        this.clockLabel = clockLabel;
    }
}