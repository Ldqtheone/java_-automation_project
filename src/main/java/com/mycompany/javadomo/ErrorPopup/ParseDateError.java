package com.mycompany.javadomo.ErrorPopup;

import javax.swing.*;

public class ParseDateError {

    public static void displayDateError() {
        JOptionPane jop = new JOptionPane();
        JOptionPane.showMessageDialog(null,
                "Wrong date format to update ! Please write correct date.",
                "ERROR", JOptionPane.ERROR_MESSAGE);
        jop.setVisible(true);
    }

    public static void displayFoodDateError(){
        JOptionPane jop = new JOptionPane();
        JOptionPane.showMessageDialog(null,
                "Wrong date format to update ! Please write correct date or let null.",
                "ERROR", JOptionPane.ERROR_MESSAGE);
        jop.setVisible(true);
    }
}
