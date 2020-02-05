package com.mycompany.javadomo.SqlQuery;

import com.mycompany.javadomo.Configuration.Cache;
import com.mycompany.javadomo.Configuration.Conf;
import com.mycompany.javadomo.ErrorPopup.IdentifierError;
import com.mycompany.javadomo.JavaDatabaseConnect;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class LoginQuery {

    private String username;
    private String lastName;
    private String role;
    private int home;

    public boolean UserLogin(String emailValue, String passwordValue, boolean remember) throws SQLException, IOException {

        Connection connection = JavaDatabaseConnect.connectDatabase();

        PreparedStatement ps = connection.prepareStatement("select firstName, user_email, password, lastName, " +
                "role_name, home_id from users as U LEFT JOIN role as R ON U.`user_role` = R.`id` " +
                "where user_email = ? and password = ?");

        ps.setString(1, emailValue);
        ps.setString(2, passwordValue);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            username = rs.getString("firstName");
            System.out.println("Connection de " + username + " en cours ...");
            lastName = rs.getString("lastName");
            role = rs.getString("role_name");
            home = rs.getInt("home_id");

            Conf conf = new Conf();
            Cache cache = new Cache();

            cache.setUserFirstName(username);
            cache.setUserLastName(lastName);
            cache.setUserRole(role);
            cache.setUserHome(home);

            if (remember) {
                try {
                    conf.setUsermail(emailValue);
                    conf.setUserpassword(passwordValue);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            } else {
                try {
                    conf.setUsermail("");
                    conf.setUserpassword("");
                    cache.deleteCache();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            return true;
        } else {
            System.out.println("Login error ...");
            IdentifierError.displayIdentifierError();
            return false;
        }
    }
}
