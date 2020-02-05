package com.mycompany.javadomo.Arduino;

import com.fazecast.jSerialComm.SerialPort;
import com.mycompany.javadomo.SqlQuery.SensorQuery;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;


public class SensorArduino implements SerialPortEventListener {
    boolean serialPort;
    private SensorQuery sensorQuery = new SensorQuery();
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 1000000;
    private OutputStream output;
    private BufferedReader input;

    public void initialize() {
        SerialPort sp = SerialPort.getCommPort("/dev/cu.usbmodem14321");

        try {
            serialPort = sp.openPort(TIME_OUT);

            sp.setComPortParameters(DATA_RATE, 8, 1, 0);

            while (sp.isOpen()) {
                // open the streams
                input = new BufferedReader(new InputStreamReader(sp.getInputStream()));
                output = sp.getOutputStream();

                try {
                    if (input.ready()) {
                        byte[] currentTemp = "a".getBytes();

                        if (Float.parseFloat(Arrays.toString(currentTemp)) < Float.parseFloat(getMaxTemp(78))
                        && Float.parseFloat(Arrays.toString(currentTemp)) > Float.parseFloat(getMinTemp(78))) {
                            System.out.println(sp.readBytes(currentTemp, 100000));
                            System.out.println("Test condition");
                        }

                        if(sp.readBytes(currentTemp, 100000) != 0) {
                            System.out.println(sp.readBytes(currentTemp, 100000));
                            System.out.println("Test problem");
                        }
                        else{
                            System.out.println("test0");
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (!sp.isOpen()) {
                    System.out.println("System closed");
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {

    }

    public String getInterval(int id) throws Throwable {

        return sensorQuery.getSelectedSensor(id).get(0);
    }

    public String getMaxTemp(int id) throws Throwable {

        return sensorQuery.getSelectedSensor(id).get(1);
    }

    public String getMinTemp(int id) throws Throwable {

        return sensorQuery.getSelectedSensor(id).get(2);
    }
}
