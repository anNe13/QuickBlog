package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connector {
    private String url = "jdbc:mysql://localhost:3306/myBlogg";
    private String user = "root";
    private String password = "tkyxxq";

            public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("couldnt get connection");
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }



}}
