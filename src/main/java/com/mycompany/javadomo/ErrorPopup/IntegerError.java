package com.mycompany.javadomo.ErrorPopup;

import javax.swing.*;

public class IntegerError {

    public static void displayIntError() {
        JOptionPane jop = new JOptionPane();
        JOptionPane.showMessageDialog(null,
                "Unable to write char , please use numbers",
                "ERROR", JOptionPane.ERROR_MESSAGE);
        jop.setVisible(true);
    }

    public static void displayFloatError(){
        JOptionPane jop = new JOptionPane();
        JOptionPane.showMessageDialog(null,
                "Only format \"10.5\" accepted ",
                "ERROR", JOptionPane.ERROR_MESSAGE);
        jop.setVisible(true);
    }
}
