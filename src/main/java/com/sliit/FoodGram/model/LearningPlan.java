package com.sliit.FoodGram.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "learning_plans")
public class LearningPlan {

    // Getters and Setters
    @Id
    private String id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private String userId;

    // Constructors
    public LearningPlan() {
        this.createdAt = LocalDateTime.now();
    }

}