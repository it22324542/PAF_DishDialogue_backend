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
    @Id
    private String id;
    private String content;
    private String postId;
    private String userId;
    private String username;
    private String createdAt;

}