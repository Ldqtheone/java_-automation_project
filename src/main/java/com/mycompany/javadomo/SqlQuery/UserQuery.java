package com.mycompany.javadomo.SqlQuery;

import com.mycompany.javadomo.Configuration.Cache;
import com.mycompany.javadomo.JavaDatabaseConnect;

import java.io.IOException;
import java.sql.*;

public class UserQuery {

    private Connection connection;
    private Cache cache = new Cache();

    public UserQuery() throws IOException {
    }

    public ResultSet getAllUsers() throws Throwable {
        connection = JavaDatabaseConnect.connectDatabase();

        String request = "SELECT firstName, lastName, role_name " +
                "FROM `users` as U LEFT JOIN role as R ON U.`user_role` = R.`id` WHERE home_id = " + cache.userHome;

        PreparedStatement data = connection.prepareStatement(request);

        return data.executeQuery();
    }

    public void createUser(String firstName, String lastName, String email, String address, String phone,
                           int postal, String password, int rank) throws IOException, SQLException {

        Connection connection = JavaDatabaseConnect.connectDatabase();

        String request = "INSERT INTO `users`(`firstName`, `lastName`, `password`, `user_email`, `user_phone`, `user_address`, `user_postalcode`, `user_role`, `home_id`) VALUES (?,?,?,?,?,?,?,?," + cache.userHome + ")";

        PreparedStatement ps = connection.prepareStatement(request);

        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setString(3, password);
        ps.setString(4, email);
        ps.setString(5, phone);
        ps.setString(6, address);
        ps.setInt(7, postal);
        ps.setInt(8, rank);

        ps.addBatch();
        ps.executeBatch();
    }

    public ResultSet getDelUsers() throws Throwable {
        connection = JavaDatabaseConnect.connectDatabase();

        String request = "SELECT firstName from `users` as U LEFT JOIN role as R ON U.`user_role` = R.`id` WHERE user_role != 2 AND home_id = " + cache.userHome;

        PreparedStatement data = connection.prepareStatement(request);

        return data.executeQuery();
    }

    public void DelUsers(String namevalue) throws Throwable {
        connection = JavaDatabaseConnect.connectDatabase();

        String request = "DELETE FROM users WHERE firstName = ?  and home_id = " + cache.userHome;

        PreparedStatement data = connection.prepareStatement(request);

        data.setString(1,namevalue);

        data.addBatch();
        data.executeBatch();
    }
}