package com.sliit.FoodGram.repository;

import com.sliit.FoodGram.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {  // This interface provides CRUD operations for Comment documents in MongoDB

    List<Comment> findByPostId(String postId);
}
