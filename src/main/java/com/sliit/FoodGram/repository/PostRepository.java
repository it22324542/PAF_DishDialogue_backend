package com.sliit.FoodGram.repository;

import com.sliit.FoodGram.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {}