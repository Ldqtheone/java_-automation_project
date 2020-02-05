package com.mycompany.javadomo.Screens;

import com.mycompany.javadomo.Actions.Homescreen.*;
import com.mycompany.javadomo.Configuration.Cache;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HomeScreen extends JFrame {

    private JButton button;
    private JButton button2 ;
    private JButton button3 ;
    private JButton button4 ;
    private JButton button5 ;
    private JButton button6 ;
    private JButton button7 ;
    private JButton button8;
    private JPanel panelM;


    public HomeScreen() throws IOException {
        super();
        build();
    }

    private void build() throws IOException {
        setTitle("JAVADOMO");
        setSize(1280, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new OnCloseAction());
        setResizable(true);
        setLocationRelativeTo(null);
        setContentPane(buildContentPane());
    }

    private JPanel buildContentPane() throws IOException {

        Cache cache = new Cache();
        panelM = new JPanel();
        GridLayout gl = new GridLayout(2, 4);
        panelM.setLayout(gl);
        panelM.setBackground(Color.darkGray);

        BufferedImage iconUser = ImageIO.read(new File("asset/user.png"));
        button = new JButton( new UserAction(this,"user"));
        button.setText("");
        button.setIcon(new ImageIcon(iconUser));

        BufferedImage iconHome = ImageIO.read(new File("asset/home.png"));
        button2 = new JButton( new RoomAction(this,"home"));
        button2.setText("");
        button2.setIcon(new ImageIcon(iconHome));

        BufferedImage iconBulb = ImageIO.read(new File("asset/bulbs.png"));
        button3 = new JButton( new BulbsAction(this,"bulbs"));
        button3.setText("");
        button3.setIcon(new ImageIcon(iconBulb));

        BufferedImage iconFridge = ImageIO.read(new File("asset/fridge.png"));
        button4 = new JButton( new FridgeAction(this,"fridge"));
        button4.setText("");
        button4.setIcon(new ImageIcon(iconFridge));

        BufferedImage iconTemp = ImageIO.read(new File("asset/temp.png"));
        button5 = new JButton( new TempAction(this,"temp"));
        button5.setText("");
        button5.setIcon(new ImageIcon(iconTemp));

        BufferedImage iconCam = ImageIO.read(new File("asset/cam.png"));
        button6 = new JButton( new CamAction(this,"cam"));
        button6.setText("");
        button6.setIcon(new ImageIcon(iconCam));
        button6.setEnabled(false);
        if(cache.userRole.equals("Admin"))
            button6.setEnabled(true);

        BufferedImage iconGallery = ImageIO.read(new File("asset/gallery.png"));
        button7 = new JButton( new PictureAction(this,"Pictures"));
        button7.setText("");
        button7.setIcon(new ImageIcon(iconGallery));

        BufferedImage iconStat = ImageIO.read(new File("asset/stats.png"));
        button8 = new JButton( new StatsAction(this,"stats"));
        button8.setText("");
        button8.setIcon(new ImageIcon(iconStat));

        panelM.add(button);
        panelM.add(button2);
        panelM.add(button3);
        panelM.add(button4);
        panelM.add(button5);
        panelM.add(button6);
        panelM.add(button7);
        panelM.add(button8);

        return panelM;
    }
}
