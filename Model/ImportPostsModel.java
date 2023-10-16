package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import ConstructorClass.PostInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ImportPostsModel {
	
	public static ObservableList<PostInfo> ImportPostfromCSV(String filePath) {
	    ObservableList<PostInfo> validImportPosts = FXCollections.observableArrayList();
	    
	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        // Skip the first header line
	        reader.readLine();

	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split(",");

	            if (parts.length == 6) {
	                String idText = parts[0];
	                String content = parts[1];
	                String author = parts[2];
	                String likesText = parts[3];
	                String sharesText = parts[4];
	                String datetime = parts[5];

	                // Check if any field is empty
	                if (idText.isEmpty() || content.isEmpty() || author.isEmpty() || likesText.isEmpty() || sharesText.isEmpty() || datetime.isEmpty()) {
	                    System.out.println("Please fill in all fields.");
	                    continue; // Skip this line and continue with the next one
	                }

	                try {
	                    int id = Integer.parseInt(idText);
	                    int likes = Integer.parseInt(likesText);
	                    int shares = Integer.parseInt(sharesText);

	                    // Additional validation for data format
	                    if (CheckPostFormatModel.isNonNegativeInt(id) && CheckPostFormatModel.hasNoComma(content)
	                            && CheckPostFormatModel.hasNoComma(author) && CheckPostFormatModel.isNonNegativeInt(likes)
	                            && CheckPostFormatModel.isNonNegativeInt(shares)
	                            && CheckPostFormatModel.isValidDateTime(datetime)) {

	                        if (PostInfoModel.CheckIDexist(id)) {
	                            PostInfo post = new PostInfo(id, content, author, likes, shares, datetime);
	                            validImportPosts.add(post);
	                        } else {
	                            System.out.println("ID already exists in the system. Please choose a different one.");
	                        }
	                    } else {
	                        System.out.println("Please enter valid content. Check the format.");
	                    }

	                } catch (NumberFormatException e) {
	                    System.out.println("ID, Likes, and Shares must be valid integers.");
	                }
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return validImportPosts;
	}



}
