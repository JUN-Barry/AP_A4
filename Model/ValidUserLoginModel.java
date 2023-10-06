package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidUserLoginModel {

	public static String authenticateUser(String username, String password) {
	    String sql = "SELECT first_name, last_name FROM User_info WHERE user_name = ? AND password = ?";
	    
	    try (Connection con = DataAnalyticsHubConnection.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {

	        pstmt.setString(1, username);
	        pstmt.setString(2, password);

	        try (ResultSet resultSet = pstmt.executeQuery()) {
	            if (resultSet.next()) {
	                String firstName = resultSet.getString("first_name");
	                String lastName = resultSet.getString("last_name");
	                String fullName = firstName + " " + lastName;
	                
	                return fullName; // Return the full name if authentication succeeds.
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return null; // Return null if authentication fails.
	}
	
	// write a method to get fullName from the above method

}

