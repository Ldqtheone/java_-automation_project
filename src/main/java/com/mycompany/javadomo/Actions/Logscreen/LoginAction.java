package com.mycompany.javadomo.Actions.Logscreen;

import com.mycompany.javadomo.Configuration.Cache;
import com.mycompany.javadomo.ErrorPopup.IdentifierError;
// import com.mycompany.javadomo.Graphics.AvgBulbsGraphics;
import com.mycompany.javadomo.Graphics.AvgBulbsGraphics;
import com.mycompany.javadomo.Graphics.BulbColorGraphics;
import com.mycompany.javadomo.Graphics.TempGraphics;
import com.mycompany.javadomo.Graphics.AvgTempGraphics;
import com.mycompany.javadomo.Screens.HomeScreen;
import com.mycompany.javadomo.Screens.LogScreen;
import com.mycompany.javadomo.SqlQuery.LoginQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginAction extends AbstractAction {

    private LogScreen panel;

    public LoginAction(LogScreen logScreen, String name) {
        super(name);

        this.panel = logScreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String emailValue = panel.getEmailTf().getText();
        String passwordValue = panel.getPasswordPf().getText();
        boolean rememberCb = panel.getCheckRemember().isSelected();

         Cache.generateCache();

        LoginQuery loginQuery = new LoginQuery();
        try {
            if (loginQuery.UserLogin(emailValue, passwordValue, rememberCb)){
                panel.dispose();
                HomeScreen homeScreen = new HomeScreen();
                homeScreen.setVisible(true);
            }
        } catch (Throwable throwable) {
            IdentifierError.displayIdentifierError();
            throwable.printStackTrace();
        }
    }
}
