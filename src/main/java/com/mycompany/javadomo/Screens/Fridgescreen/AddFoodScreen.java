package com.mycompany.javadomo.Screens.Fridgescreen;

import com.mycompany.javadomo.Actions.Fridgescreen.AddFood.AddFoodAction;
import com.mycompany.javadomo.Actions.Fridgescreen.AddFood.DisableButtonAction;
import com.mycompany.javadomo.SqlQuery.FridgeQuery;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.ResultSet;

public class AddFoodScreen extends JFrame {

    private JButton validButton;
    private Timer timer;
    private JLabel foodNameLabel;
    private JTextField foodNameTf;
    private JLabel expirationLabel;
    private JTextField expirationTf;
    private JLabel quantityLabel;
    private JTextField quantityTf;
    private int id;


    public AddFoodScreen() throws Throwable {
        super();
        build();
    }

    private void build() throws Throwable {
        setTitle("ADD FOOD");
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(buildContentPane());
    }

    private JPanel buildContentPane() throws Throwable {

        JPanel panel = new JPanel();
        GridLayout gl = new GridLayout(17, 0);
        panel.setLayout(gl);

        foodNameLabel = new JLabel("Food name");
        foodNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(foodNameLabel);

        foodNameTf = new JTextField(20);
        panel.add(foodNameTf);

        expirationLabel = new JLabel("Expiration Date");
        expirationLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(expirationLabel);

        expirationTf = new JTextField( 20);
        panel.add(expirationTf);

        quantityLabel = new JLabel("Quantity");
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(quantityLabel);

        quantityTf = new JTextField( 20);
        panel.add(quantityTf);

        FridgeQuery fridgeQuery = new FridgeQuery();
        ResultSet getFood = fridgeQuery.getAllFood();

        while (getFood.next()) {
            id = getFood.getInt("room_id");
        }

        validButton = new JButton( new AddFoodAction(this, "Validate", id));
        validButton.setEnabled(false);
        panel.add(validButton);

        timer = new Timer(500, new DisableButtonAction(this, "Disable"));
        timer.start();

        return panel;
    }

    public JButton getValidButton() {
        return validButton;
    }

    public JTextField getFoodNameTf() {
        return foodNameTf;
    }

    public JTextField getExpirationTf() {
        return expirationTf;
    }

    public JTextField getQuantityTf() {
        return quantityTf;
    }
}
