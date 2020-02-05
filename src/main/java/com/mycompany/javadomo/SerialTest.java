package com.mycompany.javadomo;

import com.fazecast.jSerialComm.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class SerialTest implements SerialPortEventListener {
    boolean serialPort;
    private BufferedReader input;
    private OutputStream output;
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 1000000;
    private String currentDate;


    public void initialize() throws IOException {
        SerialPort sp = SerialPort.getCommPort("/dev/cu.usbmodem14121");
        Connection connection = JavaDatabaseConnect.connectDatabase();

        //First, Find an instance of serial port as set in PORT_NAMES.

        if (sp == null) {
            System.out.println("Could not find COM port.");
            return;
        }
        try {
            serialPort = sp.openPort(TIME_OUT);

            sp.setComPortParameters(DATA_RATE, 8, 1, 0);


            while (sp.isOpen()) {
                // open the streams
                input = new BufferedReader(new InputStreamReader(sp.getInputStream()));
                output = sp.getOutputStream();
                try {
                    if (input.ready()) {
                        String inputLine = input.readLine().trim();

                        byte[] currentTemp = "a".getBytes();

                        if(sp.readBytes(currentTemp, 100000) != 0) {
                            System.out.println(sp.readBytes(currentTemp, 100000));
                        }

                        try {
                            System.out.println(inputLine);
                            if (inputLine.equals("Lumiere Rouge")) {
                                System.out.println("Le rouge c'est le feu!");
                                output.write("yes".getBytes());
                            } else if (inputLine.equals("Lumiere Blanche")) {
                                System.out.println("Le blanc c'est la lumiere !");
                                output.write("no".getBytes());

                                currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

                                String request = "INSERT INTO `data_bulbs`(`bulb_id`, `action`, `action_date`) VALUES (?,?,?)";

                                PreparedStatement ps = connection.prepareStatement(request);

                                ps.setInt(1, 25);
                                ps.setInt(2, 1);
                                ps.setString(3, currentDate);

                                ps.addBatch();
                                ps.executeBatch();

                                String updateOn = "UPDATE `bulbs` SET status = ? WHERE id = 25";

                                PreparedStatement updOn = connection.prepareStatement(updateOn);

                                updOn.setString(1, "on");

                                //ps.executeUpdate();
                                updOn.execute();

                            } else if (inputLine.equals("Lumiere Jaune")) {
                                currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

                                String requestDelete = "INSERT INTO `data_bulbs` (`bulb_id`, `action`, `action_date`) VALUES (?,?,?)";

                                PreparedStatement bs = connection.prepareStatement(requestDelete);

                                bs.setInt(1, 25);
                                bs.setInt(2, 0);
                                bs.setString(3, currentDate);

                                bs.addBatch();
                                bs.executeBatch();

                                String updateOff = "UPDATE `bulbs` SET status = ? WHERE id = 25";

                                PreparedStatement updOff = connection.prepareStatement(updateOff);

                                updOff.setString(1, "off");

                                //ps.executeUpdate();
                                updOff.execute();
                            }
                        } catch (Exception ignored) {
                        }
                    }
                } catch (Exception ignored) {
                }
            }
            if (!sp.isOpen()) {
                System.out.println("System closed");
            }
        } catch (Exception ignored) {
        }
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {

    }
}