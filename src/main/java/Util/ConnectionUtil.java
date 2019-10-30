package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	public static Connection getConnection() throws SQLException {
		String url = System.getenv("Hogwarts_Database");
		String username = System.getenv("Hogwarts_User");
		String password = System.getenv("Hogwarts_Password");
		
		return DriverManager.getConnection(url, username, password);
	}
}
