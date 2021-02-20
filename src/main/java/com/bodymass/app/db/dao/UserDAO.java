package com.bodymass.app.db.dao;

import com.bodymass.app.crypto.Crypter;
import com.bodymass.app.db.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Tools for getting useful data about USERS from database
 */

public class UserDAO extends AbstractDAO {
    public List<User> selectAllUsers() throws SQLException {
        List<User> result = new ArrayList<>();

        Connection conn = getConnection();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, email, password FROM users");
        while (rs.next()) {
            int id = rs.getInt("id");
            String email = rs.getString("email");
            String password = Crypter.decode(rs.getString("password"));

            result.add(new User(id, email, password));
        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqlEx) {
            } // ignore
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqlEx) {
            } // ignore
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException sqlEx) {
            } // ignore
        }
        return result;
    }

    public int addUser(User user) throws SQLException {
        Connection conn = getConnection();

        PreparedStatement preparedStatement = conn
                .prepareStatement("INSERT INTO users(email, password) VALUES(?, ?)");
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, Crypter.encode(user.getPassword()));
        int row = preparedStatement.executeUpdate();

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException sqlEx) {
            } // ignore
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException sqlEx) {
            } // ignore
        }
        return 0;
    }

    public void deleteUsers(User user) throws SQLException {
        Connection conn = getConnection();

        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM users WHERE id=?");
        preparedStatement.setLong(1, user.getId());
        int row = preparedStatement.executeUpdate();

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException sqlEx) {
            } // ignore
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException sqlEx) {
            } // ignore
        }
    }

    public void updateUsers(User user) throws SQLException {
        Connection conn = getConnection();

        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE users SET password=? where email=?");
        preparedStatement.setString(1, Crypter.encode(user.getPassword()));
        preparedStatement.setString(2, user.getEmail());
        int row = preparedStatement.executeUpdate();

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException sqlEx) {
            } // ignore
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException sqlEx) {
            } // ignore
        }
    }

    public User findUser(String email, String password) throws SQLException {

        Connection conn = getConnection();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT id, email, password FROM users WHERE email=? and password=?");
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, Crypter.encode(password));

        ResultSet rs = preparedStatement.executeQuery();
        int id = 0;
        String newEmail = "";
        String newPassword = "";
        if (rs.next()) {
            id = rs.getInt("id");
            newEmail = rs.getString("email");
            newPassword = Crypter.decode(rs.getString("password"));
        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqlEx) {
            } // ignore
        }

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException sqlEx) {
            } // ignore
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException sqlEx) {
            } // ignore
        }
        return new User(id, newEmail, newPassword);
    }

    public boolean ifUserExistsByEmail(String email) throws SQLException {

        Connection conn = getConnection();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT id FROM users WHERE email=?");
        preparedStatement.setString(1, email);

        ResultSet rs = preparedStatement.executeQuery();
        boolean result = false;
        if (rs.next()) {
            result = true;
        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqlEx) {
            } // ignore
        }

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException sqlEx) {
            } // ignore
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException sqlEx) {
            } // ignore
        }
        return result;
    }
}