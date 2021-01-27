package com.bodymass.app.db.dao;

import com.bodymass.app.db.model.User;
import com.bodymass.app.db.model.Weight;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeightDAO extends AbstractDAO {
    public List<Weight> selectLastWeek(long userId) throws SQLException {
        List<Weight> result = new ArrayList<>();

        Connection conn = getConnection();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT id, user_id, data, value FROM weight WHERE (data BETWEEN (NOW() - INTERVAL 7 DAY) AND NOW()) AND user_id=?");
        preparedStatement.setLong(1, userId);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Weight weight = new Weight(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getDate("data"),
                    rs.getDouble("value")
            );
            result.add(weight);
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

    public List<Weight> selectLastTwoWeeks(long userId) throws SQLException {
        List<Weight> result = new ArrayList<>();

        Connection conn = getConnection();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT id, user_id, data, value FROM weight WHERE (data BETWEEN (NOW() - INTERVAL 14 DAY) AND NOW()) AND user_id=?");
        preparedStatement.setLong(1, userId);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Weight weight = new Weight(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getDate("data"),
                    rs.getDouble("value")
            );
            result.add(weight);
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

    public List<Weight> selectLastMonth(long userId) throws SQLException {
        List<Weight> result = new ArrayList<>();

        Connection conn = getConnection();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT id, user_id, data, value FROM weight WHERE (data BETWEEN (NOW() - INTERVAL 1 MONTH) AND NOW()) AND user_id=?");
        preparedStatement.setLong(1, userId);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Weight weight = new Weight(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getDate("data"),
                    rs.getDouble("value")
            );
            result.add(weight);
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

    public List<Weight> selectLastHalfAYear(long userId) throws SQLException {
        List<Weight> result = new ArrayList<>();

        Connection conn = getConnection();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT id, user_id, data, value FROM weight WHERE (data BETWEEN (NOW() - INTERVAL 6 MONTH) AND NOW()) AND user_id=?");
        preparedStatement.setLong(1, userId);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Weight weight = new Weight(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getDate("data"),
                    rs.getDouble("value")
            );
            result.add(weight);
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

    public List<Weight> selectLastYear(long userId) throws SQLException {
        List<Weight> result = new ArrayList<>();

        Connection conn = getConnection();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT id, user_id, data, value FROM weight WHERE (data BETWEEN (NOW() - INTERVAL 1 YEAR) AND NOW()) AND user_id=?");
        preparedStatement.setLong(1, userId);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Weight weight = new Weight(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getDate("data"),
                    rs.getDouble("value")
            );
            result.add(weight);
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

    public List<Weight> selectPeriod(long userId, Date from, Date to) throws SQLException {
        List<Weight> result = new ArrayList<>();

        Connection conn = getConnection();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT id, user_id, data, value FROM weight WHERE (data BETWEEN ? AND ?) AND user_id=?");
        preparedStatement.setDate(1, from);
        preparedStatement.setDate(2, to);
        preparedStatement.setLong(3, userId);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Weight weight = new Weight(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getDate("data"),
                    rs.getDouble("value")
            );
            result.add(weight);
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

    public void addWeightDAO(long userId, Date data, double value) throws SQLException {
        List<Weight> result = new ArrayList<>();

        Connection conn = getConnection();

        PreparedStatement preparedStatement = conn.prepareStatement("insert into weight (user_id, data, value) values(?, ?, ?)");
        preparedStatement.setLong(1, userId);
        preparedStatement.setDate(2, data);
        preparedStatement.setDouble(3, value);

        preparedStatement.execute();

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

    public Double getLastWeight(long userId) throws SQLException {
        Connection conn = getConnection();

        PreparedStatement preparedStatement = conn.prepareStatement("select value from weight where user_id=? order by data desc limit 1");
        preparedStatement.setLong(1, userId);
        ResultSet rs = preparedStatement.executeQuery();
        Double result;
        if(rs.next()) {
            result = rs.getDouble(1);
        }
        else{
            result = null;
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