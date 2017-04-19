package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDB {
    Connector connector = new Connector();
    Connection con;
    ResultSet resultSet = null;
    PreparedStatement statement = null;

    public boolean isValid(String userName, String password) {
con = connector.getConnection();
        boolean foundAccount = false;

        try {
            statement = con.prepareStatement
                    ("SELECT COUNT(*) FROM useraccount WHERE Username = ? AND Userpassword = ?");
            statement.setString(1, userName);
            statement.setString(2, password);

            resultSet = statement.executeQuery();


            while (resultSet.next()) {
                if (resultSet.getInt(1) == 1) {
                    foundAccount = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (Exception e) {
            }
            try {
                if (statement != null) statement.close();
            } catch (Exception e) {
            }
            try {
                if (con != null) con.close();
            } catch (Exception e) {
            }
            return foundAccount;
        }


    }

    public boolean userNameExist (String userName){
        con = connector.getConnection();
        boolean foundAccountUsername = false;
        try {
            statement = con.prepareStatement
                    ("SELECT COUNT(*) FROM Useraccount WHERE Username = ?");
            statement.setString(1, userName);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getInt(1) == 1) {
                    foundAccountUsername = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (Exception e) {
            }
            try {
                if (statement != null) statement.close();
            } catch (Exception e) {
            }
            try {
                if (con != null) con.close();
            } catch (Exception e) {
            }
            return foundAccountUsername;
        }


    }

}
