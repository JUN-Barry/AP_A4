package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConstructorClass.PostInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RetrievePostModel {
	
	private static RetrievePostModel instance = null;

    // Singleton pattern, ensures that only one instance of the class exists throughout your application's lifecycle
    private RetrievePostModel() {
    }

    // Public method to access the instance (Singleton pattern)
    public static RetrievePostModel getInstance() {
        if (instance == null) {
            instance = new RetrievePostModel();
        }
        return instance;
    }
	
	
	
	
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

	
	public static boolean ExportSinglePost(String csvFilePath, int postID, String fileName) {
	    // Declare FileWriter outside the try block to make it available later
	    try (FileWriter writer = new FileWriter(csvFilePath + "\\" + fileName + ".csv")) {

	        // Write column headers to the CSV file
	        writer.append("id");
	        writer.append(",");
	        writer.append("content");
	        writer.append(",");
	        writer.append("author");
	        writer.append(",");
	        writer.append("likes");
	        writer.append(",");
	        writer.append("shares");
	        writer.append(",");
	        writer.append("datetime");
	        writer.append(",");
	        // ... continue for all columns

	        writer.append("\n");

	        // Retrieve the PostInfo object based on the postID (you need to implement this method)
	        PostInfo post = RetrievePostModel.RetrieveSinglePost(postID);

	        // Check if the post is null (i.e., post with the given ID not found)
	        if (post == null) {
	            System.out.println("Post with ID " + postID + " not found.");
	            return false;
	        }

	        // Write data to the CSV file
	        writer.append(String.valueOf(post.getId()));
	        writer.append(",");
	        writer.append(post.getContent());
	        writer.append(",");
	        writer.append(post.getAuthor());
	        writer.append(",");
	        writer.append(String.valueOf(post.getLikes()));
	        writer.append(",");
	        writer.append(String.valueOf(post.getShares()));
	        writer.append(",");
	        writer.append(post.getDatetime());
	        writer.append(",");

	        writer.append("\n");

	        return true;

	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}
