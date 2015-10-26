package common.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private static Connection conn = null;

	public static Connection getConnection() throws Exception {
		if (conn != null)
			return conn;
		else {
			 // database URL
            String dbUrl = "jdbc:mysql://localhost:3306/training?characterEncoding=UTF-8";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String connectionURL = dbUrl;
				  // set the url, username and password for the database
				conn = DriverManager.getConnection(connectionURL, "root", "");
			} catch (SQLException e) {
				System.out.println("Data Connection Error: " + e.getMessage());
				throw e;
			}
			return conn;
		}
	}
}
