package com.mycompany.javadomo.ErrorPopup;

import javax.swing.*;

public class IdentifierError {

    public static void displayIdentifierError() {
        JOptionPane jop = new JOptionPane();
        JOptionPane.showMessageDialog(null,
                "Wrong Email and/or Password !",
                "ERROR", JOptionPane.ERROR_MESSAGE);
        jop.setVisible(true);
    }
}
