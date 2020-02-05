package com.mycompany.javadomo.SqlQuery;

import com.mycompany.javadomo.Configuration.Cache;
import com.mycompany.javadomo.JavaDatabaseConnect;


import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SensorQuery {

    private String currentDate;

    public SensorQuery() {
    }

    public ResultSet getAllSensor(int id) throws Throwable {
        Connection connection = JavaDatabaseConnect.connectDatabase();
        Cache cache = new Cache();

        String request = "SELECT T.`id`, `sensor_name`, `status`, `room_name`, `thermostat_id`, `capture_interval`, `max_temp`, `min_temp`,T.`room_id` FROM `temperature_sensors` as T " +
                " LEFT JOIN rooms as R " +
                " ON T.`room_id` = R.`id` WHERE R.`home_id` = " + cache.userHome + " AND `thermostat_id` =" + id;
        PreparedStatement data = connection.prepareStatement(request);

        return data.executeQuery();
    }

    public void updateSensor(int interval, Float max, Float min, String status, int id) throws SQLException, IOException {
        Connection connection = JavaDatabaseConnect.connectDatabase();

        String request = "UPDATE `temperature_sensors` SET `status`= ? ,`capture_interval` = ? , `max_temp` = ?, `min_temp` = ? WHERE id = ?";

        PreparedStatement ps = connection.prepareStatement(request);
        ps.setString(1, status);
        ps.setInt(2, interval);
        ps.setFloat(3, max);
        ps.setFloat(4, min);
        ps.setInt(5, id);


        //ps.executeUpdate();
        ps.execute();
    }

    public ResultSet getDelSensor(int id) throws Throwable {
        Connection connection = JavaDatabaseConnect.connectDatabase();
        Cache cache = new Cache();

        String request = "SELECT `sensor_name` from `temperature_sensors` as T " +
                " LEFT JOIN rooms as R " +
                " ON T.`room_id` = R.`id` WHERE home_id = " + cache.userHome + " AND T.`thermostat_id` = " + id;

        PreparedStatement data = connection.prepareStatement(request);

        return data.executeQuery();


    }

    public void delSensor(String namevalue) throws Throwable {
        Connection connection = JavaDatabaseConnect.connectDatabase();
        Cache cache = new Cache();

        String req = "SELECT T.`id` FROM `temperature_sensors` as T " +
                " LEFT JOIN rooms as R " +
                " ON T.`room_id` = R.`id` " +
                "WHERE home_id = " + cache.userHome + " AND `sensor_name` = '" + namevalue + "'";

        PreparedStatement data = connection.prepareStatement(req);

        ResultSet res = data.executeQuery();
        while (res.next()) {
            int sensorId = res.getInt("id");

            String request = "DELETE FROM `temperature_sensors` WHERE `id` = ?";

            PreparedStatement datat = connection.prepareStatement(request);

            datat.setInt(1, sensorId);

            datat.addBatch();
            datat.executeBatch();
        }
    }

    public void createSensor(String nameValue, String statuValue, int interValue, Float maxValue, Float minValue, int ThermoId, String roomName) throws IOException, SQLException {

        Connection connection = JavaDatabaseConnect.connectDatabase();
        Cache cache = new Cache();
        String req = "SELECT `id` FROM `rooms` WHERE `room_name` = '" + roomName + "' AND `home_id` = "+ cache.userHome+"";

        PreparedStatement data = connection.prepareStatement(req);

        ResultSet res = data.executeQuery();

        while (res.next()) {
            int roomId = res.getInt("id");

            String request = "INSERT INTO `temperature_sensors` (`sensor_name`, `status`, `capture_interval`, `max_temp`, `min_temp`, `room_id`,`thermostat_id`) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement ps = connection.prepareStatement(request);

            ps.setString(1, nameValue);
            ps.setString(2, statuValue);
            ps.setInt(3, interValue);
            ps.setFloat(4, maxValue);
            ps.setFloat(5, minValue);
            ps.setInt(6, roomId);
            ps.setInt(7, ThermoId);


            ps.addBatch();
            ps.executeBatch();
        }
    }

    public ResultSet getAllThermos() throws Throwable {
        Connection connection = JavaDatabaseConnect.connectDatabase();
        Cache cache = new Cache();

        String request = " SELECT T.`id`, `thermostat_name`, `room_name`, T.`status`, `target`, `margin`, T.`room_id`, " +
                " COUNT(DISTINCT TS.`id`) as count FROM `thermostats` as T " +
                " LEFT JOIN rooms as R  " +
                " ON T.`room_id` = R.`id` " +
                " LEFT JOIN `temperature_sensors` as TS " +
                " ON T.`id` = TS.`thermostat_id` " +
                " WHERE R.`home_id` =  " + cache.userHome + " GROUP BY T.id ";
        PreparedStatement data = connection.prepareStatement(request);

        return data.executeQuery();
    }

    public void createThermo(String nameValue, String roomValue, String statuValue, Float targetValue, Float marginValue) throws IOException, SQLException {
        Connection connection = JavaDatabaseConnect.connectDatabase();
        Cache cache =new Cache();

        String req = "SELECT `id` FROM `rooms` WHERE `room_name` = '" + roomValue + "' AND `home_id` = "+ cache.userHome+"";

        PreparedStatement data = connection.prepareStatement(req);

        ResultSet res = data.executeQuery();
        while (res.next()) {
            int roomId = res.getInt("id");

            String request = "INSERT INTO `thermostats` (`thermostat_name`, `room_id`, `status`, `target`, `margin`) VALUES (?,?,?,?,?)";

            PreparedStatement ps = connection.prepareStatement(request);

            ps.setString(1, nameValue);
            ps.setInt(2, roomId);
            ps.setString(3, statuValue);
            ps.setFloat(4, targetValue);
            ps.setFloat(5, marginValue);

            ps.addBatch();
            ps.executeBatch();
        }
    }

    public ResultSet getDelThermo() throws Throwable {
        Connection connection = JavaDatabaseConnect.connectDatabase();
        Cache cache = new Cache();

        String request = "SELECT `thermostat_name` from `thermostats` as T " +
                " LEFT JOIN rooms as R " +
                " ON T.`room_id` = R.`id` " +
                " WHERE home_id = " + cache.userHome + "";

        PreparedStatement data = connection.prepareStatement(request);

        return data.executeQuery();
    }

    public void delThermo(String namevalue) throws Throwable {
        Connection connection = JavaDatabaseConnect.connectDatabase();
        Cache cache = new Cache();


        String req = "SELECT T.`id` FROM `thermostats` as T " +
                " LEFT JOIN rooms as R " +
                " ON T.`room_id` = R.`id` " +
                "WHERE home_id = " + cache.userHome + " AND `thermostat_name` = '" + namevalue + "'";

        PreparedStatement data = connection.prepareStatement(req);

        ResultSet res = data.executeQuery();
        while (res.next()) {
            int thermoId = res.getInt("id");


            String request = "DELETE FROM `thermostats` WHERE `id` = ? ";

            PreparedStatement datat = connection.prepareStatement(request);

            datat.setInt(1, thermoId);

            datat.addBatch();
            datat.executeBatch();
        }
    }

    public void updateThermo(String statusValue, float targetValue, float marginValue, int idValue) throws SQLException, IOException {

        Connection connection = JavaDatabaseConnect.connectDatabase();

        String request = "UPDATE `thermostats` SET `status`= ? ,`target` = ? , `margin` = ? WHERE id = ?";

        PreparedStatement ps = connection.prepareStatement(request);
        ps.setString(1, statusValue);
        ps.setFloat(2, targetValue);
        ps.setFloat(3, marginValue);
        ps.setInt(4, idValue);


        //ps.executeUpdate();
        ps.execute();
    }

    public ArrayList<String> getSelectedSensor(int id) throws Throwable {

        Connection connection = JavaDatabaseConnect.connectDatabase();

        String request = "SELECT `id`, `capture_interval`, `max_temp`, `min_temp`, `status` FROM `temperature_sensors` WHERE id = ?";
        PreparedStatement data = connection.prepareStatement(request);

        data.setInt(1, id);

        ResultSet rs = data.executeQuery();

        ArrayList<String> parameters = new ArrayList<>();
        String interval = "";
        String maxTmp = "";
        String minTmp = "";
        String status = "";

        while (rs.next())
        {
            interval = rs.getString("capture_interval");
            maxTmp = rs.getString("max_temp");
            minTmp = rs.getString("min_temp");
            status = rs.getString("status");
            parameters.add(interval);
            parameters.add(maxTmp);
            parameters.add(minTmp);
            parameters.add(status);
        }

        connection.close();

        return parameters;
    }

    public void insertDataTemp(int id, float temp) throws IOException, SQLException {
        Connection connection = JavaDatabaseConnect.connectDatabase();

        currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

        String request = "INSERT INTO `data_temp`(`sensor_id`, `temp_value`, `capture_date`) VALUES (?,?,?)";

        PreparedStatement ps = connection.prepareStatement(request);

        ps.setInt(1, id);
        ps.setFloat(2, temp);
        ps.setString(3, currentDate);

        ps.addBatch();
        ps.executeBatch();
    }

    public ArrayList<String> getThermoMargin(int id) throws SQLException, IOException {

        Connection connection = JavaDatabaseConnect.connectDatabase();

        String request = "SELECT `id`, `status`, `target`, `margin` FROM `thermostats` WHERE id = ?";
        PreparedStatement data = connection.prepareStatement(request);

        data.setInt(1, id);

        ResultSet rs = data.executeQuery();

        ArrayList<String> parameters = new ArrayList<>();
        String status = "";
        String target = "";
        String margin = "";

        while (rs.next())
        {
            status = rs.getString("status");
            target = rs.getString("target");
            margin = rs.getString("margin");
            parameters.add(status);
            parameters.add(target);
            parameters.add(margin);
        }

        connection.close();

        return parameters;
    }
}
