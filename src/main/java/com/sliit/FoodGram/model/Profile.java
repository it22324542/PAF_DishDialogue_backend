package com.sliit.FoodGram.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "profiles")
public class Profile {
    @Id
    private String id;
    private String userId;
    private String username;
    private String fullName;
    private String bio;
    private String profilePictureUrl;
    private String email;
    private int followers = 0;
    private int following = 0;
    private int recipeCount = 0;
}