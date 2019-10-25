package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	public static Connection getConnection() throws SQLException {
		String url = System.getenv("AdventureTimeDatabaseUrl");
		String username = System.getenv("AdventureTimeDatabaseUser");
		String password = System.getenv("AdventureTimePassword");
		
		return DriverManager.getConnection(url, username, password);
	}
}
