package com.mycompany.javadomo.Screens;

import com.mycompany.javadomo.Actions.Logscreen.DisableButtonAction;
import com.mycompany.javadomo.Actions.Logscreen.LoginAction;
import com.mycompany.javadomo.Configuration.Conf;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class LogScreen extends JFrame {

    private JLabel emailLabel;
    private JTextField emailTf;
    private JLabel passwordLabel;
    private JTextField passwordPf;
    private JCheckBox checkRemember;
    private JButton connexionButton;
    private Timer timer;

    public LogScreen() throws IOException {
        super();
        build();
    }

    private void build() throws IOException {
        setTitle("JAVADOMO");
        setSize(320, 240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(buildContentPane());
    }

    private JPanel buildContentPane() throws IOException {
        Conf conf = new Conf();
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        GridLayout gl = new GridLayout(7, 0);
        panel.setLayout(gl);

        emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        emailTf = new JTextField(conf.usermail, 20);
        passwordLabel = new JLabel("Password");
        passwordPf = new JPasswordField(conf.userpassword, 20);
        checkRemember = new JCheckBox("Remember me", true);
        connexionButton = new JButton(new LoginAction(this, "Log In"));
        connexionButton.setEnabled(false);
        timer = new Timer(1000, new DisableButtonAction(this, "Disable"));
        timer.start();

        panel.add(emailLabel);
        panel.add(emailTf);
        panel.add(passwordLabel);
        panel.add(passwordPf);
        panel.add(checkRemember);
        panel.add(connexionButton);

        return panel;
    }

    public JTextField getEmailTf() {
        return emailTf;
    }

    public JTextField getPasswordPf() {
        return passwordPf;
    }

    public JCheckBox getCheckRemember() {
        return checkRemember;
    }

    public JButton getConnexionButton() {
        return connexionButton;
    }
}



