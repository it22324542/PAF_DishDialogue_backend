package com.sliit.FoodGram.repository;

import com.sliit.FoodGram.model.Story;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoryRepository extends MongoRepository<Story, String> {
}