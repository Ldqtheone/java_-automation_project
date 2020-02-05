package com.mycompany.javadomo.SqlQuery;

import com.mycompany.javadomo.Configuration.Cache;
import com.mycompany.javadomo.JavaDatabaseConnect;

import java.io.IOException;
import java.sql.*;

public class CameraQuery {

    public CameraQuery() {
    }

    public ResultSet getAllCamera() throws Throwable {
        Connection connection = JavaDatabaseConnect.connectDatabase();
        Cache cache = new Cache();

        String request = "SELECT C.`id`, `camera_name`, `status`, `room_name`, `detection_range`, `start_time`," +
                " `end_time` FROM `camera` as C " +
                " LEFT JOIN rooms as R " +
                " ON C.`room_id` = R.`id` WHERE R.`home_id` = " + cache.userHome + "";
        PreparedStatement data = connection.prepareStatement(request);

        return data.executeQuery();
    }

    public void updateCamera(String detection, String status, int id, Time start, Time end) throws SQLException, IOException {
        Connection connection = JavaDatabaseConnect.connectDatabase();

        String request = "UPDATE `camera` SET status = ? , detection_range = ?, start_time = ?, end_time = ? WHERE id = ?";

        PreparedStatement ps = connection.prepareStatement(request);

        ps.setString(1, status);
        ps.setString(2, detection);
        ps.setTime(3, start);
        ps.setTime(4, end);
        ps.setInt(5, id);

        ps.execute();
    }

    public ResultSet getAllPictures() throws Throwable {
        Connection connection = JavaDatabaseConnect.connectDatabase();
        Cache cache = new Cache();

        String request = "SELECT P.`id` as PhotoId, `camera_id`, `capture_date`, `photo_path`," +
                " R.`room_name`, C.`room_id` as RoomId, C.camera_name" +
                " FROM photo as P " +
                " LEFT JOIN camera as C" +
                " ON P.camera_id = C.id" +
                " LEFT JOIN rooms as R " +
                " ON C.`room_id` = R.`id` WHERE R.`home_id` = " + cache.userHome + "";
        PreparedStatement data = connection.prepareStatement(request);

        return data.executeQuery();
    }

    public ResultSet getDelCamera() throws Throwable {
        Connection connection = JavaDatabaseConnect.connectDatabase();
        Cache cache = new Cache();

        String request = "SELECT camera_name, C.id from `camera` as C LEFT JOIN rooms as R " +
                " ON C.`room_id` = R.`id` WHERE R.`home_id` = " + cache.userHome + "";

        PreparedStatement data = connection.prepareStatement(request);

        return data.executeQuery();
    }

    public void delCamera(int id) throws Throwable {
        Connection connection = JavaDatabaseConnect.connectDatabase();

        String request = "DELETE FROM camera WHERE id = ?";

        PreparedStatement data = connection.prepareStatement(request);

        data.setInt(1,id);

        data.addBatch();
        data.executeBatch();
    }

    public void createCamera(String nameValue, Time start, Time end, String room, String status, String range) throws IOException, SQLException {

        Connection connection = JavaDatabaseConnect.connectDatabase();
        Cache cache = new Cache();

        String req = "SELECT `id` FROM `rooms` WHERE `room_name` = '" + room + "' AND `home_id` = "+ cache.userHome+"";

        PreparedStatement data = connection.prepareStatement(req);

        ResultSet res = data.executeQuery();

        while (res.next()) {

            int roomId = res.getInt("id");

            String request = "INSERT INTO `camera`(`camera_name`, `room_id`, `status` , `detection_range` ," +
                    " `start_time`, `end_time`) VALUES (?,?,?,?,?,?)";

            PreparedStatement ps = connection.prepareStatement(request);

            ps.setString(1, nameValue);
            ps.setInt(2, roomId);
            ps.setString(3, status);
            ps.setString(4, range);
            ps.setTime(5, start);
            ps.setTime(6, end);


            ps.addBatch();
            ps.executeBatch();
        }
    }
}