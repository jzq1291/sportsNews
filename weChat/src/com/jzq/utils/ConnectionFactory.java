package com.jzq.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class ConnectionFactory {
	private static String DRIVER;
	private static String URL;
	private static String USER;
	private static String PASSWORD;
	static {
		Properties pro = new Properties();
		try {			
			pro.load(ConnectionFactory.class.getClassLoader().getResourceAsStream("com/jzq/utils/db.properties"));
			
			DRIVER = pro.getProperty("driver");
			URL = pro.getProperty("url");
			USER = pro.getProperty("user");
			PASSWORD = pro.getProperty("password");
//			DRIVER = "com.mysql.jdbc.Driver";
//			URL = "jdbc:mysql://localhost/sports_news";
//			USER = "root";
//			PASSWORD = "";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void close(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Statement st, Connection conn) {
		close(null, st, conn);
	}

}
