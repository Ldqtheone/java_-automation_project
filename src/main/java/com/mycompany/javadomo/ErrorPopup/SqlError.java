package com.mycompany.javadomo.ErrorPopup;

import javax.swing.*;

public class SqlError {

    public static void displaySqlError() {
        JOptionPane jop = new JOptionPane();
        JOptionPane.showMessageDialog(null,
                "JAVADOMO can not connect to database !",
                "ERROR", JOptionPane.ERROR_MESSAGE);
        jop.setVisible(true);
    }
}
