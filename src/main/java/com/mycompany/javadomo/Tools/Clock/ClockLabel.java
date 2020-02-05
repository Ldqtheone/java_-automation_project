package com.mycompany.javadomo.Tools.Clock;

import com.mycompany.javadomo.Configuration.Cache;

import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockLabel extends JTextField {


    public ClockLabel() throws IOException {
        super("" + (new SimpleDateFormat("HH:mm:ss").format(new Date())));
        Cache cache = new Cache();
        if(!cache.clock.equals("")){
            this.setText(cache.clock);
        }
    }
}

