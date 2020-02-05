package com.mycompany.javadomo.Screens.Bulbscreen;

import com.mycompany.javadomo.Actions.Bulbsscreen.Del.DelBulbAction;
import com.mycompany.javadomo.Actions.Roomscreen.Del.DelRoomAction;
import com.mycompany.javadomo.SqlQuery.BulbsQuery;
import com.mycompany.javadomo.SqlQuery.RoomQuery;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class DelBulbsScreen extends JFrame {

    private JLabel bulbNameLabel;
    private JComboBox<String> bulbNameCb;
    private JButton validButton;

    public DelBulbsScreen() throws Throwable {
        super();
        build();
    }

    private void build() throws Throwable {
        setTitle("DELETE BULB");
        setSize(320, 240);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(buildContentPane());
    }

    private JPanel buildContentPane() throws Throwable {

        JPanel panel = new JPanel();
        GridLayout gl = new GridLayout(3, 0);
        BulbsQuery bulbsQuery = new BulbsQuery();
        ResultSet getDelBulbs = bulbsQuery.getAllBulbs();
        panel.setLayout(gl);

        bulbNameLabel = new JLabel("Which bulb you wan't to delete");
        bulbNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(bulbNameLabel);

        String[] names = {"No Selection"};
        bulbNameCb = new JComboBox<String>(names);
        while (getDelBulbs.next()) {
            bulbNameCb.addItem(getDelBulbs.getString("bulbs_name"));
        }
        panel.add(bulbNameCb);

        validButton = new JButton(new DelBulbAction(this, "Delete"));
        panel.add(validButton);

        return panel;
    }

    public JComboBox getBulbNameCb() {
        return bulbNameCb;
    }
}