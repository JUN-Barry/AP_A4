package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.DataAnalyticsHubConnection;

public class SignUpUserInfoTable {
	public static void main(String[] args) {
		final String TABLE_NAME = "User_info";
		String sql = "INSERT INTO " + TABLE_NAME + " (user_name, password, first_name, last_name)"
				+ " VALUES (?, ?, ?, ?)";

		try (Connection con = DataAnalyticsHubConnection.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);) {
			stmt.setString(1, "Jac");
			stmt.setString(2, "123");
			stmt.setString(3, "Barry");
			stmt.setString(4, "YAN");

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
