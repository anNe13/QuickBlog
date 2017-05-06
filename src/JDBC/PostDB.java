package JDBC;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostDB {
    Connector connector = new Connector();
    Connection con;
    PreparedStatement statement = null;

    public void post(String title, String content, String userName, String location) {
        con = connector.getConnection();

        try {
            statement = con.prepareStatement("INSERT INTO post (title, content, username, location) VALUES (?,?,?,?)");
            statement.setString(1, title);
            statement.setString(2,content);
            statement.setString(3,userName);
            statement.setString(4,location);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (statement != null) statement.close();
            } catch (Exception e) {
            }
            try {
                if (con != null) con.close();
            } catch (Exception e) {
            }

    }
    }
}
