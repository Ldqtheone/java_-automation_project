package com.mycompany.javadomo.SqlQuery;

import com.mycompany.javadomo.Configuration.Cache;
import com.mycompany.javadomo.JavaDatabaseConnect;

import java.io.IOException;
import java.sql.*;

public class FridgeQuery {

    private Connection connection;
    private Cache cache = new Cache();

    public FridgeQuery() throws IOException {
    }

    public ResultSet getAllFood() throws Throwable {
        connection = JavaDatabaseConnect.connectDatabase();

        String request = "SELECT F.`id`,`food_name`, `room_name`, F.`room_id`, `open_date`, `expiration_date`, `quantity`" +
                "FROM `food` as F " +
                " LEFT JOIN rooms as R " +
                " ON F.`room_id` = R.`id` WHERE R.`home_id` = " + cache.userHome + "";

        PreparedStatement data = connection.prepareStatement(request);

        ResultSet rs = data.executeQuery();

        return rs;
    }
    public ResultSet getDelFood() throws Throwable {
        connection = JavaDatabaseConnect.connectDatabase();

        String request = "SELECT food_name, F.id from `food` as F LEFT JOIN rooms as R " +
                " ON F.`room_id` = R.`id` WHERE R.`home_id` = " + cache.userHome + "";

        PreparedStatement data = connection.prepareStatement(request);

        return data.executeQuery();
    }

    public void DelFood(int id) throws Throwable {
        connection = JavaDatabaseConnect.connectDatabase();

        String request = "DELETE FROM food WHERE id = ?";

        PreparedStatement data = connection.prepareStatement(request);

        data.setInt(1,id);

        data.addBatch();
        data.executeBatch();
    }
    public void addFood(String nameValue, Date expiration, int quantity, int room) throws IOException, SQLException {

        connection = JavaDatabaseConnect.connectDatabase();

        String request = "INSERT INTO `food`(`food_name`, `room_id`, `expiration_date`, `quantity`) VALUES (?,?,?,?)";

        PreparedStatement ps = connection.prepareStatement(request);

        ps.setString(1, nameValue);
        ps.setInt(2,room);
        ps.setDate(3, expiration);
        ps.setInt(4, quantity);


        ps.addBatch();
        ps.executeBatch();
    }

    public void updateFood(int id,Date open, int quantity) throws SQLException, IOException {
        Connection connection = JavaDatabaseConnect.connectDatabase();

        String request = "UPDATE `food` SET open_date = ? , quantity = ? WHERE id = ?";

        PreparedStatement ps = connection.prepareStatement(request);

        ps.setDate(1, open);
        ps.setInt(2, quantity);
        ps.setInt(3, id);

        ps.execute();
    }
}