package com.mycompany.javadomo.Screens.Camerascreen;

import com.mycompany.javadomo.Actions.Camerascreen.DelCamera.DelCameraAction;
import com.mycompany.javadomo.SqlQuery.CameraQuery;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class DelCameraScreen extends JFrame {

    private JLabel cameraNameLabel;
    private JComboBox<String> cameraNameCb;
    private JButton validButton;
    private int id;

    public DelCameraScreen() throws Throwable {
        super();
        build();
    }

    private void build() throws Throwable {
        setTitle("DELETE CAMERA");
        setSize(320, 240);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(buildContentPane());
    }

    private JPanel buildContentPane() throws Throwable {

        JPanel panel = new JPanel();
        GridLayout gl = new GridLayout(3, 0);

        CameraQuery cameraQuery = new CameraQuery();
        ResultSet getCamera = cameraQuery.getDelCamera();

        panel.setLayout(gl);

        cameraNameLabel = new JLabel("Which camera you wan't to delete");
        cameraNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(cameraNameLabel);

        String[] names = {"No Selection"};
        cameraNameCb = new JComboBox<String>(names);
        while (getCamera.next()) {
            id = getCamera.getInt("id");
            cameraNameCb.addItem(getCamera.getString("camera_name"));
        }
        panel.add(cameraNameCb);

        validButton = new JButton(new DelCameraAction(this, "Delete", id));
        panel.add(validButton);

        return panel;
    }
}
