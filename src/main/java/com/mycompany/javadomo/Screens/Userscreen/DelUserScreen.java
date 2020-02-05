package com.mycompany.javadomo.Screens.Userscreen;

import com.mycompany.javadomo.Actions.Userscreen.Del.DelUserAction;
import com.mycompany.javadomo.SqlQuery.UserQuery;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class DelUserScreen extends JFrame{

    private JLabel userNameLabel;
    private JComboBox<String> userNameCb;
    private JButton validButton;

    public DelUserScreen() throws Throwable {
        super();
        build();
    }

    private void build() throws Throwable {
        setTitle("DELETE USER");
        setSize(320, 240);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(buildContentPane());
    }

    private JPanel buildContentPane() throws Throwable {

        JPanel panel = new JPanel();
        GridLayout gl = new GridLayout(3, 0);
        UserQuery userQuery = new UserQuery();
        ResultSet getDelUsers = userQuery.getDelUsers();
        panel.setLayout(gl);

        userNameLabel = new JLabel("Choose the user you wan't to delete");
        userNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(userNameLabel);

        String[] names = {"No Selection"};
        userNameCb = new JComboBox<String>(names);
        while (getDelUsers.next()) {
            userNameCb.addItem(getDelUsers.getString("firstName"));
        }
        panel.add(userNameCb);

        validButton = new JButton( new DelUserAction(this, "Delete"));
        panel.add(validButton);

        return panel;
    }

    public JComboBox getUserNameCb() {
        return userNameCb;
    }
}
