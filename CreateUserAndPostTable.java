

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateUserAndPostTable {
	public static void main(String[] args) {
		final String TABLE0_NAME = "User_info";
		final String TABLE1_NAME = "Post_info";

		try (Connection con = DataAnalyticsHubConnection.getConnection();
				Statement stmt = con.createStatement();) {
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE0_NAME 
										+ "(user_name String NOT NULL,"
										+ "password VARCHAR(8) NOT NULL,"
										+ "first_name VARCHAR(20) NOT NULL,"
										+ "last_name VARCHAR(20) NOT NULL,"
										+ "isVip int DEFAULT 0,"
										+ "PRIMARY KEY (user_name))");
			
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE1_NAME 
					+ "(ID int NOT NULL,"
					+ "content String NOT NULL,"
					+ "author String NOT NULL,"
					+ "likes int NOT NULL,"
					+ "shares int NOT NULL,"
					+ "Date String NOT NULL,"
					+ "PRIMARY KEY (ID))");
			
			System.out.print("Create table successfully");	
			} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
