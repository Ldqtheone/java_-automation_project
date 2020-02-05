package com.mycompany.javadomo.SqlQuery;

import com.mycompany.javadomo.Configuration.Cache;
import com.mycompany.javadomo.JavaDatabaseConnect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomQuery {

    private Connection connection;
    private Cache cache = new Cache();

    public RoomQuery() throws IOException {
    }

    public ResultSet getAllRooms() throws Throwable {
        connection = JavaDatabaseConnect.connectDatabase();

        String request = " SELECT T.id as tid ,R.room_name, R.room_description , COUNT(DISTINCT TS.`id`) as nb_sensor , " +
                "COUNT(DISTINCT B.`id`) as nb_bulbs " +
                " FROM `rooms` as R " +
                " LEFT JOIN `temperature_sensors` as TS " +
                " ON R.`id` = TS.`room_id` " +
                " LEFT JOIN `users` as U " +
                " ON R.`home_id` = U.`home_id` " +
                " LEFT JOIN `thermostats` as T " +
                " ON R.`id`= T.`room_id`" +
                " LEFT JOIN `bulbs` as B " +
                " ON R.`id` = B.`room_id` " +
                " WHERE R.`home_id` = " + cache.userHome +
                " GROUP BY R.`room_name` " +
                " ORDER BY R.`room_name` ASC ";

        PreparedStatement data = connection.prepareStatement(request);

        ResultSet rs = data.executeQuery();

        return rs;
    }
    public ResultSet getDelRooms() throws Throwable {
        connection = JavaDatabaseConnect.connectDatabase();

        String request = "SELECT room_name, id from `rooms` WHERE home_id = "+ cache.userHome ;

        PreparedStatement data = connection.prepareStatement(request);

        return data.executeQuery();


    }
    public void DelRoom(String namevalue) throws Throwable {
        connection = JavaDatabaseConnect.connectDatabase();

        String request = "DELETE FROM rooms WHERE room_name = ?  and home_id = " + cache.userHome;

        PreparedStatement data = connection.prepareStatement(request);

        data.setString(1,namevalue);

        data.addBatch();
        data.executeBatch();
    }
    public void createRoom(String nameValue, String descValue) throws IOException, SQLException {

        Connection connection = JavaDatabaseConnect.connectDatabase();

        String request = "INSERT INTO `rooms`(`room_name`, `room_description`, `home_id`) VALUES (?,?," + cache.userHome + ")";

        PreparedStatement ps = connection.prepareStatement(request);

        ps.setString(1, nameValue);
        ps.setString(2, descValue);


        ps.addBatch();
        ps.executeBatch();
    }
}