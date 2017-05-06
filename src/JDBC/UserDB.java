package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDB {
    Connector connector = new Connector();
    Connection con;
    ResultSet resultSet = null;
    PreparedStatement statement = null;

    public boolean isValid(String userName, String password) {
        con = connector.getConnection();

        boolean foundAccount = false;

        try {
            statement = con.prepareStatement
                    ("SELECT COUNT(*) FROM useraccount WHERE username = ? AND password = ?");
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
            closeAll();
        }

        return foundAccount;


    }
    public boolean userNameExist(String userName) {
        con = connector.getConnection();
        boolean foundAccountUsername = false;
        try {
            statement = con.prepareStatement
                    ("SELECT COUNT(*) FROM useraccount WHERE username = ?");
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
            closeAll();
        }
        return foundAccountUsername;
    }


    public void tryRegisterUser(String username, String password) {
        con = connector.getConnection();
        if (con != null) {
            try {
                statement = con.prepareStatement("INSERT INTO useraccount (username, password)VALUES(?, ?)");
                statement.setString(1, username);
                statement.setString(2, password);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeAll();
            }

        }
    }

    private void closeAll() {
        try {
            if (con != null) {
                con.close();
            }
            if (statement != null) {
                statement.close();
            }

            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
