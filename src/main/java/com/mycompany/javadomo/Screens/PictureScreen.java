package com.mycompany.javadomo.Screens;

import com.mycompany.javadomo.Actions.Homescreen.*;
import com.mycompany.javadomo.SqlQuery.CameraQuery;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;

public class PictureScreen extends JFrame {

	private JButton[] button = new JButton[100];
	private JPanel panelM;
	private JPanel panel;
	private JPanel panelP;
	private String imageLocation = "asset/photos/";
	private String imgExt = ".png";

	public PictureScreen() throws Throwable {
		super();
		build();
		this.pack();
	}

	private void build() throws Throwable {
		setTitle("PICTURES");
		setSize(1280, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new OnCloseAction());
		setResizable(false);
		setLocationRelativeTo(null);
		setContentPane(buildContentPane());
	}

	private JScrollPane buildContentPane() throws Throwable {

		panelM = new JPanel();
		GridLayout gl = new GridLayout(2, 0);
		panelM.setLayout(gl);
		Dimension d = new Dimension();
		d.setSize(1280, 800);
		panelM.setPreferredSize(d);
		panelM.setBackground(Color.darkGray);

		CameraQuery cameraQuery = new CameraQuery();
		ResultSet getCamera = cameraQuery.getAllPictures();

		int i = 0;

		while (getCamera.next()) {
			System.out.println(imageLocation + getCamera.getString("photo_path") + imgExt);

			BufferedImage iconUser = ImageIO.read(new File(imageLocation + getCamera.getString("photo_path") + imgExt));
			button[i] = new JButton();
			button[i].setText("");
			button[i].setIcon(new ImageIcon(iconUser));
			JLabel nameLabel = new JLabel(getCamera.getString("C.camera_name"));
			JLabel dateLabel = new JLabel(getCamera.getString("capture_date"));

			panel = new JPanel(new BorderLayout());
			panel.setBackground(Color.LIGHT_GRAY);

			panelP = new JPanel();
			GridLayout gla = new GridLayout(0, 2);
			panelP.setLayout(gla);
			panelP.setBackground(Color.lightGray);

			panel.add(button[i], BorderLayout.CENTER);
			panelP.add(nameLabel);
			panelP.add(dateLabel);
			panel.add(panelP, BorderLayout.SOUTH);
			panelM.add(panel);
			i++;
		}

		JScrollPane scrollPane = new JScrollPane(panelM,
				JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		return scrollPane;
	}
}
