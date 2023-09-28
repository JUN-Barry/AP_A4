

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataAnalyticsHubConnection {

	private static final String DB_URL = "jdbc:sqlite:Data_Analytics_hub.db";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL);
	}
}
