package com.mycompany.javadomo.Arduino;

import com.fazecast.jSerialComm.SerialPort;
import com.mycompany.javadomo.Configuration.Cache;
import com.mycompany.javadomo.SqlQuery.BulbsQuery;
import com.mycompany.javadomo.SqlQuery.SensorQuery;
import com.mycompany.javadomo.Tools.Clock.ClockLabel;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Time;
import java.time.Duration;
import java.time.Instant;


public class BulbsArduino implements SerialPortEventListener {
    boolean serialPort;
    private int[] bulbId = {23, 24, 25, 26};
    private BulbsQuery bulbsQuery = new BulbsQuery();
    private SensorQuery sensorQuery = new SensorQuery();
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 100000;
    private String color;
    private OutputStream output;
    private BufferedReader input;

    public BulbsArduino() {
    }

    public void initialize() {
        SerialPort sp = SerialPort.getCommPort("/dev/cu.usbmodem26");

        if (sp == null) {
            System.out.println("Could not find COM port.");
            return;
        }
        Instant lastValue = Instant.now();
        try {
            serialPort = sp.openPort(TIME_OUT);

            sp.setComPortParameters(DATA_RATE, 8, 1, 0);

            while (sp.isOpen()) {
                input = new BufferedReader(new InputStreamReader(sp.getInputStream()));
                output = sp.getOutputStream();
                Timer timer = new Timer(10000, e -> {
                    try {
                        output.write("a".getBytes());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                timer.start();
                Instant now = Instant.now();
                Duration timeElapsed = Duration.between(lastValue, now);

                if (timeElapsed.toMillis() >= Integer.parseInt(getInterval(78)) * 1000) {
                    output.write("a".getBytes());

                    try {
                        if (input.ready()) {

                            float temp = Float.parseFloat(input.readLine().trim());

                            if (temp > getMinTemp(78) && temp < getMaxTemp(78) && getStatus(78).equals("on")) {
                                System.out.println(temp);
                                sensorQuery.insertDataTemp(78, temp);
                            }

                            if (thermoStatus(30).equals("on")) {

                                if (temp < (thermoTarget(30) - thermoMargin(30))) {
                                    output.write("b".getBytes());
                                } else if (temp > (thermoTarget(30) + thermoMargin(30))) {
                                    output.write("c".getBytes());
                                } else {
                                    output.write("d".getBytes());
                                }
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    lastValue = now;
                }

                int i;
                for (i = 0; i < 4; i++) {
                    switch (bulbId[i]) {
                        case 23:
                            if (bulbsQuery.getSelectedBulb(23).get(0).equals("on")) {
                                output.write("i".getBytes());
                            } else if (bulbsQuery.getSelectedBulb(23).get(0).equals("scheduled")) {
                                if (getCurrentTime().after(getStartTime(23)) && getCurrentTime().before(getEndTime(23))) {
                                    output.write("i".getBytes());
                                } else {
                                    output.write(("j".getBytes()));
                                }
                            } else {
                                output.write(("j".getBytes()));
                            }
                            break;
                        case 24:
                            if (bulbsQuery.getSelectedBulb(24).get(0).equals("on")) {
                                output.write("k".getBytes());
                            } else if (bulbsQuery.getSelectedBulb(24).get(0).equals("scheduled")) {
                                if (getCurrentTime().after(getStartTime(24)) && getCurrentTime().before(getEndTime(24))) {
                                    output.write("k".getBytes());
                                } else {
                                    output.write(("l".getBytes()));
                                }
                            } else {
                                output.write(("l".getBytes()));
                            }
                            break;
                        case 25:
                            if (bulbsQuery.getSelectedBulb(25).get(0).equals("on")) {
                                output.write("m".getBytes());
                            } else if (bulbsQuery.getSelectedBulb(25).get(0).equals("scheduled")) {
                                if (getCurrentTime().after(getStartTime(25)) && getCurrentTime().before(getEndTime(25))) {
                                    output.write("m".getBytes());
                                } else {
                                    output.write(("n".getBytes()));
                                }
                            } else {
                                output.write(("n".getBytes()));
                            }
                            break;
                        case 26:
                            color = bulbsQuery.getSelectedBulb(26).get(1);
                            if (bulbsQuery.getSelectedBulb(26).get(0).equals("on")) {
                                getColor();
                            } else if (bulbsQuery.getSelectedBulb(26).get(0).equals("scheduled")) {
                                if (getCurrentTime().after(getStartTime(26)) && getCurrentTime().before(getEndTime(26))) {
                                    getColor();
                                } else {
                                    output.write(("p".getBytes()));
                                }
                            } else {
                                output.write(("p".getBytes()));
                            }
                            break;
                        default:
                            break;
                    }
                }
                if (!sp.isOpen()) {
                    System.out.println("System closed");
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void getColor() throws Throwable {

        color = bulbsQuery.getSelectedBulb(26).get(1);

        switch (color) {
            case "red":
                output.write("q".getBytes());
                break;
            case "blue":
                output.write("r".getBytes());
                break;
            case "green":
                output.write("s".getBytes());
                break;
            case "orange":
                output.write("v".getBytes());
                break;
            default:
                output.write("u".getBytes());
                break;
        }
    }

    public Time getCurrentTime() throws IOException {
        ClockLabel clockLabel = new ClockLabel();
        Cache cache = new Cache();
        String currentTime;

        if (cache.clock.equals("")) {
            currentTime = clockLabel.getText();
        } else {
            currentTime = cache.clock;
        }

        return Time.valueOf(currentTime);
    }

    public Time getStartTime(int id) throws Throwable {

        return Time.valueOf(bulbsQuery.getSelectedBulb(id).get(2));
    }

    public Time getEndTime(int id) throws Throwable {

        return Time.valueOf(bulbsQuery.getSelectedBulb(id).get(3));
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {

    }

    public String getInterval(int id) throws Throwable {

        return sensorQuery.getSelectedSensor(id).get(0);
    }

    public String getStatus(int id) throws Throwable {

        return sensorQuery.getSelectedSensor(id).get(3);
    }

    public Float getMaxTemp(int id) throws Throwable {

        return Float.parseFloat(sensorQuery.getSelectedSensor(id).get(1));
    }

    public Float getMinTemp(int id) throws Throwable {

        return Float.parseFloat(sensorQuery.getSelectedSensor(id).get(2));
    }

    public String thermoStatus(int id) throws Throwable {
        return sensorQuery.getThermoMargin(30).get(0);
    }

    public float thermoTarget(int id) throws Throwable {
        return Float.parseFloat(sensorQuery.getThermoMargin(30).get(1));
    }

    public float thermoMargin(int id) throws Throwable {
        return Float.parseFloat(sensorQuery.getThermoMargin(30).get(2));
    }
}
