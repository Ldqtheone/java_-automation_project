package com.mycompany.javadomo.Screens.Roomscreen;

import com.mycompany.javadomo.Actions.Roomscreen.Del.DelRoomAction;
import com.mycompany.javadomo.SqlQuery.RoomQuery;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class DelRoomScreen extends JFrame {

    private JLabel roomNameLabel;
    private JComboBox<String> roomNameCb;
    private JButton validButton;

    public DelRoomScreen() throws Throwable {
        super();
        build();
    }

    private void build() throws Throwable {
        setTitle("DELETE ROOM");
        setSize(320, 240);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(buildContentPane());
    }

    private JPanel buildContentPane() throws Throwable {

        JPanel panel = new JPanel();
        GridLayout gl = new GridLayout(3, 0);
        RoomQuery roomQuery = new RoomQuery();
        ResultSet getDelRooms = roomQuery.getDelRooms();
        panel.setLayout(gl);

        roomNameLabel = new JLabel("Which room you wan't to delete");
        roomNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(roomNameLabel);

        String[] names = {"No Selection"};
        roomNameCb = new JComboBox<String>(names);
        while (getDelRooms.next()) {
            roomNameCb.addItem(getDelRooms.getString("room_name"));
        }
        panel.add(roomNameCb);

        validButton = new JButton(new DelRoomAction(this, "Delete"));
        panel.add(validButton);

        return panel;
    }

    public JComboBox getRoomNameCb() {
        return roomNameCb;
    }
}
