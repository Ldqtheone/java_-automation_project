package com.mycompany.javadomo.Screens.Roomscreen;

import com.mycompany.javadomo.Actions.Roomscreen.Add.AddRoomAction;
import com.mycompany.javadomo.Actions.Roomscreen.Add.DisableButtonAction;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AddRoomScreen extends JFrame {

    private JButton validButton;
    private Timer timer;
    private JLabel roomNameLabel;
    private JTextField roomNameTf;
    private JLabel descLabel;
    private JTextField descNameTf;


    public AddRoomScreen() throws IOException {
        super();
        build();
    }

    private void build() throws IOException {
        setTitle("ADD ROOM");
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(buildContentPane());
    }

    private JPanel buildContentPane() throws IOException {

        JPanel panel = new JPanel();
        GridLayout gl = new GridLayout(17, 0);
        panel.setLayout(gl);

        roomNameLabel = new JLabel("room name");
        roomNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(roomNameLabel);

        roomNameTf = new JTextField(20);
        panel.add(roomNameTf);

        descLabel = new JLabel("description");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(descLabel);

        descNameTf = new JTextField(20);
        panel.add(descNameTf);

        validButton = new JButton(new AddRoomAction(this, "Validate"));
        validButton.setEnabled(false);
        panel.add(validButton);

        timer = new Timer(500, new DisableButtonAction(this, "Disable"));
        timer.start();

        return panel;
    }

    public JButton getValidButton() {
        return validButton;
    }

    public JTextField getRoomNameTf() {
        return roomNameTf;
    }

    public JTextField getDescNameTf() {
        return descNameTf;
    }
}
