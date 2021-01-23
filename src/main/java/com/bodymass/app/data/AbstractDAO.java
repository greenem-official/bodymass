package com.bodymass.app.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AbstractDAO {
	private String url = "jdbc:mysql://192.168.1.72/mydb?" + "useUnicode=true&serverTimezone=UTC" + "&user=root2&password=root2";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	protected Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(url);
		return conn;
	}
}