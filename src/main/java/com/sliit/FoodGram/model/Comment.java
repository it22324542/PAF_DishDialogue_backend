package com.sliit.FoodGram.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "comments")
public class Comment {
    // Getters and setters
    
    @Id      // Unique identifier for each comment (MongoDB _id field)

    private String id;   

    private String content;  // The content/text of the comment

    private String postId;      // ID of the post this comment is related to

    private String userId;  // ID of the user who made the comment

    private String username;   // Username of the commenter

    private String createdAt;   // Timestamp when the comment was created


}