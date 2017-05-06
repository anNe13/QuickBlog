package JDBC;

import Controller.Popup.Popup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connector {

    private String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11169993";
    private String user = "sql11169993";
    private String password = "c7SrTNlbys";
    Connection con;
    Popup popup = new Popup();

            public Connection getConnection() {

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(url, user, password);
                } catch (SQLException e) {

                    popup.warning("Could not get connection", "Please make sure you are connected to internet");

                } catch (ClassNotFoundException e) {
                    popup.warning("Internal connection-driverproblem", e.getMessage() +  " not working");

                }
                return con;
            }


}
