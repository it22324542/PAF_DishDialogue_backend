package com.sliit.FoodGram.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Story {
    private String id;
    @NotBlank(message = "Content is required")
    private String content;
    @NotBlank(message = "Image URL is required")
    private String imageUrl;
    @NotBlank(message = "User ID is required")
    private String userId;
    @NotBlank(message = "Username is required")
    private String username;
    private String createdAt;
    private String expiresAt;
    private List<String> viewedBy = new ArrayList<>();

}