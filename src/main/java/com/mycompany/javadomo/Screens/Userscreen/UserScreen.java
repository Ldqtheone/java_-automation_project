package com.mycompany.javadomo.Screens.Userscreen;

import com.mycompany.javadomo.Actions.Userscreen.AddAction;
import com.mycompany.javadomo.Actions.Userscreen.DelAction;
import com.mycompany.javadomo.Configuration.Cache;
import com.mycompany.javadomo.SqlQuery.UserQuery;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;

public class UserScreen extends JFrame {
    private JButton button;
    private JButton button2 ;
    private JPanel panelU;

    public UserScreen() throws Throwable {
        super();
        build();
        this.pack();
    }

    private void build() throws Throwable {
        setTitle("USERS");
        setSize(960, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setContentPane(buildContentPane());
    }

    private JPanel buildContentPane() throws Throwable {
        panelU = new JPanel();
        panelU.setLayout(new BorderLayout());
        panelU.setBackground(Color.LIGHT_GRAY);

        JPanel panelActualUser = new JPanel();
        GridLayout gl = new GridLayout(1, 4);
        panelActualUser.setLayout(gl);

        JPanel panelAction = new JPanel();
        GridLayout gla = new GridLayout(0, 2);
        panelAction.setLayout(gla);



        Cache cache = new Cache();
        JLabel label = new JLabel("Connected as: ");
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel actualUserNameLabel = new JLabel(cache.userFirstName);
        actualUserNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel actualUserLastLabel = new JLabel(cache.userLastName);
        actualUserLastLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel actualRankLabel = new JLabel(cache.userRole);
        actualRankLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        panelActualUser.add(label);
        panelActualUser.add(actualUserNameLabel);
        panelActualUser.add(actualUserLastLabel);
        panelActualUser.add(actualRankLabel);

        panelActualUser.setBackground(Color.GRAY);
        panelU.add(panelActualUser,BorderLayout.NORTH);


        button = new JButton( new AddAction(this, "ADD"));
        button.setEnabled(false);
        if(cache.userRole.equals("Admin"))
            button.setEnabled(true);


        button2 = new JButton( new DelAction(this, "DELETE"));
        button2.setEnabled(false);
        if(cache.userRole.equals("Admin"))
            button2.setEnabled(true);

        panelAction.add(button);
        panelAction.add(button2);
        panelU.add(panelAction,BorderLayout.SOUTH);

        JPanel panelLodger = new JPanel();
        GridLayout glaL = new GridLayout(0, 3);
        UserQuery userQuery = new UserQuery();
        ResultSet getUsers = userQuery.getAllUsers();
        panelLodger.setLayout(glaL);

        glaL.setRows(getUsers.getRow());

        JLabel columnUserNameLabel = new JLabel("FIRST NAME");
        actualUserNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel columnUserLastLabel = new JLabel("LAST NAME");
        actualUserLastLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel columnRankLabel = new JLabel("RANK");
        actualRankLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panelLodger.add(columnUserNameLabel);
        panelLodger.add(columnUserLastLabel);
        panelLodger.add(columnRankLabel);

        while (getUsers.next()) {
            JLabel lodgerFirstNameLabel = new JLabel(getUsers.getString("firstName"));
            JLabel lodgerLastNameLabel = new JLabel(getUsers.getString("lastName"));
            JLabel lodgerRankLabel = new JLabel(getUsers.getString("role_name"));

            panelLodger.add(lodgerFirstNameLabel);
            panelLodger.add(lodgerLastNameLabel);
            panelLodger.add(lodgerRankLabel);
        }
        Dimension d = new Dimension();
        d.setSize(360, 240);
        panelLodger.setPreferredSize(d);

        JScrollPane scrollPane = new JScrollPane(panelLodger,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panelU.add(scrollPane,BorderLayout.CENTER);

        return panelU;
    }
}
