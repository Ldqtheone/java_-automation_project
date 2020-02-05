package com.mycompany.javadomo.Screens.Fridgescreen;

import com.mycompany.javadomo.Actions.Fridgescreen.DelFood.DelFoodAction;
import com.mycompany.javadomo.SqlQuery.FridgeQuery;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class DelFoodScreen extends JFrame {

    private JLabel foodNameLabel;
    private JComboBox<String> foodNameCb;
    private JButton validButton;
    private int id;

    public DelFoodScreen() throws Throwable{
        super();
        build();
    }
    private void build() throws Throwable {
        setTitle("DELETE FOOD");
        setSize(320, 240);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(buildContentPane());
    }

    private JPanel buildContentPane() throws Throwable {

        JPanel panel = new JPanel();
        GridLayout gl = new GridLayout(3, 0);
        FridgeQuery fridgeQuery = new FridgeQuery();
        ResultSet getDelFood = fridgeQuery.getDelFood();
        panel.setLayout(gl);

        foodNameLabel = new JLabel("Which food you wan't to remove from fridge ?");
        foodNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(foodNameLabel);

        String[] names = {"No Selection"};
        foodNameCb = new JComboBox<String>(names);
        while (getDelFood.next()) {
            id = getDelFood.getInt("id");
            foodNameCb.addItem(getDelFood.getString("food_name"));
        }
        panel.add(foodNameCb);

        validButton = new JButton( new DelFoodAction(this, "Delete", id));
        panel.add(validButton);

        return panel;
    }

    public JComboBox getFoodNameCb() {
        return foodNameCb;
    }
}
