package com.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	public static Connection getConnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			String url = "jdbc:mysql://localhost:3306/test";
			String username = "root";
			String password = "root";
			conn = DriverManager.getConnection(url,username,password);
			return conn;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
