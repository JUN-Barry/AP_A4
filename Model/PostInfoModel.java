package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostInfoModel {
	private static PostInfoModel instance = null;

    // Singleton pattern, ensures that only one instance of the class exists throughout your application's lifecycle
    private PostInfoModel() {
    }

    // Public method to access the instance (Singleton pattern)
    public static PostInfoModel getInstance() {
        if (instance == null) {
            instance = new PostInfoModel();
        }
        return instance;
    }

	public static boolean CheckIDexist(int ID) {
		String sql = "SELECT * FROM Post_info WHERE ID = ?";

		try (Connection con = DataAnalyticsHubConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			String id=String.valueOf(ID);
			pstmt.setString(1, id);
			
			try (ResultSet resultSet = pstmt.executeQuery()) {
				if (resultSet.next()) {
					return false;
				} else {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean CheckAuthorExist(String author) {
	    String sql = "SELECT * FROM Post_info WHERE author = ?";

	    try (Connection con = DataAnalyticsHubConnection.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {

	        pstmt.setString(1, author);

	        try (ResultSet resultSet = pstmt.executeQuery()) {
	            if (resultSet.next()) {
	                return true; // Author exists in the table
	            } else {
	                return false; // Author doesn't exist in the table
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; // An error occurred
	    }
	}


	public static void PostInfoIntoTable(int id, String content, String author, int likes, int shares,
			String datetime) {
		final String TABLE_NAME = "Post_info";
		String sql = "INSERT INTO " + TABLE_NAME + " (ID, content, author, likes, shares, Date)" //IN DB, we call it Date
				+ " VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection con = DataAnalyticsHubConnection.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);) {

			stmt.setInt(1, id);
			stmt.setString(2, content);
			stmt.setString(3, author);
			
			stmt.setInt(4, likes);
			stmt.setInt(5, shares);
			
			stmt.setString(6, datetime);

			int result = stmt.executeUpdate();

			if (result == 1) {
				System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
				System.out.println(result + " row(s) affected");

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void removePost(int ID) {
		final String TABLE_NAME = "Post_info";
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";

		try (Connection con = DataAnalyticsHubConnection.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, ID);

			int result = stmt.executeUpdate();

			if (result > 0) {
				System.out.println("Delete from table " + TABLE_NAME + " executed successfully");
				System.out.println(result + " row(s) affected");
			} else {
				System.out.println("No records found for username: " + ID);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
