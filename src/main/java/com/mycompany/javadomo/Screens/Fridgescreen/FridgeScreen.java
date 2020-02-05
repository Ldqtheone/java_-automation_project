package com.mycompany.javadomo.Screens.Fridgescreen;

import com.mycompany.javadomo.Actions.Fridgescreen.UpdateAction;
import com.mycompany.javadomo.Actions.Fridgescreen.AddAction;
import com.mycompany.javadomo.Actions.Fridgescreen.DelAction;
import com.mycompany.javadomo.SqlQuery.FridgeQuery;
import com.mycompany.javadomo.Tools.Clock.ClockPanel;
import com.mycompany.javadomo.Configuration.Cache;

import javax.swing.*;
import java.awt.*;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;


public class FridgeScreen extends JFrame {

    private JPanel panelU;
    private int id;
    private JTextField[] openDateField = new JTextField[100];
    private JTextField[] quantityField = new JTextField[100];
    private int[] lastQuantity = new int[100];


    public FridgeScreen() throws Throwable {
        super();
        build();
        this.pack();
    }

    private void build() throws Throwable {
        setTitle("FRIDGE");
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

        JPanel panelHeader = new JPanel();
        GridLayout gl = new GridLayout(1, 3);
        panelHeader.setLayout(gl);

        JPanel panelAction = new JPanel();
        GridLayout gla = new GridLayout(0, 2);
        panelAction.setLayout(gla);

        Cache cache = new Cache();
        JLabel label = new JLabel("List of food ");
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel actualUserNameLabel = new JLabel(cache.userFirstName + "'s home");
        actualUserNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        ClockPanel clock = new ClockPanel();
        panelHeader.add(label);
        panelHeader.add(actualUserNameLabel);
        panelHeader.add(clock);

        panelHeader.setBackground(Color.GRAY);
        panelU.add(panelHeader,BorderLayout.NORTH);

        JPanel panelRooms = new JPanel();
        GridLayout glaL = new GridLayout(0, 6);
        FridgeQuery fridgeQuery = new FridgeQuery();
        ResultSet getFood = fridgeQuery.getAllFood();
        panelRooms.setLayout(glaL);
        Dimension d = new Dimension();
        d.setSize(960, 720);
        panelRooms.setPreferredSize(d);
        glaL.setRows(getFood.getRow());

        JLabel roomNameLabel = new JLabel("Room");
        roomNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel foodLabel = new JLabel("Food");
        foodLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel openLabel = new JLabel("Open Date");
        openLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel expirationLabel = new JLabel("Expiration Date");
        expirationLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel quantityLabel = new JLabel("Quantity");
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel blank = new JLabel("");
        blank.setFont(new Font("Arial", Font.PLAIN, 20));

        panelRooms.add(roomNameLabel);
        panelRooms.add(foodLabel);
        panelRooms.add(openLabel);
        panelRooms.add(expirationLabel);
        panelRooms.add(quantityLabel);
        panelRooms.add(blank);

        while (getFood.next()) {
            id = getFood.getInt("id");
            JLabel roomNameQLabel = new JLabel(getFood.getString("room_name"));
            JLabel foodQLabel = new JLabel(getFood.getString("food_name"));
            JButton expirationQLabel = new JButton(String.valueOf(getFood.getDate("expiration_date")));
            openDateField[id] = new JTextField(String.valueOf(new SimpleDateFormat("dd/MM/yyyy")));
            openDateField[id].setText(String.valueOf(getFood.getDate("open_date")));
            quantityField[id] = new JTextField();
            lastQuantity[id] = getFood.getInt("quantity");
            quantityField[id].setText(String.valueOf(lastQuantity[id]));
            if (quantityField[id].getText().equals("0")) {
                quantityField[id].setBackground(Color.GRAY);
            }

            JButton update = new JButton(new UpdateAction(this, "Update", id));

            panelRooms.add(roomNameQLabel);
            panelRooms.add(foodQLabel);
            panelRooms.add(openDateField[id]);
            panelRooms.add(expirationQLabel);
            panelRooms.add(quantityField[id]);
            panelRooms.add(update);
        }
        JScrollPane scrollPane = new JScrollPane(panelRooms,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panelU.add(scrollPane,BorderLayout.CENTER);

        JButton addButton = new JButton(new AddAction(this,"ADD"));
        JButton delButton = new JButton(new DelAction( this,"DELETE"));

        panelAction.add(addButton);
        panelAction.add(delButton);
        panelU.add(panelAction,BorderLayout.SOUTH);

        return panelU;
    }

    public JTextField getOpenDate(int id) {
        return openDateField[id];
    }

    public JTextField getQuantity(int id) {
        return quantityField[id];
    }

    public int getLastQuantity(int id) {
        return lastQuantity[id];
    }
}
