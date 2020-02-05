package com.mycompany.javadomo.Screens.Camerascreen;

import com.mycompany.javadomo.Actions.Camerascreen.AddCamera.AddCameraAction;
import com.mycompany.javadomo.Actions.Camerascreen.AddCamera.DisableButtonAction;
import com.mycompany.javadomo.SqlQuery.CameraQuery;
import com.mycompany.javadomo.SqlQuery.RoomQuery;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class AddCameraScreen extends JFrame {


    private JButton validButton;
    private Timer timer;
    private JLabel cameraNameLabel;
    private JTextField cameraNameTf;
    private JComboBox<String> statu;
    private JLabel StatuLabel;
    private JComboBox<String> room;
    private JLabel startTimeLabel;
    private JTextField startTimeTf;
    private JLabel endTimeLabel;
    private JTextField endTimeTf;
    private Label rangeLabel;
    private JComboBox<String> rangeCb;
    private int id;


    public AddCameraScreen() throws Throwable {
        super();
        build();
    }

    private void build() throws Throwable {
        setTitle("ADD CAMERA");
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(buildContentPane());
    }

    private JPanel buildContentPane() throws Throwable {

        JPanel panel = new JPanel();
        GridLayout gl = new GridLayout(17, 0);
        panel.setLayout(gl);

        cameraNameLabel = new JLabel("Camera name");
        cameraNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(cameraNameLabel);

        cameraNameTf = new JTextField(20);
        panel.add(cameraNameTf);

        RoomQuery roomQuery = new RoomQuery();
        ResultSet getDelRooms = roomQuery.getDelRooms();
        String[] names = {};

        room = new JComboBox<String>(names);
        while (getDelRooms.next()) {
            room.addItem(getDelRooms.getString("room_name"));
        }
        panel.add(room);

        StatuLabel = new JLabel("On/Off");
        StatuLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(StatuLabel);

        String[] status = {"on", "off"};
        statu = new JComboBox<>(status);
        panel.add(statu);

        rangeLabel = new Label("Range");
        rangeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(rangeLabel);

        String[] range = {"0.5","1","1.5","2","2.5","3","3.5","4"};
        rangeCb = new JComboBox<>(range);
        panel.add(rangeCb);

        startTimeLabel = new JLabel("Start Time");
        startTimeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(startTimeLabel);

        startTimeTf = new JTextField( 20);
        startTimeTf.setText("00:00:00");
        panel.add(startTimeTf);

        endTimeLabel = new JLabel("End Time");
        endTimeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(endTimeLabel);

        endTimeTf = new JTextField( 20);
        endTimeTf.setText("00:00:00");
        panel.add(endTimeTf);

        validButton = new JButton(new AddCameraAction(this, "Validate"));
        validButton.setEnabled(false);
        panel.add(validButton);

        timer = new Timer(500, new DisableButtonAction(this, "Disable"));
        timer.start();

        return panel;
    }

    public JButton getValidButton() {
        return validButton;
    }

    public JTextField getCameraNameTf() {
        return cameraNameTf;
    }

    public JComboBox<String> getRoomCb() {
        return room;
    }

    public JTextField getStartTimeTf() {
        return startTimeTf;
    }

    public JTextField getEndTimeTf() {
        return endTimeTf;
    }

    public JComboBox<String> getRangeCb() {
        return rangeCb;
    }

    public JComboBox<String> getStatu() {
        return statu;
    }
}
