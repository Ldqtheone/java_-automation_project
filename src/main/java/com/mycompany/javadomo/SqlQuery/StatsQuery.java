package com.mycompany.javadomo.SqlQuery;

import com.mycompany.javadomo.Configuration.Cache;
import com.mycompany.javadomo.JavaDatabaseConnect;
import org.jfree.data.jdbc.JDBCCategoryDataset;

import java.io.IOException;
import java.sql.*;

public class StatsQuery {

    private Connection connection;

    public JDBCCategoryDataset getTempValues() throws IOException, SQLException {

        connection = JavaDatabaseConnect.connectDatabase();
        String request = "SELECT `sensor_name`, AVG(`temp_value`) FROM `data_temp` as dtemp LEFT JOIN temperature_sensors as T ON dtemp.`sensor_id` = T.`id` LEFT JOIN rooms as R ON T.`room_id` = R.`id` WHERE `room_name` = \"Salon\" GROUP BY `sensor_id` ORDER BY `temp_value` ASC";

        JDBCCategoryDataset dataset = new JDBCCategoryDataset(connection);
        dataset.executeQuery(request);

        return dataset;
    }

    public ResultSet getCharts() throws IOException, SQLException {
        Connection connection = JavaDatabaseConnect.connectDatabase();

        String request = "SELECT id, graph_name FROM `graphics`";
        PreparedStatement data = connection.prepareStatement(request);

        return data.executeQuery();
    }

    public ResultSet getAvgTemp() throws Throwable {
        connection = JavaDatabaseConnect.connectDatabase();
        Cache cache = new Cache();

        String request = "SELECT `room_name`, AVG(`temp_value`) AS average_temps ,DATE(`capture_date`) AS date," +
                " HOUR(`capture_date`) as hours, MAX(temp_value) as maxTemp, MIN(temp_value) as minTemp FROM `data_temp` AS DT  LEFT JOIN `temperature_sensors` AS TS  " +
                "ON DT.`sensor_id`=TS.`id`  LEFT JOIN `rooms`AS R  ON TS.`room_id`= R.`id`  " +
                "WHERE `home_id` =" + cache.userHome + " " +
                "AND `capture_date` BETWEEN DATE_SUB(NOW(),INTERVAL 24 HOUR) AND NOW() " +
                "GROUP BY 3,4";

        PreparedStatement data = connection.prepareStatement(request);
        return data.executeQuery();
    }

    public ResultSet getAvgBulbs() throws Throwable {
        connection = JavaDatabaseConnect.connectDatabase();
        Cache cache = new Cache();

        String request = "SELECT R.room_name, `bulbs_name`, TIME_TO_SEC(TIMEDIFF((SELECT `action_date` FROM `data_bulbs` WHERE `action` = 0 " +
                "and `bulb_id` = B.`id` AND `id` > (SELECT `id` FROM `data_bulbs` WHERE `action` = 1 " +
                "and `bulb_id` = B.`id` LIMIT 1) LIMIT 1), (SELECT `action_date` FROM `data_bulbs` " +
                "WHERE `action` = 1 and `bulb_id` = B.`id` LIMIT 1))) as Duree FROM `data_bulbs` as db " +
                "LEFT JOIN bulbs as B ON db.`bulb_id` = B.`id` " +
                "LEFT JOIN rooms as R ON B.room_id = R.id WHERE `home_id` =" + cache.userHome + " " +
                "AND `action_date` BETWEEN DATE_SUB(NOW(),INTERVAL 24 HOUR) AND NOW()" +
                "GROUP BY `bulbs_name` ORDER BY Duree DESC";

        PreparedStatement data = connection.prepareStatement(request);
        return data.executeQuery();
    }

    public ResultSet getBulbsColor() throws IOException, SQLException {
        connection = JavaDatabaseConnect.connectDatabase();
        Cache cache = new Cache();

        String request = "SELECT COUNT(color) as Count , color FROM bulbs as B LEFT JOIN rooms as R ON " +
                "B.room_id = R.id WHERE R.home_id = " + cache.userHome +" GROUP BY color";

        PreparedStatement data = connection.prepareStatement(request);
        return data.executeQuery();
    }

    public ResultSet getMinMaxTemp() throws IOException, SQLException {
        connection = JavaDatabaseConnect.connectDatabase();
        Cache cache = new Cache();

        String request = "SELECT MAX(temp_value) as maxTemp, MIN(temp_value) as minTemp FROM `data_temp` AS DT  LEFT JOIN `temperature_sensors` AS TS  " +
                "ON DT.`sensor_id`=TS.`id`  LEFT JOIN `rooms`AS R  ON TS.`room_id`= R.`id`  " +
                "WHERE `home_id` =" + cache.userHome + " " +
                "AND `capture_date` BETWEEN DATE_SUB(NOW(),INTERVAL 24 HOUR) AND NOW()";

        PreparedStatement data = connection.prepareStatement(request);
        return data.executeQuery();
    }
}
