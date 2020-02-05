package com.mycompany.javadomo.Screens.Userscreen;

import com.mycompany.javadomo.Actions.Userscreen.Add.AddUserAction;
import com.mycompany.javadomo.Actions.Userscreen.Add.DisableButtonAction;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AddUserScreen extends JFrame{

    private JLabel emailLabel;
    private JTextField emailTf;
    private JLabel passwordLabel;
    private JTextField passwordPf;
    private JButton validButton;
    private Timer timer;
    private JLabel firstNameLabel;
    private JTextField firstNameTf;
    private JLabel lastNameLabel;
    private JTextField lastNameTf;
    private JLabel phoneLabel;
    private JTextField phoneTf;
    private JLabel adressLabel;
    private JTextField adressTf;
    private JLabel postalCodeLabel;
    private JTextField postalCodeTf;
    private JLabel userRankLabel;
    private JComboBox userRankTf;

    public AddUserScreen() throws IOException {
        super();
        build();
    }

    private void build() throws IOException {
        setTitle("ADD USER");
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

        firstNameLabel = new JLabel("first name");
        firstNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(firstNameLabel);

        firstNameTf = new JTextField(20);
        panel.add(firstNameTf);

        lastNameLabel = new JLabel("last name");
        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(lastNameLabel);

        lastNameTf = new JTextField( 20);
        panel.add(lastNameTf);

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(passwordLabel);

        passwordPf = new JPasswordField( 20);
        panel.add(passwordPf);

        emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(emailLabel);

        emailTf = new JTextField( 20);
        panel.add(emailTf);

        phoneLabel = new JLabel("phone");
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(phoneLabel);

        phoneTf = new JTextField( 20);
        panel.add(phoneTf);

        adressLabel = new JLabel("adress");
        adressLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(adressLabel);

        adressTf = new JTextField(20);
        panel.add(adressTf);

        postalCodeLabel = new JLabel("postal code");
        postalCodeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(postalCodeLabel);

        postalCodeTf = new JTextField(20);
        // postalCodeTf.addKeyListener(new PostalCodeListener(this));
        panel.add(postalCodeTf);

        userRankLabel = new JLabel("User rank");
        userRankLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(userRankLabel);

        Object[] elements = new Object[]{"Normal", "Admin"};
        userRankTf  = new JComboBox(elements);

        panel.add(userRankTf);

        validButton = new JButton( new AddUserAction(this, "Validate"));
        validButton.setEnabled(false);
        panel.add(validButton);

        timer = new Timer(500, new DisableButtonAction(this, "Disable"));
        timer.start();

        return panel;
    }

    public JTextField getEmailTf() {
        return emailTf;
    }


    public JTextField getPasswordPf() {
        return passwordPf;
    }

    public JButton getValidButton() {
        return validButton;
    }

    public JTextField getFirstNameTf() {
        return firstNameTf;
    }

    public JTextField getLastNameTf() {
        return lastNameTf;
    }

    public JTextField getPhoneTf() {
        return phoneTf;
    }

    public JTextField getAdressTf() {
        return adressTf;
    }

    public JTextField getPostalCodeTf() {
        return postalCodeTf;
    }

    public JComboBox getUserRankTf() {
        return userRankTf;
    }
}
