package com.mycompany.javadomo.SqlQuery;

import com.mycompany.javadomo.Configuration.Cache;
import com.mycompany.javadomo.JavaDatabaseConnect;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class BulbsQuery {

    private String currentDate;

    public BulbsQuery() {
    }

    public ResultSet getAllBulbs() throws Throwable {
        Connection connection = JavaDatabaseConnect.connectDatabase();
        Cache cache = new Cache();

        String request = "SELECT B.`id`, `bulbs_name`, `status`, `room_name`, `color`, `start_time`, `end_time` FROM `bulbs` as B " +
                " LEFT JOIN rooms as R " +
                " ON B.`room_id` = R.`id` WHERE R.`home_id` = " + cache.userHome + "";
        PreparedStatement data = connection.prepareStatement(request);

        return data.executeQuery();
    }

    public void updateBulbs(String color, String status, Time start, Time stop, int id) throws SQLException, IOException {
        Connection connection = JavaDatabaseConnect.connectDatabase();

        String request = "UPDATE `bulbs` SET color = ? , status = ?, `start_time`= ?,`end_time`= ? WHERE id = ?";

        PreparedStatement ps = connection.prepareStatement(request);

        ps.setString(1, color);
        ps.setString(2, status);
        ps.setTime(3,start);
        ps.setTime(4,stop);
        ps.setInt(5, id);


        //ps.executeUpdate();
        ps.execute();
    }

    public void createBulbs(String nameValue, String roomValue, String statuValue, String colorValue, Time startSqlDate, Time endSqlDate) throws SQLException, IOException {
        Connection connection = JavaDatabaseConnect.connectDatabase();
        Cache cache = new Cache();
        String req = "SELECT `id` FROM `rooms` WHERE `room_name` = '" + roomValue + "' AND `home_id` = "+ cache.userHome+"";

        PreparedStatement data = connection.prepareStatement(req);

        ResultSet res = data.executeQuery();
        while (res.next()) {
            int roomId = res.getInt("id");
            String request = "INSERT INTO `bulbs` (`bulbs_name`, `room_id`, `status`, `color`, `start_time`, `end_time`) VALUES (?,?,?,?,?,?)";

            PreparedStatement ps = connection.prepareStatement(request);

            ps.setString(1, nameValue);
            ps.setInt(2, roomId);
            ps.setString(3, statuValue);
            ps.setString(4, colorValue);
            ps.setTime(5, startSqlDate);
            ps.setTime(6, endSqlDate);


            ps.addBatch();
            ps.executeBatch();
        }
    }

    public void delBulb(String nameValue) throws IOException, SQLException {
        Connection connection = JavaDatabaseConnect.connectDatabase();
        Cache cache = new Cache();


        String req = "SELECT B.`id` FROM `bulbs` as B " +
                " LEFT JOIN rooms as R " +
                " ON B.`room_id` = R.`id` " +
                "WHERE home_id = " + cache.userHome + " AND `bulbs_name` = '" + nameValue + "'";

        PreparedStatement data = connection.prepareStatement(req);

        ResultSet res = data.executeQuery();
        while (res.next()) {
            int bulbId = res.getInt("id");


            String request = "DELETE FROM `bulbs` WHERE `id` = ? ";

            PreparedStatement datat = connection.prepareStatement(request);

            datat.setInt(1, bulbId);

            datat.addBatch();
            datat.executeBatch();
        }
    }

    public void switchOnOff(int id, int action) throws SQLException, IOException {
        Connection connection = JavaDatabaseConnect.connectDatabase();

        currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

        String request = "INSERT INTO `data_bulbs`(`bulb_id`, `action`, `action_date`) VALUES (?,?,?)";

        PreparedStatement ps = connection.prepareStatement(request);

        ps.setInt(1, id);
        ps.setInt(2, action);
        ps.setString(3, currentDate);

        ps.addBatch();
        ps.executeBatch();
    }

    public ArrayList<String> getSelectedBulb(int id) throws Throwable {

        Connection connection = JavaDatabaseConnect.connectDatabase();

        String request = "SELECT `id`, `status`, `color`, `start_time`, `end_time` FROM `bulbs` WHERE id = ?";
        PreparedStatement data = connection.prepareStatement(request);

        data.setInt(1, id);

        ResultSet rs = data.executeQuery();

        ArrayList<String> parameters = new ArrayList<String>();
        String status = "";
        String color = "";
        Time start = null;
        Time end = null;

        while (rs.next())
        {
            status = rs.getString("status");
            color = rs.getString("color");
            start = rs.getTime("start_time");
            end = rs.getTime("end_time");
            parameters.add(status);
            parameters.add(color);
            parameters.add(start.toString());
            parameters.add(end.toString());
        }

        connection.close();

        return parameters;
    }
}