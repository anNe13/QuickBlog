package JDBC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostDB {
    Connector connector = new Connector();
    Connection con;
    PreparedStatement statement = null;

    public void post(String tittle, String content, String userName, Date date){
        con = connector.getConnection();

        try {
            statement = con.prepareStatement("INSERT INTO blogpost (Tittle, Content, Username, NowDate) VALUES (?,?,?,?)");
            statement.setString(1, tittle);
            statement.setString(2,content);
            statement.setString(3,userName);
            statement.setDate(4, date);

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
