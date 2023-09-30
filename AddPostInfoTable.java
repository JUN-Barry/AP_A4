import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddPostInfoTable {
	public static void main(String[] args) {
		final String TABLE_NAME = "Post_info";
		String sql = "INSERT INTO " + TABLE_NAME + " (ID,content,author,likes,shares,Date)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection con = DataAnalyticsHubConnection.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);) {
			stmt.setInt(1, 20582);
			stmt.setString(2, "Come and meet us at Building 14 of RMIT.");
			stmt.setString(3, "SD2C45");
			stmt.setInt(4, 10);
			stmt.setInt(5, 24);
			stmt.setString(6, "12/05/2023 10:10");
			

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
