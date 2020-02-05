package com.mycompany.javadomo;

import com.mycompany.javadomo.Configuration.Conf;
import com.mycompany.javadomo.ErrorPopup.SqlError;

import java.io.IOException;
import java.sql.*;

public class JavaDatabaseConnect {

    public JavaDatabaseConnect() {
    }

    public static Connection connectDatabase() throws IOException {
        Conf conf = new Conf();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + conf.ipAdress + ":" + conf.port + "/"
                    + conf.dbName, conf.username, conf.password);

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            System.out.println("Failed to make connection!");
            SqlError.displaySqlError();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static boolean iscoValid(Connection connection) {
        if (connection == null) {
            return false;
        }
        ResultSet ping = null;
        try {
            if (connection.isClosed()) {
                return false;
            }
            ping = connection.createStatement().executeQuery("SELECT 1");
            return ping.next();
        } catch (SQLException sqle) {
            return false;
        } finally {
            if (ping != null) {
                try {
                    ping.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public static void closeConnection(Connection connection) throws Throwable {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Connection closed.");
        }
    }
}

