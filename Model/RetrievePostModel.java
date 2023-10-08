package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConstructorClass.PostInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RetrievePostModel {
	public static PostInfo RetrieveSinglePost(int ID) {
	    String sql = "SELECT ID, content, author, likes, shares, Date FROM Post_info WHERE ID = ?";
	    PostInfo result = null;

	    try (Connection con = DataAnalyticsHubConnection.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {

	        pstmt.setInt(1, ID);

	        ResultSet PostresultSet = pstmt.executeQuery();

	        if (PostresultSet.next()) {
	            int id = PostresultSet.getInt("ID");
	            String content = PostresultSet.getString("content");
	            String author = PostresultSet.getString("author");
	            int likes = PostresultSet.getInt("likes");
	            int shares = PostresultSet.getInt("shares");
	            String datetime = PostresultSet.getString("Date");

	            result = new PostInfo(id, content, author, likes, shares, datetime);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return result;
	}


	public static ObservableList<PostInfo> RetrieveAllUserTopPosts(int limit) {
	    String sql = "SELECT ID, content, author, likes, shares, Date FROM Post_info ORDER BY likes DESC LIMIT ?";
	    ObservableList<PostInfo> topPosts = FXCollections.observableArrayList();

	    try (Connection con = DataAnalyticsHubConnection.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {

	        pstmt.setInt(1, limit );

	        ResultSet PostresultSet = pstmt.executeQuery();

	        while (PostresultSet.next()) {
	            int id = PostresultSet.getInt("ID");
	            String content = PostresultSet.getString("content");
	            String author = PostresultSet.getString("author");
	            int likes = PostresultSet.getInt("likes");
	            int shares = PostresultSet.getInt("shares");
	            String datetime = PostresultSet.getString("Date");

	            PostInfo post = new PostInfo(id, content, author, likes, shares, datetime);
	            topPosts.add(post);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return topPosts;
	}


	public static ObservableList<PostInfo> RetrieveOneUSERTopPosts(String Targetauthor, int limit) {
	    String sql = "SELECT ID, content, author, likes, shares, Date FROM Post_info WHERE author = ?  ORDER BY likes DESC LIMIT ?";
	    ObservableList<PostInfo> topPosts = FXCollections.observableArrayList();

	    try (Connection con = DataAnalyticsHubConnection.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {

	        pstmt.setString(1, Targetauthor);
	        pstmt.setInt(2, limit);

	        ResultSet PostresultSet = pstmt.executeQuery();

	        while (PostresultSet.next()) {
	            int id = PostresultSet.getInt("ID");
	            String content = PostresultSet.getString("content");
	            String author = PostresultSet.getString("author");
	            int likes = PostresultSet.getInt("likes");
	            int shares = PostresultSet.getInt("shares");
	            String datetime = PostresultSet.getString("Date");

	            PostInfo post = new PostInfo(id, content, author, likes, shares, datetime);
	            topPosts.add(post);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return topPosts;
	}

}
