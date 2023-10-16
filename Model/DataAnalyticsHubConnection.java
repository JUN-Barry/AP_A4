package Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataAnalyticsHubConnection {
	
	private static DataAnalyticsHubConnection instance = null;

    // Singleton pattern, ensures that only one instance of the class exists throughout your application's lifecycle
    private DataAnalyticsHubConnection() {
    }

    // Public method to access the instance (Singleton pattern)
    public static DataAnalyticsHubConnection getInstance() {
        if (instance == null) {
            instance = new DataAnalyticsHubConnection();
        }
        return instance;
    }

	private static final String DB_URL = "jdbc:sqlite:Data_Analytics_hub.db";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL);
	}
}
