package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.DataAnalyticsHubConnection;

public class InsertRawPreparedUserInfoTable {
	public static void main(String[] args) {
		final String TABLE_NAME = "User_info";
		String sql = "INSERT INTO " + TABLE_NAME + " (user_name, password, first_name, last_name)"
				+ " VALUES (?, ?, ?, ?)";
		
//		String sql = "INSERT INTO " + TABLE_NAME + " (user_name, password, first_name, last_name, role)"
//				+ " VALUES (?, ?, ?, ?, ?)";

		try (Connection con = DataAnalyticsHubConnection.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);) {
			stmt.setString(1, "Bay");
			stmt.setString(2, "123");
			stmt.setString(3, "BA");
			stmt.setString(4, "Xing");
			//stmt.setString(5, "1"); // 1 means VIP
			//stmt.setString(5, "2"); // 2 means Admin

			int result = stmt.executeUpdate();

			if (result == 1) {
				System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
				System.out.println(result + " row(s) affected");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
}
