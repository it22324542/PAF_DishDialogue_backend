


package com.sliit.FoodGram.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Document(collection = "posts")
public class Post {
    // Getters and setters
    @Id
    private String id;
    private String title;
    private String content;
    private String imageUrl;
    private String userId;
    private String username;
    private String createdAt;
    private int likes;
    private List<String> ingredients;
    private List<String> steps;
    private List<String> tags;
    

}