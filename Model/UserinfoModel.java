package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserinfoModel {
	
	private static UserinfoModel instance = null;

    // Singleton pattern, ensures that only one instance of the class exists throughout your application's lifecycle
    private UserinfoModel() {
    }

    // Public method to access the instance (Singleton pattern)
    public static UserinfoModel getInstance() {
        if (instance == null) {
            instance = new UserinfoModel();
        }
        return instance;
    }

	public static boolean CheckUsername(String username) {
		String sql = "SELECT * FROM User_info WHERE user_name = ?";

		try (Connection con = DataAnalyticsHubConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, username);
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
	
	public static boolean CheckAdmin(String username) {
		String sql = "SELECT * FROM User_info WHERE user_name = ? AND role = 2";

		try (Connection con = DataAnalyticsHubConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, username);
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
	
	public static boolean validUserToEditProfile(String username) {
	    if (!CheckAdmin(SharedUsernameModel.getUsername())){
	        return true;  //Admin can edit anyone's profile
	    } else if (username.equals(SharedUsernameModel.getUsername())) {
	        return true; // normal user can only edit his own profile
	    } else {
	        return false;
	    }
	}

	

	public static void SignUpUserInfoINTOTable(String username, String password, String firstName, String lastName) {
		final String TABLE_NAME = "User_info";
		String sql = "INSERT OR REPLACE INTO " + TABLE_NAME + " (user_name, password, first_name, last_name)"
				+ " VALUES (?, ?, ?, ?)"; // update here, use replace, considering the primary key

		try (Connection con = DataAnalyticsHubConnection.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, firstName);
			stmt.setString(4, lastName);

			int result = stmt.executeUpdate();

			if (result == 1) {
				System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
				System.out.println(result + " row(s) affected");

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void deleteUserInfoFromTable(String username) {
		final String TABLE_NAME = "User_info";
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE user_name = ?";

		try (Connection con = DataAnalyticsHubConnection.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, username);

			int result = stmt.executeUpdate();

			if (result > 0) {
				System.out.println("Delete from table " + TABLE_NAME + " executed successfully");
				System.out.println(result + " row(s) affected");
			} else {
				System.out.println("No records found for username: " + username);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void upgradeVIP(String username) {
		final String TABLE_NAME = "User_info";
		String sql = "UPDATE " + TABLE_NAME + " SET role = 1 WHERE user_name = ?";

		try (Connection con = DataAnalyticsHubConnection.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, username);

			int result = stmt.executeUpdate();

			if (result == 1) {
				System.out.println("Update in table " + TABLE_NAME + " executed successfully");
				System.out.println(result + " row(s) affected");
			} else {
				System.out.println("No user found with username: " + username);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}


}
