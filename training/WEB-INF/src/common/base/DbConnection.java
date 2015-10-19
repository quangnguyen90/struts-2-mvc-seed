package common.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {
	Connection conn = null;
	
	public Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String connectionURL = "jdbc:mysql://localhost:3306/training?characterEncoding=UTF-8";
			conn = DriverManager.getConnection(connectionURL,"root","");
		} catch (SQLException e) {
			throw e;
		}
		return conn;
	}
}
