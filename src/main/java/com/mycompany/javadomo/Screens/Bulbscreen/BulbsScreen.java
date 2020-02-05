package com.mycompany.javadomo.Screens.Bulbscreen;

import com.mycompany.javadomo.Actions.Bulbsscreen.AddAction;
import com.mycompany.javadomo.Actions.Bulbsscreen.DelAction;
import com.mycompany.javadomo.Actions.Bulbsscreen.UpdateAction;
import com.mycompany.javadomo.Configuration.Cache;
import com.mycompany.javadomo.SqlQuery.BulbsQuery;
import com.mycompany.javadomo.Tools.Clock.ClockPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class BulbsScreen extends JFrame {

    private JPanel panelU;
    private JComboBox<String>[] colorLabel = new JComboBox[1000];
    private JComboBox<String>[] statusLabel = new JComboBox[1000];
    private JTextField[] startLabel = new JTextField[1000];
    private JTextField[] stopLabel = new JTextField[1000];
    private int id;
    private JLabel colorULabel;


    public BulbsScreen() throws Throwable {
        super();
        build();
        this.pack();
    }

    private void build() throws Throwable {
        setTitle("BULBS");
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
        JLabel label = new JLabel("List of Bulbs ");
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel actualUserNameLabel = new JLabel(cache.userFirstName + "'s home");
        actualUserNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        ClockPanel clock = new ClockPanel();

        panelHeader.add(label);
        panelHeader.add(actualUserNameLabel);
        panelHeader.add(clock);

        panelHeader.setBackground(Color.GRAY);
        panelU.add(panelHeader,BorderLayout.NORTH);

        JPanel panelBulbs = new JPanel();
        GridLayout glaL = new GridLayout(0, 7);
        BulbsQuery bulbsQuery = new BulbsQuery();
        ResultSet getBulbs = bulbsQuery.getAllBulbs();
        panelBulbs.setLayout(glaL);
        Dimension d = new Dimension();
        d.setSize(960, 720);
        panelBulbs.setPreferredSize(d);

        glaL.setRows(getBulbs.getRow());

        JLabel bulbsNameLabel = new JLabel("Bulb");
        bulbsNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel roomNameLabel = new JLabel("Room");
        roomNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel bulbsColor = new JLabel("Color");
        bulbsColor.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel state = new JLabel("On/Off");
        state.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel start = new JLabel("Start time");
        state.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel stop = new JLabel("Stop time");
        state.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel action = new JLabel("");
        state.setFont(new Font("Arial", Font.PLAIN, 20));

        panelBulbs.add(bulbsNameLabel);
        panelBulbs.add(roomNameLabel);
        panelBulbs.add(bulbsColor);
        panelBulbs.add(state);
        panelBulbs.add(start);
        panelBulbs.add(stop);
        panelBulbs.add(action);

        String[] status = {"on","off","scheduled"};
        String[] allColors = {"red","purple","blue","green","yellow","orange","white","pink"};

        while (getBulbs.next()) {
            id = getBulbs.getInt("id");
            statusLabel[id] = new JComboBox<>(status);
            statusLabel[id].setSelectedItem(getBulbs.getString("status"));
            if(id == 26){
            colorLabel[id] = new JComboBox<>(allColors);
            colorLabel[id].setSelectedItem(getBulbs.getString("color"));
            } else {
                colorULabel = new JLabel("white");
            }

            startLabel[id]= new JTextField(String.valueOf(new SimpleDateFormat("HH:mm:ss")));
            startLabel[id].setText(String.valueOf(getBulbs.getTime("start_time")));
            stopLabel[id]= new JTextField(String.valueOf(new SimpleDateFormat("HH:mm:ss")));
            stopLabel[id].setText(String.valueOf(getBulbs.getTime("end_time")));
            JLabel nameLabel = new JLabel(getBulbs.getString("bulbs_name"));
            JLabel roomLabel = new JLabel(getBulbs.getString("room_name"));
            JButton update = new JButton(new UpdateAction(this, "Update", id));

            panelBulbs.add(nameLabel);
            panelBulbs.add(roomLabel);
            if(id == 26) {
                panelBulbs.add(colorLabel[id]);
            }else{
                panelBulbs.add(colorULabel);
            }
            panelBulbs.add(statusLabel[id]);
            panelBulbs.add(startLabel[id]);
            panelBulbs.add(stopLabel[id]);
            panelBulbs.add(update);
        }

        JScrollPane scrollPane = new JScrollPane(panelBulbs,
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

    public JComboBox getColorCb(int id) {
        return colorLabel[id];
    }

    public JComboBox getStatusCb(int id) {
        return statusLabel[id];
    }

    public JTextField getStartLabel(int id) {
        return startLabel[id];
    }

    public JTextField getStopLabel(int id) {
        return stopLabel[id];
    }
}
