package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConstructorClass.UserInfo;

public class ValidUserLoginModel {

	public static String authenticateUser(String username, String password) {
	    String sql = "SELECT first_name, last_name FROM User_info WHERE user_name = ? AND password = ?";
	    // only the two condition meet together, we can return valid value
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
	
	// use the constructor here, retrieve the data from DB based on the user name, write it in visual studio first
	public static UserInfo getUserInfo(String username) {
	    String sql = "SELECT * FROM User_info WHERE user_name = ? ";
	    
	    try (Connection con = DataAnalyticsHubConnection.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {

	        pstmt.setString(1, username);

	        try (ResultSet resultSet = pstmt.executeQuery()) {
	            if (resultSet.next()) {
	                String password = resultSet.getString("password");
	                String firstName = resultSet.getString("first_name");
	                String lastName = resultSet.getString("last_name");

	                UserInfo user = new UserInfo(username, password, firstName, lastName);
	                return user; // Return the UserInfo object if authentication succeeds.
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return null; // Return null if authentication fails.
	}
	
	public static boolean CheckVIP(String username) {
	    String sql = "SELECT isVip FROM User_info WHERE user_name = ?";

	    try (Connection con = DataAnalyticsHubConnection.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {

	        pstmt.setString(1, username);

	        try (ResultSet resultSet = pstmt.executeQuery()) {
	            if (resultSet.next()) {
	                return resultSet.getBoolean("isVip");
	            } else {
	                return false; // No user found with the given username
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}




}

