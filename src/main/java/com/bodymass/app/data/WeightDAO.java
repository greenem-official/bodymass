package com.bodymass.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
}